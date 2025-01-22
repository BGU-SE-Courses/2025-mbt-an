/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

const BASE_URL = 'http://localhost/?redirect=0';
const courses = 'http://localhost/my/courses.php'
const admin = ''; // If this is meant to hold something specific, consider naming or populating it
const adminUsername = 'teacher'
const adminPassword = '#Aa123456'
const studentUsername = 'user1';
const studentPassword = '#Aa123456';
const xpaths = {
  login: {
    usernameInput: "/html/body/div[2]/div[2]/div/div/section/div/div/div/div/form[1]/div[1]/input",
    passwordInput: "/html/body/div[2]/div[2]/div/div/section/div/div/div/div/form[1]/div[2]/div/input",
    loginButton: "/html/body/div[2]/nav/div/div[2]/div/div/span",
    login2_: "/html/body/div[2]/div[2]/div/div/section/div/div/div/div/form[1]/div[3]/button"
  },

  studentwindow: {
    mycourses : "/html/body/div[2]/nav/div/div[1]/nav/ul/li[3]/a",
    course1: "/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/div/a/span[3]/span[2]",
    test: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/div/div/ul/li[2]/div/div[2]/ul/li/div/div[2]/div[2]/div/div/a",
    attempquiz : "/html/body/div[2]/div[4]/div/div[2]/div/section/div[2]/div[1]/div/div/form/button",
    clickNextPage: "/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[2]/input",
    previousPage : "/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[2]/input[1]"
  },

  extraTimeGroup: {
    participants: "/html/body/div[2]/div[4]/div/div[2]/nav/ul/li[3]/a",
    EnrolledUsers: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/div[1]/div/div[1]/nav/div/div",
    groups: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/div[1]/div/div[1]/nav/div/ul/li[2]/ul/li[2]",
    chooseGroup: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/form/div/div/div[1]/div[1]/select/option",
    addremoveUsers: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/form/div/div/div[2]/div[2]/button",
    chooseUser1Extra: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/div/form/div/table/tbody/tr[1]/td[1]/div/select/optgroup/option[1]",
    remove: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/div/form/div/table/tbody/tr[1]/td[2]/p/input[2]",
    chooseUser1NotExtra: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/div/form/div/table/tbody/tr[1]/td[3]/div/select/optgroup[2]/option[1]",
    add: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/div/form/div/table/tbody/tr[1]/td[2]/p/input[1]",
    backToGroups: "/html/body/div[2]/div[4]/div/div[3]/div/section/div/div/form/div/table/tbody/tr[2]/td/input"

  }

};
