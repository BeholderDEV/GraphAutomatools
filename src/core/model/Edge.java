/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.model;

/**
 *
 * @author lite
 */
public class Edge {
    private Fork vertice1;
    private Fork vertice2;
    private Double peso;

    public Edge(Fork vertice1, Fork vertice2, Double peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    public Edge(Fork vertice1, Fork vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
    }

    public Fork getVertice1() {
        return vertice1;
    }

    public void setVertice1(Fork vertice1) {
        this.vertice1 = vertice1;
    }

    public Fork getVertice2() {
        return vertice2;
    }

    public void setVertice2(Fork vertice2) {
        this.vertice2 = vertice2;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
    
}
