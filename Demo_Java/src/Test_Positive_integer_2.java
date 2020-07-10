//Editor : LiangBao
//Edit Time : 2020/7/10
//Note : 取出兩個正整數之間的正整數

import java.util.Scanner;

public class Test_Positive_integer_2 {

	public static void main(String[] args) {
		
		int a , b ; 
		Scanner one = new Scanner(System.in);
		System.out.println("請輸入兩個正整數，且第一個數必須比第二個數字小：");
		a = one.nextInt();
		b = one.nextInt();
		// 檢查輸入是否正確
		while (a >= b || a < 0 || b < 0) {
			System.out.println("請重新輸入兩個正整數，且第一個數必須比第二個數字小：");
			a = one.nextInt();
			b = one.nextInt();
		}
		
		for (int i = a ; i <= b; i++) {
			
			System.out.print(i+" ");
		}
	}
}
