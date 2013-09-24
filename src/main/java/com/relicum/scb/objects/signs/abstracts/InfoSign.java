package com.relicum.scb.objects.signs.abstracts;

import com.relicum.scb.objects.signs.ESign;
import com.relicum.scb.objects.signs.TSign;
import com.relicum.scb.objects.signs.abstracts.BaseSign;
import com.relicum.scb.objects.signs.interfaces.ISubType;
import org.bukkit.event.Listener;

/**
 * SuperSkyBros First Created 24/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class InfoSign extends BaseSign implements ISubType {

    protected ESign subType;


    InfoSign() {
        super();
    }


    @Override
    protected void setSuperType() {
        this.type = TSign.INFO;
    }


    protected abstract void setSubType();


    /**
     * Gets Sub type. From the enum ESign
     *
     * @return ESign the sub type of sign
     */
    @Override
    public ESign getSubType() {
        return this.subType;
    }
}
