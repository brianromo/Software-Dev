/*
 * Author: Brian Banfield
 * Date: 7/3/2022
 * Program computes the sum in parallel using multiple threads.
 */

import java.util.Random;

public class ParallelArraySum {
	static Random rand = new Random();

	public static void main(String[] args) {
		int[] intArr = new int[200000000];
		for(int i = 0; i < intArr.length; i++) {
			int num = rand.nextInt(10) + 1;
			intArr[i] = num;
			
		}
		long startTime = System.currentTimeMillis();		
		long sum = sumArray(intArr);
		long endTime = System.currentTimeMillis();		
		System.out.println("Time 1: " + (endTime - startTime));
		System.out.println(sum);
		startTime = System.currentTimeMillis();
		Thread t1 = new Thread(new SumParallel(0, intArr.length / 2, intArr)); 
		Thread t2 = new Thread(new SumParallel(intArr.length / 2 + 1, intArr.length - 1, intArr));
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		endTime = System.currentTimeMillis();
		System.out.println("Time 2: " + (endTime - startTime));
		System.out.println(sum);    // SumParallel.getSum());
	}
	static long sumArray(int[] intArr) {
		long sum = 0;
		for(int i = 0; i < intArr.length; i++) {
			sum += intArr[i];
			
		}
		return sum;
		
	}
	

}
class SumParallel implements Runnable {
		int startIndex;
		int endIndex;
		int arr[];
		
		static long sum;

		public SumParallel(int startIndex, int endIndex, int[] arr) {
			super();
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.arr = arr;
		//	sum = 0;		
			
		}
		

		@Override
		public void run() {			
			for(int i = startIndex; i < endIndex; i++) {
		//		synchronized(this){
				sum += arr[i];
		//		}
				
			}
			
		}


		public static long getSum() {
			return sum;
		}


//		public static void setSum(int sum) {
//			SumParallel.sum = sum;
//		}
		
		
		
	}
