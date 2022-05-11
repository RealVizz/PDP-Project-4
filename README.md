# Text-Based Adventure Game (Dungeon Game)

It is a program that simulates a typical scenario in an Cave and Treasure Game, where a monsters are reside and it can be killed or can kill..

## Installation

Install JAVA version 11 and JUnit 4 dependencies for the smooth working of the program. 

## List of features
1. The Dungeon is able to be represented on a 2-D grid.
2. There is a path from every cave in the dungeon to every other cave in the dungeon.
3. Each dungeon can be constructed with a degree of interconnectivity. if interconnectivity = 0 then there is exactly one path from every cave in the dungeon to every other cave in the dungeon. Increasing the degree of interconnectivity increases the number of paths between caves.
4. Not all dungeons "Warp" from one side to the other, this can be set during the creation of the Dungeon.
5. One cave is randomly selected as the start and one cave is randomly selected to be the end. The path between the start and the end locations is always at least of length 5 [5 hops].
6. Warping and non-warping dungeons can be created with different degrees of interconnectivity.
7. Supports for at least three types of treasure: diamonds, rubies, and sapphires.
8. Treasures can be added to a specified percentage of caves, the client is able to ask the model to add treasure to 20% of the caves and the model will add a random treasure to at least 20% of the caves in the dungeon.
9. A cave can have more than one treasure.
10. Player always enter the dungeon at the start point.
11. Model can provide a description of the player that, includes a description of what treasure the player has collected, its current location, the treasures available at current location, its possible moves from current location.
12. A Player can move from their current location to some other location.
13. A Player can pick up treasure that is located in their same location.
14. Dungeon is 2d representable.
15. There is always at least 1 Monster in the dungeon located at the end cave. 
16. The number of Monsters can specified on the command line. There is never an Monster at the start.
17. Monster are only in caves and are never found in tunnels. Their caves can also contain treasure or other items.
18. A player can smell the Monster, If 1 monster is 1 step away or more than 1 monsters are 2 step away then player will smell heavy pungent smell or if 1 monster is 2 step away then the player will smell mild pungent smell.
19. If player will enter a cave where there is a alive monster than it will kill the player, but if the monster is slayed 1 time, then it is a 50-50 chance that the player will survive.
20. If monster is slayed 2 times, the monster is dead.
21. Arrows are found in Tunnels and Caves both.
22. The Crooked Arrows are able to travel a distance of 1 to 5, tunnels are not counted in travelled distance, the path of the arrow can bend in Tunnel but not in cave.
23. Exact Distance are required to kill a monster, other wise it will just not reach the monster or will just pass him.
24. Jar file can let player play the game.


## How to run the JAR file
Running the jar file is easy, run command in shell/bash/cmd  --> "java -jar 'Project 4.jar' " and no other arguments are needed. This will generate a game based on user input and let the user play the game interactively.


## How to use the program
The program has several functionalities, Significant of all of its functionality is utilized via an object of Dungeon Class. This object internally governs the entire program as for the user, the inbuilt functionalities are already handled via multiple classes mostly which are hidden to the user.
To use the program, you need to run the jar file and play interactively via command line.


## Example
3 dry run demo files is included in the "/res/TestRuns" folder of the directory.
Each of them have there own showcase purpose.
1. File with name "PlayerPickingItems.txt" shows the player navigating through the dungeon.
2. File with name "PlayerPickingItems.txt" shows the player picking up items like Arrows. 
3. 2. File with name "PlayerPickingItems.txt" shows the player picking up items like Treasures.
4. File with name "MonsterKilledThePlayer.txt" shows a that if a player don't kill a monster than the monster will kill the player.
5. File with name "PlayerKilledMonsterAndWon.txt" shows a that if a player kill a monster than the monster will not kill the player.
6. File with name "PlayerKilledMonsterAndWon.txt" shows a that if a player reaches "END" cave, then he wins and game ends.

## Design/Model  changes
There are 1 version of the design, this version is the latest and final version of it.
In this version, the UML has few new entities to accommodate the changes in requirement of the project over last requirements (Project 3).
The classes are de-segregated and merged in order to achieve this without loosing any practical advantages.


## Assumptions
1. All the values are runtime and random.
2. There is near true randomness.
3. Min path length of 5.

## Limitations
1. As there exist near true randomness, the behavior or program may vary, and it is strongly recommended for it to be used with min number of rows to be 4 and columns to be 8.


## Citations
1. https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
