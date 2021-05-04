/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import model.entity.InventoryStatus;
import model.entity.Product;

/**
 *
 * @author Lucas
 */
@Named(value = "productService")
@ApplicationScoped
public class ProductService {

    List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1008, "vbb124btr", "Game Controller", "Product Description", "game-controller.jpg", 99, "Electronics", 2, InventoryStatus.LOWSTOCK, 4));
        products.add(new Product(1009, "cm230f032", "Gaming Set", "Product Description", "gaming-set.jpg", 299, "Electronics", 63, InventoryStatus.INSTOCK, 3));
        products.add(new Product(1011, "4920nnc2d", "Green Earbuds", "Product Description", "green-earbuds.jpg", 89, "Electronics", 23, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1014, "waas1x2as", "Headphones", "Product Description", "headphones.jpg", 175, "Electronics", 8, InventoryStatus.LOWSTOCK, 5));
        products.add(new Product(1027, "acvx872gc", "Yellow Earbuds", "Product Description", "yellow-earbuds.jpg", 89, "Electronics", 35, InventoryStatus.INSTOCK, 3));
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Product> getProducts(int size) {

        if (size > products.size()) {
            Random rand = new Random();

            List<Product> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(products.size());
                randomList.add(products.get(randomIndex));
            }

            return randomList;
        } else {
            return new ArrayList<>(products.subList(0, size));
        }

    }

    public List<Product> getClonedProducts(int size) {
        List<Product> results = new ArrayList<>();
        List<Product> originals = getProducts(size);
        for (Product original : originals) {
            results.add(original.clone());
        }
        return results;
    }

}
