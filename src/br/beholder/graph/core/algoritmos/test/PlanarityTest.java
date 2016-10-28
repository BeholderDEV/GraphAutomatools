/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos.test;

import br.beholder.graph.core.algoritmos.search.SearchAlgorithmFactory;
import br.beholder.graph.core.algoritmos.search.SearchAlgorithm;
import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adson Estevesa
 */
public class PlanarityTest extends TestAlgorithm{

    public PlanarityTest(Grafo grafo) {
        super(grafo);
    }
        
    private boolean is3Cycled(int tamanhoCiclo, Vertice atual, Vertice fim){
        
        if(tamanhoCiclo == 3){
            return atual.getRotulo().equals(fim.getRotulo());
        }
        for (Vertice v : grafo.getVizinhos(atual)) {
            if(is3Cycled(tamanhoCiclo + 1, v, fim)){
                return true;
            }
        }
        return false;
    }
    private  boolean has3Cycle()
    {
        List<Vertice> vertices = grafo.getVertices();
        for (Vertice v : vertices) {
            if(is3Cycled(0, v, v)){
                return true;
            }
        }
        return false;
    }
    
    
    
    private boolean ishomeomorphictoK5()
    {
        List<Vertice> vertices = grafo.getVertices();
        
        vertices = remove3Less(vertices);
        
        if(vertices.size()<5){
             return false;
        }           
        
        for (int i = 0; i < vertices.size(); i++) {
            List<Vertice> permutaveis =  new ArrayList<>();
            permutaveis.add(vertices.get(i));
            permutaveis.add(vertices.get((i+1)%vertices.size()));
            permutaveis.add(vertices.get((i+2)%vertices.size()));
            permutaveis.add(vertices.get((i+3)%vertices.size()));
            permutaveis.add(vertices.get((i+4)%vertices.size()));
            if(hasK5(permutaveis)){
                return true;
            }
        }        
        return false;
    }
    
    private boolean ishomeomorphictoK33()
    {
        List<Vertice> vertices = grafo.getVertices();
        vertices = remove2Less(vertices);
        
        if(vertices.size()<6){
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
            if(hasK33(permutaveisM, permutaveisN)){
                return true;
            }
        } 
        return false;
    }
    
    private boolean hasK5(List<Vertice> permutaveis)
    {
        SearchAlgorithm sa = SearchAlgorithmFactory.build(grafo, SearchAlgorithmFactory.BREADTH_FIRST_SEARCH);
        grafo.resetProperties();
        for (int i = 0; i < permutaveis.size(); i++) {
            for (int j = i; j < permutaveis.size(); j++) {
                if(i!=j){
                    Vertice v = sa.search(permutaveis.get(i).getId(), permutaveis.get(j).getId());
                    if(v==null){
                        return false;
                    }
//                    reajustar_visitados(g, v);
                    grafo.getVertice(permutaveis.get(i).getId()).setVisitado(false);
                    grafo.getVertice(permutaveis.get(j).getId()).setVisitado(false);
                }                
            }
        }
        return true;
    }
    
    private boolean hasK33(List<Vertice> permutaveisM, List<Vertice> permutaveisN)
    {
        grafo.resetProperties();
        SearchAlgorithm sa = SearchAlgorithmFactory.build(grafo, SearchAlgorithmFactory.BREADTH_FIRST_SEARCH);
        for (int i = 0; i < permutaveisM.size(); i++) {
            for (int j = 0; j < permutaveisN.size(); j++) {
                if(i!=j){
                    Vertice N = sa.search(permutaveisM.get(i).getId(), permutaveisN.get(j).getId());
                    if(N==null){
                        return false;
                    }
                    grafo.getVertice(permutaveisM.get(i).getId()).setVisitado(false);
                    grafo.getVertice(permutaveisN.get(j).getId()).setVisitado(false);
                }
            }
        }
        return true;
    }
    
    private List<Vertice> remove3Less(List<Vertice> vertices)
    {
        List<Vertice> higher =  new ArrayList<>();
        
        for (Vertice vertice : vertices){
            if(vertice.getGrau()>3){
                
                higher.add(vertice);
            }
        }
        return higher;
    }
    private List<Vertice> remove2Less(List<Vertice> vertices)
    {
        List<Vertice> higher =  new ArrayList<>();
        
        for (Vertice vertice : vertices){
            if(vertice.getGrau()>2){
                
                higher.add(vertice);
            }
        }
        return higher;
    }

    private void reajustar_visitados(Vertice N) {
        grafo.resetProperties();
        while(N.getAnterior()!=null){
            N = N.getAnterior();
            N.setVisitado(true);
        }
        N.setVisitado(false);
        N.setAnterior(null);
    }

    private static void print_vizitados(Grafo g){
        for (Vertice vertice : g.getVertices()){
            if(vertice.isVisitado())
            System.out.println(vertice.getRotulo()+" ");
        }
    }

    @Override
    public boolean is() {
        if(grafo.getVerticesCount()<=3){
            return true;
        }          

//        if(ishomeomorphictoK5(g) || ishomeomorphictoK33(g))
        if(ishomeomorphictoK5()){
            return false;
        }
        else{
            if(grafo.getArestasCount()<=3*grafo.getVerticesCount()-6){
                if(!has3Cycle() && grafo.getVerticesCount()>=3){
                    boolean isPlanar = grafo.getArestasCount()<=2*grafo.getVerticesCount()-4;
                    return isPlanar;
                }
                else{
                    return true;
                }
            }
        }
        return false;
    }
}
