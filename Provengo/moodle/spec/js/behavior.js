


bthread('Student moving to the next page in a test', function () {
  //  waitFor(Event('student_login_complete'));
    let studentSession1 = new SeleniumSession('studentSession1', 'chrome'); // Initialize the admin session
    studentSession1.start(BASE_URL); // Start the session at the base URL
    studentSession1.studentLogin_1();
  //  ensureWindowAvailable(studentSession1);
    studentSession1.studentGoToCourse();
//    ensureWindowAvailable(studentSession1);
    studentSession1.studentGoToQuiz();
    request(Event('student_moving_complete'));
});


bthread('Admin removing student from extra-time group', function(){
  //  waitFor(Event('admin_login_complete'));
    let adminSession1 = new SeleniumSession('adminSession1', 'chrome'); // Initialize the admin session
    adminSession1.start(BASE_URL); // Start the session at the base URL
    adminSession1.adminLogin_1();
 //   ensureWindowAvailable(adminSession1);
    adminSession1.studentGoToCourse();
   // ensureWindowAvailable(adminSession1);
    adminSession1.adminGoToGroup();
    adminSession1.adminRemoveStudent();
    request(Event('admin_removing_complete'));
});


bthread('coordinateActions', function () {
    // Wait for both setup tasks to complete
    sync({ waitForAll: [Event('admin_removing_complete'), Event('student_moving_complete')] });
});