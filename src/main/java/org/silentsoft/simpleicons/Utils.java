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
        if ("1001tracklists".equalsIgnoreCase(className)) {
            return "Onethousandonetracklists";
        } else if ("1password".equalsIgnoreCase(className)) {
            return "Onepassword";
        } else if ("3m".equalsIgnoreCase(className)) {
            return "Threem";
        } else if ("42".equalsIgnoreCase(className)) {
            return "Fourtytwo";
        } else if ("4chan".equalsIgnoreCase(className)) {
            return "Fourchan";
        } else if ("4d".equalsIgnoreCase(className)) {
            return "Fourd";
        } else if ("500px".equalsIgnoreCase(className)) {
            return "Fivehundredpx";
        } else {
            return className;
        }
    };

    public static String titleToSlug(String title) {
        return Normalizer.normalize(slugExceptionRule.apply(title.toLowerCase()), Normalizer.Form.NFD).replaceAll("[^a-z0-9]", "");
    }

    public static String slugToClassName(String slug) {
        return namingExceptionRule.apply(slug.substring(0, 1).toUpperCase().concat(slug.substring(1))).concat("Icon");
    }

}
