package controller;

/**
 * Repraesentiert Paare mit beliebigen Typen in den verschiedenen Komponenten.
 * 
 * @author nda
 *
 * @param <A>
 * @param <B>
 */

public class Pair<A, B> {

	private A first;
	private B second;
	
	/**
	 * Konstruiert ein neues Paar aus zwei gegebenen Objekten.
	 * @param first erste Komponente
	 * @param second zweite Komponente
	 */
	
	public Pair(A first, B second){
		this.first = first;
		this.second = second;
	}
	
	/**
	 * 
	 * @return liefert die erste Komponente
	 */
	
	public A getFirst(){
		return first;
	}
	
	/**
	 * 
	 * @return liefert die zweite Komponente
	 */
	
	public B getSecond(){
		return second;
	}
	
	/**
	 * Setzt die erste Komponente auf den gegebenen Wert (destruktiv).
	 * @param first
	 */
	
	public void setFirst(A first){
		this.first = first;
	}
	
	/**
	 * Setzt die zweite Komponente auf den gegebenen Wert (destruktiv).
	 * @param second
	 */
	
	public void setSecond(B second){
		this.second = second;
	}
	
	public String toString(){
	    return "(" + first.toString() + ", " + second.toString() + ")";
	}
}
