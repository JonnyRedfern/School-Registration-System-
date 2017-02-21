  // CLASS: COMP 2150
     //
     // Author: Jonathan Redfern, 7748707
     //
     // REMARKS: Ths is the registration class that will be called during the main file it has all the methods that
     //adds students and courses to the system, also adds students to courses etc.
     //-----------------------------------------
public class Registration {
 private List studentList;//list of registered students
 private List courseList;//list of existing courses
  
  public Registration() { 
    studentList = new List();
    courseList = new List();
  }//constructor
  
  /*Adds new student to the system
   * first param is student number second parameter is stuent name
   */
  public void addStudent(String studentNum, String name)
  {
    if(studentList.insert(new Student(studentNum,name)))
      System.out.println("CONFIRMED.");
  }//addStudent
  
  /*Adds new course to the system
   * param is the course number
   */ 
  public void addCourse(String courseNum)
  {
    if(courseList.insert(new Course(courseNum)))
      System.out.println("CONFIRMED.");
  }//addCourse
  
  /*adds a course as a pre req of another course
   *first param is parent course, second param is pre req course to parent
   */
  public void addPreReq(String courseNum,String preReqNum)
  {
    Course courseSearch = searchCourse(courseNum);//searches for desired course in course list
    Course preReqSearch = searchCourse(preReqNum);//searches for pre req in course list
    
    if(courseSearch == null || preReqSearch == null)
      System.out.println("NOT FOUND.");
    else
    {
      courseSearch.insertPreReq(preReqSearch);
    }
  }//addPreReq
  
  //tries to add a student to the course, it could be to the waiting list or the actual course
  public void enrollStudent(String studentNum, String courseNum)
  {
    Student studentSearch = searchStudent(studentNum);
    Course courseSearch = searchCourse(courseNum);
    if(studentSearch == null || courseSearch == null)
      System.out.println("NOT FOUND.");
    else
    {
      courseSearch.insertStu(studentSearch);
    }
  }//enrollStudent
  
  /*Searches for course in course list
   * param is the specified course num
   * returns a boolean value
   */ 
  public Course searchCourse(String courseNum)
  {
    Item found = courseList.search(courseNum);
    if(found == null)
      return null;
    else
      return (Course)(courseList.search(courseNum).getData());
  }//searchCourse
  
  /*searches for student in student list
   * param is student num
   * returns student object if found and null if not
   */ 
  public Student searchStudent(String studentNum)
  {
    Item found = studentList.search(studentNum);
    if(found == null)
      return null;
    else
      return (Student)(studentList.search(studentNum).getData());
  }//searchStudent
  
  /*Adds a passed course to the students transcript
   * First param is student number, second param is course number
   */
  public void passStudent(String studentNum, String courseNum)
  {
    Student studentSearch = searchStudent(studentNum);
    Course courseSearch = searchCourse(courseNum);
    if(studentSearch == null || courseSearch == null)
      System.out.println("NOT FOUND.");
    else
    {
      studentSearch.insertPassed(courseSearch);
      System.out.println("CONFIRMED.");
    }
  }//passStudent
  
  //inserts a course that the student is currently taking into his/her transcript
  public void currentStudent(String studentNum,String courseNum)
  {
    Student studentSearch = searchStudent(studentNum);
    Course courseSearch = searchCourse(courseNum);
    //if student or course isnt in system
    if(studentSearch == null || courseSearch == null)
      System.out.println("NOT FOUND.");
    else
    {
      studentSearch.insertCurr(courseSearch); 
      System.out.println("CONFIRMED.");
    }
  }//currentStudent
  
  /*Adds a failed course to the students transcript, also removes student from any coureses that needed the failed course as a pre req
   * first param is student number, second is course number
   */ 
  public void failStudent(String studentNum, String courseNum)
  {
    Student studentSearch = searchStudent(studentNum);
    Course courseSearch = searchCourse(courseNum);
    //if student isnt in system or course isnt in system
    if(studentSearch == null || courseSearch == null)
      System.out.println("NOT FOUND.");
    else
    {
      ItemInstance curr = courseList.getFirst();
      while(curr != null)
      {
        //if the pre req course that student failed is found remove the student from the curr course if he is there
        if(((Course)curr.getData()).searchPreReq(courseNum) != null)
        {
          ((Course)curr.getData()).removeFailed(studentNum); 
          studentSearch.removeFromCourse(curr.getData());
        }
        curr =curr.getNext(); 
      }
      studentSearch.insertFailed(courseSearch);//adds this failed course to student transcript
      courseSearch.removeFailed(studentSearch.getId());//removes student from original failed course
      System.out.println("CONFIRMED.");
    }
  }//failStudent
  
  /*increases the size of a specified course
   * first param is size increase, second param is course number
   */ 
  public void increaseSize(String courseNum, int n)
  {
    Course courseSearch = searchCourse(courseNum);
    if(courseSearch == null)
      System.out.println("NOT FOUND.");
    else
    {
      courseSearch.increaseSize(n); 
      System.out.println("CONFIRMED.");
    }
  }//increaseSize
  
  //removes student from the waiting list of a course
  public void removeStudentWait(String studentNum, String courseNum)
  {
    Student studentSearch = searchStudent(studentNum);
    Course courseSearch = searchCourse(courseNum);
    
    if(studentSearch == null || courseSearch == null)
      System.out.println("NOT FOUND.");
    else
    {
      if(!(studentSearch.removeFromWait(courseSearch.getId()))  || !(courseSearch.removeFromWait(studentSearch.getId())))//if student is in system but not in course
        System.out.println("NOT APPLICABLE.");
      else
      System.out.println("CONFIRMED.");
    }
  }//removeStudentWait
  
  //prints status of a current student
  public void studentStatus(String studentNum)
  {
    Student studentSearch = searchStudent(studentNum);
    if(studentSearch == null)
      System.out.println("NOT FOUND.");
    else
      studentSearch.printStatus();
  }//studentStatus
  
  //prints status of a course
  public void courseStatus(String courseNum)
  {
    Course courseSearch = searchCourse(courseNum);
    if(courseSearch == null)
      System.out.println("NOT FOUND.");
    else
      courseSearch.printCourse();
  }//courseStatus
  
  
}
