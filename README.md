# krakentest
Kraken Test is the automation project to achieve the task given in the assignment.In this I have used Selenium Cucumber Maven project to achieve the objective

Pre-requisite
--------------
* Docker latest - https://www.docker.com/
* Maven - https://maven.apache.org/download.cgi
* JDK 8

Framework Architecture
--------------
	Krakentest
		|
		|_src/test/java
		|	|_com.kraken.automation
		|	|	|_StepDefinition.java
		|	|	|_TestRunner.java
		|_src/test/resources
		|	|_features
		|	|	|_brokenlinks.feature
		|	|	|_console_errors.feature
   		|	|	|_responsecode.feature
    	|pom.xml
    	|docker-compose.yml
	|Dockerfile
    

* **src/test/resources/features** - All the cucumber features files (files .feature ext) goes here.
* **src/test/java/com/kraken/automation/StepDefinition** - I have defined step defintion under this package for your feature steps.
* **src/test/java/com/kraken/automation/TestRunner** - This file contains cucumber runner (TestRunner.java) where you can configure your glue code location (step defintions), define test result output format in html. 
* **pom.xml** - Contains all dependencies and plugins
* **docker-compose.yml** - Contains steps to run project in a dockerized mode
* **Dockerfile** - Contains steps to run project in a dockerized mode

Technologies Used
-----------------
* Maven
* Selenium WebDriver
* Cucumber
* Java
* JUnit

Running test
--------------
Checkout the project and go to your project directory from terminal and hit following commands
* docker-compose up -d (This command will create a hub on a container and also firefox and chrome nodes attached to the hub)
* mvn test

Note : I have created dockerfile and also added configurations in the docker-compose.yml file to execute the dockerfile. However in dockerfile i needed to give static IP address of the machine where it is running and moreover i was unable to add volume mount in the dockerfile as * I am completely beginner when it comes to docker.* To have a look please uncomment the commented lines in docker-compose.yml file and also update the IP address when initiating the driver in StepDefinition.java

Execute below commands to run

	*docker-compose build
	*docker-compose up 

Reports
--------------

A html report is generated in \target\cucumber-reports\index.html
