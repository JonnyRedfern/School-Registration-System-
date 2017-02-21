  // CLASS: COMP 2150
     //
     // Author: Jonathan Redfern, 7748707
     //
     // REMARKS: This class holds the information for a created course i.e whos in it, what pre reqs are needed etc
     //
     //-----------------------------------------
public class Course extends Item{
  private int size;//the number of students that the class can hold
  private Queue waiting;//the students on the waiting list
  private List preReq;//list of pre requesit courses needed to join this class
  private List enrolled;//list of students currently in the course
  
  public Course(String classNum) { 
    super(classNum);
    size = 0;
    waiting = new Queue();
    preReq = new List();
    enrolled = new List();
  }//contructor
  
  //increases the class size by n amount
  public void increaseSize(int n)
  {
    size+=n; 
    //if there are students waiting add them to the class
    for(int i = 0; i < n && waiting.getSize() > 0; i++)
    {
      Item temp = waiting.pop();
      enrolled.insert(temp);
      ((Student)temp).insertEnrolled(this);
    }
  }//increaseSize
  
  //this inserts a new student into the course if there is room
  public void insertStu(Student newStu)
  {
    boolean preReqFound = newStu.searchPreReq(preReq);//boolean value will turn true if student has at least one pre req for the course
    boolean alreadyUsed = false;//variable used to see if student is already enrolled in this class or if theyve passed it before
    if(newStu.alreadyPassed(this))
      System.out.println("DUPLICATE.");
    else if(preReqFound)
    {
      //if there's room inside the course
      if(size > enrolled.getSize())
      {
        System.out.println("ADDED.");
        newStu.insertEnrolled(this);
      }
      //if the class is full
      else
      {
        System.out.println("CONFIRMED.");
        alreadyUsed = waiting.insert(newStu);
        if(alreadyUsed)
          newStu.insertWaiting(this); 
      }
    }
    else
    {
      System.out.println("NO PREREQ"); 
    }
  }//insertStu
  
  //inserts a new pre req course
  public void insertPreReq(Item newCourse)
  {
    preReq.insert(newCourse); 
  }//insertPreReq
  
  /*searches pre req list to see if a course that a student failed was in it so it can remove the student from this course
   *param is course id
   */
  public Item searchPreReq(String id)
  {
    Item found = preReq.search(id);
    return found;
  }//searchPreReq
  
  /*removes a student from this course if he is enrolled or on the waiting list due to him failing a pre req class
   *param is student id
   */ 
  public void removeFailed(String id)
  {
    Item found = enrolled.remove(id);
    
    //if student was not found in enrolled section check waiting list
    if(found == null)
      waiting.remove(id);
    
    if(waiting.getSize() > 0 && size > enrolled.getSize())
      enrolled.insert(waiting.pop());//inserts a student from the waiting list into the class
    
  }//removeFailed
  
  //Removes a student from the wait list for this course if they are found.
  //returns true if found false if not
  public boolean removeFromWait(String studentNum)
  {
    return waiting.remove(studentNum); 
  }//removeFromWait
  
  
  public void printCourse()
  {
    System.out.print("Students currently enrolled in " + this.getId()+":"); 
    enrolled.printList();
    System.out.print("Students currently waiting for " + this.getId() + ":");
    waiting.printQueue();
  }//printCourse
  
}
