package befaster.solutions.CHK;

public class FreebieOffer {

    private final Product product;
    private final int productsAmount;
    private final Product freeProduct;

    FreebieOffer(Product product, int productsAmount, int discountPrice) {
        this.product = product;
        this.productsAmount = productsAmount;
        this.discountPrice = discountPrice;
    }

    char getProductId() {
        return product.getId();
    }

    int getProductsAmount() {
        return productsAmount;
    }

    int getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public String toString() {
        return "Offer(" + productsAmount + getProductId() + " for " + discountPrice + ')';
    }

}
