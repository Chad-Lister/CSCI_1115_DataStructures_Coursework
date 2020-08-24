/*
    Author:  Modified by Chad Lister
    Date:  08/24/2020
    
    Modified BTView.java to display Horizontally instead of vertically as per Exercise 25 -11.
    
    (Display tree) Write a new view class that displays the tree horizontally with the root on the left as shown in Figure 25.23.

    Download these files to start.
*/

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;



public class BTView extends Pane {

    private BST<Integer> tree = new BST<>();
    private double radius = 15; // Tree node radius
    private double vGap = 50; // Gap between two levels in a tree

    BTView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getHeight() / 4, getHeight() / 2, vGap);            //  Changed to height and modified by Chad Lister.
        }
    }

    //  Modified by Chad Lister.
    /**
     * Display a subtree rooted at position (x, y)
     */
    private void displayTree(BST.TreeNode<Integer> root,
            double x, double y, double hGap) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayTree(root.left, x + hGap, y + vGap, vGap / 2);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y - vGap, x, y));
            // Draw the right subtree recursively
            displayTree(root.right, x + hGap, y - vGap, vGap / 2);
        }

        // Display a node
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.element + ""));
    }
}
