/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos.search;

import br.beholder.graph.core.model.Grafo;

/**
 *
 * @author lite
 */
public class SearchAlgorithmFactory {
    public static int DEEP_FIRST_SEARCH = 1;
    public static int BREADTH_FIRST_SEARCH = 2;
    public static int DIJKSTRA_SEARCH = 3;
    
    public static SearchAlgorithm build(Grafo grafo, int method){
        if(method == DEEP_FIRST_SEARCH){
            return new DeepFirstSearch(grafo);
        }
        if(method == BREADTH_FIRST_SEARCH){
            return new BreadthFirstSearch(grafo);
        }
        if(method == DIJKSTRA_SEARCH){
            return new DijkstraSearch(grafo);
        }
        return null;
    }
}
