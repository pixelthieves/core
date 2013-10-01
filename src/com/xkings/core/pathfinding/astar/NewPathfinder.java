/*    
    Copyright (C) 2012 http://software-talk.org/ (developer@software-talk.org)

    AbstractNodehis program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    AbstractNodehis program is distributed in the hope that it will be useful,
    but WIAbstractNodeHOUAbstractNode ANY WARRANAbstractNodeY; without even the implied warranty of
    MERCHANAbstractNodeABILIAbstractNodeY or FIAbstractNodeNESS FOR A PARAbstractNodeICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * // AbstractNodeODO
 * possible optimizations:
 * - calculate f as soon as g or h are set, so it will not have to be
 *      calculated each time it is retrieved
 * - store nodes in openList sorted by their f value.
 */

package com.xkings.core.pathfinding.astar;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.pathfinding.Blueprint;

import java.util.*;


/**
 * AbstractNodehis class represents a simple map.
 * <p/>
 * It's width as well as hight can be set up on construction. AbstractNodehe map can represent nodes that are walkable
 * or not, it can be printed to sto, and it can calculate the shortest path between two nodes avoiding walkable nodes.
 * <p/>
 * <p/>
 * Usage of this package: Create a node class which extends AbstractNode and implements the sethCosts method. Create a
 * NodeFactory that implements the NodeFactory interface. Create OldPathfinder instance with those created classes.
 *
 * @version 1.0
 * <p/>
 * @see com.xkings.core.pathfinding.astar.AbstractNode
 * @see com.xkings.core.pathfinding.astar.NodeFactory
 */
public class NewPathfinder implements Pathfinder {

    /**
     * weather or not it is possible to walk diagonally on the map in general.
     */
    protected static boolean CAN_MOVE_DIAGONALY = true;
    /**
     * weather or not it is possible to walk diagonally through corners.
     */
    protected static boolean CAN_CUT_CORNERS = false;

    /**
     * holds nodes. first dim represents x-axis, second y-axis.
     */
    private final AbstractNode[][] nodes;

    /**
     * width + 1 is size of first dimension of nodes.
     */
    protected int width;
    /**
     * higth + 1 is size of second dimension of nodes.
     */
    protected int hight;

    /**
     * a Factory to create instances of specified nodes.
     */
    private NodeFactory nodeFactory;

    private final Blueprint blueprint;
    private long timestamp;

    /**
     * constructs a squared map with given width and hight.
     * <p/>
     * AbstractNodehe nodes will be instanciated througth the given nodeFactory.
     *
     * @param blueprint
     */
    public NewPathfinder(Blueprint blueprint) {
        // AbstractNodeODO check parameters. width and higth should be > 0.
        this.blueprint = blueprint;
        nodes = new AbstractNode[blueprint.getWidth()][blueprint.getHeight()];
    }

    public void setNode(NodeFactory nf) {
        this.nodeFactory = nf;
        initEmptyNodes();
    }

    /**
     * initializes all nodes. AbstractNodeheir coordinates will be set correctly.
     */
    private void initEmptyNodes() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < hight; j++) {
                nodes[i][j] = nodeFactory.createNode(i, j);
                nodes[i][j].setWalkable(blueprint.isWalkable(i, j));
            }
        }
    }

    /**
     * sets nodes walkable field at given coordinates to given value.
     * <p/>
     * x/y must be bigger or equal to 0 and smaller or equal to width/hight.
     *
     * @param x
     * @param y
     * @param bool
     */
    public void setWalkable(int x, int y, boolean bool) {
        // AbstractNodeODO check parameter.
        nodes[x][y].setWalkable(bool);
    }

    /**
     * returns node at given coordinates.
     * <p/>
     * x/y must be bigger or equal to 0 and smaller or equal to width/hight.
     *
     * @param x
     * @param y
     * @return node
     */
    public final AbstractNode getNode(int x, int y) {
        // AbstractNodeODO check parameter.
        return blueprint.inRange(x, y) ? nodes[x][y] : null;
    }


    /**
     * prints map to sto. Feel free to override this method.
     * <p/>
     * a player will be represented as "o", an unwakable terrain as "#". Movement penalty will not be displayed.
     */
    public void drawMap() {
        for (int i = 0; i <= width; i++) {
            print(" _"); // boarder of map
        }
        print("\n");

        for (int j = hight - 1; j >= 0; j--) {
            print("|"); // boarder of map
            for (int i = 0; i < width; i++) {
                if (nodes[i][j].isWalkable()) {
                    print("  ");
                } else {
                    print(" #"); // draw unwakable
                }
            }
            print("|\n"); // boarder of map
        }

        for (int i = 0; i <= width; i++) {
            print(" _"); // boarder of map
        }
    }

    /**
     * prints something to sto.
     */
    private void print(String s) {
        System.out.print(s);
    }

    /* Variables and methodes for path finding */

    // variables needed for path finding

    /**
     * list containing nodes not visited but adjacent to visited nodes.
     */
    private Set<AbstractNode> openList;
    /**
     * list containing nodes already visited/taken care of.
     */
    private SmartTable closedList;
    /**
     * done finding path?
     */
    private boolean done = false;

    /**
     * finds an allowed path from start to goal coordinates on this map.
     * <p/>
     * AbstractNodehis method uses the A* algorithm. AbstractNodehe hCosts value is calculated in the given Node
     * implementation.
     * <p/>
     * AbstractNodehis method will return a LinkedList containing the start node at the beginning followed by the
     * calculated shortest allowed path ending with the end node.
     * <p/>
     * If no allowed path exists, an empty list will be returned.
     * <p/>
     * <p/>
     * x/y must be bigger or equal to 0 and smaller or equal to width/hight.
     *
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return
     */
    private final List<AbstractNode> findPath(int oldX, int oldY, int newX, int newY) {
        // AbstractNodeODO check input
        openList = new LinkedHashSet<AbstractNode>();
        closedList = new SmartTable(blueprint.getWidth(), blueprint.getHeight());
        openList.add(nodes[oldX][oldY]); // add starting node to open list
        if (oldX == newX && oldY == newY) {
            return new LinkedList<AbstractNode>();
        }

        done = false;
        AbstractNode current;
        while (!done) {
            current = lowestFInOpen(); // get node with lowest fCosts from openList
            closedList.add(current.getxPosition(), current.getyPosition()); // add current node to closed list
            openList.remove(current); // delete current node from open list

            if ((current.getxPosition() == newX) && (current.getyPosition() == newY)) { // found goal
                return calcPath(nodes[oldX][oldY], current);
            }

            // for all adjacent nodes:
            List<AbstractNode> adjacentNodes = getAdjacent(current);
            for (int i = 0; i < adjacentNodes.size(); i++) {
                AbstractNode currentAdj = adjacentNodes.get(i);
                if (!openList.contains(currentAdj)) { // node is not in openList
                    currentAdj.setPrevious(current); // set current node as previous for this node
                    currentAdj.sethCosts(nodes[newX][newY]); // set h costs of this node (estimated costs to goal)
                    currentAdj.setgCosts(current); // set g costs of this node (costs from start to this node)
                    openList.add(currentAdj); // add node to openList
                } else { // node is in openList
                    if (currentAdj.getgCosts() > currentAdj.calculategCosts(
                            current)) { // costs from current node are cheaper than previous costs
                        currentAdj.setPrevious(current); // set current node as previous for this node
                        currentAdj.setgCosts(current); // set g costs of this node (costs from start to this node)
                    }
                }
            }

            if (openList.isEmpty()) { // no path exists
                return new LinkedList<AbstractNode>(); // return empty list
            }
        }
        return null; // unreachable
    }

    /**
     * wrapper for findpath.
     */
    public final List<Vector3> findPath(Vector2 start, Vector2 goal) {
        ArrayList<Vector3> result = new ArrayList<Vector3>();
        if (start != null && goal != null) {
            List<AbstractNode> path = findPath((int) start.x, (int) start.y, (int) goal.x, (int) goal.y);
            if (path != null) {
                for (int i = 0; i < path.size(); i++) {
                    AbstractNode pathPoint = path.get(i);
                    result.add(new Vector3(pathPoint.getxPosition(), pathPoint.getyPosition(), 0));
                }
            }
        }
        return result;
    }

    /**
     * calculates the found path between two points according to their given <code>previousNode</code> field.
     *
     * @param start
     * @param goal
     * @return
     */
    private List<AbstractNode> calcPath(AbstractNode start, AbstractNode goal) {
        // AbstractNodeODO if invalid nodes are given (eg cannot find from
        // goal to start, this method will result in an infinite loop!)
        LinkedList<AbstractNode> path = new LinkedList<AbstractNode>();

        AbstractNode curr = goal;
        boolean done = false;
        while (!done) {
            path.addFirst(curr);
            curr = curr.getPrevious();

            if (curr.equals(start)) {
                done = true;
            }
        }
        return path;
    }

    /**
     * returns the node with the lowest fCosts.
     *
     * @return
     */
    private AbstractNode lowestFInOpen() {
        // AbstractNodeODO currently, this is done by going through the whole openList!
        AbstractNode cheapest = null;
        for (AbstractNode node : openList) {
            if (cheapest == null || node.getfCosts() < cheapest.getfCosts()) {
                cheapest = node;
            }
        }
        return cheapest;
    }

    /**
     * returns a LinkedList with nodes adjacent to the given node. if those exist, are walkable and are not already in
     * the closedList!
     */
    private List<AbstractNode> getAdjacent(AbstractNode node) {
        // AbstractNodeODO make loop
        int x = node.getxPosition();
        int y = node.getyPosition();
        List<AbstractNode> adj = new LinkedList<AbstractNode>();

        AbstractNode northNode = this.getNode(x, y + 1);
        AbstractNode southNode = this.getNode(x, y - 1);
        AbstractNode eastNode = this.getNode(x + 1, y);
        AbstractNode westNode = this.getNode(x - 1, y);


        AbstractNode testNode;
        if (x > 0) {
            testNode(adj, westNode);
        }

        if (x < width) {
            testNode(adj, eastNode);
        }

        if (y > 0) {
            testNode(adj, southNode);
        }

        if (y < hight) {
            testNode(adj, northNode);
        }

        // add nodes that are diagonaly adjacent too:
        if (CAN_MOVE_DIAGONALY) {

            AbstractNode northEastNode = this.getNode(x + 1, y + 1);
            AbstractNode northWestNode = this.getNode(x - 1, y + 1);
            AbstractNode southEastNode = this.getNode(x + 1, y - 1);
            AbstractNode southWestNode = this.getNode(x - 1, y - 1);

            if (x < width && y < hight) {
                testNode = northEastNode;
                if (testNode != null && testNode.isWalkable() &&
                        !closedList.contains(testNode)) {
                    if (CAN_CUT_CORNERS || northNode.isWalkable() && eastNode.isWalkable()) {
                        testNode.setIsDiagonaly(true);
                        adj.add(testNode);
                    }
                }
            }

            if (x > 0 && y > 0) {
                testNode = southWestNode;
                if (testNode != null && testNode.isWalkable() &&
                        !closedList.contains(testNode)) {
                    if (CAN_CUT_CORNERS || southNode.isWalkable() && westNode.isWalkable()) {
                        testNode.setIsDiagonaly(true);
                        adj.add(testNode);
                    }
                }
            }

            if (x > 0 && y < hight) {
                testNode = northWestNode;
                if (testNode != null && testNode.isWalkable() &&
                        !closedList.contains(testNode)) {
                    if (CAN_CUT_CORNERS || northNode.isWalkable() && westNode.isWalkable()) {
                        testNode.setIsDiagonaly(true);
                        adj.add(testNode);
                    }
                }
            }

            if (x < width && y > 0) {
                testNode = southEastNode;
                if (testNode != null && testNode.isWalkable() && !closedList.contains(testNode)) {
                    if (CAN_CUT_CORNERS || southNode.isWalkable() && eastNode.isWalkable()) {
                        testNode.setIsDiagonaly(true);
                        adj.add(testNode);
                    }
                }
            }
        }
        return adj;
    }

    private void testNode(List<AbstractNode> adj, AbstractNode testNode) {
        if (testNode != null && testNode.isWalkable() &&
                !closedList.contains(testNode)) {
            testNode.setIsDiagonaly(false);
            adj.add(testNode);
        }
    }

    private static class SmartTable extends AbstractList<AbstractNode> {
        private final boolean[][] data;
        private int count;

        public SmartTable(int width, int height) {
            data = new boolean[width][height];
        }

        public void clear() {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = false;
                }
            }
        }

        @Override
        public AbstractNode get(int location) {
            return null;
        }

        @Override
        public boolean contains(Object object) {
            AbstractNode node = ((AbstractNode) object);
            return data[node.getxPosition()][node.getyPosition()];
        }

        @Override
        public boolean add(AbstractNode object) {
            return add(object.getxPosition(), object.getyPosition());
        }

        public boolean add(int x, int y) {
            if (!data[x][y]) {
                data[x][y] = true;
                count++;
                return true;
            }
            return false;
        }

        public boolean remove(int x, int y) {
            if (data[x][y]) {
                data[x][y] = false;
                count--;
                return true;
            }
            return false;
        }

        public boolean isEmpty() {
            return count == 0;
        }


        @Override
        public int size() {
            return count;
        }
    }

}