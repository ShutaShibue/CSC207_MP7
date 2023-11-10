import java.io.Console;

/**
 * porgram to interact with our BlockChain
 *
 * @author Kevin Johanson, Shuta Shibue
 */

public class BlockChainDriver {

    public static void main(String[] args) {

        // Create the console object
        Console console = System.console();

        boolean quit = false; // variable for quiting while loop

        boolean sQuit = false; // variable for quiting out sub while loops

        String command;

        String ammount;

        String nonce;

        // checks if more only one argument is given
        if ((args.length > 1) || (args.length == 0)) {
            System.err.println("Too many arguments: only one needed");
            return;
        }

        // also need to check that the argument is an int
        // --------------------------------------------------------------------------

        // creates a new BlockChain with the starting amount given in the first argument
        BlockChain Chain = new BlockChain(Integer.valueOf(args[0]));

        // while loop for interactive text interface
        while (quit == false) {

            // gets user input
            command = console.readLine("Command? ");

            // checks to see what the input is and uses its corresponding BlockChain method
            if (command.equals("mine")) {
                ammount = "";
                while (sQuit == false) { // makes sure ammount input is a #

                    ammount = console.readLine("Amount transferred? ");
                    try {
                        int temp = Integer.parseInt(ammount);
                        sQuit = true; // to exit the loop
                    } catch (NumberFormatException e) {
                        System.out.println("not a # try again");
                    }                    
                }
                Block block = Chain.mine(Integer.valueOf(ammount));

                sQuit = false; // resets the while exiter

                System.out.println("amount = " + ammount +", nonce = " + block.getNonce() + "\n");

                System.out.println(Chain.toString());
            } else if (command.equals("append")) {

                ammount = "";
                while (sQuit == false) { // makes sure ammount input is a #

                    ammount = console.readLine("Amount transferred? ");

                    try {
                        int temp = Integer.parseInt(ammount);
                        sQuit = true; // to exit the loop
                    } catch (NumberFormatException e) {
                        System.out.println("not a # try again");
                    }
                }

                sQuit = false; // reset while loop exiter

                while (sQuit == false) { // makes sure nonce input is a #
                    
                    nonce = console.readLine("nonce? ");

                    try {
                        int temp = Integer.parseInt(nonce);

                        // runs the append with the given ammount given nonce the previous hash and the current size of the chain
                        Chain.append(new Block(Chain.getSize(), Integer.valueOf(ammount), Chain.getHash(), Long.parseLong(nonce))); 
                        sQuit = true; // to exit the loop
                    } catch (NumberFormatException e) {
                        System.out.println("not a # try again");
                    }
                }
                

                System.out.println(Chain.toString());
            } else if (command.equals("remove")) {
                Chain.removeLast();

                System.out.println(Chain.toString());

            } else if (command.equals("check")) {
                if (Chain.isValidBlockChain()) {
                    System.out.println("Chain is Valid!");
                } else if (Chain.isValidBlockChain()) {
                    System.out.println("Chain is not Valid");
                } // else if

                System.out.println(Chain.toString());

            } else if (command.equals("report")) {
                Chain.printBalances();

                System.out.println(Chain.toString());

            } else if (command.equals("help")) {
                // prints out options menu
                System.out.println(
                        "Valid commands: \n mine: discovers the nonce for a given transaction\n append: appends a new block onto the end of the chain\n remove: removes the last block from the end of the chain\n check: checks that the block chain is valid\n report: reports the balances of Alexis and Blake\n help: prints this list of commands\n quit: quits the program");

                System.out.println(Chain.toString());

            } else if (command.equals("quit")) {
                quit = true;

                System.out.println(Chain.toString());

            } else {
                System.out.println("not a valid command. Try again.");

            } // else

        }
    }

}
