# TBA-API
A Java API for pulling robotics data from www.thebluealliance.com.

I made this API because none of the other Java ones were working for me. I hope that this
will be the last place you have to go for your TBA API needs. Thanks!

# Installation
Download the .jar file from https://github.com/techguy9984/TBA-API/releases.

Add the jar as a dependency in your project.

# Usage
To use the API, create a TBA object three parameters (id, app description, version):

TBA tba = new TBA("johnsmith","scoutingApp","v1");

Pull data from TBA by calling the respective method within TBA.
For example, to get an event you would do:
Event e = tba.getEvent("casd",2016);

Pulling data will return it's respective *model* such as Event, Team, Match, etc.
Access the model's data by calling the public variable. Example: team.team_number

Not all data will be automatically added. To configure whether to download extra data,
see the *Settings* class and change the booleans statically, such as Settings.GET_EVENT_ALLIANCE = true.

# Other
Report any bugs or suggestions to wdavies973@gmail.com
If you'd like any more functionality as far as ways you can pull data, and what you can pull, let me know and I'll add it right away.
