package com.devchao.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

	public static void main(String[] args) {
		
		Node n15 = new Node(15, null, null);
		Node n14 = new Node(14, n15, null);
		Node n13 = new Node(13, null, n14);
		Node n12 = new Node(12, n13, null);
		Node n11 = new Node(11, null, n12);
		
		Node n5 = new Node(5, null, null);
		Node n6 = new Node(6, null, null);
		Node n8 = new Node(8, null, null);
		Node n9 = new Node(9, n11, null);
		Node n10 = new Node(10, null, null);
		Node n7 = new Node(7, n8, null);
		Node n4 = new Node(4, n6, n7);
		Node n3 = new Node(3, null, n5);
		Node n2 = new Node(2, n9, n10);
		Node n1 = new Node(1, n3, n4);
		Node root = new Node(0, n1, n2);
		System.out.println(depth3(root));
	}
	
	public static int depth1(Node root){
		int dep1 = 1, dep2 = 1;
		if(root.left != null) {
			dep1 = dep1 + depth1(root.left);
		}
		if(root.right != null) {
			dep2 = dep2 + depth1(root.right);
		}
		
		System.out.println(root.data + " " + dep1 + " " + dep2);
		return dep1 >= dep2 ? dep1 : dep2;
	}
	
	public static int depth2(Node root){
		int dep = 1, tempDep = 1;
		Stack<Node> q = new Stack<Node>();
		Stack<Integer> dq = new Stack<Integer>();
		Node node = root;
		
		while(node != null || !q.isEmpty()) {
			if(node == null) {
				node = q.pop();
				dep = dep < tempDep ? tempDep : dep;
				tempDep = dq.pop();
			}
			if(node.right != null) {
				dq.add(tempDep + 1);
				q.add(node.right);
			}
			
			if(node.left != null) {
				tempDep = tempDep + 1;
			} 
			node = node.left;
		}
		return dep;
	}
	
	public static int depth3(Node root){
		int dep = 0, tempTag = 0;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		
		Queue<Integer> tagQueue = new LinkedList<>();
		tagQueue.add(1);
		
		while(!queue.isEmpty()) {
			root = queue.poll();
			if(root.left != null) {
				queue.add(root.left);
				tagQueue.add(0);
			}
			
			if(root.right != null) {
				queue.add(root.right);
				tagQueue.add(0);
			}
			
			tempTag = tagQueue.poll();
			if(tempTag == 1) {
				dep = dep + 1;
				if(!tagQueue.isEmpty()) {
					tagQueue.remove();
					tagQueue.add(1);
				}
			}
		}
		return dep;
	}
	
	
	
	static class Node {

		public int data;
		public Node left;
		public Node right;

		public Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
}

