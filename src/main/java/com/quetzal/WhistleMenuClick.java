package com.quetzal;

public class WhistleMenuClick {
    public final WhistleItem whistle;
    public final int tick;
    public final String option;

    WhistleMenuClick(WhistleItem whistle, int tick, String option) {
        this.whistle = whistle;
        this.tick = tick;
        this.option = option;
    }
}
