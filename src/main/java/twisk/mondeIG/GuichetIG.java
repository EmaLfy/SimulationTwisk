package twisk.mondeIG;

import com.google.gson.annotations.Expose;
import twisk.vues.TailleComposants;

public class GuichetIG extends EtapeIG{

    @Expose
    private int nbJeton;
    private int nbClient=0;
    @Expose
    private boolean sens;

    public GuichetIG(String nom, int larg, int haut) {
        super(nom, larg, haut);
        this.nbJeton=3;
        this.setType("GuichetIG");
        this.addPointDeControle(new PointDeControleIG(this, this.getPosX(), this.getPosY()+ (haut/2), this.getIdentifiant()+".3")); // gauche
        this.addPointDeControle(new PointDeControleIG(this, this.getPosX()+TailleComposants.getInstance().VBoxWidth()+78, this.getPosY()+(haut/2), this.getIdentifiant()+".4")); // droite
    }

    @Override
    public void reinitPdc() {
        this.getPointDeControle(this.getIdentifiant()+".3").setCenterX(this.getPosX());
        this.getPointDeControle(this.getIdentifiant()+".3").setCenterY(this.getPosY()+ (this.getHauteur()/2));
        this.getPointDeControle(this.getIdentifiant()+".4").setCenterX(this.getPosX()+TailleComposants.getInstance().VBoxWidth()+78);
        this.getPointDeControle(this.getIdentifiant()+".4").setCenterY(this.getPosY()+ (this.getHauteur()/2));
    }

    public PointDeControleIG getPdcDroite(){
        return this.getPointDeControle(this.getIdentifiant()+".4");
    }
    public PointDeControleIG getPdcGauche(){
        return this.getPointDeControle(this.getIdentifiant()+".3");
    }
    public void resetJetons(){
        if (this.nbJeton==0)
            this.nbJeton=3;
    }
    public boolean getSens(){
        return sens;
    }
    public void setSens(boolean sens){
        this.sens = sens;
    }

    @Override
    public int getNbJetons() {
        return nbJeton;
    }

    public void setNbJeton(int jeton) {
        this.nbJeton = jeton;
    }

    public boolean estGuichet(){
        return true;
    }

    @Override
    public boolean isGuichet() {
        return true;
    }

    public void addClient(){
        this.nbClient++;
    }

    public int getNbClient(){
        return this.nbClient;
    }
    public void resetClient(){
        this.nbClient=0;
    }
}
