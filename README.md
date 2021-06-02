# nordsonomu_automation

 Nordson OMU Web Automation using Selenium 3 with TestNG and Page Object Model design pattern using JAVA lanaguage, integrated with 3rd party jars like Apache POI, Log4j, Testng, ReportNG, Extent Reports, Allure Reports.
 
This project to run native browser for angularJS  using Hybrid framework with page object pattern.

# Based on:
- selenium-java 3.141.59
- testng 7.1.0
- apache.poi 4.1.2
- poi-ooxml 4.1.2
- extentreports 4.0.9
- log4j 1.2.17
- reportng 1.1.4
-Allure Reports

# Supports:
- Native AngulaJS
- Chrome browser
- Safari browser
- Supports Page Object Model
- Contains sample Test Case in TestNG
- Supports Extent, ReportNG reporting

# Desired Capabilities:
capabilities: browser: chrome, firefox, ie

# Hybrid Framework Design pattern
*Selenium Webdriver* - For launching the Selenium WebDriver is a W3C Recommendation
- WebDriver is designed as a simple and more concise programming interface.
- WebDriver is a compact object-oriented API.
- It drives the browser effectively.

*TestNG* - TestNG is a testing framework inspired from JUnit and NUnit, but introducing some new functionalities that make it more powerful and easier to use.

*Apache POI* -Apache POI is a popular API that allows programmers to create, modify, and display MS Office files using Java programs. 
It contains classes and methods to decode the user input data or a file into MS Office documents.

*Log4j* -It views the logging process in terms of levels of priorities and offers mechanisms to direct logging information to a great variety of destinations, such as a database, file, console, UNIX Syslog, etc.
log4j has three main components:
- loggers: Responsible for capturing logging information.
- appenders: Responsible for publishing logging information to various preferred destinations.
- layouts: Responsible for formatting logging information in different styles.

*Extent Reports*: With the ExtentReports library, you can create beautiful, interactive and detailed reports for your tests. Add events, screenshots, tags, devices, authors or any other relevant information you decide is important to create an descriptive and a stunning report that you can totally control.


# Follow the below commands to run Sample Tests -

1. Clone the project - https://github.com/sourcebitsllc/nordsonomu_automation.git

2. Install Maven dependencies by saving  **pom.xml** or in the terminal run the command as mvn clean install.

3.1 Run the scripts -> Update the testng.xml file with TestCases and run the xml file

3.2 Run the scripts -> By using run.bat file (make sure to give you project user directory)

4. Verify the logs in the selenium.logs file

5. Verify the HTML reports under test-output (testng-reports.xml, html folder > nordson*.html file, reportNG reports (index.html))


# Execution
- Run through testng.xml (Adding all the testcases)
- Run through mvn clean test
- Run through jenkins build - mvn clean install


# Sample Test Cases covered are:
- Login Test case with properties values
- Verify all the links in the portal
- Login Test Cases using Data Driver (.xls file)
- Registration Test Case (.xls file)
- Pressure MDS File
