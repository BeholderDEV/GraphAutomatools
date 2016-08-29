/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util;

import core.model.Vertice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author lite
 */
public class VerticeUtils {
    
    public static String verticeListToString(List<Vertice> vertices){
        String out = "";
        for (Vertice vertice : vertices) {
            out = out.concat(vertice.getRotulo()+" - ");
        }
        return out;
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
    
    public static String getPath(Vertice vertice){
        String resposta = "";
        String path="";
        List<Vertice> vertices = new ArrayList<>();
        if(vertice==null)
        {
           resposta = "Vertice não encontrado";
        }
        else
        {
           resposta = "Vertice encontrado \n caminho: \n";
           vertices = VerticeUtils.getVerticesPath(vertice);
            for (Vertice vertice1 : vertices) {
                path = path.concat(vertice1.getRotulo()+" - ");
            }
            path = path.substring(0, path.length()-3);
        }
        
        resposta = resposta.concat(path);
        return resposta;
    }
}
