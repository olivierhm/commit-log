# commit-log

>>> IN CONSTRUCTION <<<

Example project to create producers and subscribers to a "commit log"
Uses Kafka as a publish/subscribe messaging system.

## Build

Maven build: use the provided POM file.
Tested with Visual Studio Code equipped with Microsoft/RedHat's Java extensions.

## Run

Using a containerized version of Kafka with a slightly customized docker-compose.yml file that may be found in the kafka/kafka-docker directory.

Prerequisites:
- Ensure Docker Desktop is installed on your system.
- Ensure you use the custom version and further adapt the hosname/IP address to your environment.
- Then, the following commands issued from the kafka/kafka-docker directory should start the container and let you verify it it running:
  > docker-compose up -d
  > docker-compose ps
- Once done, use the following command to shut the container down:
  > docker-compose stop

Thus far, using VSCode's <Run|Debug> commands inlined in the sources for the consumer(s) and for the producer(s) found at:
  > src\main\java\com\ohm\commitlog\consumer\CLConsumer.java
  > src\main\java\com\ohm\commitlog\producer\CLProducer.java

A sample JUnit test file may also be found at:
  > src\test\java\com\ohm\commitlog\message\CLMessageTest.java

