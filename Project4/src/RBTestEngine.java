/**
 * 
 * @author cristian
 *
 */
public class RBTestEngine {

	public static void main(String[] args) {
		RedBlackTree<Integer, String> newTree = new RedBlackTree<Integer, String>();
//		newTree.add(10, "Dog");
//		newTree.add(9, "Cat");
//		newTree.add(11, "Rat");
//		newTree.add(8, "Dolphin");
//		//Case1 Works for now...
		newTree.add(20, null);
		newTree.add(21, null);
		newTree.add(18, null);
		newTree.add(25, null);
		newTree.printCase();
	}
}
