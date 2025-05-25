import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void testNullListThrowsException() {
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(null, "1234567812345678"));
        assertEquals("allItems list can't be null!", ex.getMessage());
    }

    @Test
    public void testInvalidItemName() {
        List<Item> items = List.of(new Item(null, 1, 100, 0));
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "1234567812345678"));
        assertEquals("Invalid item!", ex.getMessage());
    }

    @Test
    public void testInvalidCardNumber() {
        List<Item> items = List.of(new Item("Apple", 1, 100, 0));
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "abc"));
        assertEquals("Invalid card number!", ex.getMessage());
    }

    @Test
    public void testValidItemNoDiscount() {
        List<Item> items = List.of(new Item("Apple", 2, 100, 0));
        double result = SILab2.checkCart(items, "1234567812345678");
        assertEquals(200, result);
    }

    @Test
    public void testItemWithDiscountAndPriceGreaterThan300() {
        List<Item> items = List.of(new Item("TV", 1, 350, 0.1));
        double result = SILab2.checkCart(items, "1234567812345678");
        // -30 penalty, 350 * 0.9 = 315, total = 285
        assertEquals(285, result);
    }
}
