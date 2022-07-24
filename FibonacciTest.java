
public class FibonacciTest {

	public static void main(String[] args) {
		int fi = fibonacciIter(5);
		System.out.println(fi);
		int fr = fibonacciRecur(5);
		System.out.println(fr);

	}
	
	//Fibonacci Iterative
	
	public static int fibonacciIter(int n){
		   int v1=0,v2=1,v3=0;
		       for(int i=2 ;i <= n;i++){
		           v3 = v1 + v2;
		           v1 = v2;
		           v2 = v3;
		       }
		       return v3;
		   }
	//Recursive
	public static int fibonacciRecur(int n){
	       if(n == 0) return 0;
	       if(n == 1) return 1;
	       return fibonacciRecur(n-1) + fibonacciRecur(n-2);
	   }
}
