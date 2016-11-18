/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos.search;

import br.beholder.graph.controller.MainPanelController;
import br.beholder.graph.core.algoritmos.coloracao.Coloring;
import br.beholder.graph.core.model.Aresta;
import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adson Estevesa
 */
public class TravellingSalesman {
    
    private Grafo grafo;
    int delay = 1000;
    private final MainPanelController controller;
    public TravellingSalesman(Grafo grafo, MainPanelController controller) {
        this.controller = controller;
        this.grafo=grafo;
    }
    
    public Double find_path(int delay)
    {
        List<Aresta> escolhidos = null;        
        menor_Aresta();
        boolean first = true;
        while(!grafo.todosForamVisitados())
        {
            List<Vertice> possiveis = new ArrayList<>();
            escolhidos = caminhosJaEscolhidos();
            for (Aresta escolhido : escolhidos) {
//                possiveis.addAll(vizinhosDeAmbos(escolhido.getVertice1(), escolhido.getVertice2()));
                for (Vertice vertice : grafo.getVertices()) {
                    List<Vertice> vizinhos = grafo.getVizinhosnaoVizitados(vertice);
                    if(vizinhos.contains(escolhido.getVertice1()) && vizinhos.contains(escolhido.getVertice2()))
                    {
                        
                        Double custoNovaAresta = grafo.getAresta(vertice.getId(), escolhido.getVertice1().getId()).getPeso()+grafo.getAresta(vertice.getId(), escolhido.getVertice2().getId()).getPeso();
                        if(vertice.getCusto()==-1.0 || custoNovaAresta<vertice.getCusto())
                        {
                            vertice.setArestasSubstitutas(grafo.getAresta(escolhido.getVertice1().getId(), vertice.getId()), 0);
                            vertice.setArestasSubstitutas(grafo.getAresta(vertice.getId(), escolhido.getVertice2().getId()), 1);
                            vertice.setCusto(custoNovaAresta);
                            vertice.setArestaColocavel(escolhido);
                        }                       
                        if(!possiveis.contains(vertice))
                        {
                            possiveis.add(vertice);
                        }
                    }
                }
            }
            Vertice menor = possiveis.get(0);
            for (Vertice possivel : possiveis) {
                if(menor.getCusto()>possivel.getCusto())
                {
                    menor=possivel;
                }
                else
                {
                    possivel.setCusto(-1.0);
                }
            }
            
            if(!first)
            {
                menor.getArestaColocavel().setHinted(false);
            }
            else
            {
                first=false;
            }            
            menor.getArestasSubstitutas()[0].setHinted(true);
            menor.getArestasSubstitutas()[1].setHinted(true);
            menor.setVisitado(true);
            
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Coloring.class.getName()).log(Level.SEVERE, null, ex);
            }
            controller.renderColoration(delay);
        }
        
        return pesodocaminho();
    }
    
    public List<Vertice> vizinhosDeAmbos(Vertice vertice1, Vertice vertice2)
    {
        List<Vertice> vizinhos_comum = new ArrayList<>();
        
        for (Vertice vertice : grafo.getVertices()) {
            List<Vertice> vizinhos = grafo.getVizinhos(vertice);
            if(vizinhos.contains(vertice1) && vizinhos.contains(vertice2))
            {
                vertice.setCusto(grafo.getAresta(vertice.getId(), vertice1.getId()).getPeso()+grafo.getAresta(vertice.getId(), vertice2.getId()).getPeso());
                vizinhos_comum.add(vertice);
            }
        }
        return vizinhos_comum;
    }
    
    public List<Aresta> caminhosJaEscolhidos()
    {
        List<Aresta> escolhidos = new ArrayList<>();
        
        for (Aresta aresta : grafo.getArestas()) {
            if(aresta.isHinted())
            {
                escolhidos.add(aresta);
            }
        }
        return escolhidos;
    }
    
    public void menor_Aresta()
    {
        Double menor = -1.0;
        int num = -1;
        for (Aresta aresta : grafo.getArestas()) {
            num++;
            if(menor==-1.0)
            {
                menor=aresta.getPeso();
            }
            else
            {
                if(menor>aresta.getPeso())
                {
                    menor=aresta.getPeso();
                }
            }
        }
        grafo.getArestas().get(num).getVertice1().setVisitado(true);
        grafo.getArestas().get(num).getVertice2().setVisitado(true);
        grafo.getArestas().get(num).setHinted(true);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(Coloring.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller.renderColoration(delay);
    }

    private Double pesodocaminho() {
        Double soma = 0.0;
        for (Aresta aresta : grafo.getArestas()) {
            if(aresta.isHinted())
            {
                soma+=aresta.getPeso();
            }            
        }
        return soma;
    }
    
}
