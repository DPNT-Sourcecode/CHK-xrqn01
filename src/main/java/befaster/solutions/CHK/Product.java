package befaster.solutions.CHK;

public final class Product {

    private final char id;
    private final int price;

    public Product(char id, int price) {
        this.id = id;
        this.price = price;
    }

    public char getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
}
