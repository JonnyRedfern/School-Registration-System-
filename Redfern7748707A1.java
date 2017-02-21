  //-----------------------------------------
     // NAME  : Jonathan Redfern
     // STUDENT NUMBER : 7748707
     // COURSE  : COMP 2150
     // INSTRUCTOR : Mr.Domaratzki
     // ASSIGNMENT : assignment 1
     // QUESTION : question 1
     //
     // REMARKS: This program simulates a school's registration system it adds new students coureses and students to the system, has student records/transcripts
     // you can add students to a course and they will either be added to the waitlist or to the course itself or not be accepted if they dont have the correct
     // pre-requisites.
     //-----------------------------------------



import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Redfern7748707A1 {


  public static void main(String[] args) {
    Registration reg = new Registration();

    System.out.println("Please enter the name of the .txt file that you wish you feed into the program.");
    //gets name of file from user.
    Scanner fileNameGet = new Scanner(System.in);
    String fileName = fileNameGet.nextLine();
    File txtFile = new File(fileName);

    try
    {

      Scanner reader = new Scanner(txtFile);
      String input = reader.nextLine();
      String[] test = input.split(" ");
      String[] commands = input.split(" ");

      //keep on reading file and and end program when line is just "quit"
      while(!commands[0].equals("QUIT"))
      {
        if(commands[0].equals("NEW"))
        {
          //this just adds the full name to the student
          String name = "";
          for(int i = 2; i < commands.length; i++)
          {
            if(i == commands.length-1)
              name += commands[i];
            else
              name += commands[i] + " ";
          }

          reg.addStudent(commands[1],name);
        }//if your adding a new student to the system
        else if(commands[0].equals("COURSE"))
        {
          String courseName = commands[1] + " " + commands[2];
          boolean preReqsFound = true;

          //checks system to see if all preReqs are found
          for(int i = 3; i < commands.length-1; i+=2)
          {
            String preReqName = commands[i] + " " + commands[i+1];
            if(reg.searchCourse(preReqName) == null)
            {
              preReqsFound = false;
              break;
            }
          }

          if(preReqsFound)
          {
            reg.addCourse(courseName);
            for(int i = 3; i < commands.length-1; i+=2)
            {
              String preReqName = commands[i] + " " + commands[i+1];
              reg.addPreReq(courseName,preReqName);
            }
          }
        }//if your adding a new course to the system
        else if(commands[0].equals("ADD"))
        {
          String courseName = commands[2] + " "+commands[3];
          reg.enrollStudent(commands[1],courseName);
        }//if your adding a student to a course or to the waiting list of a course
        else if(commands[0].equals("REMOVE"))
        {
          String courseName = commands[2] + " " +commands[3];
          reg.removeStudentWait(commands[1],courseName);
        }//if your removing a student from the waiting list of a course
        else if(commands[0].equals("TRANSCRIPT"))
        {
          String courseName = commands[2] + " " + commands[3];
          if(commands[4].equals("PASS"))
            reg.passStudent(commands[1],courseName);
          else if(commands[4].equals("FAIL"))
            reg.failStudent(commands[1],courseName);
          else
            reg.currentStudent(commands[1],courseName);
        }//if your adding a course to the students transcript i.e pass, fail, or current
        else if(commands[0].equals("CAPACITY"))
        {
          String courseName = commands[1] + " " +commands[2];
          reg.increaseSize(courseName,Integer.parseInt(commands[3]));
        }//if your increasing the size of a course capacity
        else if(commands[0].equals("STATUS"))
        {
          reg.studentStatus(commands[1]);
        }//if you wish to know the status of a student i.e what courses hes in, has failed, is waiting for ect.
        else if(commands[0].equals("LISTS"))
        {
          String courseName = commands[1] + " " + commands[2];
          reg.courseStatus(courseName);
        }//if you wish to know information on a course i.e whos in the class, who is on waiting list
        else if(commands[0].equals("#"))
        {
          for(int i = 1; i < commands.length; i++)
          {
            System.out.print(commands[i].toLowerCase() + " ");
          }
          System.out.println();
        }//if just a comment line

        input = reader.nextLine();
        input = input.toUpperCase();
        commands = input.split(" ");
      }//while
      System.out.println("DONE.");
      reader.close();
      fileNameGet.close();
    }catch(FileNotFoundException e)
    {
     System.out.println("File not found.");
    }

  }

}
