package org.monstercraft.irc.command.irccommands;

import org.monstercraft.irc.IRC;
import org.monstercraft.irc.command.IRCCommand;
import org.monstercraft.irc.wrappers.IRCChannel;

public class Announce extends IRCCommand {

	public Announce(org.monstercraft.irc.IRC plugin) {
		super(plugin);
	}

	public boolean canExecute(String sender, String message, IRCChannel channel) {
		return IRC.getHandleManager().getIRCHandler().isConnected()
				&& message.startsWith(".announce");
	}

	public boolean execute(String sender, String message, IRCChannel channel) {
		if (IRC.getHandleManager()
				.getIRCHandler()
				.isVoice(sender,
						IRC.getHandleManager().getIRCHandler().getVoiceList())
				|| IRC.getHandleManager()
						.getIRCHandler()
						.isOp(sender,
								IRC.getHandleManager().getIRCHandler()
										.getOpList())) {
			plugin.getServer().broadcastMessage(
					"�4[IRC]<"
							+ sender
							+ ">: "
							+ IRC.getHandleManager().getIRCHandler()
									.formatMessage(message.substring(10)));
		}
		return true;
	}

}
