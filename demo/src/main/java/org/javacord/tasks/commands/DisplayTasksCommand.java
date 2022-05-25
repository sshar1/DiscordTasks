package org.javacord.tasks.commands;

import org.bson.Document;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static org.javacord.tasks.util.MongoUtil.*;
import org.javacord.tasks.util.Colors;

public class DisplayTasksCommand implements MessageCreateListener{
    
    /*
     * This command will display the tasks that were entered by the user
     */

     @Override
     public void onMessageCreate(MessageCreateEvent event) {

        MessageAuthor author = event.getMessage().getAuthor();

        if (event.getMessageContent().equalsIgnoreCase("?tasks")) {

            if (getDocFromId(author.getIdAsString()) == null) {
                event.getChannel().sendMessage("You haven't added any tasks!");
                return;
            }

            EmbedBuilder embed = new EmbedBuilder()
                .setTitle(author.getDisplayName() + "'s Tasks");

            Document tasks = getTasksFromId(author.getIdAsString());
            
            for (String task : tasks.keySet()) {
                embed.addField(task, (String) tasks.get(task));
            }

            embed.setColor(Colors.getColorFromString(getColor(author.getIdAsString()), Colors.WHITE));

            event.getChannel().sendMessage(embed);
        }
     }
}
