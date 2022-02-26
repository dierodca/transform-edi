# Transform-EDI

## Introduction
This project implements the capabilities to validate and transform EDI files to JSON format.

## What you’ll need
+ A favorite text editor or IDE
+ JDK 11 or later
+ Gradle 7.0.1

### Build your project with Gradle Wrapper

```
------------------------------------------------------------
compile artifact command
------------------------------------------------------------

./gradlew clean build
./gradlew install

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






