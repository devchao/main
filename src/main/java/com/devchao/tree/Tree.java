package com.devchao.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Tree {

	public static void main(String[] args) {
		Node n8 = new Node(8);
		Node n7 = new Node(7);
		Node n6 = new Node(6);
		Node n5 = new Node(5, n6, n7, n8);
		Node n4 = new Node(4);
		Node n3 = new Node(3);
		Node n2 = new Node(2);
		Node n1 = new Node(1, n2, n3, n4);
		Node top = new Node(0, n1, n5);
		test(top);
	}

	public static void test(Node top) {
		if (top != null) {
			System.out.println(top.data);//����
			Node temp = null;
			while ((temp = top.nextChild()) != null) {
				test(temp);
			}
		}
	}

	public static void test2(Node top) {
		Queue<Node> q = new LinkedList<>();
		q.add(top);

		Node temp = null;
		while (!q.isEmpty()) {
			temp = q.poll();
			System.out.println(temp.data);
			for (int i = 0; i < temp.childs.size(); i++) {
				q.add(temp.childs.get(i));
			}
		}
	}

	public static void test3(Node top) {
		Stack<Node> q = new Stack<Node>();
		q.push(top);

		Node temp = null;
		while (!q.isEmpty()) {
			temp = q.pop();
			System.out.println(temp.data);
			for (int i = temp.childs.size() - 1; i >= 0; i--) {
				q.push(temp.childs.get(i));
			}
		}
	}
	
	
	
	static class Node {

		public int data;
		public List<Node> childs;

		private int i = 0;
		private int clen = 0;

		public Node(int data, Node... child) {
			this.data = data;
			childs = Arrays.asList(child);
			clen = childs.size();
		}

		public Node nextChild() {
			if (clen == 0 || i >= clen) {
				return null;
			}
			return childs.get(i++);
		}

	}
}

