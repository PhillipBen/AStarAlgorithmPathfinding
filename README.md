Video: https://drive.google.com/file/d/1yMkVItOIxVpfvsxdYCPAUWPiphjW9dJ5/view?usp=sharing

Documentation:
	For this classes final, I worked on creating a path-finding program. One of the things I have seen a lot but never had the chance to really work out was how path-finding works. I knew it generally took the start and end point, and calculated a path between them based on generic rules. What I didn’t know, was how those rules worked.
	So in this final, I created a path-finding algorithm using one of the best algorithms I knew about, A*. After researching it, I know now that it uses 2 sets of data: The actual distance between two points, and the heuristic distance between the start and end point. The actual distance between two points is 1. This is simple. But the distance between two diagonal points is actually 1.4, or really 1.414, due to the square root thanks to the hypotenuse. The heuristic distance, is simply the distance between the start and end point. Using these two variables, you can calculate the value of moving to one point vs another. However, there is also a third factor, the accrued value.
 As the distance between the current point and the end point gets closer, the heuristic distance will decrease. This is partially offset by the accrued value, which takes all the previous steps’ values into account. This allows for the total distance value to be accumulated. This is most important when you end up having a large value distance over a short number of squares, vs a multitude of short values over a large number of squares, which add up to overpower the large-value-short-distance value.

Debugging:
One of the hardest issues I actually had with this assignment was getting the pathfinding to work properly. Even after getting the pathfinding to work, a few times it would randomly crash or get stuck on repeat. After a lot of print statements, I finally realized that the part of the code that overwrote old values for specific tiles when a new, better tile value was found, was severely bugged. It took me over an hour just to work out the logic, and I ended up going through multiple parts of the code to fix it. Part of it was due to my lack of initial understanding of how the algorithm truly worked, and the other part was due to the tutorial’s poor explanation of how each step worked, emitting certain steps. 
However, through this, not only did I get an amazing understanding of how each step works to fully perform the pathfinding process, but I was also able to successfully get the pathfinding process to work correctly in many different scenarios. I also added a debug mode just to keep my comments which thoroughly explain the step by step cycle process if one is interested.

Included Concepts:
-	Binary Search Algorithm: AddAStarNodeToSortedList() – Used to add a AStarNode to an already sorted list. Keeps the lowest value at the top, and the next to be accessed by the pathfinding algorithm.
-	Recursion: Used in tandem with the binary search algorithm in AddAStarNodeToSortedList()
-	Data Save/Load: Used in the SaveLoadManager to save and/or load the Grid, which allows for multiple different grids to be saved. I used this myself for testing the grid when certain paths weren’t working. It would’ve been a big pain to have to retype every obstacle in every time before retesting. 
-	Classes / Objects: We technically learned about this right away in class. Used for everything
-	Comments: Used in all classes to explain the classes' main functions.
-	Exception Handling: Used in the SaveLoadManager and the BensonOOPFinal class remove errors before they cause a complete program shut down.
-	JavaFX: Used in start class, BensonOOPFinal, to create multiple buttons that allow for quicker control of the program. 

Sources:
-	https://www.youtube.com/watch?v=kEY1OxOj_CY
-	https://brilliant.org/wiki/a-star-search/
-	https://www.toppr.com/guides/maths/practical-geometry/how-to-calculate-diagonal-distance-between-corners-of-a-square/
