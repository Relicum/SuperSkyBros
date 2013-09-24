package com.relicum.scb.objects.signs.abstracts;

import com.relicum.scb.objects.signs.ESign;
import com.relicum.scb.objects.signs.interfaces.SaveAble;


/**
 * SuperSkyBros First Created 23/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class JoinLobby extends ActionSign implements SaveAble {


    JoinLobby() {
        super();
        this.setSubType();
    }


    @Override
    protected void setSubType() {
        this.subType = ESign.JOINLOBBY;
    }

}
