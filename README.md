# Software Quality Engineering - System Testing
This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [Moodle](https://github.com/moodle/moodle).

Moodle is a widely used learning management system (LMS) that allows educators, administrators, and learners to create and access educational content in a flexible and customizable way. It supports features such as creating courses, managing users, uploading resources, conducting quizzes, tracking progress, and much more. Its extensible nature and modular design make it a popular choice for online education.

## Installation
1. **Download Moodle**:  
   - Visit the official Moodle GitHub repository: [https://github.com/moodle/moodle](https://github.com/moodle/moodle).  
   - Download the latest release as a `.zip` file.  
   - Extract the downloaded folder to your local system.  

2. **Install XAMPP**:  
   Download and install [XAMPP](https://www.apachefriends.org/download.html) with the required PHP version (>=8.1).  

3. **Move Moodle to XAMPP's Root Directory**:  
   - Navigate to your XAMPP installation directory.  
   - Move the extracted Moodle folder into the `htdocs` directory.  

4. **Create a Database for Moodle**:  
   - Open the XAMPP control panel and start the **MySQL** service.  
   - Open your browser and go to `http://localhost/phpmyadmin`.  
   - Create a new database named `moodle` and set the character set to `utf8mb4_unicode_ci`.  

5. **Configure Moodle**:  
   - Open your browser and navigate to `http://127.0.0.1/moodle`.  
   - Follow the setup instructions:  
     - Enter the database name as `moodle`.  
     - Use `localhost` as the database host and leave the database user as `root` with no password (default XAMPP settings).  

6. **Start XAMPP Services**:  
   - Open the XAMPP control panel and start **Apache** and **MySQL**.  

7. **Access Moodle**:  
   - Open your browser and go to `http://127.0.0.1/moodle` to access Moodle. 
    
## What we tested
In this assignment, we tested the following use cases in Moodle:

*User story:* A teacher removes a student from an extra-time group.

*Preconditions:* There is a course with a teacher, and the teacher has assigned a student to a group that allows extra time during tests.

*Expected outcome:* The student is successfully removed from the group and no longer receives extra time during tests.

*User story:* A student moves to the next page in a test.

*Preconditions:* There is a course with a test containing multiple pages, and the student is actively taking the test.

*Expected outcome:* The student is able to navigate to the next page in the test without any issues.

## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results
Update all README.md files (except for d-e, see Section 1). Specifically, replace all $$*TODO*…$$ according to the instructions inside the $$.

## Detected Bugs
We detected the following bugs:

1. Bug 1: 
   1. General description: ...
   2. Steps to reproduce: ...
   3. Expected result: ...
   4. Actual result: ...
   5. Link to the bug report: (you are encouraged to report the bug to the developers of the software)
2. Bug 2: ...

$$*TODO* if you did not detect the bug, you should delete this section$$  
