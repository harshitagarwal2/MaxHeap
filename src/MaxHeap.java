import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap
{
   private ArrayList<Student> students;
   
   public MaxHeap(int capacity)
   {
      students = new ArrayList<Student>(capacity);
   }
      
   public MaxHeap(Collection<Student> collection)
   {
      students = new ArrayList<Student>(collection);
      for(int j = size()/2 + 1; j < size(); j++)
      {
          students.get(j).setIndex(j);
      }
      for(int i = size()/2; i >= 0; i--)
      {
          students.get(i).setIndex(i);
          maxHeapify(i);
      }
   }
   
   public Student getMax()
   {
      if(size() < 1)
      {
         throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
      }
      return students.get(0);
   }
   
   public Student extractMax()
   {
      Student value = getMax();
      students.set(0,students.get(size()-1));
      students.remove(size()-1);
      maxHeapify(0);
      return value;
   }
   
   private void bubble(int index)
   {
       if (students.get(index).compareTo(students.get(parent(index))) > 0)
       {
           swap(index, parent(index));
           bubble(parent(index));
       }  
   }
   
   public void insert(Student elt)
   {
       students.add(size(), elt);
       elt.setIndex(size() - 1);
       bubble(size() - 1);
   }
   
   public void changeKey(Student s, double newGPA)
   {
       int index = s.getIndex();
       if(index < size() && students.get(index).compareTo(s) == 0)
       {
           students.get(index).setGPA(newGPA);
           maxHeapify(index);
           bubble(index);
       }
       else 
           System.out.println("Student is not in the heap.");
   }

   private int parent(int index)
   {
       return (index - 1)/2;
   }
   
   private int left(int index)
   {
       return 2 * index + 1;
   }
   
   private int right(int index)
   {
       return 2 * index + 2;
   }
   
   private int size()
   {
       return students.size();
   }
   
   private void swap(int from, int to)
   {
       Student val = students.get(from);
       students.set(from,  students.get(to));
       students.set(to,  val);
       students.get(to).setIndex(to);
       students.get(from).setIndex(from);
   }
   
   private void maxHeapify(int index)
   {
      int left = left(index);
      int right = right(index);
      int largest = index;
      if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0)
      {
         largest = left;
      }
      if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0)
      {
         largest = right;
      }
      if (largest != index)
      {
         swap(index, largest);
         maxHeapify(largest);
      }  
   }
}