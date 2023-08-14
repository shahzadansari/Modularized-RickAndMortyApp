# Rick & Morty App ✨

This project demonstrates **Clean Architecure** best practices in a **Multi-Module** Android application using **MVI** architecture pattern. This app is made entirely in Kotlin and consumes pure Kotlin libraries to manage application data **(SqlDelight, Ktor)** and UI **(Jetpack Compose)**.

## Screenshots (Light & Dark Color Schemes)

<img width="345" alt="characters-list" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/46920ca2-7616-4cb6-a6a5-01886b816c50">

<img width="345" alt="character-details" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/e5356755-af20-4a40-9550-1cee56950810">

<img width="345" alt="characters-list-dark" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/94145aa5-e2f2-4fd5-96b4-b45dda8521a0">

<img width="345" alt="characters-details-dark" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/3021ee4e-ae05-4e0f-bad4-a74dea5d1029">



## Dynamic Colors - Android 12+
Dynamic Color enables users to personalize their devices to align tonally with the color scheme of their personal wallpaper or through a selected color in the wallpaper picker. You can read more about Dynamic Colors [here](https://developer.android.com/develop/ui/views/theming/dynamic-colors).

https://github.com/shahzadansari/RickAndMorty/assets/43310446/1ce325e6-265e-4a7a-bb0a-f6e1ff5c36eb

## Architecture Overview

The app architecture has three layers: a [data layer](https://developer.android.com/jetpack/guide/data-layer), a [domain layer](https://developer.android.com/jetpack/guide/domain-layer) and a [UI layer](https://developer.android.com/jetpack/guide/ui-layer). The architecture follows a reactive programming model with [unidirectional data flow](https://developer.android.com/jetpack/guide/ui-layer#udf). With the data layer at the bottom, the key concepts are:
*   Higher layers react to changes in lower layers.
*   Events flow down.
*   Data flows up.

The data flow is achieved using streams, implemented using [Kotlin Flows](https://developer.android.com/kotlin/flow).

<img width="530" alt="architecture-overview" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/4a1c529b-450e-45e0-ab6b-ee434e925a96">

### UI Layer
The UI layer is the pipeline that converts application data changes to a form that the UI can present and then displays it. The state is managed using a [Unidirectional Data Flow (UDF)](https://developer.android.com/topic/architecture/ui-layer#udf) which aligns with the  **MVI (Model View Intent)** architecture pattern's **event-based** nature.

<img width="530" alt="ui-layer-arch" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/f3a89ee9-e3af-4077-9f94-73cc12486f0d">

### Data Layer
While the UI layer contains UI-related state and UI logic, the data layer contains application data and business logic. The business logic is what gives value to your app—it's made of real-world business rules that determine how application data must be created, stored, and changed. The data layer is implemented as an offline-first source of app data and business logic. It is the [source of truth](https://developer.android.com/topic/architecture#single-source-of-truth) for all data in the app.

<img width="667" alt="data-layer-arch" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/3b64e4cc-3d6f-4f37-ba8b-a9ef534bc406">

### Domain Layer
The domain layer is an optional layer that sits between the UI layer and the data layer. The domain layer is responsible for encapsulating complex business logic, or simple business logic that is reused by multiple ViewModels. This layer contains Usecases/Interactors which interact with data sources inside Data Layer.

<img width="540" alt="domain-layer-arch" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/d8db0c33-1d32-4508-b1f6-129046c7095e">

---

**Rick & Morty App** is an [offline-first app](https://developer.android.com/topic/architecture/data-layer/offline-first#:~:text=An%20offline%2Dfirst%20app%20is%20an%20app%20that%20is%20able%20to%20perform%20all%2C%20or%20a%20critical%20subset%20of%20its%20core%20functionality%20without%20access%20to%20the%20internet.%20That%20is%2C%20it%20can%20perform%20some%20or%20all%20of%20its%20business%20logic%20offline.) and the app flow works as:
1. Characters are fetched from [Rick & Morty API](https://rickandmortyapi.com/) using Ktor-client for Android.
2. Characters are then stored in a SqlDelight Database. Our database inside Data layer is our single source of truth.
3. The stored characters in cache are then requested by the Usecase in our domain layer.
4. Characters from the cache are then exposed to the UI layer where they can be accessed as observable data streams i.e. Kotlin Flows.

This app has two screens:
1. **Characters List Screen**
2. **Character Details Screen**

## Characters List Screen Architecture

<img width="530" alt="characters-list-arch" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/9cdd1ee9-9d0d-4f56-a593-04abc2be858e">

## Character Details Screen Architecture

<img width="530" alt="character-details-arch" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/f2d66f2b-e4f9-4f4a-bef0-2032e517c705">

## Modularization Overview
We have following modules in our project. Among these modules, we have **Android Application** module, **Android Library** modules & pure **Java/Kotlin Library** modules. I have preferred using pure Kotlin libraries in domain & data layers as they can easily be reused in Kotlin Multiplatform projects. Therefore, **SqlDelight** is preferred over **Room** & **Ktor client** is used instead of **Retrofit**.



| Module name                                 | Type                   | Description                                                                                                                          |
| -------------                               | -------------          | -------------                                                                                                                        |
| app                                         | Android Application    | Brings everything together required for the app to function correctly. This includes UI scaffolding and navigation.                  |
| core                                        | Java/Kotlin Library    | Core business models and classes (such as DataState/Result wrapper classes) that are used by multiple modules.                       |
| character-datasource                        | Java/Kotlin Library    | Contains data-sources and private models such as `CharacterDto` & `CharacterEntity` (network and cache) for the characters module.   |
| character-domain                            | Java/Kotlin Library    | Domain models and classes for the characters module i.e. data class `Character`                                                      |
| character-interactors                       | Java/Kotlin Library    | Use-cases for the characters Module.                                                                                                 |
| ui-character-list                           | Android Library        | UI components for the CharactersList screen.                                                                                         |
| ui-character-details                        | Android Library        | UI components for the CharacterDetails screen.                                                                                       |
| components                                  | Android Library        | Common Composables.                                                                                                                  ||



<img width="311" alt="modules" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/8366a9fb-ec19-47f3-9677-ce1d2a8590f7">

<img width="481" alt="modularization-overview" src="https://github.com/shahzadansari/RickAndMorty/assets/43310446/9511a4bb-f0b9-4e55-b194-2c9bf64658d3">

## Tech Stack
* [100% Kotlin](https://kotlinlang.org/)
    + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    + [Kotlin Flow](https://kotlinlang.org/docs/flow.html) - data flow across all app layers, including views
    + [Kotlin Symbol Processing](https://kotlinlang.org/docs/ksp-overview.html) - enable compiler plugins
    + [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - parse [JSON](https://www.json.org/json-en.html)
  * [Ktor client](https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html) - Networking
* [Jetpack](https://developer.android.com/jetpack)
    * [Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit
    * [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) - in-app navigation
  * [SqlDelight](https://cashapp.github.io/sqldelight/1.5.4/) - For local cache
  * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - dependency injection (dependency retrieval)
  * [Coil](https://github.com/coil-kt/coil) - image loading library
* Modern Architecture
  * [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
  * MVI Architecture Pattern
  * [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
    ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    ,[Kotlin Flows](https://kotlinlang.org/docs/flow.html)
    ,[Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
  * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* UI
  * Reactive UI
  * [Jetpack Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit
  * [Material Design 3](https://m3.material.io/) - application design system providing UI components
  * Theme selection
    * [Dark Theme](https://material.io/develop/android/theming/dark) - dark theme for the app (Android 10+)
    * [Dynamic Theming](https://m3.material.io/styles/color/dynamic-color/overview) - use generated, wallpaper-based theme (Android 12+)
    * Theme generated via [Material Theme Builder](https://m3.material.io/theme-builder)
* For Gradle dependency management, **buildSrc** approach is used with Kotlin DSL.

