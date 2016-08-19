/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

import core.model.Grafo;
import core.model.Vertice;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author lite
 */
public class DeepFirstSearch {
    
    
    public static Stack search(Grafo grafo, int idVerticeInicial, int idVerticeProcurado){
        Stack<Vertice> stack = new Stack<>();
        stack.add(grafo.getVertice(idVerticeInicial));
        grafo.getVertice(idVerticeInicial).setVisitado(true);
        while (!stack.isEmpty())  
        {  
            Vertice vertice=stack.pop();  
            System.out.print(vertice.getRotulo() + "\t");  

            ArrayList<Vertice> vizinhos = (ArrayList < Vertice >) grafo.getVizinhos(vertice);  
            for (int i = 0; i < vizinhos.size(); i++) {  
                Vertice n=vizinhos.get(i);
                if(n.getId()==idVerticeProcurado){
                    stack.add(n);
                    return stack;
                }
                if(n!=null && !n.isVisitado())  
                {  
                    stack.add(n);  
                    n.setVisitado(true);

                }  
            }  
        }
        return stack;
    }
}
