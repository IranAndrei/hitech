/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.context.FacesContext;

/**
 *
 * @author Lucas
 */
public class Utilidade {
    
    public static Object verificarSessao(String chave)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(chave);
    }
    
    public static boolean verificarExisteSessao(String chave)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(chave);
    }
}
