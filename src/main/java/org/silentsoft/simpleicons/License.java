package org.silentsoft.simpleicons;

import java.util.Objects;

public class License {

    private String type;

    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        License license = (License) o;
        return Objects.equals(getType(), license.getType()) && Objects.equals(getUrl(), license.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getUrl());
    }

    @Override
    public String toString() {
        return "License{" +
                "type='" + getType() + '\'' +
                ", url='" + getUrl() + '\'' +
                '}';
    }

}
