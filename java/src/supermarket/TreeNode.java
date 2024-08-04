package supermarket;

class TreeNode {
    int amntSold;
    String productName;
    TreeNode left;
    TreeNode right;
    // C'tor TreeNode
    public TreeNode(String productName ,int amntSold) {
        this.amntSold = amntSold;
        this.productName = productName;
        left = null;
        right = null;
    }
}