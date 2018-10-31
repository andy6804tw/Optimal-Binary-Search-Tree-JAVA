import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("請輸入每一個鍵(key)和機率(probability)兩者以空白隔開，結束請輸入 0 :");
		ArrayList<Integer> key =new ArrayList<>(); // key 鍵值
		ArrayList<Double> value =new ArrayList<>(); // 被搜尋的機率 probability
		while(true) {
			String str[]=scn.nextLine().split(" ");
			if(str[0].equals("0"))
				break;
			key.add(Integer.parseInt(str[0]));
			value.add(Double.parseDouble(str[1]));
		}
		mergeSort(key, 0, key.size() - 1,value);// (key要被排序資料,最左邊索引值,最右邊索引值,value值)
		int n = key.size(), j = 0;
		double A[][] = new double[n + 2][n + 1], min = Double.MAX_VALUE;
		int R[][] = new int[n + 2][n + 1];

		for (int i = 1; i <= n; i++) {
			A[i][i - 1] = 0; // 對角線為0
			A[i][i] = value.get(i - 1); // 自己本身被搜尋的機率
			R[i][i] = i;
		}
		A[n + 1][n] = 0;
		R[n + 1][n] = 0;

		for (int diagonal = 1; diagonal <= n - 1; diagonal++) { 
			for (int i = 1; i <= n - diagonal; i++) {
				j = i + diagonal;
				min = Double.MAX_VALUE;
				// tot計算加總(pi~pj)
				double tot = 0.0;
				for (int o = i; o <= j; o++)
					tot += A[o][o];
				for (int k = i; k <= j; k++) { // 比較尋找最小搜尋值
					if ((A[i][k - 1] + A[k + 1][j]) < min) { // 左子樹和右子樹的平均搜尋時間
						min = A[i][k - 1] + A[k + 1][j]; // 比較出最小的將存入陣列中
						A[i][j] = (A[i][k - 1] + A[k + 1][j]) + tot;
						R[i][j] = k;
					}
				}
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
		drawTree(R,key, value, n);
	}

	public static void drawTree(int R[][], ArrayList<Integer> key, ArrayList<Double> value, int n) {
		BinarySearchTree tree = new BinarySearchTree();

		tree.root = tree.addNode(1, n, R,key, value);
		System.out.print("\nPreOrder: ");
		tree.preOrderTree(tree.root); // 前序走訪的遞迴方法
		System.out.print("\nInOrder: ");
		tree.inOrderTree(tree.root); // 中序走訪
		System.out.print("\nPostOrder: ");
		tree.postOrderTree(tree.root); // 後序走訪
		System.out.println();
		// tree.print2(tree.root, 0);

		int height = tree.root.getHeight();
		System.out.println("\nPrint Optimal Binary Search Tree:\n");
		tree.root.printTree(height);

	}
	public static void mergeSort(List<Integer> list, int left, int right,List<Double> value) {
		if (left < right) { // 當左邊大於右邊時代表只剩一個元素了
			int mid = (left + right) / 2; // 每次對切，切到只剩一個為止
			mergeSort(list, left, mid,value); // 左邊等份
			mergeSort(list, mid + 1, right,value); // 右邊等份
			Merge(list, left, mid + 1, right,value); // 排序且合併
		}
	}

	public static void Merge(List<Integer> list, int left, int mid, int right, List<Double> value) {
		// 建立一個temp陣列存放排序後的值
		int[] tempKey = new int[right + 1]; 
		double[] tempValue = new double[right + 1]; 
		int left_end = mid - 1; // 左邊最後一個位置
		int index = left; // 位移起始點
		int origin_left = left; // 將最左邊的變數儲存起來(最後搬移元素會用到)

		while ((left <= left_end) && (mid <= right)) { // 左右兩串列比大小依序放入temp陣列中儲存
			if (list.get(left) <= list.get(mid)) {
				tempKey[index] = list.get(left);
				tempValue[index++] = value.get(left++);
			} else {
				tempKey[index] = list.get(mid);
				tempValue[index++] = value.get(mid++);
			}
		}

		if (left <= left_end) { // 若左邊的串列尚未走完將剩餘的數值依序放入temp陣列中
			while (left <= left_end) {
				tempKey[index] = list.get(left);
				tempValue[index++] = value.get(left++);
			}
		} else { // 反之若右邊的串列尚未走完將剩餘的數值依序放入temp陣列中
			while (mid <= right) {
				tempKey[index] = list.get(mid);
				tempValue[index++] = value.get(mid++);
			}
		}
		// 最後將排序好的temp陣列複製到list串列中
		for (int i = origin_left; i <= right; i++) {
			list.set(i, tempKey[i]);
			value.set(i, tempValue[i]);
		}

	}

}
/** 測資
2 .375
3 .125
4 .125
1 .375
0

2 .15
1 .2
3 .35
4 .15
5 .15
0

6 0.29
7 0.09
3 0.2
4 0.05
5 0.06
2 0.14
1 0.15
0
**/
