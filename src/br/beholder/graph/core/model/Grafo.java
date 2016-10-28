/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.model;

import br.beholder.graph.core.algoritmos.test.ConectivityTest;
import br.beholder.graph.core.algoritmos.test.PlanarityTest;
import br.beholder.graph.core.algoritmos.search.SearchAlgorithm;
import br.beholder.graph.core.algoritmos.search.SearchAlgorithmFactory;
import br.beholder.graph.core.algoritmos.test.TestAlgorithm;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author lite
 */
public class Grafo {
    private final List<Vertice> vertices;
    private List<Vertice> visitados;
    private final List<Aresta> arestas;
    private Boolean ponderado = false;
    private Boolean dirigido = false;
    private final TestAlgorithm planar = new PlanarityTest(this);
    private final TestAlgorithm conexo = new ConectivityTest(this);
    private final List<Color> lista_cores = new ArrayList<>();
    
    public Grafo() {
        visitados = new ArrayList<>();
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
        lista_cores.add(new Color(26, 188, 156));        
        lista_cores.add(new Color(52, 152, 219));  
        lista_cores.add(new Color(230, 126, 34));  
        lista_cores.add(new Color(155, 89, 182));  
        lista_cores.add(new Color(231, 76, 60));  
        lista_cores.add(new Color(46, 204, 113));  
        lista_cores.add(new Color(52, 73, 94));  
        lista_cores.add(new Color(241, 196, 15));  
        lista_cores.add(new Color(192, 57, 43));  
    }

    public boolean isPlanar(){
        return planar.is();
    }
    public boolean isConexo(){
        return conexo.is();
    }
    
    public Grafo(List<Vertice> vertices, List<Aresta> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
        setGraus();
    }
    
    public void addVertice(Vertice vertice){
        vertices.add(vertice);
    }

    public Vertice getVertice(int id){
        for (Vertice vertice : vertices) {
            if(vertice.getId()==id){
                return vertice;
            }
        }
        return null;
    }

    public List<Vertice> getVisitados() {
        return visitados;
    }
    
    public void addVisitado(Vertice v){
        if(!visitados.contains(v)){
            visitados.add(v);
        }
    }
    
    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public Boolean isPonderado() {
        return ponderado;
    }

    public void setPonderado(Boolean ponderado) {
        this.ponderado = ponderado;
    }

    public Boolean isDirigido() {
        return dirigido;
    }

    public void setDirigido(Boolean dirigido) {
        this.dirigido = dirigido;
    }
    
    public void addAresta(Aresta aresta){
        arestas.add(aresta);
    }
    
    public Aresta getAresta(int idV1,int idV2){
        
        if(dirigido){
            for (Aresta aresta: arestas) {
               if(aresta.getVertice1().getId()==idV1 && aresta.getVertice2().getId()==idV2){
                   return aresta;
               }
            }   
        }
        else{
            for (Aresta aresta: arestas) {
                if(aresta.getVertice1().getId()==idV1 && aresta.getVertice2().getId()==idV2 || aresta.getVertice1().getId()==idV2 && aresta.getVertice2().getId()==idV1){
                    return aresta;
                }
            }
        }       
        
        return null;
    }
    
    public List<Vertice> getVizinhos(Vertice vertice){
        List<Vertice> vizinhos = new ArrayList<>();
        for (Aresta aresta: arestas) {
            if(!dirigido){
                if(aresta.getVertice2()==vertice){
                    vizinhos.add(aresta.getVertice1());
                }
            }            
            if(aresta.getVertice1()==vertice){
                vizinhos.add(aresta.getVertice2());
            }
        }
        Collections.sort(vizinhos, new OrdenaPorRotulo());
        return vizinhos;
    }
    
    public boolean hasVertice(int id){
        for (Vertice vertice : vertices) {
            if(vertice.getId()==id){
                return true;
            }
        }
        return false;
    }
    
    public int getArestasCount(){
        return arestas.size();
    }
    
    public int getVerticesCount(){
        return  vertices.size();
    }
    
    @Override
    public String toString() {
        String eu="";
        eu = eu.concat("Vertices\n");
        for (Vertice vertice : vertices) {
            eu = eu.concat(vertice.getRotulo()+"\n");
        }
        
        eu = eu.concat("Arestas\n");
        for (Aresta aresta : arestas) {
            eu = eu.concat(aresta.getVertice1().getRotulo()+" ---- "+aresta.getPeso()+" ---- "+aresta.getVertice2().getRotulo()+"\n");
        }
        return eu;
    }
    
    public void resetAll(){
        resetAnteriores();
        resetProperties();
    }
    
    public void resetProperties(){
        for (Vertice vertice : vertices) {
            vertice.setVisitado(false);
            vertice.setCusto(-1d);
            vertice.setCor(null);
        }
        for (Aresta aresta : arestas) {
            aresta.setHinted(false);
        }
        visitados = new ArrayList<>();
        System.gc();
    }
    
    public void resetAnteriores(){
        for (Vertice vertice : vertices) {
            vertice.setAnterior(null);
        }
    }

    public boolean verificarVisitados() {
        for(Vertice vertice : vertices){
            if(!vertice.isVisitado())
            {
                return false;
            }
        }
        return true;
    }
    
    public void setGraus()    
    {
        for(Vertice vertice : vertices){
            vertice.setGrau(this.getVizinhos(vertice).size());
        }
    }

    public Vertice getVerticeMaiorGrau() {
        int maior_grau = 0;
        Vertice maior = vertices.get(0);
        for(Vertice vertice : vertices)
        {
            if(vertice.getGrau()>maior_grau)
            {
                maior_grau = vertice.getGrau();
                maior = vertice;
            }
        }
        return maior;
    }
    
    public Color getCorNumero (int i)
    {
        return lista_cores.get(i);
    }
    
}

class OrdenaPorRotulo implements Comparator<Vertice> {
    @Override
    public int compare(Vertice um, Vertice dois) {
        return um.getRotulo().compareTo(dois.getRotulo());
    }
}