package com.example.gb03_android_on_java_notes.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Note implements Serializable {
    private UUID id;
    private Date date;
    private Color color;
    private String header;
    private String text;

    public Note() {
        this.id = UUID.randomUUID();
        this.color = Color.randomColor();
        this.date = new Date();
    }

    public Note(Date date, Color color, String header, String text) {
        this();
        this.date = date;
        this.color = color;
        this.header = header;
        this.text = text;
    }

    public Note(String header, String text) {
        this();
        this.header = header;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return text;
    }

    public void setContent(String text) {
        this.text = text;
    }
}
