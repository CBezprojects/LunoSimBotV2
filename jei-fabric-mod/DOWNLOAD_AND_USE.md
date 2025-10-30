# Download and Use Locally

## 📦 Download Options

I've created two archive files for you:

1. **ZIP Archive**: `/workspace/jei-fabric-mod.zip` (161KB)
2. **TAR.GZ Archive**: `/workspace/jei-fabric-mod.tar.gz` 

Both contain the complete project with:
- ✅ All source code (Java files)
- ✅ Build configuration (Gradle)
- ✅ Documentation (README, guides)
- ✅ Git repository (with initial commit)
- ✅ Ready to build and run

---

## 🚀 Quick Setup (5 minutes)

### Step 1: Extract the Archive

**On Windows:**
```cmd
unzip jei-fabric-mod.zip
cd jei-fabric-mod
```

**On Linux/Mac:**
```bash
tar -xzf jei-fabric-mod.tar.gz
cd jei-fabric-mod
```

Or for ZIP:
```bash
unzip jei-fabric-mod.zip
cd jei-fabric-mod
```

### Step 2: Verify Java Version

You need Java 21 or higher:

```bash
java -version
```

Should show: `openjdk version "21.x.x"` or higher

**Don't have Java 21?**
- Download from: https://adoptium.net/temurin/releases/?version=21
- Or use your package manager:
  - Linux: `sudo apt install openjdk-21-jdk`
  - Mac: `brew install openjdk@21`
  - Windows: Download installer from link above

### Step 3: Build the Mod

```bash
# On Linux/Mac:
./gradlew build

# On Windows:
gradlew.bat build
```

First build takes ~2-5 minutes to download dependencies.

✅ **Success!** Compiled mod at: `build/libs/justenoughitems-fabric-1.0.0.jar`

---

## 🧪 Test the Mod

### Option A: Development Testing (Recommended)

This automatically launches Minecraft with your mod:

```bash
# Linux/Mac:
./gradlew runClient

# Windows:
gradlew.bat runClient
```

⏱️ First run takes ~5-10 minutes to download Minecraft.

### Option B: Install in Real Minecraft

1. **Install Fabric Loader**
   - Download: https://fabricmc.net/use/installer/
   - Run installer for Minecraft 1.21.1

2. **Download Fabric API**
   - Get it: https://modrinth.com/mod/fabric-api/version/0.107.0+1.21.1
   - Save to your mods folder

3. **Copy Your Mod**
   ```bash
   # Copy from: build/libs/justenoughitems-fabric-1.0.0.jar
   # To mods folder:
   
   # Windows: %APPDATA%\.minecraft\mods\
   # Linux: ~/.minecraft/mods/
   # Mac: ~/Library/Application Support/minecraft/mods/
   ```

4. **Launch Minecraft**
   - Select "Fabric" profile
   - Start game

---

## 🎮 Using the Mod In-Game

Once Minecraft launches:

1. **Create or load a world**
2. **Open inventory** (E key)
3. **Press O** - Toggle item overlay (appears on right side)
4. **Type to search** - Filter items instantly
5. **Hover + Press R** - View crafting recipe
6. **Hover + Press U** - View item usage

### Keybindings
- `O` = Toggle overlay on/off
- `R` = Show recipe for hovered item
- `U` = Show usage for hovered item
- `Mouse Wheel` = Scroll through items

All keys can be changed in Minecraft Settings > Controls > Just Enough Items

---

## 📝 Project Structure

```
jei-fabric-mod/
├── src/
│   ├── main/java/                     # Core mod code
│   ├── client/java/                   # Client-side code
│   │   ├── gui/                       # UI components
│   │   ├── recipe/                    # Recipe management
│   │   └── mixin/                     # Minecraft hooks
│   └── resources/                     # Mod metadata & assets
├── build.gradle                        # Build configuration
├── gradle.properties                   # Version settings
├── gradlew & gradlew.bat              # Build scripts
├── README.md                          # Full documentation
├── QUICKSTART.md                      # Quick reference
└── TEST_INSTRUCTIONS.md               # Testing guide
```

---

## 🛠️ Development

### Make Changes to Code

1. Edit Java files in `src/` directories
2. Rebuild: `./gradlew build`
3. Test: `./gradlew runClient`

### IDE Setup

**IntelliJ IDEA** (Recommended):
1. File > Open > Select `jei-fabric-mod` folder
2. Import as Gradle project
3. Wait for indexing to complete
4. Run: `./gradlew genSources` to decompile Minecraft
5. Refresh Gradle project

**VS Code**:
1. Install "Extension Pack for Java"
2. Open `jei-fabric-mod` folder
3. Run: `./gradlew genSources`

**Eclipse**:
1. File > Import > Gradle > Existing Gradle Project
2. Select `jei-fabric-mod` folder
3. Run: `./gradlew eclipse`

---

## 🐛 Troubleshooting

### "Permission denied" on gradlew
```bash
chmod +x gradlew
```

### "Java version mismatch"
- Ensure Java 21+ is installed
- Set JAVA_HOME environment variable

### "Could not resolve dependencies"
- Check internet connection
- Try: `./gradlew build --refresh-dependencies`

### Build fails
```bash
# Clean and rebuild
./gradlew clean build
```

### Mod doesn't load
- Check Minecraft version is 1.21.1
- Verify Fabric Loader is installed
- Ensure Fabric API mod is in mods folder
- Check logs: `~/.minecraft/logs/latest.log`

---

## 📤 Push to GitHub

Already initialized as git repository:

```bash
# Create repo on GitHub first, then:
git remote add origin https://github.com/YOUR_USERNAME/jei-fabric-mod.git
git branch -M main
git push -u origin main
```

---

## 📚 Additional Resources

- **Fabric Wiki**: https://fabricmc.net/wiki/
- **Minecraft Modding**: https://fabricmc.net/develop/
- **Fabric API Docs**: https://maven.fabricmc.net/docs/

---

## ✅ What's Included

- [x] Complete working mod
- [x] All source code
- [x] Build system (Gradle)
- [x] Documentation
- [x] Git repository
- [x] MIT License
- [x] Ready to compile and run

**Total Files**: 21 source files  
**Project Size**: 47MB (includes downloaded Gradle)  
**Compressed**: 161KB (zip) without build artifacts

---

Need help? Check README.md for detailed documentation!
