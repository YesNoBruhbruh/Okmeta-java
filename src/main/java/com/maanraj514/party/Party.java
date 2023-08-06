package com.maanraj514.party;

import com.maanraj514.Okmeta;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class Party {
    private final UUID partyUUID;
    private UUID owner;
    private final Map<UUID, PartyRank> players;
    private final List<UUID> onlinePlayers;
    private final List<UUID> offlinePlayers;
    private final Map<UUID, BukkitTask> inviteTasks;
    private final Map<UUID, BukkitTask> removeTasks;

    public Party(UUID partyUUID, UUID owner) {
        this.partyUUID = partyUUID;
        this.owner = owner;
        this.players = new HashMap<>();
        this.onlinePlayers = new ArrayList<>();
        this.offlinePlayers = new ArrayList<>();
        this.inviteTasks = new HashMap<>();
        this.removeTasks = new HashMap<>();
    }

    public void addPlayer(Player player, PartyRank rank) {
        players.put(player.getUniqueId(), rank);
        onlinePlayers.add(player.getUniqueId());
    }

    public void acceptInvite(Player player, PartyRank partyRank) {
        if (inviteTasks.containsKey(player.getUniqueId())) {
            inviteTasks.get(player.getUniqueId()).cancel();
            inviteTasks.remove(player.getUniqueId());
        }
        addPlayer(player, partyRank);
    }

    public void removePlayer(Player player, long time, JavaPlugin plugin) {
        removeTasks.put(player.getUniqueId(), Bukkit.getScheduler().runTaskLater(
                plugin,
                () -> removePlayer(player),
                time)
        );
    }

    public void removePlayer(OfflinePlayer offlinePlayer) {
        players.remove(offlinePlayer.getUniqueId());
        onlinePlayers.remove(offlinePlayer.getUniqueId());
        offlinePlayers.remove(offlinePlayer.getUniqueId());

        if (this.players.isEmpty()){
            this.disband();
        }
    }

    public void invite(Player invited, long timeToExpire, JavaPlugin plugin) {
        inviteTasks.put(invited.getUniqueId(), Bukkit.getScheduler().runTaskLater(
                plugin,
                () -> inviteTasks.remove(invited.getUniqueId()),
                timeToExpire)
        );
    }

    public void rejoin(Player player) {
        if (removeTasks.containsKey(player.getUniqueId())) {
            removeTasks.get(player.getUniqueId()).cancel();
            removeTasks.remove(player.getUniqueId());
        }
    }

    public void transfer(Player newOwner, PartyRank newOwnerRank, PartyRank oldOwnerRank) {
        players.put(owner, oldOwnerRank);
        players.put(newOwner.getUniqueId(), newOwnerRank);
        owner = newOwner.getUniqueId();
    }

    public void promote(Player player, PartyRank rankToPromote) {
        PartyRank rank = players.get(player.getUniqueId());

        double rankStrength = rank != null ? rank.getStrength() : 0;
        double rankToPromoteStrength = rankToPromote.getStrength();

        if (rankStrength <= rankToPromoteStrength) {
            players.put(player.getUniqueId(), rankToPromote);
        }
    }

    public void demote(Player player, PartyRank rankToDemote) {
        PartyRank rank = players.get(player.getUniqueId());

        double rankStrength = rank != null ? rank.getStrength() : 0;
        double rankToDemoteStrength = rankToDemote.getStrength();

        if (rankStrength >= rankToDemoteStrength) {
            players.put(player.getUniqueId(), rankToDemote);
        }
    }

    public void kickOffline() {
        for (UUID uuid : offlinePlayers) {
            removePlayer(Bukkit.getOfflinePlayer(uuid));
        }
    }

    public void kickOffline(OfflinePlayer offlinePlayer) {
        removePlayer(offlinePlayer);
    }

    public void disband() {
        players.clear();
        onlinePlayers.clear();
        offlinePlayers.clear();

        for (BukkitTask task : inviteTasks.values()) {
            task.cancel();
        }
        inviteTasks.clear();

        for (BukkitTask task : removeTasks.values()) {
            task.cancel();
        }
        removeTasks.clear();
    }

    public boolean hasRank(Player player, PartyRank partyRank) {
        return players.get(player.getUniqueId()) == partyRank;
    }

    public boolean hasPlayer(Player player) {
        return players.containsKey(player.getUniqueId());
    }

    public Map<UUID, PartyRank> getPlayers() {
        return players;
    }

    public List<UUID> getOnlinePlayers() {
        return onlinePlayers;
    }

    public UUID getPartyUUID() {
        return partyUUID;
    }

    public UUID getOwner() {
        return owner;
    }

    public List<UUID> getOfflinePlayers() {
        return offlinePlayers;
    }

    public Map<UUID, BukkitTask> getInviteTasks() {
        return inviteTasks;
    }

    public Map<UUID, BukkitTask> getRemoveTasks() {
        return removeTasks;
    }
}