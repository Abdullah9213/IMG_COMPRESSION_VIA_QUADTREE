

/**
 * A class named QTreeNode that represents a specific region of an image.
 */
public class QTreeNode {
    private int x;
    private int y;
    private int size;
    private int color;
    private QTreeNode parent;
    private QTreeNode[] children;

    /**
     * Default constructor for QTreeNode.
     */
    public QTreeNode() {
        parent = null;
        children = new QTreeNode[4];
        for (int i = 0; i < 4; i++) {
            children[i] = null;
        }
        x = 0;
        y = 0;
        size = 0;
        color = 0;
    }

    /**
     * Constructor for QTreeNode.
     * @ param theChildren The children of the node.
     * @ param xcoord The x-coordinate of the node.
     * @ param ycoord The y-coordinate of the node.
     * @ param theSize The size of the node.
     * @ param theColor The color of the node.
     * @throws QTreeException If the children array is null.
     * @return A new QTreeNode object.
     * 
     */
    public QTreeNode(QTreeNode[] theChildren, int xcoord, int ycoord, int theSize, int theColor) {
        parent = null;
        if(theChildren!=null)
            children = theChildren;
        else
           { 
                children = new QTreeNode[4];
                for (int i = 0; i < 4; i++) 
                    children[i] = null;
            }
        x = xcoord;
        y = ycoord;
        size = theSize;
        color = theColor;
    }

    

    // Getter methods
    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

    public QTreeNode getParent() {
        return parent;
    }

    /**
     * See the child(the sub-region of image ) node at the specified index.
     * 
     * @param index The index of the child node to return.
     * @return The child node at the specified index.
     * @throws QTreeException If the index is invalid or the children array is null.
     */
    public QTreeNode getChild(int index) throws QTreeException {
        if (children == null || index < 0 || index > 3) {
            throw new QTreeException("Invalid index or null children array");
        }
        return children[index];
    }

    // Setter methods
    public void setx(int newx) {
        x = newx;
    }

    public void sety(int newy) {
        y = newy;
    }

    public void setSize(int newSize) {
        size = newSize;
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public void setParent(QTreeNode newParent) {
        parent = newParent;
    }

    /**
     * Set the child(the sub-region of image ) node at the specified index.
     * 
     * @param newChild The new child node to set.
     * @param index    The index of the child node to set.
     * @throws QTreeException If the index is invalid or the children array is null.
     */
    public void setChild(QTreeNode newChild, int index) throws QTreeException {
        if (children == null || index < 0 || index > 3) {
            throw new QTreeException("Invalid index or null children array");
        }
        children[index] = newChild;
    }


    /**
     * Check if the node contains the specified point.
     * 
     * @param xcoord The x-coordinate of the point.
     * @param ycoord The y-coordinate of the point.
     * @return True if the node contains the point, false otherwise.
     */
    public boolean contains(int xcoord, int ycoord) {

        // Check if the point (xcoord, ycoord) lies within the bounds
        return xcoord >= x && xcoord < x + size && ycoord >= y && ycoord < y + size;
    }

    /**
     * Check if the node is a leaf node.
     * 
     * @return True if the node is a leaf node, false otherwise.
     */
    public boolean isLeaf() {
        if (children == null) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if (children[i] != null) {
                return false;
            }
        }
        return true;
    }
}
