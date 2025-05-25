package org.silentsoft.simpleicons;

import java.text.Normalizer;
import java.util.function.Function;

public class Utils {

    private Utils() { }

    private static final Function<String, String> slugExceptionRule = (slug) -> {
        return slug.replaceAll("\\+", "plus")
                .replaceAll("\\.", "dot")
                .replaceAll("&", "and")
                .replaceAll("đ", "d")
                .replaceAll("ħ", "h")
                .replaceAll("ı", "i")
                .replaceAll("ĸ", "k")
                .replaceAll("ŀ", "l")
                .replaceAll("ł", "l")
                .replaceAll("ß", "ss")
                .replaceAll("ŧ", "t");
    };

    private static final Function<String, String> namingExceptionRule = (className) -> {
        switch (className.toLowerCase()) {
            case "1and1": return "Oneandone";
            case "1dot1dot1dot1": return "Onedotonedotonedotone";
            case "1panel": return "Onepanel";
            case "1password": return "Onepassword";
            case "2fas": return "Twofas";
            case "2k": return "Twok";
            case "3m": return "Threem";
            case "4chan": return "Fourchan";
            case "4d": return "Fourd";
            case "7zip": return "Sevenzip";
            case "9gag": return "Ninegag";
            case "30secondsofcode": return "Thirtysecondsofcode";
            case "42": return "Fourtytwo";
            case "99designs": return "Ninetyninedesigns";
            case "365datascience": return "Threehundredsixtyfivedatascience";
            case "500px": return "Fivehundredpx";
            case "1001tracklists": return "Onethousandonetracklists";
            default: return className;
        }
    };

    public static String titleToSlug(String title) {
        return Normalizer.normalize(slugExceptionRule.apply(title.toLowerCase()), Normalizer.Form.NFD).replaceAll("[^a-z0-9]", "");
    }

    public static String slugToClassName(String slug) {
        return namingExceptionRule.apply(slug.substring(0, 1).toUpperCase().concat(slug.substring(1))).concat("Icon");
    }

}
