import java.util.*;
import java.io.*;
import java.lang.*;
/**
 * This is the driver class for a program that will take in an adjacency list and find a correct
 * topological order and shortest path from a node to 
 * 
 * @author Christopher Stump
 * @version 2/24/2016
 */
public class Driver
{
    public static void main(String [] args) throws IOException
    {
        Scanner keyboard = new Scanner(System.in); //Input from keyboard
        StringTokenizer stok;
        String fileName;
        String temp;
        String currNode = "";
        String targetNode = "";
        String distance = "";
        String[][] tempArray;
        int arraySize;
        int matrixSize;
        int iterator = 0;
        int row = 0, col = 0;
        
        System.out.println("Enter the location of the file: ");
        fileName = keyboard.nextLine();
        
        Scanner inputFile = new Scanner(new File(fileName));
        
        ArrayList<String> myList = new ArrayList(); //This will be used to store the unique Nodes.
        
        /*
         * Here the file is read and unique vertexes are stored in an ArrayList.
         */
        while(inputFile.hasNext()){
            stok = new StringTokenizer(inputFile.nextLine(), ", "); 
            while(stok.hasMoreTokens()){
                temp = stok.nextToken();
                if(!myList.contains(temp) && Character.isLetter(temp.charAt(0))){
                    myList.add(temp);
                }
            }
        }        
        inputFile.close();
        
        //Create a matrix to represent the values.
        Collections.sort(myList); //Make the matrix pleasing to the eye
        /*
         * To make this easier, we are turning the adjacency list into an adjacency matrix.
         */
        matrixSize = myList.size()+1;
        String[][] matrix = new String[matrixSize][matrixSize];
        matrix[0][0] = "N"; //Replace Null with N
        for(int i = 1; i < matrixSize; i++){
            matrix[i][0] = myList.get(i-1); //Fill the adjacency matrix with the nodes, so that it is easier to see.
            matrix[0][i] = myList.get(i-1);
        }
        //Replace all null within the matrix with zero.
        for(int i = 1; i < matrixSize; i++){
            for(int j = 1; j < matrixSize; j++){
                if(matrix[i][j] == null){
                    matrix[i][j] = "0";
                }
            }
        }        
        
        //Display the current matrix.
        for(int i = 0; i < matrixSize; i++){
            for(int k = 0; k < matrixSize; k++){
                System.out.print(matrix[i][k] + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
        
        //Read the file once more to fill all the spots with values.
        Scanner inputFile2 = new Scanner(new File(fileName));
        while(inputFile2.hasNext()){
            stok = new StringTokenizer(inputFile2.nextLine(), ", ");
            arraySize = (stok.countTokens() - 1) / 2; //The indegree of a certain vertex.
            tempArray = new String[2][arraySize];
            while(stok.hasMoreTokens()){
                currNode = stok.nextToken();             
                for(int i = 0; i < arraySize; i++){
                    distance = stok.nextToken();
                    targetNode = stok.nextToken();
                    tempArray[0][i] = distance;
                    tempArray[1][i] = targetNode;
                }   
                //This clumped for loop puts the values in their places in the adjacency matrix.
                for(int i = 0; i < arraySize; i++){
                    for(int j = 1; j < matrixSize; j++){
                        if(matrix[j][0].equals(currNode)){
                            for(int k = 1; k < matrixSize; k++){
                                if(matrix[0][k].equals(tempArray[1][i])){
                                    matrix[j][k] = tempArray[0][i];                                   
                                }                                
                            }                            
                        }
                    }
                }
            }            
        }
        inputFile2.close();
        
        for(int i = 0; i < matrixSize; i++){
            for(int k = 0; k < matrixSize; k++){
                System.out.print(matrix[i][k] + ", ");
            }
            System.out.println("");
        }
    }
}
