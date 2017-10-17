import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * This class uses the Radix Sort method to sort an array of Integers
 * 
 * @author Christopher Stump
 * @version 2/3/2016
 */

public class Radix{
    int[] currArray;

    public Radix(int[] _currArray){
        currArray = _currArray;
    }

    public int[] sort(){
        // Here we create an array of 10 elements (0 to 9) of type LinkedList
        LinkedList<Integer>[] arr = new LinkedList[10];
        
        //Here we initialize each element in the array of Linked List to a Linked List of type Integer
        for(int i = 0; i < 10; i++){
            arr[i] = new LinkedList<Integer>();
        }

        // Here we loop through three times (m = 10, 100, 1000) as described in the video
        for (int m = 10; m <= 1000; m = m * 10)
        {
            int n = m / 10;
            int temp; //Holds the number to be sorted
            int place; //Holds which location in the array to go into
            int iterator = 0; //Used to rebuild the new array
            
            //Here the numbers are placed in the Linked List array
            for(int i = 0; i < currArray.length; i++){
                temp = currArray[i];
                place = (temp % m) / n;
                arr[place].add(temp);
            }
            
            //Here each sub Linked List is turned into an array and rebuilt into the orginal array
            for(int i = 0; i < 10; i++){
                Object[] tempArray = arr[i].toArray();              
                for(int j = 0; j < tempArray.length; j++){
                    currArray[iterator] = (Integer) tempArray[j];
                    iterator++;
                }
            }
            
            //Here we clear each sub Linked List and create fresh ones
            for(int i = 0; i < 10; i++){    
                arr[i].clear();
                arr[i] = new LinkedList<Integer>();
            }
        }

        //The sorted array is returned
        return currArray;
    }
}
