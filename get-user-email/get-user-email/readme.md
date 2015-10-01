This sample project shows how easy it is to get the user's email address with great UX already provided by Android.


1. In AndroidManifest.xml, add `<uses-permission android:name="ANDROID.PERMISSION.GET_ACCOUNTS"/>`
2. In the project's build.gradle, add dependency `compile 'com.google.android.gms:play-services:8.1.0'` (or similar version)
3. Take a look in 'com.danialgoodwin.get_user_email.MainActivity' for the rest of the code


## Further Resources ##
- [SO: Getting Android owner's email address nicely](http://stackoverflow.com/questions/6502017/getting-android-owners-email-address-nicely)
- [SO: How to get the Android device's primary e-mail address](http://stackoverflow.com/questions/2112965/how-to-get-the-android-devices-primary-e-mail-address)