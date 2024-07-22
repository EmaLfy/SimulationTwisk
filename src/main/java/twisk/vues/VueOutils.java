package twisk.vues;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import twisk.ecouteurs.EcouteurBouton;
import twisk.ecouteurs.EcouteurBoutonGuichet;
import twisk.ecouteurs.EcouteurSimuler;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.SimulationIG;

import java.util.ArrayList;

public class VueOutils extends TilePane implements Observateur {

    private MondeIG m;
    private SimulationIG s;
    private Button simuler;

    private ArrayList<Button> buttons = new ArrayList<>();

    public VueOutils(MondeIG m) {
        this.m = m;
        this.s = s;
        this.setStyle("-fx-background-color: grey; -fx-padding: 12px; -fx-background-radius: 15px");
        Button ajouterActivite = new Button("+A");
        ajouterActivite.setStyle("-fx-border-width: 3px; -fx-border-style: solid; -fx-border-radius: 5px;");
        Tooltip t = new Tooltip("Ajouter une étape");
        ajouterActivite.setTooltip(t);
        ajouterActivite.setOnAction(new EcouteurBouton(this.m)::handle);
        ajouterActivite.setMinSize(30,30);
        buttons.add(ajouterActivite);
        Button ajouterGuichet = new Button("+G");
        ajouterGuichet.setStyle("-fx-border-width: 3px; -fx-border-style: solid; -fx-border-radius: 5px;");
        Tooltip t2 = new Tooltip("Ajouter un guichet");
        ajouterGuichet.setTooltip(t2);
        ajouterGuichet.setOnAction(new EcouteurBoutonGuichet(this.m)::handle);
        ajouterGuichet.setMinSize(30,30);
        buttons.add(ajouterGuichet);
        this.simuler = new Button("Start");

        this.simuler.setStyle("-fx-border-width: 3px; -fx-border-style: solid; -fx-border-radius: 5px;");
        Tooltip t3 = new Tooltip("Simuler");
        this.simuler.setTooltip(t3);
        this.simuler.setOnAction(new EcouteurSimuler(this.m));
        ajouterGuichet.setMinSize(30,30);
        buttons.add(this.simuler);
        this.getChildren().addAll(buttons.get(0), buttons.get(1), buttons.get(2));
    }

    @Override
    public void reagir() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if (m.isRun()) {
                    simuler.setText("Stop");
                    //System.out.println("Simulation en cours");
                } else {
                    simuler.setText("Start");
                    //System.out.println("Simulation terminée");
                }
            }
        };
        Platform.runLater(r);
    }
}
