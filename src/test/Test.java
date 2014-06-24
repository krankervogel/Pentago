package test;

import controller.Pair;

public class Test {
	
	public static void test(int n) throws Exception{
		if(n != 0)
			throw new Exception("muh");
		System.out.println("hallo");
	}

	public static void main(String[] args) {
	    Pair<Integer, Boolean> pari= new Pair<Integer, Boolean> (3, false);
//		System.out.println(7%3);
//		System.out.println(7/3);
		try {
            System.out.println("Hallo");
			test(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
