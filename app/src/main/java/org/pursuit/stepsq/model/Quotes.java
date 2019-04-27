package org.pursuit.stepsq.model;

public class Quotes {

    private String quote;
    private String length;
    private String author;
    private String[] tags;
    private String category;
    private String date;
    private String title;
    private String background;
    private String id;

    public Quotes(String quote, String length, String author,
                  String[] tags, String category, String date,
                  String title, String background, String id) {
        this.quote = quote;
        this.length = length;
        this.author = author;
        this.tags = tags;
        this.category = category;
        this.date = date;
        this.title = title;
        this.background = background;
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public String getLength() {
        return length;
    }

    public String getAuthor() {
        return author;
    }

    public String[] getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getBackground() {
        return background;
    }

    public String getId() {
        return id;
    }
}
