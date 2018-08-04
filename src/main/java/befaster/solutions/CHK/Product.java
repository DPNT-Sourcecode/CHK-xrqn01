package befaster.solutions.CHK;

import java.util.Objects;

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

    @Override
    public String toString() {
        return String.valueOf(id) + '(' + price + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
