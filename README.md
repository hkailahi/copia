# Copia

Welcome to Copia! This is a simplified scheduling algorithm for matches food donations to local charities.

![Screenshot of CLI](docs/img/cli_screen_shot.png)

This project was built with Spring Boot (Java) and H2.

# Table of Contents

- [Configuration](https://gitlab.com/hkailahi/copia/blob/master/README.md#configuration)
    - [Quick Start](https://gitlab.com/hkailahi/copia/blob/master/README.md#quick-start)
    - [Step By Step](https://gitlab.com/hkailahi/copia/blob/master/README.md#step-by-step)
    - [Interact with Database](https://gitlab.com/hkailahi/copia/blob/master/README.md#interact-with-database)
- [Rules](https://gitlab.com/hkailahi/copia/blob/master/README.md#rules)
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
- [How I Made It]()
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
    
## Interact with Database
- To run SQL queries in H2, please change the boolean [willInteractWithDatabase](https://gitlab.com/hkailahi/copia/blob/master/src/main/java/com/heneli/copia/CopiaApplication.java#L84) in  to true. Please note, you will have to manually shut down the server after you are done with H2!

# Rules

// TODO - 5 miles, categories vs restrictions, open at same time, match from one pickup to many recipients

# Implementation Details

## Application Overview
### High Level Summary

Program Steps:
1. Spring Boot launches Tomcat server with H2 instance available on *localhost:8080*
2. *schema.sql* in src/java/resources/ gets ran before *run()* method in core app (*CopiaApplication.java*), which imports Pickups and Recipients CSVs to tables, and intializes empty *Matches* table
3. List of all pickups and list of all recipients are loaded from H2 into the core application
4. *Schedule* is created, calls *generateSchedule* on itself, and populates the *matchMap* hashtable by keying pickup ids to matches
5. Matches sent from *MatchMap* into H2
6. (Optional) *Matches* table is sorted
7. *Matches* table is exported to a CSV in the project directory
8. (Optional) Application code shuts down Tomcat server

#### Core Datatypes

// TODO - User as superclass to Pickup and Recipient

// TODO - Match

#### Schedule

// TODO - 

##### Algorithm

// TODO - explain
// TODO - filter for qualified recipients (match candidates)
// TODO - for each pickup : find all matches from pickup to qualified candidates
// TODO - parallel stream

### Database
#### jdbcTemplate
#### Tables

# How I Made It
## Initial thoughts

// TODO - summarize first thoughts from initial_thoughts.md and link to it for further exploration

## Plans

// TODO - summarize first sketches from initial_thoughts.md and link to it for further exploration

## Approach

// TODO - Explain why I used database

// TODO - Explain why I switched from Postgres to H2

// TODO - Explain failed attempt using generalized k-sum (Reason: required perfect matches)

// TODO - Explain why I attempted k-sum (Reason: I realized this was going the algorithm be very computationally expensive)

## Unit test results
## Overall result
## Results analysis

// TODO - 1604551 matches

// TODO - Explain repeat subsets exist, which are partial deliveries to recipients in order to get more

// TODO - Explain why eliminating subsets is 1. not useful (we want to deliver smaller subsets anyways, so computing bigger ones should actually be avoided)

// TODO - Explain why eliminating subsets is 2. not possible.. at least on my laptop (powerset problem - exponential algorithm nested at each level of a O(pr^6) + algorithm)

// TODO - Show problem pickups (aka no matches or easily found matches), and how I dealt with them (in k-sum reasearch)

## Conclusion
