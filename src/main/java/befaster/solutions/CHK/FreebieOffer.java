package befaster.solutions.CHK;

public class FreebieOffer {

    private final Product product;
    private final int productsAmount;
    private final Product freeProduct;

    FreebieOffer(Product product, int productsAmount, Product freeProduct) {
        this.product = product;
        this.productsAmount = productsAmount;
        this.freeProduct = freeProduct;
    }

    char getProductId() {
        return product.getId();
    }

    int getProductsAmount() {
        return productsAmount;
    }

    Product getFreeProduct() {
        return freeProduct;
    }

    @Override
    public String toString() {
        return "Offer(" + productsAmount + getProductId() + " + free " + freeProduct + ')';
    }

}
