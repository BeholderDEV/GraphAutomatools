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
        if(g.getVerticesCount()<=3)
        {
            return true;
        }          

        if(ishomeomorphictoK5(g))
        {
            System.out.println("testaK5");
            return false;
        }
        else
        {
            if(g.getArestasCount()<=3*g.getVerticesCount()-6){
                if(!has3Cycle(g) && g.getVerticesCount()>=3)
                {
                    if(g.getArestasCount()<=2*g.getVerticesCount()-4)
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
    
    public static boolean is3Cycled(Grafo g, int tamanhoCiclo, Vertice atual, Vertice fim){
        
        if(tamanhoCiclo == 3){
            return atual.getRotulo().equals(fim.getRotulo());
        }
        for (Vertice v : g.getVizinhos(atual)) {
            if(is3Cycled(g, tamanhoCiclo + 1, v, fim)){
                return true;
            }
        }
        return false;
    }
    public static boolean has3Cycle(Grafo g)
    {
        List<Vertice> vertices = g.getVertices();
        for (Vertice v : vertices) {
            if(is3Cycled(g, 0, v, v)){
                return true;
            }
        }
        return false;
    }
    
    
    
    public static boolean ishomeomorphictoK5(Grafo g)
    {
        List<Vertice> vertices = g.getVertices();
        
        vertices = remove3Less(vertices);
        
        if(vertices.size()<5)
        {
             return false;
        }           
        
        for (int i = 0; i < vertices.size(); i++) {
            List<Vertice> permutaveis =  new ArrayList<>();
            permutaveis.add(vertices.get(i));
            permutaveis.add(vertices.get((i+1)%vertices.size()));
            permutaveis.add(vertices.get((i+2)%vertices.size()));
            permutaveis.add(vertices.get((i+3)%vertices.size()));
            permutaveis.add(vertices.get((i+4)%vertices.size()));
            if(hasK5(g, permutaveis)){
                return true;
            }
            System.out.println("entrou");
        }        
        return false;
    }
    
    public static boolean ishomeomorphictoK33(Grafo g)
    {
        return true;
    }
    
    public static boolean hasK5(Grafo g, List<Vertice> permutaveis)
    {
        SearchAlgorithm sa = SearchAlgorithmFactory.build(SearchAlgorithmFactory.DEEP_FIRST_SEARCH);
        g.resetProperties();
        for (int i = 0; i < permutaveis.size(); i++) {
            for (int j = i; j < permutaveis.size(); j++) {
                if(sa.search(g, permutaveis.get(i).getId(), permutaveis.get(j).getId())==null){
                    System.out.println("deu merda");
                    return false;
                }
                System.out.println("deboa");
            }
        }
        return true;
    }
    
    public static List<Vertice> remove3Less(List<Vertice> vertices)
    {
        List<Vertice> higher =  new ArrayList<>();
        
        for (Vertice vertice : vertices) {
            System.out.println("Grau:"+vertice.getGrau());
            if(vertice.getGrau()>3){
                
                higher.add(vertice);
            }
        }
        return higher;
    }
}
