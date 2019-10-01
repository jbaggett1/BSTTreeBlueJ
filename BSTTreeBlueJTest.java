
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BSTTreeBlueJTest.
 *
 * @author  Jillian Baggett
 * @version 9/30/19
 */
public class BSTTreeBlueJTest
{   BSTTreeBlueJ<Integer> example;
    BSTTreeBlueJ<Integer> example2;
    BSTTreeBlueJ<Integer> example3;
    /**
     * Default constructor for test class BSTTreeBlueJTest
     */
    public BSTTreeBlueJTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {  example = new BSTTreeBlueJ<Integer>();
       example2 = new BSTTreeBlueJ<Integer>();
       example3 = new BSTTreeBlueJ<Integer>();
    }
    @Test
    public void testInsert()
    { 
      //Test case where both sides branch equally 
      example.insert(6);
      example.insert(12);
      example.insert(3);
      example.insert(1);
      example.insert(8);
      //System.out.println("List is " + example.printTree().toString());
      String testAns = example.printTree().toString();
      String ans1 = "[6, 3, 12, 1, 8]";
      assertEquals(ans1,testAns);
      
      //Test case where only one side is added too
      example2.insert(6);
      example2.insert(12);
      example2.insert(8);
      example2.insert(7);
      example2.insert(14);
      String testAns2 = example2.printTree().toString();
      String ans2 = "[6, 12, 8, 14, 7]";
      assertEquals(ans2,testAns2);
    }
    
    @Test
    public void testRemove()
    {   
      //Testing removing on the root.
      example.insert(6);
      example.insert(12);
      example.insert(3);
      example.insert(1);
      example.insert(8);
      example.remove(6);
      String testAns = example.printTree().toString();
      System.out.println("List is " + example.printTree().toString());
      String ans2 = "[8, 3, 12, 1]";
      assertEquals(ans2, testAns);
      
      //Testing removing a node that has a child in the tree
      example2.insert(6);
      example2.insert(12);
      example2.insert(3);
      example2.insert(1);
      example2.insert(8);
      example2.remove(12);
      String ans3 = "[6, 3, 8, 1]";
      String testAns3 = example2.printTree().toString();
      assertEquals(ans3, testAns3);
      
      
      //Testing remove on a leaf
      example3.insert(6);
      example3.insert(12);
      example3.insert(3);
      example3.insert(1);
      example3.insert(8);
      example3.remove(1);
      String ans4 = "[6, 3, 12, 8]";
      String testAns4 = example3.printTree().toString();
      assertEquals(ans4, testAns4);
      
    }
    
    @Test
    public void testMin()
    { //Find the min of a tree with multiple branches
      example.insert(6);
      example.insert(12);
      example.insert(3);
      example.insert(0);
      example.insert(10);
      assertEquals("0", example.findMin().toString());
    }
    
    @Test
    public void testContains()
    { //Test for node in tree
      example.insert(6);
      example.insert(12);
      example.insert(3);
      example.insert(0);
      example.insert(10);
      assertEquals(true, example.contains(0));
      //Test leaf of tree
      example2.insert(1);
      example2.insert(2);
      example2.insert(3);
      example2.insert(4);
      example2.insert(5);
      assertEquals(true, example2.contains(5));
      //Test negative case 
      example3.insert(1);
      example3.insert(2);
      example3.insert(3);
      example3.insert(4);
      example3.insert(5);
      assertEquals(false, example2.contains(12));
    }
   
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        example = null;
        example2 = null;
        example3 = null;
    }
    
}
