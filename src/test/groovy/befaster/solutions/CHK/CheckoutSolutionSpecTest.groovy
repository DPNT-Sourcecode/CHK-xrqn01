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
          "${INVALID_PRODUCT_ID}" || -1
          ""                      || 0
          "A"                     || 50
          "AAA"                   || 130
          "AAABB"                 || 130 + 45
          "BBAAA"                 || 130 + 45
          "BAAAB"                 || 130 + 45
          "AAAAA"                 || 200
          "AAAAAA"                || 250
          "B"                     || 30
          "BB"                    || 45
          "EEB"                   || (2 * 40)
          "EEBB"                  || (2 * 40) + 30
          "AAAEEBB"               || 130 + (2 * 40) + 30

    }

}
