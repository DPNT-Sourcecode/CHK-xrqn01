package befaster.solutions.CHK

import spock.lang.Specification
import spock.lang.Unroll

import static java.util.stream.Collectors.toMap

class CheckoutSolutionSpecTest extends Specification {

    static INVALID_PRODUCT_ID = 'X'

    @Unroll
    "should return #expectedPrice for products=#products and special offers=#specialOffers and given #productsString"() {
        given:
          Map<Character, Product> productsMap = products.stream().collect(toMap({ it.id }, { it }))
          Map<Character, SpecialOffer> specialOfferMap = specialOffers.stream().collect(toMap({ it.productId }, { it }))
          def solution = new CheckoutSolution(productsMap, specialOfferMap)

        when:
          def actualPrice = solution.checkout productsString

        then:
          actualPrice == expectedPrice

        where:
          products                             | specialOffers       | productsString            || expectedPrice
          []                                   | []                  | null                      || -1
          []                                   | []                  | ""                        || 0
          [product('A', 50)]                   | []                  | "${INVALID_PRODUCT_ID}"   || -1
          [product('A', 50)]                   | []                  | "AA${INVALID_PRODUCT_ID}" || -1
          [product('A', 50)]                   | []                  | "A"                       || 50
          [product('A', 50)]                   | [offer('A', 2, 20)] | "AA"                      || 20
          [product('A', 50), product('B', 30)] | [offer('B', 2, 20)] | "ABAB"                    || 120
          [product('A', 50), product('B', 30)] | [offer('B', 2, 20)] | "AABB"                    || 120
          [product('A', 50), product('B', 30)] | [offer('B', 2, 20)] | "BAABB"                   || 150
          exerciseProducts                     | exerciseOffers      | ""                        || 0
          exerciseProducts                     | exerciseOffers      | "B"                       || 30
          exerciseProducts                     | exerciseOffers      | "ABCD"                    || 115
    }

    static offer(String productId, int amount, int price) {
        new SpecialOffer(new Product(productId.charAt(0), -1), amount, price)
    }

    static product(String id, int price) {
        new Product(id.charAt(0), price)
    }

}
