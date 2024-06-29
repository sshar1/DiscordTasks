package org.javacord.tasks.commands;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static org.javacord.tasks.util.MongoUtil.*;

public class DestroyTaskCommand implements MessageCreateListener {
 
    /*
     * This command will destroy a task that has been completed
     */

     @Override
     public void onMessageCreate(MessageCreateEvent event) {

        MessageAuthor author = event.getMessage().getAuthor();

        if (event.getMessageContent().length() < 11) {
            return;
        }

        if (event.getMessageContent().substring(0, 11).equalsIgnoreCase("!removeTask")) {

            String title;

            if (getDocFromId(author.getIdAsString()) == null) {
                event.getChannel().sendMessage("Oops! You have no tasks!");
            }

            if (event.getMessageContent().length() < 13 || !event.getMessageContent().substring(11, 12).equals(" ")) {
                event.getChannel().sendMessage("Oops! Your format for removing a task is not valid\nPlease use the following format: !removeTask <title>");
                return;
            }

            title = event.getMessageContent().substring(12);

            if (taskExists(author.getIdAsString(), title)) {
                removeTask(author.getIdAsString(), title);
                event.getChannel().sendMessage("Successfully removed task!\nUse ?tasks to view them");
            }
            else {
                event.getChannel().sendMessage("Oops! That task does not exist\nPlease use the following format: !removeTask <title>");
            }
        }
     }
}