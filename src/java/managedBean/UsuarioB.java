/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.dao.UsuarioDAO;
import model.entity.Usuario;
import utils.Utilidade;

/**
 *
 * @author Lucas Queiroz Rocha dos Reis 
 */
@Named(value = "usuarioB")
@RequestScoped
public class UsuarioB {

    @Inject
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String cep;
    private String administrador;
     
    public UsuarioB() 
    {
        if (Utilidade.verificarExisteSessao("usuario"))
        {
            usuario = (Usuario) Utilidade.verificarSessao("usuario");
        }
    }
    
    /**
    *
    * @author Lucas Queiroz Rocha dos Reis 
    */
    public void salvarUsuario(){
        Usuario u = new Usuario();
        
        u.setNome(nome);
        u.setEmail(email);
        u.setSenha(senha);
        u.setCpf(cpf);
        u.setCep(cep);
        
        usuarioDAO.save(u);
    }
    
    
    
    public String logar()
    {
        usuario = usuarioDAO.logar(email, senha);
        
        if (usuario == null)
        {
            return "loginAdmin";
        }
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
        
        return "gerenciarPedidos?faces-redirect=true";
    }
    
    
    public String logarCliente()
    {
        usuario = usuarioDAO.logarCliente(nome, senha);
        
        if (usuario == null)
        {
            return "loginCliente";
        }
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
        
        return "listaProdutos?faces-redirect=true";
    }
    
    public String logout()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
        
        return "index?faces-redirect=true";
    }
    
    public String getPaginaLoginCliente()
    {
        return "loginCliente";
    }
    
    public String getCadastrarProduto()
    {
        return "cadastrarProduto";
    }
    
    public String getPaginaLoginAdmin()
    {
        return "loginAdmin";
    }
    
    public String getPaginaInicial()
    {
        return "index";
    }
    
    public String getPaginaListaProduto()
    {
        return "listaProdutos";
    }
    
    public String getPaginaRegistro()
    {
        return "cadastrarCliente";
    }
    
    public String getPaginaCarrinho()
    {
        return "carinhoCompra";
    }
    
    /**
     * @return the administrador
     */
    public String getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

     /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

}
