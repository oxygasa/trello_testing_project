# Diary of trello testing project


* Application location:  [Trello for desktop web-browsers](https://trello.com)

* Environment: Windows 10, Chrome last.
* Test type priority: Integration tests are priorytised.

## Tools quick overview
* Testing documentation: Git Markdown files (.md), Google Drive Docs (.gdoc)
* 
* Source code editing and run tools and dependencies: IntelliJ IDEA (IDE), JDK 8, Maven 3, TestNG, Selenium 4, ChromeDriver.
* Report tool: Allure report.
* TestOps tool: Jenkins CI/CD.
For detailed info, please, check the [Test plan document.](https://docs.google.com/document/d/1l68Rcsw-6cTbhl-qOcNNxPaNueLskr_ZQUb1wRXZaB8/edit?usp=sharing)

## Goal checklist
1. [DONE] Create a Test Plan
2. Make a Test Desing by creating Test Suite Lists.
3. Make an Autotesting scenarios and code it.
4. Connect to CI/CD system.

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
* Automation Framework and project structure creation has been started. 
* Automation Tools (Dependencies) have been added and plugged in to the project structure.

**09 Mar - 10 Mar 2022 - External job**

Shift 11 a.m. - 11 p.m.

**11 Mar 2022 - 13 Mar 2022 - Testing job**
* Employee's English assessment has been completed.
* Automation scripting is started.
* Packages, classes, methods with empty tests filling are created according to the structure of the 
[Test suite list.](https://docs.google.com/spreadsheets/d/1gaVGRTgIrKwP1MWMsqEYsPVpk4Y6xZjXLurVHDh1ZCc/edit?usp=sharing)
* Register (Sign up) functionality is covered by 60% automated test cases in case of blocker: Google Recaptcha.
* Login (Sign in) method is implemented and don't call recaptcha if credentials are correct.

**14 Mar - 15 Mar 2022 - External job**

Shift 11 a.m. - 11 p.m.

**16 Mar 2022 - Testing job**

* Automation tests are observed by QA Team Lead.

Questions:
1. Recaptcha on reg.
2. ChromeOptions driver(options) asking for code - regular browser local storage sharing within the web driver.  

Ideas what to do:

-----------