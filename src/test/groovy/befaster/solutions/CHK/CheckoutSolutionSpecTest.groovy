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
          products | specialOffers | productsString          || expectedPrice
          []       | []            | null                    || -1
          []       | []            | "${INVALID_PRODUCT_ID}" || -1

    }

    static product(char id, int price) {
        new Product(id, price)
    }

}
