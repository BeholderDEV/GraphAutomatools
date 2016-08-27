/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.model;

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
    List<Aresta> arestas;
    Boolean ponderado = false;
    Boolean dirigido = false;

    public Grafo() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }

    public Grafo(List<Vertice> vertices, List<Aresta> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
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

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public Boolean getPonderado() {
        return ponderado;
    }

    public void setPonderado(Boolean ponderado) {
        this.ponderado = ponderado;
    }

    public Boolean getDirigido() {
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
    
    public void resetVisitedsandHinteds(){
        for (Vertice vertice : vertices) {
            vertice.setVisitado(false);
        }
        for (Aresta aresta : arestas) {
            aresta.setHinted(false);
        }
    }
    
}

class OrdenaPorRotulo implements Comparator<Vertice> {
    @Override
    public int compare(Vertice um, Vertice dois) {
        return um.getRotulo().compareTo(dois.getRotulo());
    }
}