# Quick Start Guide

## Building the Mod

```bash
cd /workspace/jei-fabric-mod
./gradlew build
```

The compiled mod will be at: `build/libs/justenoughitems-fabric-1.0.0.jar`

## Installing the Mod

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.21.1
2. Download [Fabric API](https://modrinth.com/mod/fabric-api) for Minecraft 1.21.1
3. Place both the Fabric API JAR and the built mod JAR into your `.minecraft/mods` folder
4. Launch Minecraft with the Fabric profile

## Using the Mod

Once in-game:

- **O key**: Toggle the item overlay on/off (visible in inventory screens)
- **R key**: Show recipe for the hovered item
- **U key**: Show usage/crafting uses for the hovered item
- **Search**: Type in the search bar to filter items
- **Scroll**: Use mouse wheel to scroll through items in the overlay

## Testing in Development

To test without installing:

```bash
./gradlew runClient
```

This launches Minecraft with the mod pre-loaded in a development environment.

## Features

✅ Browse all game items in a side panel overlay  
✅ Search items by name  
✅ View crafting recipes  
✅ View item usage in recipes  
✅ Keyboard shortcuts for quick access  
✅ Integrates with all inventory screens  

## Troubleshooting

**Build fails**: Ensure Java 21+ is installed (`java -version`)  
**Mod doesn't load**: Check that you're using Minecraft 1.21.1 with Fabric Loader  
**Overlay not visible**: Press 'O' to toggle, or check key bindings in settings  

## Next Steps

Check out the full [README.md](README.md) for detailed documentation, development setup, and contribution guidelines.
