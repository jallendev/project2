# project2

## Problem Solving Approach

For this project, I worked mostly from the output sample specified in the project requirements. To accomplish the project requirements I split the project into 5 basic tasks:

 - Read the file, Mesonet.txt.
 - Calculate the Average ASCII value of a given station ID.
 - Convert this average to its char representation.
 - Find the stations adjacent to the given station ID.
 - Find the stations beginning with a given letter.

These helper methods are initiated from the constructor, and implemented in MesoInherit. The other classes can then call the helper methods in MesoInherit in order to access the protected Mesonet array data.

## Description of Methods and Variables

### MesoInherit

### Variables

- I used a variety of const variables to avoid unneccessary use of "magic" data.
- stations: a String[] to hold all station IDs read from the file.
- stID: a String to hold the selected ID to use for processing.

### Methods

#### public MesoInherit(MesoStation stn)

Constructor for MesoInherit. Sets the stID and reads the Mesonet.txt file with the use of helper methods. MesoStation is given by the Driver class.

#### public int[] calAverage()

Calculates the average ASCII value of the given station ID. Returns an int array with the ceil of the average in [0], the floor of the average in [1], and the rounded value of the average in [2].

#### public char letterAverage()

Converts the returned average of calAverage() to its char representation.

#### public int getIndex()

Finds the index of the station ID in the Mesonet.txt file.

#### public String[] getAvgStations(int index)

Find the four stations adjacent to the station at the given index

#### public int getNoStationsForLetter(char letter)

Reads the stations array to find the number of stations beginning with the given letter.

#### public String[] getStationsForLetter(char letter)

Creates a list with all stations beginning with the given letter.

#### private void readFile()

Reads the Mesonet.txt file and inputs the results into the stations array. See code comments for detailed implementation explanation. Basic summary:

 - Uses try-catch block to mitigate IOExceptions thrown.
 - Reads file utilizing BufferedReader object.
 - Uses Scanner to acquire the first token from each line.
 - Begins reading upon finding the STID header.

#### private String[] expandStations()

Expands the stations array with 10 extra slots. Returns the expanded array with values copied from the stations array.

### LetterAvg

### Variables

- letter: holds the letter to search for in MesoInherit.getStationsForLetter(letter).

### Methods

#### public LetterAvg(char letter)

Initializes the letter char with the given letter.

#### public int numberOfStationWithLetterAvg()

Returns the int from the MesoInherit.getNoStationsForLetter(letter) helper method.

#### public String toString()

Returns a string in the format:
<code> They are: (all stations beginning with given char letter) </code>

### PosAvg

### Variables

- stID: the station to use for processing
- index: an integer to hold the stations position in the Mesonet.txt file
- avgStations: a String[] to hold the 4 adjacent stations to this station

### Methods

#### public PosAvg(String stID)

Initializes stID with the given value, and other variables with MesoInherit helper methods.

#### public int indexOfStation()

Returns the index value.

#### public String toString()

Returns a string in the format:
<code> "This index is average of " + avgStations[1] + " and " + avgStations[2] + ", " + avgStations[0] + " and " + avgStations[3] + ", and so on." </code>

## Code Analysis

This implementation is efficient with regards to time, but I do not believe it is as effecient as it could be with regards to system resources. In hindsight I would have liked to make most of the helper methods in MesoInherit static as this could have cut down on the amount of memory needed to run the program. As it is though, the program uses an extremely minimal amount of resources anyway, so this change would likely be only for personal satisfaction with the implementation.
