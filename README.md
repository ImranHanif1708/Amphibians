# Amphibians App

An Android app built with Kotlin, Jetpack Compose, and MVVM architecture that displays a list of amphibians fetched from a remote API.

## Features

- Fetches amphibian data from a REST API using Retrofit and Kotlinx Serialization
- Displays amphibian details in a modern Compose UI
- Handles loading and error states gracefully
- MVVM architecture with a repository pattern

## Tech Stack

- Kotlin
- Jetpack Compose
- Retrofit
- Kotlinx Serialization
- Coroutines
- MVVM Architecture

## Getting Started

### Prerequisites

- Android Studio (Giraffe or newer)
- Kotlin 1.9.x or newer

### Setup

1. Clone the repository:
2.
   git clone https://github.com/yourusername/amphibians.git
4. Open the project in Android Studio.
5. Sync Gradle and build the project.

### Running the App

- Connect an Android device or start an emulator.
- Click **Run** in Android Studio.

## Project Structure

- `data/` - Data models and repository
- `network/` - Retrofit API service
- `ui/` - ViewModel and Compose UI components

## Troubleshooting

- Ensure your `Amphibians` data class is annotated with `@Serializable`.
- Make sure the Kotlin serialization plugin is applied in your `build.gradle`.

## License

This project is licensed under the MIT License.
