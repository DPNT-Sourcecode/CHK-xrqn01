package befaster.solutions.CHK;

public class SameFreebiePriceCalculationStrategy extends FreebiePriceCalculationStrategy {

    private final char productId;
    private final int requiredProductsAmount;

    SameFreebiePriceCalculationStrategy(char productId, int requiredProductsAmount) {
        super(productId, requiredProductsAmount + 1, productId);
        this.productId = productId;
        this.requiredProductsAmount = requiredProductsAmount;
    }

    @Override
    public String toString() {
        return String.valueOf(requiredProductsAmount) + productId + " get one " + productId + " free";
    }

}
