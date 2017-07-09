# V2 is Deprecated
Check out the V3 library here: https://github.com/wdavies973/TBA-API-V3. 

# TBA-API
A Java API for pulling robotics data from www.thebluealliance.com.

I made this API because none of the other Java APIs were working for me. I hope that this
will be the last place you have to go for your TBA API needs. Thanks!

# Installation
Download the .jar file from https://github.com/techguy9984/TBA-API/releases.

Add the jar as a dependency in your project. Let me know if you'd like Maven or Gradle download support, although currently,
it seems like it's easiest just to use a .jar.

# Dependencies
This API requires the use of JSON-Simple. Make sure you download the .jar file at https://code.google.com/archive/p/json-simple/ and add the jar as a dependency as well.

# Android troubleshooting
Make sure you have internet permissions declared in the manifest:  
```java
<uses-permission android:name="android.permission.INTERNET"/> 
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 ```

Make sure you run this line of code before calling any API commands:  
```java
StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build(); StrictMode.setThreadPolicy(policy);
```

# Tutorial
Find it at https://www.github.com/techguy9984/TBA-API/wiki.
The API is designed to be easy to use and fairly idiot-proof (no offense, I wish everyone designed their software like that).

# Other
Report any bugs or suggestions to wdavies973@gmail.com
If you'd like any more functionality as far as ways you can pull data, and what you can pull, let me know and I'll add it right away.

# Roblu
This API is using in my scouting app Roblu. It's an all-in-one solution to scouting.
Check it out at: https://github.com/techguy9984/Roblu
