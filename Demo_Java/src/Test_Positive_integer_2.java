//Editor : LiangBao
//Edit Time : 2020/7/10
//Note : ���X��ӥ���Ƥ����������

import java.util.Scanner;

public class Test_Positive_integer_2 {

	public static void main(String[] args) {
		
		int a , b ; 
		Scanner one = new Scanner(System.in);
		System.out.println("�п�J��ӥ���ơA�B�Ĥ@�Ӽƥ�����ĤG�ӼƦr�p�G");
		a = one.nextInt();
		b = one.nextInt();
		// �ˬd��J�O�_���T
		while (a >= b || a < 0 || b < 0) {
			System.out.println("�Э��s��J��ӥ���ơA�B�Ĥ@�Ӽƥ�����ĤG�ӼƦr�p�G");
			a = one.nextInt();
			b = one.nextInt();
		}
		
		for (int i = a ; i <= b; i++) {
			
			System.out.print(i+" ");
		}
	}
}
