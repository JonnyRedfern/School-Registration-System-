/*This is basically the glue that keeps the Student and Class classes together so they can both be used in the same
 linked list
 */
public abstract class Item  {
  
  private String id;//is used for student num/ course name/num
  
  public Item(String id) { 
    this.id = id;
  }//constructor
  
  public String getId()
  {
    return id; 
  }
  
 
  
}
