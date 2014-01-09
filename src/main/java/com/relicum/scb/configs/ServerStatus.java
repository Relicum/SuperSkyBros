package com.relicum.scb.configs;

/**
 * The enum Server status.
 */
public enum ServerStatus {

    /**
     * The MODEUNSET.
     */MODEUNSET(0),
    /**
     * The SETLOBBY.
     */SETLOBBY(1),
    /**
     * The SETARENA.
     */SETARENA(2),
    /**
     * The SETAREASPAWNS.
     */SETAREASPAWNS(3),
    /**
     * The SETAREALOBBY.
     */SETAREALOBBY(4),
    /**
     * The SETENABLE.
     */SETENABLE(5),
    /**
     * The CREATEJOINSIGN.
     */CREATEJOINSIGN(6),
    /**
     * The READY.
     */READY(7);


    private final int name;


    private ServerStatus(int node) {
        this.name = node;
    }


    public String getNode() {
        return "ServerStatus-" + name;
    }
}
