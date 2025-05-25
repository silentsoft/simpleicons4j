package org.silentsoft.simpleicons;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SimpleIconsTest {

    @Test
    public void getTest() throws IOException {
        JSONArray icons = IconGenerator.getSimpleIcons();
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

}
