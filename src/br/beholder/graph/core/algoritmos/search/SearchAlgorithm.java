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
public abstract class SearchAlgorithm extends GraphAlgorithm{

    public SearchAlgorithm(Grafo grafo) {
        super(grafo);
    }
    
    public abstract Vertice search(int idVerticeInicial, int idVerticeProcurado);
}
