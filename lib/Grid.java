import java.util.ArrayList;
import java.io.Serializable;

class Grid implements Serializable{
	
	/**
	The grid is used to hold all nodes and calculate the path-finding using the A* algorithm.
	- All nodes are virtual, except for the obstacleList, since those are user-input.
	
	A* Algorithm:
	- The A* algorithm is one of the best path-finding algorithms to calculate a path from start to end, including possible obstacles.
	- It starts at the start point and works it's way to the end, using the accrued value from previous nodes + the heuristic value, or the total distance left to travel to get to it's destination.
	- It removes all points already traveled to, but can overwrite and bring back old points if better values are found.
	- Through these abilities, it will create a quick path forwards when low values are found (low values are better), 
		but slowly puff out in a circle when all similar values are found.
		This allows it to quickly attempt the best path, and if no best path is found, slowly expand it's surroundings until another good path is found.
		You can experience this by turning on debug mode and trying some of my grid scenarios.
	 */

	private Vector2 size;
	private ArrayList<Vector2> obstacleList = new ArrayList<Vector2>();
	private Vector2 gridStartPoint = new Vector2();
	private Vector2 gridEndPoint = new Vector2();
	
	public Grid(int x, int y) {
		size = new Vector2(x, y);
	}
	
	public void AddStartPoint(int x, int y) {
		if (InBoundsTF(x, y))
			gridStartPoint = new Vector2(x, y);
		else
			System.out.println("Out of bounds!");
	}
	
	public void AddEndPoint(int x, int y) {
		if (InBoundsTF(x, y))
			gridEndPoint = new Vector2(x, y);
		else
			System.out.println("Out of bounds!");
	}
	
	public void AddObstacle(int x, int y) {
		if (InBoundsTF(x, y))
			obstacleList.add(new Vector2(x, y));
		else
			System.out.println("Out of bounds!");
	}
	
	public boolean InBoundsTF(int x, int y) {
		if (x >= 0 && x < size.xI() && y >= 0 && y < size.yI())
			return true;
		else
			return false;
	}
	
	public void RemoveObstacle(int x, int y) {
		for (int i = 0; i < obstacleList.size(); i++) {
			if (obstacleList.get(i).xI() == x && obstacleList.get(i).yI() == y) {
				obstacleList.remove(i);
				break;
			}
		}
	}
	
	public void PrintGrid() {
		for (int j = 0; j < size.xI(); j++) {
			System.out.print("----");
		}
		System.out.println("");
		for (int i = size.yI() - 1; i >= 0 ; i--) {
			System.out.printf("%3s", i); 
			System.out.print("|");
			
			for (int j = 0; j < size.xI(); j++) {
				if (ObstacleInList(j, i)) {
					System.out.print(" X |");
				} else if (j == gridStartPoint.xI() && i == gridStartPoint.yI()) {
					System.out.print("STA|");
				} else if (j == gridEndPoint.xI() && i == gridEndPoint.yI()) {
					System.out.print("END|");
				} else {
					System.out.print("   |");
				}
			}
			System.out.println("");
			for (int j = 0; j <= size.xI(); j++) {
				System.out.print("----");
			}
			System.out.println("");
		}
		for (int j = 0; j <= size.xI() ; j++) {
			System.out.printf("%3s", j - 1); 
			System.out.print("|");
		}
		System.out.println("");
		System.out.println("");
	}
	
	public void PrintGrid(ArrayList<AStarNode> path) {
		for (int j = 0; j < size.xI(); j++) {
			System.out.print("----");
		}
		System.out.println("");
		for (int i = size.yI() - 1; i >= 0 ; i--) {
			System.out.printf("%3s", i); 
			System.out.print("|");
			
			for (int j = 0; j < size.xI(); j++) {
				if (ObstacleInList(j, i)) {
					System.out.print(" X |");
				} else if (AStarNodeAtPos(new Vector2(j, i), path)) {
					System.out.print("+++|");
				} else if (j == gridStartPoint.xI() && i == gridStartPoint.yI()) {
					System.out.print("STA|");
				} else if (j == gridEndPoint.xI() && i == gridEndPoint.yI()) {
					System.out.print("END|");
				} else {
					System.out.print("   |");
				}
			}
			System.out.println("");
			for (int j = 0; j <= size.xI(); j++) {
				System.out.print("----");
			}
			System.out.println("");
		}
		for (int j = 0; j <= size.xI() ; j++) {
			System.out.printf("%3s", j - 1); 
			System.out.print("|");
		}
		System.out.println("");
		System.out.println("");
	}
	
	public boolean AStarNodeAtPos(Vector2 pos, ArrayList<AStarNode> list) {
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i).ReturnPos().xI() == pos.xI() && list.get(i).ReturnPos().yI() == pos.yI()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean ObstacleInList(int x, int y) {
		for (int i  = 0; i < obstacleList.size(); i++) {
			if (obstacleList.get(i).xI() == x && obstacleList.get(i).yI() == y) {
				return true;
			}
		}
		return false;
	}
	
	public boolean ObstacleInList(Vector2 pos) {
		for (int i  = 0; i < obstacleList.size(); i++) {
			if (obstacleList.get(i).xI() == pos.xI() && obstacleList.get(i).yI() == pos.yI()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean AStarNodeInList(AStarNode a, ArrayList<AStarNode> list) {
		for (int i  = 0; i < list.size(); i++) {
			if (list.get(i).ReturnPos().xI() == a.ReturnPos().xI() && list.get(i).ReturnPos().yI() == a.ReturnPos().yI()) {
				return true;
			}
		}
		return false;
	}
	
	public int GetAStarNodeInList(AStarNode a, ArrayList<AStarNode> list) {
		for (int i  = 0; i < list.size(); i++) {
			if (list.get(i).ReturnPos().xI() == a.ReturnPos().xI() && list.get(i).ReturnPos().yI() == a.ReturnPos().yI()) {
				return i;
			}
		}
		return -1;
	}
	public void AddAStarNodeToSortedList(AStarNode item, ArrayList<AStarNode> list) {
		//Uses the binary search algorithm.
		//Used to add a AStarNode to an already sorted list. Keeps the lowest value at the top, and the next to be accessed by the path-finding algorithm.
		if (list.size() != 0) {
			int leftIndex = 0;
		    int rightIndex = list.size() - 1;
		    list.add(AddAStarNodeToSortedList(item, list, leftIndex, rightIndex), item);
		}else {
			list.add(item);
		}
	}
	
	public int AddAStarNodeToSortedList(AStarNode item, ArrayList<AStarNode> list, int leftIndex, int rightIndex) {
		if (leftIndex > rightIndex)  
		      return leftIndex;
		if (rightIndex < 0)
			return rightIndex;

	    int mid = (leftIndex + rightIndex) / 2;
	    if (item.ReturnValue() < list.get(mid).ReturnValue()) //if (item.compareNumericTo(list.get(mid)) < 0)
	      return AddAStarNodeToSortedList(item, list, leftIndex, mid - 1);
	    else if (item.ReturnValue() == list.get(mid).ReturnValue())
	      return mid;
	    else
	      return AddAStarNodeToSortedList(item, list, mid + 1, rightIndex);
	}
	
	public void RemoveAStarNodeFromSortedList(AStarNode a, ArrayList<AStarNode> list) {
		for (int i = 0; i < list.size(); i++) { //Simple alg cause I'm lazy
			if (a.ReturnPos().xI() == list.get(i).ReturnPos().xI() && a.ReturnPos().yI() == list.get(i).ReturnPos().yI()) {
				list.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<AStarNode> GetNodeNeighbors(AStarNode origNode, Vector2 targetPos) {
		//Based off 2D grid
		Vector2 currentPos = origNode.ReturnPos();
		ArrayList<AStarNode> returnList = new ArrayList<AStarNode>();
		double accruedValue = origNode.ReturnAccruedValue() + 1;
		double accruedDiagValue = origNode.ReturnAccruedValue() + 1.4; //https://www.toppr.com/guides/maths/practical-geometry/how-to-calculate-diagonal-distance-between-corners-of-a-square/
		
		//##### Beg of Corner Positions #####
		Vector2 leftPos = new Vector2(currentPos.xI() - 1, currentPos.yI());
		if (leftPos.xI() >= 0 && leftPos.xI() < size.xI() && leftPos.yI() >= 0 && leftPos.yI() < size.yI()) {
			//If Vector2 in bounds
			returnList.add(new AStarNode(accruedValue + Math.abs(leftPos.xI() - targetPos.xI()) + Math.abs(leftPos.yI() - targetPos.yI()), leftPos, accruedValue, origNode));
			//Right here is the heuristic calculation. Simple box distance is the calculating value.
		}
		
		Vector2 rightPos = new Vector2(currentPos.xI() + 1, currentPos.yI());
		if (rightPos.xI() >= 0 && rightPos.xI() < size.xI() && rightPos.yI() >= 0 && rightPos.yI() < size.yI()) {
			returnList.add(new AStarNode(accruedValue + Math.abs(rightPos.xI() - targetPos.xI()) + Math.abs(rightPos.yI() - targetPos.yI()), rightPos, accruedValue, origNode));
		}
		
		Vector2 bottomPos = new Vector2(currentPos.xI(), currentPos.yI() - 1);
		if (bottomPos.xI() >= 0 && bottomPos.xI() < size.xI() && bottomPos.yI() >= 0 && bottomPos.yI() < size.yI()) {
			returnList.add(new AStarNode(accruedValue + Math.abs(bottomPos.xI() - targetPos.xI()) + Math.abs(bottomPos.yI() - targetPos.yI()), bottomPos, accruedValue, origNode));
		}
		
		Vector2 topPos = new Vector2(currentPos.xI(), currentPos.yI() + 1);
		if (topPos.xI() >= 0 && topPos.xI() < size.xI() && topPos.yI() >= 0 && topPos.yI() < size.yI()) {
			returnList.add(new AStarNode(accruedValue + Math.abs(topPos.xI() - targetPos.xI()) + Math.abs(topPos.yI() - targetPos.yI()), topPos, accruedValue, origNode));
		}
		//##### End of Corner Positions #####
		
		//##### Beg of Diagonal Positions #####
		Vector2 leftDiagPos = new Vector2(currentPos.xI() - 1, currentPos.yI() - 1);
		if (leftDiagPos.xI() >= 0 && leftDiagPos.xI() < size.xI() && leftDiagPos.yI() >= 0 && leftDiagPos.yI() < size.yI()) {
			returnList.add(new AStarNode(accruedDiagValue + Math.abs(leftDiagPos.xI() - targetPos.xI()) + Math.abs(leftDiagPos.yI() - targetPos.yI()), leftDiagPos, accruedDiagValue, origNode));
		}

		Vector2 rightDiagPos = new Vector2(currentPos.xI() + 1, currentPos.yI() - 1);
		if (rightDiagPos.xI() >= 0 && rightDiagPos.xI() < size.xI() && rightDiagPos.yI() >= 0 && rightDiagPos.yI() < size.yI()) {
			returnList.add(new AStarNode(accruedDiagValue + Math.abs(rightDiagPos.xI() - targetPos.xI()) + Math.abs(rightDiagPos.yI() - targetPos.yI()), rightDiagPos, accruedDiagValue, origNode));
		}

		Vector2 downLeftDiagPos = new Vector2(currentPos.xI() - 1, currentPos.yI() + 1);
		if (downLeftDiagPos.xI() >= 0 && downLeftDiagPos.xI() < size.xI() && downLeftDiagPos.yI() >= 0 && downLeftDiagPos.yI() < size.yI()) {
			returnList.add(new AStarNode(accruedDiagValue + Math.abs(downLeftDiagPos.xI() - targetPos.xI()) + Math.abs(downLeftDiagPos.yI() - targetPos.yI()), downLeftDiagPos, accruedDiagValue, origNode));
		}

		Vector2 downRightDiagPos = new Vector2(currentPos.xI() + 1, currentPos.yI() + 1);
		if (downRightDiagPos.xI() >= 0 && downRightDiagPos.xI() < size.xI() && downRightDiagPos.yI() >= 0 && downRightDiagPos.yI() < size.yI()) {
			returnList.add(new AStarNode(accruedDiagValue + Math.abs(downRightDiagPos.xI() - targetPos.xI()) + Math.abs(downRightDiagPos.yI() - targetPos.yI()), downRightDiagPos, accruedDiagValue, origNode));
		}
		return returnList;
	}
	
	public ArrayList<AStarNode> AStarSearch(boolean debugModeTF) {
		//List and initial node initialization
		ArrayList<AStarNode> openList = new ArrayList<AStarNode>();
		ArrayList<AStarNode> closedList = new ArrayList<AStarNode>();
		AStarNode startingNode = new AStarNode(Math.abs(gridStartPoint.xI() - gridEndPoint.xI()) + Math.abs(gridStartPoint.yI() - gridEndPoint.yI()), gridStartPoint);
		openList.add(startingNode);
		boolean closestNodeFoundTF = false;
		
		
		int index = 0; 
		while(!closestNodeFoundTF) {
			if (debugModeTF)
				System.out.println("Cycle: " + index);
			if (openList.size() > 0) {
				AStarNode currentNode = openList.get(0);
				if (currentNode.ReturnPos().compareTo(gridEndPoint)) {
					//Target Point Found
					if (debugModeTF)
						System.out.println("Target Point: " + gridEndPoint.xI() + gridEndPoint.yI());
					closestNodeFoundTF = true;
				}else {
					if (debugModeTF) {
						//All current openList items
						for (int i = 0; i < openList.size(); i++) {
							System.out.println("OL: " + openList.get(i).ReturnPos().xI() + " " + openList.get(i).ReturnPos().yI() + " " + openList.get(i).ReturnValue());
						}
					}
					
					//Add the lowest value node (Top of list) to the closedList, and remove from the openList
					AddAStarNodeToSortedList(currentNode, closedList);
					RemoveAStarNodeFromSortedList(currentNode, openList);
					
					//Then, using that same node, find all neighbors that are within the map bounds
					ArrayList<AStarNode> neighborList = GetNodeNeighbors(currentNode, gridEndPoint);

					if (debugModeTF) {
						//Print all neigbors
						for (int i = 0; i < neighborList.size(); i++) {
							System.out.println("NL: " + neighborList.get(i).ReturnPos().xI() + " " + neighborList.get(i).ReturnPos().yI() + "     " + neighborList.get(i).ReturnValue());
						}
					}
					
					for (AStarNode neighbor : neighborList) {
						if (!ObstacleInList(neighbor.ReturnPos())) {
							//If there is no obstacle at that pos
							if (AStarNodeInList(neighbor, closedList)) {
								//If neighbor is the same as a node in the closed list, but has a lower value, replace the value in the closed list neighbor, then return that node to the open list, as it should be available to check again. Thus remove it from the closed list.
								int neighbor2Index = GetAStarNodeInList(neighbor, closedList);
								if (neighbor.ReturnValue() < closedList.get(neighbor2Index).ReturnValue()) {
									if (debugModeTF)
										System.out.println("Opt 1");
									closedList.get(neighbor2Index).SetValue(neighbor.ReturnValue());
									closedList.get(neighbor2Index).SetAccruedValue(neighbor.ReturnAccruedValue());
									closedList.get(neighbor2Index).SetParent(currentNode);
									AddAStarNodeToSortedList(closedList.get(neighbor2Index), openList);
									closedList.remove(neighbor2Index);
								}
							}else if(AStarNodeInList(neighbor, openList)) {
								//If the neighbor already exists in the openList, but with a higher value, replace that with a lower value and it's new corresponding parent.
								int neighbor2Index = GetAStarNodeInList(neighbor, openList);
								if (neighbor.ReturnValue() < openList.get(neighbor2Index).ReturnValue()) {
									if (debugModeTF)
										System.out.println("Opt 2");
									openList.get(neighbor2Index).SetAccruedValue(neighbor.ReturnAccruedValue());
									openList.get(neighbor2Index).SetValue(neighbor.ReturnValue());
									openList.get(neighbor2Index).SetParent(currentNode);
								}
							}else if(!AStarNodeInList(neighbor, closedList) && !AStarNodeInList(neighbor, openList)) {
								//If not in either list, add to openList
								AddAStarNodeToSortedList(neighbor, openList);
							}
						}
					}
				}
				//Index used only for debug purposes (Cycle number).
				index += 1;
			} else {
				System.out.println("No available path");
				break;
			}
		}
		
		//Calculate and return path by starting with the final node and working it's way back, selecting each parent and adding them to the path list, until it reaches the start node.
		if (closestNodeFoundTF) {
			AStarNode endNode = openList.get(0);
			ArrayList<AStarNode> path = new ArrayList<AStarNode>();
			path.add(endNode);
			AStarNode nextNode = endNode.GetParent();
			path.add(nextNode);
			if (nextNode != null) {
				if (debugModeTF)
					System.out.println("Getting Next Node" + nextNode.ReturnPos().xI() + " " + nextNode.ReturnPos().yI()); //For debugging
				while(nextNode.GetParent() != null) {
					nextNode = nextNode.GetParent();
					path.add(nextNode);
					if (debugModeTF)
						System.out.println("Getting Next Node" + nextNode.ReturnPos().xI() + " " + nextNode.ReturnPos().yI()); //For debugging
				}
			}
			return path;
		}else {
			return null;
		}
	}
	
}