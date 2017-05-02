# ynab-fun [![CircleCI](https://circleci.com/gh/ryanwalker/ynab-fun/tree/master.svg?style=svg&circle-token=c614633bdd4db4479d6ee0c93f7c60e348505d68)](https://circleci.com/gh/ryanwalker/ynab-fun/tree/master)


## Summary
I solved the coding problme here: https://www.interviewzen.com/interview/3NNWPcF

I cleaned it up a bit and turned it into a Spring Boot java microservice. It gets built and tested in CircleCi and deploys automatically to Heroku.

It's deployed here: https://ynab-fun.herokuapp.com

The 2 main classes to look at are:
```
src/main/java/com/ryanwalker/domain/YNABReport.java
src/main/java/com/ryanwalker/rest/api/controller/YnabReportController.java
```


## View the report
Edit `bin/data.json` (this json will get sent to the server and parsed)

To view the report, run the following script
```
./bin/prod_report
```


## Build and run locally
You'll need a java 8 development environment setup for this:
```
./gradlew bootRun
```


## Test locally
Edit `bin/data.json`

Then run
```
./bin/local_report
```

