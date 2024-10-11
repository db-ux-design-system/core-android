# Contributing to Android Jetpack Compose Library

Thank you for considering contributing to our Android Jetpack Compose Library project. Your contributions help make this project better for everyone.

## Table of Contents
1. [Code of Conduct](#code-of-conduct)
2. [How to Contribute](#how-to-contribute)
3. [Reporting Issues](#reporting-issues)
4. [Pull Requests](#pull-requests)
5. [Development Setup](#development-setup)
6. [Coding Standards](#coding-standards)
7. [Code Quality](#code-quality)
8. [Documentation](#documentation)
9. [Running Tests](#running-tests)
10. [Creating ADRs](#creating-adrs)

## Code of Conduct
This project adheres to a [Code of Conduct](./CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code. Please report any unacceptable behavior to [db-ux-designsystem@deutschebahn.com](mailto:db-ux-designsystem@deutschebahn.com).

## How to Contribute
### Reporting Issues
If you find a bug or have a feature request, please open an issue in the [issue tracker](https://github.com/your-repo/compose-library/issues). Be sure to include:
- A descriptive title.
- A detailed explanation of the issue or request.
- Steps to reproduce the issue (if applicable).
- Any relevant screenshots or logs.

### Pull Requests
We welcome your pull requests! To ensure a smooth process, please follow these steps:

1. **Fork the Repository:**
   - Create your own copy of the repository by clicking the `Fork` button on GitHub.

2. **Clone the Forked Repository:**
   - `git clone https://github.com/your-username/compose-library.git`

3. **Create a New Branch:**
   - `git checkout -b feature/your-feature-name`

4. **Make Your Changes:**
   - Develop your feature or fix on the new branch. Ensure that you follow the [coding standards](#coding-standards).

5. **Commit Your Changes:**
   - Use clear and concise commit messages.
   - `git commit -m "Add a concise and descriptive commit message"`

6. **Push Your Changes:**
   - `git push origin feature/your-feature-name`

7. **Submit a Pull Request:**
   - Go to the original repository and click `New pull request`.
   - Provide a detailed description of your changes.
   - Link to any relevant issues or ADRs if applicable.

## Development Setup
Follow these steps to set up your development environment:

1. **Clone the Repository:**
   - `git clone https://github.com/your-repo/compose-library.git`

2. **Navigate to the Project Directory:**
   - `cd compose-library`

3. **Open the Project in Android Studio:**
   - Open the project folder in Android Studio:
     - File > Open... and select the cloned repository folder.

4. **Sync Project with Gradle Files:**
   - Android Studio should automatically resolve and fetch dependencies when you open the project.

5. **Run Tests:**
   - Ensure all tests pass by selecting `Run > Run...` and choosing the test configuration.

## Coding Standards
Please adhere to the following coding standards to ensure consistency:

- Follow the Kotlin Coding Conventions.
- Follow Markdown and YAML style guides:
  - Markdown: Follow the [Markdown Guide](https://www.markdownguide.org/basic-syntax/).
  - YAML: Adhere to typical YAML syntax and style rules.

## Code Quality
To maintain high code quality, we use the following linters and tools in our CI/CD pipeline. Please ensure your code adheres to these standards before submitting a pull request:

1. **Android Lint:**
   - Android Lint checks the code for potential bugs, stylistic errors, and other issues. 
   - You can run Android Lint manually using the following command:
     ```sh
     ./gradlew lint
     ```

2. **Markdown Lint:**
   - Linting for Markdown can be done using `markdownlint-cli`:
     ```sh
     npm install -g markdownlint-cli
     markdownlint '**/*.md'
     ```

3. **YAML Lint:**
   - Linting for YAML files can be done using `yamllint`:
     ```sh
     brew install yamllint
     yamllint .
     ```

### CI/CD Integration
Our CI/CD pipeline will automatically run these linters on every pull request to ensure code quality. Here is a summary of the checks performed:

- **Android Lint:** Ensures Android code adheres to style and quality guidelines.
- **Markdown Lint:** Checks Markdown files for style and syntax correctness.
- **YAML Lint:** Checks YAML files for syntax errors and style issues.

Make sure your code passes these checks before opening a pull request.

## Documentation
Good documentation helps others understand your code. Please:

- Update the `DEVELOPMENT.md` file if your changes affect the development process.
- Ensure your code is well-commented, especially for complex logic.
- Update or create relevant ADRs in the `docs/adr` directory.

## Running Tests
To run the test suite:

1. **Open the Project in Android Studio:**
   - Open the project folder in Android Studio.

2. **Run Tests:**
   - Run the test suite by selecting `Run > Run Tests` or by using the command:
     ```sh
     ./gradlew testDebugUnitTest
     ./gradlew connectedAndroidTest
     ```

Ensure that all tests pass before submitting your pull request.

## Creating ADRs
Architecture Decision Records (ADRs) document significant architectural decisions made during the project. When you need to record an architectural decision, follow these steps:

1. **Create an ADR Document:**
   - ADRs should be created in the `docs/adr` directory.
   - Use the template provided in `docs/adr-xx-Template.md`.

2. **Document the Decision:**
   - Follow the structure in the template.

3. **Review and Approval:**
   - Submit the ADR for review by creating a pull request.
   - The ADR must be approved by the Maintainers/Codeowners before it is merged. This ensures that all architectural decisions are aligned with the project's overall strategy.

## Thank You!
Thank you for contributing to the Android Jetpack Compose Library project. Your efforts help improve the project for everyone. We appreciate your time and dedication!
