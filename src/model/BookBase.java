package model;

import interfaces.PricedItem;

public abstract class BookBase implements PricedItem {

    private int id;
    private String name;
    private Author author;

    protected BookBase(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract String getFormat();
    public abstract double getPrice();

    public String getDisplayInfo() {
        return name + " (" + getFormat() + ")";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
