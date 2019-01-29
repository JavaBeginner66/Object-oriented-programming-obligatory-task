import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.text.DecimalFormat;

/**
 * Sirkel klasse med passende metoder
 */

public class Sirkel extends Circle implements Figuregenskaper {

    private String name = "Sirkel";
    private Color linjeFarge;
    private Color fyllFarge;

    public Sirkel(double x, double y, double radius){
        super(x, y, radius);
    }

    public String omkrets(){
        double o = (getRadius() + getRadius()) * Math.PI;
        return new DecimalFormat("#.00").format(o);
    }

    public String areal(){
        double a = Math.PI * getRadius() * getRadius();
        return new DecimalFormat("#.00").format(a);
    }
    public void makeBigger(){
        if(getRadius() <= 300)
        setRadius(getRadius()+10);
    }
    public void makeSmaller(){
        if(getRadius() >= 10)
        setRadius(getRadius()-10);
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
