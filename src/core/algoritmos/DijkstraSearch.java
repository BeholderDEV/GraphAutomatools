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
 * @author lite
 */
public class DijkstraSearch implements SearchAlgorithm{

    @Override
    public Vertice search(Grafo grafo, int idVerticeInicial, int idVerticeProcurado) {
        Queue<Vertice> vertices = new LinkedList<>();
        vertices.add(grafo.getVertice(idVerticeInicial));
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
            if(grafo.verificarVisitados()){
                break;
            }
        }
        grafo.getVertice(idVerticeInicial).setAnterior(null);
        return grafo.getVertice(idVerticeProcurado);
    }
    
}
