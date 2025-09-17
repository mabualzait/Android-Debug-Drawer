# Android Debug Drawer

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![CI](https://github.com/mabualzait/Android-Debug-Drawer/workflows/CI/badge.svg)](https://github.com/mabualzait/Android-Debug-Drawer/actions)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org/)

A **comprehensive, production-ready debug drawer for Android apps** that provides developers with powerful debugging tools directly within their applications. This library overlays your app in debug builds and offers modular debugging capabilities including app inspection, network monitoring, feature flag management, and more—all without leaving your app.

## 🎯 Why Android Debug Drawer?

- **🚀 Zero Setup** - Get started in minutes with minimal configuration
- **🔧 Modular Design** - Add or remove debugging modules as needed
- **📱 Production Ready** - Automatically disabled in release builds
- **🎨 Beautiful UI** - Clean, intuitive interface following Material Design principles
- **⚡ High Performance** - Lightweight with minimal impact on app performance
- **🧪 Well Tested** - Comprehensive test coverage with CI/CD pipeline

## 🌟 Features

The drawer is **modular**, allowing developers to add or remove widgets. Built-in modules include:

- **📱 App & Device Info** - Display application and device information
- **🌐 Network Logs** - View HTTP request and response logs with timing
- **🚩 Feature Flags** - Toggle feature flags at runtime for testing
- **📋 Logs Viewer** - Browse system and application logs with filtering
- **⚙️ Settings Override** - Override app settings for testing
- **📋 Clipboard Tools** - Copy and paste text for debugging

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

#### Option A: Clone and Include as Module (Recommended)

1. **Clone the repository:**
   ```bash
   git clone https://github.com/mabualzait/Android-Debug-Drawer.git
   ```

2. **Add to your project:**
   - Copy the `debugdrawer` folder to your project root
   - Add to your `settings.gradle.kts`:
     ```kotlin
     include(":debugdrawer")
     ```

3. **Add dependency in your app's `build.gradle.kts`:**
   ```kotlin
   dependencies {
       debugImplementation project(":debugdrawer")
   }
   ```

#### Option B: JitPack (Coming Soon)

```kotlin
dependencies {
    debugImplementation 'com.github.mabualzait:Android-Debug-Drawer:1.0.0'
}
```

### 2. Basic Setup

#### Step 1: Add Hilt to your project (if not already added)

Add to your app's `build.gradle.kts`:
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

#### Step 2: Initialize in your Application class

```kotlin
@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var debugDrawer: DebugDrawer

    override fun onCreate() {
        super.onCreate()
        
        if (BuildConfig.DEBUG) {
            // Debug drawer is automatically initialized when first accessed
        }
    }
}
```

#### Step 3: Set up in your MainActivity

```kotlin
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    @Inject
    lateinit var debugDrawer: DebugDrawer
    
    @Inject
    lateinit var appInfoModule: AppInfoModule
    
    @Inject
    lateinit var networkModule: NetworkModule
    
    @Inject
    lateinit var featureFlagsModule: FeatureFlagsModule
    
    @Inject
    lateinit var logsModule: LogsModule
    
    @Inject
    lateinit var settingsModule: SettingsModule
    
    @Inject
    lateinit var clipboardModule: ClipboardModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setupDebugDrawer()
    }
    
    private fun setupDebugDrawer() {
        // Initialize the debug drawer
        debugDrawer.initialize(this)
        
        // Add all modules
        debugDrawer.addModule(appInfoModule)
        debugDrawer.addModule(networkModule)
        debugDrawer.addModule(featureFlagsModule)
        debugDrawer.addModule(logsModule)
        debugDrawer.addModule(settingsModule)
        debugDrawer.addModule(clipboardModule)
    }
    
    // Add a button to toggle the debug drawer
    private fun setupToggleButton() {
        findViewById<Button>(R.id.btn_toggle_debug)?.setOnClickListener {
            debugDrawer.toggle()
        }
    }
}
```

### 3. Using the Debug Drawer

#### Opening the Debug Drawer

- **Programmatically:** Call `debugDrawer.toggle()` or `debugDrawer.show()`
- **Via Button:** Add a button in your UI to toggle the drawer
- **Via Gesture:** Implement custom gestures (e.g., shake detection)

#### Available Modules

1. **📱 App & Device Info**
   - View app name, version, package name
   - Display device model, Android version, build info
   - Useful for QA testing and bug reports

2. **🌐 Network Logs**
   - Monitor HTTP requests and responses
   - View request/response headers and body
   - Track response times and status codes
   - Filter by method, status, or time

3. **🚩 Feature Flags**
   - Toggle feature flags at runtime
   - Persist flag states across app restarts
   - Test different feature combinations
   - A/B testing support

4. **📋 Logs Viewer**
   - Browse system and application logs
   - Filter by log level (Verbose, Debug, Info, Warning, Error)
   - Search through log messages
   - Export logs for debugging

5. **⚙️ Settings Override**
   - Modify app settings at runtime
   - Test different configuration values
   - Persist settings across sessions
   - API endpoints, timeouts, feature toggles

6. **📋 Clipboard Tools**
   - Copy text to clipboard
   - Paste from clipboard
   - View current clipboard content
   - Useful for testing text input scenarios

### 4. Advanced Configuration

#### Customizing Modules

```kotlin
// Add only specific modules
debugDrawer.addModule(appInfoModule)
debugDrawer.addModule(networkModule)

// Remove a module
debugDrawer.removeModule(logsModule)

// Check if drawer is visible
if (debugDrawer.isVisible()) {
    // Handle drawer visibility
}
```

#### Network Logging Setup

To enable network request logging, add the NetworkInterceptor to your OkHttpClient:

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

#### Feature Flags Integration

```kotlin
@Inject
lateinit var featureFlagsModule: FeatureFlagsModule

// Check if a feature is enabled
lifecycleScope.launch {
    val isNewUIEnabled = featureFlagsModule.isFeatureEnabled("enable_new_ui")
    if (isNewUIEnabled) {
        // Show new UI
    }
}
```

#### Settings Integration

```kotlin
@Inject
lateinit var settingsModule: SettingsModule

// Get a setting value
lifecycleScope.launch {
    val apiUrl = settingsModule.getSettingValue("api_base_url")
    // Use the setting value
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
│   │   │   ├── FeatureFlagsModule.kt
│   │   │   ├── LogsModule.kt
│   │   │   ├── SettingsModule.kt
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

## 🔧 Configuration

### Customizing Modules

You can customize which modules are shown by adding or removing them:

```kotlin
// Add only specific modules
debugDrawer.addModule(appInfoModule)
debugDrawer.addModule(networkModule)

// Remove a module
debugDrawer.removeModule(logsModule)
```

### Creating Custom Modules

Create your own debug module by implementing the `DebugModule` interface:

```kotlin
class CustomModule @Inject constructor(
    private val context: Context,
    private val logger: Logger
) : DebugModule {
    
    override val name: String = "custom_module"
    override val title: String = "Custom Module"
    override val description: String = "My custom debugging module"
    override val priority: Int = 10
    
    override fun createView(): View {
        // Create and return your custom view
        return LayoutInflater.from(context)
            .inflate(R.layout.custom_module_layout, null)
    }
    
    // Implement other interface methods as needed
}
```

### Feature Flags

Feature flags are automatically persisted using DataStore. Access them in your app:

```kotlin
@Inject
lateinit var featureFlagsModule: FeatureFlagsModule

// Check if a feature is enabled
lifecycleScope.launch {
    val isNewUIEnabled = featureFlagsModule.isFeatureEnabled("enable_new_ui")
    if (isNewUIEnabled) {
        // Show new UI
    }
}
```

### Settings Override

Settings are also persisted using DataStore:

```kotlin
@Inject
lateinit var settingsModule: SettingsModule

// Get a setting value
lifecycleScope.launch {
    val apiUrl = settingsModule.getSettingValue("api_base_url")
    // Use the setting value
}
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

*Screenshots coming soon - showing the debug drawer interface and various modules*

## 🚀 Roadmap

- [ ] JitPack integration for easy dependency management
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

---

**Made with ❤️ for the Android development community**
