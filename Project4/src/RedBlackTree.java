/**
 * 
 * @author cristian
 *
 * @param <K>
 * @param <V>
 */

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
		// Case: Tree is empty
		Node<K, V> x = new Node<K, V>(key, value);
		if (root == null) {
			root = x;
			root.currentColor = Color.BLACK;
			addLeaves(root);
			return;
		} else {
			x = BSTadd(key, value);
			treeFixup(x);
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
				if (x.parent == x.parent.parent.left){ //Parent is on left side of subtree
					Node<K, V> y = x.parent.parent.right; //uncle on the right side
					if (y.currentColor == Color.RED){ //Repaint (Case1)
						case1(x, y);
					}else if(x == x.parent.right){ //Right side of subtree
						case2(x);
						case3(x);
					}
				}else{
					invertedCase2(x);
					invertedCase3(x);
				}
				root.currentColor = Color.BLACK;
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
		reColoring(x, y);
		x = x.parent.parent; // Pointer is moved to grandparent
	}

	public void case2(Node<K, V> x) {
		x = x.parent;
		rotateLeft(x);
	}

	public void case3(Node<K, V> x) {
		x.parent.currentColor = Color.BLACK;
		x.parent.parent.currentColor = Color.RED;
		rotateRight(x.parent.parent); //Grandparent Rotation..
	}

	public void invertedCase2(Node<K, V> x) {
		x = x.parent;
		rotateRight(x);
	}

	public void invertedCase3(Node<K, V> x) {
		x.parent.currentColor = Color.BLACK;
		x.parent.parent.currentColor = Color.RED;
		rotateLeft(x.parent.parent);
	}

	/**
	 * This left rotation is called when it hits the black uncle case when
	 * invariant 4 is broken, how this method works is it sets a pointer to the
	 * value being added (pointer y), if we are dealing with case N, it is
	 * assumed that there is a pointer placed at the parent of pointer x. we
	 * then set the subtree connected to the left of y to the right pointer of
	 * x.
	 * 
	 * @param x
	 * @param y
	 */
	public void rotateLeft(Node<K, V> x) {
		Node<K, V> y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		
		y.parent = x.parent;
		
		if (x.parent == null) {
			root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

	public void rotateRight(Node<K, V> x) {
		Node<K, V> y = x.left;
		x.left = y.right;
		if (y.right != null) {
			y.right.parent = x;
		}
		
		y.parent = x.parent;
		
		if (x.parent == null) {
			root = y;
		} else if (x == x.parent.right) {
			x.parent.right = y;
		} else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}

	public void reColoring(Node<K, V> x, Node<K, V> y) {
		x.parent.currentColor = Color.BLACK;
		// added node's parent to black,
		// restoring invariant 4
		y.currentColor = Color.BLACK;
		// uncle is set to black to restore
		// invariant 5
		x.parent.parent.currentColor = Color.RED; // grandparent is set to RED

	}

	public void printCase() {
		 //Case 1
//		 System.out.println(root.currentColor);
//		 System.out.println(root.left.currentColor);
//		 System.out.println(root.right.currentColor);
//		 System.out.println(root.left.left.currentColor);

		// Case 2 && Case 3
		System.out.println(root.key);
		System.out.println(root.left.key);
		System.out.println(root.right.key);
		System.out.println(root.right.right.key);

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