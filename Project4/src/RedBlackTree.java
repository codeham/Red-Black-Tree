
public class RedBlackTree<K extends Comparable<K>, V> implements Tree<K, V> {
	public enum Color {
		RED, BLACK
	}

	// Nested Node Class
	private class Node {
		private K key;
		private V value;
		private Node left, right;
		private Color currentColor;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.currentColor = Color.RED;
			left = null;
			right = null;
		}
	}
	
	Node root = null;
	public void add(K key, V value) {
		// Case 1: Tree is empty
		if (root == null) {
			root = new Node(key, value);
			root.currentColor = Color.BLACK;
		} else {
			BSTadd(key, value);
		}

		// end of method
	}

	// Helper function to implement BST adding invariants
	public void BSTadd(K addKey, V addValue) {
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
		} else {
			prev.right = new Node(addKey, addValue);
		}
		// end of method
	}
	
	public void printCase(){
		System.out.println(root.key);
		System.out.println(root.left.key);
		System.out.println(root.right.key);
		System.out.println(root.right.right.key);
		System.out.println(root.right.left.key);
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
