# Maven Central Publishing Setup Guide

This guide will help you publish the Android Debug Drawer library to Maven Central.

## Prerequisites

1. **Sonatype OSS Account**: Create an account at https://s01.oss.sonatype.org/
2. **GPG Key**: Generate a GPG key for signing artifacts
3. **Domain Verification**: Verify ownership of the `com.abualzait` domain

## Step 1: Create Sonatype OSS Account

1. Go to https://s01.oss.sonatype.org/
2. Click "Sign Up" and create an account
3. Create a new project ticket for `com.abualzait` group ID
4. Wait for approval (usually 1-2 business days)

## Step 2: Generate GPG Key

### On macOS/Linux:
```bash
# Install GPG if not already installed
brew install gnupg  # macOS
# or
sudo apt-get install gnupg  # Ubuntu/Debian

# Generate a new key
gpg --gen-key

# List your keys to get the key ID
gpg --list-secret-keys

# Export your public key
gpg --armor --export YOUR_KEY_ID > public_key.asc

# Export your private key
gpg --armor --export-secret-keys YOUR_KEY_ID > private_key.asc
```

### Upload Public Key to Keyservers:
```bash
gpg --send-keys YOUR_KEY_ID
gpg --keyserver hkp://keyserver.ubuntu.com --send-keys YOUR_KEY_ID
```

## Step 3: Configure Credentials

Create a `~/.gradle/gradle.properties` file with your credentials:

```properties
# Sonatype OSS credentials
sonatypeUsername=your-sonatype-username
sonatypePassword=your-sonatype-password

# GPG signing credentials
signingKeyId=YOUR_KEY_ID
signingKey=YOUR_PRIVATE_KEY
signingPassword=YOUR_KEY_PASSPHRASE
```

**⚠️ Security Note**: Never commit these credentials to version control!

## Step 4: Publish to Maven Central

### Option 1: Using the Script
```bash
# Set environment variables
export SONATYPE_USERNAME=your-username
export SONATYPE_PASSWORD=your-password
export SIGNING_KEY_ID=your-key-id
export SIGNING_KEY=your-private-key
export SIGNING_PASSWORD=your-passphrase

# Run the publishing script
./publish-to-maven-central.sh
```

### Option 2: Manual Gradle Commands
```bash
# Clean and build
./gradlew clean
./gradlew :debugdrawer:assembleRelease

# Publish to staging
./gradlew :debugdrawer:publishReleasePublicationToSonatypeRepository
```

## Step 5: Release from Staging

1. Go to https://s01.oss.sonatype.org/
2. Login with your credentials
3. Navigate to "Staging Repositories"
4. Find your repository (should contain `comabualzait`)
5. Click "Close" and wait for validation
6. If validation passes, click "Release"
7. The library will be available on Maven Central in ~10 minutes

## Step 6: Verify Publication

Check that your library is available at:
- https://search.maven.org/artifact/com.abualzait/debugdrawer
- https://repo1.maven.org/maven2/com/abualzait/debugdrawer/

## Usage in Projects

Once published, users can add the library to their projects:

```kotlin
// In project-level build.gradle.kts
allprojects {
    repositories {
        mavenCentral()
        // ... other repositories
    }
}

// In app-level build.gradle.kts
dependencies {
    implementation("com.abualzait:debugdrawer:1.2.3")
}
```

## Troubleshooting

### Common Issues:

1. **"Repository not found"**: Make sure you've created a ticket for the group ID
2. **"Invalid signature"**: Verify your GPG key is uploaded to keyservers
3. **"Authentication failed"**: Check your Sonatype credentials
4. **"Staging validation failed"**: Check the validation errors in the Sonatype UI

### Getting Help:

- Sonatype OSS Documentation: https://central.sonatype.org/
- Maven Central Search: https://search.maven.org/
- GPG Documentation: https://gnupg.org/documentation/

## Library Information

- **Group ID**: `com.abualzait`
- **Artifact ID**: `debugdrawer`
- **Current Version**: `1.2.3`
- **License**: MIT
- **Repository**: https://github.com/mabualzait/Android-Debug-Drawer
