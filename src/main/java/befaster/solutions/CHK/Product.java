package befaster.solutions.CHK;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@Getter
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor(access = PACKAGE)
final class Product {

    private final char id;
    private final int price;

    @Override
    public String toString() {
        return String.valueOf(id) + '(' + price + ')';
    }

}
