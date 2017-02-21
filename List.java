  // CLASS: COMP 2150
     //
     // Author: Jonathan Redfern, 7748707
     //
     // REMARKS: This is the list class that will be used throughout the program to hold objects together
     //
     //-----------------------------------------
public class List {
  private ItemInstance first;//first item in list
  private int currSize;
  
  public List() { 
    first = null;
    currSize = 0;
  }//constructor
  
  //inserts item into the list
  //returns false if it was already inside of this list, returns true if it was not
  public boolean insert(Item input)
  {
    //if the input item isn't already inside the list
    if(search(input.getId()) == null)
    {
      //special case when the list is empty
      if(first == null)
      {
        first = new ItemInstance(input);
      }
      else
      {
        ItemInstance temp = new ItemInstance(input);
        temp.setNext(first);
        first.setPrev(temp);
        temp.setPrev(null);
        first = temp;
      }
      currSize++;
      return true;
    }//insert if its not already in queue
    else
    {
      System.out.println("DUPLICATE.");
      return false;
    }
  }//add
  
  //this checks to see if the id is found in this list and if it is removes it from this list
  public Item remove(String id)
  {
    ItemInstance found = search(id);//this searches for the id in this list
    
    //if the course/student is found
    if(found != null)
    {
      //special case for when the course/student is first in the list
      if(found == first)
      {
        first = found.getNext();
        found.setNext(null);
        found.setPrev(null);
      }
      //special case for when class/student was last item in list
      else if(found.getNext() == null)
      {
        found.getPrev().setNext(null); 
        found.setPrev(null);
      }
      else
      {
        found.getPrev().setNext(found.getNext());
        found.getNext().setPrev(found.getPrev());
        found.setNext(null);
        found.setPrev(null);
      }
      currSize--;
      return found.getData();
    }//if id is found
    return null;
  }//remove
  
  //returns currSize of the list
  public int getSize()
  {
    return currSize; 
  }//getSize
  
  //this searches for a certain id in this list and if it is found returns the ItemInsance object containing the course or student that was being looked for
  //parameter is the id being searched for
  public ItemInstance search(String input)
  {
    boolean found = false;
    ItemInstance curr = first;
    //checks list until the end or a duplicate is found
    while(curr != null && !found)
    {
      if(curr.getId().compareTo(input) == 0)
        found = true;
      else
        curr = curr.getNext();
    }
    return curr;
  }//checkDup
  
  
  public ItemInstance getFirst()
  {
    return first; 
  }//getFirst
  
  public void printList()
  {
    ItemInstance curr = first;
    //if its a list of students
    if(curr != null)
    {
      if(curr.getData() instanceof Student)
      {
        while(curr != null)
        {
          if(curr.getNext() == null)
            System.out.print(((Student)curr.getData()).getName() + " " + curr.getId()); 
          else
            System.out.print(((Student)curr.getData()).getName() + " " + curr.getId()+", ");
          
          curr = curr.getNext();
        }
      }
      //if its a list of courses
      else
      {
        while(curr != null)
        {
          if(curr.getNext() == null)
            System.out.print(curr.getId()); 
          else
            System.out.print(curr.getId()+", ");
          
          curr = curr.getNext();
        }
      }
    }
    System.out.println();
  }//printList
  
}
