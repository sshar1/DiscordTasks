package org.javacord.tasks;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.tasks.commands.*;

public class Main {
    
    public static void main(String[] args) {

        String token = "OTc2MjMzNzIyNDY4NzczOTA4.G1aWiO.HdpE5U6ERQpm8NWvAx7Y_eIzgQnYbtGGLR7dJY";

        // if (args.length < 1) {
        //     System.out.println("please enter valid token");
        //     return;
        // }

        // token = args[0];

        DiscordApi api = new DiscordApiBuilder()
            .setToken(token)
            .login().join();

        api.addMessageCreateListener(new DisplayTasksCommand());
        api.addMessageCreateListener(new StoreTaskCommand());
        api.addMessageCreateListener(new SetColorCommand());
        api.addMessageCreateListener(new DestroyTaskCommand());
    }
}
