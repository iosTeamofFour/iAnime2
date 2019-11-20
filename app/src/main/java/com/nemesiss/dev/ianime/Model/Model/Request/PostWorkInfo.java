package com.nemesiss.dev.ianime.Model.Model.Request;

import java.util.List;

public class PostWorkInfo {
    private String name;
    private int created;
    private String description;
    private List<String> tags;
    private Boolean allow_download;
    private Boolean allow_sketch;
    private Boolean allow_fork;
    private String original_image;
    private String colorization_image;

    public int getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public Boolean getAllow_sketch() {
        return allow_sketch;
    }

    public Boolean getAllow_fork() {
        return allow_fork;
    }

    public Boolean getAllow_download() {
        return allow_download;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getColorization_image() {
        return colorization_image;
    }

    public String getOriginal_image() {
        return original_image;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllow_sketch(Boolean allow_sketch) {
        this.allow_sketch = allow_sketch;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAllow_fork(Boolean allow_fork) {
        this.allow_fork = allow_fork;
    }

    public void setAllow_download(Boolean allow_download) {
        this.allow_download = allow_download;
    }

    public void setColorization_image(String colorization_image) {
        this.colorization_image = colorization_image;
    }

    public void setOriginal_image(String original_image) {
        this.original_image = original_image;
    }
}
