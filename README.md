# DrinksDiscovery

App that displays a list of albums and their details.


<img src="https://github.com/sophicapri/DrinksDiscovery/blob/master/screenshots/app_tour3.gif" align="right" width="40%">

Notes
-----

Here are some improvements that could be made:
- Handle procress death with SavedStateHandle in the ViewModel
- Handle when the user is not connected to the internet
- Handle web service potential errors by using Retrofit's Response object
- Test Paging3 in a more efficient way (I haven't found a way to test all the use cases yet for this part)
- Test the web service with MockWebServer
- A more attractive UI ^^

Architecture
--------------

- Feature oriented multi-module architecture
    - app
    - core
        - network
        - ui
    - feature-albums
        - presentation
        - di
        - data
        - api
- MVVM design pattern


Technical Stack
--------------
  * [Navigation][14] 
  * [Kotlin Flow][13]
  * [Paging][7]
  * [Compose][11] 
  * [Retrofit][5]
  * [Hilt][92]
  * [Coil][32] 
  * [Kotlin Coroutines][91] 
  * [Moshi][9]
  * [MockK][20] 
  * [AndroidX][1] 
  * [Android KTX][2] 
 

[1]: https://developer.android.com/jetpack/androidx
[2]: https://developer.android.com/kotlin/ktx
[13]: https://developer.android.com/kotlin/flow
[11]: https://developer.android.com/jetpack/compose
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[30]: https://developer.android.com/guide/topics/ui
[34]: https://developer.android.com/guide/components/fragments
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[92]: https://developer.android.com/training/dependency-injection/hilt-android
[5]: https://github.com/square/retrofit
[7]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[9]: https://github.com/square/moshi
[20]: https://github.com/mockk/mockk
[21]: https://github.com/airbnb/lottie-android
[33]: https://square.github.io/leakcanary/
[32]: https://github.com/coil-kt/coil
