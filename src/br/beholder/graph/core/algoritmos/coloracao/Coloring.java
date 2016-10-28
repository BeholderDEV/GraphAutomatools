/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos.coloracao;

import br.beholder.graph.controller.MainPanelController;
import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adson Estevesa
 */
public class Coloring {
    private final Grafo grafo;
    private int ultimaCor;
    private final MainPanelController controller;
    boolean animated = true;
    int delay = 1000;
    boolean dirigido;
    
    public Coloring(Grafo grafo, MainPanelController controller) {
        this.grafo = grafo;
        this.controller = controller;
        
    }
    
    private boolean verificarCoresVizinhas(List<Color> cores, Color corVizinho){
        return cores.stream().anyMatch((cor) -> (corVizinho.getRGB() == cor.getRGB()));
    }
    
    private Vertice getVerticeHigherSaturation(){
        int maiorGrau = 0;
        int grauAtual;
        int id = -1;
        List<Vertice> vizinhos;
        List<Color> cores = new ArrayList<>();
        for (Vertice v : removeColored(grafo.getVertices())) {
            cores.clear();
            grauAtual = 0;
            vizinhos = grafo.getVizinhos(v);
            for (Vertice vizinho : removeNonColored(vizinhos)) {
                if(!verificarCoresVizinhas(cores, vizinho.getCor())){
                    grauAtual++;
                    cores.add(vizinho.getCor());
                }
            }
            if(grauAtual >= maiorGrau){
                if(maiorGrau != 0 && grauAtual == maiorGrau){
                    if(v.getRotulo().compareTo(grafo.getVertice(id).getRotulo())>0){
//                    if(v.getId()==id){
                        continue;
                    }
                }
                maiorGrau = grauAtual;
                id = v.getId();
            }
        }
        if(id == -1){
            return null;
        }else{
            ultimaCor = maiorGrau;
            return grafo.getVertice(id);
        }
    }
    
    private void doNewColor(Vertice verticeAtual, int NumeroCores){
        boolean corValida;
        List<Vertice> vizinhos = grafo.getVizinhos(verticeAtual);
        for(int i = 1; i <= NumeroCores; i++){
            verticeAtual.setCor(grafo.getCorNumero(i-1));
            corValida = true;
            for (Vertice vizinho : removeNonColored(vizinhos)) {
                if(vizinho.getCor().getRGB() == verticeAtual.getCor().getRGB()){
                    corValida = false;
                    break;
                }
                if(animated){
                    grafo.setDirigido(dirigido);
                    controller.renderColoration(verticeAtual.getId());
                    grafo.setDirigido(false);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Coloring.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if(corValida){
                break;
            }
        }
    }
    
    public int colorGraph(boolean animated, int delay){
        dirigido=grafo.isDirigido();
        grafo.setDirigido(false);
        this.animated = animated;
        this.delay = delay;
        int NumeroCores = 1;
        Vertice maiorGrau  = grafo.getVerticeMaiorGrau();
        maiorGrau.setCor(grafo.getCorNumero(0));
        if(animated){
            grafo.setDirigido(dirigido);
            controller.renderColoration(maiorGrau.getId());
            grafo.setDirigido(false);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Coloring.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Vertice verticeAtual;
        while(true){
            verticeAtual = getVerticeHigherSaturation();
            if(verticeAtual == null){
                break;
            }
            if(ultimaCor == NumeroCores){
                NumeroCores++;
                verticeAtual.setCor(grafo.getCorNumero(NumeroCores-1));
                if(animated){
                    grafo.setDirigido(dirigido);
                    controller.renderColoration(verticeAtual.getId());
                    grafo.setDirigido(false);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Coloring.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }else{
                doNewColor(verticeAtual, NumeroCores);
            }
            
        }
        grafo.setDirigido(dirigido);
        return NumeroCores;
    }
    
    private List<Vertice> removeColored(List<Vertice> vertices)
    {
        List<Vertice> noncolored = new ArrayList<>();
        for (Vertice vertice : vertices) {
            if(vertice.getCor()==null)
            {
                noncolored.add(vertice);
            }
        }
        return noncolored;
    }
    
    private List<Vertice> removeNonColored(List<Vertice> vertices)
    {
        List<Vertice> colored = new ArrayList<>();
        for (Vertice vertice : vertices) {
            if(vertice.getCor()!=null)
            {
                colored.add(vertice);
            }
        }
        return colored;
    }
    
}
