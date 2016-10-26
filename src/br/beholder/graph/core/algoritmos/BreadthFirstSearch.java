/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos;

import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;
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
            grafo.addVisitado(primeiro);
            
            for(Vertice vizinho : grafo.getVizinhos(primeiro))
            {
                if(!vizinho.isVisitado())
                {
                    vizinho.setAnterior(primeiro);
                    q.add(vizinho);
                    grafo.addVisitado(vizinho);
                    if(vizinho.getId() == idVerticeProcurado)
                    {
                        vizinho.setVisitado(true);
                        return vizinho;
                    }
                }
            }
        }
        return null;
    }
}
