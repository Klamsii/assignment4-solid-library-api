package interfaces;

public interface PricedItem {

    double getPrice();

    default boolean isExpensive() {
        return getPrice() > 10;
    }

    static void printPrice(PricedItem item) {
        System.out.println("Price: " + item.getPrice());
    }
}
