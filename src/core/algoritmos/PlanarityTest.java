/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Grafo;

/**
 *
 * @author Adson Estevesa
 */
public class PlanarityTest {
    
    public static boolean isPlanar(Grafo g)
    {
        if(isIsomorphictoK3K55(g))
        {
            return false;
        }
        else
        {
            if(g.getArestas().size()<=3*g.getVertices().size()-6){
                if(!have3sizecycles(g))
                {
                    if(g.getArestas().size()<=2*g.getVertices().size()-4)
                    {
                        return true;
                    }
                }
                else
                {
                    return true;
                }
            }
        }        
        
        return false;
    }
    
    public static boolean have3sizecycles(Grafo g){
        return true;
    }
    
    public static boolean isIsomorphictoK3K55(Grafo g)
    {
        return true;
    }
    
}
