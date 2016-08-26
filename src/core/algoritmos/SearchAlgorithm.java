/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Graph;
import core.model.Fork;

/**
 *
 * @author lite
 */
public interface SearchAlgorithm {
     public Fork search(Graph grafo, int idVerticeInicial, int idVerticeProcurado);
}
