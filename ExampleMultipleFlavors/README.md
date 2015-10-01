# ExampleMultipleFlavors #

This example app provides a quick and simple full example for the following:
- 1.0. Setting up build.gradle to use be able to handle multiple version/flavors of an app.
- 2.0. How to access build.gradle in AndroidManifest.
- 3.0. How to access build.gradle and AndroidManifest info in the Java files.
- 4.0. How to setup the project structure so that certain files only appear in certain flavors.
- 5.0. How to setup build.gradle for handling keystore signing.
- 6.0. How to access AndroidManifest meta-data in the Java files.


## 1.0. Setting up build.gradle to use be able to handle multiple version/flavors of an app ##
- File needed:
  - /app/build.gradle


## 2.0. How to access build.gradle in AndroidManifest ##
- Files needed:
  - /app/main/AndroidManifest.xml
  - /app/build.gradle

In the manifest, you can dynamically add values from build.gradle by using something like "${buildGradleProperty}".


## 3.0. How to access build.gradle and AndroidManifest info in the Java files ##
- Files needed:
  - /app/main/AndroidManifest.xml
  - /app/build.gradle
  - /app/main/java/net.simplyadvanced.examplemultipleflavors/MainActivity.java

In MainActivity.java, there are helper methods that should how to get the values.


## 4.0. How to setup the project structure so that certain files only appear in certain flavors ##
In the /app/src/ directory, there will be a "main" directory for files that are common to all of
the flavors. Then, there is a "pro" directory to match with the productFlavor of pro. Then, there
is a "free" directory to match with the productFlavor of free. The name of the directories should
match the names of the flavors. To create the directory, just right-click on /src/ and choose New->
Directory. Then, setup up the internals to match the file structure of the main directory. For the
build variants, the different directories will be merged together, so during development, you can
treat things like they are in the same/main directory.

Only one type of build variant can be active at once (where Shift+F10 runs that build). To change
between which is active, open View->Tool Windows->Build Variants, then click around to see the
different things that you can change.


## 5.0. How to setup build.gradle for handling keystore signing ##
- Files needed:
  - /app/build.gradle
  - /app/gradle.properties

The gradle.properties info is automatically included in build.gradle, which means that the
properties can be directly accessed.

To make sure the passwords stay safe, add the `gradle.properties` files to the `.gitignore` file. Then, if the `gradle.properties` file is already included in the repo, run `git rm --cached gradle.properies` to make sure the file isn't committed anymore.

Optionally, create an easy template for others to follow also. Ex: `gradle.properties.template`

    STORE_FILE=/path/to/your/keystore.release
    STORE_PASSWORD=password
    KEY_ALIAS=key_name
    KEY_PASSWORD=password
    OUTPUT_DIR=


## 6.0. How to access AndroidManifest meta-data in the Java files ##
- Files needed:
  - /app/main/AndroidManifest.xml
  - /app/main/java/net.simplyadvanced.examplemultipleflavors/MainActivity.java

There is a meta-data tag added in the AndroidManifest, which is accessed via a helper method in MainActivity.getExampleMetaData().

