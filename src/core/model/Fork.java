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
public class Fork {
    private final int id;
    private String rotulo;
    private Point position;
    boolean visitado = false;
    private Fork anterior = null;

    public Fork(int id, String rotulo) {
        this.id = id;
        this.rotulo = rotulo;
    }

    public Fork(int id, String rotulo, Point position) {
        this.id = id;
        this.rotulo = rotulo;
        this.position = position;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
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

    public Fork getAnterior() {
        return anterior;
    }

    public void setAnterior(Fork anterior) {
        this.anterior = anterior;
    }
    
}
