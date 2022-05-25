package org.javacord.tasks;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.tasks.commands.*;

public class Main {
    
    public static void main(String[] args) {

        DiscordApi api = new DiscordApiBuilder()
            .setToken("OTc2MjMzNzIyNDY4NzczOTA4.G1aWiO.HdpE5U6ERQpm8NWvAx7Y_eIzgQnYbtGGLR7dJY")
            .login().join();

        api.addMessageCreateListener(new DisplayTasksCommand());
        api.addMessageCreateListener(new StoreTaskCommand());
        api.addMessageCreateListener(new SetColorCommand());
        api.addMessageCreateListener(new DestroyTaskCommand());
    }
}
