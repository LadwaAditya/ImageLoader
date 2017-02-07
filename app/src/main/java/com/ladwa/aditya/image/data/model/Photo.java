package com.ladwa.aditya.image.data.model;

/**
 * Created by Aditya on 07-Feb-17.
 */

public class Photo {
    private Long id;
    private String displayName;
    private String uri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
