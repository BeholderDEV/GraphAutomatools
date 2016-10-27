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
import java.util.List;

/**
 *
 * @author lite
 */
public class ConectivityTest extends TestAlgorithm{
 
    public ConectivityTest(Grafo grafo) {
        super(grafo);
    }
    
    @Override
    public boolean is(){
        SearchAlgorithm sa = SearchAlgorithmFactory.build(grafo, SearchAlgorithmFactory.BREADTH_FIRST_SEARCH);
        List<Vertice> vertices = grafo.getVertices();
        for(Vertice vertice : vertices)
        {
            grafo.resetProperties();
            sa.search(vertice.getId(), -1);
            if(!grafo.verificarVisitados())
            {
                return false;
            }
        }
        return true;
    }
    
}
