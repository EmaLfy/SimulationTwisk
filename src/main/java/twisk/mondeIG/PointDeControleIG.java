package twisk.mondeIG;

import com.google.gson.annotations.Expose;

import java.awt.*;

public class PointDeControleIG {

    private int centerX, centerY;

    private EtapeIG etapeIG;

    @Expose
    private String id;


    public PointDeControleIG(EtapeIG e, int x, int y, String s) {
        this.centerX = x;
        this.centerY = y;
        this.id = s;
        this.etapeIG = e;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public EtapeIG getEtapeIG() {
        return etapeIG;
    }

    public void setEtapeIG(EtapeIG etapeIG) {
        this.etapeIG = etapeIG;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
