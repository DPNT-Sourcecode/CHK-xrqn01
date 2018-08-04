package befaster.solutions.CHK;

final class SpecialOffer {

    private final Product product;
    private final int productsAmount;
    private final int discountPrice;

    SpecialOffer(Product product, int productsAmount, int discountPrice) {
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
}
