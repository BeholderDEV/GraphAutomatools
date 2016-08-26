/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.graph;

import core.model.Aresta;
import core.model.Vertice;
import core.model.Grafo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import ui.utils.ColorController;

/**
 *
 * @author lite
 */
public class GraphDrawer {
    private Grafo graph;
    private int nodeSize = 35;
    private int width;
    private  int height;
    
    public GraphDrawer(Grafo graph) {
        this.graph = graph;
    }
    
    
    public Image drawGraph(){
        Point maxSize = getGraphMaxPoint();
        Point minSize = getGraphMinPoint();
        BufferedImage bufferedImage = new BufferedImage(maxSize.x, maxSize.y, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = bufferedImage.getGraphics();
        Graphics2D gd = (Graphics2D) g;
        
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        gd.setStroke(new BasicStroke(3));
        for (Aresta edge: graph.getArestas()) {
            if(edge.isHinted()){
//                gd.setColor(new Color(69,189,255));
                gd.setColor(new Color(0,239,192));
            }
            else{
                gd.setColor(ColorController.COR_LETRA);
            }
            gd.drawLine(edge.getVertice1().getPosition().x, edge.getVertice1().getPosition().y, edge.getVertice2().getPosition().x, edge.getVertice2().getPosition().y);
        }
        for (Vertice fork: graph.getVertices()) {
            gd.setColor(new Color(69,189,255));
//            gd.setColor(new Color(0,239,192));
            gd.fillOval(fork.getPosition().x-nodeSize/2, fork.getPosition().y-nodeSize/2, nodeSize, nodeSize);
            gd.setColor(ColorController.FUNDO_ESCURO);
                
            width = gd.getFontMetrics().stringWidth(fork.getRotulo());
            height = gd.getFontMetrics().getHeight()-gd.getFontMetrics().getDescent();
            gd.drawString(fork.getRotulo(),fork.getPosition().x-(width/2), fork.getPosition().y+(height/2));
            
            
        }
        
        if(minSize.x<0){
            minSize.x=0;
        }
        if(minSize.y<0){
            minSize.y=0;
        }
        bufferedImage = bufferedImage.getSubimage(minSize.x, minSize.y, maxSize.x-minSize.x, maxSize.y-minSize.y);
        return  bufferedImage;
    }
    
    private Point getGraphMinPoint(){
        Point point = new Point();
        point.x = graph.getVertices().get(0).getPosition().x;
        point.y = graph.getVertices().get(0).getPosition().y;
        for (Vertice fork: graph.getVertices()) {
            if(fork.getPosition().x<point.x){
                point.x=fork.getPosition().x;
            }
            if(fork.getPosition().y<point.y){
                point.y=fork.getPosition().y;
            }
        }
        point.x -= 50;
        point.y -= 50;
        return point;
    }
    private Point getGraphMaxPoint(){
        Point point = new Point();
        for (Vertice fork: graph.getVertices()) {
            if(fork.getPosition().x>point.x){
                point.x=fork.getPosition().x;
            }
            if(fork.getPosition().y>point.y){
                point.y=fork.getPosition().y;
            }
        }
        point.x += 50;
        point.y += 50;
        return point;
    }
}
