# Development Guide for Android Jetpack Compose Library

## Table of Contents

1. [Introduction](#introduction)
2. [Project Overview](#project-overview)
3. [Architecture Overview](#architecture-overview)
4. [ADR Reference](#adr-reference)
5. [Implementation Guidelines](#implementation-guidelines)
6. [Code Quality](#code-quality)
7. [Contribution Guidelines](#contribution-guidelines)
8. [Getting Started](#getting-started)

## Introduction

Welcome to the development guide for the Android Jetpack Compose Library of DB
UX Design System. This document outlines the development concepts and practices
for this framework, helping new developers get up to speed and contribute
efficiently.

## Project Overview

This SwiftUI-based UX Design System Framework aims to:

* Define design elements such as colors, typography, and spacing as tokens.
* Provide pre-built UI components like buttons, alerts, and chips.

The framework is designed to be user-friendly, leveraging Jetpack Compose for a
modern and maintainable codebase. The project is offered as a Compose Library
for easy integration.

## Architecture Overview

The architecture of this library follows the principles of modularity and
separation of concerns. The key components include:

* **Foundation:** Central definition of design elements including colors,
  typography and spacing.
* **Components:** Essential UI components built with Jetpack Compose, such as
  buttons, alerts and chips.

## ADR Reference

Significant architectural decisions are documented in Architecture Decision
Records (ADRs). These records ensure transparency and consistency throughout the
project. Developers are encouraged to document new decisions as ADRs to maintain
project integrity.

* All ADRs are located in the `docs/adr` directory of the repository.
* [Refer to ADR Documentation](./docs/adr)

## Implementation Guidelines

To ensure high standards of code quality and consistency, adhere to the
following guidelines:

1. **Code Structure:**
    * Organize files by feature or module.
    * Follow the Android API Design Guidelines and Kotlin coding conventions.
2. **Components:**
    * Build UI components using Jetpack Compose.
    * Ensure components are reusable and customizable via modifiers.
3. **Design Tokens Usage:**
    * Consistently apply design tokens (such as colors, typography, and spacing)
      across all components.
4. **Documentation:**
    * Document all public APIs thoroughly.
    * Include inline comments for complex logic.

## Code Quality

Maintaining high code quality is crucial. Follow these best practices:

1. **Code Reviews:** All code changes must undergo a rigorous review process.
2. **Unit Tests:** Write unit tests for all critical functionalities.
3. **Linting:** Use Android Lint to enforce a consistent code style.
4. **CI Pipeline:** Implement automated testing and linting for every pull
   request.

## Contribution Guidelines

We welcome contributions from the community! For details on how to contribute to
this project, refer to the `CONTRIBUTING.md` file. It includes information on
how to report issues, submit pull requests, and follow coding standards.

## Getting Started

1. **Prerequisites:**
    * Java Development Kit (JDK) 17 or newer
    * Android Studio (latest stable version)

2. **Clone the Repository:**

   ```sh
   git clone https://github.com/your-repo/compose-library.git
   ```

3. **Open the Project in Android Studio:**
    * Open Android Studio.
    * Select `File > Open...`.
    * Navigate to the cloned repository and click `Open`.

4. **Sync Project with Gradle Files:**
    * Android Studio should automatically sync and resolve dependencies. If not,
      go to `File > Sync Project with Gradle Files`.

5. **Build the Project:**

   ```sh
   ./gradlew assembleDebug
   ```

6. **Run Tests:**
    * Unit Tests:

      ```sh
      ./gradlew testDebugUnitTest
      ```

    * UI Tests:

      ```sh
      ./gradlew connectedAndroidTest
      ```

Thank you for contributing to our project! Together, we can develop a robust and
versatile DB UX Design System using Jetpack Compose.
