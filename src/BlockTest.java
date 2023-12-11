import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BlockTest {
    /**
     * Implementingg block testing class
     *
     * @author Kevin Johanson, Shuta Shibue
     */
    @Test
    public void Test1() {
        Block block = new Block(0, 300, null);
        assertEquals(block.nonce, 9324351);
        assertEquals(block.getHash().toString(),
                "000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7");
    }
}
