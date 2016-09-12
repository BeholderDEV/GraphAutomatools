/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.util;

import com.bethecoder.ascii_table.ASCIITable;
import core.model.Grafo;
import core.model.Vertice;
import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthAbsoluteEven;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
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
    
    public static String getPath(Grafo grafo, Vertice vertice, int idProcurado){
        String resposta = "";
        String path="";
        List<Vertice> vertices = new ArrayList<>();        
        if(vertice==null)
        {
           if(grafo.hasVertice(idProcurado)){
               resposta = resposta.concat("Vertice encontrado, porém não há Caminho possível para o Vertice");
           }
           else
           {
             resposta =  resposta.concat("Vertice não encontrado");  
           }
        }
        else
        {
           resposta = "Vertice encontrado \n\nCaminho: \n";
           vertices = VerticeUtils.getVerticesPath(vertice);
           double peso = 0;
            for (Vertice vertice1 : vertices) {
                path = path.concat(vertice1.getRotulo()+" - ");
                if(vertice1.getAnterior()!= null && vertice1!= null)
                {
                    peso += grafo.getAresta(vertice1.getAnterior().getId(),vertice1.getId()).getPeso();
                }               
            }
            
            path = path.substring(0, path.length()-3);
            
            path = path.concat("\n\nPesquisa:\n");
            for (Vertice vertice1 : grafo.getVisitados()) {
                path = path.concat(vertice1.getRotulo()+" - ");
            }
            path = path.substring(0, path.length()-3);
            path = path.concat("\n\nTamanho do Caminho:\n"+(vertices.size()-1));
            path = path.concat("\nPeso do Caminho:\n"+peso);
            
        }
        
        resposta = resposta.concat(path);
        return resposta;
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
