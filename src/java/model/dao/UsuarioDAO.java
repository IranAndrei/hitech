/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.enterprise.context.Dependent;
import model.entity.Usuario;

/**
 *
 * @author Lucas
 */
@Dependent
public class UsuarioDAO extends BaseDao <Usuario> {
    
    public Usuario logar(String email, String senha){
        
        try
        {
            return (Usuario) getEntityManager().createNamedQuery("usuario.logar")
                .setParameter("email", email)
                .setParameter("senha", senha)
                .getSingleResult();
        }
        catch (Exception e)
        {
            System.out.println("Erro: "+ e.getLocalizedMessage());
            return null;
        }
    }
    
    public Usuario logarCliente(String nome, String senha){
        
        try
        {
            return (Usuario) getEntityManager().createNamedQuery("usuario.logarCliente")
                .setParameter("nome", nome)
                .setParameter("senha", senha)
                .getSingleResult();
        }
        catch (Exception e)
        {
            System.out.println("Erro: "+ e.getLocalizedMessage());
            return null;
        }
    }
}
