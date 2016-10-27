/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos;

import br.beholder.graph.core.model.Grafo;

/**
 *
 * @author lite
 */
public abstract class GraphAlgorithm {
    protected Grafo grafo;

    public GraphAlgorithm(Grafo grafo) {
        this.grafo = grafo;
    }
}
