import java.util.*;
import java.io.*;
/**
 * This class takes in a file to read Integers and sort them by calling Radix Sort.
 * 
 * @author Christopher Stump
 * @version 2/3/2016
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        Scanner keyboard = new Scanner(System.in);
        StringTokenizer stok;
        String fileName;
        String temp;
        int arraySize;
        int iterator = 0;
        
        System.out.print("Enter the location of the file: "); //Ask user for input file
        fileName = keyboard.nextLine();
        Scanner inputFile = new Scanner(new File(fileName));
        
        stok = new StringTokenizer(inputFile.nextLine(), ","); //Create StringTokenizer that seperates by commas
        arraySize = stok.countTokens();
        int radixArray[] = new int[arraySize]; //Creat an Integer array of appropriate size
        
        //Take in the tokens and put them into the array
        while(stok.hasMoreTokens()){
            temp = stok.nextToken();
            radixArray[iterator] = Integer.valueOf(temp);
            iterator++;
        }
        
        //Create an instance of the Radix class and passing in the array
        Radix myRadix = new Radix(radixArray);
        radixArray = myRadix.sort(); //Sort the array
        
        //Print the sorted array
        for(int i = 0; i < radixArray.length; i++){
            System.out.print(radixArray[i] + ", ");
        }
        
        System.exit(0);
    }
}
