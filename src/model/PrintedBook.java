package model;

public class PrintedBook extends BookBase {

    private int pages;

    public PrintedBook(int id, String name, int pages) {
        super(id, name);
        this.pages = pages;
    }

    @Override
    public String getFormat() {
        return "PRINTED";
    }

    @Override
    public double getPrice() {
        return 14.99;
    }
}
