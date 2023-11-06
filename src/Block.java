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
    // Micah was not sure about mining in the constructor ------------------------------------------------------------ 

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
    }

    public Block(int num, int amount, Hash prevHash, long nonce) {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
    }

    public int getNum() {
        return num;
    }

    public int getAmount() {
        return amount;
    }

    public long getNonce() {
        return nonce;
    }

    public Hash getPrevHash() {
        return prevHash;
    }

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
    }

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
    }

    public String toString() {
        String str = "Block " + getNum() + " (Amount: " + getAmount() + ", Nonce: " + getNonce()
                + ", prevHash: " + getPrevHash() + ", hash: " + getHash() + ")";
        return str;
    }
}
