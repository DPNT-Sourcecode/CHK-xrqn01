package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.Arrays;
import java.util.List;

public class CheckoutSolution {

    private final List<Product> products;
    private final List<SpecialOffer> specialOffers;

    public CheckoutSolution() {
        final Product productA = new Product('A', 50);
        final Product productB = new Product('B', 30);
        this.products = Arrays.asList(productA,
                                      productB,
                                      new Product('C', 20),
                                      new Product('D', 15));
        this.specialOffers = Arrays.asList(new SpecialOffer(productA, 3, 130),
                                           new SpecialOffer(productB, 2, 45));
    }

    public CheckoutSolution(List<Product> products, List<SpecialOffer> specialOffers) {
        this.products = products;
        this.specialOffers = specialOffers;
    }

    public Integer checkout(String skus) {
        throw new SolutionNotImplementedException();
    }
}
