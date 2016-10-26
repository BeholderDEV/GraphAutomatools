/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.model;

/**
 *
 * @author lite
 */
public class Aresta {
    private Vertice vertice1;
    private Vertice vertice2;
    private Double peso;
    private boolean hinted=false;

    public Aresta(Vertice vertice1, Vertice vertice2, Double peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    public boolean isHinted() {
        return hinted;
    }

    public void setHinted(boolean hinted) {
        this.hinted = hinted;
    }
    
    public Aresta(Vertice vertice1, Vertice vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
    }

    public Vertice getVertice1() {
        return vertice1;
    }

    public void setVertice1(Vertice vertice1) {
        this.vertice1 = vertice1;
    }

    public Vertice getVertice2() {
        return vertice2;
    }

    public void setVertice2(Vertice vertice2) {
        this.vertice2 = vertice2;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
    
}
