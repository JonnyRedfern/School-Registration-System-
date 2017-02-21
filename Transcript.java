  // CLASS: COMP 2150
     //
     // Author: Jonathan Redfern, 7748707
     //
     // REMARKS: This is the transcript class, it will hold all of the information on failed, passed or currently enrolled in courses
     //for each individual student
     //-----------------------------------------
public class Transcript {
  private String name;
  private String number;
  private List failed;
  private List current;
  private List passed;
  
  public Transcript(String number, String name) { 
    this.name = name;
    this.number = number;
    failed = new List();
    current = new List();
    passed = new List();
  }//constructor
  
  //inserts course into passed list and also removes course from failed list if its in there
  public void insertPassed(Course input)
  {
    Item removedCourse = failed.remove(input.getId());
    
    //if course was not in failed list check enrolled list
    if(removedCourse == null)
      removedCourse = current.remove(input.getId());
    
    passed.insert(input);
  }//addPassed
  
  //inserts course into current list
  public void insertCurrent(Course input)
  {
    current.insert(input); 
  }//addCurrent
  
  //inserts course into failed list and removes it from enrolled list if it is in there
  public void insertFailed(Course input)
  {
    current.remove(input.getId());
    failed.insert(input); 
  }//addFailed
  
  //this method accepts a list of pre reqs as the parameter and searches it for classes completed or currently enrolled in by the student
  public boolean searchPreReq(List preReqList)
  {
    ItemInstance curr = current.getFirst();
    boolean found = false;
    if(preReqList.getSize() == 0)
      return true;
    else
    {
      while(!found && curr != null)
      {
        if(preReqList.search(curr.getId()) != null)
          found = true;
        else
          curr = curr.getNext();
      }
      
      curr = passed.getFirst();
      
      while(!found && curr != null)
      {
        if(preReqList.search(curr.getId()) != null)
          found = true;
        else
          curr = curr.getNext();
      }
      return found;
    }
  }//searchPreReq
  
  public boolean searchPassed(Course input)
  {
    if(passed.search(input.getId()) == null)
      return false;
    else
      return true;
  }//searchPassed
  
  public void printTrans()
  {
    System.out.print("courses that student "+number+" is currently taking:");
    current.printList();
    System.out.print("Courses that student "+number+" has passed:");
    passed.printList();
    System.out.print("Courses that student "+number+" has failed:");
    failed.printList();
  }//printTrans
  
  
}
