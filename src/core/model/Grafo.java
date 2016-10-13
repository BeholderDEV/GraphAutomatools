/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.model;

import core.algoritmos.SearchAlgorithm;
import core.algoritmos.SearchAlgorithmFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author lite
 */
public class Grafo {
    List<Vertice> vertices;
    List<Vertice> visitados;
    List<Aresta> arestas;
    Boolean ponderado = false;
    Boolean dirigido = false;
    

    public Grafo() {
        visitados = new ArrayList<>();
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
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
    
    public boolean isDesconexo()
    {
        SearchAlgorithm sa = SearchAlgorithmFactory.build(SearchAlgorithmFactory.BREADTH_FIRST_SEARCH);
        for(Vertice vertice : vertices)
        {
            resetProperties();
            sa.search(this, vertice.getId(), -1);
            if(!verificarVisitados())
            {
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
    
    public void resetProperties(){
        for (Vertice vertice : vertices) {
            vertice.setVisitado(false);
            vertice.setCusto(-1d);
        }
        for (Aresta aresta : arestas) {
            aresta.setHinted(false);
        }
        visitados = new ArrayList<>();
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
    
}

class OrdenaPorRotulo implements Comparator<Vertice> {
    @Override
    public int compare(Vertice um, Vertice dois) {
        return um.getRotulo().compareTo(dois.getRotulo());
    }
}