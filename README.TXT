***README***


PROJECT TITLE: Tower Simulator

PURPOSE OF PROJECT: Generate and simulate an apartment or hotel building

VERSION or DATE: V. 0.5 5/14/2015

DEPENDENCIES: Just about everything in the project depends on everything else, the Resident class is the only one that can be independant

HOW TO START THIS PROJECT: Run the main method of the "Viewer" class, no further input is required for operation

AUTHORS: Ben Mathews


--USER INSTRUCTIONS--

This can be considered largely unfinished, but it will function in the state that it is in. With that said, most things are undocumented, so in order
to test different situations, modification of the instance variables within the "Viewer" class will suffice. All of those are documented to some degree,
so it should be explained as to what they do. I would recommend avoiding the other classes, as how they interact with one another is not clearly stated.

When the program runs two windows are opened, but it may not be obvious as one hides behind the other. The upper window (labeled as Tower) is the tower view, and the 
lower window behind (labeled as Resident Lives) it is a log of all the residents in the tower - specifically their actions at a given point in time, along with their color, making
it possible to focus on one resident if you wish.

The log or "ResidentReadout" window does not adjust itself to how many residents are in the building, so if the size of the tower bumped up to a very
high number, some of the residents may not be visible in the log. The Resident Lives window is resizeable to help combat this issue, but it will only resize
as tall as the monitor it is being viewed on is.

Once the program is running, there is not much to do afterward, it's mainly a way to monitor resident behavior, or make some cool designs if you're into
that sort of thing.

--IMPORTANT THINGS TO ADD--

TOWER GROWTH: Already partially implemented - the issue is resizing the rooms to fit the window better, but the tower can grow without issue currently.

SCROLLABLE RESIDENT LOG: Requires a simple MouseWheelListener to be implemented in the ResidentReadout class - should not take that long.