import java.util.*;
import java.io.*;
public class HuffmanCode{
    //Builds the tree based off of the frequencies of each character.
    public static HuffmanTree buildTree(int[] charFreqs){
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        //Create a leaf for each character with a frequency greater than 0.
        for (int i = 0; i < charFreqs.length; i++){
            if (charFreqs[i] > 0){
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i)); //Use the offer method to insert into priority queue.
            }
        }
 
        //Go through the priority queue until there is only one tree left.
        while (trees.size() > 1) {
            //Take the two characters with the least frequency.
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
 
            //Combine the two characters to make a subtree with the Node being their combined frequencies.
            trees.offer(new HuffmanNode(a, b));
        }
        return trees.poll();
    }
 
    public static void printCodes(HuffmanTree tree, StringBuffer prefix){
        //In this method, we use the 'instanceof' keyword to tell whether or not it is a leaf or a node.
        if(tree instanceof HuffmanLeaf){ //If it is an actual character or symbol, then print it out along with it's frequency and code.
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
 
            // print out character, frequency, and code for this leaf (which is just the prefix)
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix); 
        }
        //We traverse the tree using recursion.
        else if(tree instanceof HuffmanNode){ //If it is just a node with a combined frequency, then use recursion to traverse to the next node or leaf.
            HuffmanNode node = (HuffmanNode)tree;
 
            //Traverse left
            prefix.append('0'); //Left is '0'
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1); //Clear prefix
 
            //Traverse right
            prefix.append('1'); //Right is '1'
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1); //Clear prefix
        }
    }
 
    public static void main(String[] args) throws IOException{
        String fileName;
        Scanner keyboard = new Scanner(System.in);
        
        int[] charFreqs = new int[256]; //Stores the frequency of characters by it's ASCII code.
        //Read letter by letter getting the frequencies.
        
        System.out.println("Enter the loaction of the file: ");
        fileName = keyboard.nextLine();
        
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){        
                for (char c : line.toCharArray()){ //Go through each character of the line and increase the frequencies.
                    charFreqs[c]++;
                }
            }
        }
        
 
        //Create the Huffman Tree by sending our frequencies.
        HuffmanTree tree = buildTree(charFreqs);
 
        //Print out the Frequencies and the Huffman Code of each character.
        System.out.println("SYMBOL\tFREQ\tHUFFMAN CODE");
        printCodes(tree, new StringBuffer());
    }
}