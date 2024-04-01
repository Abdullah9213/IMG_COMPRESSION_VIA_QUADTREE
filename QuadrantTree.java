import java.util.ArrayList;
import java.util.List;

/*
 * A class named QuadrantTree that represents a tree of QTreeNodes and provides methods to search for nodes(entire image or sub-region of it) and pixels.
 
 */
public class QuadrantTree {
    private QTreeNode root;     // The root of the tree contains the entire image

    /**
     * Constructor for QuadrantTree.
     * @param thePixels The pixels of the image.
     */
    public QuadrantTree(int[][] thePixels) {
        root = buildTree(thePixels, 0, 0, thePixels.length);
    }

    /**
     * Returns the root of the tree.
     * @return The root of the tree.
     */

    public QTreeNode getRoot() {
        return root;
    }

    /**
     * Returns the pixels of the tree at a specific level.
     * @param r The root of the tree.
     * @param theLevel The level of the tree.
     * @return The pixels of the tree at a specific level.
     */
    public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
        List<QTreeNode> result = new ArrayList<>();
        getPixelsHelper(r, theLevel, 0, result);
        return listToLinkedList(result);
    }

    private ListNode<QTreeNode> listToLinkedList(List<QTreeNode> nodes) {
        ListNode<QTreeNode> head = null;
        ListNode<QTreeNode> prev = null;
        for (QTreeNode node : nodes) {
            ListNode<QTreeNode> current = new ListNode<>(node);
            if (head == null) {
                head = current;
            } else {
                prev.setNext(current);
            }
            prev = current;
        }
        return head;
    }

    /**
     * Helper method for getPixels.
     * @param node The node to start from.
     * @param targetLevel The target level to search for.
     * @param currentLevel The current level of the tree.
     * @param result The list to store the nodes.
     */
    private void getPixelsHelper(QTreeNode node, int targetLevel, int currentLevel, List<QTreeNode> result) {
        if (node == null || currentLevel > targetLevel) {
            return;
        }

        if (currentLevel == targetLevel || node.isLeaf()) {
            result.add(node);
        } else {
            QTreeNode[] children = new QTreeNode[4];
            for (int i = 0; i < 4; i++) {
                children[i] = node.getChild(i);
            }
            for (QTreeNode child : children) {
                getPixelsHelper(child, targetLevel, currentLevel + 1, result);
            }
        }
    }

    /**
     * Returns the number of nodes in the tree at the specified level.
     * @param r The root of the tree.
     * @param theLevel The level of the tree.
     * @return The number of nodes in the tree at the specified level.
     */
    public Duple findMatching(QTreeNode r, int theColor, int theLevel) {
        List<QTreeNode> matchingNodes = new ArrayList<>();
        int count = findMatchingHelper(r, theColor, theLevel, 0, matchingNodes);
        ListNode<QTreeNode> front = createLinkedList(matchingNodes); // Convert list to linked list
        return new Duple(front, count);
    }

    /**
     * Creates a linked list from the specified list of nodes.
     * @param nodes The list of nodes to convert.
     * @return The linked list of nodes.
     */
    private ListNode<QTreeNode> createLinkedList(List<QTreeNode> nodes) {
        ListNode<QTreeNode> head = null;
        ListNode<QTreeNode> current = null;

        for (QTreeNode node : nodes) {
            ListNode<QTreeNode> newNode = new ListNode<>(node);
            if (head == null) {
                head = newNode;
                current = newNode;
            } else {
                current.setNext(newNode);
                current = newNode;
            }
        }

        return head;
    }

    /**
     * Helper method for findMatching.
     * @param node The node to start from.
     * @param targetColor The target color to search for.
     * @param targetLevel The target level to search for.
     * @param currentLevel The current level of the tree.
     * @param result The list to store the nodes.
     * @return The number of matching nodes.
     */
    private int findMatchingHelper(QTreeNode node, int targetColor, int targetLevel, int currentLevel, List<QTreeNode> result) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (currentLevel == targetLevel || node.isLeaf()) {
            if (Gui.similarColor(node.getColor(), targetColor)) {
                result.add(node);
                count++;
            }
        } else {
            QTreeNode[] children = new QTreeNode[4];
            for (int i = 0; i < 4; i++) {
                children[i] = node.getChild(i);
            }
            for (QTreeNode child : children) {
                count += findMatchingHelper(child, targetColor, targetLevel, currentLevel + 1, result);
            }
        }

        return count;
    }

    /**
     * Returns the node at the specified coordinates.
     * @param r The root of the tree.
     * @param theLevel The level of the tree.
     * @param x The x-coordinate of the node.
     * @param y The y-coordinate of the node.
     * @return The node at the specified coordinates.
     */
    public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
        if (r == null || theLevel < 0) {
            return null;
        }

        if (theLevel == 0 || r.isLeaf()) {
            return r;
        }

        int size = r.getSize();
        int halfSize = size / 2;

        if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
            return null;
        }

        if (x < halfSize && y < halfSize) {
            return findNode(r.getChild(0), theLevel - 1, x, y);
        } else if (x >= halfSize && y < halfSize) {
            return findNode(r.getChild(1), theLevel - 1, x - halfSize, y);
        } else if (x < halfSize && y >= halfSize) {
            return findNode(r.getChild(2), theLevel - 1, x, y - halfSize);
        } else {
            return findNode(r.getChild(3), theLevel - 1, x - halfSize, y - halfSize);
        }
    }

    /**
     * Builds a tree from the specified pixels.
     * @param pixels 2d arraylist consisting of pixels of the image.
     * @param x The x-coordinate of the node.
     * @param y The y-coordinate of the node.
     * @param size The size of the node.
     * @return The root of the tree.
     */
    private QTreeNode buildTree(int[][] pixels, int x, int y, int size) {
        if (size == 1) {
            return new QTreeNode(null,x, y, size, pixels[y][x]);
        } else if (size == 0) {
            return null;
        } else {
            int halfSize = size / 2;
            int lastSize = size - halfSize; // Adjust size for last quadrant
            QTreeNode[] children = new QTreeNode[4];
            children[0] = buildTree(pixels, x, y, halfSize);
            children[1] = buildTree(pixels, x + halfSize, y, halfSize);
            children[2] = buildTree(pixels, x, y + halfSize, halfSize);
            children[3] = buildTree(pixels, x + halfSize, y + halfSize, lastSize); // Use adjusted size
            QTreeNode node = new QTreeNode(null, x, y, size, averageColor(pixels, x, y, size));
            for (QTreeNode child : children) {
                if (child != null) {
                    child.setParent(node);
                }
            }
            for (int i = 0; i < 4; i++) {
                node.setChild(children[i],i);
            }
            return node;
        }
    }
    


    /**
     * Returns the average color of the specified pixels.
     * @param pixels 2d arraylist consisting of pixels of the image.
     * @param x The x-coordinate of the node.
     * @param y The y-coordinate of the node.
     * @param size The size of the node.
     * @return The average color of the specified pixels.
     */
    private int averageColor(int[][] pixels, int x, int y, int size) {
        if (pixels.length == 0) {
            return 0; // Default color for empty array
        }
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;

        // Iterate over all pixels within the actual quadrant size
        for (int i = y; i < y + Math.min(size, pixels.length - y); i++) {
            for (int j = x; j < x + Math.min(size, pixels[0].length - x); j++) {
                int color = pixels[i][j];
                redSum += (color >> 16) & 0xff;
                greenSum += (color >> 8) & 0xff;
                blueSum += color & 0xff;
            }
        }

        int numPixels = Math.min(size, pixels.length - y) * Math.min(size, pixels[0].length - x);
        // Ensure no division by zero
        if (numPixels == 0) {
            return 0; // Or a default color if desired
        }

        int averageRed = Math.round(redSum / (float) numPixels);
        int averageGreen = Math.round(greenSum / (float) numPixels);
        int averageBlue = Math.round(blueSum / (float) numPixels);

        // Combine average components back into an ARGB integer
        return (0xFF << 24) | (averageRed << 16) | (averageGreen << 8) | averageBlue;
    }
   


}
