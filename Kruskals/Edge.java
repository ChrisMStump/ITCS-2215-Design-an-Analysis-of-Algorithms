public class Edge{
    Vertex src;
    Vertex desti;
    Edge next;
    int weight;

    Edge(Vertex src, Vertex desti, int weight, Edge next){
        this.src = src;
        this.desti = desti;
        this.weight = weight;
        this.next = next;
    }
}