
import java.util.HashMap;
import java.util.Map;

public class StoreInventory {
    private Map<Integer, Product> inventory;

    public StoreInventory() {
        this.inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getId(), product);
    }

    public void removeProduct(int id) {
        inventory.remove(id);
    }

    public void updateProductQuantity(int id, int quantity) {
        Product product = inventory.get(id);
        if (product != null) {
            product.setQuantity(quantity);
        }
    }

    public Map<Integer, Product> viewInventory() {
        return inventory;
    }

    public double calculateTotalInventoryValue() {
        double totalValue = 0;
        for (Product product : inventory.values()) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        return totalValue;
    }
}