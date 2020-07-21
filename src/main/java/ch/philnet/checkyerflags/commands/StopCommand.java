package ch.philnet.checkyerflags.commands;

import org.slf4j.Logger;
import org.sobotics.chatexchange.chat.Room;

public class StopCommand extends Command {
    public StopCommand(Room chatRoom, Logger commandLogger) {
        //Allowed Patters:
        // stop
        // bye
        commandPattern = "(?i)(stop|bye)";
        room = chatRoom;
        logger = commandLogger;
    }

    @Override
    public boolean testCommandPattern(String command) {
        return super.testCommandPattern(command);
    }

    @Override
    public void run(long messageId) {
        if (super.hasPrivileges(messageId)) {
            room.send("I'll be back!");
            logger.info("Stopping the bot");
            room.leave();
            System.exit(0);
        }
    }
}
