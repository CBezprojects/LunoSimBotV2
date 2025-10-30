# Just Enough Items - Fabric Mod for Minecraft 1.21.1

A recipe and item viewing mod for Minecraft, inspired by the original JEI mod. This version is built for Minecraft 1.21.1 (closest stable version to 1.21.10) using the Fabric modloader.

## Features

- **Item Browser**: Browse all items in the game with an intuitive overlay interface
- **Search Functionality**: Quickly find items by name
- **Recipe Viewer**: View crafting recipes for any item
- **Usage Viewer**: See what recipes use a particular item
- **Keyboard Shortcuts**: Quick access to all features
- **Inventory Integration**: Seamlessly integrates with vanilla and modded inventory screens

## Keybindings

- **O**: Toggle item overlay visibility
- **R**: Show recipe for hovered item
- **U**: Show usage for hovered item

All keybindings can be customized in Minecraft's controls menu.

## Building the Mod

### Prerequisites

- Java 21 or higher
- Git (optional, for version control)

### Build Instructions

1. Clone or download this repository

2. Open a terminal in the project directory

3. Run the build command:
   ```bash
   ./gradlew build
   ```
   
   On Windows:
   ```cmd
   gradlew.bat build
   ```

4. The compiled mod JAR will be located in:
   ```
   build/libs/justenoughitems-fabric-1.0.0.jar
   ```

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.21.1

2. Install [Fabric API](https://modrinth.com/mod/fabric-api) - download the version for Minecraft 1.21.1

3. Copy the built JAR file to your Minecraft mods folder:
   - Windows: `%APPDATA%\.minecraft\mods`
   - Linux: `~/.minecraft/mods`
   - macOS: `~/Library/Application Support/minecraft/mods`

4. Launch Minecraft with the Fabric profile

## Development Setup

### Setting up the development environment

1. Import the project into your IDE (IntelliJ IDEA or Eclipse)

2. Run the Gradle setup tasks:
   ```bash
   ./gradlew genSources
   ```

3. Refresh your IDE's Gradle project

### Running in Development

To test the mod in a development environment:

```bash
./gradlew runClient
```

This will launch Minecraft with the mod loaded in a development environment.

### Project Structure

```
jei-fabric-mod/
├── src/
│   ├── main/
│   │   ├── java/com/justenoughitems/fabric/
│   │   │   └── JustEnoughItems.java          # Main mod initializer
│   │   └── resources/
│   │       ├── fabric.mod.json                # Mod metadata
│   │       ├── justenoughitems.mixins.json   # Mixin configuration
│   │       └── assets/justenoughitems/
│   │           └── lang/en_us.json            # English translations
│   └── client/
│       └── java/com/justenoughitems/fabric/client/
│           ├── JustEnoughItemsClient.java     # Client-side initializer
│           ├── gui/
│           │   ├── OverlayRenderer.java       # Item overlay rendering
│           │   └── RecipeViewerScreen.java    # Recipe viewing GUI
│           ├── recipe/
│           │   └── RecipeManager.java         # Recipe indexing and search
│           └── mixin/
│               └── ScreenMixin.java           # Screen event hooks
├── build.gradle                                # Gradle build configuration
├── gradle.properties                           # Project properties
└── settings.gradle                             # Gradle settings
```

## How It Works

### Item Indexing

The mod indexes all items from the Minecraft registry on client startup and when joining a world. Items are stored in memory for quick searching and filtering.

### Recipe Management

Recipes are indexed by:
- **Output**: What items they produce
- **Ingredients**: What items they require

This allows for fast lookups when showing recipes or usage information.

### Overlay System

The item overlay is rendered using Fabric's HUD rendering API. It appears on the right side of inventory screens and updates in real-time based on the search query.

### Mixin Integration

A lightweight mixin on the Screen class allows the mod to intercept scroll events for the item overlay, providing smooth scrolling through long item lists.

## Future Enhancements

Potential features to add:

- [ ] Support for more recipe types (furnace, brewing, etc.)
- [ ] Bookmarks/favorites system
- [ ] Item tags and category filtering
- [ ] Support for modded recipe types
- [ ] Recipe transfer (click to move items from inventory)
- [ ] Multi-page recipe support
- [ ] Tooltip information for items
- [ ] Integration with mod APIs
- [ ] Configuration file for customization

## Compatibility

- **Minecraft Version**: 1.21.1
- **Mod Loader**: Fabric
- **Required Dependencies**: Fabric API
- **Java Version**: 21+

## Troubleshooting

### Mod doesn't load

- Ensure you have the correct Minecraft version (1.21.1)
- Verify Fabric Loader is installed
- Check that Fabric API is in your mods folder
- Look for errors in the Minecraft logs (`logs/latest.log`)

### Overlay doesn't appear

- Press 'O' to toggle overlay visibility
- Make sure you're in an inventory screen
- Check keybinding settings in case of conflicts

### Build fails

- Ensure you have Java 21 or higher installed
- Run `./gradlew clean` before building again
- Check your internet connection (Gradle needs to download dependencies)

## License

MIT License - Feel free to modify and distribute as needed.

## Credits

Inspired by the original Just Enough Items mod by mezz.

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.
