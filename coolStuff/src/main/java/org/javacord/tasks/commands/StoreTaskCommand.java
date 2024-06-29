package org.javacord.tasks.commands;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static org.javacord.tasks.util.MongoUtil.*;

public class StoreTaskCommand implements MessageCreateListener {
 
    /*
     * This command will store a task
     */

     @Override
     public void onMessageCreate(MessageCreateEvent event) {

        MessageAuthor author = event.getMessage().getAuthor();
        String message = event.getMessageContent();

        if (event.getMessageContent().length() < 8) {
            return;
        }

        if (event.getMessageContent().substring(0, 8).equalsIgnoreCase("!addTask")) {
            
            String title;
            String task;

            if (getDocFromId(author.getIdAsString()) == null) {
                addUser(author.getIdAsString());
            }
            
            if (message.indexOf("title:") == -1 || message.indexOf("task:") == -1) {
                event.getChannel().sendMessage("Oops! Your format for adding a task is not valid\nPlease use the following format: !addTask title: <title> task: <task>");
                return;
            }

            if (message.indexOf("title:") < message.indexOf("task:")) {
                title = message.substring(message.indexOf("title:") + 6, message.indexOf("task:"));
                task = message.substring(message.indexOf("task:") + 5);
            }
            else {
                title = message.substring(message.indexOf("title:") + 6);
                task = message.substring(message.indexOf("task:") + 5, message.indexOf("title:"));
            }

            if (taskExists(author.getIdAsString(), title)) {
                event.getChannel().sendMessage("Oops! You already have a task with that title! Please use a unique title");
                return;
            }

            title = title.trim();
            task = task.trim();

            addTask(author.getIdAsString(), title, task);

            event.getChannel().sendMessage("Successfully stored task!\nUse ?tasks to view them");
        }
     }
}