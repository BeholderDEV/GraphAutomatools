/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Graph;
import core.model.Fork;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author lite
 */
public class DeepFirstSearch implements SearchAlgorithm{
    
    
    
    @Override
    public Fork search(Graph grafo, int idVerticeInicial, int idVerticeProcurado){
        Fork verticeInicial = grafo.getVertice(idVerticeInicial);
        verticeInicial.setVisitado(true);
        for(Fork n: grafo.getVizinhos(verticeInicial))
        {
            //if childs state is not visited then recurse
            if(!n.isVisitado())
            {
                n.setAnterior(verticeInicial);
                if(n.getId() == idVerticeProcurado){
                    return n;
                }
                Fork son = search(grafo, n.getId(), idVerticeProcurado);
                if(son != null){
                    return son;
                }
            }
        }
        return null;
    }
}
