Maestro: Development
============

A sample development workflow for Maestro looks like this: launch the basic infrastructure for running Maestro and 
launching the peers/nodes that you need to execute your development.


Building
----

Use the following command to build Maestro:
 
```./mvnw -PPackage clean package```.
 
This should generate the following tarball files:

```
./maestro-agent/target/maestro-agent-1.5.0-SNAPSHOT-bin.tar.gz
./maestro-worker/target/maestro-worker-1.5.0-SNAPSHOT-bin.tar.gz
./maestro-inspector/target/maestro-inspector-1.5.0-SNAPSHOT-bin.tar.gz
./maestro-reports/maestro-reports-tool/target/maestro-reports-tool-1.5.0-SNAPSHOT-bin.tar.gz
./maestro-test-scripts/target/maestro-test-scripts-1.5.0-SNAPSHOT-bin.tar.gz
./maestro-exporter/target/maestro-exporter-1.5.0-SNAPSHOT-bin.tar.gz
./maestro-cli/target/maestro-cli-1.5.0-SNAPSHOT-bin.tar.gz
```

Running the Infrastructure Locally
----

1. Run the infrastructure using the docker compose locally on your computer: 
```docker-compose -f docker-devel-compose.yml up```

The composer images will expose the management interfaces for the Maestro broker and the SUT. If needed, they can 
be accessed via the following URLs:

* URL for the Maestro broker: [http://localhost:18161/](http://localhost:18161/)
* URL for the SUT broker: [http://localhost:8161/](http://localhost:8161/)


Running the Workers Locally
----

To run the workers locally might depend on the IDE, or if you are using the CLI. The usual configuration for the CLI
involves the following steps:

1: Adjust the maestro home property: 

```-Dorg.maestro.home=${project.location}/maestro-java/maestro-worker/src/main/resources/```

**Note**: Replace the pseudo-variable for the project location (`${project.location}`) with the actual directory for the project (ie: /path/to/the/project)

2 Adjust the program arguments so that the workers connect to the local infrastructure:

* Receiver:
 
```-m mqtt://localhost:1884 -r receiver -H localhost -w org.maestro.worker.jms.JMSReceiverWorker -l /storage/tmp/maestro-java/worker/receiver```

* Sender:
 
```-m mqtt://localhost:1884 -r sender -H  localhost -w org.maestro.worker.jms.JMSSenderWorker -l /storage/tmp/maestro-java/worker/sender```



Running a Client
----

This might also depend on the IDE and CLI. The overall steps are:

1. Adjust the maestro home property for the client: 

```-Dorg.maestro.home=${project.location}/maestro-java/maestro-cli/src/main/resources/```

**Note**: Replace the pseudo-variable for the project location (`${project.location}`) with the actual directory for the project (ie: /path/to/the/project).

2. Adjust the command line for the maestro client:

```exec -d ${report.directory} -s ${project.location}/maestro-java/maestro-test-scripts/src/main/groovy/singlepoint/FixedRateTest.groovy```

**Note 1**: Replace the pseudo-variable for the report directory (`${report.directory}`) with the desired location for saving the reports.

**Note 2**: Replace the pseudo-variable for the project location (`${project.location}`) with the actual directory for the project (ie: /path/to/the/project).

3. Set the environment variables for the test: 

```
SEND_RECEIVE_URL=amqp://localhost:5672/test.performance.queue?protocol=AMQP&limitDestinations=5
# If needed 
# INSPECTOR_NAME=ArtemisInspector
MAESTRO_BROKER=mqtt://localhost:1884

# If needed
# MANAGEMENT_INTERFACE=http://admin:admin@localhost:8161/console/jolokia
MESSAGE_SIZE=~200
PARALLEL_COUNT=5
RATE=0
TEST_DURATION=3m
```


Run Configurations for IntelliJ
----

Some tips and tricks for developing and debugging Maestro are available [here](development/runConfigurations). To use
those, you can copy all the XML files to your ```${project.dir}/.idea/runConfigurations``` directory


Remote Debugging 
----

To enable remote debugging, export the variable MAESTRO_DEBUG and set it to "y". The test for the variable is case 
sensitive. The debug port is set to 8000 for all components.

Version bump
----

Run the following to bump the versions:

```
mvn versions:set -DnewVersion=new_version
```

And then the following to accept the changes:

```
mvn versions:commit
```

Reports Server Pages
----

Make sure [bower](https://bower.io/) and [lessc](http://lesscss.org/) are installed. [NPM](https://www.npmjs.com/get-npm) is required for installing it.: 

```
npm install -g bower
```

The web resources are located in `maestro-reports/maestro-reports-server/src/main/resources/site/resources`. Web 
dependencies are defined in the bower.json file. 

```
cd maestro-reports/maestro-reports-server/src/main/resources/site/resources
bower install
```

Use the compile target on the Makefile to update the css style from the less file.

```
make compile
```

References: 
* [Patternfly Setup](https://www.patternfly.org/get-started/setup/)
* [How to use Patternfly](http://andresgalante.com/howto/2016/05/06/how-to-use-patternfly.html)

Maestro Libraries: Deploying in Self-Maintained Maven Repository
----

If you maintain your own Maven repository, you can deploy this library using:

```
mvn deploy -DaltDeploymentRepository=libs-snapshot::default::http://hostname:8081/path/to/libs-snapshot-local
```

Releasing
----

To release a new version and publish the jars to the public repositories:

```
mvn -DautoVersionSubmodules=true -PDelivery release:prepare && echo "Prepare complete" && mvn -PDelivery release:perform
```