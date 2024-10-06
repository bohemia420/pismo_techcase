Pismo Banking Application Tech Case
====

### **Abstract**:
This repository contains implementation to the Banking Application tech case. \
The implementation comes as a 
- dockerized container, controlled with `docker-compose` atop a docker engine, 
- and has been implemented as a Spring Boot MVC Application, with handlers implemented for
    - creating accounts
    - retrieving accounts
    - creating transactions basis transaction types
- Following SDLC Best Practices, the application has been tested with tests available underneath `/tests/` folder.
- the application has been packaged using `maven` **build tool**, and has been crafted in `IntelliJ IDE`. 

### **USER GUIDE:**

The server i.e the web application, and the client i.e the cURLs for account/transaction CRUDs,
have been modelled as the following scripts serving as respective endpoints:
- `scripts/build-and-run.sh`: This script (optionally)* builds the docker container and runs the same serving the SpringBoot Application at 8080 EXPOSED endpoint.
- `scripts/client.sh`: This bash script takes in arguments arround 
  - `--get-account`: an `account-id` integer, e.g `./scripts/client.sh --get-account 1 `
  - `--post-account`: a payload like ```'{"document_number": "12345678901"}' ``` to create an account.
  - `--post-transaction`: a payload like ```'{"account_id": 1, "operation_type_id": 4, "amount": 123.45}'``` specifying the account, operation type, and the amount aspects of a transaction.

Therefore, as following steps, we can demonstrate this Banking App implementation:

1. get into the project directory. Ensure docker and docker-compose have been installed.
```shell
➜  pismo_techcase git:(master) ✗ pwd
/Users/khanna/Documents/repos/personal/interviews/pismo_techcase

➜  pismo_techcase git:(master) ✗ docker -v
Docker version 26.1.1, build 4cf5afa
➜  pismo_techcase git:(master) ✗ docker-compose -v
Docker Compose version v2.27.0-desktop.2
```

2. test the application by running unit tests, enlisted inside `src/test/java` folder.
```shell
➜  pismo_techcase mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------< org.example:pismo_techcase >---------------------
[INFO] Building Pismo Banking API 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
...
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running banking.example.repository.OperationTypeRepositoryTest
...
[INFO] Results:
[INFO] 
[INFO] Tests run: 16, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.199 s
[INFO] Finished at: 2024-10-06T15:40:24+05:30
[INFO] ------------------------------------------------------------------------
➜
```

3. set the Spring Application for Banking Api UP and RUNNING. This spins up a docker container.
```shell
➜  pismo_techcase ./scripts/build-and-run.sh --build                                     
Building Docker image...
[+] Building 9.1s (15/15) FINISHED                                                                                                                                                    docker:desktop-linux
 ...
View build details: docker-desktop://dashboard/build/desktop-linux/desktop-linux/srqrgtz0nvm7xxjt64qvei0tn
 ...
Docker image built successfully.
Running the Docker container...
 ...
[+] Running 2/2
 ✔ Network pismo_techcase_bank-network  Created                                                                                                                                                       0.0s 
 ✔ Container pismo_techcase-bank-api-1  Started                                                                                                                                                       0.1s 
➜  pismo_techcase  
```

4. Verify that docker container is up and running:
```shell
➜  pismo_techcase docker ps
CONTAINER ID   IMAGE                                 COMMAND                  CREATED             STATUS             PORTS                                                                                                                                  NAMES
9587d3e90acb   bank-api:latest                       "/bin/sh -c 'java -j…"   About an hour ago   Up About an hour   0.0.0.0:8080->8080/tcp                                                                                                                 pismo_techcase-bank-api-1
```

5. Run the client queries:
```shell
➜  pismo_techcase ./scripts/client.sh --post-account '{"document_number": "12345678901"}'                             
Creating a new account with the following payload: {"document_number": "12345678901"}
{"accountId":1,"document_number":"12345678901"}

➜  pismo_techcase ./scripts/client.sh --get-account 1                                                                 
{"accountId":1,"document_number":"12345678901"}%                                                                                                                                                           ➜  pismo_techcase ./scripts/client.sh --get-account 2
account_id 2 not found

➜  pismo_techcase ./scripts/client.sh --post-transaction '{"account_id": 1, "operation_type_id": 4, "amount": 123.45}'
Creating a new transaction with the following payload: {"account_id": 1, "operation_type_id": 4, "amount": 123.45}
{"transactionId":1,"account":{"accountId":1,"document_number":"12345678901"},"operationType":{"operationTypeId":4,"description":"PURCHASE_WITH_INSTALLMENTS"},"eventDate":"2024-10-06T14:36:47.091517","amount":-123.45}

➜  pismo_techcase ./scripts/client.sh --post-transaction '{"account_id": 1, "operation_type_id": 3, "amount": 123.45}'
Creating a new transaction with the following payload: {"account_id": 1, "operation_type_id": 3, "amount": 123.45}
{"transactionId":2,"account":{"accountId":1,"document_number":"12345678901"},"operationType":{"operationTypeId":3,"description":"CREDIT_VOUCHER"},"eventDate":"2024-10-06T14:37:01.952843","amount":123.45}

➜  pismo_techcase ./scripts/client.sh --post-transaction '{"account_id": 1, "operation_type_id": 2, "amount": 123.45}'
Creating a new transaction with the following payload: {"account_id": 1, "operation_type_id": 2, "amount": 123.45}
{"transactionId":3,"account":{"accountId":1,"document_number":"12345678901"},"operationType":{"operationTypeId":2,"description":"WITHDRAWAL"},"eventDate":"2024-10-06T14:37:05.603658","amount":-123.45}

➜  pismo_techcase ./scripts/client.sh --post-transaction '{"account_id": 1, "operation_type_id": 1, "amount": 123.45}'
Creating a new transaction with the following payload: {"account_id": 1, "operation_type_id": 1, "amount": 123.45}
{"transactionId":4,"account":{"accountId":1,"document_number":"12345678901"},"operationType":{"operationTypeId":1,"description":"NORMAL_PURCHASE"},"eventDate":"2024-10-06T14:37:09.354441","amount":-123.45}
 
```

### **Important Files/Folders**:
- `scripts`: folder for bringing the application UP and RUNNING, and then testing with client cURLs.
- `src/main/java`: contains the code for the Spring Application for Banking System.
- `src/test/java`: contains the test cases for all classes in Spring Application Above.
- `src/main/java/banking.example/BankApiApplication`: The entry-point for Banking Application (SpringBootApplication).
- `src/main/java/banking.example/DataLoader`: A class that is run as an alternative to script to create the TxnType Metadata.
- `Dockerfile` and `docker-compose.yml`: files to run the App as a containerized application and managed using `docker-compose`.
- `pom.xml`: with maven as our build tool, this contains all the dependencies and build metadata.
