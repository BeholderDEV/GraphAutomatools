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
    
    
    public static Vertice search(Grafo grafo, int idVerticeInicial, int idVerticeProcurado){
        Stack<Vertice> stack = new Stack<>();
        Vertice verticeInicial = grafo.getVertice(idVerticeInicial);
        stack.add(verticeInicial);
        verticeInicial.setVisitado(true);
        System.out.println(" Inicial --- "+verticeInicial.getRotulo()+" - "+verticeInicial.getId());
        for(Vertice n: grafo.getVizinhos(verticeInicial))
        {
            //if childs state is not visited then recurse
            if(!n.isVisitado())
            {
                System.out.println(n.getRotulo()+" - "+n.getId());
                n.setAnterior(verticeInicial);
                if(n.getId() == idVerticeProcurado){
                    System.out.println("achooo");
                    return n;
                }
                return DeepFirstSearch.search(grafo, n.getId(), idVerticeProcurado);
            }
        }
        return null;
    }
//    public static Vertice search(Grafo grafo, int idVerticeInicial, int idVerticeProcurado){
//        Stack<Vertice> stack = new Stack<>();
//        stack.add(grafo.getVertice(idVerticeInicial));
//        grafo.getVertice(idVerticeInicial).setVisitado(true);
//        while (!stack.isEmpty())  
//        {  
//            Vertice vertice=stack.pop();
//            ArrayList<Vertice> vizinhos = (ArrayList < Vertice >) grafo.getVizinhos(vertice);  
//            for (int i = 0; i < vizinhos.size(); i++) {  
//                Vertice n=vizinhos.get(i);
//                if(n.getId()==idVerticeProcurado){
//                    stack.add(n);
//                    n.setVisitado(true);
//                    n.setAnterior(vertice);
//                    return n;
//                }
//                if(n!=null && !n.isVisitado())  
//                {
//                    stack.add(n);
//                    n.setVisitado(true);
//                    n.setAnterior(vertice);
//                }  
//            }  
//        }
//        return null;
//    }
}
