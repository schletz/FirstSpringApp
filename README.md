# Erste Spring Boot Applikation in VS Code

## Pakete in VS Code
- Java SE Development Kit von [oracle.com](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html) herunterladen und 
  in den Ordner *C:/jdk-11.0.3* (als Beispiel) installieren. Die Umgebungsvariable *JAVA_HOME* muss 
  auf den Ordner *C:/jdk-11.0.3* zeigen. Mit `echo %JAVA_HOME%` kann das in der Kommandozeile
  geprüft werden. Ist das nicht der Fall, kann in Windows unter *Systemumgebungsvariablen bearbeiten* im
  Startmenü diese Variable erstellt werden.
- In VS Code die Extension *Java Extension Pack* installieren.
- In VS Code die Extension *Lombok Annotations Support for VS Code* installieren.

## Anlegen des Projektes
Auf https://start.spring.io kann nun ein Maven Project mit Java und der Version 2.2.0 von Spring Boot
generiert werden. Als Gruppe wird *com.spengergasse*, als Articact *firstapp* eingegeben. In den 
Optionen können auch gleich Dependencies für Maven eingetragen werden. Für das Projekt werden 
folgende Abhängigkeiten eingefügt:
- Spring Web               (https://mvnrepository.com/artifact/org.springframework/spring-web)
- Spring Data JPA          (https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa)
- Thymeleaf                (https://mvnrepository.com/artifact/org.thymeleaf/thymeleaf)
- H2 Database              (https://mvnrepository.com/artifact/com.h2database/h2)
- Spring Boot Actuator     (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-actuator)
- Spring REST Docs         (https://mvnrepository.com/artifact/org.springframework.restdocs/spring-restdocs-mockmvc)
- Lombok

Später kann in VS Code mit *F1* und *Maven: Add a dependency* ein Paket installiert werden. Dabei werden
die Einträge zur Datei *pom.xml* hinzugefügt.

Nachdem das ZIP geladen wurde, wird es entpackt ohne einen Unterordner zu erstellen.

## Laden des Projektes
In VS Code kann dieser Ordner mit *Open Folder* nun geöffnet werden. Zu Beginn werden die 
Abhängigkeiten geladen, welches einige Zeit braucht. Falls die Meldung kommt, dass
VS Code neu gestartet werden muss, sollte dies nach dem Laden der Pakete geschehen.

Da noch keine *launch.json* Datei angelegt wurde, muss beim ersten Starten des Debuggers mit *F5* 
die Konfiguration (Java) ausgewählt werden. Beim Starten wird im Terminal (*View* - *Terminal*) die Meldung 
angezeigt, dass Spring geladen und die Applikation gestartet wurde.

## Anlegen der Verzeichnisse
In *src/main/java/com/spengergasse/firstapp* (nicht darüber in der Hierachie) werden folgende Verzeichnisse angelegt.
Wenn mit der rechten Maustaste auf firstapp und dann auf *Open in Terminal* geklickt wird, können diese
Verzeichnisse leicht mit `md (name)` angelegt werden.
- *domain*: Entityklassen, greift nicht auf andere Layer zu. Enthält zum Teil datenzentrische Businesslogik.
- *persistence*: Klassen für die Speicherung der Entities. Greift auf die Domain zu.
- *presentation*: Die View.
- *service*: Businesslogik für Datenübergreifende Logik. Orchestrierungslayer.
- *foundation*: Hilfsklassen (z. B. Extensions in C#, ...)

## Weitere Maven Kommandos in der Konsole
Alternativ kann auch mit dem Befahl `./mvnw spring-boot:run` im firstapp Verzeichnis  statt mit F5 
die Applikation gestartet werden. 
Mit `./mvnw clean package` im firstapp Verzeichnis die Pakete frisch aus dem Netz geladen werden. 
Mit `./mvnw dependency:go-offline` können die Pakete für den Offlinemodus geladen werden.
