package befaster.solutions.CHK;

public final class SpecialOffer {

    private final char productId;
    private final int productsAmount;
    private final int discountPrice;

    public SpecialOffer(Product product, int productsAmount, int discountPrice) {
        this.productId = product.getId();
        this.productsAmount = productsAmount;
        this.discountPrice = discountPrice;
    }

    public char getProductId() {
        return productId;
    }

    public int getProductsAmount() {
        return productsAmount;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
