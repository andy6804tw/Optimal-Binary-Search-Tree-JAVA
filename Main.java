import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		String str[] = scn.nextLine().split(" ");
		int n = str.length, j = 0;
		double arr[][] = new double[n+2][n+1], min = Double.MAX_VALUE;
//		for(int i=0;i<n+2;i++)
//			for(int k=0;k<n+1;k++)
				//arr[i][k]=-1;
		for(int i=1;i<=n;i++) {
			arr[i][i - 1] = 0;
			arr[i][i]=Double.parseDouble(str[i-1]);
		}
		//	0.375 0.375 0.125 0.125

		for (int diagonal = 1; diagonal <= n - 1; diagonal++) {
			for (int i = 1; i <= n - diagonal; i++) {
				j = i + diagonal;
				min = Double.MAX_VALUE;
				for (int m = i; m <= j; m++) {
					System.out.println(i + " " + j + "  " + (arr[i][m - 1] + arr[m + 1][j]));
					if ((arr[i][m - 1] + arr[m + 1][j]) < min) {
						min = arr[i][m - 1] + arr[m + 1][j];
						double tot = 0.0;
						for (int o = i; o <= j; o++)
							tot += arr[o][o];
						arr[i][j] = (arr[i][m - 1] + arr[m + 1][j]) + tot;
						System.out.println((arr[i][m - 1] + arr[m + 1][j]) + tot);
					}
				}
				System.out.println();
			}
		}

		for (int i = 0; i < 6; i++) {
			for (int a = 0; a < 5; a++) {
				System.out.printf("%-8.3f",arr[i][a]);
			}
			System.out.println();
		}

	}

  public static void optsearchtree(int n, float p[], float minAvg) {

  }

}