To get all the libraries for the EasyBPEL validation, process as following:
* Create a directory and add a pom.xml file in it.
* Copy the POM content given below.
* Open a shell in this directory and execute "maven clean install".
* Copy all the libraries located under the "target/dependencies" directory.


<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<name>test</name>
	<artifactId>test</artifactId>
	<groupId>com.ebmwebsourcing.easybpel</groupId>
	<version>1-SNAPSHOT</version>
	<packaging>jar</packaging>


	<dependencies>
		<dependency>
			<artifactId>easybpel.model.bpel.tools.validator</artifactId>
			<groupId>com.ebmwebsourcing.easybpel</groupId>
			<version>1.3</version>
		</dependency>
	</dependencies>


	<repositories>
		<repository>
			<id>ebmws-public.release</id>
			<url>http://maven.petalslink.com/public</url>
			<releases>
				<enabled>true</enabled>
			</releases>
		</repository>
	</repositories>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-dependency-plugin</artifactId>
				<version>2.4</version>
				<executions>
					<execution>
						<id>copy-dependencies</id>
						<phase>package</phase>
						<goals>
							<goal>copy-dependencies</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

</project>
