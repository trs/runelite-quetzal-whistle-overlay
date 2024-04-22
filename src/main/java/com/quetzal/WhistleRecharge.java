package com.quetzal;

import net.runelite.api.ItemID;

public class WhistleRecharge {
    public static int chargesForItemId(int itemId) {
        switch (itemId) {
            case ItemID.RAW_WILD_KEBBIT:
            case ItemID.RAW_BARBTAILED_KEBBIT:
            case ItemID.RAW_LARUPIA:
                return 1;
            case ItemID.RAW_GRAAHK:
            case ItemID.RAW_KYATT:
            case ItemID.RAW_PYRE_FOX:
                return 2;
            case ItemID.RAW_DASHING_KEBBIT:
            case ItemID.RAW_SUNLIGHT_ANTELOPE:
            case ItemID.RAW_MOONLIGHT_ANTELOPE:
                return 3;
            default: return 0;
        }
    }
}
