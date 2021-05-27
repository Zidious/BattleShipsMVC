# BattleShipsMVC

**W3007 - Advanced Object-Orientated Programming**

*For this coursework, you will produce two versions of the one-player game of Battleships . One version will have a Graphical User Interface (GUI) and the other version will have a command-line interface (CLI). The GUI version will be constructed according to the principles of Model View Controller, and the CLI version will use the same model. The two versions will from now on be called the GUI version and the CLI version.*

## Functional & Non-Functional Requirements

<p align="center">
  <img width="500" height="500" src="https://github.com/Zidious/BattleShipsMVC/blob/master/src/FRandNFR.png">
</p>

## My Design

- 2D Array of integers for the gameboard
- Each type of ship has been given a int constant to identify itself on the board i.e. 5 = Battleship, 4 = Carrier etc
- Each ship type is a sub-class of Ship, Ship holds the hit coordinates within an array lists and carries out various checks
- Win condition is checked if all the ships have been sunk via the sunk indicator
- Various jUnit tests have been completed; ship has been hit, miss and ships are placed on the 2D board

## Functional & Non-Functional Requirements Achieved 

- [x] FR1
- [x] FR2
- [x] FR3
- [x] FR4
- [ ] FR5 (with file upload intersecting has not been completed)
- [x] FR6
- [x] FR7
- [x] FR8
- [x] NFR1
- [x] NFR2
- [x] NFR3
- [x] NFR4

