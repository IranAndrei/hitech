/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

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
    
    public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public static boolean verificaExisteSessao()
    {
        return !FacesContext.getCurrentInstance().getExternalContext().getSessionMap().isEmpty();
    }
    
    public static boolean verificaExisteRegistroSessao(String registro)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(registro);
    }
    
    public static Object recuperaRegistroSessao(String registro)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(registro);
    }
    
    public static Object salvaRegistroSessao(String registro, Object objeto)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(registro, objeto);
    }
    
    public static String getRealPath()
    {
        return ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/");
    }
}
