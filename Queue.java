  // CLASS: COMP 2150
     //
     // Author: Jonathan Redfern, 7748707
     //
     // REMARKS: This is the queue class which will be used to hold students on the waiting list for a course
     //
     //-----------------------------------------
public class Queue {
  private int currSize;
  private ItemInstance first;
  private ItemInstance last;
  
  public Queue() { 
    first = null;
    last = null;
    currSize = 0;
  }//constructor
  
  //inserts student into the queue
  public boolean insert(Item input)
  {
    /*clears linking Items just to be safe
     input.setNext(null);
     input.setPrev(null);*/
    //special case for when the queue is empty
    if(!search(input))
    {
      if(currSize == 0)
      {
        first = new ItemInstance(input);
        last = first;
      }
      else
      {
        ItemInstance temp = new ItemInstance(input);
        last.setNext(temp);
        temp.setPrev(last);
        temp.setNext(null);
        last = temp;
      }
      currSize++;
      return true;
    }//checks to see that its not already in queue
    else
    {
      System.out.println("DUPLICATE.");
      return false;
    }
  }//insert
  
  public Item pop()
  {
    //if theres students in the queue
    if(currSize != 0)
    {
      ItemInstance temp = first;
      first = first.getNext();
      temp.setNext(null);
      temp.setPrev(null);
      currSize--;
      return temp.getData();
    }
    else
    {
      return null;
    }
  }//pop
  
  //removes a student from this queue
  public boolean remove(String id)
  {
    ItemInstance curr = first;
    //searches list for the student id
    while(curr != null && curr.getId().compareTo(id) != 0)
    {
      curr = curr.getNext();
    }
    //if the student is found
    if(curr != null)
    {
      if(curr.getId().compareTo(id) == 0)
      {
        //special case for when student is first in list
        if(curr == first)
        {
          pop();
        }
        else if(curr == last)
        {
          curr.getPrev().setNext(null);
          curr.setPrev(null);
          currSize--;
        }
        else
        {
          curr.getNext().setPrev(curr.getPrev());
          curr.getPrev().setNext(curr.getNext());
          currSize--;
        }
      }//if student is found
      return true;
    }//if curr != null
    return false; //if student is not found
  }//removeId
  
  private boolean search(Item input)
  {
    boolean duplicate = false;
    ItemInstance curr = first;
    
    //iterates list until item is found or all items are checked
    while( curr != null && !duplicate)
    {
      if(curr.getId().compareTo(input.getId()) == 0)
        duplicate = true;
      else
        curr = curr.getPrev();
    }
    return duplicate;
  }
  
  //returns currSize of queue
  public int getSize()
  {
    return currSize; 
  }//getSize
  
  public void printQueue()
  {
    ItemInstance curr = first;
    while(curr != null)
    {
      if(curr.getNext() == null)
        System.out.print(((Student)(curr.getData())).getName() + " " + curr.getId());
      else
        System.out.print(((Student)curr.getData()).getName() + " " + curr.getId() + ", ");
      
      curr = curr.getNext();
    }
    System.out.println();
  }//printQueue
  
  
}
