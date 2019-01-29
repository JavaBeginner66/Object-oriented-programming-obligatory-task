import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Tekst klasse med passende metoder
 */

public class Tekst extends Text implements Figuregenskaper {

    private String name = "Tekst";
    private Color linjeFarge;
    private Color fyllFarge;
    private int size;

    public Tekst(double x, double y, String navn, int size){
        super(x, y, navn);
        this.size = size;
    }

    public String areal(){
        return 0.0 +"";
    }
    public String omkrets(){
        return 0.0 +"";
    }
    public void makeBigger(){
        if(size <= 200)
        setFont(new Font(size+=5));
    }
    public void makeSmaller(){
        if(size >= 10)
            setFont(new Font(size-=5));
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
