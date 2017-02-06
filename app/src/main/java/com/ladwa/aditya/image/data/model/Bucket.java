package com.ladwa.aditya.image.data.model;

/**
 * Created by Aditya on 04-Feb-17.
 */

public class Bucket {
    private String albumName;
    private Integer totalPhoto;
    private String thumbnailUri;
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
