package com.quetzal;

import java.util.regex.Pattern;

public class WhistleMessage {
    public static final Pattern WHISTLE_CHECK = Pattern.compile("^Your quetzal whistle has (\\d+) charges remaining\\.$");

    public static final Pattern WHISTLE_RECHARGE_CONFIRM = Pattern.compile("^There you go. Some whistle charges for you!$");

    public static final Pattern WHISTLE_RUMOR_TARGET = Pattern.compile("^Your current rumour target is a (.+?)\\. You'll need to bring back some of its .+?\\.$");
}