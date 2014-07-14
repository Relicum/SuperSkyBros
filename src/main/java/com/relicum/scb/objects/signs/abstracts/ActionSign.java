package com.relicum.scb.objects.signs.abstracts;

import com.relicum.scb.objects.signs.ESign;
import com.relicum.scb.objects.signs.TSign;
import com.relicum.scb.objects.signs.interfaces.ISubType;
import com.relicum.scb.objects.signs.interfaces.PSign;


/**
 * SuperSkyBros First Created 24/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class ActionSign extends BaseSign implements ISubType, PSign {

    protected ESign subType;


    ActionSign(TSign action, ESign joinlobby) {
        super(action, joinlobby);
        this.setSuperType();
    }

    /**
     * Gets Sub type. From the enum ESign
     *
     * @return ESign the sub type of sign
     */
    @Override
    public ESign getSubType() {
        return this.subType;
    }

    protected abstract void setSubType(ESign e);
}
