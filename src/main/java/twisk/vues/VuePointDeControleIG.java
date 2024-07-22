package twisk.vues;

import javafx.scene.shape.Circle;
import twisk.ecouteurs.EcouteurPointDeControle;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

public class VuePointDeControleIG extends Circle implements Observateur{
    private MondeIG m;

    private PointDeControleIG pdc;

    public VuePointDeControleIG(MondeIG mondeIG, PointDeControleIG p) {
        super();
        this.pdc = p;
        this.m = mondeIG;
        this.setStyle("-fx-background-color: red");
        this.setRadius(5);
        this.setCenterX(p.getCenterX());
        this.setCenterY(p.getCenterY());
        this.setOnMouseClicked(new EcouteurPointDeControle(this.m, this.pdc));
    }

    @Override
    public void reagir() {

    }
}
