package oldTest;

public class Input {
	
	static class test{
		@SuppressWarnings("unused")
		private static int a = getNumber();
		static {
			System.out.println("this is static block!");
		}
		static public int getNumber(){
			System.out.println("getNumber function!");
			return 1;
		}
	}
	
	public static void main(String args[]) throws InstantiationException, IllegalAccessException{
		int X=3;
		int number=0;
		int count=1;
		int sum=X*(X+1)/2;
		for (int i=1; i<=sum; i++){
			System.out.print(i+" ");
			if(i==number+count){
				number=i;
				count++;
				System.out.println("");
			}
		}
//		try {
//			@SuppressWarnings("deprecation")
//			String s = 
//			Object c = Class.forName("Input").newInstance();
//			System.out.println(c);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
