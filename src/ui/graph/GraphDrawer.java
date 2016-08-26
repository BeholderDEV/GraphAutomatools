/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.graph;

import core.model.Edge;
import core.model.Fork;
import core.model.Graph;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author lite
 */
public class GraphDrawer {
    private Graph graph;
    private int nodeSize = 35;
    private int width;
    private  int height;
    
    public GraphDrawer(Graph graph) {
        this.graph = graph;
    }
    
    public Image drawGraph(){
        Point size = getGraphSize();
        BufferedImage bufferedImage = new BufferedImage(size.x, size.y, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = bufferedImage.getGraphics();
        Graphics2D gd = (Graphics2D) g;
        
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        for (Edge edge: graph.getArestas()) {
            gd.setColor(Color.DARK_GRAY);
            gd.drawLine(edge.getVertice1().getPosition().x, edge.getVertice1().getPosition().y, edge.getVertice2().getPosition().x, edge.getVertice2().getPosition().y);
        }
        for (Fork fork: graph.getVertices()) {
            gd.setColor(Color.ORANGE);
            gd.fillOval(fork.getPosition().x-nodeSize/2, fork.getPosition().y-nodeSize/2, nodeSize, nodeSize);
            gd.setColor(Color.BLACK);
                
            width = gd.getFontMetrics().stringWidth(fork.getRotulo());
            height = gd.getFontMetrics().getHeight()-gd.getFontMetrics().getDescent();
            gd.drawString(fork.getRotulo(),fork.getPosition().x-(width/2), fork.getPosition().y+(height/2));
            
            
        }
        
        return  bufferedImage;
    }
    
    private Point getGraphSize(){
        Point point = new Point();
        for (Fork fork: graph.getVertices()) {
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
