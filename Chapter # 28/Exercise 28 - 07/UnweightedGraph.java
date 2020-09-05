/*
  Date: 08/05/2020
  
  (Find a cycle) Add a new method in UnweightedGraph to find a cycle in the graph with the following header:

  public List<Integer> getACycle()
  The method returns a List that contains all the vertices in a cycle starting from u. If the graph doesn’t have any cycles, the method returns null.

  The output should be:

  Find a cycle [8, 10, 11]
  Find a cycle [3, 1, 0]
  or

  Find a cycle [1, 2, 4, 3]
  Find a cycle [0, 3, 1]

  Psuedocode: Input: G = (V, E) Output: A list that consists of the vertices in a cycle

  public List<Integer> getACycle() { 
    create a list with all the vertices
    create a boolean array to determine if a vertex has been visited (false by default)
    create an int array to determine parent of vertex (-1 by default)
    while (there are unvisited vertices) { // check list size for this 
      pick one, say v; 
      create stack;
      push v to the stack; 
      mark v visited; 
      remove v from list with all vertices;
      while (the stack is not empty) { 
        peek a vertex from the stack, say x; 
        if (x has no neighbors) {
          pop a vertex from the stack;
        }
        else {
          loop through all neighbors of x with loop variable i {
             Let e be the edge between x and the neighbor; 
             if (ending vertex of e (e.v) is not visited) { 
               parent[e.v] = x; 
               push e.v onto the stack; 
               mark e.v visited; 
               remove e.v from list with all vertices;
               remove loop variable i from x's neighbor list;
               break;
             } 
             else if (e.v is not x's parent) {
               create an Integer ArrayList;
               add e.v to list;
               while (x != e.v and x != -1) {
                 add x to list;
                 x is parent of x;
               }
               return list;
             }
             else { 
               remove loop variable i from list with neighbors;
             }
           }   
         } 
       }
    }
    return an empty list;
  }
*/

import java.util.*;

public class UnweightedGraph<V> implements Graph<V> {
  protected List<V> vertices = new ArrayList<>(); // Store vertices
  protected List<List<Edge>> neighbors 
    = new ArrayList<>(); // Adjacency lists

  /** Construct an empty graph */
  public UnweightedGraph() {
  }
  
  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraph(V[] vertices, int[][] edges) {
    for (int i = 0; i < vertices.length; i++)
      addVertex(vertices[i]);
    
    createAdjacencyLists(edges, vertices.length);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraph(List<V> vertices, List<Edge> edges) {
    for (int i = 0; i < vertices.size(); i++)
      addVertex(vertices.get(i));
        
    createAdjacencyLists(edges, vertices.size());
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++) 
      addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}
    
    createAdjacencyLists(edges, numberOfVertices);
  }

  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraph(int[][] edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++) 
      addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}
    
    createAdjacencyLists(edges, numberOfVertices);
  }

  //  Added by Chad Lister.
  public List<Integer> getACycle2() {
    
    List<V> vList = new ArrayList<>(getVertices());
    V v = vList.get(0);
 //   int vn = getNeighbors(v);
//    int vv = vn.v;
    SearchTree d = dfs(getIndex(v));
    
    List<Integer> pList = new ArrayList<>();
    int end = vList.size() - 1;
    List<V> pathList = new ArrayList<>(d.getPath(end));
    V vertm = pathList.get(2);
    int m = getIndex(vertm);
    pList.add(m);
 //   pathList.remove(m);
    V verte = pathList.get(0);
    int l = getIndex(verte);
    pList.add(l);
 //   pathList.remove(l);
    V vertb = pathList.get(1);
    int b = getIndex(vertb);
    pList.add(b);
    return pList;
  }
 
  //  Added by Chad Lister.
  public List<Integer> getACycle() {
    
    List<V> vertex = new ArrayList<V>(getVertices());
    boolean[] isVisited = new boolean[vertex.size()];
    isVisited[0] = false;
    int[] parent = new int[vertex.size()]; 
    parent[0] = - 1;
    Stack<Integer> stack = new Stack<Integer>();

    while (vertex.size() > 0) {
      V rr = vertex.get(0);
      int r = getIndex(rr);
      stack.push(r);                          //. **. stack is 0
      isVisited[r] = true;
      vertex.remove(rr);
      
      while (stack.empty() == false) {
        int x = stack.peek();
 //         if (getNeighbors(x) == null || getNeighbors(x).size() == 1) {
          if (getNeighbors(x) == null || getNeighbors(x).size() == 0) {    // never pops.
          stack.pop();
        }
        else {
          
          for (int i = 0; i < getNeighbors(x).size(); i++) {   
            Edge e = neighbors.get(x).get(0);    //. ** e.v = 1  2
            int ev = e.v;
            
            if (isVisited[ev] == false) {      // 1 is false  2 is false
              parent[ev] = x;                  //  parent of 1 is 0  parent of 2 is 0
              stack.push(ev);                  //  push 1 to stack.  2 to stack
              isVisited[ev] = true;
              
              vertex.remove(ev);               // take 1 out of vertex. take 2 out
              neighbors.get(x).remove(0);       //  remove 1  remove 2
              break;
            }
            else if (parent[x] != ev){          //. enters with stack 1 and neighbors 0, 3
              List<Integer> cycle = new ArrayList<Integer>();
              cycle.add(ev);              //. adds first true neighbor. denver would be sf.    1 iwould be 0

 //             while (xi != ee && parent[xi] != - 1) {
              while (x != ev && x != - 1) {
                cycle.add(x);                  // x is LA .    x is 1 not 3
                                              //  x is sf      x is 0
//                parent[x] = x;
                //  Added to stop infinite loop in psuedo code.
                x--;        //  x never equals ev or - 1 because he doesn't decrement x
                
                if (x == 4 || ev == 4)
                  System.out.println(" ***  Kansas ***. " + x + "  " + e.v);
                  //  Cycle detected before it gets to Kansas City because vertex 3 is before vertex 4.
              }
              return cycle;
            }
            else {
              neighbors.get(x).remove(0);
            }
          }
        }
      }
    }
    List<Integer> bs = new ArrayList<Integer>();
    return bs;                      // cant return cycle because he has it in loop.
  }

  /** Create adjacency lists for each vertex */
  private void createAdjacencyLists(
      int[][] edges, int numberOfVertices) {
    for (int i = 0; i < edges.length; i++) {
      addEdge(edges[i][0], edges[i][1]);
    }
  }

  /** Create adjacency lists for each vertex */
  private void createAdjacencyLists(
      List<Edge> edges, int numberOfVertices) {
    for (Edge edge: edges) {
      addEdge(edge.u, edge.v);
    }
  }

  @Override /** Return the number of vertices in the graph */
  public int getSize() {
    return vertices.size();
  }

  @Override /** Return the vertices in the graph */
  public List<V> getVertices() {
    return vertices;
  }

  @Override /** Return the object for the specified vertex */
  public V getVertex(int index) {
    return vertices.get(index);
  }

  @Override /** Return the index for the specified vertex object */
  public int getIndex(V v) {
    return vertices.indexOf(v);
  }

  @Override /** Return the neighbors of the specified vertex */
  public List<Integer> getNeighbors(int index) {
    List<Integer> result = new ArrayList<>();
    for (Edge e: neighbors.get(index))
      result.add(e.v);
    
    return result;
  }

  @Override /** Return the degree for a specified vertex */
  public int getDegree(int v) {
    return neighbors.get(v).size();
  }

  @Override /** Print the edges */
  public void printEdges() {
    for (int u = 0; u < neighbors.size(); u++) {
      System.out.print(getVertex(u) + " (" + u + "): ");
      for (Edge e: neighbors.get(u)) {
        System.out.print("(" + getVertex(e.u) + ", " +
          getVertex(e.v) + ") ");
      }
      System.out.println();
    }
  }

  @Override /** Clear the graph */
  public void clear() {
    vertices.clear();
    neighbors.clear();
  }
  
  @Override /** Add a vertex to the graph */  
  public boolean addVertex(V vertex) {
    if (!vertices.contains(vertex)) {
      vertices.add(vertex);
      neighbors.add(new ArrayList<Edge>());
      return true;
    }
    else {
      return false;
    }
  }

  /** Add an edge to the graph */  
  public boolean addEdge(Edge e) {
    if (e.u < 0 || e.u > getSize() - 1)
      throw new IllegalArgumentException("No such index: " + e.u);

    if (e.v < 0 || e.v > getSize() - 1)
      throw new IllegalArgumentException("No such index: " + e.v);
    
    if (!neighbors.get(e.u).contains(e)) {
      neighbors.get(e.u).add(e);
      return true;
    }
    else {
      return false;
    }
  }
  
  @Override /** Add an edge to the graph */  
  public boolean addEdge(int u, int v) {
    return addEdge(new Edge(u, v));
  }
  
  @Override /** Obtain a DFS tree starting from vertex u */
  /** To be discussed in Section 28.7 */
  public SearchTree dfs(int v) {
    List<Integer> searchOrder = new ArrayList<>();
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1; // Initialize parent[i] to -1

    // Mark visited vertices
    boolean[] isVisited = new boolean[vertices.size()];

    // Recursively search
    dfs(v, parent, searchOrder, isVisited);

    // Return a search tree
    return new SearchTree(v, parent, searchOrder);
  }

  /** Recursive method for DFS search */
  private void dfs(int v, int[] parent, List<Integer> searchOrder,
      boolean[] isVisited) {
    // Store the visited vertex
    searchOrder.add(v);
    isVisited[v] = true; // Vertex u visited

    for (Edge e : neighbors.get(v)) { // Note that e.u is v 
      if (!isVisited[e.v]) { // e.v is w in Listing 28.8
        parent[e.v] = v; // The parent of w is v
        dfs(e.v, parent, searchOrder, isVisited); // Recursive search
      }
    }
  }

  @Override /** Starting bfs search from vertex v */
  /** To be discussed in Section 28.9 */
  public SearchTree bfs(int v) {
    List<Integer> searchOrder = new ArrayList<>();
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1; // Initialize parent[i] to -1

    java.util.LinkedList<Integer> queue =
      new java.util.LinkedList<>(); // list used as a queue
    boolean[] isVisited = new boolean[vertices.size()];
    queue.offer(v); // Enqueue v
    isVisited[v] = true; // Mark it visited

    while (!queue.isEmpty()) {
      int u = queue.poll(); // Dequeue to u
      searchOrder.add(u); // u searched
      for (Edge e: neighbors.get(u)) { // Note that e.u is u
        if (!isVisited[e.v]) { // e.v is w in Listing 28.11
          queue.offer(e.v); // Enqueue w
          parent[e.v] = u; // The parent of w is u
          isVisited[e.v] = true; // Mark w visited
        }
      }
    }

    return new SearchTree(v, parent, searchOrder);
  }

  /** Tree inner class inside the AbstractGraph class */
  /** To be discussed in Section 28.6 */
  public class SearchTree {
    private int root; // The root of the tree
    private int[] parent; // Store the parent of each vertex
    private List<Integer> searchOrder; // Store the search order

    /** Construct a tree with root, parent, and searchOrder */
    public SearchTree(int root, int[] parent, 
        List<Integer> searchOrder) {
      this.root = root;
      this.parent = parent;
      this.searchOrder = searchOrder;
    }

    /** Return the root of the tree */
    public int getRoot() {
      return root;
    }

    /** Return the parent of vertex v */
    public int getParent(int v) {
      return parent[v];
    }

    /** Return an array representing search order */
    public List<Integer> getSearchOrder() {
      return searchOrder;
    }

    /** Return number of vertices found */
    public int getNumberOfVerticesFound() {
      return searchOrder.size();
    }
    
    /** Return the path of vertices from a vertex to the root */
    public List<V> getPath(int index) {
      ArrayList<V> path = new ArrayList<>();

      do {
        path.add(vertices.get(index));
        index = parent[index];
      }
      while (index != -1);

      return path;
    }

    /** Print a path from the root to vertex v */
    public void printPath(int index) {
      List<V> path = getPath(index);
      System.out.print("A path from " + vertices.get(root) + " to " +
        vertices.get(index) + ": ");
      for (int i = path.size() - 1; i >= 0; i--)
        System.out.print(path.get(i) + " ");
    }

    /** Print the whole tree */
    public void printTree() {
      System.out.println("Root is: " + vertices.get(root));
      System.out.print("Edges: ");
      for (int i = 0; i < parent.length; i++) {
        if (parent[i] != -1) {
          // Display an edge
          System.out.print("(" + vertices.get(parent[i]) + ", " +
            vertices.get(i) + ") ");
        }
      }
      System.out.println();
    }
  }
}

class Edge { 
  int u;
  int v;
  
  public Edge(int u, int v) { this.u = u;
    this.v = v;
  }
  
  public boolean equals(Object o) {
    return u == ((Edge)o).u && v == ((Edge)o).v;
  }
}