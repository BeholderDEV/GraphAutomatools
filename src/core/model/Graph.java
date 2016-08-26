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
public class Graph {
    List<Fork> vertices;
    List<Edge> arestas;

    public Graph() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }

    public Graph(List<Fork> vertices, List<Edge> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
    }
    
    public void addVertice(Fork vertice){
        vertices.add(vertice);
    }

    public Fork getVertice(int id){
        for (Fork vertice : vertices) {
            if(vertice.getId()==id){
                return vertice;
            }
        }
        return null;
    }
    
    public void addAresta(Edge aresta){
        arestas.add(aresta);
    }
    
    public Edge getAresta(int idV1,int idV2){
        for (Edge aresta: arestas) {
            if(aresta.getVertice1().getId()==idV1 && aresta.getVertice2().getId()==idV2 || aresta.getVertice1().getId()==idV2 && aresta.getVertice2().getId()==idV1){
                return aresta;
            }
        }
        return null;
    }
    
    public List<Fork> getVizinhos(Fork vertice){
        List<Fork> vizinhos = new ArrayList<>();
        for (Edge aresta: arestas) {
            if(aresta.getVertice1()==vertice){
                vizinhos.add(aresta.getVertice2());
            }
            if(aresta.getVertice2()==vertice){
                vizinhos.add(aresta.getVertice1());
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
        for (Fork vertice : vertices) {
            eu = eu.concat(vertice.getRotulo()+"\n");
        }
        
        eu = eu.concat("Arestas\n");
        for (Edge aresta : arestas) {
            eu = eu.concat(aresta.getVertice1().getRotulo()+" ---- "+aresta.getPeso()+" ---- "+aresta.getVertice2().getRotulo()+"\n");
        }
        return eu;
    }
    
    public void resetVisiteds(){
        for (Fork vertice : vertices) {
            vertice.setVisitado(false);
        }
    }
    
}

class OrdenaPorRotulo implements Comparator<Fork> {
    @Override
    public int compare(Fork um, Fork dois) {
        return um.getRotulo().compareTo(dois.getRotulo());
    }
}