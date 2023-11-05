public class Block {
    
    // Fields
    int num;
    int amount;
    Hash prevHash;
    long nonce;
    // Constructor

    public Block(int num, int amount, Hash prevHash){

    }

    public Block(int num, int amount, Hash prevHash, long nonce){

    }

    public int getNum(){
        return num;
    }

    public int getAmount(){
        return amount;
    }

    public long getNonce(){
        return nonce;
    }

    public Hash getPrevHash(){
        return prevHash;
    }

    public Hash getHash(){
        
    }

    public String toString(){
        return "";
    }
}
