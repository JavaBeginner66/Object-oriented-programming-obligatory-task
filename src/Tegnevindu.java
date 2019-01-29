import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import java.util.ArrayList;


public class Tegnevindu extends Pane {

    private Infovindu edit;
    private Figurvindu figur;
    private Layoutclass hoved;
    private Systemvindu systemVindu;

    private ArrayList<Shape> figurTabell = new ArrayList<Shape>();

    private int shape;

    public Tegnevindu(Layoutclass hoved, Figurvindu figur){
        this.figur = figur;
        this.hoved = hoved;
        tegnFigur();
    }

    /*
        Metoden inneholder all handler-funksjonalitet for alle figurene,
        både lage figur, trykke på figur, dra figur og diverse andre ting.
    */
    private void tegnFigur(){

        final ArrayList<Double> strekPunkt = new ArrayList<>();
        final ArrayList<Double> polygonPunkt = new ArrayList<>();
        final ObjectProperty<Point2D> musPosisjon = new SimpleObjectProperty<>();

        this.setOnMouseClicked(e -> {

            // Tekst
            if(figur.getButtons()[0].isSelected()){
                if (e.getButton() == MouseButton.PRIMARY) {
                    systemVindu = new Systemvindu();
                    hoved.getMainPane().setBottom(systemVindu);
                    systemVindu.getBoksTekst().requestFocus();
                    systemVindu.setOnKeyPressed(m -> {
                        if(m.getCode() == KeyCode.ENTER) {
                            if (!systemVindu.getBoksTekst().getText().equals("")) {
                                textStyle(e);
                                systemVindu.getChildren().clear();
                            } else {
                                systemVindu.getChildren().clear();
                            }
                        }
                    });
                }
            }

            // Linje
            if(figur.getButtons()[1].isSelected()) {
                    double[] punkt = new double[4];
                    if (e.getButton() == MouseButton.PRIMARY) {
                        strekPunkt.add(e.getX());
                        strekPunkt.add(e.getY());
                        if(strekPunkt.size() == 4) {
                            for(int i = 0; i < punkt.length; i++){
                                punkt[i] = strekPunkt.get(i);
                            }
                            lineStyle(punkt[0], punkt[1], punkt[2], punkt[3]);
                            strekPunkt.clear();
                        }
                    }
                }

            // Polygon
            if(figur.getButtons()[2].isSelected()) {
                if(e.getButton() == MouseButton.PRIMARY) {
                    polygonPunkt.add(e.getX());
                    polygonPunkt.add(e.getY());
                    }
                else if (e.getButton() == MouseButton.SECONDARY) {
                    double[] polygonXY = new double[polygonPunkt.size()];
                    for (int i = 0; i < polygonPunkt.size(); i++) {
                        polygonXY[i] = polygonPunkt.get(i);
                    }
                    polygonStyle(polygonXY);
                    polygonPunkt.clear();
                }
            }

            // Sirkel
            if(figur.getButtons()[3].isSelected()) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    sirkelStyle(e);
                }
            }

            // Firkant
            if(figur.getButtons()[4].isSelected()) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    rektangelStyle(e);
                }
            }

            musPosisjon.set(new Point2D(e.getSceneX(), e.getSceneY()));

        }); // setOnMouseClicked

            // Velg
            this.setOnMouseEntered(p -> {
            if(figur.getButtons()[5].isSelected()) {
                    for (int i = figurTabell.size() - 1; i >= 0 ; i--) {
                        Shape figur = figurTabell.get(i);
                        // Figur info
                        figur.setOnMouseClicked(c -> {
                            edit = new Infovindu(figur, figurTabell, this);
                            hoved.getMainPane().setRight(edit);
                            if(edit != null) {
                                edit.getLinjeFarge().setOnAction(e -> {
                                    setLineColorAfterCreated(figur);
                                });
                                edit.getFyllFarge().setOnAction(e -> {
                                    setFillColorAfterCreated(figur);
                                });
                            }
                        });
                        // Flytt figur
                        figur.setOnMouseDragged(d -> {
                            if(figur instanceof Rektangel){
                                Rektangel rekt = (Rektangel)figur;
                                rekt.setX(d.getX()-rekt.getHeight()/2);
                                rekt.setY(d.getY()-rekt.getWidth()/2);
                            }
                            else if(figur instanceof Sirkel){
                                Sirkel sirkel = (Sirkel)figur;
                                sirkel.setCenterX(d.getX());
                                sirkel.setCenterY(d.getY());
                            }
                            else if(figur instanceof Linje){
                                Linje linje = (Linje)figur;
                                double deltaX = linje.getStartX();
                                double deltaY = linje.getStartY();
                                linje.setStartX(d.getX());
                                linje.setStartY(d.getY());
                                deltaX = linje.getStartX() - deltaX;
                                deltaY = linje.getStartY() - deltaY;
                                linje.setEndX(linje.getEndX() + deltaX);
                                linje.setEndY(linje.getEndY() + deltaY);

                            }
                            else if(figur instanceof Polygon){
                                Polygon poly = (Polygon)figur;
                                double deltaX = d.getSceneX() - musPosisjon.get().getX();
                                double deltaY = d.getSceneY() - musPosisjon.get().getY();
                                poly.setLayoutX(poly.getLayoutX()+deltaX);
                                poly.setLayoutY(poly.getLayoutY()+deltaY);
                                musPosisjon.set(new Point2D(d.getSceneX(), d.getSceneY()));



                            }
                            else if(figur instanceof Tekst){
                                Tekst tekst = (Tekst)figur;
                                tekst.setX(d.getX());
                                tekst.setY(d.getY());
                            }
                        });

                        // Setter filter på alle figurene så ''this'' bare
                        // teller for der det ikke er figurer
                        this.addEventFilter(MouseEvent.MOUSE_CLICKED, m -> {
                            if (!iTegnePanel(m.getPickResult().getIntersectedNode(), figur)) {
                                if(edit != null) {
                                    edit.getChildren().clear();
                                }
                            }
                        });
                    }
                }
            });


    } // method end

    // Metoden sjekker om en figur og tegnepanelet er overlappet.
    private static boolean iTegnePanel(Node tegnepanel, Node figur) {
        if (figur == null) {
            return true;
        }
        while (tegnepanel != null) {
            if (tegnepanel == figur) {
                return true;
            }
            tegnepanel = tegnepanel.getParent();
        }
        return false;
    }
/*
     Alle metodene under er lagd for å sette legge figurene til panelet, og sette default style.
     Det ble dobbelt opp her, siden det er to par med fargeknapper.
     Kunne ikke bruke e.getSource siden jeg ikke bruker setOnAction, og bare henter fargen.
*/
    private void textStyle(MouseEvent e){
        Tekst t = new Tekst(e.getX(), e.getY(), systemVindu.getBoksTekst().getText(), 10);
        setLineColor(t);
        setFillColor(t);
        figurTabell.add(t);
        this.getChildren().add(figurTabell.get(figurTabell.size() - 1));
    }
    private void lineStyle(double startX, double startY, double endX, double endY){
        Linje linje = new Linje(startX, startY, endX, endY, 10);
        setLineColor(linje);
        setFillColor(linje);
        figurTabell.add(linje);
        this.getChildren().add(figurTabell.get(figurTabell.size() - 1));
    }

    private void polygonStyle(double[] verdier){
        Flerkant poly = new Flerkant(verdier);
        setLineColor(poly);
        setFillColor(poly);
        figurTabell.add(poly);
        this.getChildren().add(figurTabell.get(figurTabell.size() - 1));
    }

    private void sirkelStyle(MouseEvent m){
        Sirkel sirkel = new Sirkel(m.getX(), m.getY(), 50);
        setLineColor(sirkel);
        setFillColor(sirkel);
        figurTabell.add(sirkel);
        this.getChildren().add(figurTabell.get(figurTabell.size() - 1));
    }

    private void rektangelStyle(MouseEvent m){
        Rektangel rekt = new Rektangel(m.getX()-50, m.getY()-50, 100, 100 );
        setLineColor(rekt);
        setFillColor(rekt);
        figurTabell.add(rekt);
        this.getChildren().add(figurTabell.get(figurTabell.size() - 1));
    }

    private void setLineColor(Shape form) {
        form.setStroke(figur.getLinjeF().getValue());
        Figuregenskaper nyForm = (Figuregenskaper) form;
        nyForm.setLineColor(figur.getLinjeF().getValue());
    }

    private void setFillColor(Shape form){
        form.setFill(figur.getFyllF().getValue());
        Figuregenskaper nyForm = (Figuregenskaper) form;
        nyForm.setFillColor(figur.getFyllF().getValue());
    }
    private void setLineColorAfterCreated(Shape form){
        form.setStroke(edit.getLinjeFarge().getValue());
        Figuregenskaper nyForm = (Figuregenskaper) form;
        nyForm.setLineColor(edit.getLinjeFarge().getValue());
    }
    private void setFillColorAfterCreated(Shape form){
        form.setFill(edit.getFyllFarge().getValue());
        Figuregenskaper nyForm = (Figuregenskaper) form;
        nyForm.setLineColor(edit.getFyllFarge().getValue());
    }

}// class end

