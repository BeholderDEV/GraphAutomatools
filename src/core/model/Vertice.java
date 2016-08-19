/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.model;

import java.awt.Point;


/**
 *
 * @author lite
 */
public class Vertice {
    private final int id;
    private String rotulo;
    private Point position;

    public Vertice(int id, String rotulo) {
        this.id = id;
        this.rotulo = rotulo;
    }

    public Vertice(int id, String rotulo, Point position) {
        this.id = id;
        this.rotulo = rotulo;
        this.position = position;
    }
    

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    
    public int getId() {
        return id;
    }

    public String getRotulo() {
        return rotulo;
    }

    public Point getPosition() {
        return position;
    }
    
}
