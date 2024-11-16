import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class StoreInventoryTest {
    private StoreInventory inventory;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    private Product product1;

    @BeforeEach
    public void setUp() {
        inventory = new StoreInventory();
        System.setOut(new PrintStream(outputStream));


        product1 = new Product("product1", 1, 30.0, 2);

        inventory.addProduct(product1);
    }

    @Test
    public void testAddProduct() {
        Product product2 = new Product("product2", 2, 30.0, 8);
        inventory.addProduct(product2);
        assertEquals(2, inventory.viewCurrentInventory().size());
        assertTrue(inventory.viewCurrentInventory().containsKey(2));
    }
    @Test
    public void testAddProductWithDuplicateId() {
        Product duplicateProduct = new Product("product", 1, 30.0, 8);
        inventory.addProduct(duplicateProduct);
        assertEquals(1, inventory.viewCurrentInventory().size());
    }
    @Test
    public void testAddProductWithNegativeQuantity() {
        Product product = new Product("product", 1, 20.0, -1);
        boolean result = inventory.addProduct(product);
        assertFalse(result);
        assertFalse(inventory.viewCurrentInventory().containsValue(product));
    }

    @Test
    public void testAddProductWithNegativePrice() {
        Product product = new Product("product", 1, -0.01, 5);
        boolean result = inventory.addProduct(product);
        assertFalse(result);
        assertFalse(inventory.viewCurrentInventory().containsValue(product));
    }




    @Test
    public void testRemoveProduct() {
        inventory.removeProduct(2);
        assertEquals(1, inventory.viewCurrentInventory().size());
        assertFalse(inventory.viewCurrentInventory().containsKey(2));
    }

    @Test
    public void testRemoveAllProduct(){
        Product product2 = new Product("product2", 2, 300.0, 8);
        inventory.addProduct(product2);
        inventory.removeProduct(1);
        inventory.removeProduct(2);
        assertEquals(0, inventory.viewCurrentInventory().size());
        assertFalse(inventory.viewCurrentInventory().containsKey(2));
        assertFalse(inventory.viewCurrentInventory().containsKey(1));
    }




    @Test
    public void testUpdateProductQuantity() {
        inventory.updateProductQuantity(1, 7);
        assertEquals(7, inventory.viewCurrentInventory().get(1).getQuantity());
    }

    @Test
    public void testUpdateQuantityForNonExistentProduct() {
        inventory.updateProductQuantity(4, 10);
        assertNull(inventory.viewCurrentInventory().get(4));
    }

    @Test
    public void testViewCurrentInventory() {
        assertEquals(1, inventory.viewCurrentInventory().size());
        assertTrue(inventory. viewCurrentInventory().containsKey(1));
        Product product1 = inventory.viewCurrentInventory().get(1);
        assertEquals("product1", product1.getName());
        assertEquals(30.0, product1.getPrice());
        assertEquals(2, product1.getQuantity());
    }

    @Test
    public void testTotalInventoryValue() {
        Product product3 = new Product("product3", 3, 20.0, 1);
        inventory.addProduct(product3);
        double totalValue = inventory.totalInventoryValue();
        assertEquals(30.0 * 2 + 20.0 * 1, totalValue, 0.001);
        inventory.displayTotalInventoryValue();
    }
    @Test
    public void testDisplayTotalInventoryValue(){
        inventory.displayTotalInventoryValue();

        String output = outputStream.toString().trim();

        double expectedTotalValue = (30.0 * 2);
        String expectedOutput = "Total value of the inventory: $" + expectedTotalValue;

        assertEquals(expectedOutput, output);



    }
}
