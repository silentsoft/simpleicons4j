package org.silentsoft.simpleicons;

import java.util.Objects;

public class Icon {

    private String title;

    private String slug;

    private String hex;

    private String source;

    private String svg;

    private String path;

    private String guidelines;

    private License license;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGuidelines() {
        return guidelines;
    }

    public void setGuidelines(String guidelines) {
        this.guidelines = guidelines;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Icon)) return false;
        Icon icon = (Icon) o;
        return Objects.equals(getTitle(), icon.getTitle()) && Objects.equals(getSlug(), icon.getSlug()) && Objects.equals(getHex(), icon.getHex()) && Objects.equals(getSource(), icon.getSource()) && Objects.equals(getSvg(), icon.getSvg()) && Objects.equals(getPath(), icon.getPath()) && Objects.equals(getGuidelines(), icon.getGuidelines()) && Objects.equals(getLicense(), icon.getLicense());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getSlug(), getHex(), getSource(), getSvg(), getPath(), getGuidelines(), getLicense());
    }

    @Override
    public String toString() {
        return "Icon{" +
                "title='" + getTitle() + '\'' +
                ", slug='" + getSlug() + '\'' +
                ", hex='" + getHex() + '\'' +
                ", source='" + getSource() + '\'' +
                ", svg='" + getSvg() + '\'' +
                ", path='" + getPath() + '\'' +
                ", guidelines='" + getGuidelines() + '\'' +
                ", license=" + getLicense() +
                '}';
    }

}
