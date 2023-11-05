// @author Kevin Johanson and Shuta Shibue 11/5/2023
// Class for Mini-proj07 in CSC207
//
// Implements a simple hash class

import java.util.Arrays;

public class Hash {
    // fields ----------------------------------

    byte[] hash;



    // Constructors ----------------------------


    // @param data - an array of bytes
    // constructs a new Hash object that contains the given hash (as an array of bytes).
    public Hash(byte[] data) {
        this.hash = data;
    } // Hash

    // Methods----------------------------------



    // @return - returns the hash contained in this object.
    public byte[] getData(){
        return hash;
    } // getData

    // @return - returns true if this hash meets the criteria for validity, i.e., its first three indices contain zeroes.
    public boolean isValid(){
        for(int i = 0; i < 3; i++) {
            if (hash[i] != 0) {
                return false;
            } // if 
        } // for 
        return true;
    } // isValid


    // @return - returns the string representation of the hash as a string of hexadecimal digits, 2 digits per byte.
    public String toString(){
        String sHash = "";
        for(int i = 0; i < hash.length; i ++) {
            sHash = sHash.concat(String.format("%02x", Byte.toUnsignedInt(hash[i])));
        } // for
        return sHash;
    } // toString

    
    // @param other - an instance of Hash using the instanceof operator.
    // @return - returns true if this hash is structurally equal to the argument.
    public boolean equals(Object other){
        if (other instanceof Hash) { 
            return Arrays.equals(((Hash) other).getData(), hash);
        } // if 
        return false;
    } // equals

} // Hash
