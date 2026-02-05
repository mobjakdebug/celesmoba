# Celestial Project Context

## Project Overview
Celestial is an open-source Minecraft launcher that serves as an alternative to Lunar Client. It's designed to be lightweight, high-performance, and cross-platform while maintaining compatibility with Lunar Client features. The project is written in Kotlin and uses Java Swing for the GUI, with a focus on being a community-driven solution.

Key features include:
- Cross-platform support (Windows, Linux, macOS)
- Lightweight and high-performance
- No installation required
- External browser login support
- Custom API address configuration
- Java agent support (experimental)
- Weave support (experimental)
- Version JSON dumping to local storage
- Offline launch capabilities

## Architecture
The project follows a modular architecture with the following key components:
- **GUI Layer**: Built with Java Swing and FlatLaf for theming
- **Game Management**: Handles Minecraft/Lunar Client launching and configuration
- **API Integration**: Connects to Lunar Client's API for game data
- **Authentication**: Includes an auth server for Minecraft accounts
- **Addon System**: Supports experimental features like Weave and Java agents

## Building and Running

### Prerequisites
- Zulu JDK 21 (minimum Java 17, recommended Java 21)
- Gradle (wrapper provided)

### Build Commands
```bash
# Build the project (creates shadow JAR)
./gradlew build

# Create fat JAR with all dependencies
./gradlew shadowJar

# Run the application directly
./gradlew run

# Clean build artifacts
./gradlew clean
```

### Development Environment
The project supports Nix-based development environments using devenv:
- `devenv.yaml` configures the development environment
- Uses Zulu JDK 21 as specified in requirements

## Key Technologies
- **Language**: Kotlin (primary), some Java
- **Build System**: Gradle with Kotlin DSL
- **GUI Framework**: Java Swing with FlatLaf themes
- **Serialization**: KotlinX Serialization
- **HTTP Client**: OkHttp
- **JSON Processing**: Gson
- **Logging**: SLF4J with Log4J implementation
- **Coroutines**: KotlinX Coroutines for async operations

## Project Structure
```
src/
├── main/
│   ├── kotlin/           # Main Kotlin source code
│   │   └── org/cubewhy/celestial/
│   │       ├── Celestial.kt          # Main application entry point
│   │       ├── Configuration.kt      # Configuration data classes
│   │       ├── entities/             # Data models
│   │       ├── event/                # Event system
│   │       ├── files/                # File handling utilities
│   │       ├── game/                 # Game launching logic
│   │       ├── gui/                  # GUI components
│   │       ├── utils/                # Utility functions
│   │       └── ...
│   ├── resources/        # Resource files
│   │   ├── game/         # Game-related resources
│   │   ├── images/       # UI images
│   │   ├── languages/    # Localization files
│   │   └── log4j.properties
│   └── proto/            # Protocol buffer definitions
```

## Configuration
The application uses a JSON-based configuration system stored in `~/.cubewhy/celestial/celestial.json`. Key configuration areas include:
- API endpoint addresses
- Game settings (RAM allocation, directories, arguments)
- Theme selection
- Language preferences
- Addon configurations (Weave, LunarCN, LCQT)
- Proxy settings
- UI scaling options

## Development Conventions
- Written primarily in Kotlin (contributing guide states "Do not use JAVA")
- Uses Kotlin coroutines for asynchronous operations
- Event-driven architecture with EventManager
- Serialization using KotlinX Serialization
- Follows object-oriented design patterns with clear separation of concerns
- Uses modern Java/Kotlin libraries for networking, logging, and UI

## Important Notes
- The project is licensed under GPLv3
- Requires a Minecraft account to function (does not bypass authentication)
- Designed as a replacement for Lunar Client rather than a cracked version
- Actively maintained with nightly builds available
- Experimental features marked as such in the codebase