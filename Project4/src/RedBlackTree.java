
public class RedBlackTree<K extends Comparable<K>, V> implements Tree<K, V> {
	public enum Color {
		RED, BLACK
	}

	// Nested Node Class
	private class Node {
		private K key;
		private V value;
		private Node left, right;
		private Node parent;
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

	Node root = null;

	public void add(K key, V value) {
		// Case 1: Tree is empty
		Node x = null; // local variable
		if (root == null) {
			root = new Node(key, value);
			root.currentColor = Color.BLACK;
			return;
		} else {
			x = BSTadd(key, value);
			treeFixup(x);
		}
		// end of method
	}

	// Helper function to fix tree
	public void treeFixup(Node x) {
		// Case: Parent is black
		if (x.parent.isBlack()) {
			return;
		} else {
			// Case: Double Red Invariant
			while (x.parent.isRed()) {
				if (x.parent == x.parent.parent.left) {
					Node y = x.parent.parent.right;
					if (y.isRed()) {
						// uncle is red, re-color
						reColoring(x, y);
					}
				}
			}
		}
		//End of method
	}

	// Helper function to implement BST adding invariants
	public Node BSTadd(K addKey, V addValue) {
		Node current = root;
		Node prev = null;
		while (current != null) {
			prev = current;
			if (addKey.compareTo(current.key) <= 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		if (addKey.compareTo(prev.key) <= 0) {
			prev.left = new Node(addKey, addValue);
			prev.left.parent = prev;
			return prev.left;
		} else {
			prev.right = new Node(addKey, addValue);
			prev.right.parent = prev;
			return prev.right;
		}
		// end of method
	}

	public void rotateLeft() {

	}

	public void rotateRight(Node x, Node y) {

	}

	public void reColoring(Node x, Node y) {

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
