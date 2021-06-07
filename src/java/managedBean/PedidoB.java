/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import model.dao.PedidoDAO;
import model.dao.ProdutoDAO;
import model.dao.ProdutoPedidoDAO;
import model.entity.Pedido;
import model.entity.Produto;
import model.entity.ProdutoPedido;
import model.entity.Usuario;
import utils.Utilidade;

/**
 *
 * @author Lucas
 */
@Named(value = "pedidoB")
@RequestScoped
public class PedidoB {

    /**
     * Creates a new instance of PedidoB
     */
    
    @Inject
    private PedidoDAO pedidoDAO;
    @Inject
    private ProdutoPedidoDAO produtoPedidoDAO;
    
    private Usuario usuario;
    private List<Produto> carrinho;
    
    public PedidoB() {
        
        carrinho = new ArrayList<>();
        
        if(Utilidade.verificaExisteRegistroSessao("carrinho"))
        {
            carrinho = (List<Produto>) Utilidade.recuperaRegistroSessao("carrinho");
        }
        
        if (Utilidade.verificaExisteRegistroSessao("usuario"))
        {
            usuario = (Usuario) Utilidade.recuperaRegistroSessao("usuario");
        }
    }
    
    public List<Pedido> getTodosPedidos()
    {
        return pedidoDAO.getAllResults("pedido.findAll");
    }
    
    public String efetuarCompra()
    {
        if(carrinho.isEmpty())
        {
            Utilidade.addMessage(FacesMessage.SEVERITY_ERROR, "Carrinho vazio!", "Você não adicionou nenhum produto no carrinho.");
            
            return "index?faces-redirect=true";
        }
        
        double valorTotal = 0;
        
        for (Produto produtoCarrinho : carrinho)
        {
            valorTotal += produtoCarrinho.getPreco();
        }
        
        Pedido pedido = new Pedido();
        
        pedido.setUsuario(usuario);
        pedido.setValorTotal(valorTotal);
        
        pedido = pedidoDAO.save(pedido);
        
        if (pedido == null)
        {
            Utilidade.addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao efetuar o pedido!", "Não foi possivel efetuar o pedido, tente novamente!");
   
            return "index?faces-redirect=true";
        }
        
        for (Produto produtoCarrinho : carrinho)
        {
            ProdutoPedido produtoPedido = new ProdutoPedido();
            
            produtoPedido.setPedido(pedido);
            produtoPedido.setProduto(produtoCarrinho);
            
            produtoPedido = produtoPedidoDAO.save(produtoPedido);
            
            if (produtoPedido == null)
            {
                Utilidade.addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao efetuar o pedido!", "Não foi possivel efetuar o pedido, tente novamente!");
            }
            
            pedido.addProdutoPedido(produtoPedido);
        }
        
        pedidoDAO.save(pedido);
        
        Utilidade.addMessage(FacesMessage.SEVERITY_INFO, "Pedido salvo com sucesso!", "Aguardando o pagamento!");
        
        carrinho = new ArrayList<>();
        
        Utilidade.salvaRegistroSessao("carrinho", carrinho);
        
        return "index?faces-redirect=true";
    }
}
