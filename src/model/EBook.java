package model;

public class EBook extends BookBase {

    private double fileSize;

    public EBook(int id, String name, double fileSize) {
        super(id, name);
        this.fileSize = fileSize;
    }

    @Override
    public String getFormat() {
        return "EBOOK";
    }

    @Override
    public double getPrice() {
        return 6.99;
    }
}
