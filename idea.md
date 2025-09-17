# Android Debug Drawer (Traditional Views)

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A **customizable debug drawer for Android apps** that overlays your app in debug builds and provides useful debugging tools for developers. Inspect app info, logs, network requests, feature flags, and more—without leaving the app.

---

## 🌟 Features

The drawer is **modular**, allowing developers to add or remove widgets. Built-in modules include:

- **App & Device Info**
- **Network Logs**
- **Feature Flags**
- **Authentication**
- **Logs Viewer**
- **Settings Overrides**
- **Clipboard Tools**

(See detailed features in previous sections)

---

## 🛠️ Tech Stack

- **Language:** Kotlin  
- **UI:** Traditional Android Views (XML + Kotlin)  
- **Dependency Injection:** Hilt or Koin (optional)  
- **Networking:** Retrofit + OkHttp (for request logging)  
- **Persistence:** SharedPreferences or DataStore (feature flags & settings)

---

## 📐 Architecture

- **Library Module** (`debugdrawer`) → reusable across projects  
- **Sample App Module** (`sampleapp`) → demonstrates usage  
- **Modular Design:** Each widget = independent Fragment or View that can be added dynamically to the drawer

---

## 📁 Folder Structure

Use **triple backticks with `text`** to avoid Markdown breaking:

```text
android-debug-drawer/
|
├── debugdrawer/                
│   ├── src/main/java/com/debugdrawer/
│   │   ├── DebugDrawer.kt      
│   │   ├── modules/
│   │   │   ├── AppInfoModule.kt
│   │   │   ├── NetworkModule.kt
│   │   │   ├── FeatureFlagsModule.kt
│   │   │   ├── LogsModule.kt
│   │   │   ├── SettingsModule.kt
│   │   │   └── ClipboardModule.kt
│   │   ├── utils/
│   │   │   ├── Logger.kt
│   │   │   └── NetworkInterceptor.kt
│   ├── res/layout/             
│   │   ├── drawer_container.xml
│   │   ├── module_app_info.xml
│   │   ├── module_network.xml
│   │   └── ...
|
├── sampleapp/                  
│   ├── MainActivity.kt
│   ├── SampleNetworkClient.kt
|
├── README.md                   
├── build.gradle
└── LICENSE
