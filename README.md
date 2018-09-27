# RayonProtocol KYC Systyem Prototype

This is a prototype of RayonProtocol's KYC System


## About

### [Requirements](https://findainc.atlassian.net/wiki/spaces/BLOC/pages/367690328/26.+KYC+System?src=jira)


## Getting Started

### Prerequisite

- [Java 8](https://www.oracle.com/technetwork/java/index.html)

- [Gradle 4](https://gradle.org/install/)

- Set enviroment variables for wallet configuration used in 
`/src/main/resources/application.yml`
```bash
export WALLET_CREDENTIAL_SOURCE=path/to/your/keyfile
export WALLET_CREDENTIAL_PASSWORD=passwordForKeyfile
```

### startup
```
gradle bootRun
```