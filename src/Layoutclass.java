import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Obligatorisk oppgave 1 i objektorientert programmering
 * Et simpelt tegneprogram med noe funksjonalitet som
 * å lage figurer, dra figurer og få informasjon om dem.
 *
 * Ble litt blanding mellom Norsk og Engelsk.
 *
 * @author  Jan Andreas Sletta
 * @version 1.0
 * @since   15.10.2018
 */

public class Layoutclass extends Application {

    private BorderPane mainPane;

    // Her setter jeg bare opp Gui'en helt grunnleggende
    public void start(Stage stage) {
        mainPane = new BorderPane();
        Figurvindu figurVindu = new Figurvindu();
        Tegnevindu tegneVindu = new Tegnevindu(this, figurVindu);

        mainPane.setTop(figurVindu);
        mainPane.setCenter(tegneVindu);

        Scene scene = new Scene(mainPane, 1100, 650);
        stage.setTitle("Tegneprogram");
        stage.setScene(scene);
        stage.show();
    }

    public BorderPane getMainPane(){
        return mainPane;
    }

}