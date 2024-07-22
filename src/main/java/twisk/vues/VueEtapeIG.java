package twisk.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import twisk.ecouteurs.EcouteurDnDEtape;
import twisk.ecouteurs.EcouteurEtape;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public abstract class VueEtapeIG extends VBox implements Observateur{

    private MondeIG m;

    private EtapeIG etapeIG;
    private Label label;

    public VueEtapeIG(MondeIG monde, EtapeIG etape) {
        this.m = monde;
        this.etapeIG = etape;
        this.setId(this.etapeIG.getIdentifiant());
        if (this.etapeIG.estGuichet()){
            this.setMinSize(this.etapeIG.getLargeur(), this.etapeIG.getHauteur());
            this.label = new Label(etapeIG.getNom() + "  |  Jetons : " + etapeIG.getNbJetons());
        }
        else {
            this.setMinSize(this.etapeIG.getLargeur(), this.etapeIG.getHauteur());
            this.label = new Label(etapeIG.getNom() + "  |  " + etapeIG.getDelai() + " ± " + etapeIG.getEcart());
        }
        this.setOnMouseClicked(new EcouteurEtape(m, etapeIG));
        if (this.etapeIG.estGuichet()){

        }
        //        this.label = new Label(etapeIG.getNom() + "  |  " + etapeIG.getDelai() + " ± " + etapeIG.getEcart());
        this.setSpacing(5);

        this.setPadding(new Insets(10,10, 10,10));
        this.getChildren().add(this.label);
        this.setStyle("  -fx-background-color: white;" +
                "  -fx-border-radius: 6;" +
                "  -fx-background-radius: 6;" +
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");

        if (etapeIG.isIn()){
            this.setStyle("-fx-background-color: green;" +
                    "-fx-border-radius: 6;" +
                    "  -fx-background-radius: 6;" +
                    "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");

        }
        if (etapeIG.isOut()){
            this.setStyle("-fx-background-color: darkred;" +
                    "-fx-border-radius: 6;" +
                    "  -fx-background-radius: 6;" +
                    "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
        }
        if (etapeIG.isIn() && etapeIG.isOut()){
            this.setStyle("-fx-background-color: linear-gradient(darkred, green);" +
                    "-fx-border-radius: 6;" +
                    "  -fx-background-radius: 6;" +
                    "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
        }
        if (etapeIG.isSelct()) {
            this.setStyle(this.getStyle() +
                    "-fx-border-color: red; -fx-border-radius: 6px; -fx-border-width: 2px; -fx-effect: none");
        }
        this.setOnDragDetected(new EcouteurDnDEtape(this));

    }
    public EtapeIG getEtapeIG() {
        return etapeIG;
    }
}
