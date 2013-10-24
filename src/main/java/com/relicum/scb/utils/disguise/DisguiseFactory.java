package com.relicum.scb.utils.disguise;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;

public class DisguiseFactory {

    @SuppressWarnings({"unchecked", "varargs"})
    public static <T> Object getFactory(DISTYPE distype, T name) {

        if (distype.toString().equalsIgnoreCase(DISTYPE.PLAYERD.toString())) {
            return new PlayerDisguise((String) name);
        }
        if (distype.toString().equalsIgnoreCase(DISTYPE.MODD.toString())) {
            return new MobDisguise((DisguiseType) name);
        }

        return new MiscDisguise((DisguiseType) name);
    }

}
