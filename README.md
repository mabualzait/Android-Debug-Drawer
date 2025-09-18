# Android Debug Drawer

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![CI](https://github.com/mabualzait/Android-Debug-Drawer/workflows/CI/badge.svg)](https://github.com/mabualzait/Android-Debug-Drawer/actions)
[![JitPack](https://jitpack.io/v/mabualzait/Android-Debug-Drawer.svg)](https://jitpack.io/#mabualzait/Android-Debug-Drawer)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org/)

A **comprehensive, production-ready debug drawer for Android apps** that provides developers with powerful debugging tools directly within their applications. 

**Stop switching between your app and external debugging tools.** This library gives you everything you need for debugging right inside your app - real-time logcat streaming, network request monitoring, feature flag toggles, app inspection, and more. Perfect for QA testing, development debugging, and production troubleshooting.

**Built by developers, for developers.** Created by **Malik Abualzait** with a focus on simplicity, performance, and extensibility.

## 🎯 Why Android Debug Drawer?

- **🚀 Zero Setup** - Get started in minutes with minimal configuration
- **🔧 Modular Design** - Add or remove debugging modules as needed
- **📱 Production Ready** - Automatically disabled in release builds
- **🎨 Beautiful UI** - Clean, intuitive interface following Material Design principles
- **⚡ High Performance** - Lightweight with minimal impact on app performance
- **🧪 Well Tested** - Comprehensive test coverage with CI/CD pipeline

## ⚡ Quick Start

**Want to try it right now?** Download the latest APK and install it on your device:

[![Download APK](https://img.shields.io/badge/Download-Latest%20APK-brightgreen.svg?style=for-the-badge&logo=android)](https://github.com/mabualzait/Android-Debug-Drawer/actions/workflows/ci.yml)

1. Click the button above → Go to latest workflow run → Download APK artifact
2. Install on your Android device
3. Open the app and tap "Toggle Debug Drawer"
4. Explore all the debugging modules!

## 🌟 Features

### 🔧 **Modular Architecture**
Pick and choose only the debugging tools you need. Each module is independent and can be added/removed dynamically.

### 📱 **Built-in Debug Modules**

- **📱 App & Device Info** - View app version, device model, Android version, and build details
- **🌐 Network Monitoring** - Real-time HTTP request/response logging with headers, timing, and status codes
- **📋 Advanced Logcat Viewer** - Stream system and app logs with real-time filtering, search, and export
- **🚩 Feature Flags** - Toggle features at runtime with persistent storage across app restarts
- **⚙️ Settings Override** - Modify app settings on-the-fly for testing different configurations
- **📋 Clipboard Tools** - Copy/paste utilities for testing text input scenarios

### 🚀 **Developer Experience**
- **Zero Performance Impact** - Automatically disabled in release builds
- **Real-time Updates** - Live log streaming and network monitoring
- **Easy Integration** - Works with existing Hilt/DI setup
- **Extensible** - Create custom modules for your specific needs

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI:** Traditional Android Views (XML + Kotlin)
- **Dependency Injection:** Hilt
- **Networking:** Retrofit + OkHttp (for request logging)
- **Persistence:** DataStore (feature flags & settings)
- **Testing:** JUnit, Mockito, Robolectric, Espresso
- **Code Quality:** ktlint, detekt

## 📐 Architecture

- **Library Module** (`debugdrawer`) → reusable across projects
- **Sample App Module** (`sampleapp`) → demonstrates usage
- **Modular Design:** Each widget = independent module that can be added dynamically to the drawer

## 📖 User Manual

### Getting Started

The Android Debug Drawer is designed to be simple to integrate and use. Follow these steps to get started:

### 1. Installation

#### 🚀 **Quick Start (JitPack - Recommended)**

Add to your project's `build.gradle.kts` (project level):
```kotlin
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add to your app's `build.gradle.kts`:
```kotlin
dependencies {
    debugImplementation 'com.abualzait:debugdrawer:1.0.0'
}
```

**Available versions:**
- `1.0.0` - Latest stable release
- `main-SNAPSHOT` - Latest development version

#### 🔧 **Local Development (Clone as Module)**

For contributing or custom modifications:

1. **Clone and integrate:**
   ```bash
   git clone https://github.com/mabualzait/Android-Debug-Drawer.git
   # Copy debugdrawer folder to your project root
   ```

2. **Add to `settings.gradle.kts`:**
   ```kotlin
   include(":debugdrawer")
   ```

3. **Add dependency:**
   ```kotlin
   dependencies {
       debugImplementation project(":debugdrawer")
   }
   ```


### 2. Setup

#### **Prerequisites**
Ensure you have Hilt set up in your project. If not, add to your `build.gradle.kts`:
```kotlin
plugins {
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
}
```

#### **Application Setup**
```kotlin
@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var debugDrawer: com.abualzait.debugdrawer.DebugDrawer

    override fun onCreate() {
        super.onCreate()
        // Debug drawer auto-initializes when first accessed
    }
}
```

#### **Activity Integration**
```kotlin
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    @Inject lateinit var debugDrawer: com.abualzait.debugdrawer.DebugDrawer
    @Inject lateinit var appInfoModule: com.abualzait.debugdrawer.modules.AppInfoModule
    @Inject lateinit var networkModule: com.abualzait.debugdrawer.modules.NetworkModule
    @Inject lateinit var logsModule: com.abualzait.debugdrawer.modules.LogsModule
    @Inject lateinit var featureFlagsModule: com.abualzait.debugdrawer.modules.FeatureFlagsModule
    @Inject lateinit var settingsModule: com.abualzait.debugdrawer.modules.SettingsModule
    @Inject lateinit var clipboardModule: com.abualzait.debugdrawer.modules.ClipboardModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDebugDrawer()
    }
    
    private fun setupDebugDrawer() {
        debugDrawer.initialize(this)
        
        // Add modules (pick only what you need)
        debugDrawer.addModule(appInfoModule)
        debugDrawer.addModule(networkModule)
        debugDrawer.addModule(logsModule)
        debugDrawer.addModule(featureFlagsModule)
        debugDrawer.addModule(settingsModule)
        debugDrawer.addModule(clipboardModule)
        
        // Add toggle button
        findViewById<Button>(R.id.btn_toggle_debug)?.setOnClickListener {
            debugDrawer.toggle()
        }
    }
}
```

### 3. Usage

#### **Opening the Debug Drawer**
```kotlin
// Programmatically
debugDrawer.toggle()  // Show/hide
debugDrawer.show()    // Show only
debugDrawer.hide()    // Hide only

// Via UI button
findViewById<Button>(R.id.btn_toggle_debug)?.setOnClickListener {
    debugDrawer.toggle()
}
```

#### **Module Overview**

| Module | Purpose | Key Features |
|--------|---------|--------------|
| **📱 App Info** | App & device inspection | Version, package, device model, Android version |
| **🌐 Network** | HTTP monitoring | Request/response logging, timing, status codes |
| **📋 Logcat** | Real-time log streaming | Live filtering, search, export, auto-scroll |
| **🚩 Feature Flags** | Runtime feature toggles | Persistent storage, A/B testing support |
| **⚙️ Settings** | Configuration override | Runtime settings modification |
| **📋 Clipboard** | Text utilities | Copy/paste for testing scenarios |

#### **Real-world Use Cases**

**🔍 Development Debugging:**
- Monitor network requests during API development
- Stream logs in real-time without switching to external tools
- Toggle features instantly for testing different flows

**🧪 QA Testing:**
- Inspect app version and device info for bug reports
- Test feature combinations with flag toggles
- Override settings to test edge cases

**🐛 Production Troubleshooting:**
- Enable debug drawer in debug builds for customer support
- Export logs for analysis
- Monitor network issues in real-time

### 4. Advanced Usage

#### **Module Management**
```kotlin
// Add/remove modules dynamically
debugDrawer.addModule(customModule)
debugDrawer.removeModule(logsModule)

// Check drawer state
if (debugDrawer.isVisible()) {
    // Handle visibility
}
```

#### **Network Monitoring Setup**
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(networkInterceptor: NetworkInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .build()
    }
}
```

#### **Feature Flags Integration**
```kotlin
@Inject
lateinit var featureFlagsModule: com.abualzait.debugdrawer.modules.FeatureFlagsModule

// Check feature state
lifecycleScope.launch {
    val isEnabled = featureFlagsModule.isFeatureEnabled("new_feature")
    if (isEnabled) {
        // Enable new feature
    }
}
```

#### **Custom Modules**
```kotlin
class CustomModule @Inject constructor(
    private val context: Context
) : DebugModule {
    
    override val name = "custom"
    override val title = "Custom Module"
    override val description = "My custom debugging tool"
    
    override fun createView(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.custom_module_layout, null)
    }
}
```

## 📁 Project Structure

```
android-debug-drawer/
├── debugdrawer/                    # Library module
│   ├── src/main/java/com/debugdrawer/
│   │   ├── DebugDrawer.kt         # Main debug drawer class
│   │   ├── modules/               # Debug modules
│   │   │   ├── AppInfoModule.kt
│   │   │   ├── NetworkModule.kt
│   │   │   ├── com.abualzait.debugdrawer.modules.FeatureFlagsModule.kt
│   │   │   ├── LogsModule.kt
│   │   │   ├── com.abualzait.debugdrawer.modules.SettingsModule.kt
│   │   │   └── ClipboardModule.kt
│   │   ├── utils/                 # Utility classes
│   │   │   ├── Logger.kt
│   │   │   └── NetworkInterceptor.kt
│   │   └── di/                    # Dependency injection
│   │       └── DebugDrawerModule.kt
│   └── src/main/res/layout/       # Layout files
│       ├── module_app_info.xml
│       ├── module_network.xml
│       └── ...
├── sampleapp/                     # Sample application
│   ├── src/main/java/com/debugdrawer/sampleapp/
│   │   ├── MainActivity.kt
│   │   ├── SampleApplication.kt
│   │   └── network/
│   │       └── SampleNetworkClient.kt
│   └── src/main/res/
├── .github/workflows/             # CI/CD workflows
│   └── ci.yml
├── README.md
├── build.gradle.kts
└── settings.gradle.kts
```


## 🧪 Testing

The project includes comprehensive testing:

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Code Coverage
```bash
./gradlew jacocoTestReport
```

### Code Quality
```bash
./gradlew ktlintCheck
./gradlew detekt
```

## 📊 Code Quality

This project maintains high code quality standards:

- **ktlint** for code formatting
- **detekt** for static analysis
- **Unit tests** with JUnit, Mockito, and Robolectric
- **Integration tests** with Espresso
- **Code coverage** reporting with JaCoCo

## 🚀 CI/CD

The project uses GitHub Actions for continuous integration:

- **Code Quality Checks** - ktlint, detekt
- **Unit Tests** - JUnit, Mockito, Robolectric
- **Instrumented Tests** - Espresso on Android emulator
- **Build Verification** - Debug and release APK builds
- **Code Coverage** - JaCoCo reports uploaded to Codecov

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

If you have any questions or need help, please:

1. Check the [Issues](https://github.com/mabualzait/Android-Debug-Drawer/issues) page
2. Create a new issue if your question isn't already answered
3. Join our discussions for general questions

## 🎬 Demo

Check out the sample app included in this repository to see the Android Debug Drawer in action:

1. Clone the repository
2. Open in Android Studio
3. Run the `sampleapp` module
4. Tap "Toggle Debug Drawer" to see all features

## 📸 Screenshots

### Main Interface
![Debug Drawer Main Menu](Screenshot%20/Screenshot_20250918_114543_Debug%20Drawer%20Sample.png)
*Main debug drawer interface with module selection grid*

### Logcat Viewer
![Logcat Viewer](Screenshot%20/Screenshot_20250918_114555_Debug%20Drawer%20Sample.png)
*Enhanced logcat viewer with real-time streaming and filtering*

### Network Monitoring
![Network Module](Screenshot%20/Screenshot_20250918_114606_Debug%20Drawer%20Sample.png)
*Network request monitoring and logging*

### App Information
![App Info Module](Screenshot%20/Screenshot_20250918_114617_Debug%20Drawer%20Sample.png)
*App and device information display*

## 🚀 Roadmap

- [x] JitPack integration for easy dependency management
- [ ] More built-in modules (Database Inspector, Performance Monitor)
- [ ] Custom theme support
- [ ] Gesture-based activation (shake to open)
- [ ] Export functionality for logs and settings
- [ ] Plugin system for third-party modules

## 🙏 Acknowledgments

- [Material Design](https://material.io/) for the UI components
- [Hilt](https://dagger.dev/hilt/) for dependency injection
- [Retrofit](https://square.github.io/retrofit/) for networking
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) for data persistence
- Special thanks to the Android development community for inspiration and feedback

**Developer:** [Malik Abualzait](https://github.com/mabualzait) - Android Developer & Open Source Contributor

---

**Made with ❤️ by [Malik Abualzait](https://github.com/mabualzait) for the Android development community**
