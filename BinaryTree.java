import java.util.Scanner;

public class BinaryTree {

	Node root;
	int COUNT = 10;

	public void addNode(int key, String name) {

		Node newNode = new Node(key, name);
		if (root == null) { // 建立根節點
			root = newNode;
		} else { // 建立子數
			Node focusNode = root;
			Node parent;
			while (true) {
				parent = focusNode;
				if (key < focusNode.key) {
					focusNode = focusNode.leftChild; // 小於放左邊
					if (focusNode == null) {
						parent.leftChild = newNode; // 判斷左子樹是否有值，若無則新增Node
						return;
					}
				} else {
					focusNode = focusNode.rightChild; // 大於放右邊
					if (focusNode == null) {
						parent.rightChild = newNode; // 判斷右子樹是否有值，若無則新增Node
						return;
					}
				}
			}
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
		space += COUNT;
		// Process right child first
		print(focusNode.rightChild, space);
		// Print current node after space
		// count
		System.out.println();
		for (int i = COUNT; i < space; i++)
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

	public void print3(Node focusNode, int space) {

		if (focusNode != null) {
			if (focusNode.leftChild != null)
				print3(focusNode.leftChild, space + 4);
			if (focusNode.rightChild != null)
				print3(focusNode.rightChild, space + 4);
			if (space > 0) {
				for (int i = 0; i < space; i++)
					System.out.printf(" ");
			}
			System.out.printf("%d\n ", focusNode.key);
		}

	}

	public static void main(String[] args) {
		BinaryTree theTree = new BinaryTree();

		theTree.addNode(50, "Boss");
		theTree.addNode(25, "Vice President");
		theTree.addNode(15, "Office Manager");
		theTree.addNode(30, "Office Manager");
		theTree.addNode(75, "Secretary");
		theTree.addNode(85, "Sales Manager");
		// theTree.addNode(6, "Salesman 1");
		// theTree.addNode(7, "Salesman 1");
		// theTree.addNode(9, "Salesman 1");
		// theTree.addNode(9, "Salesman 1");

		System.out.print("PreOrder: ");
		theTree.preOrderTree(theTree.root); // 前序走訪的遞迴方法
		System.out.print("\nInOrder: ");
		theTree.inOrderTree(theTree.root); // 中序走訪
		System.out.print("\nPostOrder: ");
		theTree.postOrderTree(theTree.root); // 後序走訪
		System.out.println();
		theTree.print2(theTree.root, 0);

		int height = theTree.root.getHeight();
		System.out.println(height);
		theTree.root.prettyPrint(height);

	}
}

class Node {

	int key; // 節點資料
	String name; // 節點名稱
	Node leftChild; // 左子樹
	Node rightChild; // 右子樹
	// 建構子

	Node(int key, String name) {
		this.key = key;
		this.name = name;
	}

	public int getHeight() {
		return getHeight(this);
	}

	private int getHeight(Node root) {
		if (root == null)
			return 0;
		return Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;
	}

	public void prettyPrint(int height) {
		System.out.println(prettyPrint(this, 1, height));
	}

	private StringBuilder prettyPrint(Node root, int currentHeight, int totalHeight) {
		StringBuilder sb = new StringBuilder();
		int spaces = getSpaceCount(totalHeight - currentHeight + 1);
		if (root == null) {
			// create a 'spatial' block and return it
			String row = String.format("%" + (2 * spaces + 1) + "s%n", "");
			// now repeat this row space+1 times
			String block = new String(new char[spaces + 1]).replace("\0", row);
			return new StringBuilder(block);
		}
		if (currentHeight == totalHeight)
			return new StringBuilder(root.key + "");
		int slashes = getSlashCount(totalHeight - currentHeight + 1);
		sb.append(String.format("%" + (spaces + 1) + "s%" + spaces + "s", root.key + "", ""));
		sb.append("\n");
		// now print / and \
		// but make sure that left and right exists
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
		// sb.append("\n");

		// now get string representations of left and right subtrees
		StringBuilder leftTree = prettyPrint(root.leftChild, currentHeight + 1, totalHeight);
		StringBuilder rightTree = prettyPrint(root.rightChild, currentHeight + 1, totalHeight);
		// now line by line print the trees side by side
		Scanner leftScanner = new Scanner(leftTree.toString());
		Scanner rightScanner = new Scanner(rightTree.toString());
		// spaceInBetween+=1;
		while (leftScanner.hasNextLine()) {
			if (currentHeight == totalHeight - 1) {
				sb.append(String.format("%-2s %2s", leftScanner.nextLine(), rightScanner.nextLine()));
				sb.append("\n");
				spaceInBetween -= 2;
			} else {
				sb.append(leftScanner.nextLine());
				sb.append(" ");
				sb.append(rightScanner.nextLine() + "\n");
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

	public String toString() {
		return name + " has the key " + key;
	}

}

// http://www.newthinktank.com/2013/03/binary-tree-in-java/
// https://stackoverflow.com/questions/13484943/print-a-binary-tree-in-a-pretty-way
// https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
