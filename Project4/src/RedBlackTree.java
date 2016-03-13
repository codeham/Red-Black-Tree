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
  int size = 0;
  public void addLeaves(Node<K, V> current) {
    current.left = new Node<K, V>(null, null);
    current.left.currentColor = Color.BLACK;
    current.right = new Node<K, V>(null, null);
    current.right.currentColor = Color.BLACK;
  }

  public void add(K key, V value) {
    // Case: Tree is&& current != root empty
    Node<K, V> current = new Node<K, V>(key, value);
    if (root == null) {
      root = current;
      root.currentColor = Color.BLACK;
      addLeaves(root);
      size++;
      return;
    } else {
      current = BSTadd(key, value);
      size++;
      treeFixup(current);
    }
    // end of method
  }

  // Helper function to fix tree
  public void treeFixup(Node<K, V> current) {
    // Case: Parent is black
	  if ( current == root ){
		  current.currentColor = Color.BLACK;
	  }else{
      while (current.parent.currentColor == Color.RED && current.parent != null) {
        if (current.parent == current.parent.parent.left) { // Parent is on left side of subtree
          Node<K, V> other = current.parent.parent.right; // uncle on the right side
          if (other.currentColor == Color.RED) { // Repaint (Case1)
            case1(current, other);
          } else {
            if (current == current.parent.right) { // Right side of subtree
              case2(current);
            } // Continue to case 3..
            case3(current);
          }  
        } else {
        	System.out.println(current.key + " GOT HERE");
          invertedCase2(current);
          invertedCase3(current);
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
      if (addKey.compareTo(current.key) < 0) {
        current = current.left;
      } else {
        current = current.right;
      }
    }

    if (addKey.compareTo(prev.key) < 0) {
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

  public void case1(Node<K, V> current, Node<K, V> other) {
    reColoring(current, other);
    current = current.parent.parent; // Pointer is moved to grandparent
  }

  public void case2(Node<K, V> current) {
    current = current.parent;
    rotateLeft(current);
  }

  public void case3(Node<K, V> current) {
    current.parent.currentColor = Color.BLACK;
    current.parent.parent.currentColor = Color.RED;
    rotateRight(current.parent.parent); // Grandparent Rotation..
    current = current.parent;
  }

  public void invertedCase2(Node<K, V> current) {
    current = current.parent;
    rotateRight(current);
  }

  public void invertedCase3(Node<K, V> current) {
    current.parent.currentColor = Color.BLACK;
    current.parent.parent.currentColor = Color.RED;
    rotateLeft(current.parent.parent);
    current = current.parent;
    System.out.println(current.key + " parent " + current.parent.left.key + " Got to case 3");
  }

  /**
   * This left rotation is called when it hits the black uncle case when
   * invariant 4 is broken, how this method works is it sets a pointer to the
   * value being added (pointer other), if we are dealing with case N, it is
   * assumed that there is a pointer placed at the parent of pointer current. we
   * then set the subtree connected to the left of other to the right pointer of
   * current.
   * 
   * @param current
   * @param other
   */
  public void rotateLeft(Node<K, V> current) {
    Node<K, V> other = current.right;
    current.right = other.left;
    if (other.left != null) {
      other.left.parent = current;
    }
    other.parent = current.parent;
    if (current.parent == null) {
      root = other;
    } else if (current == current.parent.left) {
      current.parent.left = other;
    } else {
      current.parent.right = other;
    }
    other.left = current;
    current.parent = other;
  }

  public void rotateRight(Node<K, V> current) {
    Node<K, V> other = current.left;
    current.left = other.right;
    if (other.right != null) {
      other.right.parent = current;
    }
    other.parent = current.parent;
    if (current.parent == null) {
      root = other;
    } else if (current == current.parent.left) {
      current.parent.left = other;
    } else {
      current.parent.right = other;
    }
    other.right = current;
    current.parent = other;
  }

  public void reColoring(Node<K, V> current, Node<K, V> other) {
    current.parent.currentColor = Color.BLACK;
    // added node's parent to black,
    // restoring invariant 4root.currentColor = Color.BLACK;
    other.currentColor = Color.BLACK;
    // uncle is set to black to restore
    // invariant 5
    current.parent.parent.currentColor = Color.RED; // grandparent is set to RED

  }

  public void printCase() {
    // Case 2 && Case 3
    System.out.println(root.key);
    System.out.println(root.left.key);
    System.out.println(root.right.key);
    
    System.out.println(root.left.left.key);
    System.out.println(root.left.right.key);
    
    System.out.println(root.right.left.key);
    System.out.println(root.right.right.key);
    System.out.println(root.right.right.right.key);
    System.out.println(root.right.right.left.key);


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