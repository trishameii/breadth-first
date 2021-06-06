/**
 * 
 * Complete the stubbed methods in the "Maze.java" and
 * "MazeNode.java" source files. The areas where you need to insert your
 * codes are commented with three successive question marks (???). Read the
 * inline comments to get further information regarding the code elements. You
 * can define auxiliary methods to your liking in this class. 
 * 
 * 
 * To compile and/or run the sources in this homework, include the provided .JAR
 * file as well as the current directory containing the sources in the
 * classpath. This can be done by using the "-cp <classpaths>" option of the
 * "javac" and "java" executables. For example, assuming that the .JAR file and
 * the source files are in the current directory, use the following commands to
 * compile and run the program.
 * 
 * To compile:
 * 
 * javac -cp MazeUI.jar;. Maze.java MazeNode.java
 * 
 * To run:
 * 
 * java -cp MazeUI.jar;. Maze
 */
import java.util.*;

 public class Maze {
	/**
	 * The field that holds the instance of the GUI object that accesses the
	 * instance of this class.
	 */
	protected MazeUI _mazeUI;

	/**
	 * This method implements the searching algorithm of the state space graph of the
	 * maze. The search starts from the starting state of the maze whose node is
	 * returned by the getStartNode() method of the _mazeUI object. A node of the
	 * state space graph is represented by an instance of the MazeNode
	 * class. The GUI will animate the search by drawing the nodes contained in
	 * the closed list. Because of this, the updateGUIState() method of the
	 * _mazeUI object should be invoked every time a new node is added into the
	 * closed list in order for the display of the maze on the GUI to be updated.
	 * 
	 * The closed list is a SortedSet that is stored internally in the _mazeUI
	 * object and can only be accessed via the accessor methods provided in the
	 * _mazeUI object. Almost all accessor methods are functions similar to the
	 * SortedSet methods and the names of these accessor methods have analogous
	 * names in the SortedSet class except for the closedListGet(MazeNode)
	 * method, which is independently defined. The names of the accessor methods
	 * have the form "closedListXXX", where the "XXX" part is the name of the
	 * equivalent method in the underlying SortedSet class. For example, the
	 * closedListIsEmpty() method of the _mazeUI object is equivalent to the
	 * isEmpty() method of the underlying SortedSet class.
	 * 
	 * Aside from the methods of the _mazeUI object mentioned above, you can also
	 * use the hasBarricade(int, int, int, int), getMazeSize(), getGoalStateX(),
	 * getGoalStateY(), and isGoalNode(MazeNode) methods of the _mazeUI
	 * object. Read the accompanying Javadoc documentation for further information
	 * about the methods of the _mazeUI object.
	 * 
	 * This method should never catch the InterruptedException exception thrown by
	 * the other methods that this method invokes.
	 * 
	 * @return true if the search was able to reach the goal state, false
	 *         otherwise
	 * @throws InterruptedException
	 *           if the search thread has been externally interrupted
	 */
	public boolean doSearch() throws InterruptedException {

		// ??? - modify this method

		Queue<MazeNode> queue = new LinkedList<>();

        int size = _mazeUI.getMazeSize();

        MazeNode node = _mazeUI.getStartNode();
        _mazeUI.closedListAdd(node);
        _mazeUI.updateGUIState();
        queue.offer(node);

        int nodeStateX = _mazeUI.getStartNode().getStateX();
		int nodeStateY = _mazeUI.getStartNode().getStateY();

        while(!queue.isEmpty()){

            nodeStateX = node.getStateX();
            nodeStateY = node.getStateY();

			float gValue = node.getGValue()+1;
            
            //essay
			MazeNode down = new MazeNode(nodeStateX,nodeStateY+1,node,gValue,node.computeHValue(nodeStateX,nodeStateY+1,_mazeUI.getGoalStateX(),_mazeUI.getGoalStateY()));
            MazeNode right = new MazeNode(nodeStateX+1,nodeStateY,node,gValue,node.computeHValue(nodeStateX+1,nodeStateY,_mazeUI.getGoalStateX(),_mazeUI.getGoalStateY()));
            MazeNode up = new MazeNode(nodeStateX,nodeStateY-1,node,gValue,node.computeHValue(nodeStateX,nodeStateY-1,_mazeUI.getGoalStateX(),_mazeUI.getGoalStateY()));
            MazeNode left = new MazeNode(nodeStateX-1,nodeStateY,node,gValue,node.computeHValue(nodeStateX-1,nodeStateY,_mazeUI.getGoalStateX(),_mazeUI.getGoalStateY()));

            if(!_mazeUI.hasBarricade(nodeStateX,nodeStateY,nodeStateX,nodeStateY+1) && (nodeStateX>=0 && nodeStateX<size) && (nodeStateY+1>=0 && nodeStateY+1<size) && !_mazeUI.closedListContains(down)){
                _mazeUI.closedListAdd(down);
                _mazeUI.updateGUIState();
				if(_mazeUI.isGoalNode(down)) return true;
                queue.offer(down);
            }
            if(!_mazeUI.hasBarricade(nodeStateX,nodeStateY,nodeStateX+1,nodeStateY) && (nodeStateX+1>=0 && nodeStateX+1<size) && (nodeStateY>=0 && nodeStateY<size) && !_mazeUI.closedListContains(right)){
                _mazeUI.closedListAdd(right);
                _mazeUI.updateGUIState();
				if(_mazeUI.isGoalNode(right)) return true;
                queue.offer(right);
            }
            if(!_mazeUI.hasBarricade(nodeStateX,nodeStateY,nodeStateX,nodeStateY-1) && (nodeStateX>=0 && nodeStateX<size) && (nodeStateY-1>=0 && nodeStateY-1<size) && !_mazeUI.closedListContains(up)){
                _mazeUI.closedListAdd(up);
                _mazeUI.updateGUIState();
				if(_mazeUI.isGoalNode(up)) return true;
                queue.offer(up);
            }
            if(!_mazeUI.hasBarricade(nodeStateX,nodeStateY,nodeStateX-1,nodeStateY) && (nodeStateX-1>=0 && nodeStateX-1<size) && (nodeStateY>=0 && nodeStateY<size) && !_mazeUI.closedListContains(left)){
                _mazeUI.closedListAdd(left);
                _mazeUI.updateGUIState();
				if(_mazeUI.isGoalNode(left)) return true;
                queue.offer(left);
            }
            node = queue.poll();
        }
		return false;
	}


	/**
	 * The method that runs an instance of the search using the graphical user
	 * interface application. Nothing needs to be done in this method.
	 * 
	 * @param mazeAStar
	 *          the instance of this class that will be used by the GUI
	 *          application
	 */
	public static void run(Maze mazeAStar) {
		mazeAStar._mazeUI = MazeUI.run(mazeAStar);
	}

	/**
	 * This is the main method that starts the graphical user interface of the
	 * application. The doSearch() method of an instance of this class is called
	 * from inside the GUI application. Nothing needs to be done in this method.
	 * 
	 * @param args
	 *          the command-line arguments passed during the invocation of this
	 *          program (not used)
	 */
	public static void main(String[] args) {
		run(new Maze());
	}
}
