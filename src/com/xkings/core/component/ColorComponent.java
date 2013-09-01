package com.xkings.core.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

/**
 * User: Tomas <br>
 * Date: 6/27/13 <br>
 * Time: 12:37 PM <br>
 */
public class ColorComponent extends Component {

    private Color color;

    public ColorComponent(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
