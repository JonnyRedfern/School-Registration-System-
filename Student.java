  // CLASS: COMP 2150
     //
     // Author: Jonathan Redfern, 7748707
     //
     // REMARKS: This is the student class, it will hold information on each individual student
     //
     //-----------------------------------------
public class Student extends Item {
  private String name;//students name
  private List enrolled; //list of classes student is enrolled in 
  private List waiting;//list of classes student is waiting to get into
  private Transcript trans;
  
  public Student(String studentNum,String name) {
    super(studentNum);
    this.name = name;
    trans = new Transcript(studentNum,name);
    enrolled = new List();
    waiting = new List();
  }//constructor
  
  //inserts course into enrolled list and removes it from waiting list
  public void insertEnrolled(Course input)
  {
    waiting.remove(input.getId());
    enrolled.insert(input); 
  }//addEnrolled
  
  //inserts a course that the student is currently in into his transcript
  public void insertCurr(Course input)
  {
    trans.insertCurrent(input); 
  }//insertCurr
  
  //inserts a failed course into the students transcript
  public void insertFailed(Course input)
  {
    trans.insertFailed(input);
  }
  
  //inserts a passed course into the students transcript
  public void insertPassed(Course input)
  {
    trans.insertPassed(input); 
  }
  
  //inserts course into waiting list
  public void insertWaiting(Course input)
  {
    waiting.insert(input); 
  }//addWaiting
  
  //removes a course from the students waiting or enrolled list
  public void removeFromCourse(Item course)
  {
    if(enrolled.remove(course.getId()) == null)
      waiting.remove(course.getId());
  }//removeFromCourse
  
  //removes a course from the students waiting list, list
  public boolean removeFromWait(String courseNum)
  {
    if(waiting.remove(courseNum) == null)
      return false;
    else
      return true;
  }//removeFromWait
  
  //this method accepts a list of pre reqs as the parameter and searches it for classes completed or currently enrolled in by the student
  public boolean searchPreReq(List preReqList)
  {
    ItemInstance curr = preReqList.getFirst();
    boolean found = false;
    //search enrolled list for the pre Req
    while(curr != null && !found)
    {
      if(enrolled.search(curr.getId()) != null)
        found = true;
      curr = curr.getNext();
    }
    
    //if not in enrolled list search for current or passed courses in transcript
    if(!found)
      found = trans.searchPreReq(preReqList);
    
    return found;
  }//searchPreReq
  
  //checks already passed list to see if student has already completed the course
  boolean alreadyPassed(Course input)
  {
    return trans.searchPassed(input); 
  }//alreadyPassed
  
  public String getName()
  {
    return name; 
  }//getName()
  
  //prints status of student
  public void printStatus()
  {
    trans.printTrans();
    System.out.print("Courses student " + this.getId() + " is currently enrolled in:");
    enrolled.printList();
    System.out.print("Courses student " + this.getId() + " is currently waiting for:");
    waiting.printList();
    
  }//printStatus
  
  
}
