package controller;

/**
 * Positionen sind Paare von ganzzahligen Koordinaten. Da int als Typ nicht in
 * Typvariablen vorkommen darf, wird intern überall Integer verwendet.
 * 
 * @author nda*/

public class Position {

    private Pair<Integer, Integer> values;
   
    
    /**
     * Konstruiert eine neue Position aus zwei ganzen Zahlen.
     * @param x horizontal
     * @param y vertikal
     */
    
    public Position(int x, int y){
        values = new Pair<Integer, Integer>(new Integer(x), new Integer(y));
    }
    
    /**
     * Setzt die x-Koordinate auf den neuen Wert (destruktiv).
     * @param x
     */
    
    public void setX(int x){
        values.setFirst(new Integer(x));
    }
    
    /**
     * Setzt die y-Koordinate auf den neuen Wert (destruktiv).
     * @param y
     */
    
    public void setY(int y){
        values.setSecond(new Integer(y));
    }
    
    /**
     * Liefert die x-Koordinate.
     * @return
     */
    
    public int getX(){
        return values.getFirst().intValue();
    }
    
    /**
     * Liefert die y-Koordinate.
     * @return
     */
    
    public int getY(){
        return values.getSecond().intValue();
    }
    
    /**
     * Es wird das zugrundeliegende Paar angezeigt.
     */
    
    public String toString(){
        return values.toString();
    }

