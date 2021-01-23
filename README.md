# Search_Parameter

This is a simple search parameter to search for data in the database. Built using Java and JSP, MySQL, and on Eclipse IDE, the program takes in the search request and the category from the front-end and gives the search results in a downloadable Excel spreadsheet.<br>
It is built in a fairly simple method so that in case there is a need for change of the database, we can just change the database names and change the connectors. In this program, I have used a database of songs and music to search through.<br>
There is also a test file to serve as an example for how the programs run.

## Requirements

- Any new JDK and an IDE (I have used Java jdk-11.0.2 and Eclipse IDE Version: 2018-12)
- MySQL and the required database tables
- Apache Tomcat v9.0
- mysql-connector-java-8.0.22 JARs
- javax.servlet-3.0+ JARs
- Apache POI JARs
- Selenium WebDriver JARs
- geckodriver (If you want to run on Firefox browser)

## Initiation of the programs

1. Make sure the MySQL server is running. Start your IDE; on Eclipse, create a workspace. Click on *Projects Tab -> Open Projects -> Select the SEARCH_PARA directory*.
2. Now to add all the required JARs, *right-click on the project in the Project Explorer -> select Build Path -> Configure Build Path -> Click on Libraries tab -> Under Classpath, click on Add External JARs -> Add every JAR from the requirements specified above -> Apply and Close*. For this project do not make any changes in the `WEB-INF/lib` or the `WEB-INF/web.xml` files since they either already have some JARs stored, or edited to work according to this project.
3. Now we need to establish a database connection. Run the *DB_Connection.java* file as Java Application, which can be done by right-clicking on the program and hovering over Run tab. This will start the database and ensure that database is in proper state.
4. Next, we can try manually testing the search parameter. Right-click on the *get_values.jsp* file and Run on Server. Here you need to select the server for it to run on, i.e Apache Tomcat v9.0, and then a new tab will open up with the webpage. You can instead go to a browser and navigate locally to http://localhost:8080/SEARCH_PARA/get_values.jsp to test it.
5. Finally, for an automated testing, we can set the search query and the category on the *AutoTest.java* program and run it as Java Application.

## What could be better ?

Though it's a simple project, it lacks in quite a lot of areas. One of them is that the Selenium automated testing just tests if the program runs properly. It could be imporved to check data integrity and such.<br>
If you have any questions, hit me up !
