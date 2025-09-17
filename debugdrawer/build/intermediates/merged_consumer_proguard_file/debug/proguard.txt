# Consumer ProGuard rules for the debugdrawer library
# These rules will be applied to the consuming app when the library is used

# Keep debug drawer classes
-keep class com.debugdrawer.** { *; }

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep Retrofit classes
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }

# Keep DataStore classes
-keep class androidx.datastore.** { *; }
