package com.relicum.scb.objects.signs.abstracts;

import com.relicum.scb.objects.signs.ESign;
import com.relicum.scb.objects.signs.TSign;
import com.relicum.scb.objects.signs.interfaces.ISigns;
import com.relicum.scb.objects.signs.interfaces.ISubType;
import com.relicum.scb.objects.signs.interfaces.ISuperType;
import org.bukkit.event.Listener;

/**
 * SuperSkyBros First Created 24/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class BaseSign implements ISigns, ISuperType, ISubType, Listener {

    protected ESign subType;

    protected TSign type;


    public BaseSign() {
        //To change body of created methods use File | Settings | File Templates.
    }


    public BaseSign(TSign action, ESign joinlobby) {
        //To change body of created methods use File | Settings | File Templates.
    }

    /**
     * Gets super type. From the enum TSign
     *
     * @return TSign the super type of sign
     */
    @Override
    public TSign getSuperType() {

        return this.type;
    }

    protected void setSuperType(TSign t) {
        this.type = t;
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

    protected void setSubType(ESign e) {
        this.subType = e;
    }

    protected void setSuperType() {
        this.type = TSign.ACTION;
    }
}
