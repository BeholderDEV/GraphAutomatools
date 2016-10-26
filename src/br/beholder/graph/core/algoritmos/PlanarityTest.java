/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos;

import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;
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

//        if(ishomeomorphictoK5(g) || ishomeomorphictoK33(g))
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
        List<Vertice> vertices = g.getVertices();
        vertices = remove2Less(vertices);
        
        if(vertices.size()<6)
        {
             return false;
        }
        for (int i = 0; i < vertices.size(); i++) {
            List<Vertice> permutaveisM =  new ArrayList<>();
            List<Vertice> permutaveisN =  new ArrayList<>();
            permutaveisM.add(vertices.get(i));
            permutaveisM.add(vertices.get((i+1)%vertices.size()));
            permutaveisM.add(vertices.get((i+2)%vertices.size()));
            permutaveisN.add(vertices.get((i+3)%vertices.size()));
            permutaveisN.add(vertices.get((i+4)%vertices.size()));
            permutaveisN.add(vertices.get((i+5)%vertices.size()));
            if(hasK33(g, permutaveisM, permutaveisN)){
                return true;
            }
            System.out.println("entrou");
        } 
        return false;
    }
    
    public static boolean hasK5(Grafo g, List<Vertice> permutaveis)
    {
        SearchAlgorithm sa = SearchAlgorithmFactory.build(SearchAlgorithmFactory.BREADTH_FIRST_SEARCH);
        g.resetProperties();
        for (int i = 0; i < permutaveis.size(); i++) {
            for (int j = i; j < permutaveis.size(); j++) {
                if(i!=j){
                    Vertice v = sa.search(g, permutaveis.get(i).getId(), permutaveis.get(j).getId());
                    if(v==null){
                        System.out.println("deu merda "+permutaveis.get(i).getRotulo()+" pro "+permutaveis.get(j).getRotulo());
                        return false;
                    }
//                    reajustar_visitados(g, v);
                    g.getVertice(permutaveis.get(i).getId()).setVisitado(false);
                    g.getVertice(permutaveis.get(j).getId()).setVisitado(false);
                }                
                System.out.println("deboa "+permutaveis.get(i).getRotulo()+" pro "+permutaveis.get(j).getRotulo());
            }
        }
        return true;
    }
    
    public static boolean hasK33(Grafo g, List<Vertice> permutaveisM, List<Vertice> permutaveisN)
    {
        g.resetProperties();
        SearchAlgorithm sa = SearchAlgorithmFactory.build(SearchAlgorithmFactory.BREADTH_FIRST_SEARCH);
        for (int i = 0; i < permutaveisM.size(); i++) {
            for (int j = 0; j < permutaveisN.size(); j++) {
                if(i!=j){
                    Vertice N = sa.search(g, permutaveisM.get(i).getId(), permutaveisN.get(j).getId());
                    System.out.println("Tentar "+permutaveisM.get(i).getRotulo()+" pro "+permutaveisN.get(j).getRotulo());
                    if(N==null){
                        System.out.println("deu merda "+permutaveisM.get(i).getRotulo()+" pro "+permutaveisN.get(j).getRotulo());
                        return false;
                    }
                    g.getVertice(permutaveisM.get(i).getId()).setVisitado(false);
                    g.getVertice(permutaveisN.get(j).getId()).setVisitado(false);
                }                
                System.out.println("deboa "+permutaveisM.get(i).getRotulo()+" pro "+permutaveisN.get(j).getRotulo());
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
    public static List<Vertice> remove2Less(List<Vertice> vertices)
    {
        List<Vertice> higher =  new ArrayList<>();
        
        for (Vertice vertice : vertices) {
            System.out.println("Grau:"+vertice.getGrau());
            if(vertice.getGrau()>2){
                
                higher.add(vertice);
            }
        }
        return higher;
    }

    private static void reajustar_visitados(Grafo g, Vertice N) {
        g.resetProperties();
        while(N.getAnterior()!=null)
        {
            N = N.getAnterior();
            N.setVisitado(true);
        }
        N.setVisitado(false);
        N.setAnterior(null);
    }

    private static void print_vizitados(Grafo g) {
        System.out.print("Visitados: ");
        for (Vertice vertice : g.getVertices()) {
            if(vertice.isVisitado())
            System.out.println(vertice.getRotulo()+" ");
        }
    }
}
