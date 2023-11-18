package com.example.notes.model;

public class Notebook {
    private String id; // поле идентификатора записи в блокноте
    private String heading; // поле заголовка записи в блокноте
    private String description; // поле описания записи в блокноте

    // конструктор
    public Notebook(String id, String heading, String description) {
        this.id = id;
        this.heading = heading;
        this.description = description;
    }

    // геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
