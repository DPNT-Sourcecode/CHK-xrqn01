package befaster.solutions.CHK

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class CheckoutSolutionSpecTest extends Specification {

    static INVALID_PRODUCT_ID = 'X'


    @Subject
    def solution = new CheckoutSolution()

    @Unroll
    "should return #expectedPrice for products=#products and special offers=#specialOffers and given #productsString"() {


        where:
          products           | specialOffers | productsString          || expectedPrice
          []                 | []            | null                    || -1
          [product('A', 50)] | []            | "${INVALID_PRODUCT_ID}" || -1
          [product('A', 50)] | []            | "A"                     || 50
          [product('A', 50)] | []            | "A"                     || 50

    }

    static offer(String productId, int amount, int price) {
        new SpecialOffer(new Product(productId.charAt(0), _ as int), amount, price)
    }

    static product(String id, int price) {
        new Product(id.charAt(0), price)
    }

}
