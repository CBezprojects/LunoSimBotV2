# Testing Instructions

## Method 1: Development Environment (Recommended)

This launches Minecraft with the mod already loaded:

```bash
cd /workspace/jei-fabric-mod
./gradlew runClient
```

This will:
- Download Minecraft 1.21.1 client
- Set up a development environment
- Launch the game with your mod loaded
- Create a `run/` directory for game data

**First run takes 5-10 minutes to download dependencies**

Once in-game:
1. Start a world or join a server
2. Open your inventory (E key)
3. Press **O** to toggle the item overlay
4. Hover over items and press **R** to view recipes
5. Press **U** to view item usage

---

## Method 2: Install as Regular Mod

### Prerequisites
1. Install [Fabric Loader](https://fabricmc.net/use/installer/) for Minecraft 1.21.1
2. Download [Fabric API](https://modrinth.com/mod/fabric-api/version/0.107.0+1.21.1)

### Installation
```bash
# Build the mod
cd /workspace/jei-fabric-mod
./gradlew build

# The compiled JAR will be at:
# build/libs/justenoughitems-fabric-1.0.0.jar

# Copy to Minecraft mods folder:
# Linux: ~/.minecraft/mods/
# Windows: %APPDATA%\.minecraft\mods\
# macOS: ~/Library/Application Support/minecraft/mods/
```

Then launch Minecraft with the Fabric profile.

---

## Testing Checklist

- [ ] Mod loads without errors
- [ ] Item overlay appears when pressing O
- [ ] Search bar filters items correctly
- [ ] Items are visible and clickable
- [ ] R key shows recipe information
- [ ] U key shows usage information
- [ ] Scroll wheel navigates item list
- [ ] Works in different inventory screens

---

## Debugging

### Check logs
```bash
# Development logs
tail -f run/logs/latest.log

# After installation
# Linux/Mac: ~/.minecraft/logs/latest.log
# Windows: %APPDATA%\.minecraft\logs\latest.log
```

### Common Issues

**"Mod requires Fabric API"**
- Install Fabric API mod

**"Wrong Minecraft version"**
- Ensure you're running Minecraft 1.21.1

**"Java version mismatch"**
- Requires Java 21+
- Check with: `java -version`

**Overlay doesn't appear**
- Press O to toggle visibility
- Check you're in an inventory screen
- Look for errors in logs
