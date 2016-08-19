/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Grafo;
import core.model.Vertice;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Adson Estevesa
 */
public class BreadthFirstSearch {
    Grafo grafo;
    int verticeInicial;
    int verticeProcurado;
    List<Vertice> visited;

    public BreadthFirstSearch(Grafo grafo) {
        this.grafo = grafo;
        visited = new ArrayList<>();
    }
    
    public void buscar(int idVerticeInicial, int idVerticeProcurado){
        Queue q = new LinkedList();
    }
}
