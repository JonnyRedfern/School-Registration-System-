  // CLASS: COMP 2150
     //
     // Author: Jonathan Redfern, 7748707
     //
     // REMARKS: this class acts as the nodes that are linked together inside of the list and queue classes inside of each of these are 
     //the actual coures or student objects that hold their data
     //-----------------------------------------
public class ItemInstance extends Item {
  
 private Item data; //this will hold either the student object or the course object
 private ItemInstance next;
 private ItemInstance prev;
  
  public ItemInstance(Item object) { 
    super(object.getId());
    data = object;
    next = null;
    prev = null;
  }//constructor
  
  public ItemInstance getNext()
  {
    return next; 
  }//getNext
  
  public ItemInstance getPrev()
  {
    return prev; 
  }//getPrev
  
  public void setNext(ItemInstance input)
  {
    next = input; 
  }//setNext
  
  public void setPrev(ItemInstance input)
  {
    prev = input; 
  }//setPrev
  
  public Item getData()
  {
    return data; 
  }//getData
  
  
}
