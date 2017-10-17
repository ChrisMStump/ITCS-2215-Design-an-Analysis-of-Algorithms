import java.io.*;
import java.util.*;
import java.lang.*;
public class KruskalsAlgorithm{    
    public static void main(String[] args) throws IOException
    {
        Scanner keyboard = new Scanner(System.in);
        StringTokenizer stok;
        String fileName;
        String first;
        String temp;
        String[] tempEdge = new String[3];
        int verticesCount; //Number of vertices in the graph.
        int verticesIndex1; //Source
        int verticesIndex2; //Destination
        int weight; //Weight
        
        //Ask the user for the file name.
        System.out.println("Enter the location of the file: ");
        fileName = keyboard.nextLine();
        
        Scanner inputFile = new Scanner(new File(fileName));
        
        ArrayList<String> vertices = new ArrayList(); //This will store the vertices.
        ArrayList<String> edges = new ArrayList<>(); //This will store the edges.
        while(inputFile.hasNext()){
            stok = new StringTokenizer(inputFile.nextLine(), ", ");
            if(stok.countTokens() == 0){
                stok = new StringTokenizer(inputFile.nextLine(), ", ");
            }
            first = stok.nextToken();
            vertices.add(first);
            while(stok.hasMoreTokens()){
                edges.add(first);
                edges.add(stok.nextToken());
                edges.add(stok.nextToken());
            }
        }
        
        verticesCount = vertices.size();
        
        Graph graph = new Graph(verticesCount);
        for(int i = 1; i <= verticesCount; i++){
            graph.addVertex(i);
        }
        for(int i = 0; i < edges.size(); i++){
            verticesIndex1 = vertices.indexOf(edges.get(i))+1;
            while(Character.isDigit(edges.get(i+1).charAt(0))){
                i++;
                weight = Integer.parseInt(edges.get(i));
                i++;
                verticesIndex2 = vertices.indexOf(edges.get(i))+1;
                graph.addEdge(verticesIndex1, verticesIndex2, weight);
                if(i == edges.size()-1){
                    break;
                }
            }
        }
        graph.applyKruskalAlgo(vertices);
    }
}