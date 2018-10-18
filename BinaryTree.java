
public class BinaryTree {

	Node root;
	int COUNT=10;
	
	public void addNode(int key, String name) {
		
		Node newNode = new Node(key, name);
		if(root == null) {
			root =newNode;
		}else {
			Node focusNode =root;
			Node parent;
			while(true) {
				parent = focusNode;
				if(key<focusNode.key) {
					focusNode=focusNode.leftChild; //小於放左邊
					if(focusNode ==null) {
						parent.leftChild= newNode; // 判斷左子樹是否有值，若無則新增Node 
						return;
					}
				}else {
					focusNode=focusNode.rightChild; //大於放右邊
					if(focusNode ==null) {
						parent.rightChild= newNode; // 判斷右子樹是否有值，若無則新增Node 
						return;
					}
				}
			}
			
		}
		
	}
	
	public void inOrderTraverseTree(Node focusNode) {
		
		if(focusNode != null) {
			inOrderTraverseTree(focusNode.leftChild);
			System.out.println(focusNode);
			// Traverse the right node
			inOrderTraverseTree(focusNode.rightChild);

		}
	}
	
	public void print(Node focusNode ,int space) {
		
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
	
	public void print2(Node focusNode ,int space) {
		
	    if (focusNode != null) {
	    	if(focusNode.rightChild!=null)
	    		print2(focusNode.rightChild,space+4);
	    	if(space>0) {
		    	for (int i = 0; i < space; i++) 
			    	System.out.printf(" "); 
		    }
		    if(focusNode.rightChild!=null) {
		    	System.out.println(" /");
		    	for (int i = 0; i < space; i++) 
			    	System.out.printf(" "); 
		    	}
		    	System.out.printf("%d\n ",focusNode.key);
		    
		    if(focusNode.leftChild!=null) {
		    	for (int i = 0; i < space; i++) 
			    	System.out.printf(" "); 
		    	System.out.println(" \\");
		    	print2(focusNode.leftChild,space+4);
		    }
	    }
	    
}
	
	public void print3(Node focusNode, int space) {

		if (focusNode != null){
			if(focusNode.leftChild!=null)
				print3(focusNode.leftChild, space+4);
			if(focusNode.rightChild!=null)
				print3(focusNode.rightChild, space+4);
			if(space>0) {
		    	for (int i = 0; i < space; i++) 
			    	System.out.printf(" "); 
		    }
			System.out.printf("%d\n ",focusNode.key);
		}


	}

	public static void main(String[] args) {
		BinaryTree theTree = new BinaryTree();

		theTree.addNode(4, "Boss");
		theTree.addNode(3, "Vice President");
		theTree.addNode(1, "Office Manager");
		theTree.addNode(2, "Secretary");
		theTree.addNode(5, "Sales Manager");
		theTree.addNode(6, "Salesman 1");
		theTree.addNode(7, "Salesman 1");
//		theTree.addNode(8, "Salesman 1");
//		theTree.addNode(6, "Salesman 1");
//		theTree.addNode(4, "Salesman 1");
		
		//theTree.inOrderTraverseTree(theTree.root);
		theTree.print3(theTree.root ,0);
	}
}

class Node {

	int key;
	String name;
	Node leftChild;
	Node rightChild;

	Node(int key, String name) {
		this.key = key;
		this.name = name;
	}

	public String toString() {
		return name + " has the key " + key;
	}

}


// https://stackoverflow.com/questions/13484943/print-a-binary-tree-in-a-pretty-way
