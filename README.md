# TBA-API
A Java API for pulling robotics data from www.thebluealliance.com.

I made this API because none of the other Java ones were working for me. I hope that this
will be the last place you have to go for your TBA API needs. Thanks!

# Installation
Download the .jar file from https://github.com/techguy9984/TBA-API/releases.

Add the jar as a dependency in your project.

# Usage
To use the API, first, set the ID statically with three variables:  
```java
TBA.setID("id","description","version");
```
Then, create a TBA object:  
```java
TBA tba = new TBA();
```java
Pull data from TBA by calling the respective method within TBA.
For example, to get an event you would do:
```java
Event e = tba.getEvent("casd",2016);
```

Pulling data will return it's respective *model* such as Event, Team, Match, etc.
Access the model's data by calling the public variable. Example: 
```java 
team.team_number
```

Not all data will be automatically added. To configure whether to download extra data,
see the *Settings* class and change the booleans statically, such as 
```java 
Settings.GET_EVENT_ALLIANCE = true; // Gets the alliance picks and declines in the event.
Settings.FIND_TEAM_RANKINGS = true; // Sorts the Event.Teams[] array by the team's rank within the event. Fills out 8 more variables in the Team model.
Settings.GET_EVENT_MATCHES = true; // Whether to get matches when pulling an event.
Settings.GET_EVENT_TEAMS = true; // Whether to get teams when pulling an event. 
Settings.GET_EVENT_AWARDS = true; // Whether to get awards won when pulling an event.
```

# Other
Report any bugs or suggestions to wdavies973@gmail.com
If you'd like any more functionality as far as ways you can pull data, and what you can pull, let me know and I'll add it right away.

# Roblu
This API is using in my scouting app Roblu. It's an all-in-one solution to scouting.
Check it out at: https://github.com/techguy9984/Roblu
