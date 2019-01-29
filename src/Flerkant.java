import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Polygon klasse med passende metoder
 */

public class Flerkant extends Polygon implements Figuregenskaper {

    private String name = "Polygon";
    private Color linjeFarge;
    private Color fyllFarge;
    private List list = getPoints();


    public Flerkant(double[] verdier){
        super(verdier);
    }

    public String areal(){

        double[] punkt = new double[list.size()];
        for(int j = 0; j < list.size()-1; j++){
            punkt[j] = (Double)list.get(j);
        }

        double svar = 0.0;
        double svar2 = 0.0;
        for(int h = 0; h < punkt.length; h+=2){
            if( h+3 > punkt.length){
                svar += punkt[h] * punkt[1];
            }else
                svar += punkt[h] * punkt[h+3];
        }
        for(int j = 0; j < punkt.length-1; j+=2){
            if(j+2 > punkt.length-1){
                svar2 += punkt[j+1] * punkt[0];
            }else
                svar2 += punkt[j+1] * punkt[j+2];
        }
        return Math.abs((svar - svar2)/2) + "";
    }


    public String omkrets(){

        double[] punkt = new double[list.size()];

        double svar = 0.0;
        for(int j = 0; j < list.size()-1; j++){
            punkt[j] = (Double)list.get(j);
        }
        for(int i = 0; i < punkt.length-1; i+=2){
            if(i+2 < punkt.length-1){
                svar += distance(punkt[i], punkt[i+1], punkt[i+2], punkt[i+3]);
            }else{
                svar += distance(punkt[i], punkt[i+1], punkt[0], punkt[1]);
            }
        }
        return new DecimalFormat("#.00").format(svar);
    }

    public void makeBigger(){}
    public void makeSmaller(){}

    private double distance(double x1, double y1, double x2, double y2){
        double d = Math.hypot(x1-x2, y1-y2);
        return d;
    }


    public String getName(){
        return name;
    }

    public void setLineColor(Color linje){
        this.linjeFarge = linje;

    }
    public void setFillColor(Color fyll){
        this.fyllFarge = fyll;
    }

    public String getFarge(){
        String out = linjeFarge + "/" + fyllFarge;
        return out;
    }


    public String toString(){
        return name;
    }
}
