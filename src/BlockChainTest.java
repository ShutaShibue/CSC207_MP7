import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BlockChainTest {
    @Test
    public void BlockChainTest1(){
        BlockChain bc = new BlockChain(300);
        Block block = bc.mine(-100);
        bc.append(block);

        assertEquals(true, bc.isValidBlockChain());
        assertEquals(2, bc.getSize());
    }
}
