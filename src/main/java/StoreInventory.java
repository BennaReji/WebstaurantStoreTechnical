
import java.util.HashMap;
import java.util.Map;


public class StoreInventory {
    private Map<Integer, Product> inventory;

    public StoreInventory() {
        this.inventory = new HashMap<>();
    }

    public boolean addProduct(Product product) {
        if (product == null || product.getQuantity() < 0 || product.getPrice() < 0) {
            return false;
        }

        inventory.put(product.getId(), product);
        return true;
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

    public Map<Integer, Product> viewCurrentInventory() {
        return inventory;
    }


    public double totalInventoryValue() {
        double totalValue = 0;
        for (Product product : inventory.values()) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        return totalValue;
    }

    public void displayTotalInventoryValue() {
        System.out.println("Total value of the inventory: $" + totalInventoryValue());
    }
}