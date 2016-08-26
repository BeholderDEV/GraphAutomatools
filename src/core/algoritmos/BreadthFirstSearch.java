/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Grafo;
import core.model.Vertice;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Adson Estevesa
 */
public class BreadthFirstSearch {
    
    public static Vertice search(Grafo grafo, int idVerticeInicial, int idVerticeProcurado){
        
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

    private static List<Vertice> removeVisited(List<Vertice> proximosVizinhos, Vertice primeiro) {
        List<Vertice> naovisitados = new ArrayList<>();
        
        for(Vertice vizinho : proximosVizinhos)
        {
            if(!vizinho.isVisitado())
            {
                vizinho.setAnterior(primeiro);
                naovisitados.add(vizinho);
            }
        }
        return naovisitados;
    }
}
