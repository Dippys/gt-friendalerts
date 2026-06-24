GT Friend Alerts Plugin
=======================

A small Arcturus Morningstar plugin that provides Bubble Alert notifications for Friend Login / Logoff.

**Requirements**
- **Java:** JDK 8 or newer
- **Build:** Maven 3.x

**Build**
- Build the project with: `mvn clean package`
- The compiled JAR will be created in `target/`.

**Install / Use**
- Copy the produced JAR from `target/` into your Plugins folder and restart the Emulator.

**Project Structure**
- **Main class:** [src/main/java/xyz/barrawi/gtfriendalerts/Main.java](src/main/java/xyz/barrawi/gtfriendalerts/Main.java)
- **Plugin descriptor:** [src/main/resources/plugin.json](src/main/resources/plugin.json)
- **Events:**
  - [src/main/java/xyz/barrawi/gtfriendalerts/events/FriendStatusEvent.java](src/main/java/xyz/barrawi/gtfriendalerts/events/FriendStatusEvent.java)
  - [src/main/java/xyz/barrawi/gtfriendalerts/events/EmulatorLoadEvent.java](src/main/java/xyz/barrawi/gtfriendalerts/events/EmulatorLoadEvent.java)

**Development**
- Use your IDE to open the project.
- Make sure you have already installed the Emulator via maven.
- Update the pom.xml to use your specific emulator version.
- Typical workflow: make changes → `mvn clean package` → copy JAR → restart Emulator.
