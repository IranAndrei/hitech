/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Lucas
 */
@Named(value = "produtoB")
@RequestScoped
public class ProdutoB {

    /**
     * Creates a new instance of ProdutoB
     */
    public ProdutoB() {
    }
    
}
