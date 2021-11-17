# Technology used
* [Jetpack Compose](https://developer.android.com/jetpack/compose)
* [Android Databinding](https://developer.android.com/topic/libraries/data-binding/index.html)
* [Android View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Koin for Dependency Injection](https://insert-koin.io)
* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Apollo](https://www.apollographql.com/docs/android/)
* [Mockk.io](https://mockk.io)
* [Dynamic Features](https://proandroiddev.com/developing-your-own-dynamic-feature-3c48378e3065)
* [Modularization](https://medium.com/ne-digital/a-cleaner-way-to-modular-architecture-in-android-2608795f09b6)


# modules has sample unittests:
* :artist/
* :core/

# known issues

* i could not apply plugin modules by using constants. i guess somethings has changed with latest gradle version. i have tried to find a solution about that but could not success for yetr.
* when we use koin for dependency injection unfortnuetly we must load related modules when we open any dynamic feature. as i know koin developers are still working on this issue.
* could not write module level repos unittest due to i had implemented it **untestable** when i developed it. today i fixed this problem and these repos are **testable** but i could not apply unit testing due to i am in shifting time.









