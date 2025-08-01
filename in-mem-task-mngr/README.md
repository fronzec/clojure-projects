# In-Memory Task Manager

An in-memory task manager built with Clojure that uses ULID for unique task identification.

## Features

- Add tasks with description, status, priority, and due date
- List all tasks
- Uses ULID for unique task IDs
- In-memory storage using Clojure atoms

## Prerequisites

- [Leiningen](https://leiningen.org/) 2.0.0 or above
- Java 8 or above

## Usage

### Running the REPL

```bash
lein repl
```

### Running Tests

```bash
lein test
```

### Building an Uberjar

```bash
lein uberjar
```

## Development

The project uses Leiningen for dependency management and build automation.

### Project Structure

- `src/task_mngr/core.clj` - Main application code
- `test/task_mngr/core_test.clj` - Test suite
- `project.clj` - Leiningen project configuration

### Dependencies

- `clj-ulid/clj-ulid` - ULID generation for unique task IDs
- `org.clojure/test.check` - Property-based testing (dev dependency)

## Migration Note

Jul 2025 - This project was migrated from Clojure CLI tools (deps.edn) to Leiningen for improved build and dependency management.
