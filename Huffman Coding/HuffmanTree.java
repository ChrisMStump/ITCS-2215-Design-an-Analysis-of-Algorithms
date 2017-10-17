//Binary Tree Structure of leaf and nodes.
abstract class HuffmanTree implements Comparable<HuffmanTree>{
    public final int frequency; // the frequency of this tree
    public HuffmanTree(int freq){ 
        frequency = freq; 
    }
 
    //Compares on the frequency
    public int compareTo(HuffmanTree tree){
        return frequency - tree.frequency;
    }
}