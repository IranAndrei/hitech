/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import model.entity.Product;

/**
 *
 * @author Lucas
 */
@Named(value = "dataScrollerView")
@ViewScoped
public class DataScrollerView implements Serializable {

    private List<Product> products;

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products = service.getProducts(100);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setService(ProductService service) {
        this.service = service;
    }
    
    public DataScrollerView() {
    }
    
}
