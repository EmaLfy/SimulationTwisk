package twisk.mondeIG;

import com.google.gson.annotations.Expose;

public class ArcIG {
    @Expose
    private PointDeControleIG pdc1;
    @Expose
    private PointDeControleIG pdc2;

    private boolean estSelectionne;

    public ArcIG(PointDeControleIG pdc1, PointDeControleIG pdc2) {
        this.pdc1 = pdc1;


        this.pdc2 = pdc2;

        this.estSelectionne = false;
    }

    public PointDeControleIG getPdc1() {
        return pdc1;
    }

    public void setPdc1(PointDeControleIG pdc1) {
        this.pdc1 = pdc1;
    }

    public PointDeControleIG getPdc2() {
        return pdc2;
    }

    public void setPdc2(PointDeControleIG pdc2) {
        this.pdc2 = pdc2;
    }

    public boolean isEstSelectionne() {
        return estSelectionne;
    }

    public void setEstSelectionne(boolean estSelectionne) {
        this.estSelectionne = estSelectionne;
    }

    public int getX1(){
        return this.pdc1.getCenterX();
    }
    public int getY1(){
        return this.pdc1.getCenterY();
    }
    public int getX2(){
        return this.pdc2.getCenterX();
    }
    public int getY2(){
        return this.pdc2.getCenterY();
    }

    public void rm(){
        this.pdc1.getEtapeIG().rmSuccesseur(this.pdc2.getEtapeIG());
        this.pdc2.getEtapeIG().rmPredecesseur(this.pdc1.getEtapeIG());
    }
}
