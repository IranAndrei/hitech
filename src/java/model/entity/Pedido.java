package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(name = "pedido.findAll", query = "SELECT p FROM Pedido p")
})
public class Pedido implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "valor_total")
    private Double valorTotal;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "pedido")
    private List<ProdutoPedido> produtoPedidos = new ArrayList<>();
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Double getValorTotal()
    {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal)
    {
        this.valorTotal = valorTotal;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public void addProdutoPedido(ProdutoPedido pp)
    {
        pp.setPedido(this);
        this.produtoPedidos.add(pp);
    }
    /**
     * @return the produtoPedidos
     */
    public List<ProdutoPedido> getProdutoPedidos() {
        return produtoPedidos;
    }

    /**
     * @param produtoPedidos the produtoPedidos to set
     */
    public void setProdutoPedidos(List<ProdutoPedido> produtoPedidos) {
        this.produtoPedidos = produtoPedidos;
    }
    
}
