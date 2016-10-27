/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos.test;

import br.beholder.graph.core.algoritmos.GraphAlgorithm;
import br.beholder.graph.core.model.Grafo;

/**
 *
 * @author lite
 */
public abstract class TestAlgorithm extends GraphAlgorithm{

    public TestAlgorithm(Grafo grafo) {
        super(grafo);
    }
    
    public abstract boolean is();
}
