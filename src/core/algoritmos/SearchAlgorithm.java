/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Grafo;
import core.model.Vertice;

/**
 *
 * @author lite
 */
public interface SearchAlgorithm {
     public Vertice search(Grafo grafo, int idVerticeInicial, int idVerticeProcurado);
}
