# Task Manager CLI Application

![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)
![Build](https://img.shields.io/badge/Build-Maven-yellow.svg)

A simple command-line task manager with JSON storage, built with Java and Jackson.

## Features

- ✅ Add, remove, and list tasks
- ✅ Mark tasks as complete/incomplete
- ✅ Automatic JSON file persistence
- ✅ Clean terminal interface
- 📅 Date tracking for tasks

## Quick Start

### Option 1: Run from Source
```bash
git clone https://github.com/AbdullahMakhos/Task-Manager-CLI-Application.git
cd Task-Manager-CLI-Application
mvn exec:java -Dexec.mainClass="Main"
```

### Option 2: Build & Run JAR
```bash
mvn clean package
java -jar target/CLITASKMANAGER-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
```

## Commands

| Command        | Description                          | Example                     |
|----------------|--------------------------------------|-----------------------------|
| `show`         | List all tasks                       | `show`                      |
| `add <task>`   | Add new task                         | `add Buy milk`              |
| `remove <#>`   | Remove by number                     | `remove 1`                  |
| `done <#>`     | Mark task complete                   | `done 2`                    |
| `undone <#>`   | Mark task incomplete                 | `undone 2`                  |
| `clear`        | Delete all tasks                     | `clear`                     |
| `exit`         | Quit application                     | `exit`                      |

## Data Storage
Tasks save automatically to `tasks.json`:
```json
{
  "tasks": [
    {
      "description": "Finish project",
      "done": false,
      "date": "2023-11-20"
    }
  ]
}
```

## Development

### Build System
```bash
mvn clean package       # Build JAR
mvn test                # Run tests
```

### Dependencies
- Jackson Core/Databind (v2.15+)
- Java 17+

## Sample Session
```text
> add Buy eggs
✓ Added: Buy eggs
> add Walk dog
✓ Added: Walk dog
> show
1. [ ] Buy eggs (2023-11-20)
2. [ ] Walk dog (2023-11-20)
> done 1
✓ Marked complete: Buy eggs
```




