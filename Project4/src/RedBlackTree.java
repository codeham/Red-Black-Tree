public class RedBlackTree<K extends Comparable<K>, V> implements Tree<K, V> {
	public enum Color {
		RED, BLACK
	}

	// Nested Node Class
	private class Node<K, V> {
		private K key;
		private V value;
		private Node<K, V> left, right;
		private Node<K, V> parent;
		private Color currentColor;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.currentColor = Color.RED;
			left = null;
			right = null;
			parent = null;
		}

		public boolean isRed() {
			return currentColor == Color.RED;
		}

		public boolean isBlack() {
			return !isRed();
		}
	}

	Node<K, V> root = null;

	public void addLeaves(Node<K, V> x) {
		x.left = new Node<K, V>(null, null);
		x.left.currentColor = Color.BLACK;
		x.right = new Node<K, V>(null, null);
		x.right.currentColor = Color.BLACK;
	}

	public void add(K key, V value) {
		// Case 1: Tree is empty
		Node<K, V> x = null; // local variable
		if (root == null) {
			root = new Node<K, V>(key, value);
			root.currentColor = Color.BLACK;
			addLeaves(root);
			return;
		} else {
			x = BSTadd(key, value);
			// treeFixup(x);
		}
		// end of method
	}

	// Helper function to fix tree
	public void treeFixup(Node<K, V> x) {
		// Case: Parent is black
		if (x.parent.isBlack()) {
			return;
		} else {
			while (x.parent.currentColor == Color.RED) {
				if (x.parent == x.parent.parent.left) {
					Node<K, V> y = x.parent.parent.right;
					if(y.currentColor == Color.RED){
						case1(x,y);
					}else if(x == x.parent.right){
						x = x.parent;
						rotateLeft();
						x.parent.currentColor = Color.BLACK;
						x.parent.parent.currentColor = Color.RED;
						rotateRight();
					}else{
						root.currentColor = Color.BLACK;
					}
				}
			}
		}
		// End of method
	}

	// Helper function to implement BST adding invariants
	public Node<K, V> BSTadd(K addKey, V addValue) {
		Node<K, V> current = root;
		Node<K, V> prev = null;
		while (current.key != null) {
			prev = current;
			if (addKey.compareTo(current.key) <= 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		if (addKey.compareTo(prev.key) <= 0) {
			prev.left = new Node<K, V>(addKey, addValue);
			addLeaves(prev.left);
			prev.left.parent = prev;
			return prev.left;
		} else {
			prev.right = new Node<K, V>(addKey, addValue);
			addLeaves(prev.right);
			prev.right.parent = prev;
			return prev.right;
		}
		// end of method
	}

	public void case1(Node<K, V> x, Node<K, V> y) {
		// Case: Double Red Invariant
		x.parent.currentColor = Color.BLACK;
		y.currentColor = Color.BLACK;
		x.parent.parent.currentColor = Color.RED;
		x = x.parent.parent;
	}

	public void case2(Node<K, V> x) {

	}

	public void rotateLeft() {

	}

	public void rotateRight() {

	}

	public void reColoring(Node<K, V> x, Node<K, V> y) {
		x.parent.currentColor = Color.BLACK;
		y.currentColor = Color.BLACK;
		x.parent.parent.currentColor = Color.RED;

	}

	public void printCase() {
		System.out.println(root.key);
		System.out.println(root.left.key);
	}

	@Override
	public V remove(K key) {
		return null;
	}

	@Override
	public V lookup(K key) {
		return null;
	}

	@Override
	public String toPrettyString() {
		return null;
	}

}