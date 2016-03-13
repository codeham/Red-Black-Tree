/**
 * 
 * @author cristian
 *
 */
public class RBTestEngine {

	public static void main(String[] args) {
		RedBlackTree<Integer, String> newTree = new RedBlackTree<Integer, String>();
		newTree.add(10, "Dog");
		newTree.add(9, "Cat");
		newTree.add(11, "Rat");
		newTree.add(8, "Dolphin");
		newTree.add(5, null);
		newTree.add(10, null);
		newTree.add(13, null);
		newTree.add(14, null);
		newTree.add(15, null);
		System.out.println("Size: " + newTree.size);
		newTree.printCase();
	}
}
