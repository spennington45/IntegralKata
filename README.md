# IntegralKata

This is the kata provided by Integral for technical skill assessment for Steven Pennington. 

# Setup

## Database

The database used in this project is PostgresSQL, and can be downloaded at the link below if needed.
https://www.postgresql.org/download/

To set up the db run the `SetupDB.sh` script in root/Database. NOTE If you have any db called "intigral" already you may not want to run this script. 

## Java

This project is a Spring project using Mavin and should be opened as an existing mavin project in the directory of the pom.xml found in root/IntegralKata.

To make this interact with the database on your computer you may need to change a few things in the integration tests and resources folder. 

Resources folder can be found in root/IntegralKata/src/main/resources where you will want to change the values of username and password and perhaps the URL if you are not using the default Postgres URL.

Test folder can be found in root/IntegralKata/src/test/java/IntegrationTests and the same changed made to the resources will need to be made in each test file.

## Tests

This project is using JUnit 4 tests. 

# Notes

If you have any db called "intigral" already you may not want to run the `SetupDB.sh` script. This is worth repeating as I do not want to rewin anyone's day.

There is a commit history in my repo that shows the time spend on different parts as well as the order of events for this assessment. You can see the approach with the different red and green cycles for TDD that I used.  The commit messages will also give you an idea of what I was working on at a glance, although some refactoring went on as well for some commits.  

I spent approximately 2 hours on the coding and a day planning the project out in my head.  

I chose Mavin and Spring as my starting point as opposed to the starter code provided because I am familiar with it and thought my 2 hours was better spend building something that would show off my skills rather then learning a new framework.

I eventually would like to make this a REST api and do a Vue.js front end but there is no way I could get to that in a day let alone 2 hours, but that is the direction I would head in.  I did include a controller that does nothing to help illustrate the direction I wanted to go. 

## Tools used

Spring initializr https://start.spring.io/

Eclipse IDE with java 8 and jdk 1.8

VSCode to write the shell script

DB Visualizer for generation running of sql scripts

GitBash for git and running shell script