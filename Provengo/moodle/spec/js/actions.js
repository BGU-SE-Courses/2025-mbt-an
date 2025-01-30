defineAction = function (name, func) {
    SeleniumSession.prototype[name] = function (data) {
        let session = this;
        sync({ request: bp.Event(`Start(${name})`, { session: session, startEvent: true, parameters: data }) });
        func(session, data);
        sync({ request: bp.Event(`End(${name})`, { session: session, endEvent: true, parameters: data }) });
    };
};


defineAction('adminLogin_1', function (session) {
    sync({request: Event('startAdminLogin')});
//    with (session) {
//        click(xpaths.login.loginButton);
//        writeText(xpaths.login.usernameInput, adminUsername);
//        writeText(xpaths.login.passwordInput, adminPassword);
//        waitForClickability(xpaths.login.login2_);
//        click(xpaths.login.login2_);
//
//    }
    sync({request: Event('EndAdminLogin')});
});

defineAction('studentLogin_1', function (session) {
    sync({request: Event('startStudentLogin')});
//    with (session) {
//        click(xpaths.login.loginButton);
//        writeText(xpaths.login.usernameInput, studentUsername);
//        writeText(xpaths.login.passwordInput, studentPassword);
//        waitForClickability(xpaths.login.login2_);
//        click(xpaths.login.login2_);
//
//    }
    sync({request: Event('EndStudentLogin')});
});

defineAction('studentGoToCourse', function (session) {
    sync({
            request: Event('startAdminOrStudentInCourse'),
            waitFor: [Event('EndStudentLogin'), Event('EndAdminLogin')]
        });
//    with (session) {
//        click(xpaths.studentwindow.mycourses)
//        click(xpaths.studentwindow.course1);
//
//    }
    sync({request: Event('endAdminOrStudentInCourse')});
});

defineAction('studentGoToQuiz', function (session) {
    sync({
        request: Event('startStudentInQuiz'),
        waitFor: Event('endAdminOrStudentInCourse')
    });
//    with (session) {
//        click(xpaths.studentwindow.test);
//        click(xpaths.studentwindow.attempquiz);
//        click(xpaths.studentwindow.clickNextPage);
//        click(xpaths.studentwindow.previousPage);
//    }
    sync({request: Event('endStudentInQuiz')});
});

defineAction('adminGoToGroup', function (session) {
    sync({
            request: Event('startAdminInGroup'),
            waitFor: Event('endAdminOrStudentInCourse')
        });
//    with (session) {
//        click(xpaths.extraTimeGroup.participants)
//        click(xpaths.extraTimeGroup.EnrolledUsers)
//        click(xpaths.extraTimeGroup.groups)
//        click(xpaths.extraTimeGroup.chooseGroup)
//    }
    sync({request: Event('endAdminInGroup')});
});

defineAction('adminRemoveStudent', function (session){
   sync({
               request: Event('startAdminRemoving'),
               waitFor: Event('endAdminInGroup')
           });
//   with (session){
//      click(xpaths.extraTimeGroup.addremoveUsers)
//      click(xpaths.extraTimeGroup.chooseUser1Extra)
//      click(xpaths.extraTimeGroup.remove)
//      click(xpaths.extraTimeGroup.chooseUser1NotExtra)
//      click(xpaths.extraTimeGroup.add)
//      click(xpaths.extraTimeGroup.backToGroups)
//   }
       sync({request: Event('endAdminRemoving')});

});

function ensureWindowAvailable(session) {
    try {
         session.waitForVisibility("//body", 1000);
    } catch (e) {
        session.recover(); // Custom recovery function to handle window recovery
        }
    }

SeleniumSession.prototype.recover = function() {

        this.start(BASE_URL);  // Attempt to restart the session

};
