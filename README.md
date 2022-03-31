# Diary of trello testing project

## Tools quick overview

* Application location:  [Trello for desktop web-browsers](https://trello.com)
* Client: Windows 10, Chrome last. Server: Google cloud + Jenkins.
* Test type priority: Integration tests are prioritised.
* Testing documentation: Git Markdown files (.md), Google Drive Docs (.gdoc)
* Source code editing and run tools: IntelliJ IDEA (IDE), JDK 8, Maven 3, TestNG, Selenium 4, ChromeDriver.
* Report tool: Allure report.
* TestOps tool: Jenkins CI/CD.
For detailed info, please, check the [Test plan document.](https://docs.google.com/document/d/1l68Rcsw-6cTbhl-qOcNNxPaNueLskr_ZQUb1wRXZaB8/edit?usp=sharing)

## Credentials and links

* [Trello address and credentials for the test automation:](https://trello.com)
Login constants for tests:

Primary user
LOGIN: trellou0@gmail.com
PASSWORD: Trellouser999Te!42

2nd invited user
LOGIN: trello2nduser@gmail.com
PASSWORD: Trellouser999Te!42

Workspaces constants for tests: TEN_BOARDS_WORKSPACE, WORKSPACE25

* [Google drive Test suite list public link.](https://docs.google.com/spreadsheets/d/1gaVGRTgIrKwP1MWMsqEYsPVpk4Y6xZjXLurVHDh1ZCc/edit?usp=sharing)

* [Test plan public link.](https://docs.google.com/document/d/1l68Rcsw-6cTbhl-qOcNNxPaNueLskr_ZQUb1wRXZaB8/edit?usp=sharing)

* [GitHub address and SSH public key for source code sharing:] (https://github.com/oxygasa/trello_testing_project) git@github.com:oxygasa/trello_testing_project.git

* [Jenkins address and credentials for the pipelines editing:] (http://34.64.230.195:8080/)
lOGIN: Dzmitry_Samasiuk
PASSWORD: TrelloTest334
 

## Goal checklist

1. [DONE] Create a Test Plan 
2. [DONE] Make a Test Design by creating Test Suite Lists.
3. [DONE] Do the defect reports during testing.
4. [IN PROGRESS ... 40%] Make an Automation testing scenarios and code it.
5. [DONE] Connect to CI/CD system.

## Progress history:

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

Mentoring updates: need to start earlier TestOps preparation via CI/CD tool "Jenkins". 
Powered by Google Cloud Virtual Machine (GNU Linux Ubuntu 20.04 LTS)
Jenkins workspace address http://34.64.230.195:8080/
Credentials:
----- User: Dzmitry_Samasiuk
----- Password : TrelloTest334