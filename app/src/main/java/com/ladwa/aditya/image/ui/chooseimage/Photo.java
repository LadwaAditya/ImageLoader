package com.ladwa.aditya.image.ui.chooseimage;

/**
 * Created by Aditya on 07-Feb-17.
 */

public class Photo {
    private Long id;
    private String albumName;
    private String uri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
