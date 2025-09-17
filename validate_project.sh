#!/bin/bash

# Android Debug Drawer Project Validation Script
# This script validates that the project is properly set up and ready to use

echo "🔍 Validating Android Debug Drawer Project..."
echo "=============================================="

# Check if we're in the right directory
if [ ! -f "settings.gradle.kts" ]; then
    echo "❌ Error: Not in the project root directory"
    exit 1
fi

echo "✅ Project root directory found"

# Check for required files
echo ""
echo "📁 Checking project structure..."

required_files=(
    "build.gradle.kts"
    "settings.gradle.kts"
    "gradle.properties"
    "gradlew"
    "gradlew.bat"
    "README.md"
    "detekt.yml"
    "debugdrawer/build.gradle.kts"
    "sampleapp/build.gradle.kts"
    "debugdrawer/src/main/AndroidManifest.xml"
    "sampleapp/src/main/AndroidManifest.xml"
    ".github/workflows/ci.yml"
)

for file in "${required_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ Missing: $file"
        exit 1
    fi
done

# Check for required directories
echo ""
echo "📂 Checking directory structure..."

required_dirs=(
    "debugdrawer/src/main/java/com/debugdrawer"
    "debugdrawer/src/main/res/layout"
    "debugdrawer/src/test/java/com/debugdrawer"
    "sampleapp/src/main/java/com/debugdrawer/sampleapp"
    "sampleapp/src/main/res/layout"
    "gradle/wrapper"
)

for dir in "${required_dirs[@]}"; do
    if [ -d "$dir" ]; then
        echo "✅ $dir"
    else
        echo "❌ Missing directory: $dir"
        exit 1
    fi
done

# Check for required Kotlin files
echo ""
echo "📝 Checking Kotlin source files..."

kotlin_files=(
    "debugdrawer/src/main/java/com/debugdrawer/DebugDrawer.kt"
    "debugdrawer/src/main/java/com/debugdrawer/modules/DebugModule.kt"
    "debugdrawer/src/main/java/com/debugdrawer/modules/AppInfoModule.kt"
    "debugdrawer/src/main/java/com/debugdrawer/modules/NetworkModule.kt"
    "debugdrawer/src/main/java/com/debugdrawer/modules/FeatureFlagsModule.kt"
    "debugdrawer/src/main/java/com/debugdrawer/modules/LogsModule.kt"
    "debugdrawer/src/main/java/com/debugdrawer/modules/SettingsModule.kt"
    "debugdrawer/src/main/java/com/debugdrawer/modules/ClipboardModule.kt"
    "debugdrawer/src/main/java/com/debugdrawer/utils/Logger.kt"
    "debugdrawer/src/main/java/com/debugdrawer/utils/NetworkInterceptor.kt"
    "debugdrawer/src/main/java/com/debugdrawer/di/DebugDrawerModule.kt"
    "sampleapp/src/main/java/com/debugdrawer/sampleapp/MainActivity.kt"
    "sampleapp/src/main/java/com/debugdrawer/sampleapp/SampleApplication.kt"
    "sampleapp/src/main/java/com/debugdrawer/sampleapp/network/SampleNetworkClient.kt"
)

for file in "${kotlin_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ Missing: $file"
        exit 1
    fi
done

# Check for required layout files
echo ""
echo "🎨 Checking layout files..."

layout_files=(
    "debugdrawer/src/main/res/layout/module_app_info.xml"
    "debugdrawer/src/main/res/layout/module_network.xml"
    "debugdrawer/src/main/res/layout/module_feature_flags.xml"
    "debugdrawer/src/main/res/layout/module_logs.xml"
    "debugdrawer/src/main/res/layout/module_settings.xml"
    "debugdrawer/src/main/res/layout/module_clipboard.xml"
    "debugdrawer/src/main/res/layout/item_network_log.xml"
    "debugdrawer/src/main/res/layout/item_feature_flag.xml"
    "debugdrawer/src/main/res/layout/item_log.xml"
    "debugdrawer/src/main/res/layout/item_setting.xml"
    "sampleapp/src/main/res/layout/activity_main.xml"
)

for file in "${layout_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ Missing: $file"
    fi
done

# Check for test files
echo ""
echo "🧪 Checking test files..."

test_files=(
    "debugdrawer/src/test/java/com/debugdrawer/DebugDrawerTest.kt"
    "debugdrawer/src/test/java/com/debugdrawer/modules/AppInfoModuleTest.kt"
    "debugdrawer/src/test/java/com/debugdrawer/utils/LoggerTest.kt"
)

for file in "${test_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ Missing: $file"
    fi
done

# Check for resource files
echo ""
echo "🎨 Checking resource files..."

resource_files=(
    "debugdrawer/src/main/res/values/strings.xml"
    "debugdrawer/src/main/res/values/colors.xml"
    "debugdrawer/src/main/res/values/themes.xml"
    "sampleapp/src/main/res/values/strings.xml"
    "sampleapp/src/main/res/values/colors.xml"
    "sampleapp/src/main/res/values/themes.xml"
)

for file in "${resource_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ Missing: $file"
    fi
done

# Check for configuration files
echo ""
echo "⚙️ Checking configuration files..."

config_files=(
    "detekt.yml"
    "gradle.properties"
    "debugdrawer/proguard-rules.pro"
    "debugdrawer/consumer-rules.pro"
    "sampleapp/proguard-rules.pro"
)

for file in "${config_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ Missing: $file"
    fi
done

# Check for CI/CD files
echo ""
echo "🚀 Checking CI/CD files..."

ci_files=(
    ".github/workflows/ci.yml"
    "gradle/wrapper/gradle-wrapper.properties"
)

for file in "${ci_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ Missing: $file"
    fi
done

# Check for documentation files
echo ""
echo "📚 Checking documentation files..."

doc_files=(
    "README.md"
    "PROJECT_SUMMARY.md"
    "idea.md"
)

for file in "${doc_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ Missing: $file"
    fi
done

# Check gradlew permissions
echo ""
echo "🔐 Checking gradlew permissions..."

if [ -x "gradlew" ]; then
    echo "✅ gradlew is executable"
else
    echo "❌ gradlew is not executable"
    echo "   Run: chmod +x gradlew"
fi

# Final summary
echo ""
echo "🎉 Project Validation Complete!"
echo "==============================="
echo ""
echo "The Android Debug Drawer project is properly structured and ready to use."
echo ""
echo "Next steps:"
echo "1. Open the project in Android Studio"
echo "2. Sync Gradle files"
echo "3. Run the sample app"
echo "4. Explore the debug drawer features"
echo ""
echo "For detailed usage instructions, see README.md"
echo "For project overview, see PROJECT_SUMMARY.md"
