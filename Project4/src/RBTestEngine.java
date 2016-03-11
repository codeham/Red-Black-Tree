
public class RBTestEngine {

	public static void main(String[] args) {
		RedBlackTree<Integer, String> newTree = new RedBlackTree<Integer, String>();
		newTree.add(10, "Dog");
		newTree.add(9, "Cat");
		newTree.printCase();
	}
}
