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
    
    public static String search(Grafo grafo, int idVerticeInicial, int idVerticeProcurado){
        String caminho = "";
        Queue q = new LinkedList();
        q.add(grafo.getVertice(idVerticeInicial));
        grafo.getVertice(idVerticeInicial).setVisitado(true);
        
        while(!q.isEmpty())
        {
            Vertice primeiro = (Vertice)q.remove();
            if(primeiro.getId() == idVerticeProcurado)
            {
                return "Vertice Encontrado";
            }
            primeiro.setVisitado(true);
            q.addAll(removeVisited(grafo.getVizinhos(primeiro)));
        }
        
        return "Vertice não encontrado";
    }

    private static List<Vertice> removeVisited(List<Vertice> proximosVizinhos) {
        List<Vertice> naovisitados = new ArrayList<>();
        
        for(Vertice vizinho : proximosVizinhos)
        {
            if(vizinho.isVisitado())
            {
                naovisitados.add(vizinho);
            }
        }
        return naovisitados;
    }
}
