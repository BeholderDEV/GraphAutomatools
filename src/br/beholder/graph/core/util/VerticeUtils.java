/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.core.util;

import com.bethecoder.ascii_table.ASCIITable;
import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author lite
 */
public class VerticeUtils {
    
    public static String verticeListToString(List<Vertice> vertices){
        StringBuilder out = new StringBuilder();
        vertices.stream().forEach((vertice) -> {
            out.append(vertice.getRotulo()).append(" - ");
        });
        return out.toString();
    }
    
    public static List<Vertice> getVerticesPath(Vertice vertice){
        List<Vertice> vertices = new ArrayList<>();
        while(vertice.getAnterior()!=null)
        {
            vertices.add(vertice);
            vertice = vertice.getAnterior();
        }
        vertices.add(vertice);
        Collections.reverse(vertices);
        return vertices;
    }
    
    public static String getPath(Grafo grafo, Vertice vertice, int idProcurado){
        StringBuilder resposta = new StringBuilder();
        StringBuilder path= new StringBuilder();
        List<Vertice> vertices;      
        if(vertice==null)
        {
           if(grafo.hasVertice(idProcurado)){
               resposta.append("Vertice encontrado, porém não há Caminho possível para o Vertice");
           }
           else{
               resposta.append("Vertice não encontrado");  
           }
        }
        else
        {
           resposta.append("Vertice encontrado \n\nCaminho: \n");
           vertices = VerticeUtils.getVerticesPath(vertice);
           double peso = 0;
            for (Vertice vertice1 : vertices) {
                path.append(vertice1.getRotulo()).append(" - ");
                if(vertice1.getAnterior()!= null && vertice1!= null)
                {
                    peso += grafo.getAresta(vertice1.getAnterior().getId(),vertice1.getId()).getPeso();
                }               
            }
            path.setLength(path.length()-3);
            
            
            path.append("\n\nPesquisa:\n");
            grafo.getVisitados().stream().forEach((vertice1) -> {
                path.append(vertice1.getRotulo()).append(" - ");
            });
            path.setLength(path.length()-3);
            path.append("\n\nTamanho do Caminho:\n").append(vertices.size()-1);
            path.append("\nPeso do Caminho:\n").append(peso);
            
        }
        
        resposta = resposta.append(path);
        return resposta.toString();
    }
    
    public static String getTabelaCustos(Grafo grafo)
    {
        String [] colunas = new String[grafo.getVerticesCount()+1];
        String [][] matrizDados =  new String[2][grafo.getVerticesCount()+1];
        colunas[0]="Vertices";
        matrizDados[0][0]="Estimativas";
        matrizDados[1][0]="Precedentes";
        for (int i = 0; i < grafo.getVerticesCount(); i++) {
            colunas[i+1]=grafo.getVertices().get(i).getRotulo();
            matrizDados[0][i+1]=grafo.getVertices().get(i).getCusto().toString();
            if(grafo.getVertices().get(i).getAnterior()!=null){
                matrizDados[1][i+1]=grafo.getVertices().get(i).getAnterior().getRotulo();
            }else{
                matrizDados[1][i+1]=grafo.getVertices().get(i).getRotulo();
            }
        }
        return "\n"+ASCIITable.getInstance().getTable(colunas, matrizDados);
        
    }
}
