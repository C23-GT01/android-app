# TrackMate Mobile App (Android)

TrackMate is an Android application designed for product traceability tracking, built with Jetpack Compose for a modern
and efficient user interface.

## Installation

To install TrackMate on your device, follow these steps:

1. [Download the latest release](https://github.com/C23-GT01/android-app/releases) from the Releases section.
2. Install the APK on your Android device.
3. Open the app and grant any necessary permissions.

## Build Your Own App

If you want to build your own instance of TrackMate or make modifications to the existing app, follow these steps:

### Prerequisites

Before you begin, make sure you have the following:

1. Android Studio installed on your development machine.
2. Git installed for version control.

### Steps

1. Clone the Repository

```bash
git clone https://github.com/C23-GT01/android-app.git
```

2. Open Android Studio and select "Open an existing Android Studio project." Navigate to the cloned repository and open
   the project.
3. Replace Google Maps API Key in Android Manifest

```bash
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API" />
```

4. Update API Endpoint in `build.gradle`

```bash
buildConfigField(
    "String",
    "BASE_URL",
    "\"https://your-api-endpoint.com/\""
)
```

5. Build the project in Android Studio and run it on an emulator or a physical Android device.

## Contributors

TrackMate is developed and maintained by the C23-GT01 Mobile Development Team:

- [Noel Marcell Jonathan Wongkar](https://github.com/noeljonathan)
- [I Gusti Ngurah Agung Kade Dwi Arsana](https://github.com/gustingurahagung)
- [Abdullah Fikri Handi Saputra](https://github.com/fikrihandy)
