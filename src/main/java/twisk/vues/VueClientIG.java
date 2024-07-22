package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.mondeIG.ClientIG;
import twisk.mondeIG.MondeIG;

import java.util.Random;

public class VueClientIG extends Circle {
    private ClientIG c;
    private MondeIG monde;
    private int posX,posY;
    public VueClientIG(ClientIG c, MondeIG monde){
        this.c=c;
        this.monde=monde;
        if(c.getEtapeIG()!=null){
            Random rand = new Random();
            if(c.getEtapeIG().isGuichet()) {
                if (c.getEtapeIG().getSens()) {
                    int cl = c.getEtapeIG().getNbClient();
                    this.posX = (c.getEtapeIG().getPosX() + c.getEtapeIG().getLargeur() - 1) - cl * 25;
                    this.posY = (c.getEtapeIG().getPosY() + c.getEtapeIG().getHauteur() - 27);
                    c.getEtapeIG().addClient();
                }
                else {
                    int cl = c.getEtapeIG().getNbClient();
                    this.posX = c.getEtapeIG().getPosX() + 25 + cl * 25;
                    this.posY = (c.getEtapeIG().getPosY() + c.getEtapeIG().getHauteur() - 27);
                    c.getEtapeIG().addClient();
                }
            }else{
//                this.posX = c.getEtapeIG().getPosX()+c.getEtapeIG().getLargeur()/2;
//                this.posY = c.getEtapeIG().getPosY()+c.getEtapeIG().getHauteur()/2;
                int minX = c.getEtapeIG().getPosX()+30;
                int maxX = minX + c.getEtapeIG().getLargeur()-50;
                int minY = c.getEtapeIG().getPosY()+40;
                int maxY = minY + c.getEtapeIG().getHauteur()-70;

                this.posX = rand.nextInt(maxX - minX) + minX;
                this.posY = rand.nextInt(maxY - minY) + minY;
            }
            this.setCenterX(posX);
            this.setCenterY(posY);
            this.setRadius(4);
            this.setFill(Color.RED);
            //System.out.println("Client créé");
            //System.out.println("\nPosition du client :\n  - X : "+this.posX+"  - Y : "+this.posY+"\n");
        }
//        this.posX=c.getEtapeIG().getPosX();
//        this.posY=c.getEtapeIG().getPosY();
//        this.setCenterX(posX);
//        this.setCenterY(posY);
//        this.setRadius(5);
//        this.setStyle("-fx-background-color: darkred");
//        System.out.println("Client créé");
    }
//        this.c=c;
//        this.setStyle("-fx-background-color: darkred");
//        this.setRadius(5);

    public ClientIG getClient(){
        return c;
    }
}
