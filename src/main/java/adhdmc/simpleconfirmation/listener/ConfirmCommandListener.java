package adhdmc.simpleconfirmation.listener;

import adhdmc.simpleconfirmation.event.CommandConfirmedEvent;
import adhdmc.simpleconfirmation.util.CommandRequest;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ConfirmCommandListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onProtectedCommand(PlayerCommandPreprocessEvent event) {
        if (event.getPlayer().hasPermission("simpleconfirmation.bypass")) {
            event.getPlayer().sendMessage("onProtectedCommand(): permission");
            return;
        }
        CommandRequest request = CommandRequest.popCommandRequest(event.getPlayer());
        if (request != null && request.command.equals(event.getMessage())) {
            event.getPlayer().sendMessage("onProtectedCommand(): Allowing send.");
            return;
        }
        event.setCancelled(true);
        event.getPlayer().sendMessage(
                MiniMessage.miniMessage().deserialize(
                        "<red>You must confirm before using this command: <gray>" + event.getMessage() + "\n"
                        + "<gold>Confirm by sending the same command again."
                )
        );
        CommandRequest.addCommandRequest(event.getPlayer(), event.getMessage());
    }

}
