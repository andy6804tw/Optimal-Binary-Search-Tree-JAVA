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
	 * 前序走訪
	 *   2
	 *  / \
	 * 1   3 
	 * 前序 = 2 -> 1 -> 3
	 **/
	public void preOrderTree(Node focusNode) {
		if (focusNode != null) {
			System.out.print(focusNode.key + " "); // 先印出節點內容
			preOrderTree(focusNode.leftChild); // 再走訪左子樹
			preOrderTree(focusNode.rightChild); // 再走訪右子樹
		}
	}

	/**
	 * 中序走訪 
	 *	2
	 * / \
	 *1   3 
	 *中序 = 1 -> 2 -> 3
	 **/
	public void inOrderTree(Node focusNode) {
		if (focusNode != null) {
			inOrderTree(focusNode.leftChild); // 先走訪左子樹
			System.out.print(focusNode.key + " "); // 印出節點內容
			inOrderTree(focusNode.rightChild); // 再走訪右子樹
		}
	}

	/**
	 * 後序走訪 
	 *  2
	 * / \ 
	 *1   3
	 *後序 = 1 -> 3 -> 2
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

	public String toString() {
		return name + " has the key " + key;
	}

}

// https://stackoverflow.com/questions/13484943/print-a-binary-tree-in-a-pretty-way
