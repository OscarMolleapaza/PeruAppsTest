<h1 align="center">PeruApps Technical Test</h1>

<p align="center">
Technical Test Android
</p>
</br>

<p align="center">
<img src="/assets/screenshot.png"/>
</p>

## Download
Go to the [Link](https://drive.google.com/file/d/1GwWbHszwJcempvpCFNP4iYtxmbYXv_SB/view?usp=sharing) to download the APK.


## Tech stack & Open-source libraries

- [Kotlin](https://kotlinlang.org/) based
- [Koin](https://insert-koin.io/) for dependency injection.
- Jetpack
  - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - DataBinding - Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - Room Persistence - Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository Pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Material-Components](https://github.com/material-components/material-components-android)


<img src="/assets/architecture.JPG" align="right" width="32%"/>

## Architecture
Is based on the MVVM architecture and the Repository pattern.

