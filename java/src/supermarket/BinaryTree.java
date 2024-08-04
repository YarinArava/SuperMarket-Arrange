package supermarket;

class BinaryTree {
    private TreeNode root;

    // Method to initialize Binary Tree.
    public BinaryTree() {
        root = null;
    }

    // Method that adding to Binary Tree.
    public void add(String productName, int amntSold) {
        root = addRecursive(root, productName, amntSold);
    }
    
    // Assistant Method that adding to Binary Tree (left path is lower sales orders than right path).
    private TreeNode addRecursive(TreeNode node, String productName, int amntSold) {
        if (node == null) {
            return new TreeNode(productName, amntSold);
        }

        if (amntSold <= node.amntSold) {
            node.left = addRecursive(node.left, productName, amntSold);
        } else {
            node.right = addRecursive(node.right, productName, amntSold);
        }

        return node;
    }
    
    // Function that used for searching all the sales and ordering from the best to least 
    public void preorderTraversal() {
        preorderTraversalRecursive(root);
    }

    private void preorderTraversalRecursive(TreeNode node) {
        if (node != null) {
        	 preorderTraversalRecursive(node.right);
            System.out.println("Product: " + node.productName + ", Amount Sold: " + node.amntSold);          
            preorderTraversalRecursive(node.left);
        }
    }
}

