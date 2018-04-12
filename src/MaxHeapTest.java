import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import java.util.Collection;


public class MaxHeapTest
{
   private MaxHeap heap;


   @Before
   public void setUp() throws Exception
   {
       //Creating a collection
       Student Boyd = new Student("Boyd", 40, 3.0);
       Collection<Student> collection = new ArrayList<Student>();
       collection.add(Boyd);
       
       //Putting collection into heap for testing
       heap = new MaxHeap(collection);
       
       //Rewriting a heap for testing
       heap = new MaxHeap(10);
       heap.insert(new Student("Susan", 60, 3.5));
       heap.insert(new Student("Ben", 70, 3.4));
       heap.insert(new Student("Reed", 120, 4.0));
       heap.insert(new Student("Johnny", 50, 1.2));
       Student Joe = new Student("Joe");
       Joe.setGPA(2.3);
       Joe.setUnits(25);
       heap.insert(Joe);
   }

   @Test
   public void test()
   {
       //Testing Student functions
       assertEquals("Reed", heap.getMax().getName());
       assertEquals(120, heap.getMax().units(), .000001);
       assertEquals(4.0, heap.getMax().gpa(), .000001);

       //Testing extraction and proper sorting
       assertEquals(4.0, heap.extractMax().gpa(), .000001);
       assertEquals(3.5, heap.getMax().gpa(), .000001);
       assertEquals(3.5, heap.extractMax().gpa(), .000001);
       assertEquals(3.4, heap.getMax().gpa(), .000001);

       //Testing changeKey, bubbling, and maxHeapify
       Student changeJohnny = new Student("Johnny", 50, 1.2);
       changeJohnny.setIndex(1);
       heap.changeKey(changeJohnny, 3.8);
       assertEquals(3.8, heap.getMax().gpa(), .000001);
       assertEquals(3.8, heap.extractMax().gpa(), .000001);
       heap.changeKey(changeJohnny, 3.0);
       Student changeBen = new Student("Ben", 70, 3.4);
       changeBen.setIndex(0);
       assertEquals(3.4, heap.getMax().gpa(), .000001);
       heap.changeKey(changeBen, 3.5);
       changeBen = new Student("Ben", 70, 3.5);
       heap.changeKey(changeBen, 2.8);
       changeBen = new Student("Ben", 70, 2.8);
       heap.changeKey(changeBen, 3.9);
       
       //Testing when the student is not found
       heap.changeKey(changeBen, 4.0);
           
       assertNotSame(3.6, heap.getMax().gpa());
       assertEquals(3.9, heap.extractMax().gpa(), .000001);
              
       //Testing things when the heap is empty
       heap.extractMax();
       heap.changeKey(changeJohnny, 4.0);
              
       //Testing for extractMax when no elements
       try
       {
           assertEquals("No maximum value:  the heap is empty.", heap.getMax());
           fail("should have thrown an exception");
       }
       catch(Exception e)
       {
           String expectedMessage = "No maximum value:  the heap is empty.";
           assertEquals( "Exception message must be correct", expectedMessage, e.getMessage() );
                  
       } 
   }
}