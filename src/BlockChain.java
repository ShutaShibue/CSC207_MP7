import java.util.ArrayList;

    /**
     * Block class to store List of transactions as a chain.
     *
     * @author Kevin Johanson, Shuta Shibue
     */

public class BlockChain {
    
    // Fields
    ArrayList<Block> blocks;
    
    // Constructor
    public BlockChain(int initial){
        blocks = new ArrayList<>();
        blocks.add(new Block(0, initial, null));
    } // BlockChain(int)

    public Block mine(int amount){
        return new Block(getSize(), amount, getHash(), amount);
    } // mine(int)

    public int getSize(){
        return blocks.size();
    } // getSize()

    public void append(Block blk){
        if(!blocks.get(getSize() - 1).getHash().equals(blk.getPrevHash()))
            throw new IllegalArgumentException();

        blocks.add(blk);
    } // append(Block)

    public boolean removeLast(){
        if(getSize() == 1) return false;
        blocks.remove(getSize() - 1);
        return true;
    } // removeLast()

    public Hash getHash(){
        return blocks.get(getSize() - 1).getHash();
    } // getHash()

    public boolean isValidBlockChain(){
        for (int i = 0; i < blocks.size() - 1; i++) {
            if(!blocks.get(i).getHash().equals(blocks.get(i+1).getPrevHash())) return false;
            if(!blocks.get(i).getHash().isValid()) return false;
        }
        return true;
    } // isValidBlockChain

    public void printBalances(){
        int a = blocks.get(0).amount; // Initial amount
        int transaction = 0;

        for (int i = 1; i < getSize(); i++) {
            transaction += blocks.get(i).amount;
        }
        a += transaction;
        int b = -transaction;
        System.out.println("Alexis: " + a + ", Blake: " + b);
    } // printBalance

    public String toString(){  
        String str = "";
        for (Block block : blocks) {
            str += block.toString() + "\n";
        }
        return str;
    } // toString
}
