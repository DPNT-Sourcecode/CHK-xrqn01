package befaster.solutions.CHK;

final class Product {

    private final char id;
    private final int price;

    Product(char id, int price) {
        this.id = id;
        this.price = price;
    }

    char getId() {
        return id;
    }

    int getPrice() {
        return price;
    }
}
