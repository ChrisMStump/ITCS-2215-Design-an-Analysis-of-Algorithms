public class Neighbor {
    int index;
    Neighbor next; //List of neighbors for the source vertex.
    int weight;

    Neighbor(int index, int weight, Neighbor next) {
        this.index = index;
        this.weight = weight;
        this.next = next;
    }
}