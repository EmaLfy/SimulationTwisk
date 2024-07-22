package twisk.vues;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.ecouteurs.EcouteurArc;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

import java.awt.*;

public class VueArcIG extends Group implements Observateur{

    private ArcIG a;

    private MondeIG m;

    public VueArcIG(MondeIG monde, ArcIG arc) {
        //super();
        this.a = arc;
        this.m = monde;

        Line l = new Line(a.getX1(), a.getY1(), a.getX2(), a.getY2());
        l.setStrokeWidth(3);
        l.setOnMouseClicked(new EcouteurArc(this.m, this.a));

        this.getChildren().add(l);
        Polyline pointe = new Polyline();
        pointe.setStrokeWidth(1);

        Point c = new Point(a.getX2() - a.getX1(), a.getY2() - a.getY1());
        double rap = 15.0 / Math.sqrt(Math.pow((a.getX2() - a.getX1()), 2) + Math.pow((a.getY2() - a.getY1()), 2));
        double dX = -c.getX() * rap;
        double dY = -c.getY() * rap;
        double eX = a.getX2() + dX;
        double eY = a.getY2() + dY;
        dX /= 2;
        dY /= 2;

        pointe.getPoints().addAll((double) a.getX2(), (double) a.getY2(), eX - dY, eY + dX, eX + dY, eY - dX, (double) a.getX2(), (double) a.getY2());
        pointe.setFill(Color.RED);
        if (a.isEstSelectionne()){
            l.setStrokeWidth(5);
            pointe.setStrokeWidth(3);
        }
        this.getChildren().add(pointe);
    }

    @Override
    public void reagir() {
    }
}
