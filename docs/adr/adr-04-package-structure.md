# ADR-04 - Modular Package Structure for UX Design System in Jetpack Compose

## Decision and Justification

To maintain a clean, modular, and reusable codebase for our UX Design System, we
have decided to organize design tokens (such as colors, typography, dimension)
and complete components (such as buttons and modal dialogs) into separate
modules within the Kotlin framework. This separation will facilitate easier
integration for developers who may only need specific parts of the design
system.

## Problem Description and Context

Our goal is to implement a ready-made UX Design System specifically for Jetpack
Compose. This design system will include design tokens (such as colors,
typography, and dimension) and complete UI components (such as buttons and modal
dialogs). By modularizing the package, we aim to provide developers with the
flexibility to use only the parts they need without importing unnecessary code.
This will ensure a more efficient, maintainable, and scalable implementation.

Additionally, this modular structure will make it easier to develop and
integrate additional design components independently. Developers will be able to
create new components and simply import the existing design tokens, ensuring
consistency across all components while maintaining the flexibility to expand
the design system as needed.

## General Conditions and Decision Criteria

### General Conditions

* The package must be easy to integrate with existing projects.
* Both design tokens and components should be easily accessible and
  independently maintainable.
* The package should remain lean, only including necessary dependencies for the
  specific parts being used.

### Decision Criteria

* Modularity: The package structure should allow developers to include only the
  parts of the system they need.
* Maintainability: Design tokens and components should be maintained separately
  to promote cleaner code organization.
* Efficiency: The system should minimize unnecessary code inclusion and
  dependencies.

## Alternatives

### A - Monolithic Package Structure

#### Evaluation

* **Pros:** Simplified structure with all elements in one place.
* **Cons:** Leads to unnecessary code inclusion, harder maintenance, and more
  challenging project integration.

### B - Modular Package Structure

#### Evaluation

* **Pros:** Encourages clean code separation, easier maintenance, and efficient
  project integration.
* **Cons:** Initially requires more setup and thoughtful organization.

## Decision

We choose **Alternative B - Modular Package Structure**. This approach ensuring
ease of integration, maintenance, and scalability.

## Consequences

* **Positive:** A modular package will provide flexibility and clarity, making
  it easier for developers to integrate and maintain. It will ensure minimal
  code bloat and facilitate independent updates.
* **Negative:** The initial implementation may require more effort and a
  well-thought-out structure.

## Links

* [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose/documentation)
* [Common modularization patterns](hhttps://developer.android.com/topic/modularization/patterns)

### Example Code

#### Package Structure

```lang-none
MyProject/
├── foundation/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── designsystem/
│   │   │   │           └── foundation/
│   │   │   │               ├── Colors.kt
│   │   │   │               ├── Typography.kt
│   │   │   │               ├── Spacing.kt
│   │   └── ...
│   ├── build.gradle
├── components/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── designsystem/
│   │   │   │           └── components/
│   │   │   │               ├── TextField.kt
│   │   │   │               ├── ModalDialog.kt
│   │   └── ...
│   ├── build.gradle
├── build.gradle
└── settings.gradle
```

#### Foundation (foundation/Colors.kt)

```kotlin
import androidx.compose.ui.graphics.Color

object AppColors {
    val primary = Color(0xFF0000FF)
    val secondary = Color(0xFF00FF00)
    // Additional colors...
}
```

#### Component (components/TextField.kt)

```kotlin
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = AppColors.secondary
                )
            }
            innerTextField()
        }
    )
}
```

#### build.gradle Configuration

```gradle
plugins {
    id 'com.android.library'
    id 'kotlin-android'
}

android {
    compileSdk 33

    defaultConfig {
        minSdk 21
        targetSdk 33
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {
    implementation "androidx.compose.ui:ui:1.0.0"
    implementation "androidx.compose.material:material:1.0.0"
    implementation "androidx.compose.ui:ui-tooling-preview:1.0.0"
    // More dependencies...
}
```
