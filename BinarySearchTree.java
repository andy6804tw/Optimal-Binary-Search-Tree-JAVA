import java.util.ArrayList;
import java.util.StringTokenizer;

public class BinarySearchTree {

	Node root;

	public Node addNode(int i, int j, int R[][], ArrayList<Integer> key, ArrayList<Double> value) {

		int k = R[i][j];
		if (k == 0)
			return null;
		else {
			Node newNode = new Node(key.get(k - 1), value.get(k - 1));
			newNode.leftChild = addNode(i, k - 1, R, key, value); // 新增左子樹節點
			newNode.rightChild = addNode(k + 1, j, R, key, value); // 新增右子數節點
			return newNode;
		}

	}

	/**
	 * 前序走訪 2 / \ 1 3 前序 = 2 -> 1 -> 3
	 **/
	public void preOrderTree(Node focusNode) {
		if (focusNode != null) {
			System.out.print(focusNode.key + " "); // 先印出節點內容
			preOrderTree(focusNode.leftChild); // 再走訪左子樹
			preOrderTree(focusNode.rightChild); // 再走訪右子樹
		}
	}

	/**
	 * 中序走訪 2 / \ 1 3 中序 = 1 -> 2 -> 3
	 **/
	public void inOrderTree(Node focusNode) {
		if (focusNode != null) {
			inOrderTree(focusNode.leftChild); // 先走訪左子樹
			System.out.print(focusNode.key + " "); // 印出節點內容
			inOrderTree(focusNode.rightChild); // 再走訪右子樹
		}
	}

	/**
	 * 後序走訪 2 / \ 1 3 後序 = 1 -> 3 -> 2
	 **/
	public void postOrderTree(Node focusNode) {
		if (focusNode != null) {
			postOrderTree(focusNode.leftChild); // 先走訪左子樹
			postOrderTree(focusNode.rightChild); // 再走訪右子樹
			System.out.print(focusNode.key + " "); // 再印出節點內容
		}
	}

	public void print(Node focusNode, int space) {

		if (focusNode == null)
			return;

		// Increase distance between levels
		space += 10;
		// Process right child first
		print(focusNode.rightChild, space);
		// Print current node after space
		// count
		System.out.println();
		for (int i = 10; i < space; i++)
			System.out.printf(" ");
		System.out.print(focusNode.key);
		// Process left child
		print(focusNode.leftChild, space);

	}

	public void print2(Node focusNode, int space) {

		if (focusNode != null) {
			if (focusNode.rightChild != null)
				print2(focusNode.rightChild, space + 4);
			if (space > 0) {
				for (int i = 0; i < space; i++)
					System.out.printf(" ");
			}
			if (focusNode.rightChild != null) {
				System.out.println(" /");
				for (int i = 0; i < space; i++)
					System.out.printf(" ");
			}
			System.out.printf("%d\n ", focusNode.key);

			if (focusNode.leftChild != null) {
				for (int i = 0; i < space; i++)
					System.out.printf(" ");
				System.out.println(" \\");
				print2(focusNode.leftChild, space + 4);
			}
		}

	}

	public static void main(String[] args) {

	}
}

class Node {
	int key; // 節點資料
	double value; // 節點名稱
	Node leftChild; // 左子樹
	Node rightChild; // 右子樹
	// 建構子

	Node(int key, double value) {
		this.key = key;
		this.value = value;
	}

	public int getHeight() {
		return getHeight(this);
	}

	// 取得樹高
	private int getHeight(Node root) {
		if (root == null)
			return 0;
		return Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;
	}

	public void printTree(int height) {
		System.out.println(printTree(this, 1, height));
	}

	private StringBuilder printTree(Node root, int currentHeight, int totalHeight) {
		StringBuilder sb = new StringBuilder();
		int spaces = getSpaceCount(totalHeight - currentHeight + 1);
		// 處理最底部沒有樹葉時給予空白
		if (root == null) {
			String row = String.format("%" + (2 * spaces + 1) + "s%n", "");
			String block = new String(new char[spaces + 1]).replace("\0", row);
			return new StringBuilder(block);
		}
		// 當到最後一層時直接印出key
		if (currentHeight == totalHeight)
			return new StringBuilder(root.key + "");
		int slashes = getSlashCount(totalHeight - currentHeight + 1);
		sb.append(String.format("%" + (spaces + 1) + "s%" + spaces + "s", root.key + "", "") + "\n");
		// 判斷是否有左右子樹，若有則需印出斜線
		char leftSlash = root.leftChild == null ? ' ' : '/';
		char rightSlash = root.rightChild == null ? ' ' : '\\';
		int spaceInBetween = 1;
		for (int i = 0, space = spaces - 1; i < slashes; i++, space--, spaceInBetween += 2) {
			for (int j = 0; j < space; j++)
				sb.append(" ");
			sb.append(leftSlash);
			for (int j = 0; j < spaceInBetween; j++)
				sb.append(" ");
			sb.append(rightSlash + "");
			for (int j = 0; j < space; j++)
				sb.append(" ");
			sb.append("\n");
		}

		// 分別取得目前的左子樹及右子樹
		StringBuilder leftTree = printTree(root.leftChild, currentHeight + 1, totalHeight);
		StringBuilder rightTree = printTree(root.rightChild, currentHeight + 1, totalHeight);
		// 字串切割分別將左右子樹分別地依據換行切開
		StringTokenizer leftStringToken = new StringTokenizer(leftTree.toString(), "\r\n");
		StringTokenizer rightStringToken = new StringTokenizer(rightTree.toString(), "\r\n");
		// 進行左右子數每一行的合併
		while (leftStringToken.hasMoreTokens()) {
			if (currentHeight == totalHeight - 1) {
				sb.append(String.format("%-2s %2s", leftStringToken.nextToken(), rightStringToken.nextToken()));
				sb.append("\n");
				spaceInBetween -= 2;
			} else {
				sb.append(leftStringToken.nextToken());
				sb.append(" ");
				sb.append(rightStringToken.nextToken() + "\n");
			}
		}
		return sb;
	}

	private int getSlashCount(int height) {
		if (height <= 3)
			return height - 1;
		return (int) (3 * Math.pow(2, height - 3) - 1);
	}

	private int getSpaceCount(int height) {
		return (int) (3 * Math.pow(2, height - 2) - 1);
	}

}
