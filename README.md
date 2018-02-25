# Copia

Welcome to Copia! This is a simplified scheduling algorithm for matches food donations to local charities.

![Screenshot of CLI](docs/img/cli_screen_shot.png)

# Table of Contents

- [Configuration]()
- [Rules]()
- [Implementation Details]()
    - [Server]()
        - [Overview]()
            - [Core Datatypes]()
            - [Model]()
            - [Schedule]()
                - [Algorithm]()
    - [Database]()
        - [Overview]()
            - [jdbcTemplate]()
        - [Tables]()
- [How I made it]()
    - [Initial thoughts]()
    - [Plans]()
    - [Approach]()
    - [Unit test results]()
    - [Overall result]()
    - [Results analysis]()
    - [Conclusion]()

# Configuration
## Quick Start

 - You will need Java 8 and Maven to build and run this project.
     - Maven is a Java build/package manager that will use the pom.xml file.
 - Run the following in a terminal window.
 
    ```bash
        $ mvn clean package -DskipTests
        $ java -jar target/hangman-app-0.0.1-SNAPSHOT.jar
    ```


## Step By Step

- Install JDK and make sure your JAVA_HOME environment variable is set.
    - Check with echo

        ```bash
        $ echo $JAVA_HOME
        /Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home
        ```
    - On a Mac, you can set JAVA_HOME in the .bash_profile in your home directory.

      ```bash
        $ emacs .bash_profile

        export JAVA_HOME=$(/usr/libexec/java_home)

        $ source .bash_profile

        $ echo $JAVA_HOME
        /Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home
      ```
- Configure maven (Java build tool / package manager)
    - Install from https://maven.apache.org/download.cgi and follow installation instructions from https://maven.apache.org/install.html .
        - On Mac, the step "Add the bin directory of the created directory apache-maven-3.5.2 to the PATH environment variable" can be done by adding the following to your .bash_profile    

        ```bash
        export PATH=$PATH:/opt/apache-maven-3.5.2/bin:$PATH
        ```
- Build project
  ```bash
    $ mvn clean package -DskipTests
  ```
    - This will build and install the necessary dependencies like Spring Boot, H2, and more.
- Run the project
    ```bash
    $ java -jar target/copia-0.0.1-SNAPSHOT.jar
    ```
    
# Rules

# Implementation Details

## Server
### Overview
#### Core Datatypes
#### Model
#### Schedule
##### Algorithm

## Database
### Overview
#### jdbcTemplate
#### Tables

# How I made it
## Initial thoughts
## Plans
## Approach
## Unit test results
## Overall result
## Results analysis
## Conclusion
