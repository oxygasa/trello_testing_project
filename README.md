# Diary of trello testing project

## Intro

* This project has a developing stage **"In progress"**.
* Progress and a number of completed Test cases you can see [by this link](https://docs.google.com/spreadsheets/d/1gaVGRTgIrKwP1MWMsqEYsPVpk4Y6xZjXLurVHDh1ZCc/edit?usp=sharing).

## Tools quick overview

* Application location:  [Trello for desktop web-browsers](https://trello.com)
* Client: Windows 10 Enterprise LTS, Chrome v. 100+ last. 
* Server: Google cloud node (Jenkins), EPAM cloud node (Jenkins), Localhost node (Jenkins).
* Test type priority: Integration tests are prioritised. API tests are allowed.
* Testing documentation: Git Markdown files (.md), Google Drive Docs (.gdoc)
* Source code editing and run tools: IntelliJ IDEA Community Edition 2022.1 (Freeware IDE), Java Dev Kit 11 (Apache zulu-11 Openjdk), Apache Maven 3.8.3, Apache Gradle 7.4.2, TestNG 7.3.0, Selenium 4.1.3, WebDrivers Cross browser by Bonigarcia.
* Report tool: QA meta Allure reports 2.17.3, slf4j-api 1.7.36, assertj 3.22.0, Maven Surefire 3.8.1, Gradle HTML reports 7.4.2
* TestOps tool: CI/CD Jenkins 2.342, TeamCity (optional)
For detailed info, please, check the [Test plan document.](https://docs.google.com/document/d/1l68Rcsw-6cTbhl-qOcNNxPaNueLskr_ZQUb1wRXZaB8/edit?usp=sharing)
* MailTools: Javax (Optional), Spring-Starter (Optional), [Emkei mail sender service](https://emkei.cz/), [Temp-mail receiver](https://temp-mail.io/).
* Comfortable screen resolution for UI web elements founding: 1920x1080 and higher. You can use less, but mind the elements must not overlap each other.

## Credentials and links

Login constants for testing the [Trello login page](https://trello.com/login):

**Primary user:**
**LOGIN:** trellou0@gmail.com
**PASSWORD:** Trellouser999Te!42

**2nd invited user:**
**LOGIN:** trello2nduser@gmail.com
**PASSWORD:** Trellouser999Te!42

**Workspaces constants for tests:** TEN_BOARDS_WORKSPACE, WORKSPACE25


**[Jenkins address](https://jenkins.epam.com/jenkins)
  and credentials** for the pipelines editing (credentials also are allowed for localhost node):

**LOGIN:** Dzmitry_Samasiuk@epam.com
**PASSWORD:** TrelloTest334


**Testing manual style documentation:**
* [Test suite list.](https://docs.google.com/spreadsheets/d/1gaVGRTgIrKwP1MWMsqEYsPVpk4Y6xZjXLurVHDh1ZCc/edit?usp=sharing)

* [Test plan.](https://docs.google.com/document/d/1l68Rcsw-6cTbhl-qOcNNxPaNueLskr_ZQUb1wRXZaB8/edit?usp=sharing)

* [Bug (Defect) reports.](https://docs.google.com/document/d/1BAZBrh_fKAvKcIpSMAeZU9E4eP0P4QHBsINjis0egMA)

SSH and redirecting to GitHub page by clicking: [git@github.com:oxygasa/trello_testing_project.git](https://github.com/oxygasa/trello_testing_project)
 

## Goal checklist

1. **[DONE]** Create a Test Plan 
2. **[DONE]** Make a Test Design by creating Test Suite Lists.
3. **[DONE]** Do the defect reports during testing.
4. **[IN PROGRESS ... 50%]** Make an Automation testing scenarios and code them.
5. **[DONE]** Connect to CI/CD system.
6. **[DONE]** Link all builders with an external servers.

## Progress history (log):

**21 Feb 2022 - Testing job** 

Test plan has been created.
Test Plan location: [Test plan by a link with a reader access.](https://docs.google.com/document/d/1l68Rcsw-6cTbhl-qOcNNxPaNueLskr_ZQUb1wRXZaB8/edit?usp=sharing)

**22 Feb 2022 - Testing job**

Test suite list has been started. 
[Test suite list progess is allowing by a link with a reader access.](https://docs.google.com/spreadsheets/d/1gaVGRTgIrKwP1MWMsqEYsPVpk4Y6xZjXLurVHDh1ZCc/edit?usp=sharing)

**23 Feb - 24 Feb 2022 - External job**

Shift 11 a.m. - 11 p.m.

**25 Feb - 27 Feb 2022 - Testing job**

Test suite list has been finished at the point: 
all "for free" functions has been tested. 
[Test suite list progess is allowing by a link with a reader access.](https://docs.google.com/spreadsheets/d/1gaVGRTgIrKwP1MWMsqEYsPVpk4Y6xZjXLurVHDh1ZCc/edit?usp=sharing)

**28 Feb - 1 Mar 2022 - External job**

Shift 11 a.m. - 11 p.m.

**02 Mar 2022 - Testing job**

* Test suite list is observed by QA Team Lead. 
* Defined a list of test cases for automation. 
* It was decided to test only for free functionality of the application.

**03 Mar 2022 - Testing job**

Bug (issue) report list has been started. 
[All issues are located by the link.](https://docs.google.com/document/d/1BAZBrh_fKAvKcIpSMAeZU9E4eP0P4QHBsINjis0egMA/edit?usp=sharing)

**04 Mar - 07 Mar 2022 - External job**

Shift 11 a.m. - 11 p.m.

**08 Mar 2022 - Testing job**
* English assessment preparations. The date for exams is 11 Mar 2022.
* Automation Framework and project structure creation has been finished. 
* Automation Tools (Dependencies) have been added and plugged in to the project structure.

**09 Mar - 10 Mar 2022 - External job**

Shift 11 a.m. - 11 p.m.

**11 Mar 2022 - 13 Mar 2022 - Testing job**
* Employee's English assessment has been completed.
* Automation scripting is started.
* Packages, classes, methods with empty tests filling are created.
* All Blockers notations about Registration, Sign In and Dropdown menues (React-select hidden html) are located in the Test Suite Lists.
* Test cases have been automatised (TC ID TRE001 - TRE 015)

**14 Mar - 15 Mar 2022 - External job**

Shift 11 a.m. - 11 p.m.

**16 Mar 2022 - Testing job**

Questions:
1. How to make already logged in Chrome browser via Google acc? Which filepath you used? [ChromeOptions] (https://stackoverflow.com/questions/31062789/how-to-load-default-profile-in-chrome-using-python-selenium-webdriver/31063104#31063104)
2. Drop down menues in Trello. How to find via xpath the hidden HTML (React-select hidden html)?
3. Ideas about Explicity waits for AJAX? Is it prohibited to use Thread.sleep for AJAX? I'd want to see your examples of Explicity waits.
4. Comments in the code, is this ok for the clean code rules?

Ideas from the mentor what to do:
1. Explicity waiters could be implemented by special methods using few Selenium waiters together.
2. Try to cooperate with Drop down menues via "Actions" class and "sendKeys" and "perform" methods.
3. Comments are ok for explanation the code. But must be short.
4. ChromeOptions class is disabled for last ChromeDriver versions.
5. Cookies have short-time exiration. The better way is to log in by classic login via Trello credentials method and within login/password precondition data.
6. The main target of Mentoring program is marked: try impossible scenarios transform into possible solution. More situations are good for discovering.

**17 Mar 2022 - Testing job**
* Waiters implementation have been created and added to all Test cases. Sometime AJAX elements ask to compare Thread.sleep method within Waiters, because Explicity waiting couldn't catch elements on a client's side postfactum after page is uploaded. 
* All dropdown blockers have been repaired by keyboard selection compromise.
* Boards creation and deletion have been automated by loops and exceptions catching to increase manual deletion after test failure.

**18 Mar - 20 Mar 2022 - External job**

Shift 11 a.m. - 11 p.m.

**21 Mar - 22 Mar 2022 - Sick days**

Taking medicare that occurred due consequences of heavy physical work executing.
Mentoring weekly updates have been cancelled.

**23 Mar - 24 Mar 2022 - External job**

Shift 11 a.m. - 11 p.m.

**25 Mar - 27 Mar 2022 - Testing job**

* TC complete status till TRE030 + Refactoring (methods cleanup - make steps shorter, comments shorting).
* All repeated methods have the Test Base class and the CommonAction class as base classes. 
* @BeforeTest methodes have been created to make the precondition code shorter.
* Debug tool has been used for make less try/catch situations via solving the source of the problems.

**28 Mar - 29 Mar 2022 - External job**

Shift 11 a.m. - 11 p.m.

**30 Mar - 31 Mar 2022 - Testing job**

* Mentoring updates: need to start earlier TestOps preparation via CI/CD tool "Jenkins". 

* Powered by Google Cloud Virtual Machine (GNU Linux Ubuntu 20.04 LTS)

* Jenkins workspace address http://jenkins.epam.com/jenkins

Credentials:
**User:** Dzmitry_Samasiuk@epam.com
**Password:** TrelloTest334

**1 Apr - 3 Apr 2022 - External job**

Shift 11 a.m. - 11 p.m.

**4 Apr - 5 Apr 2022 - Mentoring updates**

* CI/CD Flaking test repairing.
* Blockers repairing.
* Code refactoring.
* Windows and Linux nodes adding.
* Variations of the reporting systems: Allure report, Maven Surefire, Jenkins command promt.

**6 Apr - 7 Apr 2022 - External job**

Shift 11 a.m. - 11 p.m.


**8 Apr - 10 Apr 2022 - Testing job**

* Massive refactoring.
* Implement the encapsulation.
* Erase static methods.
* Retest the 30% of the test materials.

**11 Apr - 12 Apr 2022 - External job**

Shift 11 a.m. - 11 p.m.

**13 Apr - 15 Apr 2022 Mentoring updates**
* Mentor is on vacation from 8 Apr till 29 Apr
* Massive refactoring.
* Retest the 100% of the test materials made till current date.

**16 Apr - 20 Apr 2022 Testing job**

* Cross the project from Maven to Gradle.
* Make test suites by Test NG and connect them to Gradle.
* Make scripts for running different types of tests.
* Refactor tests by Lazy init.
* Add the parallel run, (UPD: Parallel run ruins preconditions, when they are the same at time).
* Add preconditions and post conditions (UPD: Before methods of classes in TestNG work could ruin test, because can start before super method of login randomly).

**21 Apr - 26 Apr 2022 Testing job**
* The Goal is about to take 40-45 test cases completed till 26 Apr.
* Add a mail sender service for cards values adding via email sending. Trello's recommendations: list must be created before providing email operations. 
* Spring-context and mail sending via javax work correctly. But memory overloading and time-wasting are the reason to use external services for email sending and receiving.
* Remind to make possible copy hidden invite link for 2nd user. (UPD: Done within the method of reading this link from the clipboard).
* An achievement: now all hidden by React-select webElements could be found by parent web element (Selenium 4.1 feature). Dropdown menu hidden values problem is solved.
* Publish a defect reports public link.

**27 Apr 2022 - 28 Apr 2022 - Mentoring updates**
* Mentor is on vacation from 8 Apr till 29 Apr.

**29 Apr - 2 May 2022 - External job**
Shift 11 a.m. - 11 p.m.

**3 May - Finishing Mentoring programm**
* Mark API Rest Assured demo test project as completed.
* Mark Appium demo test project as completed.
* Fill up EPAM Past project section in the Telescope profile.

**4 May - 5 May 2022 - External job**
Shift 11 a.m. - 11 p.m.

**6 May - 10 May 2022 - Final preparations to the technical interview**
Make the project more pretty.
Prepare to the conversation with the interviewer.
