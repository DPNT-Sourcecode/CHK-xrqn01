package befaster.solutions.CHK

import spock.lang.Specification
import spock.lang.Unroll

class CheckoutSolutionSpecTest extends Specification {

    static INVALID_PRODUCT_ID = 'X'

    @Unroll
    "should return #expectedPrice for products=#products and special offers=#specialOffers and given #productsString"() {
        given:
          def solution = new CheckoutSolution(products, specialOffers)

        when:
          def actualPrice = solution.checkout productsString

        then:
          actualPrice == expectedPrice

        where:
          products                             | specialOffers       | productsString          || expectedPrice
          []                                   | []                  | null                    || -1
          [product('A', 50)]                   | []                  | "${INVALID_PRODUCT_ID}" || -1
          [product('A', 50)]                   | []                  | "A"                     || 50
          [product('A', 50)]                   | [offer('A', 2, 20)] | "AA"                    || 20
          [product('A', 50), product('B', 30)] | [offer('B', 2, 20)] | "ABAB"                  || 120
          [product('A', 50), product('B', 30)] | [offer('B', 2, 20)] | "AABB"                  || 120
          [product('A', 50), product('B', 30)] | [offer('B', 2, 20)] | "BAABB"                 || 150
    }

    static offer(String productId, int amount, int price) {
        new SpecialOffer(new Product(productId.charAt(0), -1), amount, price)
    }

    static product(String id, int price) {
        new Product(id.charAt(0), price)
    }

}
