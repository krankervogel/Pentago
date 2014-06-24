package controller;

/**@author nda*/

public class Position {

    private Pair<Integer, Integer> values;
    
    public Position(int x, int y){
        values = new Pair<Integer, Integer>(new Integer(x), new Integer(y));
    }
    
    public void setX(int x){
        values.setFirst(new Integer(x));
    }
    
    public void setY(int y){
        values.setSecond(new Integer(y));
    }
    
    public int getX(){
        return values.getFirst().intValue();
    }
    
    public int getY(){
        return values.getSecond().intValue();
    }
    
    public String toString(){
        return values.toString();
    }
}
