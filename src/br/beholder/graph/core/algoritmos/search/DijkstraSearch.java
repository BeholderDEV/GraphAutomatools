/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos.search;

import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author lite
 */
public class DijkstraSearch extends SearchAlgorithm{

    public DijkstraSearch(Grafo grafo) {
        super(grafo);
    }

    @Override
    public Vertice search(int idVerticeInicial, int idVerticeProcurado) {
        Queue<Vertice> vertices = new LinkedList<>();
        vertices.add(grafo.getVertice(idVerticeInicial));
        vertices.peek().setCusto(0.0);
        while(!vertices.isEmpty()){
            Vertice verticeAtual = vertices.poll();
            verticeAtual.setVisitado(true);
            for(Vertice vizinho : grafo.getVizinhos(verticeAtual)){
                if(vizinho.getCusto() == -1){
                    vizinho.setCusto(verticeAtual.getCusto() + grafo.getAresta(verticeAtual.getId(), vizinho.getId()).getPeso());
                    vizinho.setAnterior(verticeAtual);
                    vertices.add(vizinho);
                }else if(vizinho.getCusto() > verticeAtual.getCusto() + grafo.getAresta(verticeAtual.getId(), vizinho.getId()).getPeso()){
                    vizinho.setCusto(verticeAtual.getCusto() + grafo.getAresta(verticeAtual.getId(), vizinho.getId()).getPeso());
                    vizinho.setAnterior(verticeAtual);
                    vertices.add(vizinho);
                }
            }
            if(grafo.todosForamVisitados()){
                break;
            }
        }
        return grafo.getVertice(idVerticeProcurado);
    }
    
}
