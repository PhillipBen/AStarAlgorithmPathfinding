import java.util.ArrayList;

class AStarNode {
	/*
	The AStarNode is a class that represents on point on the grid that is currently being evaluated in the path-finding process.
	To improve performance, only the looked-at points are added as nodes. 
	- A node has a value, which is the sum of it's accruedValue and it's heuristic value
	- The accruedValue is the value that has been added to it since the startPoint. Each point inherits it's accrued value from it's parent + a new amount.
		This shows the current cost of getting to this node, which offset's the heuristic value
	- The heuristic value is a one time calculation, done in the Grid GetNodeNeighbors() function. 
		It is the total distance in exact squares between the current node's position and the end position.
	- The parent is used to get the node's previous parent. This is used to create a list of nodes from the end pos to the start pos.
	*/
	private double value; //Total current value
	private double accruedValue; //accrued value
	private Vector2 pos;
	private ArrayList<Vector2> pathList = new ArrayList<Vector2>();
	private AStarNode parent; //This is used to link each node to the prev node, together creating the fastest path to the target.

	public AStarNode(int value, Vector2 pos) {
		this.value = value;
		this.pos = pos;
		accruedValue = 0;
		parent = null;
	}

	public AStarNode(double value, Vector2 pos, double accruedValue, AStarNode parent) {
		this.value = value;
		this.pos = pos;
		this.accruedValue = accruedValue;
		this.parent = parent;
	}
	
	public double ReturnValue() {
		return value;
	}
	
	public void SetValue(double value) {
		this.value = value;
	}
	
	public double ReturnAccruedValue() {
		return accruedValue;
	}
	
	public void SetParent(AStarNode parent) {
		this.parent = parent;
	}
	
	public AStarNode GetParent() {
		return parent;
	}
	
	public void SetAccruedValue(double value) {
		this.accruedValue = value;
	}
	
	public Vector2 ReturnPos() {
		return pos;
	}
	
	public void SetPos(Vector2 pos) {
		this.pos = pos;
	}
	
	public void AddPathPoint(Vector2 pos) {
		pathList.add(pos);
	}
	
	public ArrayList<Vector2> ReturnPath() {
		return pathList;
	}
	
    public boolean compareTo(AStarNode o) {
    	//If bigger, return true. Else return false.
        if (value == o.value) {
        	return false;
        }else if (value > o.value) {
        	return true;
        }else {
        	return false;
        }
    }
}