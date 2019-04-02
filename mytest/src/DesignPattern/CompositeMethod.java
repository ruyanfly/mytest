package DesignPattern;

import java.util.Enumeration;
import java.util.Vector;

class TreeNode{
	private String name;
	private TreeNode parent;
	private Vector<TreeNode> children=new Vector<TreeNode>();
	public TreeNode(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public TreeNode getParent(){
		return parent;
	}
	public void setParent(TreeNode parent){
		this.parent=parent;
	}
	public void add(TreeNode node){
		children.add(node);
	}
	public void remove(TreeNode node){
		children.remove(node);
	}
	public Enumeration<TreeNode> getChildren(){
		return children.elements();
	}
}

public class CompositeMethod {
	TreeNode root=null;
	public CompositeMethod(String name){
		root=new TreeNode(name);
	}
	public static void main(String[] args){
		CompositeMethod tree=new CompositeMethod("A");
		TreeNode nodeB=new TreeNode("B");
		TreeNode nodeC=new TreeNode("C");
		nodeB.add(nodeC);
		tree.root.add(nodeB);
		System.out.println("build the tree finished!");
	}
}
