import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Main {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		String str[] = scn.nextLine().split(" ");
		int n = str.length, j = 0;
		double A[][] = new double[n+2][n+1], min = Double.MAX_VALUE,R[][]= new double[n+2][n+1];

		for(int i=1;i<=n;i++) {
			A[i][i - 1] = 0; // 對角線為0
			A[i][i]=Double.parseDouble(str[i-1]); // 自己本身的權重
			R[i][i]=i;
		}
		//	0.375 0.375 0.125 0.125

		for (int diagonal = 1; diagonal <= n - 1; diagonal++) {
			for (int i = 1; i <= n - diagonal; i++) {
				j = i + diagonal;
				min = Double.MAX_VALUE;
				for (int k = i; k <= j; k++) { //比較尋找最小權重值
					System.out.println(i + " " + j + "  " + (A[i][k - 1] + A[k + 1][j]));
					if ((A[i][k - 1] + A[k + 1][j]) < min) { // 比較出最小的存入陣列中
						min = A[i][k - 1] + A[k + 1][j];
						// tot計算加總
						double tot = 0.0;
						for (int o = i; o <= j; o++)
							tot += A[o][o];
						A[i][j] = (A[i][k - 1] + A[k + 1][j]) + tot;
						R[i][j]=k;
						System.out.println((A[i][k - 1] + A[k + 1][j]) + tot);
					}
				}
				System.out.println();
			}
		}

		for (int i = 1; i < n+2; i++) {
			for (int a = 0; a < n+1; a++) {
				System.out.printf("%-8.3f",A[i][a]);
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < n+2; i++) {
			for (int a = 0; a < n+1; a++) {
				System.out.printf("%-3.0f",R[i][a]);
			}
			System.out.println();
		}
		
		call(R,n);

	}
	
	public static void call(double R[][], int n) {

		Set<Integer> set = new LinkedHashSet<Integer>();
		for (int i = 1; i < n + 1; i++) {
			for (int j = n; j >= i; j--) {
				set.add((int) R[i][j]);
			}
		}
		BinaryTree theTree = new BinaryTree();
		for(Integer element : set) {
			theTree.addNode(element,"");
	    }
		System.out.print("PreOrder: ");
		theTree.preOrderTree(theTree.root); // 前序走訪的遞迴方法
		System.out.print("\nInOrder: ");
		theTree.inOrderTree(theTree.root); // 中序走訪
		System.out.print("\nPostOrder: ");
		theTree.postOrderTree(theTree.root); // 後序走訪
		System.out.println();
		theTree.print2(theTree.root ,0);
		
		int height = theTree.root.getHeight();
		System.out.println(height);
		theTree.root.prettyPrint(height);

	}

}
