public class Vertex {
    int rank;
    Vertex representative;
    int name;
    Neighbor adj;
    Vertex(int name) {
        this.name = name;
        representative = this; //MakeSet
        //Used to create disjointed sets.
    }
}