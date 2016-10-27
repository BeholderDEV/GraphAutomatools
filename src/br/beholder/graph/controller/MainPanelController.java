/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.graph.controller;

import br.beholder.graph.core.algoritmos.coloracao.Coloring;
import br.beholder.graph.core.algoritmos.search.SearchAlgorithm;
import br.beholder.graph.core.algoritmos.search.SearchAlgorithmFactory;
import br.beholder.graph.core.model.Aresta;
import br.beholder.graph.core.model.Grafo;
import br.beholder.graph.core.model.Vertice;
import br.beholder.graph.core.util.VerticeUtils;
import br.beholder.graph.core.web.XMLReader;
import br.beholder.graph.ui.MainPanel;
import br.beholder.graph.ui.graph.GraphDrawer;
import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author lite
 */
public class MainPanelController {
    MainPanel mainPanel;
    private Grafo grafo;
    GraphDrawer drawer;

    public MainPanelController(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
    private int getVerticeID(String rotulo){
        for (Vertice vertice: grafo.getVertices()) {
            if(vertice.getRotulo().equalsIgnoreCase(rotulo)){
                return vertice.getId();
            }
        }
        return -1;
    }
    
    private void setImage(Image image){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPanel imgPanel = mainPanel.getImagePanel();
                imgPanel.removeAll();
                WebImage webImage = new WebImage(image);
                webImage.setDisplayType(DisplayType.fitComponent);
                imgPanel.add(webImage);
                imgPanel.revalidate();
            }
        });
    }
    
    private Image drawImage(){
        drawer = new GraphDrawer(grafo);
        return drawer.drawGraph();
    }
    
    public void readXML(){
        grafo = XMLReader.grafoFromXML();
        setImage(drawImage());
    }
    
    
    public void search(){
        if(grafo!=null){
            grafo.resetProperties();
            SearchAlgorithm sa = SearchAlgorithmFactory.build(grafo, mainPanel.getSearchMethod());
            Vertice vertice = sa.search(getVerticeID(mainPanel.getIDVerticeInicial()),getVerticeID(mainPanel.getIDVerticeFinal()));
            Aresta aresta;
            String path = VerticeUtils.getPath(grafo,vertice,getVerticeID(mainPanel.getIDVerticeFinal()));
            if(mainPanel.getSearchMethod() == SearchAlgorithmFactory.DIJKSTRA_SEARCH){
                path=path.concat(VerticeUtils.getTabelaCustos(grafo));
            }
            
            mainPanel.getTextArea().setText(path);
            if(vertice!=null){
                while(vertice.getAnterior()!=null)
                {
                    aresta = grafo.getAresta(vertice.getAnterior().getId() ,vertice.getId());
                    aresta.setHinted(true);
                    vertice = vertice.getAnterior();
                }
            }
            setImage(drawImage());
        }
        else{
        }
    }
    
    public void conectividade(){
        String resposta = (grafo.isConexo()) ? "Grafo Conexo":"Grafo Desconexo";
        mainPanel.getTextArea().setText(resposta);
    }
    
    public void planaridade(){
        String resposta = (grafo.isPlanar()) ? "Grafo Planar":"Grafo não Planar";
        mainPanel.getTextArea().setText(resposta);
    }
    
    public void coloracao()
    {
        Coloring coloracao =  new Coloring(grafo);        
        String resposta = "Usa-se "+coloracao.ColorGraph()+" cores";
        mainPanel.getTextArea().setText(resposta);
        setImage(drawImage());
    }
    
}
