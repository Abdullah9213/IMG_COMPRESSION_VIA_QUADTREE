public class TestQuadrant {

	/* Test constructor, setter, and getter methods */
	private static boolean test1() {
		boolean testPassed = true;
		QTreeNode root = new QTreeNode();
		try {
			if (root.getChild(1) != null)
				testPassed = false;
		} catch (Exception e) {ex(e); testPassed = false;}
		
		QTreeNode child = new QTreeNode(null,0,0,10,1);
		try {
			child.setParent(root);
			root.setChild(child,3);
			if (root.getChild(0) != null) testPassed = false;
			if (root.getChild(3) != child) testPassed = false;
			if (child.getParent() != root) testPassed = false;
			root.setChild(child,4);
		} catch (QTreeException e) {;}
		catch (Exception e) {ex(e); testPassed = false;}
		try {
			QTreeNode c = child.getChild(0);
		} catch (QTreeException e) {;}
		catch (Exception e1) {ex(e1); testPassed = false;}
		try {
			QTreeNode c = root.getChild(4);
		} catch (QTreeException e) {;}
		catch (Exception e1) {ex(e1); testPassed = false;}	
		// try {
		// 	if (root.getSize() != 0) testPassed = false;
		// 	if (root.getColor() != 0) testPassed = false;
		// 	if (root.getParent() != null) testPassed = false;
		// 	root.setx(10);
		// 	if (root.getx() != 10) testPassed = false;
		// 	root.sety(20);
		// 	if (root.gety() != 20) testPassed = false;
		// 	root.setSize(30);
		// 	if (root.getSize() != 30) testPassed = false;
		// 	root.setColor(40);
		// 	if (root.getColor() != 40) testPassed = false;
		// } catch (Exception e) {ex(e); testPassed = false;}	
		// try {
		// 	QTreeNode c = root.getChild(0);
		// } catch (QTreeException e) {;}
		// catch (Exception e1) {ex(e1); testPassed = false;}
		// try {
		// 	QTreeNode c = root.getChild(1);
		// } catch (QTreeException e) {;}
		// catch (Exception e1) {ex(e1); testPassed = false;}
		// //edge case
		// try {
		// 	QTreeNode c = root.getChild(2);
		// } catch (QTreeException e) {;}
		// catch (Exception e1) {ex(e1); testPassed = false;}
		return testPassed;
	}
	
	/* Test method contains */
	public static boolean test2() {
		boolean testPassed = true;
		QTreeNode root = new QTreeNode(null,0,0,10,2);
		if (root.contains(1,1) == false) testPassed = false;
		if (root.contains(11,0)) testPassed = false;
		if (root.contains(0,-1)) testPassed = false;
		if (root.contains(0,0) == false) testPassed = false;
		if (root.contains(10,0)) testPassed = false;
		if (root.contains(9,9) == false) testPassed = false;
		if (root.contains(0,9) == false) testPassed = false;
		if (root.contains(9,0) == false) testPassed = false;
		// if (root.contains(10,10)) testPassed = false;
		// if (root.contains(0,10)) testPassed = false;
		// //edge cases
		// if (root.contains(0,0) == false) testPassed = false;
		// if (root.contains(10,0)) testPassed = false;
		// if (root.contains(0,10)) testPassed = false;
		// if (root.contains(10,10)) testPassed = false;
		// if (root.contains(5,5) == false) testPassed = false;
		return testPassed;
	}
	
	/* Test constructor */
	private static boolean test3() {
		boolean testPassed = true;
		int[][] pixels = new int[1][1];
		pixels[0][0] = 8;
		try {
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();
			if (numNodes(root) != 1) testPassed = false;
			if (root.getColor() != 8) testPassed = false;
			pixels = new int[2][2];
			for (int i = 0; i < 2; ++i)
				for (int j = 0; j < 2; ++j)
					pixels[i][j] = 2;
			tree = new QuadrantTree(pixels);
			if (numNodes(tree.getRoot()) != 5) testPassed = false; 
			
		    pixels = new int[32][32];
			for (int i = 0; i < 32; ++i)
				for (int j = 0; j < 32; ++j)
					pixels[i][j] = 32;		
			tree = new QuadrantTree(pixels);
			if (numNodes(tree.getRoot()) != 1365) testPassed = false;
			if (height(tree.getRoot()) != 5) testPassed = false;
			

		} catch (Exception e) {ex(e); testPassed = false;}
		return testPassed;
	}
	
	/* Test findNode */
	private static boolean test4() {
		boolean testPassed = true;
		int[][] pixels = new int[32][32];
		for (int i = 0; i < 32; ++i)
			for (int j = 0; j < 32; ++j)
				pixels[i][j] = i+j;
		try {		
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();
			QTreeNode r = tree.findNode(root,0,0,0);
			if (r != root) testPassed = false;
			r = tree.findNode(root,5,1,1);
			if (r.getColor() != 2) testPassed = false;
			r = tree.findNode(root,4,32,32);
			if (r != null) testPassed = false;

		} catch (Exception e) {ex(e); testPassed = false;}	
		pixels = new int[4][4];
		for (int i = 0; i < 4; ++i)
			for (int j = 0; j < 4; ++j)
				pixels[i][j] = i+j;
		try {
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();
			QTreeNode r = tree.findNode(root,0,0,0);
			if (r != root) testPassed = false;
			r = tree.findNode(root,5,1,1);
			if (r.getColor() != 2) testPassed = false;
			r = tree.findNode(root,4,4,4);
			if (r != null) testPassed = false;
		} catch (Exception e) {ex(e); testPassed = false;}
		//r = tree.findNode(root, 3, 16, 16);
		pixels = new int[8][8];
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				pixels[i][j] = i+j;
		try {
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();
			QTreeNode r = tree.findNode(root,0,0,0);
			if (r != root) testPassed = false;
			r = tree.findNode(root,5,1,1);
			if (r.getColor() != 2) testPassed = false;
			r = tree.findNode(root, 3, 16, 16);
			if (r != null) testPassed = false;
		} catch (Exception e) {ex(e); testPassed = false;}
		return testPassed;
	}	
	
	/* Test getPixels */
	private static boolean test5() {
		boolean testPassed = true;
		int[][] pixels = new int[32][32];
		for (int i = 0; i < 32; ++i)
			for (int j = 0; j < 32; ++j)
				pixels[i][j] = i;
		try {		
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();			
			ListNode<QTreeNode> list = tree.getPixels(root,0);
			if (length(list) != 1) testPassed = false;
			list = tree.getPixels(root,5);
			if (length(list) != 1024) testPassed = false;
		} catch (Exception e) {ex(e); testPassed = false;}	

		return testPassed;
	}		
	
	/* Test findMatching */
	private static boolean test6() {
		boolean testPassed = true;
		int[][] pixels = new int[32][32];
		for (int i = 0; i < 32; ++i)
			for (int j = 0; j < 32; ++j)
				pixels[i][j] = i;
		try {		
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();
			Duple pair = tree.findMatching(root,1,5);			
			ListNode<QTreeNode> list = pair.getFront();
			if (length(list) != 512) testPassed = false;
			pair = tree.findMatching(root,2,7);
			list = pair.getFront();
			if (length(list) != 544) testPassed = false;
		} catch (Exception e) {ex(e); testPassed = false;}		
		return testPassed;
	}

	//Additional test cases

	/* Test constructor with empty array */
	// private static boolean test7() {
	// 	boolean testPassed = true;
	// 	int[][] pixels = new int[0][0];
	// 	try {
	// 		QuadrantTree tree = new QuadrantTree(pixels);
	// 		QTreeNode root = tree.getRoot();
	// 		if (root != null) testPassed = false; // The root should be null for an empty array
	// 	} catch (Exception e) {
	// 		ex(e);
	// 		testPassed = false;
	// 	}
	// 	return testPassed;
	// }

	/* Test constructor with single pixel */
	private static boolean test8() {
		boolean testPassed = true;
		int[][] pixels = {{255}}; // Single white pixel
		try {
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();
			if (root.getSize() != 1 || root.getColor() != 255) testPassed = false;
		} catch (Exception e) {
			ex(e);
			testPassed = false;
		}
		return testPassed;
	}

	/* Test constructor with large array */
	
	

	/* Test constructor with negative pixel values */
	private static boolean test9() {
		boolean testPassed = true;
		int[][] pixels = {{-100, -50}, {-200, -150}}; // Two pixels with negative colors
		try {
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();
			if (root == null || root.getSize() != 2 || root.getColor() != -125) testPassed = false; // Expected average color for this test case
		} catch (Exception e) {
			ex(e);
			testPassed = false;
		}
		return testPassed;
	}

	

	/* Test constructor with random input */
	private static boolean test10() {
		boolean testPassed = true;
		int size = 10; // Size of the array
		int[][] pixels = new int[size][size];
		// Initialize pixels with random values
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				pixels[i][j] = (int) (Math.random() * 256); // Random color value between 0 and 255
			}
		}
		try {
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();
			// You can add assertions based on your expectations for this test case
		} catch (Exception e) {
			ex(e);
			testPassed = false;
		}
		return testPassed;
	}

	/* Test constructor with large array */
	private static boolean test11() {
		boolean testPassed = true;
		int size = 1000; // Size of the array
		int[][] pixels = new int[size][size];
		// Initialize pixels with random values
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				pixels[i][j] = (int) (Math.random() * 256); // Random color value between 0 and 255
			}
		}
		try {
			QuadrantTree tree = new QuadrantTree(pixels);
			QTreeNode root = tree.getRoot();
			// You can add assertions based on your expectations for this test case
		} catch (Exception e) {
			ex(e);
			testPassed = false;
		}
		return testPassed;
	}

	
	
		
	public static void main(String[] args) {
		// The first two tests are for class QTreeNode
		try {
			if (test1()) System.out.println("Test 1 passed");
			else System.out.println("Test 1 failed");
		} catch (Exception e) {ex(e); System.out.println("Test 1 failed");}
		try {
			if (test2()) System.out.println("Test 2 passed");
			else System.out.println("Test 2 failed");
		} catch (Exception e) {ex(e); System.out.println("Test 2 failed");}	
		
		// The remaining tests are for class QuadrantTree
		try {
			if (test3()) System.out.println("Test 3 passed");
			else System.out.println("Test 3 failed");
		} catch (Exception e) {ex(e); System.out.println("Test 3 failed");}	
		
		try {
			if (test4()) System.out.println("Test 4 passed");
			else System.out.println("Test 4 failed");
		} catch (Exception e) {ex(e); System.out.println("Test 4 failed");}	
		
		try {
			if (test5()) System.out.println("Test 5 passed");
			else System.out.println("Test 5 failed");
		} catch (Exception e) {ex(e); System.out.println("Test 5 failed");}	
		
		try {
			if (test6()) System.out.println("Test 6 passed");
			else System.out.println("Test 6 failed");
		} catch (Exception e) {ex(e); System.out.println("Test 6 failed");}

		// Additional test cases
		try {
			if (test7()) System.out.println("Test 7 passed");
			else System.out.println("Test 7 failed");
		} catch (Exception e) {
			ex(e);
			System.out.println("Test 7 failed");
		}

		try {
			if (test8()) System.out.println("Test 8 passed");
			else System.out.println("Test 8 failed");
		} catch (Exception e) {
			ex(e);
			System.out.println("Test 8 failed");
		}

		try {
			if (test9()) System.out.println("Test 9 passed");
			else System.out.println("Test 9 failed");
		} catch (Exception e) {
			ex(e);
			System.out.println("Test 9 failed");
		}

		try {
			if (test10()) System.out.println("Test 10 passed");
			else System.out.println("Test 10 failed");
		} catch (Exception e) {
			ex(e);
			System.out.println("Test 10 failed");
		}

		try {
			if (test11()) System.out.println("Test 11 passed");
			else System.out.println("Test 11 failed");
		} catch (Exception e) {
			ex(e);
			System.out.println("Test 11 failed");
		}


		
													
	}
	
	/* Count the number of nodes in the tree with root r */
	private static int numNodes (QTreeNode r) {
		int c = 1;
		if (r == null) return 0;
		else if (r.isLeaf()) return 1;
		else {
			for (int i = 0; i < 4; ++i)
				c = c + numNodes(r.getChild(i));
		}
		return c;
	}
	
	/* Compute the height of the tree rooted at r */
	private static int height(QTreeNode r) {
		if (r == null) return 0;
		else if (r.isLeaf()) return 0;
		else {
			int h = 0, mh;
			mh = height(r.getChild(0));
			for (int i = 1; i < 4; ++i) {
				h = height(r.getChild(i));
				if (h > mh) mh = h;
			}
			return h + 1;
		}
	}
	
	/* Returns the length of the given list */
	private static int length(ListNode<QTreeNode> list) {
		int c = 0;
		while (list != null) {
			++c;
			list = list.getNext();
		}
		return c;
	}
	
	private static void ex (Exception e) {
		System.out.println("Your code has crashed. The following exception was thrown:");
		System.out.println(e.getMessage());
	}
}
