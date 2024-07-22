package twisk;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.SimulationIG;
import twisk.outils.KitC;
import twisk.outils.ThreadsManager;
import twisk.vues.*;

public class MainTwisk extends Application {

    @Override
    public void start(Stage stage){
        BorderPane root = new BorderPane();
        MondeIG m = new MondeIG();
        VueMenu vueMenu = new VueMenu(m);
        VueOutils vueOutils = new VueOutils(m);
        VueMondeIG vueMondeIG = new VueMondeIG(m);
        m.ajouterObservateur(vueMondeIG);
        m.ajouterObservateur(vueOutils);
        m.ajouterObservateur(vueMenu);
        root.setTop(vueMenu);
        root.setCenter(vueMondeIG);
        root.setBottom(vueOutils);

        stage.setTitle("TWISK ALPHA v0.71");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        ThreadsManager.getInstance().detruireTout();
        super.stop();
        Platform.exit();
        //System.out.println(ThreadsManager.getInstance().getSize());
        System.exit(0);
    }
}
