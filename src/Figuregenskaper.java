import javafx.scene.paint.Color;

/**
 * Brukergrensesnittet blir brukt i hovedsak
 * for å skape Polymorphism, med at
 * alle figerene extender egne figur-klasser
 * og ikke har en superklasse til felles. (Utenom Shape)
 */

public interface Figuregenskaper {

    public String areal();
    public String omkrets();
    public String getName();
    public void setLineColor(Color linje);
    public void setFillColor(Color fyll);
    public String getFarge();
    public void makeBigger();
    public void makeSmaller();

}
