/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos.search;

import br.beholder.graph.core.algoritmos.GraphAlgorithm;
import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;

/**
 *
 * @author lite
 */
public class DeepFirstSearch extends GraphAlgorithm implements SearchAlgorithm{

    public DeepFirstSearch(Grafo grafo) {
        super(grafo);
    }
    
    @Override
    public Vertice search(int idVerticeInicial, int idVerticeProcurado){
        Vertice verticeInicial = grafo.getVertice(idVerticeInicial);
        verticeInicial.setVisitado(true);
        grafo.addVisitado(verticeInicial);
        for(Vertice n: grafo.getVizinhos(verticeInicial))
        {
            //if childs state is not visited then recurse
            if(!n.isVisitado())
            {
                n.setAnterior(verticeInicial);
                if(n.getId() == idVerticeProcurado){
                    n.setVisitado(true);
                    grafo.addVisitado(n);
                    return n;
                }
                Vertice son = search(n.getId(), idVerticeProcurado);
                if(son != null){
                    return son;
                }
            }
        }
        return null;
    }
}
