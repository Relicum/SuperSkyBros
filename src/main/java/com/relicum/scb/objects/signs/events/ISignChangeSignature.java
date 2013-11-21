package com.relicum.scb.objects.signs.events;

import com.google.common.base.Strings;

import java.util.ArrayList;

/**
 * SuperSkyBros First Created 30/09/13 Sets the signature from the sign to decide what type of sign the use Is trying to
 * make.
 *
 * @author Relicum
 * @version 0.1
 */
public interface ISignChangeSignature extends IOnchange {

    /**
     * Gets lines signature. What we check for ro decide what type of sign The user is making
     *
     * @return the lines signature
     */
    public ArrayList<Strings> getLinesSignature();


}
