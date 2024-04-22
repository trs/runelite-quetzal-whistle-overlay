package com.quetzal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.ItemID;

public enum WhistleItem {
    BASIC(5),
    ENHANCED(20),
    PERFECTED(50);

    @Getter(AccessLevel.PACKAGE)
    public final int capacity;

    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    public int charges = -1;

    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    public String target = "?";

    WhistleItem(int capacity) {
        this.capacity = capacity;
    }

    public static WhistleItem forItemID(int itemID) {
        switch (itemID) {
            case ItemID.BASIC_QUETZAL_WHISTLE:
                return BASIC;
            case ItemID.ENHANCED_QUETZAL_WHISTLE:
                return ENHANCED;
            case ItemID.PERFECTED_QUETZAL_WHISTLE:
                return PERFECTED;
            default:
                return null;
        }
    }
}
