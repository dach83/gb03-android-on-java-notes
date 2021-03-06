package com.example.gb03_android_on_java_notes.domain;

import java.util.Date;

public class Note {
    private final int id;
    private Date date;
    private Color color;
    private String header;
    private String text;

    public Note(int id, Date date, Color color, String header, String text) {
        this.id = id;
        this.date = date;
        this.color = color;
        this.header = header;
        this.text = text;
    }

    public Note(int id, String header, String text) {
        this(id, new Date(), Color.randomColor(), header, text);
    }

    public Note(int id) {
        this(id, "", "");
    }

    public int getId() {
        return id;
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
