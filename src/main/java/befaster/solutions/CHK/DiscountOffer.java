package befaster.solutions.CHK;

final class DiscountOffer {

    private final Product product;
    private final int productsAmount;
    private final int discountPrice;

    DiscountOffer(Product product, int productsAmount, int discountPrice) {
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
