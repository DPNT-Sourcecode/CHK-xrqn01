package befaster.solutions.CHK;

import lombok.Builder;

@Builder
public class SameFreebiePriceCalculationStrategy extends FreebiePriceCalculationStrategy {

    private final char productId;

    SameFreebiePriceCalculationStrategy(char productId, int requiredProductsAmount) {
        super(productId, requiredProductsAmount + 1, productId);
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "";
    }
}
