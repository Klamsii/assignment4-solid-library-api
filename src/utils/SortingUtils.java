package utils;

import model.BookBase;
import java.util.List;

public class SortingUtils {

    public static void sortByName(List<BookBase> books) {
        books.sort((a, b) -> a.getName().compareTo(b.getName()));
    }
}
