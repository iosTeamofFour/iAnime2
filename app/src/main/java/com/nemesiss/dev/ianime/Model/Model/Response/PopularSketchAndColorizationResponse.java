package com.nemesiss.dev.ianime.Model.Model.Response;

public class PopularSketchAndColorizationResponse {
    private int id;
    private String name;
    private int created;
    private String artist_name;

    public String getName() {
        return name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public int getId() {
        return id;
    }

    public int getCreated() {
        return created;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
