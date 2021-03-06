/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.dao.ProdutoDAO;
import model.entity.Produto;
import static org.primefaces.component.focus.FocusBase.PropertyKeys.context;
import org.primefaces.model.file.UploadedFile;
import utils.Utilidade;

/**
 *
 * @author Lucas
 */
@Named(value = "produtoB")
@RequestScoped
public class ProdutoB {

    private List<Produto> carrinho;
    
    @Inject
    private ProdutoDAO produtoDAO;
    
    private Integer id;
    private String nome;
    private String descricao;
    private String foto;
    private Double preco;
    
    private UploadedFile imagem;
    
    public ProdutoB() {
        carrinho = new ArrayList<>();
        
        if (Utilidade.verificaExisteRegistroSessao("carrinho"))
        {
            carrinho = (List<Produto>) Utilidade.recuperaRegistroSessao("carrinho");
        }
    }
    
    public List<Produto> getTodosProdutos()
    {
        return produtoDAO.getAllResults("produto.findAll");
    }
    
    public String adicionarCarrinho(Produto p)
    {
        getCarrinho().add(p);
        Utilidade.addMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "O produto foi adicionado ao carrinho");
   
        Utilidade.salvaRegistroSessao("carrinho", getCarrinho());
              
        return "carinhoCompra?faces-redirect=true";
    }
    
    public String salvarProduto() {
        
        try
        {
            String caminho = Utilidade.getRealPath();
            
            File file = new File(caminho + "/resources/produtos/" + imagem.getFileName());
            
            OutputStream out = new FileOutputStream(file);
            out.write(imagem.getContent());
            out.close();
            
            foto = imagem.getFileName();
        }
        catch (Exception ex)
        {
             Utilidade.addMessage(FacesMessage.SEVERITY_FATAL, "Erro ao salvar a imagem", "Erro: " + ex.getLocalizedMessage());
             
             FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
             
             return "index?faces-redirect=true";
        }
        
        Produto p = new Produto();

        p.setFoto(foto);
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setPreco(preco);

        p = produtoDAO.save(p);
        
        if (p == null)
        {
            Utilidade.addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar", "N??o foi possivel salvar!");
        }
        else
        {
            Utilidade.addMessage(FacesMessage.SEVERITY_INFO, "Registrado", "O produto foi registrado com sucesso!");
        }
        
        return "listaProdutos?faces-redirect=true";
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

    /**
     * @return the imagem
     */
    public UploadedFile getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(UploadedFile imagem) {
        this.imagem = imagem;
    }

    /**
     * @return the carrinho
     */
    public List<Produto> getCarrinho() {
        return carrinho;
    }

    /**
     * @param carrinho the carrinho to set
     */
    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }
}
