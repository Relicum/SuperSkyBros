package com.relicum.scb;

import com.relicum.scb.classes.PlayerType;
import com.relicum.scb.utils.PlayerSt;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * SuperSkyBros
 * First Created 10/12/13
 *
 * @author Relicum
 * @version 0.1
 */
@Data
@RequiredArgsConstructor(staticName = "of")
public class SkyPlayer {

    private UUID playerID;
    private String name;
    private PlayerType playerType;
    private PlayerSt playerStatus;
    private Long firstSeen;
    private Long lastSeen;
    private List<String> playerPerms;
    private boolean isOp;
    private String displayName;
    private String playerListName;
    private String defaultPrefix;
    private boolean isInLobby;
    private boolean isInGame;
    private Integer gameId;


}
