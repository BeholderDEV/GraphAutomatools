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
    Grafo grafo;
    int verticeInicial;
    int verticeProcurado;
    List<Vertice> visited;

    public BreadthFirstSearch(Grafo grafo) {
        this.grafo = grafo;
        visited = new ArrayList<>();
    }
    
    public String search(int idVerticeInicial, int idVerticeProcurado){
        String caminho = "";
        Queue q = new LinkedList();
        q.add(grafo.getVertice(verticeInicial));
        visited.add(grafo.getVertice(verticeInicial));
        
        while(!q.isEmpty())
        {
            Vertice primeiro = (Vertice)q.remove();
            if(primeiro.getId() == idVerticeProcurado)
            {
                return "Vertice Encontrado";
            }
            addToVisitedList(primeiro);
            q.addAll(removeVisited(grafo.getVizinhos(primeiro)));
        }
        
        return "Vertice não encontrado";
    }
    
    private void addToVisitedList(Vertice vertice)
    {
        for(Vertice visitado : visited)
        {
            if(vertice.getId() == visitado.getId())
            {
                return;
            }
        }
        visited.add(vertice);
    }

    private List<Vertice> removeVisited(List<Vertice> proximosVizinhos) {
        List<Vertice> naovisitados = new ArrayList<>();
        
        for(Vertice vizinho : proximosVizinhos)
        {
            if(!isVisited(vizinho))
            {
                naovisitados.add(vizinho);
            }
        }
        
        return naovisitados;
    }
    
    private boolean isVisited(Vertice vizinho)
    {
        for(Vertice visitado : visited)
        {
            if(vizinho.getId() == visitado.getId())
            {
                return true;
            }
        }
        
        return false;
    }
}
