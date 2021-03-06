package ch.philnet.checkyerflags.commands;

import org.sobotics.chatexchange.chat.Room;
import org.sobotics.chatexchange.chat.User;
import org.sobotics.chatexchange.chat.event.PingMessageEvent;

import ch.philnet.checkyerflags.utils.MessageHandler;

import java.util.regex.Pattern;

/**
 * Parent Class for Commands
 */
public abstract class Command {
    String commandPattern;
    Room room;
    MessageHandler messageHandler;

    /**
     * Test if the supplied command string has a valid pattern for the command instance
     * @param command Command string to test
     */
    public boolean testCommandPattern(String command) {
        return Pattern.matches(commandPattern, command);
    }

    /**
     * Test if the user that called the command has privileges to run it
     * @param messageId Id of the message that called the command
     */
    public boolean hasPrivileges(long messageId) {
        return this.hasPrivileges(messageId, true);
    }
      
    /**
     * Test if the user that called the command has privileges to run it
     * @param messageId Id of the message that called the command
     * @param verbose To post a message if the user does not have the privileges
     */
    public boolean hasPrivileges(long messageId, boolean verbose) {
        User messageAuthor = room.getMessage(messageId).getUser();

        //Only allowed to room owners and moderators
        if (messageAuthor.isModerator() || messageAuthor.isRoomOwner()) {
            return true;
        } else {
            if (verbose)
                room.replyTo(messageId, "This command is restricted to moderators and room owners.");
                
            return false;
        }
    }

    /**
     * Run a specific command
     * @param messageId The message id
     */
    public abstract void run(long messageId, PingMessageEvent event);
}
