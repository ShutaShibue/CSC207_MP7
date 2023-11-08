import java.nio.ByteBuffer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

public class Block {

    /**
     * Block class to store single transaction information
     *
     * @author Kevin Johanson, Shuta Shibue
     */

    // Fields
    int num;
    int amount;
    Hash prevHash;
    long nonce;

    // Constructor

    /**
     * Constructor without known nonce. Nonce is automatically calculated.
     */
    public Block(int num, int amount, Hash prevHash) {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        try {
            this.nonce = mine();
        } catch (NoSuchAlgorithmException | DigestException e) {
            e.printStackTrace();
            System.err.println("No nonce was found");
        }
    } // Block(int, int, Hash)

    /**
     * Constructor with known nonce.
     */
    public Block(int num, int amount, Hash prevHash, long nonce) {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
    } // Block(int, int, Hash, long)

    /**
     * returns the number of this block.
     */
    public int getNum() {
        return num;
    } // getNum()

    /**
     * returns the amount transferred that is recorded in this block.
     */
    public int getAmount() {
        return amount;
    } // getAmount()

    /**
     * returns the nonce of this block.
     */
    public long getNonce() {
        return nonce;
    } // getNonce()

    /**
     * returns the hash of the previous block in the blockchain.
     */
    public Hash getPrevHash() {
        return prevHash;
    } // getPrevHash()

    /**
     * returns the hash of this block.
     */
    public Hash getHash() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("sha-256");
            md.update(ByteBuffer.allocate(4).putInt(getNum()).array());
            md.update(ByteBuffer.allocate(4).putInt(getAmount()).array());
            if (getPrevHash() != null)
                md.update(getPrevHash().toString().getBytes()); // Only when prev exists
            md.update(ByteBuffer.allocate(8).putLong(nonce).array());

            byte[] hashByte = md.digest();
            Hash hashObj = new Hash(hashByte);

            return hashObj;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    } // getHash()

    /**
     * Mines and return nonce.
     */
    long mine() throws NoSuchAlgorithmException, DigestException {
        MessageDigest md = MessageDigest.getInstance("sha-256");

        try {
            md.update(ByteBuffer.allocate(4).putInt(getNum()).array());
            md.update(ByteBuffer.allocate(4).putInt(getAmount()).array());
            if (getPrevHash() != null)
                md.update(getPrevHash().toString().getBytes()); // Only when prev exists
            for (long i = 0; i < Long.MAX_VALUE - 1; i++) {
                MessageDigest mdCopy = (MessageDigest) md.clone();
                mdCopy.update(ByteBuffer.allocate(8).putLong(i).array());
                byte[] hash = mdCopy.digest();

                Hash tmpHash = new Hash(hash);
                if (tmpHash.isValid()) {
                    return i;
                }
            }
            throw new NoSuchElementException();

        } catch (CloneNotSupportedException cnse) {
            throw new DigestException("couldn't make digest of partial content");
        }
    } // mine()

    /**
     * returns a string representation of the block.
     */
    public String toString() {
        String str = "Block " + getNum() + " (Amount: " + getAmount() + ", Nonce: " + getNonce()
                + ", prevHash: " + getPrevHash() + ", hash: " + getHash() + ")";
        return str;
    } // toString()
}
