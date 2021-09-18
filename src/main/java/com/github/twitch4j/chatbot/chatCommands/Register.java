package com.github.twitch4j.chatbot.chatCommands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.chatbot.objects.ChatCommandInterface;
import com.github.twitch4j.common.events.domain.EventChannel;
import com.github.twitch4j.common.events.domain.EventUser;

public class Register extends ChatCommandInterface {

    @Override
    public void handleCommand(ChannelMessageEvent event, EventChannel channel, EventUser user, String message) {

    }
}
