# Task Tracker CLI

A simple command-line task management application built with Java.

The application allows you to create, update, delete, and manage tasks directly from the command line. Tasks are persisted in a text file so that they remain available between program executions.

[Project URL](https://github.com/SirVishnu/task-tracker-cli)

## Clone the Repository

Clone the repository using Git:

```bash
git clone https://github.com/SirVishnu/task-tracker-cli.git
```

Then navigate into the project:

```bash
cd task-tracker-cli
```


## Features

* Add new tasks
* Update existing tasks
* Delete tasks
* Mark tasks as `todo`
* Mark tasks as `in-progress`
* Mark tasks as `done`
* List all tasks
* List tasks by status
* Store tasks persistently in a JSON file
* Automatically generate unique task IDs
* Track task creation and update timestamps

## Technologies

* Java
* Java Collections
* Java File I/O
* Command-line arguments

No external libraries or frameworks are required.

## Task Structure

Each task contains:

```json
{
  "id": 1,
  "description": "Buy groceries",
  "status": "todo",
  "createdAt": "2026-09-21T12:00:00",
  "updatedAt": "2026-09-21T12:00:00"
}
```

### Properties

| Property      | Description                                  |
| ------------- | -------------------------------------------- |
| `id`          | Unique identifier for the task               |
| `description` | Description of the task                      |
| `status`      | `todo`, `in-progress`, or `done`             |
| `createdAt`   | Date and time when the task was created      |
| `updatedAt`   | Date and time when the task was last updated |

## Usage

### Add a task

```bash
java App.java add "Buy groceries"
```

### Update a task

```bash
java App.java update 1 "Buy groceries and cook dinner"
```

### Delete a task

```bash
java App.java delete 1
```

### Mark a task as in progress

```bash
java App.java mark-in-progress 1
```

### Mark a task as done

```bash
java App.java mark-done 1
```

### List all tasks

```bash
java App.java list
```

### List completed tasks

```bash
java App.java list done
```

### List incomplete tasks

```bash
java App.java list todo
```

### List tasks in progress

```bash
java App.java list in-progress
```

## Requirements

* Java JDK 17 or later
* Command-line terminal

## Running the Project

Compile the source files:

```bash
javac -d bin src/*.java
```
