package com.xkings.core.logic;

import com.xkings.core.server.ClientCommand;

public class ClockCommand extends ClientCommand {
    /**
     *
     */
    private static final long serialVersionUID = 8079538732187185325L;

    public ClockCommand(ClientCommand c, long clocks) {
        super(c.getAction(), clocks, c.getValue());
    }
}
