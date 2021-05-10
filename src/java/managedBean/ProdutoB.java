/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.dao.ProdutoDAO;
import model.entity.Produto;

/**
 *
 * @author Lucas
 */
@Named(value = "produtoB")
@RequestScoped
public class ProdutoB {

    @Inject
    private ProdutoDAO produtoDAO;
    
    private Integer id;
    private String nome;
    private String descricao;
    private String foto;
    private Double preco;
    
    public ProdutoB() {
    }
    
    public void salvarProduto() {
        Produto p = new Produto();

        p.setFoto(foto);
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setPreco(preco);

        produtoDAO.save(p);
    }
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
