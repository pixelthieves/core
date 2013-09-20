package com.xkings.core.pathfinding.astar;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

/**
 * Created by Tomas on 9/20/13.
 */
public interface Pathfinder {
    List<Vector3> findPath(Vector2 start, Vector2 goal);

    void setNode(NodeFactory nf);
}
