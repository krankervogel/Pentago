package controller;

/**@author nda*/

public interface PositionGenerator {

    /**
     * Diese Methode erzeugt aus einem gegebenen Index (entlang der Zeile, Spale oder Diagonale) eine Position.
     * @param i
     * @return
     */
    
    public Position makePosition(int i);
}
