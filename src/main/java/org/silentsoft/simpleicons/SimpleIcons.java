package org.silentsoft.simpleicons;

public class SimpleIcons {

    private SimpleIcons() { }

    /**
     * Returns the {@link Icon} with the given slug.
     *
     * @param slug the slug of the icon
     * @return null if the slug is not found, otherwise the {@link Icon} instance
     */
    public static Icon get(String slug) {
        try {
            Class<?> clazz = Class.forName(String.format("%s.icons.%s", SimpleIcons.class.getPackage().getName(), Utils.slugToClassName(slug)));
            return (Icon) clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

}
