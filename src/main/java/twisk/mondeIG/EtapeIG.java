package twisk.mondeIG;

import com.google.gson.annotations.Expose;
import twisk.outils.FabriqueIdentifiant;
import twisk.vues.TailleComposants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public abstract class EtapeIG implements Iterable<PointDeControleIG>{
    @Expose
    private String nom, identifiant, type;
    @Expose
    private int posX, posY, largeur, hauteur, delai, ecart;

    @Expose
    private boolean in, out;
    private boolean selct;

    @Expose
    private ArrayList<PointDeControleIG> pdc;

    private ArrayList<EtapeIG> successeurs;

    private ArrayList<EtapeIG> predecesseurs;

    public EtapeIG(String nom, int largeur, int hauteur) {
        Random r = new Random();
        this.pdc = new ArrayList<>(4);
        this.posX = r.nextInt(500);
        this.posY = r.nextInt(400);
        this.identifiant = FabriqueIdentifiant.getInstance().getCptEtape();
        this.nom = nom;
        this.delai = 3;
        this.ecart = 2;
        this.selct = false;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.in = false;
        this.out = false;
        this.successeurs = new ArrayList<>();
        this.predecesseurs= new ArrayList<>();

    }


    public void resetSuccesseursPredecesseurs(){
        if (this.successeurs == null) {
            this.successeurs = new ArrayList<>();
        }
        if (this.predecesseurs == null) {
            this.predecesseurs = new ArrayList<>();
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelct() {
        return selct;
    }

    public void setSelct(boolean selct) {
        this.selct = selct;
    }

    public int getDelai() {
        return delai;
    }

    public boolean estGuichet(){
        return false;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public int getEcart() {
        return ecart;
    }

    public void setEcart(int ecart) {
        this.ecart = ecart;
    }

    public boolean isIn() {
        return in;
    }

    public void setIn(boolean in) {
        this.in = in;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void addPointDeControle(PointDeControleIG pdc){
        this.pdc.add(pdc);
    }

    public PointDeControleIG getPointDeControle(String id){
        for (PointDeControleIG p : this.pdc){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public abstract void setNbJeton(int nbJetons);

    public abstract boolean isGuichet();

    public abstract void reinitPdc();

    public abstract int getNbJetons();

    public void addSuccesseur(EtapeIG e){
        this.successeurs.add(e);
        //System.out.println("Successeurs "+ this.nom+" : " +this.successeurs.toString());
    }

    public void rmSuccesseur(EtapeIG e){
        this.successeurs.remove(e);
        //System.out.println("Successeurs "+ this.nom+" : " +this.successeurs.toString());
    }

    public ArrayList<EtapeIG> getSuccesseurs(){
        ArrayList<EtapeIG> out= new ArrayList<>();
        for (EtapeIG e : this.successeurs){
            out.add(e);
        }
        return out;
    }

    public int getNbSuccesseurs(){
        return this.successeurs.size();
    }

    public void addPredecesseur(EtapeIG e){
        this.predecesseurs.add(e);
        //System.out.println("Predecesseurs "+ this.nom+" : " +this.predecesseurs.toString());
    }

    public void rmPredecesseur(EtapeIG e){
        this.predecesseurs.remove(e);
        //System.out.println("Predecesseurs "+ this.nom+" : " +this.predecesseurs.toString());

    }

    public void setSens(boolean sens){
    }

    public boolean getSens(){
        return false;
    }

    public ArrayList<EtapeIG> getPredecesseurs(){
        ArrayList<EtapeIG> out= new ArrayList<>();
        for (EtapeIG e : this.predecesseurs){
            out.add(e);
        }
        return out;
    }

    public int getNbPredecesseurs(){
        return this.predecesseurs.size();
    }

    @Override
    public Iterator<PointDeControleIG> iterator() {
        return this.pdc.iterator();
    }

    public abstract void addClient();
    public abstract int getNbClient();
    public abstract void resetClient();

    @Override
    public String toString() {
        return "EtapeIG{" +
                "nom='" + nom + '\'';
    }
}
