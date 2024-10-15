# ADR-06 - Prefix Naming Convention for Components & Accessors

## Decision and Justification
We have decided to adopt a systematic naming convention for all components and accessors within our Design System. The names will follow specific rules to make the system intuitive and easy to use, while also preventing naming collisions and ensuring compatibility with Android and Kotlin standards. This approach will ensure clarity, consistency, and scalability.

## Problem Description and Context
A standardized naming convention is essential for achieving clear identification and avoiding conflicts within the design system. By adopting a consistent naming strategy, we can ensure that all components are easily recognizable as part of the Design System, which aids in both development and maintenance.

## General Conditions and Decision Criteria

### General Conditions
- The naming convention should facilitate easy integration and usage, providing clear and intuitive names for all components and accessors.
- Names must avoid conflicts with existing framework or library names.
- The convention should support future scalability, accommodating the addition of new components without breaking established patterns.

### Decision Criteria
- **Unique Prefix:** Use a unique prefix to categorize components of our design system.
- **Short Names:** Prefer shorter names for brevity and ease of use.
- **Jetpack Compose Alignment:** Names should align with Jetpack Compose conventions to ensure familiarity and ease of integration.
- **Modular Flexibility:** The naming convention should support modularity, ensuring that additional components can be seamlessly integrated.
- **Collision Prevention:** Implement a prefix strategy to avoid potential naming collisions.

## Alternatives

### A - Company-Specific Prefix (e.g., `DB`)
#### Evaluation
- **Pros:** Clearly identifies components as part of the Deutsche Bahn Design System.
- **Cons:** Could deter other companies from using the Design System due to the company-specific prefix, leading to a sense of ownership by Deutsche Bahn.

### B - Neutral Prefix (e.g., `DS` for Design System)
#### Evaluation
- **Pros:** Neutral prefix that can be adopted by any organization, reducing the risk of conflicts with other company-specific prefixes.
- **Cons:** Less immediate brand recognition for Deutsche Bahn components, but increases the system's universality.

### C - No Prefix
#### Evaluation
- **Pros:** Simplifies names by focusing on the type indicator and eliminates potential prefix conflicts.
- **Cons:** May lead to naming collisions with existing Jetpack Compose or third-party components. Less clear association with the design system.

### D - Namespace (e.g., via Kotlin package structure)
#### Evaluation
- **Pros:** Provides a clear scope for all design system components, reducing the risk of naming collisions. Namespaces make it clear that components and accessors belong to the design system.
- **Cons:** Adds an extra layer of nesting, which can make usage more verbose and may affect readability.

## Decision
We will adopt **Option B - Neutral Prefix (e.g., `DS` for Design System)**. This approach ensures unique identification, also reinforces brand recognition, and provides consistency with our established design system. It balances the need to prevent naming collisions while supporting a modular and scalable design system.

## Consequences
- **Positive:** The naming convention will provide clarity and consistency, ensuring easy integration and maintenance. The use of an neutral prefix avoids naming collisions.
- **Negative:** Initial transition to the new naming convention may require updates to existing components and documentation. Less immediate brand recognition for Deutsche Bahn.

By implementing this naming convention, we ensure that our UX Design System remains clear, consistent, and scalable, providing an excellent developer experience and facilitating its adoption across various projects and organizations.

## Naming Convention Guidelines
1. **Prefix with `DS` (Design System):** All components and accessors will be prefixed with `DS`.
3. **Short and Concise:** Names should be as short as possible while still clearly conveying the component's purpose.
4. **Alignment with Jetpack Compose:** Follow Jetpack Compose naming conventions to enhance familiarity and ease of use.
5. **Modular and Extensible:** Ensure the structure supports the addition of new components without breaking the established pattern.

By adhering to this naming convention, we ensure that our components are easily identifiable and maintainable. This makes it easier for developers to navigate and utilize the design system, fostering a more consistent and scalable development environment.

## Example Code

### Design Tokens (foundation/DSColors.kt)
```kotlin
import androidx.compose.ui.graphics.Color

object DSColors {
    val primary = Color(0xFF0000FF)
    val secondary = Color(0xFF00FF00)
    // Additional colors...
}
```

### Components/DSTextField.kt
```kotlin
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun DSTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(placeholder, color = DSColors.secondary)
            }
            innerTextField()
        }
    )
}
```

### Modifiers/DSTextFieldModifier.kt
```kotlin
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

fun Modifier.dsTextFieldStyle(): Modifier = this
    .padding(8.dp)
    .background(DSColors.secondary)
    .clip(RectangleShape)
```
