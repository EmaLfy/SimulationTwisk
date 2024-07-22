package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.SimulationIG;
import twisk.outils.ThreadsManager;

public class EcouteurSimuler implements EventHandler<ActionEvent> {

    private MondeIG mondeIG;

    public EcouteurSimuler(MondeIG m) {
        this.mondeIG = m;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            SimulationIG simulation = new SimulationIG(this.mondeIG);
            if (this.mondeIG.isRun()) {
                ThreadsManager.getInstance().detruireTout();
            }
            else {
                simulation.simuler();
            }


        } catch (Exception exception) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur simulation");
            a.setHeaderText("Erreur lors de la simulation");
            a.setContentText(exception.getMessage());
            a.show();
            PauseTransition p = new PauseTransition(Duration.seconds(5));
            p.setOnFinished(event -> {
                a.close();
            });
            p.play();
        }
    }
}
