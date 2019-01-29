import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 Klassen er hovedsaklig lagd for å få opp et tekstfelt
 når brukeren velger tekst og trykker på skjermen.
 Valgte å lage en klasse og ikke en metode i tilfelle
 jeg ville legge til mer funksjonalitet i det vinduet.
 */

public class Systemvindu extends HBox {

    private TextField boksTekst;
    private TextField polygonTekst;

    public Systemvindu(){
        setStyle("-fx-background-color: #c4c4c4;");
        boksTekst = new TextField();
        HBox.setMargin(boksTekst, new Insets(2, 2, 2, 10));
        this.getChildren().add(boksTekst);

    }

    public TextField getBoksTekst() {
        return boksTekst;
    }
}
