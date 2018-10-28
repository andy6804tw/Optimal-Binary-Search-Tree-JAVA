public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		String value[] = scn.nextLine().split(" "); // 輸入 search key (k[i])的機率
		int n = value.length, j = 0;
		double A[][] = new double[n + 2][n + 1], min = Double.MAX_VALUE;
		int R[][] = new int[n + 2][n + 1];

		for (int i = 1; i <= n; i++) {
			A[i][i - 1] = 0; // 對角線為0
			A[i][i] = Double.parseDouble(value[i - 1]); // 自己本身被搜尋的機率
			R[i][i] = i;
		}
    A[n+1][n]=0;
		R[n+1][n]=0;

		for (int diagonal = 1; diagonal <= n - 1; diagonal++) { // 對角線1開始
			for (int i = 1; i <= n - diagonal; i++) {
				j = i + diagonal;
				min = Double.MAX_VALUE;
				for (int k = i; k <= j; k++) { // 比較尋找最小搜尋值
					System.out.println(i + " " + j + "  " + (A[i][k - 1] + A[k + 1][j]));
					if ((A[i][k - 1] + A[k + 1][j]) < min) { // 比較出最小的存入陣列中
						min = A[i][k - 1] + A[k + 1][j];
						// tot計算加總
						double tot = 0.0;
						for (int o = i; o <= j; o++)
							tot += A[o][o];
						A[i][j] = (A[i][k - 1] + A[k + 1][j]) + tot;
						R[i][j] = k;
						System.out.println((A[i][k - 1] + A[k + 1][j]) + tot);
					}
				}
				System.out.println();
			}
		}
    // 印出陣列A(最佳平均時間)
		for (int i = 1; i < n + 2; i++) {
			for (int a = 0; a < n + 1; a++) {
				System.out.printf("%-8.3f", A[i][a]);
			}
			System.out.println();
		}
		System.out.println();
    // 印出陣列R(二元樹根節點)
		for (int i = 0; i < n + 2; i++) {
			for (int a = 0; a < n + 1; a++) {
				System.out.printf("%d ", R[i][a]);
			}
			System.out.println();
		}
    // 使用陣列R繪出一棵樹
		drawTree(R, value, n);
	}
