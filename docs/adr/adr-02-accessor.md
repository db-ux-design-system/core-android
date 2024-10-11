# ADR-02 - Accessor Strategy for Component Styling

## Decision and justification
We need to decide on an accessor strategy for styling components, such as adaptive color or density, throughout our Jetpack Compose library and the app-project.

## Problem description and context
We require a flexible and consistent method to apply styling to our Compose components. The settings, adaptive color and density, must be adjustable and independently set. The structure for colors and themes should be easy to understand and maintain. Apply another density results in adaptive typography and dimensions, such as sizing, spacing and border.

## General conditions and decision criteria

### General conditions
- The styling approach must allow easy adjustment of color and density parameters.
- Each styling parameter should be set independently.
- The styling approach must cosider all adaptive tokens: color, typography and dimensions.
- Components should support adaptive identifier that use specific color schemes based on the current theme.
- The solution should balance performance, scalability, flexibility, and maintainability.

### Decision criteria
- **Performance:** Minimal performance overhead.
- **Scalability:** Capable of future extensions.
- **Maintainability:** Easy to maintain and understand.
- **Consistency:** Reliable and consistent application of styles.
- **Usability:** Simple for developers to use and integrate with existing Jetpack Compose components.

## Alternatives

### A - Hardcoded Styling in Components

#### Evaluation
- **Performance:** High, no additional processing required.
- **Scalability:** Poor, difficult to extend.
- **Maintainability:** Low, hard to manage and update styles.
- **Consistency:** Low, prone to discrepancies.
- **Usability:** Low, not flexible for developers.

### B - Centralized Style Accessors

#### Evaluation
- **Performance:** Moderate, slight overhead for accessing centralized styles.
- **Scalability:** High, easy to extend with new styles.
- **Maintainability:** High, centralized management of styles.
- **Consistency:** High, uniform application of styles.
- **Usability:** High, provides a flexible and consistent styling interface for developers.
- **Inheritance:** Moderate, needs explicit handling of inheritance for adaptive flags.

### C - Dynamic Styling via Composition Local

#### Evaluation
- **Performance:** Moderate, with minor overhead from Composition Local lookups.
- **Scalability:** High, easily extendable by adding more Composition Local providers.
- **Maintainability:** High, centralized management of styles with local overrides as needed.
- **Consistency:** High, ensures uniform styling with the flexibility of local overrides.
- **Usability:** High, provides a flexible and consistent interface for developers.
- **Inheritance:** High, naturally supports hierarchical inheritance and adaptive flags.

## Decision
We choose **Alternative C - Dynamic Styling via Composition Local**. This approach provides the best support for hierarchical inheritance and adaptive flags, ensuring consistent and flexible styling management. It leverages the Composition Local mechanism for efficient state management and propagation through the Compose tree.

## Consequences
- **Positive:** Utilizes Composition Local to provide dynamic and inheritable styling. It simplifies the process of applying complex styling configurations and allows for easy updates and extensions. Ensures adaptive flags are consistently applied through the hierarchy.
- **Negative:** There could be minor performance overhead due to Composition Local lookups, but this is offset by the flexibility and maintainability benefits.

## Links
- [Theme-Builder GitHub Repository](https://github.com/db-ui/theme-builder)
- [Jetpack Compose Theming Documentation](https://developer.android.com/jetpack/compose/themes)
- [Composition Local Documentation](https://developer.android.com/jetpack/compose/compositionlocal)

## Sample Code

1. **Define Composition Local Providers:**
   ```kotlin
   val LocalColors = staticCompositionLocalOf { getColorSchemeLight() }
   val LocalActiveColor = staticCompositionLocalOf { getColorSchemeLight().neutral }
   ```

2. **Make it accessible via theme accessor:**
   ```kotlin
   object DesignSystemTheme {
    val colors: DesignSystemColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val activeColor: AdaptiveColors
        @Composable
        @ReadOnlyComposable
        get() = LocalActiveColor.current

   // Same for other stylings, such as typography and dimensions
   }

   @Composable
   fun DesignSystemTheme(
      density: Density = Density.REGULAR,
      darkTheme: Boolean = isSystemInDarkTheme(),
      content: @Composable () -> Unit
   ) {
      // Use density to choose correct style for typography and dimensions
   
      // colors
      val colorScheme: DesignSystemColorScheme = when {
         darkTheme -> getColorSchemeDark()
         else -> getColorSchemeLight()
      }
      CompositionLocalProvider(
         LocalColors provides colorScheme,
         LocalActiveColor provides colorScheme.neutral,
         // Same for typography and dimensions
      ) {
         content()
      }
   }
   ```

3. **Apply Styles in Composable Functions:**
   ```kotlin
   @Composable
   fun StyledText(text: String) {
      Box(
        modifier = Modifier.padding(DesignSystemTheme.dimensions.spacing.fixedMd),
      ) {
         Text(
            text = text,
            color = DesignSystemTheme.activeColor.onBgBasicEmphasis100Default,
         )
      }
   }
   ```

4. **Provide Composable to switch between styles:**
   ```kotlin
   @Composable
   fun AdaptiveLayout(
      density: Density = Density.REGULAR,
      adaptiveColors: AdaptiveColors = DesignSystemTheme.colors.neutral,
      content: @Composable () -> Unit,
   ) {
      // Same logic for typography and density as shown in 2.

      CompositionLocalProvider(
         LocalActiveColor provides adaptiveColors,
         // Same for typography and dimensions
      ) {
         content()
      }
   }
   ```

5. **Provide Adaptive Values in the Composition:**
   ```kotlin
   @Composable
   fun CriticalExpressiveView() {
       AdaptiveLayout(
           density = Density.EXPRESSIVE,
           adaptiveColors = DesignSystemTheme.colors.critical,
       ) {
           StyledText("Hello world!")
       }
   }
   ```
