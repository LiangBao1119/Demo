//Editor : LiangBao
//Edit Time : 2020/7/10
//Note : ���X��ӯB�I�Ƥ����������

import java.util.Scanner;

public class Test_Positive_integer {

	public static void main(String[] args) {
		
		double a , b ; 
		Scanner one = new Scanner(System.in);
		System.out.println("�п�J��ӯB�I�ơA�B�Ĥ@�Ӽƥ�����ĤG�ӼƦr�p�G");
		a = one.nextDouble();
		b = one.nextDouble();
		// �ˬd��J�O�_���T
		while (a >= b) {
			System.out.println("�Э��s��J��ӯB�I�ơA�B�Ĥ@�Ӽƥ�����ĤG�ӼƦr�p�G");
			a = one.nextDouble();
			b = one.nextDouble();
		}
		
		//�Ĥ@�ؤ�k
		System.out.print("�Ĥ@�ؤ�k�G");
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
		
		//�ĤG�ؤ�k
		System.out.print("�ĤG�ؤ�k�G");
		for (double i = a ; i <= b; i++) {
			
			int c = (int)Math.ceil(i);
			if (c <= b) {
				System.out.print(c+" ");
			}
		}
	}
}
