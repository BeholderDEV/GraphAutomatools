/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.algoritmos.coloracao;

import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adson Estevesa
 */
public class Coloring {
    private final Grafo grafo;
    private int NumeroCores = 1;
    private int ultimaCorSaturacao;
    
    public Coloring(Grafo grafo) {
        this.grafo = grafo;
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
        for (Vertice v : grafo.getVertices()) {
            if(v.getCor()!= null){
                continue;
            }
            cores.clear();
            grauAtual = 0;
            vizinhos = grafo.getVizinhos(v);
            for (Vertice vizinho : vizinhos) {
                if(vizinho.getCor() == null){
                    continue;
                }
                if(!verificarCoresVizinhas(cores, vizinho.getCor())){
                    grauAtual++;
                    cores.add(vizinho.getCor());
                }
            }
            if(grauAtual >= maiorGrau){
                if(maiorGrau != 0 && grauAtual == maiorGrau){
                    if(v.getId() == id){
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
            ultimaCorSaturacao = maiorGrau;
            return grafo.getVertice(id);
        }
    }
    
    private void doNewColor(Vertice verticeAtual){
        boolean corValida;
        List<Vertice> vizinhos = grafo.getVizinhos(verticeAtual);
        for(int i = 1; i <= NumeroCores; i++){
            verticeAtual.setCor(grafo.getCorNumero(i));
            corValida = true;
            for (Vertice vizinho : vizinhos) {
                if(vizinho.getCor() == null){
                    continue;
                }
                if(vizinho.getCor().getRGB() == verticeAtual.getCor().getRGB()){
                    corValida = false;
                    break;
                }
            }
            if(corValida){
                break;
            }
        }
    }
    
    public int ColorGraph(){
        grafo.getVerticeMaiorGrau().setCor(grafo.getCorNumero(1));
        Vertice verticeAtual;
        while(true){
            verticeAtual = getVerticeHigherSaturation();
            if(verticeAtual == null){
                break;
            }
            if(ultimaCorSaturacao == NumeroCores){
                NumeroCores++;
                verticeAtual.setCor(grafo.getCorNumero(NumeroCores));
            }else{
                doNewColor(verticeAtual);
            }
        }
        return NumeroCores;
    }
    
}
