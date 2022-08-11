# STAR WARS

### 👨‍💻 [Download APK](https://drive.google.com/file/d/12uqeQaPSUW0FYD8_wuU6IIvXUXkXNgX_/view?usp=sharing)

<img src="https://img.shields.io/badge/code_style-standard-brightgreen.svg"> <img src="https://img.shields.io/badge/architecture-MVVM-blue"> 
<img src="https://img.shields.io/badge/minSdk-21-orange"> <img src="https://img.shields.io/badge/targetSdk-32-lightgrey"> 
<img src="https://img.shields.io/badge/version-1-yellow">

A simple android application where user can get to see all the star war characters with thier details like films, dob, etc. It fetches data from [API](https://swapi.dev/), and
shows it in list format. Along with this, there is offline support and pagingation also available.


![WhatsApp Video 2022-08-11 at 2 11 18 PM](https://user-images.githubusercontent.com/54764235/184096528-92304d6e-b954-44d8-ac56-ac4a4302b2e2.gif)



---
## Class Diagram

This diagram contains the flow of architecture used in this application:

![image](https://user-images.githubusercontent.com/54764235/184091689-84d3782a-0385-4720-9e4d-49f830b7b42d.png)


---
## Requirements

For development, you will only need:

* Android Studio
* Android Device or Emulator installed along with Android Studio
* Minimum supported Android SDK


## Getting started

- [Install Android Studio](https://developer.android.com/studio/install.html)
- Download the project 
```bash
 git clone https://github.com/The-Fuse/StarWars.git
```
- Open the project into Android Studio.
- Build the project and run the sample. You may need to update gradle and library versions. 
- Follow the guidance provided by Android Studio. 
- If you still not able to build the project try installing the [APK](https://drive.google.com/file/d/12uqeQaPSUW0FYD8_wuU6IIvXUXkXNgX_/view?usp=sharing) of the applicaiton


## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services..
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - [Safe Args](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) -  Safe Args generates simple object and builder classes for type-safe navigation and access to any associated arguments.
- [Pagination](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - Used Paging 3 to give the support of paginatioin
- [Room](https://developer.android.com/training/data-storage/room) - For offline support in app
- [Retrofit](https://square.github.io/retrofit/) - For making networking request to api





