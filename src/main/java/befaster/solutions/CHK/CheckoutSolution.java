package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.Arrays;
import java.util.List;

public class CheckoutSolution {

    private final List<Product> products;
    private final List<SpecialOffer> specialOffers;

    public CheckoutSolution() {
        this(Arrays.asList(new Product('A', 50)),
             Arrays.asList(new SpecialOffer()));
    }

    public CheckoutSolution(List<Product> products, List<SpecialOffer> specialOffers) {
        this.products = products;
        this.specialOffers = specialOffers;
    }

    public Integer checkout(String skus) {
        throw new SolutionNotImplementedException();
    }
}
