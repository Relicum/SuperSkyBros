package com.relicum.scb.objects.signs.abstracts;

import com.relicum.scb.objects.signs.TSign;
import com.relicum.scb.objects.signs.interfaces.ISigns;
import com.relicum.scb.objects.signs.interfaces.ISuperType;
import org.bukkit.event.Listener;

/**
 * SuperSkyBros First Created 24/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class BaseSign implements ISigns, ISuperType, Listener {


    protected TSign type;


    BaseSign() {


    }


    protected abstract void setSuperType();


    /**
     * Gets super type. From the enum TSign
     *
     * @return TSign the super type of sign
     */
    @Override
    public TSign getSuperType() {

        return this.type;
    }

}
