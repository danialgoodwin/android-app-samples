# kiosk-mode #

This simple sample app demonstrates how to build an app that is practically impossible to exit. As a precaution the `onBackPressed()` method override is commented out so that exiting can be done easily for testing and demoing. A production version would likely create a way for an admin to exit.

Currently, this doesn't work for Lollipop. Still need to find some better methods of preventing the user from leaving. So, hiding system UI is a temporary solution that doesn't work on all Android versions. If everything else can be made working, then may not need to hide system UI because that system UI comes back is user swipes from top or bottom. Navigation bar is only hidden on KitKat and above.

## Further Resources ##
- Source: [andreas-schrade.de/2015/02/16/android-tutorial-how-to-create-a-kiosk-mode-in-android](http://www.andreas-schrade.de/2015/02/16/android-tutorial-how-to-create-a-kiosk-mode-in-android/)
