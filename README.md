#EBUSINESS-API

# Introduction
This project is the implementation of the baseline for Regular APIs Microservices (Core), in which the structure for the construction of business domain APIs is found, these are in charge of accessing its data repository (SQL or NoSQL), and expose your domain capabilities.

# What you’ll need
+ A favorite text editor or IDE
+ JDK 11 or later
+ Install Gradle 7.0.1

### Install Gradle

+ **On Unix**

```
$ sudo add-apt-repository ppa:cwchien/gradle
$ sudo apt-get update
$ sudo apt-get install gradle
```

+ **On Windows**

  + [Download from Gradle site](https://docs.gradle.org/current/userguide/installation.html).

  + Unzip the Gradle download to the folder to which you would like to install Gradle, eg. “C:\Program Files”. The subdirectory gradle-x.x will be created from the archive, where x.x is the version.

  + Add location of your Gradle “bin” folder to your path. Open the system properties (WinKey + Pause), select the “Advanced” tab, and the “Environment Variables” button, then add “C:\Program Files\gradle-x.x\bin” (or wherever you unzipped Gradle) to the end of your “Path” variable under System Properties. Be sure to omit any quotation marks around the path even if it contains spaces. Also make sure you separated from previous PATH entries with a semicolon “;”.

  + In the same dialog, make sure that JAVA_HOME exists in your user variables or in the system variables and it is set to the location of your JDK, e.g. C:\Program Files\Java\jdk1.7.0_06 and that %JAVA_HOME%\bin is in your Path environment variable.

  + Open a new command prompt (type cmd in Start menu) and run gradle –version to verify that it is correctly installed.
  
### To test the Gradle installation, run Gradle from the command-line: `gradle`
+ If all goes well, you see a welcome message:

```
:help

Welcome to Gradle 7.0.1

To run a build, run gradle <task> ...

To see a list of available tasks, run gradle tasks

To see a list of command-line options, run gradle --help

To see more detail about a task, run gradle help --task <task>

BUILD SUCCESSFUL

Total time: 6.317 secs
```

+  You now have Gradle installed.

```
gradle -version

------------------------------------------------------------
Gradle 7.0.1
------------------------------------------------------------

Build time:   2021-05-10 16:08:58 UTC
Revision:     67e618faef187783dadd03a34fdab9dc71b85b19

Kotlin:       1.4.31
Groovy:       3.0.7
Ant:          Apache Ant(TM) version 1.10.9 compiled on September 27 2020
JVM:          11.0.10 (Oracle Corporation 11.0.10+8-LTS-162)

```

### Build your project with Gradle 

```
------------------------------------------------------------
compile artifact command
------------------------------------------------------------

./gradle clean build
./gradle install

------------------------------------------------------------
generate jacoco report
------------------------------------------------------------
./gradle jacocoTestReport

```


### Build your project with Gradle Wrapper

```
------------------------------------------------------------
compile artifact command
------------------------------------------------------------

./gradlew clean build
./gradlew install

------------------------------------------------------------
generate jacoco report
------------------------------------------------------------
./gradle jacocoTestReport


```


### Dependency Management

the project dependency management are in the file service-dependencies.gradle

```
Example:

  SPRING_BOOT_RUNTIME = [
            [group: 'org.springframework.boot', name: 'spring-boot-starter-actuator'],
            [group: 'org.springframework.boot', name: 'spring-boot-starter-web'],
            [group: 'org.springframework', name: 'spring-context-support'],
    ]

```


###  Files Clean Code

Task files for clean code good practice validation

+ code-quality-config.gradle
+ cv-framework-config.gradle

The configuration files of the rules for checkstyle, pmd and spotbugs can be found in the forder configuration and in k8s the manifest to deploy the container in kubernetes.


```

    └── config
        └── checkstyle
            └── checkstyle.xml
        └── k8s
            └── transform-edi.yml
        └── pmd
            └── pmd-ruleset.xml
        └── spotbugs
            └── spotbogs-exclude.xml        
```







