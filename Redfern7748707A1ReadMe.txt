This program only accepts a specific syntax for its input. I provided a text file with a long list of commands ready to go, all you need to do is type into the promp "schoolData.txt" (excluding the qutation marks).
Duplicate students won't be added to classes. You can remove students, will return student not found if student doesn't exist. Wont let student enroll in class he/she has already passed.
Syntax:

//Add a new student

	NEW studentNumber firstName lastName

//Add a new course/courses
	
	COURSE COMP 1010
	
	or
	
	COURSE COMP 1010 1020 3400 

//Add transcript for a student

	TRANSCRIPT studentNum COMP 1010 PASS/FAIL

	Ex: 1234567 COMP 1010 PASS

//Add to class will either be enlisted rejected or added to waiting list. If student doesn't have pre requisite he/she will not be accepted

	ADD studentNum COMP 1010

//Show status of student

	STATUS studentNum

//Increase capacity of class
		
	CAPACITY COMP 1010 amountIncrease


In terminal write
javac Redfern7748707A1.java
then push enter to compile, then type in terminal
java Redfern7748707A1

to run it.
It will prompt you to enter the name of the .txt file that you are trying to feed into my program so 
just make sure that text file is also saved in the same file as the one i sent you and you can just
type in the name of it when the program is running and it should work.

Thank you.