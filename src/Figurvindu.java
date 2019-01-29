import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Figurvindu extends GridPane {

    private ToggleButton[] buttons = {new ToggleButton("Tekst"), new ToggleButton("Linje"),
            new ToggleButton("Polygon"), new ToggleButton("Sirkel"), new ToggleButton("Rektangel"),
            new ToggleButton("Velg")};
    private ColorPicker linjeF = new ColorPicker(Color.BLACK);
    private ColorPicker fyllF = new ColorPicker(Color.TRANSPARENT);

    public Figurvindu(){
        setup();
    }

    private void setup(){
        plasserElement(buttons);
        this.setHgap(50);
        setPadding(new Insets(15, 8, 15,8));
        setStyle("-fx-background-color: #c4c4c4;");
    }
    // Plasserer de forskjellige elementene og putter style på dem
    private void plasserElement(ToggleButton[] button ){
        ToggleGroup tg = new ToggleGroup();
        for(int i = 0; i < button.length; i++){
            button[i].setStyle("-fx-background-radius: 10 10 10 10; -fx-base: #c4c4c4;-fx-font: 14 arial;" +
                                "-fx-base: #008B8B; -fx-font-size: 12px;" +
                                "-fx-text-fill:white; -fx-font-family: \"Arial Narrow\";" +
                                "-fx-font-weight: bold;");
            button[i].setMinWidth(80);
            button[i].setMinHeight(30);
            button[i].setToggleGroup(tg);
            this.add(button[i], i, 1);
        }
            button[5].setStyle("-fx-background-radius: 10 10 10 10; -fx-base: #c4c4c4;-fx-font: 14 arial;" +
                "-fx-base: #3CB371; -fx-font-size: 12px;" +
                "-fx-text-fill:white; -fx-font-family: \"Arial Narrow\";" +
                "-fx-font-weight: bold;");

        GridPane farger = new GridPane();
        this.add(farger, 7, 1);

        Label linje = new Label("Linjefarge:");
        Label fyll = new Label("Fyllfarge:");
        linje.setFont(Font.font("Monospace",
                FontWeight.BOLD, FontPosture.REGULAR, 13));
        fyll.setFont(Font.font("Monospace",
                FontWeight.BOLD, FontPosture.REGULAR, 13));
        farger.add(linje, 0, 0);
        farger.add(linjeF, 0, 1);
        farger.add(fyll, 1, 0);
        farger.add(fyllF, 1, 1);
    }

    public ToggleButton[] getButtons(){
        return buttons;
    }
    public ColorPicker getLinjeF(){
        return linjeF;
    }
    public ColorPicker getFyllF(){
        return fyllF;
    }
}
