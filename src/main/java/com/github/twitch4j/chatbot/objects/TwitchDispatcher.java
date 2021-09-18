package com.github.twitch4j.chatbot.objects;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.chatbot.chatCommands.Discord;
import com.github.twitch4j.chatbot.chatCommands.HelloUser;
import com.github.twitch4j.chatbot.chatCommands.Here;
import com.github.twitch4j.common.events.domain.EventChannel;

import static com.github.twitch4j.chatbot.Launcher.botPrefix;

public class TwitchDispatcher {

    private final Set<ChatCommandInterface> chatCommands = ConcurrentHashMap.newKeySet();

    public TwitchDispatcher(){

        this.registerCommand(new Here());
        this.registerCommand(new Discord());
        this.registerCommand(new HelloUser());

    }

    public Set<ChatCommandInterface> getCommands() {
        return Collections.unmodifiableSet(new HashSet<>(this.chatCommands));
    }

    public boolean registerCommand(final ChatCommandInterface chatCommand){
        if(chatCommand.getName().contains(" "))
            throw new IllegalArgumentException("Name must not have spaces!");
        if(this.chatCommands.stream().map(ChatCommandInterface::getName).anyMatch(c -> chatCommand.getName().equalsIgnoreCase(c)))
            return false;
        this.chatCommands.add(chatCommand);
        return true;
    }

    public void onChannelMessage(ChannelMessageEvent event){

        final String prefix = botPrefix;
        final String message = event.getMessage();

        if(message.toLowerCase().startsWith(prefix.toLowerCase())){
            //Message does start with prefix

            for(final ChatCommandInterface c: this.getCommands()){
                if(message.toLowerCase().startsWith(prefix.toLowerCase() + c.getName().toLowerCase() + ' ') || message.equalsIgnoreCase(prefix + c.getName())){

                    final String content = this.removePrefix(c.getName(),prefix,message);

                    this.executeCommand(c,event.getChannel(),content,event);
                }
            }


        }

    }

    private void executeCommand(final ChatCommandInterface c, final EventChannel channel, final String message, final ChannelMessageEvent event){
        c.handleCommand(event,channel,event.getUser(),message);
    }

    private String removePrefix(final String commandName, final String prefix, String content) {
        content = content.substring(commandName.length() + prefix.length());
        if (content.startsWith(" "))
            content = content.substring(1);
        return content;
    }

}
