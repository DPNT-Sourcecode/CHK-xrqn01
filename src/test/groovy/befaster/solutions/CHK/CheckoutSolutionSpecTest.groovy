package befaster.solutions.CHK

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class CheckoutSolutionSpecTest extends Specification {

    static INVALID_PRODUCT_ID = 'X'

    @Subject
    def solution = new CheckoutSolution()

    /**
     * +------+-------+------------------------+
     * | Item | Price | Special offers         |
     * +------+-------+------------------------+
     * | A    | 50    | 3A for 130, 5A for 200 |
     * | B    | 30    | 2B for 45              |
     * | C    | 20    |                        |
     * | D    | 15    |                        |
     * | E    | 40    | 2E get one B free      |
     * +------+-------+------------------------+
     */

    @Unroll
    "should return #expectedPrice for given products #productsString"() {
        when:
          def actualPrice = solution.checkout productsString

        then:
          actualPrice == expectedPrice

        where:
          productsString          || expectedPrice
          null                    || -1
          ""                      || 0
          "${INVALID_PRODUCT_ID}" || -1

    }

}
