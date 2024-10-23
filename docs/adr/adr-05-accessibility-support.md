# ADR-05 - Utilize Native Accessibility APIs in the Design System

## Decision and Justification

We have decided to integrate native accessibility features and settings into our
Design System components by leveraging Android's native accessibility APIs. This
approach will ensure our colors, dimension, and typography comply with
accessibility standards and offer an optimal experience for users with
disabilities.

## Problem Description and Context

The Design System must support various accessibility features to comply with
accessibility standards and ensure an optimal experience for all users,
including those with disabilities. This involves leveraging native APIs for
features such as TalkBack, font size adjustments, high contrast text, and more.

## General Conditions and Decision Criteria

### General Conditions

* **Target User Groups:** All users, with a focus on those with visual,
  auditory, motor, and cognitive disabilities.
* **Platforms Supported:** Primarily Android.
* **Native Accessibility Features to be Supported:**
    * TalkBack
    * Font size adjustments (Text Scaling)
    * High contrast text
    * Reduce motion
    * Color inversion
    * Mono audio
* **Affected Components:** Colors, dimension, and typography from the Foundation
  Package.
* **Standards and Guidelines:** Compliant with WCAG AA standards.
* **Testing and Validation:** Automated testing in the CI pipeline.
* **Documentation:** High-level documentation indicating that accessibility
  features are implemented.
* **User Customizations:** Support for user customizations and settings for
  accessibility.

### Decision Criteria

* **Compliance:** The Design System must comply with WCAG AA standards.
* **Ease of Integration:** Attributes related to accessibility should be easily
  integrated into the Design System.
* **Maintainability:** The solution should be easy to maintain and update.
* **Compatibility:** It should support the features and settings on Android.
* **Scalability:** The solution should support different screen sizes,
  resolutions, and themes.
* **Testing:** Automated testing for accessibility features.

## Alternatives

### A - Framework-Specific Accessibility Implementation

#### Evaluation

* **Pros:** Tailored solutions for specific frameworks, possibly offering
  enhanced feature sets.
* **Cons:** Increased complexity and maintenance overhead due to
  framework-specific implementations.

### B - Utilize Native Accessibility APIs

#### Evaluation

* **Pros:** Ensures compliance with accessibility standards, provides a better
  user experience, and leverages existing platform capabilities. Simplifies
  maintenance by using native APIs that are consistently updated by the
  platform.
* **Cons:** Requires additional development effort and testing to integrate and
  validate accessibility features.

## Decision

We choose **Alternative B - Utilize Native Accessibility APIs**. This approach
ensuring compliance with accessibility standards, provides a better user
experience, and leverages existing platform capabilities.

## Consequences

* **Positive:** Utilizing native accessibility APIs will provide a better user
  experience for all users, ensure compliance with standards, and make it easier
  to maintain and extend accessibility support.
* **Negative:** There may be an initial increase in development and testing
  effort.

## Links

* [Android Accessibility Overview](https://developer.android.com/guide/topics/ui/accessibility)
* [WCAG 2.1 Guidelines](https://www.w3.org/WAI/standards-guidelines/wcag/)
* [Accessibility Scanner](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor)

## Sample Code

### TalkBack Example

Ensure all interactive elements have appropriate content descriptions, hints,
and accessibility roles.

```kotlin
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription

@Composable
fun AccessibleButton(onClick: () -> Unit) {
    Text(
        text = "Submit",
        modifier = Modifier
            .clickable(onClick = onClick)
            .semantics {
                contentDescription = "Submit button"
                stateDescription = "Double tap to submit the form"
            }
    )
}
```
