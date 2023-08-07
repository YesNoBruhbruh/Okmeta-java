package com.maanraj514.party;

import com.maanraj514.party.ranks.Member;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartyManager {
    private final Map<UUID, Party> parties;

    public PartyManager() {
        this.parties = new HashMap<>();
    }

    public Party createParty(Player player) {
        UUID partyUUID = UUID.randomUUID();
        Party party = new Party(partyUUID, player.getUniqueId());
        parties.put(partyUUID, party);
        return party;
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