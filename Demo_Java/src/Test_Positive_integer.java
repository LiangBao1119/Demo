//Editor : LiangBao
//Edit Time : 2020/7/10
//Note : 取出兩個浮點數之間的正整數

import java.util.Scanner;

public class Test_Positive_integer {

	public static void main(String[] args) {
		
		double a , b ; 
		Scanner one = new Scanner(System.in);
		System.out.println("請輸入兩個浮點數，且第一個數必須比第二個數字小：");
		a = one.nextDouble();
		b = one.nextDouble();
		// 檢查輸入是否正確
		while (a >= b) {
			System.out.println("請重新輸入兩個浮點數，且第一個數必須比第二個數字小：");
			a = one.nextDouble();
			b = one.nextDouble();
		}
		
		//第一種方法
		System.out.print("第一種方法：");
		for (double i = a ; i <= b; i++) {
			
			if (i > a) {
				System.out.print((int)i+" ");
			} else if (i % 1 == 0 ) {
				System.out.print((int)i+" ");
			}
			if (a % 1 >= b % 1) {
				if (i != b && i + 1 > b) {
					System.out.print((int)i + 1+" ");
				}
			}
		}
		
		System.out.println();
		
		//第二種方法
		System.out.print("第二種方法：");
		for (double i = a ; i <= b; i++) {
			
			int c = (int)Math.ceil(i);
			if (c <= b) {
				System.out.print(c+" ");
			}
		}
	}
}
