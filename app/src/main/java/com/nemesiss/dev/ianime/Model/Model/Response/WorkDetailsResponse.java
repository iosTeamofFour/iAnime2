package com.nemesiss.dev.ianime.Model.Model.Response;

import java.util.List;

public class WorkDetailsResponse  {
    private int id;
    private String artist;
    private String artist_name;
    private String name;
    private int created;
    private String description;
    private List<String> tags;
    private int forks;
    private int like;
    private Boolean allow_download;
    private Boolean allow_sketch;
    private Boolean allow_fork;

    public int getCreated() {
        return created;
    }

    public int getId() {
        return id;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getForks() {
        return forks;
    }

    public String getArtist() {
        return artist;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Boolean getAllow_download() {
        return allow_download;
    }

    public Boolean getAllow_fork() {
        return allow_fork;
    }

    public Boolean getAllow_sketch() {
        return allow_sketch;
    }

    public int getLike() {
        return like;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setAllow_download(Boolean allow_download) {
        this.allow_download = allow_download;
    }

    public void setAllow_fork(Boolean allow_fork) {
        this.allow_fork = allow_fork;
    }

    public void setAllow_sketch(Boolean allow_sketch) {
        this.allow_sketch = allow_sketch;
    }

}
