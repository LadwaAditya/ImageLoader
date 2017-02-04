package com.ladwa.aditya.image.data.model;

/**
 * Created by Aditya on 04-Feb-17.
 */

public class Bucket {
    private String albumName;
    private Integer totalPhoto;
    private String thumbnailUri;
    private String id;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getTotalPhoto() {
        return totalPhoto;
    }

    public void setTotalPhoto(Integer totalPhoto) {
        this.totalPhoto = totalPhoto;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
