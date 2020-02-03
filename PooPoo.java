import java.util.*;
import java.io.*;



public class PooPoo
{
// private variable accessible to all methods
private static BinaryTreeNode<String> root=null;
private static BinaryTreeNode<String> root2=null;
private static BinaryTreeNode<String> root3=null;
private static BinaryTreeNode<String> node=null;
private static BinaryTreeNode<String> node2=null;
private static BinaryTreeNode<String> node3=null;
private static final boolean DEBUG=false;

public static void main(String[] args) 
{
System.out.println("Number of arguments: " + args.length);
if(args.length!=1 && args.length!=3)
  {
  System.out.println("\nUsage: 1 or 3 arguments required\n");
  System.out.println("1 argument: java PooPoo <listn.txt> for n=0:6");
  System.out.println("Example: java PooPoo list0.txt\n");
  System.out.println("3 arguments: java PooPoo <listn.txt> <listm.txt> <root string> ");
  System.out.println("for n,m=0:6, n,m can be the same");
  System.out.println("Example: java PooPoo list0.txt list1.txt \"Q\"");
  System.out.println("\n");
  }

if(args.length==1 || args.length==3)
{
String listFileName = args[0];
// Create listReader Object
InStringFile listReader=new InStringFile(listFileName);
StringTokenizer tokenizer;
String line = listReader.read();
tokenizer=new StringTokenizer(line);
int numTokens=tokenizer.countTokens();
for(int i=0;i<numTokens;i++)
      {
      String element=tokenizer.nextToken();
      if(DEBUG) System.out.println("element read: " + element);
      node=new BinaryTreeNode<String>(element);
      root=insertInBinaryTree(root,node);
      }
listReader.close();

int n=countBinaryTreeNodes(root);
System.out.println("\n\nNumber of nodes in the tree:" + n);
int h=findHeight(root);
System.out.format("Height of tree: %d\n",h);
System.out.format("Number of nodes in complete binary tree with height %d: %d\n",
                   h,(int) (Math.pow(2.0,h+1.0)-1));
PrettyPrintTree.printNode(root);
System.out.println("inOrder traversal of tree: " + inOrder(root,""));
System.out.println("preOrder traversal of tree: " + preOrder(root,""));
System.out.println("postOrder traversal of tree: " + postOrder(root,""));
System.out.println("levelOrder traversal of tree: " + levelOrder(root,""));
}

if(args.length==3) // args.length==1 cose has already run
{
String listFileName = args[1];
String rootString = args[2];
// Create listReader Object
InStringFile listReader=new InStringFile(listFileName);
StringTokenizer tokenizer;
String line = listReader.read();
tokenizer=new StringTokenizer(line);
int numTokens=tokenizer.countTokens();
for(int i=0;i<numTokens;i++)
      {
      String element=tokenizer.nextToken();
      if(DEBUG) System.out.println("element read: " + element);
      node2=new BinaryTreeNode<String>(element);
      root2=insertInBinaryTree(root2,node2);
      }
listReader.close();

int n2=countBinaryTreeNodes(root2);
System.out.println("\n\nNumber of nodes in the tree:" + n2);
int h2=findHeight(root2);
System.out.format("Height of tree: %d\n",h2);
System.out.format("Number of nodes in complete binary tree with height %d: %d\n",
                   h2,(int) (Math.pow(2.0,h2+1.0)-1));
PrettyPrintTree.printNode(root2);
System.out.println("inOrder traversal of tree: " + inOrder(root2,""));
System.out.println("preOrder traversal of tree: " + preOrder(root2,""));
System.out.println("postOrder traversal of tree: " + postOrder(root2,""));
System.out.println("levelOrder traversal of tree: " + levelOrder(root2,""));

System.out.println("\n\n");
System.out.println("-----------------------------------------------------");
System.out.println(" 1st and 2nd trees become the left and right subtrees");
System.out.println(" of the 3rd tree with root labeled " + rootString);
System.out.println("-----------------------------------------------------");
System.out.println("The value of rootString is " + rootString);
root3=joinTrees(root,root2,rootString);
int n3=countBinaryTreeNodes(root3); // could compute n+n2+1
System.out.println("Number of nodes in the tree:" + n3);
int h3=findHeight(root3);
System.out.format("Height of tree: %d\n",h3); // could compute as max(h,h2)+1
System.out.format("Number of nodes in complete binary tree with height %d: %d\n",
                   h3,(int) (Math.pow(2.0,h3+1.0)-1)); 
PrettyPrintTree.printNode(root3);
System.out.println("inOrder traversal of tree: " + inOrder(root3,""));
System.out.println("preOrder traversal of tree: " + preOrder(root3,""));
System.out.println("postOrder traversal of tree: " + postOrder(root3,""));
System.out.println("levelOrder traversal of tree: " + levelOrder(root3,""));
} // if(args.length==4)

} // main

////////////////////////////////////////////////////
// Join 2 binary trees into a 3D binary tree
// The leftsubtree is root, the right subtree is root2
// and the new root's label is rootString
////////////////////////////////////////////////////
public static BinaryTreeNode<String> joinTrees(BinaryTreeNode<String> leftSubtree,
                                               BinaryTreeNode<String> rightSubtree,
                                               String rootString)
{
root=new BinaryTreeNode<String>(rootString);
 // YOUR CODE HERE

	/**
	 * With the root we are given, the left sub tree is set to the left sub tree we are given
	 */

root.setLeft(leftSubtree);

	/**
	 * With the root we are give, the right sub tree is set to the right sub tree we are given
	 */

root.setRight(rightSubtree);
return root;
}

////////////////////////////////////////////////////
// insert a BinaryTreeNode<String> node in a binary tree
// rooted by BinaryTreeNode<String> root
////////////////////////////////////////////////////
public static BinaryTreeNode<String> insertInBinaryTree(BinaryTreeNode<String> root,
                                                        BinaryTreeNode<String> node) 
{
if(root==null) 
{
root=node;
if(DEBUG) System.out.format("\nroot set to node: %s\n",root.getElement());
return(root);
}
else 
  if(node.getElement().compareTo(root.getElement())<=0)
    {
    if(DEBUG) 
       System.out.format("\n%s <= %s using root.left\n",
       node.getElement(),root.getElement());
    root.setLeft(insertInBinaryTree(root.getLeft(),node));
    }
  else
    {
    if(DEBUG) 
       System.out.format("\n%s > %s using root.right\n",
       node.getElement(),root.getElement());
    root.setRight(insertInBinaryTree(root.getRight(),node));
    }
return(root);
}

////////////////////////////////////////////////////
// The height of a tree is the length of the path from 
// the root to the deepest node in the tree
// An empty tree has height -1
// A tree with just 1 node (the root) has height 0
////////////////////////////////////////////////////
public static int findHeight(BinaryTreeNode<String> node)
{
if(node==null) return -1;

	/**
	 * Using recursion, the nodes are counted on the very left side of the tree
	 */

int leftHeight = findHeight(node.getLeft());

	/**
	 * Using recursion, the nodes are counted on the very right side of the tree
	 */

int rightHeight = findHeight(node.getRight());

	/**
	 * This checks to see if the left side is grater in height than the right side
	 * If so, the left height plus one is returned
	 * The one added is for the root
	 */

if (leftHeight > rightHeight){
	return leftHeight + 1;
}
else{
	
	/**
	 * In case the right height is grater than the left side, the right side plus one is returned 
	 * Again, the one is for the root
	 */
	
		return rightHeight + 1;
	}
}


// YOUR CODE HERE


////////////////////////////////////////////////////
// The number of nodes in a binary tree is the 
// number of nodes in the root's left subtree
// plus the number of nodes in its right subtree 
// plus one (for the root itself).
////////////////////////////////////////////////////
public static int countBinaryTreeNodes(BinaryTreeNode<String> root)
{
if(root==null) return(0);
// YOUR CODE HERE

	/**
	 * Using recursion, this method scales through the tree
	 * while it is scaling through, all the nodes are coutned
	 */

return 1 + countBinaryTreeNodes(root.getLeft()) + countBinaryTreeNodes(root.getRight());
}
////////////////////////////////////////////////////
// inOrder travesal of binary tree, stg contains
// the node in inOrder order.
////////////////////////////////////////////////////
public static String inOrder(BinaryTreeNode<String> root,String stg)
{
if(root!=null) 
  {
  // YOUR CODE HERE
	
	/**
	 * A string is made that traverses through the binary tree given
	 * This method used inOrder while it is traversing through the given tree and concatenates in one string
	 */
	
	stg = stg + inOrder(root.getLeft(), stg) + root.getElement() + " " + inOrder(root.getRight(), stg);
  }
return stg;
} // inOrder

////////////////////////////////////////////////////
// preOrder travesal of binary tree, stg contains
// the node in preOrder order.
////////////////////////////////////////////////////
public static String preOrder(BinaryTreeNode<String> root,String stg)
{
if(root!=null) 
  {
  // YOUR CODE HERE
	
	/**
	 * A string is made that traverses through the binary tree given
	 * This method used preOrder while it is traversing through the given tree and concatenates in one string
	 */
	
	stg = stg + root.getElement() + " " + preOrder(root.getLeft(), stg) + preOrder(root.getRight(), stg);
  } 
return stg;
} // preOrder

////////////////////////////////////////////////////
// postOrder travesal of binary tree, stg contains
// the node in postOrder order.
////////////////////////////////////////////////////
public static String postOrder(BinaryTreeNode<String> root,String stg)
{
if(root!=null) 
  {
  // YOUR CODE HERE

	/**
	 * A string is made that traverses through the binary tree given 
	 * This method uses postOrder while it is traversing through the given tree and concatenates in one string
	**/
	
	stg = stg + postOrder(root.getLeft(), stg) + postOrder(root.getRight(), stg) + root.getElement() + " ";
}
return stg;
}
 // postOrder

////////////////////////////////////////////////////
// levelOrder travesal of binary tree, stg contains
// the node in levelOrder order.
////////////////////////////////////////////////////
public static String levelOrder(BinaryTreeNode<String> root,String stg) 
{ 
if(root==null) return stg;
LinkedQueue<BinaryTreeNode<String>> queue=new LinkedQueue<BinaryTreeNode<String>>(); 
queue.enqueue(root);
while(!queue.isEmpty()) 
    {
    BinaryTreeNode<String> node = queue.dequeue();
    stg=stg+node.getElement() +  " ";
    if(node.getLeft()!=null) queue.enqueue(node.getLeft());
    if(node.getRight()!=null) queue.enqueue(node.getRight());
    } 
return stg;
}

} // PooPoo
