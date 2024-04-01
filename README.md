# Project Name: Quadrant Tree Image Processing

## Overview
This project implements a Quadrant Tree data structure for image processing tasks. The Quadrant Tree is used to efficiently represent images and perform various operations such as querying for pixels at different levels of detail, finding nodes with matching colors, and constructing the tree from pixel data.

## Files
- **QTreeNode.java**: Defines the `QTreeNode` class representing a node in the Quadrant Tree.
- **QuadrantTree.java**: Contains the `QuadrantTree` class responsible for building and manipulating the Quadrant Tree.
- **TestQuadrant.java**: Provides test cases to verify the correctness of the implemented methods.
- **Duple.java**: Defines the `Duple` class used to hold a pair of values.
- **ListNode.java**: Defines the `ListNode` class for linked list operations.
- **Gui.java**: Contains GUI-related functionalities.
- **CheckTree.java**: Checks the structure of the Quadrant Tree.
- **DrawImage.java**: Handles image drawing operations.
- **QTreeException.java**: Defines the `QTreeException` class for custom exceptions.

## Usage
1. **QTreeNode**: Represents a node in the Quadrant Tree. It stores information such as coordinates, size, color, parent, and children.
2. **QuadrantTree**: Builds the Quadrant Tree from pixel data and provides methods for querying and manipulation.
3. **TestQuadrant**: Contains test cases to ensure the correctness of the implemented functionalities.

## Dependencies
- Java SE Development Kit (JDK)
- GUI libraries (if GUI functionalities are used)

## How to Run
1. Compile the Java source files using the Java compiler (`javac`).
2. Run the compiled classes using the Java Virtual Machine (`java`).
3. Execute the `TestQuadrant` class to run the provided test cases and verify the correctness of the implementation.

## Example
```java
// Example usage of Quadrant Tree
int[][] pixels = { {0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0} };
QuadrantTree tree = new QuadrantTree(pixels);
QTreeNode root = tree.getRoot();

// Get pixels at a specific level of detail
ListNode<QTreeNode> pixelsAtLevel = tree.getPixels(root, 2);

// Find nodes with matching color at a specific level
Duple matchingNodes = tree.findMatching(root, 1, 1);

// Perform other operations as required
```

## Credits
This project is developed by Abdullah9213.

## License
This project is licensed under the [MIT License](LICENSE).
