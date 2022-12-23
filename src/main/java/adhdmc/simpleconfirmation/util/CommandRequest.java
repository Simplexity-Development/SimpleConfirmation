package adhdmc.simpleconfirmation.util;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CommandRequest {

    private static final HashMap<UUID, CommandRequest> needConfirmation = new HashMap<>();

    public final long timeRequested;
    public final String command;
    public CommandRequest(long timeRequested, String command) {
        this.timeRequested = timeRequested;
        this.command = command;
    }

    /**
     * Gets the Command Request made by the ConfirmCommandListener then removes it from the Map.
     * A player can only have one command request at a time.
     * @param p Player
     * @return CommandRequest of the player, null if it does not exist or has expired.
     */
    public static CommandRequest popCommandRequest(Player p) {
        if (!needConfirmation.containsKey(p.getUniqueId())) { return null; }
        CommandRequest request = needConfirmation.get(p.getUniqueId());
        needConfirmation.remove(p.getUniqueId());
        // TODO: Replace placeholder 20s (20,000ms) with configurable option.
        long timeDelta = System.currentTimeMillis() - request.timeRequested;
        if (timeDelta > 20000) return null;
        return request;
    }

    public static void addCommandRequest(Player p, String command) {
        needConfirmation.put(p.getUniqueId(), new CommandRequest(System.currentTimeMillis(), command));
    }

}
