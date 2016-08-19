/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Grafo;
import core.model.Vertice;
import java.util.Stack;

/**
 *
 * @author lite
 */
public class DeepFirstSearch {
    Grafo grafo;
    Vertice verticeInicial;
    
    public DeepFirstSearch(Grafo grafo) {
        this.grafo = grafo;
    }
    
    public Stack search(int idVerticeInicial, int idVerticeProcurado){
        Stack<Vertice> stack = new Stack<>();
        stack.add(grafo.getVertice(idVerticeInicial));
        return stack;
    }
}
