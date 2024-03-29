# Template for micro-service in Kotlin #
[![Build Status](https://dev.azure.com/butzist/DevOpsDemo/_apis/build/status/DevOpsDemoTF.DevOpsDemo-template-Kotlin?branchName=master)](https://dev.azure.com/butzist/DevOpsDemo/_build/latest?definitionId=5&branchName=master)

### Description ###
Micro-service template to use with my [DevOpsDemo](https://github.com/DevOpsDemoTF/DevOpsDemo)

### Features ###
* Build in multi-stage Docker container
* Configuration via environment variables
* Logging in JSON
* State passed to API handlers
* Health-check endpoint
* Prometheus metrics
* Unit tests with JUnit-compatible output
* API/integration tests with docker-compose

### Links ###
* [Java multi-stage Docker build](http://paulbakker.io/java/docker-gradle-multistage/)
* [Structured logging with JSON](https://stackoverflow.com/questions/54934658/how-to-write-slf4j-over-logback-logs-as-json)