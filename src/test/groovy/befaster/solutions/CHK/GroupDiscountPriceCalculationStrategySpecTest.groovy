package befaster.solutions.CHK

import spock.lang.Specification
import spock.lang.Subject

class GroupDiscountPriceCalculationStrategySpecTest extends Specification {

    static APPLICABLE_PRODUCTS = ['A' as char, 'B' as char, 'C' as char]

    @Subject
    def strategy = new GroupDiscountPriceCalculationStrategy()

}
