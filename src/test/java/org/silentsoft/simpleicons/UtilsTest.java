package org.silentsoft.simpleicons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    public void titleToSlugTest() {
        Assertions.assertEquals("dotnet", Utils.titleToSlug(".NET"));
        Assertions.assertEquals("e", Utils.titleToSlug("/e/"));
        Assertions.assertEquals("1001tracklists", Utils.titleToSlug("1001Tracklists"));
        Assertions.assertEquals("1password", Utils.titleToSlug("1Password"));
        Assertions.assertEquals("3m", Utils.titleToSlug("3M"));
        Assertions.assertEquals("42", Utils.titleToSlug("42"));
        Assertions.assertEquals("4d", Utils.titleToSlug("4D"));
        Assertions.assertEquals("4chan", Utils.titleToSlug("4chan"));
        Assertions.assertEquals("500px", Utils.titleToSlug("500px"));
        Assertions.assertEquals("aframe", Utils.titleToSlug("A-Frame"));
        Assertions.assertEquals("abbott", Utils.titleToSlug("Abbott"));
        Assertions.assertEquals("abbrobotstudio", Utils.titleToSlug("ABB RobotStudio"));
    }

    @Test
    public void slugToClassNameTest() {
        Assertions.assertEquals("DotnetIcon", Utils.slugToClassName("dotnet"));
        Assertions.assertEquals("EIcon", Utils.slugToClassName("e"));
        Assertions.assertEquals("OnethousandonetracklistsIcon", Utils.slugToClassName("1001tracklists"));
        Assertions.assertEquals("OnepasswordIcon", Utils.slugToClassName("1password"));
        Assertions.assertEquals("ThreemIcon", Utils.slugToClassName("3m"));
        Assertions.assertEquals("FourtytwoIcon", Utils.slugToClassName("42"));
        Assertions.assertEquals("FourchanIcon", Utils.slugToClassName("4chan"));
        Assertions.assertEquals("FourdIcon", Utils.slugToClassName("4d"));
        Assertions.assertEquals("FivehundredpxIcon", Utils.slugToClassName("500px"));
        Assertions.assertEquals("AframeIcon", Utils.slugToClassName("aframe"));
        Assertions.assertEquals("AbbottIcon", Utils.slugToClassName("abbott"));
        Assertions.assertEquals("AbbrobotstudioIcon", Utils.slugToClassName("abbrobotstudio"));
    }

}
