# TIPpicture

Here your find the TIP picture program. It is a program/GUI to create your own TipToi game which can be played with the Ravensburger tiptoi pen. 
Note that this is currently the first version, in case of problems or concerns raise an issue. Due to COVID-19 the created game
could only tested partly as my printer couldn't print it (STAY HOME). 


## The game ##

The game makes it possible to make photos interact with the pen. In more detail it provides information about the people in the photo as well as games.

The game has 4 different modes:

### name normal  ###
When clicking a person for the first time their name will be said. If a person is clicked another time a feature will be said.

### name game ###
The pen will ask the learner to find a person with a certain name, for example "Please look for Catra". 
The player can then click on the person and gets feedback from the pen.

### feature game ##

As in the name game the pen will ask to find a person with a certain feature, for example
"feature game" to whom a feature belongs, for example "Who am I looking for? The person in question likes dogs".

## description mode ##
This mode is somewhat special as it is not intended as a game but more as an aid for visually impaired. Note that this is my own
try and might not represent ideas or thoughts of them, further I wasn't able to consult or test a person of that group. 

The mode begins with a description of the whole picture. 
When after this a person is clicked a description of that person, their name or a feature is given at random.


## The GUI ##

The GUI runs on JAVA I advise to use version 1.8. The GUI will ask you to provide information about the photo you want to convert into a game.
Note that for every new photo the GUI needs to be executed again. Further to create a file which can be loaded and read by the tiptoi pen 
the \tttool by Joachim Breitner is needed (https://github.com/entropia/tip-toi-reveng)

