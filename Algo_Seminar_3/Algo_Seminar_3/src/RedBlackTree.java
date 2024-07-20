class RedBlackNode{
    RedBlackNode leftChild, rightChild;
    int element;
    int color;

    //constructor to set the value of a node having no left and right child
    public RedBlackNode(int element){
        this( element, null, null );
    }

    //constructor to set value of element, leftChild, rightChild and color
    public RedBlackNode(int element, RedBlackNode leftChild, RedBlackNode rightChild){
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        color = 1;
    }
}

class RedBlackTree{
    private static RedBlackNode nullNode;   //define null node
    private RedBlackNode current;   //define current node
    private RedBlackNode parent;    //define parent node
    private RedBlackNode header;   // define header node
    private RedBlackNode grand; //define grand node
    private RedBlackNode great; //define great node

    //create two variables, i.e., RED and Black for color and the values of these variables are 0 and 1 respectively.
    static final int RED   = 0;
    static final int BLACK = 1;

    // using static initializer for initializing null Node
    static
    {
        nullNode = new RedBlackNode(0);
        nullNode.leftChild = nullNode;
        nullNode.rightChild = nullNode;
    }


    // Constructor for creating header node
    public RedBlackTree(int header){
        this.header = new RedBlackNode(header);
        this.header.leftChild = nullNode;
        this.header.rightChild = nullNode;
    }

    // delete all nodes
    public void removeAll(){
        header.rightChild = nullNode;
    }

    // check whether the tree is empty or not
    public boolean checkEmpty(){
        return header.rightChild == nullNode;
    }

    // add a new node in the red black tree
    public void insertNewNode(int newElement ){
        current = parent = grand = header;      //set header value to current, parent, and grand node
        nullNode.element = newElement;          //set newElement to the element of the null node

        //repeat statements until the element of the current node is not equal to the value of the newElement
        while (current.element != newElement){
            great = grand;
            grand = parent;
            parent = current;

            //if the value of the newElement is lesser than the current node element, the current node will point to the current left child else point to the current right child.
            current = newElement < current.element ? current.leftChild : current.rightChild;

            // Check whether both the children are RED or NOT. If both the children are RED change them by using handleColors() method
            if (current.leftChild.color == RED && current.rightChild.color == RED)
                handleColors( newElement );
        }

        // insertion of the new node will be fail if a duplicate is already present in the tree
        if (current != nullNode)
            return;

        //create a node having no left and right child and pass it to the current node
        current = new RedBlackNode(newElement, nullNode, nullNode);

        //connect the current node with the parent
        if (newElement < parent.element)
            parent.leftChild = current;
        else
            parent.rightChild = current;
        handleColors( newElement );
    }

    // maintain the colors of Red-black tree nodes
    private void handleColors(int newElement){
        // flip the colors of the node
        current.color = RED;    //make current node RED
        current.leftChild.color = BLACK;    //make leftChild BLACK
        current.rightChild.color = BLACK;   //make rightChild BLACK

        //check the color of the parent node
        if (parent.color == RED)
        {
            // perform rotation in case when the color of parent node is RED
            grand.color = RED;

            if (newElement < grand.element && grand.element != newElement && newElement < parent.element)
                parent = performRotation( newElement, grand );  // Start dbl rotate
            current = performRotation(newElement, great );
            current.color = BLACK;
        }

        // change the color of the root node with BLACK
        header.rightChild.color = BLACK;
    }

    // perform double rotation
    private RedBlackNode performRotation(int newElement, RedBlackNode parent){
        //check whether the value of the newElement is lesser than the element of the parent node or not
        if(newElement < parent.element)
            //if true, perform the rotation with the left child and right child based on condition and set return value to the left child of the parent node
            return parent.leftChild = newElement < parent.leftChild.element ? rotationWithLeftChild(parent.leftChild) : rotationWithRightChild(parent.leftChild) ;
        else
            //if false, perform the rotation with the left child and right child based on condition and set return value to the right child of the parent node
            return parent.rightChild = newElement < parent.rightChild.element ? rotationWithLeftChild(parent.rightChild) : rotationWithRightChild(parent.rightChild);
    }

    // rotate node with left child
    private RedBlackNode rotationWithLeftChild(RedBlackNode node2){
        RedBlackNode node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        return node1;
    }

    // rotate node with right child
    private RedBlackNode rotationWithRightChild(RedBlackNode node1){
        RedBlackNode node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1.leftChild;
        return node2;
    }

    // get total number of nodes in tree
    public int nodesInTree(){
        return nodesInTree(header.rightChild);
    }
    private int nodesInTree(RedBlackNode node){
        if (node == nullNode)
            return 0;
        else
        {
            int size = 1;
            size = size + nodesInTree(node.leftChild);
            size = size + nodesInTree(node.rightChild);
            return size;
        }
    }
    // get desired node from the Red-Black tree
    public boolean searchNode(int value)
    {
        return searchNode(header.rightChild, value);
    }
    private boolean searchNode(RedBlackNode node, int value)
    {
        boolean check = false;
        while ((node != nullNode) && check != true)
        {
            int nodeValue = node.element;
            if (value < nodeValue)
                node = node.leftChild;
            else if (value > nodeValue)
                node = node.rightChild;
            else
            {
                check = true;
                break;
            }
            check = searchNode(node, value);
        }
        return check;
    }

    // inorder traversal
    public void preorderTraversal()
    {
        preorderTraversal(header.rightChild);
    }
    private void preorderTraversal(RedBlackNode node)
    {
        if (node != nullNode)
        {
            char c = 'R';
            if (node.color == 1)
                c = 'B';
            System.out.print(node.element +""+c+" ");
            preorderTraversal(node.leftChild);
            preorderTraversal(node.rightChild);
        }
    }

    // inorder traversal
    public void inorderTraversal()
    {
        inorderTraversal(header.rightChild);
    }
    private void inorderTraversal(RedBlackNode node)
    {
        if (node != nullNode)
        {
            inorderTraversal(node.leftChild);
            char c = 'R';
            if (node.color == 1)
                c = 'B';
            System.out.print(node.element +""+c+" ");
            inorderTraversal(node.rightChild);
        }
    }

    // postorder traversal
    public void postorderTraversal()
    {
        postorderTraversal(header.rightChild);
    }
    private void postorderTraversal(RedBlackNode node)
    {
        if (node != nullNode)
        {
            postorderTraversal(node.leftChild);
            postorderTraversal(node.rightChild);
            char c = 'R';
            if (node.color == 1)
                c = 'B';
            System.out.print(node.element +""+c+" ");
        }
    }
}  