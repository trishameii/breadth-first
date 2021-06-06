/**
 * This class represents a node in the state space graph of the maze. This class
 * provides an easy way to access the x-coordinate part of the state contained
 * in the node, the y-coordinate part of the state contained in the node, the
 * parent node of this node, the f-value of the node, the g-value of the node,
 * and the h-value of the node. You are going to implement the computeHValue()
 * method in this class. The area where you need to insert your code is
 * commented with three successive question marks (???).
 */
public class MazeNode implements Comparable<MazeNode> {
	/**
	 * The field that contains the x-coordinate part of the state contained by
	 * this node.
	 */
	private final int _stateX;

	/**
	 * The field that contains the y-coordinate part of the state contained by
	 * this node.
	 */
	private final int _stateY;

	/**
	 * The field that contains the f-value of this node.
	 */
	private final float _fValue;

	/**
	 * The field that contains the g-value of this node.
	 */
	private final float _gValue;

	/**
	 * The field that contains the h-value of this node.
	 */
	private final float _hValue;

	/**
	 * The field that contains the reference to the parent of this node.
	 */
	private final MazeNode _parent;

	/**
	 * This class constructor takes five parameters. The first parameter is the
	 * x-coordinate part of the state that will be contained in this node, the
	 * second parameter is the y-coordinate part of the state that will be
	 * contained in this node, the third parameter is the reference to the parent
	 * of this node, the fourth parameter is the g-value of this node, and the
	 * last parameter is the h-value of this node. Nothing needs to be done in
	 * this method.
	 * 
	 * @param x
	 *          the x-coordinate part of the state that will be contained in this
	 *          node
	 * @param y
	 *          the y-coordinate part of the state that will be contained in this
	 *          node
	 * @param parent
	 *          the reference to the parent of this node
	 * @param gValue
	 *          the g-value of this node
	 * @param hValue
	 *          the h-value of this node
	 */
	public MazeNode(int x, int y, MazeNode parent, float gValue,
			float hValue) {
		_stateX = x;
		_stateY = y;

		_parent = parent;

		_gValue = gValue;
		_hValue = hValue;
		_fValue = gValue + hValue;

	}

	/**
	 * This class constructor takes four parameters. The first parameter is the
	 * x-coordinate part of the state that will be contained in this node, the
	 * second parameter is the y-coordinate part of the state that will be
	 * contained in this node, the third parameter is the g-value of this node,
	 * and the last parameter is the h-value of this node. The node created by
	 * this constructor has no parent. This constructor is intended to create a
	 * root node. Nothing needs to be done in this method.
	 * 
	 * @param x
	 *          the x-coordinate part of the state that will be contained in this
	 *          node
	 * @param y
	 *          the y-coordinate part of the state that will be contained in this
	 *          node
	 * @param gValue
	 *          the g-value of this node
	 * @param hValue
	 *          the h-value of this node
	 */
	public MazeNode(int x, int y, float gValue, float hValue) {
		_stateX = x;
		_stateY = y;

		_parent = null;

		_gValue = gValue;
		_hValue = hValue;
		_fValue = gValue + hValue;

	}

	/**
	 * This method returns a reference to the parent node of this node. Nothing
	 * needs to be done in this method.
	 * 
	 * @return the reference to the parent node of this node
	 */
	public MazeNode getParent() {
		return _parent;
	}

	/**
	 * This method returns the x-coordinate part of the state contained in this
	 * node. Nothing needs to be done in this method.
	 * 
	 * @return the x-coordinate part of the state contained in this node
	 */
	public int getStateX() {
		return _stateX;
	}

	/**
	 * This method returns the y-coordinate part of the state contained in this
	 * node. Nothing needs to be done in this method.
	 * 
	 * @return the y-coordinate part of the state contained in this node
	 */
	public int getStateY() {
		return _stateY;
	}

	/**
	 * This method returns the h-value of this node. Nothing needs to be done in
	 * this method.
	 * 
	 * @return the h-value of this node
	 */
	public float getHValue() {
		return _hValue;
	}

	/**
	 * This method returns the g-value of this node. Nothing needs to be done in
	 * this method.
	 * 
	 * @return the g-value of this node
	 */
	public float getGValue() {
		return _gValue;
	}

	/**
	 * This method returns the f-value of this node. Nothing needs to be done in
	 * this method.
	 * 
	 * @return the f-value of this node
	 */
	public float getFValue() {
		return _fValue;
	}

	/**
	 * This static method computes the h-value of the given state from the given
	 * goal state. This method implements the heuristic function that your A-star
	 * implementation should use. You can design your admissible heuristic
	 * function to your liking.
	 * 
	 * @param nodeX
	 *          the x-coordinate part of the state to compute the h-value of
	 * @param nodeY
	 *          the y-coordinate part of the state to compute the h-value of
	 * @param goalX
	 *          the x-coordinate part of the goal state to be used in computing
	 *          the h-value
	 * @param goalY
	 *          the y-coordinate part of the goal state to be used in computing
	 *          the h-value
	 * @return the h-value of the given state from the given goal state
	 */
	public static float computeHValue(int nodeX, int nodeY, int goalX, int goalY) {

		//Manhattan distance
		return Math.abs(goalX - nodeX) + Math.abs(goalY - nodeY);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MazeNode o) {
		if (_stateY < o._stateY) {
			return -1;
		} else if (_stateY > o._stateY) {
			return 1;
		} else {
			// y coordinates are equal

			if (_stateX < o._stateX) {
				return -1;
			} else if (_stateX > o._stateX) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return compareTo((MazeNode) obj) == 0;
	}
}
