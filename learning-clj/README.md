# 🚀 Clojure Project with Leiningen and Task

This project uses **Leiningen** as the build tool for Clojure and **Task** as a command automation tool.

## 📋 Table of Contents

- [Installation](#-installation)
- [Quick Start](#-quick-start)
- [Task Commands](#-task-commands)
- [Leiningen Commands Explained](#-leiningen-commands-explained)
- [Project Structure](#-project-structure)
- [Practical Examples](#-practical-examples)
- [Troubleshooting](#-troubleshooting)

## 🛠 Installation

### Prerequisites

1. **Java** (JDK 8 or higher)
2. **Leiningen** - Build tool for Clojure
3. **Task** - Task automation tool

### Install Task (required to use this project)

```bash
# macOS
brew install go-task/tap/go-task

# Linux
curl -sL https://taskfile.dev/install.sh | sh

# Windows
scoop install task
```

## ⚡ Quick Start

```bash
# See all available commands
task

# Execute the program
task run

# Set up the project for the first time
task setup
```

## 🎯 Task Commands

### Basic Commands

| Command | Description | When to use |
|---------|-------------|-------------|
| `task` | Shows help and command list | To see what you can do |
| `task run` | Executes a specific algorithm | To run algorithms with custom namespace |
| `task run-core` | Shows available algorithms | To see what algorithms are available |
| `task run-difference` | Runs difference of squares | To test the difference algorithm |
| `task setup` | Sets up the project initially | First time you download the project |
| `task info` | Shows project information | To verify configuration |

### Development Commands

| Command | Description | When to use |
|---------|-------------|-------------|
| `task repl` | Opens interactive console | To experiment with code |
| `task dev` | Development mode (watches changes) | While developing |
| `task check` | Verifies syntax without executing | To find errors |
| `task test` | Runs tests | To verify everything works |

### Build Commands

| Command | Description | When to use |
|---------|-------------|-------------|
| `task compile` | Compiles code to bytecode | To verify compilation |
| `task build` | Complete build process | Before distributing |
| `task uberjar` | Creates executable file | To distribute application |
| `task clean` | Cleans temporary files | When having compilation issues |

### Advanced Commands

| Command | Description | When to use |
|---------|-------------|-------------|
| `task deps` | Downloads dependencies | When adding new libraries |
| `task install` | Installs to local repository | To use in other projects |
| `task new` | Creates new Clojure project | To start fresh projects |

## 📚 Leiningen Commands Explained

### For Absolute Beginners

#### `lein run` 
**What does it do?** Executes your Clojure program.
```bash
task run  # Equivalent to: lein run
```
**Explanation:** Looks for the `-main` function in your code and executes it. Like double-clicking on a program.

#### `lein repl`
**What does it do?** Opens an interactive console where you can write Clojure code.
```bash
task repl  # Equivalent to: lein repl
```
**Explanation:** REPL means "Read-Eval-Print Loop". You can write code line by line and see results immediately.

**Example usage in REPL:**
```clojure
user=> (+ 2 3)
5
user=> (defn greet [name] (str "Hello " name))
#'user/greet
user=> (greet "Maria")
"Hello Maria"
```

#### `lein check`
**What does it do?** Checks if your code has syntax errors without executing it.
```bash
task check  # Equivalent to: lein check
```
**Explanation:** Like a spell checker for code. It tells you if you wrote something wrong.

#### `lein compile`
**What does it do?** Converts your Clojure code to code that the Java machine can understand.
```bash
task compile  # Equivalent to: lein compile
```
**Explanation:** Like translating a book to another language. Your code gets translated to "machine language".

#### `lein test`
**What does it do?** Runs automatic tests of your code.
```bash
task test  # Equivalent to: lein test
```
**Explanation:** Like giving your code an exam to verify it works correctly.

#### `lein uberjar`
**What does it do?** Creates a .jar file that you can run without having the source code.
```bash
task uberjar  # Equivalent to: lein uberjar
```
**Explanation:** Like creating a .exe file in Windows. It's your program packaged for distribution.

#### `lein clean`
**What does it do?** Deletes temporary and compiled files.
```bash
task clean  # Equivalent to: lein clean
```
**Explanation:** Like emptying the recycle bin. Useful when something doesn't work well.

#### `lein deps`
**What does it do?** Downloads external libraries that your project needs.
```bash
task deps  # Equivalent to: lein deps
```
**Explanation:** Like downloading apps on your phone. Gets code from other programmers that you need.

## 🏗 Project Structure

```
learning-clj/
├── project.clj                              # 📋 Project configuration
├── Taskfile.yml                            # 🤖 Command automation
├── README.md                               # 📖 This file
├── src/                                    # 📁 Source code
│   └── learning_clojure/                   # 📦 Main namespace
│       ├── core.clj                        # 🎯 Main entry point
│       └── difference_of_squares.clj       # 🧮 Algorithm implementation
├── test/                                   # 🧪 Test files
│   └── learning_clojure/
│       └── difference_of_squares_test.clj  # ✅ Algorithm tests
├── resources/                              # 📄 Configuration files, data
└── target/                                 # 📁 Compiled files (auto-generated)
```

### What does each directory/file do?

- **project.clj**: Defines project name, version, dependencies, etc.
- **src/learning_clojure/**: Contains all your algorithm implementations
- **test/learning_clojure/**: Contains tests for your algorithms
- **Taskfile.yml**: Automates long commands with short names
- **resources/**: For configuration files, data files, etc.
- **target/**: Folder where compiled files are saved (you can ignore it)

## 💡 Practical Examples

### Typical Workflow

1. **First time with the project:**
   ```bash
   task setup
   ```

2. **Developing new functionality:**
   ```bash
   task dev  # Runs automatically when you change code
   ```

3. **Testing your code:**
   ```bash
   task run
   ```

4. **Experimenting in console:**
   ```bash
   task repl
   ```

5. **Before sharing your code:**
   ```bash
   task build
   ```

### Modifying the Code

To change what your program does, edit `test.clj`:

```clojure
(defn -main []
  (println "Hello world!")
  (println "The result is:" (difference 50)))
```

Then run:
```bash
task run
```

## 🔧 Troubleshooting

### Common Problems

#### "Task not found"
**Problem:** You don't have Task installed.
**Solution:** 
```bash
brew install go-task/tap/go-task  # macOS
```

#### "lein: command not found"
**Problem:** You don't have Leiningen installed.
**Solution:**
```bash
brew install leiningen  # macOS
```

#### "Could not locate ... on classpath"
**Problem:** File not found.
**Solution:**
```bash
task clean
task deps
```

#### Permission errors
**Problem:** You don't have permissions to create files.
**Solution:**
```bash
sudo chmod -R 755 .
```

### Emergency Commands

```bash
# If nothing works, run these in order:
task clean
task deps
task setup
task run
```

## 🚀 Next Steps

1. **Learn more Clojure:** [ClojureDocs](https://clojuredocs.org/)
2. **Explore Leiningen:** [Official Documentation](https://leiningen.org/)
3. **Improve your workflow:** [Task Documentation](https://taskfile.dev/)

## 📝 Notes

- Your program currently calculates the difference between the square of the sum and the sum of squares for the first 100 numbers
- Current result is: **25,164,150**
- You can change the number by editing `test.clj` in the line `(difference 100)`

Happy programming! 🎉 