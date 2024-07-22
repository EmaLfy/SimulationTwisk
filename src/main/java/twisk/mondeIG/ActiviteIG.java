package twisk.mondeIG;

import twisk.vues.TailleComposants;

public class ActiviteIG extends EtapeIG{


    public ActiviteIG(String nom, int larg, int haut) {
        super(nom, larg, haut);
        this.setType("ActiviteIG");
        this.addPointDeControle(new PointDeControleIG(this, this.getPosX()+ (TailleComposants.getInstance().VBoxWidth()/2), this.getPosY(), this.getIdentifiant()+".1")); // haut
        this.addPointDeControle(new PointDeControleIG(this, this.getPosX()+ (TailleComposants.getInstance().VBoxWidth()/2), this.getPosY()+ TailleComposants.getInstance().VBoxHeigth(), this.getIdentifiant()+".2")); // bas
        this.addPointDeControle(new PointDeControleIG(this, this.getPosX(), this.getPosY()+ (TailleComposants.getInstance().VBoxHeigth()/2), this.getIdentifiant()+".3")); // gauche
        this.addPointDeControle(new PointDeControleIG(this, this.getPosX()+TailleComposants.getInstance().VBoxWidth(), this.getPosY()+(TailleComposants.getInstance().VBoxHeigth()/2), this.getIdentifiant()+".4")); // droite
    }
    public void reinitPdc() {
        this.getPointDeControle(this.getIdentifiant()+".1").setCenterX(this.getPosX()+ (TailleComposants.getInstance().VBoxWidth()/2));
        this.getPointDeControle(this.getIdentifiant()+".1").setCenterY(this.getPosY());
        this.getPointDeControle(this.getIdentifiant()+".2").setCenterX(this.getPosX()+ (TailleComposants.getInstance().VBoxWidth()/2));
        this.getPointDeControle(this.getIdentifiant()+".2").setCenterY(this.getPosY()+ TailleComposants.getInstance().VBoxHeigth());
        this.getPointDeControle(this.getIdentifiant()+".3").setCenterX(this.getPosX());
        this.getPointDeControle(this.getIdentifiant()+".3").setCenterY(this.getPosY()+ (TailleComposants.getInstance().VBoxHeigth()/2));
        this.getPointDeControle(this.getIdentifiant()+".4").setCenterX(this.getPosX()+TailleComposants.getInstance().VBoxWidth());
        this.getPointDeControle(this.getIdentifiant()+".4").setCenterY(this.getPosY()+ (TailleComposants.getInstance().VBoxHeigth()/2));

    }

    @Override
    public boolean isGuichet() {
        return false;
    }

    @Override
    public int getNbJetons() {
        return 0;
    }
    public void setNbJeton(int i){
    }

    @Override
    public void addClient() {

    }

    public void resetClient(){

    }

    @Override
    public int getNbClient() {
        return 0;
    }
}
