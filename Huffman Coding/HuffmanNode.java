class HuffmanNode extends HuffmanTree{
    public final HuffmanTree left, right; //Reference to the subtrees of a node.
 
    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}