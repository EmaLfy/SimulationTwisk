package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import twisk.exceptions.ArcException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

public class EcouteurPointDeControle implements EventHandler<MouseEvent> {

    private MondeIG m;

    private PointDeControleIG pdc;

    public EcouteurPointDeControle(MondeIG mondeIG, PointDeControleIG p){
        this.pdc = p;
        this.m = mondeIG;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        try{
            m.ajouterPDCTempo(this.pdc);
        }
        catch (ArcException e){

            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur création arc");
            a.setHeaderText("Erreur lors de la création de l'arc");
            a.setContentText("Un arc ne peut être crée entre 2 points d'une\nmême étape, si cet arc existe déjà ou s'il y a un cycle.");
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(5));
            p.setOnFinished(actionEvent -> {
                a.close();
            });
            p.play();

        }

    }
}
