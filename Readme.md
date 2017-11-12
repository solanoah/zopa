# Zopa Rate Calculation system

## Problem Statement
There is a need for a rate calculation system allowing prospective borrowers to obtain a quote from zopa's pool of lenders for 36 month loans.

This new system should strive to provide as low a rate to the borrower as is possible to ensure that Zopa's quotes are as competitive as they can be against our competitors'. 

And also to provide the borrower with the details of the monthly repayment amount and the total repayment amount.


## Assumptions
* That the supplied rate in the excel file is yearly rate
* That a lender's offer can be partly utilized but at the given offer rate i.e. a lender can have availability of £600 but we may only need £300

## Solution
The technical intention is to build a loosely coupled system using Spring.


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
See deployment for notes on how to deploy the project on a live system.

## Prerequisites
You need to install the following tools

* [Java 1.8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Lambda and Annotation were used in the code
* [Maven](https://maven.apache.org/) - Dependency Management

## Building the code:
* Unzip the supplied zip file
* From the terminal (MAC) navigate to the root location of the project, where the POM is.

```
mvn clean install
```

## Running the App
* Sample test files are located at /src/test/resources
```
From the root path, run the following commands:

* mvn exec:java -Dexec.mainClass=com.zopa.dev.AppEntry -Dexec.args="src/test/resources/MarketData.csv 1000"
```

## Running the tests
```
mvn test
```

## Test driven development (TDD) using jUnit
About 14 tests were written to effectively test all scenarios.


## TO DO
* Generate javadoc
* Implement dependency injection for a loosely couple system
* Implement proper logging 

