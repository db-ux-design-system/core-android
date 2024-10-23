# ADR-03 - Integration of Assets (Icons, Fonts) in the Package

## Decision and justification
We need to decide how to integrate assets, specifically icons and fonts, into the package. The assets include functional and illustrative icons (in SVG format) and fonts (in TTF format).

## Problem description and context
The package must include assets from internal sources, organized in a way that allows for easy usage, maintainability, and cross-platform support. The assets consist of functional icons that can be colored, illustrative icons that are multi-colored and cannot be re-colored, and fonts. The icons are organized by functional grouping. The icons also have to support light and dark mode.

## General conditions and decision criteria

### General conditions
- **Asset Types:** Functional icons (colorable SVG), illustrative icons (multi-colored SVG), and fonts (TTF).
- **Source:** Internal sources, to be added to the package.
- **Formats:** SVG for icons, TTF for fonts.
- **Organization:** Icons are organized by functional grouping.
- **Usage:** Assets need to be easily accessible and usable within the app.
- **Performance:** No specific caching or performance requirements.
- **Build Process:** Assets should be included in the repository and integrated during the build process.
- **Platform Support:** While Jetpack Compose is primarily for Android, extensions or compatibility wrappers should be considered for potential cross-platform requirements.
- **Licensing:** Compliance with any relevant licensing requirements.
- **Scalability:** Support for different screen sizes, resolutions, and themes (e.g., Dark Mode).
- **Future Maintenance:** Assets should be easy to update and maintain.

### Decision criteria
- **Ease of Integration:** Assets should be easily integrated into the package and accessible to developers.
- **Maintainability:** The solution should be easy to maintain and update.
- **Compatibility:** It should support all target platforms.
- **Organization:** The assets should be well-organized and easy to find.
- **Usage:** Assets should be straightforward to use in the app.
- **Scalability:** The solution should support different screen sizes, resolutions, and themes.

## Alternatives

### A - Organize Assets in Resource Directories

#### Evaluation
- **Ease of Integration:** High, standard practice for Android development, provided that SVG icons are converted to VectorDrawables beforehand.
- **Maintainability:** High, assets are easily updatable in one place.
- **Compatibility:** High, supports all target platforms.
- **Organization:** High, clear separation of assets by type (icons, fonts) and functionality.
- **Usage:** High, straightforward to use assets in Compose.
- **Scalability:** High, supports different screen sizes, resolutions, and themes seamlessly.

### B - External Asset Management System

#### Evaluation
- **Ease of Integration:** Low to Moderate, requires setting up and interfacing with an external system.
- **Maintainability:** High, external systems can manage asset versions and updates.
- **Compatibility:** High, can support various platforms.
- **Organization:** Moderate to High, dependent on the external system's capabilities.
- **Usage:** Moderate, may require additional steps to integrate assets into the app.
- **Scalability:** High, well-managed external systems can handle scaling efficiently.

## Decision
We choose **Alternative A - Organize Assets in Resource Directories**. This approach aligns with standard Android development practices, ensuring ease of integration, maintenance, and scalability. It allows for clear organization and accessibility of assets, provided that SVG icons are converted to VectorDrawables beforehand.

## Consequences
- **Positive:** Organizing assets in resource directories ensures they are easily manageable, well-organized, and consistently updated. This approach supports scalability and is in alignment with Android best practices.
- **Negative:** Initial setup might require some effort to properly organize all assets and convert SVG icons to VectorDrawables, but this setup promotes long-term manageability and usability.

## Links
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Android Asset Management Documentation](https://developer.android.com/studio/write/resource-manager)

## Implementation Plan

1. **Add Icons and Fonts to the Project:**
   - Create a `res/drawable` directory for SVG icons.
   - Create a `res/font` directory for TTF fonts.
   - Organize icons by functional grouping within the `drawable` directory.
   - Organize icons by light/dark mode grouping within the `drawable` and `drawable-night` directory.

2. **Manual Conversion of SVG Files:**
   - Use Android Studio to convert SVG files to VectorDrawables.
   - Save the converted files in the `res/drawable`/`res/drawable-night` directory.

3. **Optimize SVG and TTF Files:**
   - Ensure all SVG icons are optimized for performance and meet the required standards.
   - Ensure all TTF fonts include necessary style variations (e.g., bold, italic).

4. **Load SVG Icons and TTF Fonts in Compose:**
   ```kotlin
   @Composable
   fun VectorIconLoader(@DrawableRes iconRes: Int, contentDescription: String?) {
       val icon = painterResource(id = iconRes)
       Icon(painter = icon, contentDescription = contentDescription)
   }

   @Composable
   fun DirectoryCustomText(text: String) {
       val fontFamily = FontFamily(Font(R.font.custom_font))
       Text(text = text, fontFamily = fontFamily)
   }
   ```

5. **Provide Usage Examples:**
   ```kotlin
   @Composable
   fun ExampleUsage() {
       VectorIconLoader(iconRes = R.drawable.ic_functional, contentDescription = "Functional Icon")
       DirectoryCustomText(text = "Hello, Compose!")
   }
   ```
