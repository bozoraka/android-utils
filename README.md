# Android Utils

[![JitPack Badge](https://jitpack.io/v/com.bozoraka/android-utils.svg)](https://jitpack.io/#com.bozoraka/android-utils)

A collection of Kotlin extensions that we use in our Android applications.

## Installation

### Gradle

Add JitPack in your root `build.gradle` at the end of repositories:

```gradle
  allprojects {
    repositories {
      // ...
      maven { url 'https://jitpack.io' }
    }
  }
```

Add **Android Utils** to (sub-)project(s) `build.gradle`:

```gradle
  dependencies {
    // ...
    implementation 'com.bozoraka:android-utils:1.0.2'
  }
```

## License

The library is distributed under the [Apache 2 license](./LICENSE).
