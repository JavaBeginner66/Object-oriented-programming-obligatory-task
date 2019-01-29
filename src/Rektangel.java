import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Rektangel klasse med passende metoder
 */

public class Rektangel extends Rectangle implements Figuregenskaper {

    private String name = "Rektangel";
    private Color linjeFarge;
    private Color fyllFarge;

    public Rektangel(double x, double y, double width, double height){
        super(x, y, width, height);
    }

    public String omkrets(){
        return getHeight() + getHeight() + getWidth() + getWidth() + "";
    }

    public String areal(){
        return getWidth() * getHeight() + "";
    }
    public void makeBigger(){
        if(getWidth() <= 500 && getHeight() <= 500) {
            setWidth(getWidth() + 10);
            setHeight(getHeight() + 10);
        }
    }
    public void makeSmaller(){
        if(getWidth() >= 10 && getHeight() >= 10) {
            setWidth(getWidth() - 10);
            setHeight(getHeight() - 10);
        }
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
