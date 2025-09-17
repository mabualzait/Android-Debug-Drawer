#!/bin/bash

# Android Debug Drawer - Simple Release Script for JitPack
# This script helps you create and publish releases to JitPack (skips code quality checks)

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if we're in the right directory
if [ ! -f "build.gradle.kts" ] || [ ! -d "debugdrawer" ]; then
    print_error "Please run this script from the project root directory"
    exit 1
fi

# Check if git is clean
if [ -n "$(git status --porcelain)" ]; then
    print_warning "You have uncommitted changes. Please commit or stash them first."
    git status --short
    read -p "Do you want to continue anyway? (y/N): " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
fi

# Get current version from gradle.properties
CURRENT_VERSION=$(grep "VERSION_NAME=" gradle.properties | cut -d'=' -f2)
print_status "Current version: $CURRENT_VERSION"

# Ask for new version
read -p "Enter new version (current: $CURRENT_VERSION): " NEW_VERSION

if [ -z "$NEW_VERSION" ]; then
    NEW_VERSION=$CURRENT_VERSION
fi

# Validate version format (basic check)
if [[ ! $NEW_VERSION =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
    print_error "Version must be in format X.Y.Z (e.g., 1.0.0)"
    exit 1
fi

print_status "Preparing release version: $NEW_VERSION"

# Update version in gradle.properties
sed -i.bak "s/VERSION_NAME=.*/VERSION_NAME=$NEW_VERSION/" gradle.properties
print_success "Updated version in gradle.properties"

# Build the library only (skip code quality checks)
print_status "Building library (skipping code quality checks)..."
./gradlew :debugdrawer:assembleRelease

if [ $? -eq 0 ]; then
    print_success "Library build successful"
else
    print_error "Library build failed. Please fix the issues and try again."
    exit 1
fi

# Commit version change
git add gradle.properties
git commit -m "Bump version to $NEW_VERSION"

# Create and push tag
TAG_NAME="v$NEW_VERSION"
print_status "Creating tag: $TAG_NAME"

git tag -a "$TAG_NAME" -m "Release version $NEW_VERSION"
git push origin master
git push origin "$TAG_NAME"

print_success "Tag $TAG_NAME created and pushed"

# Display JitPack information
echo
print_success "Release $NEW_VERSION is now available on JitPack!"
echo
echo "Your library can be used with:"
echo "  implementation 'com.abualzait:debugdrawer:$NEW_VERSION'"
echo
echo "Or with the specific tag:"
echo "  implementation 'com.abualzait:debugdrawer:$TAG_NAME'"
echo
echo "Check the build status at: https://jitpack.io/#mabualzait/Android-Debug-Drawer/$TAG_NAME"
echo
print_status "It may take a few minutes for JitPack to build your library."

# Clean up backup file
rm -f gradle.properties.bak

print_success "Release process completed!"
