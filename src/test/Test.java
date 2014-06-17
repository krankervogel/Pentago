package test;

public class Test {
	
	public static void test(int n) throws Exception{
		if(n != 0)
			throw new Exception("muh");
		System.out.println("hallo");
	}

	public static void main(String[] args) {
//		System.out.println(7%3);
//		System.out.println(7/3);
		try {
			test(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
