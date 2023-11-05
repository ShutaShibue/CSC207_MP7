// @author Kevin Johanson and Shuta Shibue 11/5/2023
// Class for Mini-proj07 in CSC207
//
// Implements a simple hash class tester

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HashTests {

    // test that fails every time
    @Test
    public void willFail() {
        assertEquals(1, 2);
    } //willFail


    //test to make sure getData works on a "normal" array of bytes
    @Test
    public void getDataTester() {
        byte[] byteArray = {1,2,3,4,5}; // a temporary array of bytes
        Hash tester = new Hash(byteArray);
        assertEquals(tester.getData(), byteArray);
    } //getDataTester
    

    //test to make sure getData works on a empty array of bytes
    @Test
    public void emptyGetDataTester() {
        byte[] byteArray = {}; // a temporary array of bytes
        Hash tester = new Hash(byteArray);
        assertEquals(tester.getData(), byteArray);
    } //emptyGetDataTester

    // tests to make is valid works when given a valid input
    @Test
    public void isValidPass() {
        byte[] byteArray = {0,0,0,1,1,1,1}; // a temporary array of bytes
        Hash tester = new Hash(byteArray);
        assertEquals(tester.isValid(), true);
    } //isValidPass

    // tests to make is valid works when given a invalid input
    @Test
    public void isValidFail() {
        byte[] byteArray = {0,1,2,0,8}; // a temporary array of bytes
        Hash tester = new Hash(byteArray);
        assertEquals(tester.isValid(), false);
    } //isValidFail

    // tests to make is valid works when given an empty input
    @Test
    public void isValidempty() {
        byte[] byteArray = {}; // a temporary array of bytes
        Hash tester = new Hash(byteArray);
        assertEquals(tester.isValid(), false);
    } //isValidEmpty

    // tests toString
    @Test
    public void toStringTester() {
        byte[] byteArray = {0,0,0,15,1,1,1,1,1,1}; // a temporary array of bytes
        Hash tester = new Hash(byteArray);
        assertEquals(tester.toString(), "0000000F010101010101");
    } //isValidFail
}
