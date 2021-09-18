package com.github.twitch4j.chatbot;

import com.github.twitch4j.chatbot.objects.TwitchDispatcher;

public class Launcher {

	public static TwitchDispatcher twitchDispatcher = null;
	public static String botPrefix = null;
	public static Bot bot = null;

	public static void main(String[] args) {
		bot = new Bot();
		bot.registerFeatures();

		twitchDispatcher = new TwitchDispatcher();
		botPrefix = bot.getBotPrefix();

		bot.start();
	}
}
