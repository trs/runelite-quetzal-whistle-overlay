package com.quetzal;

public class WhistleRumorTarget {
    public static String targetOverlayString(String value) {
        switch (value) {
            case "tropical wagtail": return "TRP WAG";
            case "wild kebbit": return "WLD KBT";
            case "sapphire glacialis": return "SPH GLC";
            case "swamp lizard": return "SWP LZD";
            case "spined larupia": return "SPD LRP";
            case "snowy knight": return "SWY KGT";
            case "prickly kebbit": return "PRK KBT";
            case "embertailed jerboa": return "EMB JER";
            case "horned graahk": return "HRN GRK";
            case "spotted kebbit": return "SPT KBT";
            case "black warlock": return "BLK WLK";
            case "orange salamander": return "ORG SLM";
            case "razor-backed kebbit": return "RZR KBT";
            case "sabre-toothed kebbit": return "SBR KBT";
            case "grey chinchompa": return "GRY CHN";
            case "sabre-toothed kyatt": return "SBR KYT";
            case "dark kebbit": return "DRK KBT";
            case "pyre fox": return "PYR FOX";
            case "red salamander": return "RED SLM";
            case "red chinchompa": return "RED CHN";
            case "sunlight moth": return "SNL MTH";
            case "dashing kebbit": return "DSH KBT";
            case "sunlight antelope": return "SUN ANT";
            case "moonlight moth": return "MNL MTH";
            case "tecu salamander": return "TCU SLM";
            case "herbiboar": return "HERBI";
            case "moonlight antelope": return "MNL ANT";
            default: return "UNKWN";
        }
    }
}
