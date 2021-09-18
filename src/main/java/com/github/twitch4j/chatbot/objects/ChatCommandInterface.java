package com.github.twitch4j.chatbot.objects;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.events.domain.EventChannel;
import com.github.twitch4j.common.events.domain.EventUser;

public abstract class ChatCommandInterface {


    public abstract void handleCommand(ChannelMessageEvent event, EventChannel channel, EventUser user, String message);

    public String getName(){
        return this.getClass().getSimpleName();
    }

    public void replyToUser(ChannelMessageEvent event, String message){
        event.getTwitchChat().sendMessage(event.getChannel().getName(),String.format("%s %s",event.getUser().getName(),message));
    }

}
