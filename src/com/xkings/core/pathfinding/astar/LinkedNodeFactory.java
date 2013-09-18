/*    
    Copyright (C) 2012 http://software-talk.org/ (developer@software-talk.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.xkings.core.pathfinding.astar;

/**
 * A Factory for LinkedNode.
 */
public class LinkedNodeFactory implements NodeFactory {
    private final boolean[][] footprint;

    public LinkedNodeFactory(boolean[][] footprint) {
        this.footprint = footprint;
    }

    @Override
    public AbstractNode createNode(int x, int y) {
        return new LinkedNode(footprint, x, y);
    }

}