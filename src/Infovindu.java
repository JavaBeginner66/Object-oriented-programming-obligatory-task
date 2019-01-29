import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Collections;

/**
 * En ny instans av Infovindu klassen blir opprettet for hver gang
 * brukeren trykker på en figur.
 */

public class Infovindu extends BorderPane {

    private GridPane info;
    private GridPane edit;

    private TextField[] figurInfo = {new TextField(), new TextField(),
                                new TextField(), new TextField(), new TextField(), new TextField()};
    private Button[] buttons = { new Button("Flytt bakover"), new Button("Flytt bakerst"),
                                new Button("Flytt framover"), new Button("Flytt fremst"),
                                new Button("Større"), new Button("Mindre")};
    private ColorPicker linjeFarge = new ColorPicker();
    private ColorPicker fyllFarge = new ColorPicker();

    // Typetvinger figur som kommer inn via parameter om til
    // interface-typen, og bruker  figur-metodene med polymorphism
    public Infovindu(Shape form, ArrayList<Shape> figurTab, Tegnevindu vindu){
        setup();
        Figuregenskaper nyForm = (Figuregenskaper) form;
        changeIndex(form, figurTab, vindu);
        changeArea(nyForm);
        figurInfo[0].setText(nyForm + "");
        figurInfo[1].setText(nyForm.getFarge());
        figurInfo[2].setText(nyForm.omkrets() + "");
        figurInfo[3].setText(nyForm.areal() + "");
        figurInfo[4].setText(figurTab.indexOf(nyForm) + "");
    }

    // Bytter posisjon på figurene ved klikk på knapp
    private void changeIndex(Shape form, ArrayList<Shape> figurTab, Tegnevindu tegneVindu){
        // Flytt bakover
        buttons[0].setOnAction(e -> {
            if(figurTab.indexOf(form) > 0) {
                Collections.swap(figurTab, figurTab.indexOf(form) - 1, figurTab.indexOf(form));
                tegneVindu.getChildren().setAll(figurTab);
            }
            figurInfo[4].setText(figurTab.indexOf(form) + "");
        });
        // Flytt bakerst
        buttons[1].setOnAction(e -> {
            while(figurTab.indexOf(form) != 0) {
                Collections.swap(figurTab, figurTab.indexOf(form) - 1, figurTab.indexOf(form));
                tegneVindu.getChildren().setAll(figurTab);
            }
            figurInfo[4].setText(figurTab.indexOf(form) + "");
        });
        // Flytt framover
        buttons[2].setOnAction(e -> {
            if(figurTab.indexOf(form)+1 <= figurTab.size()-1) {
                Collections.swap(figurTab, figurTab.indexOf(form) + 1, figurTab.indexOf(form));
                tegneVindu.getChildren().setAll(figurTab);
            }
            figurInfo[4].setText(figurTab.indexOf(form) + "");
        });
        // Flytt fremst
        buttons[3].setOnAction(e -> {
            while(figurTab.indexOf(form) != figurTab.size()-1) {
                Collections.swap(figurTab, figurTab.indexOf(form) + 1, figurTab.indexOf(form));
                tegneVindu.getChildren().setAll(figurTab);
            }
            figurInfo[4].setText(figurTab.indexOf(form) + "");
        });
    }
    public void changeArea(Figuregenskaper form){
        buttons[4].setOnAction(e -> {
            form.makeBigger();
        });
        buttons[5].setOnAction(e ->{
            form.makeSmaller();
        });
    }
    // Generelt oppsett
    private void setup(){
        info = new GridPane();
        edit = new GridPane();
        this.setTop(info);
        this.setCenter(edit);

        setStyle("-fx-background-color: #c4c4c4;");

        info.setPadding(new Insets(8, 8, 8,8));
        edit.setPadding(new Insets(8, 8, 8,8));

        String[] labels = {"Figur: ", "Farge: ", "Omkrets: ", "Areal: ", "Posisjon: " };
        placeInfoPanel(labels);
        placeEditPanel();
    }

    // Info-blokken
    private void placeInfoPanel(String[] labels){
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.setFont(Font.font("Monospace",
                    FontWeight.BOLD, FontPosture.REGULAR, 15));
            info.add(label, 0, i);
            figurInfo[i].setEditable(false);
            figurInfo[i].setMaxWidth(142);
            info.add(figurInfo[i], 1, i);
            info.setHgap(5.5);
            info.setVgap(5.5);
        }
    }

    // Redigering-blokken
    private void placeEditPanel(){
            Label overSkrift = new Label("Rediger Figur: ");
            overSkrift.setFont(Font.font("Monospace",
                    FontWeight.BOLD, FontPosture.REGULAR, 15));
            overSkrift.setPadding(new Insets(8, 0, 15,2));
            edit.add(overSkrift, 0, 0);
            edit.add(buttons[0], 0, 1);
            edit.add(buttons[1], 1, 1);
            edit.add(buttons[2], 0, 2);
            edit.add(buttons[3], 1, 2);
            edit.add(buttons[4], 0, 3);
            edit.add(buttons[5], 1, 3);
            Label linje = new Label("Linje farge: ");
            Label fyll = new Label("Fyll farge: ");
            linje.setFont(Font.font("Monospace",
                FontWeight.BOLD, FontPosture.REGULAR, 13));
            fyll.setFont(Font.font("Monospace",
                FontWeight.BOLD, FontPosture.REGULAR, 13));

            edit.add(linje, 0, 4);
            edit.add(fyll, 0, 5);
            linjeFarge.setMaxWidth(100);
            fyllFarge.setMaxWidth(100);
            edit.add(linjeFarge, 1, 4);
            edit.add(fyllFarge, 1, 5);

            for(int i = 0; i<6; i++) {
                buttons[i].setStyle("-fx-font: 14 arial; -fx-base: #008B8B; -fx-font-size: 12px;" +
                        "-fx-text-fill:white; -fx-font-family: \"Arial Narrow\";" +
                        "    -fx-font-weight: bold;");
                buttons[i].setMinWidth(100);
                buttons[i].setMinHeight(35);
                edit.setVgap(6);
            }
    }
    public Button[] getButtons(){
        return buttons;
    }
    public ColorPicker getLinjeFarge(){
        return linjeFarge;
    }
    public ColorPicker getFyllFarge(){
        return fyllFarge;
    }
}
