import java.util.*;
public class Graph {
    Vertex[] vertices;
    Edge edgeList;
    int maxSize; //Size of the graph.
    int size; //Used to inset vertices.
    int edgeNum; //The number of edges in the graph.
    int totalWeight; //Weight of the minimum spanning tree.
    
    /*
     * Initialize the graph with the amount of vertices in the graph.
     */
    public Graph(int maxSize){
        this.maxSize = maxSize;
        vertices = new Vertex[maxSize];
    }

    /*
     * Add each unique vertex to the graph.
     */
    public void addVertex(int name){
        vertices[size++] = new Vertex(name);
    }

    /*
     * Add an edge and keep track of all that edges neighbors.
     */
    public void addEdge(int src, int dest, int weight){
        vertices[src - 1].adj = new Neighbor(dest - 1, weight, vertices[src - 1].adj);
        edgeList = new Edge(vertices[src - 1], vertices[dest - 1], weight, edgeList);
        edgeNum++;
    }

    /*
     * This method is called by main and it takes in all the edges and sorts  them.
     * It chooses the smallest edges that do not create a loop.
     * This is done until we have visited all the nodes.
     */
    public void applyKruskalAlgo(ArrayList<String> vertices){
        Edge[] edges = new Edge[edgeNum];
        int i = 0;
        while (edgeList != null){ //Go through the list of edges.
            edges[i] = edgeList;
            i++;
            edgeList = edgeList.next;
        }
        quicksort(edges, 0, edgeNum - 1); //Sort the edges by weight.
        for (i = 0; i < edgeNum; i++) { //Find the next edge that doesn't make a loop
            Vertex u = findSet(edges[i].src);
            Vertex v = findSet(edges[i].desti);
            if (u != v) { //If the edge doesn't make a loop then print out the edge with it's weight
                System.out.println(vertices.get(edges[i].src.name - 1) + " - " + vertices.get(edges[i].desti.name - 1) + " Weight "+ edges[i].weight);
                totalWeight += edges[i].weight;
                union(u, v);
            }
        }
        System.out.println("The weight of the minimum spanning tree is : " + totalWeight);
    }

    /*
     * Used to check whether or not the source does not lead to the destination.
     */
    public Vertex findSet(Vertex u) {
        if (u.representative != u) {
            u.representative = findSet(u.representative); // path compression
        }
        return u.representative;
    }

    /*
     * Make the edge used and out of question.
     */
    public void union(Vertex u, Vertex v) {
        if(u.rank == v.rank){
            v.representative = u;
            u.rank++;
        }else if(u.rank < v.rank){
            v.representative = u;
        }else{
            u.representative = v;
        }
    }

    /*
     * Sort the edges by their weight
     */
    public void quicksort(Edge[] edges, int start, int end) {
        if (start < end) {
            swap(edges, end, start + (end - start) / 2);
            int pIndex = pivot(edges, start, end);
            quicksort(edges, start, pIndex - 1);
            quicksort(edges, pIndex + 1, end);
        }
    }

    /*
     * If a weight is les then the other weight then call swap.
     */
    public int pivot(Edge[] edges, int start, int end) {
        int pIndex = start;
        Edge pivot = edges[end];
        for (int i = start; i < end; i++) {
            if (edges[i].weight < pivot.weight) {
                swap(edges, i, pIndex);
                pIndex++;
            }
        }
        swap(edges, end, pIndex);
        return pIndex;
    }

    /*
     * Swap the two edges since one of them has a smaller weight.
     */
    public void swap(Edge[] edges, int index1, int index2) {
        Edge temp = edges[index1];
        edges[index1] = edges[index2];
        edges[index2] = temp;
    }
}