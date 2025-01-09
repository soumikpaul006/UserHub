# UserHub

UserHub is an Android application that demonstrates modern Android development practices. It fetches user data from a remote API, stores it locally, and allows users to view detailed information including user comments.

## Features

- **Splash Screen**: Initial loading screen with smooth transition
- **Home Screen**: Navigation to different data sources
- **Local Data Fetching**: View cached user data
- **Remote Data Fetching**: Fetch and store fresh user data
- **User Details**: View detailed user information and associated comments
- **Offline Support**: Access previously fetched data without internet connection

## Technology Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependencies**:
  - Hilt for dependency injection
  - Room for local database
  - Retrofit for network calls
  - Coroutines for asynchronous operations
  - LiveData for observable data holders
  - ViewBinding for view binding
  - JUnit and MockK for testing

## Project Structure

```
app/
├── data/
│   ├── local/
│   │   ├── UserDao
│   │   └── UserDatabase
│   └── remote/
│       └── ApiService
├── di/
│   └── AppModule
├── model/
│   ├── User
│   └── Comment
├── repository/
│   ├── IUserRepository
│   └── UserRepository
├── ui/
│   ├── MainActivity
│   ├── SplashFragment
│   ├── HomeFragment
│   ├── FetchLocalFragment
│   ├── FetchRemoteFragment
│   └── DetailScreenFragment
└── viewmodel/
    └── UserViewModel
```

## Setup

1. Clone the repository
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the app on an emulator or physical device

## Testing

The project includes comprehensive unit tests for the ViewModel layer. To run the tests:

```bash
./gradlew test
```

Test coverage includes:
- Remote data fetching
- Local data storage
- Error handling
- Loading states
- Comment fetching

## API

The app uses the JSONPlaceholder API for demonstration purposes:
- Users endpoint: https://jsonplaceholder.typicode.com/users
- Comments endpoint: https://jsonplaceholder.typicode.com/comments

## Architecture

The app follows MVVM architecture pattern and clean architecture principles:

1. **View Layer**: Activities and Fragments
2. **ViewModel Layer**: UserViewModel
3. **Repository Layer**: UserRepository
4. **Data Sources**: 
   - Remote: Retrofit API service
   - Local: Room Database

## Dependencies

Add these dependencies in your app's build.gradle:

```gradle
// Hilt
implementation "com.google.dagger:hilt-android:2.45"
kapt "com.google.dagger:hilt-android-compiler:2.45"

// Room
implementation "androidx.room:room-runtime:2.5.0"
implementation "androidx.room:room-ktx:2.5.0"
kapt "androidx.room:room-compiler:2.5.0"

// Retrofit
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-gson:2.9.0"

// Coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"

// Testing
testImplementation "junit:junit:4.13.2"
testImplementation "io.mockk:mockk:1.12.0"
testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1"
testImplementation "androidx.arch.core:core-testing:2.2.0"
```
