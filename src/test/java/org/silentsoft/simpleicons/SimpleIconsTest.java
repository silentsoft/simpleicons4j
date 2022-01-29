package org.silentsoft.simpleicons;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SimpleIconsTest {

    @Test
    public void getTest() throws IOException {
        JSONArray icons = IconGenerator.getSimpleIconsFromWebjar();
        for (Object json : icons) {
            Icon icon1 = IconGenerator.toIcon((JSONObject) json);
            IconGenerator.fill(icon1);

            Assertions.assertEquals(icon1, icon1);
            Assertions.assertNotEquals(icon1, null);
            Assertions.assertNotEquals(icon1, new Icon());
            Assertions.assertNotEquals(icon1, new Object());

            Icon icon2 = SimpleIcons.get(icon1.getSlug());

            Assertions.assertEquals(icon1, icon2);
            Assertions.assertEquals(icon1.hashCode(), icon2.hashCode());
            Assertions.assertEquals(icon1.toString(), icon2.toString());
        }

        Assertions.assertNull(SimpleIcons.get("not-exist-icon"));
    }

    @Test
    public void sampleTest() {
        Icon dotnetIcon = SimpleIcons.get("dotnet");
        Assertions.assertEquals(".NET", dotnetIcon.getTitle());
        Assertions.assertEquals("dotnet", dotnetIcon.getSlug());
        Assertions.assertEquals("512BD4", dotnetIcon.getHex());
        Assertions.assertEquals("https://github.com/dotnet/brand/", dotnetIcon.getSource());
        Assertions.assertNull(dotnetIcon.getLicense());
        Assertions.assertNull(dotnetIcon.getGuidelines());
        String svg = "<svg role=\"img\" viewBox=\"0 0 24 24\" xmlns=\"http://www.w3.org/2000/svg\"><title>.NET</title><path d=\"M24 8.77h-2.468v7.565h-1.425V8.77h-2.462V7.53H24zm-6.852 7.565h-4.821V7.53h4.63v1.24h-3.205v2.494h2.953v1.234h-2.953v2.604h3.396zm-6.708 0H8.882L4.78 9.863a2.896 2.896 0 0 1-.258-.51h-.036c.032.189.048.592.048 1.21v5.772H3.157V7.53h1.659l3.965 6.32c.167.261.275.442.323.54h.024c-.04-.233-.06-.629-.06-1.185V7.529h1.372zm-8.703-.693a.868.829 0 0 1-.869.829.868.829 0 0 1-.868-.83.868.829 0 0 1 .868-.828.868.829 0 0 1 .869.829Z\"/></svg>";
        Assertions.assertEquals(svg, dotnetIcon.getSvg());
        String path = "M24 8.77h-2.468v7.565h-1.425V8.77h-2.462V7.53H24zm-6.852 7.565h-4.821V7.53h4.63v1.24h-3.205v2.494h2.953v1.234h-2.953v2.604h3.396zm-6.708 0H8.882L4.78 9.863a2.896 2.896 0 0 1-.258-.51h-.036c.032.189.048.592.048 1.21v5.772H3.157V7.53h1.659l3.965 6.32c.167.261.275.442.323.54h.024c-.04-.233-.06-.629-.06-1.185V7.529h1.372zm-8.703-.693a.868.829 0 0 1-.869.829.868.829 0 0 1-.868-.83.868.829 0 0 1 .868-.828.868.829 0 0 1 .869.829Z";
        Assertions.assertEquals(path, dotnetIcon.getPath());

        Icon freecodecampIcon = SimpleIcons.get("freecodecamp");
        Assertions.assertEquals("freeCodeCamp", freecodecampIcon.getTitle());
        Assertions.assertEquals("freecodecamp", freecodecampIcon.getSlug());
        Assertions.assertEquals("0A0A23", freecodecampIcon.getHex());
        Assertions.assertEquals("https://design-style-guide.freecodecamp.org/", freecodecampIcon.getSource());
        Assertions.assertEquals("https://design-style-guide.freecodecamp.org/", freecodecampIcon.getGuidelines());
        Assertions.assertNotNull(freecodecampIcon.getLicense());
        Assertions.assertEquals("CC-BY-SA-4.0", freecodecampIcon.getLicense().getType());
        Assertions.assertEquals("https://github.com/freeCodeCamp/design-style-guide/blob/cc950c311c61574b6ecbd9e724b6631026e14bfa/LICENSE", freecodecampIcon.getLicense().getUrl());
        License license = new License();
        Assertions.assertNotEquals(license, freecodecampIcon.getLicense());
        license.setType("CC-BY-SA-4.0");
        license.setUrl("https://github.com/freeCodeCamp/design-style-guide/blob/cc950c311c61574b6ecbd9e724b6631026e14bfa/LICENSE");
        Assertions.assertEquals(license, license);
        Assertions.assertEquals(license, freecodecampIcon.getLicense());
        Assertions.assertNotEquals(license, null);
        Assertions.assertNotEquals(license, new License());
        Assertions.assertNotEquals(license, new Object());

    }

}
