/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Grafo;
import core.model.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adson Estevesa
 */
public class PlanarityTest {
    
    public static boolean isPlanar(Grafo g)
    {
        if(ishomeomorphictoK5(g) || ishomeomorphictoK33(g))
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
    
    public static boolean ishomeomorphictoK5(Grafo g)
    {
        List<Vertice> vertices = g.getVertices();
        
        vertices = remove3Less(vertices);
        SearchAlgorithm sa = SearchAlgorithmFactory.build(SearchAlgorithmFactory.DIJKSTRA_SEARCH);
        for (Vertice vertice : vertices) {
            sa.search(g, vertice.getId(), vertice.getId());
            List<Vertice> permutaveis = new ArrayList<>();
            permutaveis.add(vertice);
            for (int i = 0; i < vertices.size()-3; i++) {
                permutaveis.add(vertices.get(i));
                permutaveis.add(vertices.get(i+1));
                permutaveis.add(vertices.get(i+2));
                permutaveis.add(vertices.get(i+3));
                
            }
        }        
        return true;
    }
    
    public static boolean ishomeomorphictoK33(Grafo g)
    {
        return true;
    }
    
    public static List<Vertice> remove3Less(List<Vertice> vertices)
    {
        List<Vertice> higher =  new ArrayList<>();
        
        for (Vertice vertice : vertices) {
            if(vertice.getGrau()>3){
                higher.add(vertice);
            }
        }
        return higher;
    }
}
