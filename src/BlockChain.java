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
    /**
     * Constructor with initial amount of money in the environment.
     */
    public BlockChain(int initial){
        blocks = new ArrayList<>();
        blocks.add(new Block(0, initial, null));
    } // BlockChain(int)

    /**
     * mines a new candidate block to be added to the end of the chain.
     */
    public Block mine(int amount){
        return new Block(getSize(), amount, getHash());
    } // mine(int)

    /**
     * returns the size of the blockchain.
     */
    public int getSize(){
        return blocks.size();
    } // getSize()

    /**
     * adds this block to the list, throwing an IllegalArgumentException if the block is not following rules.
     */
    public void append(Block blk){
        if(!blocks.get(getSize() - 1).getHash().equals(blk.getPrevHash()))
            throw new IllegalArgumentException();

        blocks.add(blk);
    } // append(Block)

    /**
     * removes the last block from the chain, returning true. 
     * If the chain only contains a single block, then removeLast does nothing and returns false.
     */
    public boolean removeLast(){
        if(getSize() == 1) return false;
        blocks.remove(getSize() - 1);
        return true;
    } // removeLast()

    /**
     * returns the hash of the last block in the chain.
     */
    public Hash getHash(){
        return blocks.get(getSize() - 1).getHash();
    } // getHash()

    /**
     * walks the blockchain and ensures that its blocks are consistent and valid.
     */
    public boolean isValidBlockChain(){
        for (int i = 0; i < blocks.size() - 1; i++) {
            if(!blocks.get(i).getHash().equals(blocks.get(i+1).getPrevHash())) return false;
            if(!blocks.get(i).getHash().isValid()) return false;
        }
        return true;
    } // isValidBlockChain()

    /**
     * prints Alexis’s and Blake’s respective balances.
     */
    public void printBalances(){
        int a = blocks.get(0).amount; // Initial amount
        int transaction = 0;

        for (int i = 1; i < getSize(); i++) {
            transaction += blocks.get(i).amount;
        }
        a += transaction;
        int b = -transaction;
        System.out.println("Alexis: " + a + ", Blake: " + b);
    } // printBalance()

    /**
     * returns a string representation of the BlockChain.
     */
    public String toString(){  
        String str = "";
        for (Block block : blocks) {
            str += block.toString() + "\n";
        }
        return str;
    } // toString()
}
