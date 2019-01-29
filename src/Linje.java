import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Linje klasse med passende metoder
 */

public class Linje extends Line implements Figuregenskaper {

    private String name = "Linje";
    private Color linjeFarge;
    private Color fyllFarge;

    public Linje(double startX, double startY, double endX, double endY, int size)
    {
        super(startX, startY, endX, endY);
        setStrokeWidth(size);
    }

    public String areal(){
        return 0.0 + "";
    }
    public String omkrets(){
        return 0.0+ "";
    }

    public void makeBigger(){
        if(getStrokeWidth() <= 200)
        setStrokeWidth(getStrokeWidth()+5);
    }
    public void makeSmaller(){
        if(getStrokeWidth() >= 10)
            setStrokeWidth(getStrokeWidth()-5);
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
