package dto;

public class BookWithAuthor {

    private int bookId;
    private String bookName;
    private String format;
    private String authorName;

    public BookWithAuthor(int bookId, String bookName, String format, String authorName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.format = format;
        this.authorName = authorName;
    }

    // ✅ ГЕТТЕРЫ ДЛЯ ТАБЛИЦЫ
    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getFormat() {
        return format;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public String toString() {
        return bookId + ") " + bookName + " (" + format + ") - " + authorName;
    }
}
