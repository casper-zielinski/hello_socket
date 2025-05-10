# Hello Socket Project with Sockets and GUI with Java FX

## New Elements in Pom.xml

- to execute more than one java class, you can declare an id to the class you want to run

```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <version>3.1.0</version>
  <executions>
    <execution>
      <id>run-server</id>
      <goals>
        <goal>java</goal>
      </goals>
      <configuration>
        <mainClass>at.fhj.msd.Server.App</mainClass>
      </configuration>
    </execution>
    <execution>
      <id>run-client</id>
      <goals>
        <goal>java</goal>
      </goals>
      <configuration>
        <mainClass>at.fhj.msd.Client.App</mainClass>
      </configuration>
    </execution>
  </executions>
</plugin>
```

with this you can call the ``mvn exec:java`` commmand with an id, you do it by calling the command and after the java word, you put a ``@`` and the id you put. I put for the  id: ``<id>run-server</id>`` the main class: ``<mainClass>at.fhj.msd.Server.App</mainClass>``, so he executes this Class when the command  
``mvn exec:java@run-server`` is called.
To call the Client App.java you need to call the command:
``mvn exec:java@run-client``

This Project works by first calling the Server, the Server then waits for a response from the Client, the Client prints the Messege he got from the Server and the Server print how many times the Client has connected to him. The Server works endlessly until it's stopped (by the Ctrl+C Combination).

There are also two JavaFX Stages in it. each one called by a different commenad.

To implement Java FX:

- settings.json`

```json
{
      "java.project.referencedLibraries": [
        "lib/**/*.jar"
      ],
      "java.configuration.updateBuildConfiguration": "interactive"
}
```

- launch.json

```json
{
      "version": "0.2.0",
      "configurations": [
      {
            "type":"java",
            "name":"MainServer",
            "request":"launch",
            "mainClass":"at.fhj.msd.Server.MainServer",
            "projectName":"MSD-Server"
      },
      {
            "type":"java",
            "name":"Java FX Server",
            "request":"launch",
            "mainClass": "Main",
            "vmArgs": "--module-path lib --add-modules javafx.controls"
  
      }
      ]
}
```

- pom.xml

  - properties 

      ```xml
      <!-- JavaFX-Konfiguration: Version und Plattform -->
      <javafx.version>21</javafx.version>
      <javafx.platform>win</javafx.platform>
      ```

   - dependency

      ```xml
       <!-- JavaFX Dependencies mit platform-spezifischem classifier-->
      <dependency>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
            <classifier>${javafx.platform}</classifier>
      </dependency>
      <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
            <classifier>${javafx.platform}</classifier>
      </dependency>
      ```
  - plugin

  ```xml
      plugin>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-maven-plugin</artifactId>
        <version>0.0.8</version>
        <configuration>
          <mainClass>at.fhj.msd.Server.MainServer</mainClass> <!-- TO change -->
        </configuration>
        <executions>
          <execution>
            <id>run-server</id> <!-- Id to call it by -->
            <goals>
              <goal>run</goal>
            </goals>
            <configuration>
              <mainClass>at.fhj.msd.Server.MainServer</mainClass> <!-- change it-->
            </configuration>
          </execution>
          <execution>
            <id>run-client</id> <!-- Id to call it by -->
            <goals>
              <goal>run</goal>
            </goals>
            <configuration>
              <mainClass>at.fhj.msd.Client.MainClient</mainClass> <!-- change it -->
            </configuration>
            </execution>
        </executions>
      </plugin>
  ``` 

The Blocks commented are to be changed when using it in other classes, also you can change the id to make it more sense.
When you want to call the Server GUI for example, you can call the command:  
``mvn javafx:run@run-server``
