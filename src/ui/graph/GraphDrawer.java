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
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import ui.utils.ColorController;

/**
 *
 * @author lite
 */
public class GraphDrawer {
    private Grafo graph;
    private int nodeSize = 35;
    private int fontWidth;
    private int fontHeight;
    private double phi = Math.toRadians(15);
    private int barb= 20;
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

        gd.setStroke(new BasicStroke(3.0f));
        for (Aresta edge: graph.getArestas()) {
            if(edge.isHinted()){
                gd.setColor(new Color(0,239,192));
            }
            else{
                gd.setColor(ColorController.COR_LETRA);
            }
            Point sw = edge.getVertice1().getPosition();
            Point ne = edge.getVertice2().getPosition();
            gd.drawLine(sw.x, sw.y, ne.x, ne.y);
            if(graph.getDirigido())                
            {
                drawArrowHead(gd, ne, sw);
            }
            
        }
        
        for (Vertice fork: graph.getVertices()) {
            gd.setColor(new Color(69,189,255));
            gd.fillOval(fork.getPosition().x-nodeSize/2, fork.getPosition().y-nodeSize/2, nodeSize, nodeSize);
            gd.setColor(ColorController.COR_LETRA);
            gd.drawOval(fork.getPosition().x-nodeSize/2, fork.getPosition().y-nodeSize/2, nodeSize, nodeSize);

            gd.setColor(ColorController.FUNDO_MEDIO);
            fontWidth = gd.getFontMetrics().stringWidth(fork.getRotulo());
            fontHeight = gd.getFontMetrics().getHeight()-gd.getFontMetrics().getDescent();
            gd.drawString(fork.getRotulo(),fork.getPosition().x-(fontWidth/2), fork.getPosition().y+(fontHeight/2));
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
    
    private void drawArrowHead(Graphics2D g2, Point tip, Point tail)
    {
        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double theta = Math.atan2(dy, dx);
        //System.out.println("theta = " + Math.toDegrees(theta));
        double x, y, rho = theta + phi;
        
        double iniX = tip.x - (nodeSize/2) * Math.cos(theta);
        double iniY = tip.y - (nodeSize/2) * Math.sin(theta);
        
        for(int j = 0; j < 2; j++)
        {
            x = iniX - barb * Math.cos(rho);
            y = iniY - barb * Math.sin(rho);
            g2.draw(new Line2D.Double(iniX, iniY, x, y));
            rho = theta - phi;
        }
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
