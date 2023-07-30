package com.maanraj514.party;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartyManager {
    private Map<UUID, Party> parties;

    public PartyManager() {
        this.parties = new HashMap<>();
    }

    public void createParty(Player player) {
        UUID partyUUID = UUID.randomUUID();
        parties.put(partyUUID, new Party(partyUUID, player.getUniqueId()));
    }

    public Party getParty(UUID partyUUID) {
        return parties.get(partyUUID);
    }

    public boolean isPlayerInParty(Player player) {
        for (Party party : parties.values()) {
            if (party.hasPlayer(player)) {
                return true;
            }
        }
        return false;
    }

    public Party getPlayerParty(Player player) {
        for (Party party : parties.values()) {
            if (party.hasPlayer(player)) {
                return party;
            }
        }
        return null;
    }

    public Map<UUID, Party> getParties() {
        return parties;
    }
}