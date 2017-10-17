import java.io.*;
import java.lang.*;
import java.util.*;
/**
 * This is the driver class that reads in the coordinates and sends them off to find the convex hull.
 * 
 * @author Christopher Stump
 * @version 3/21/2016
 */
public class Driver
{
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        StringTokenizer stok;
        String fileName;
        String temp;
        int listIterator = 0;
        
        System.out.print("Enter the location of the text file containing the coordinates: ");
        fileName = keyboard.nextLine();
        Scanner inputFile = new Scanner(new File(fileName));
        
        List<Integer> list = new ArrayList<>();
        Integer coordinates[];
        while(inputFile.hasNext()){
            stok = new StringTokenizer(inputFile.nextLine(), ", ");
            while(stok.hasMoreTokens()){
                temp = stok.nextToken();
                list.add(Integer.parseInt(temp));
            }
        }
        
        Point[] p = new Point[(list.size())/2];
        
        for (int i = 0; i < list.size()/2; i++) {
            if(list.get(i) == null){
                break;
            }
            p[i] = new Point();
            p[i].x = list.get(listIterator); // Read X coordinate 
            p[i].y = list.get(listIterator+1); // Read y coordinate
            listIterator += 2;
        }
        
        Point[] hull = ConvexHull.calculateCH(p).clone();
        
        for (int i = 0; i < hull.length; i++) {
            if (hull[i] != null)
                System.out.print(hull[i]);
        }
    }

}
