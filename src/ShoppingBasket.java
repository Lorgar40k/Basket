import java.util.*;
import java.util.stream.Collectors;

interface Basket {
    void addProduct(String product, int quantity);
    void removeProduct(String product);
    void updateProductQuantity(String product, int quantity);
    void clear();
    List<String> getProducts();
    int getProductQuantity(String product);
}

public class ShoppingBasket implements Basket {

    private final Map<String, Integer> products = new HashMap<>();

    @Override
    public void addProduct(String product, int quantity) {
        products.merge(product, quantity, Integer::sum);
    }

    @Override
    public void removeProduct(String product) {
        products.remove(product);
    }

    @Override
    public void updateProductQuantity(String product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, quantity);
        } else {
            System.out.println("Товар \"" + product + "\" не найден в корзине.");
        }
    }

    @Override
    public void clear() {
        products.clear();
    }

    @Override
    public List<String> getProducts() {
        return products.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public int getProductQuantity(String product) {
        return products.getOrDefault(product, 0);
    }

    // Дополнительно: красиво выводим корзину
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста.");
        } else {
            System.out.println("Товары в корзине:");
            products.entrySet().stream()
                    .forEach(e -> System.out.println("- " + e.getKey() + ": " + e.getValue() + " шт."));
        }
    }

    // Пример использования
    public static void main(String[] args) {
        ShoppingBasket basket = new ShoppingBasket();

        basket.addProduct("Хлеб", 2);
        basket.addProduct("Молоко", 1);
        basket.addProduct("Яблоки", 5);
        basket.updateProductQuantity("Молоко", 3);
        basket.removeProduct("Хлеб");

        basket.printBasket();

        System.out.println("\nВсего товаров: " + basket.getProducts().size());
        System.out.println("Количество яблок: " + basket.getProductQuantity("Яблоки"));
    }
}
