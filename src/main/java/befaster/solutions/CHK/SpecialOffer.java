package befaster.solutions.CHK;

public final class SpecialOffer {

    private final Product product;
    private final int productsAmount;
    private final int discountPrice;

    public SpecialOffer(Product product, int productsAmount, int discountPrice) {
        this.product = product;
        this.productsAmount = productsAmount;
        this.discountPrice = discountPrice;
    }

    public char getProductId() {
        return product.getId();
    }

    public int getProductsAmount() {
        return productsAmount;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
