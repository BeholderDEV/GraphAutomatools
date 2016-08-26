/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Grafo;
import core.model.Vertice;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Adson Estevesa
 */
public class BreadthFirstSearch implements SearchAlgorithm{
    
    @Override
    public Vertice search(Grafo grafo, int idVerticeInicial, int idVerticeProcurado){
        
        if(idVerticeInicial == idVerticeProcurado)
        {
            return grafo.getVertice(idVerticeInicial);
        }
        Queue q = new LinkedList();
        q.add(grafo.getVertice(idVerticeInicial));
        grafo.getVertice(idVerticeInicial).setVisitado(true);
        
        while(!q.isEmpty())
        {
            Vertice primeiro = (Vertice)q.remove();
            primeiro.setVisitado(true);
            
            for(Vertice vizinho : grafo.getVizinhos(primeiro))
            {
                if(!vizinho.isVisitado())
                {
                    vizinho.setAnterior(primeiro);
                    q.add(vizinho);
                    if(vizinho.getId() == idVerticeProcurado)
                    {
                        return vizinho;
                    }
                }
            }
        }
        return null;
    }
}
