# ADR-01 - Integration of Color Tokens from Theme Builder

## Decision and justification

We will integrate color tokens exported by the [theme-builder](https://github.com/db-ui/theme-builder) into our Compose library. These tokens will be defined as `Color` instances prefixed with the exported theme name and used in different color schemes through adaptive themes.

## Problem description and context

We want to ensure that color definitions from the theme-builder are efficiently integrated into our Compose library. The color tokens should be adaptable to different color modes (e.g., light and dark mode). The structure for colors and themes should be easy to understand and maintain.

## General conditions and decision criteria

### General conditions

* The color tokens must be easily imported from the theme-builder export.
* The naming and mapping of tokens to color schemes should be consistent and clear.
* The structure must be flexible enough to accommodate future changes or expansions.

### Decision criteria

* **Clarity**: The integration of color tokens should be clear and understandable.
* **Consistency**: The naming and structure of color tokens and themes should be uniform.
* **Flexibility**: The structure should be easy to extend.
* **Performance**: The implementation should be performant and should not consume unnecessary resources.

## Alternatives

### A - Direct definition of color tokens in code

#### Evaluation

* **Pros:** Simple implementation without additional tools or dependencies.
* **Cons:** Less flexible for changes and not automatically synchronized with the `theme-builder`.

### B - Using the export from the `theme-builder`

#### Evaluation

* **Pros:** Direct adoption of color tokens from the `theme-builder`, simple maintenance and synchronization.
* **Cons:** Dependency on the `theme-builder` and its export structure.

## Decision

We choose **Alternative B - Using the export from the `theme-builder`** to directly adopt the color tokens. This ensures simple maintenance and synchronization of color definitions.

## Consequences

* **Positive:** Using the `theme-builder` export allows for consistent and easy updates of color tokens. The structure enables simple customization and expansion of themes.
* **Negative:** There is a dependency on the `theme-builder` and its export functions.

By implementing this approach, we ensure that our color tokens are clear, consistent and scalable, providing a solid foundation for the color schemes in our design system and facilitating seamless updates.

## Links

* [Theme-Builder GitHub Repository](https://github.com/db-ui/theme-builder)
* [Jetpack Compose Theming Documentation](https://developer.android.com/jetpack/compose/themes)

## Sample Code

1. **Import Color Tokens:**

   ```kotlin
   val DeutscheBahnColorMap = mapOf(
       "neutral0" to Color(0xff070709),
       "neutral1" to Color(0xff0d0e11),
       "neutral2" to Color(0xff121316),
       "neutral3" to Color(0xff1a1c1f),
       "neutral4" to Color(0xff2e3036),
       "neutral5" to Color(0xff43474e),
       // ... Add more color tokens as needed
   )
   ```

2. **Define ColorScheme Using Color Tokens:**

   ```kotlin
   class DSColorVariant private constructor(
       val bgBasicLevel1Default: Color,
       val bgBasicLevel1Hovered: Color,
       val bgBasicLevel1Pressed: Color,
       val bgBasicLevel2Default: Color,
       val bgBasicLevel2Hovered: Color,
       val bgBasicLevel2Pressed: Color,
       val bgBasicLevel3Default: Color,
           // ...
   ) {
    companion object {
       fun dark(colorName: String) = DSColorVariant(
           DBColorMap.getValue(colorName + "3"),
           DBColorMap.getValue(colorName + "4"),
           DBColorMap.getValue(colorName + "5"),
           DBColorMap.getValue(colorName + "2"),
           DBColorMap.getValue(colorName + "3"),
           DBColorMap.getValue(colorName + "4"),
           DBColorMap.getValue(colorName + "1"),
           // ...
       )
       fun light(colorName: String) = DSColorVariant(
           DBColorMap.getValue(colorName + "14"),
           DBColorMap.getValue(colorName + "13"),
           DBColorMap.getValue(colorName + "12"),
           DBColorMap.getValue(colorName + "13"),
           DBColorMap.getValue(colorName + "12"),
           DBColorMap.getValue(colorName + "11"),
           DBColorMap.getValue(colorName + "12"),
           // ...
       )
    }
   }
   ```

3. **Instantiate ColorScheme:**

   ```kotlin
   val NeutralColorsDark = DSColorVariant.dark("neutral")
   val BrandColorsDark = DSColorVariant.dark("brand")
   val InformationalColorsDark = DSColorVariant.dark("informational")
   ```
