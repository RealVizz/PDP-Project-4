import java.util.Arrays;

class Graph {
  // A class to represent a graph edge
  static class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int weight;

    public int compareTo(Edge compareEdge) {
      return this.weight - compareEdge.weight;
    }
  }

  static class Subset {
    int parent;
    int rank;
  }

  int verticesCount;  // V-> no. of vertices & E->no.of edges
  int edgesCount;
  Edge[] edge; // collection of all edges

  // Creates a graph with V vertices and E edges
  Graph(int v, int e) {
    verticesCount = v;
    edgesCount = e;
    edge = new Edge[edgesCount];
    for (int i = 0; i < e; ++i) {
      edge[i] = new Edge();
    }
  }

  int find(Subset[] subsets, int i) {
    if (subsets[i].parent != i) {
      subsets[i].parent = find(subsets, subsets[i].parent);
    }
    return subsets[i].parent;
  }

  void union(Subset[] subsets, int x, int y) {
    int xroot = find(subsets, x);
    int yroot = find(subsets, y);

    if (subsets[xroot].rank < subsets[yroot].rank) {
      subsets[xroot].parent = yroot;
    } else if (subsets[xroot].rank > subsets[yroot].rank) {
      subsets[yroot].parent = xroot;
    } else {
      subsets[yroot].parent = xroot;
      subsets[xroot].rank++;
    }
  }


  Edge[][] kruskalMST() {
    // This will store the resultant MST
    Edge[] result = new Edge[verticesCount - 1];
    Edge[] discardedEdges = new Edge[verticesCount];

    // An index variable, used for result[]
    int e = 0;
    // An index variable, used for discardedEdges[]
    int e2 = 0;

    // An index variable, used for sorted edges
    int i;
    for (i = 0; i < verticesCount - 1; i++) {
      result[i] = new Edge();
      discardedEdges[i] = new Edge();
    }

    // Step 1:  Sort all the edges in non-decreasing
    // order of their weight.  If we are not allowed to
    // change the given graph, we can create a copy of
    // array of edges
    Arrays.sort(edge);

    // Allocate memory for creating V subsets
    Subset[] subsets = new Subset[verticesCount];
    for (i = 0; i < verticesCount; ++i) {
      subsets[i] = new Subset();
    }

    // Create V subsets with single elements
    for (int v = 0; v < verticesCount; ++v) {
      subsets[v].parent = v;
      subsets[v].rank = 0;
    }

    i = 0; // Index used to pick next edge

    // Number of edges to be taken is equal to V-1
    while (e < verticesCount - 1) {
      // Step 2: Pick the smallest edge. And increment
      // the index for next iteration
      Edge next_edge = edge[i++];

      int x = find(subsets, next_edge.src);
      int y = find(subsets, next_edge.dest);

      // If including this edge doesn't cause cycle,
      // include it in result and increment the index
      // of result for next edge
      if (x != y) {
        result[e++] = next_edge;
        union(subsets, x, y);
      }
      // Else discard the next_edge
      else {
        discardedEdges[e2++] = next_edge;
      }
    }

    // clean up discarded edges empty values
    Edge[] discardedEdges2 = new Edge[e2];
    for (int k = 0; k < e2; k++) {
      discardedEdges2[k] = discardedEdges[k];
    }

    Edge[][] cc = new Edge[2][result.length];
    cc[0] = result;
    cc[1] = discardedEdges2;
    return cc;
  }

}