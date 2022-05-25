package org.javacord.tasks.commands;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static org.javacord.tasks.util.MongoUtil.*;
import org.javacord.tasks.util.Colors;

public class SetColorCommand implements MessageCreateListener{
    
    /*
     * This command will set the embed color
     */

     @Override
     public void onMessageCreate(MessageCreateEvent event) {

        MessageAuthor author = event.getMessage().getAuthor();

        if (event.getMessageContent().length() >= 9 && event.getMessageContent().substring(0, 9).equalsIgnoreCase("!setColor")) {

            String color;

            if (getDocFromId(author.getIdAsString()) == null) {
                addUser(author.getIdAsString());
            }
            
            if (event.getMessageContent().indexOf(" ") <= 8) {
                event.getChannel().sendMessage("Oops! Your format for setting your embed color is not valid!\nPlease use the following format: !setColor <color>");
                return;
            }

            color = event.getMessageContent().substring(event.getMessageContent().indexOf(" ") + 1);

            if (Colors.colorValid(color)) {
                setColor(author.getIdAsString(), color);
                event.getChannel().sendMessage("Succesfully set color to " + color);
            }
            else {
                event.getChannel().sendMessage("Oops! That color is not supported!\nPlease use the following colors: " + Colors.getColors());
            }
        }
     }
}
