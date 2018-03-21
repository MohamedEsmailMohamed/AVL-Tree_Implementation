package eg.edu.alexu.csd.filestructure.avl.cs53;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import eg.edu.alexu.csd.filestructure.avl.INode;

public class Main  <T extends Comparable<T>>{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyAvl v = new MyAvl<>();
		Main m = new Main<>();
		
		/*for (int i = 1; i <1000; ++i)
			v.insert(i);
		
		System.out.println(m.validateAVL(v.getTree()));
		*/
		
		/*m.testSearch(v);*/
		
		/*m.testDelete(v);*/
		
		/*m.testHeight(v);
		*/
		/*m.testSearchSmoke(v);*/
		
		
		/*int[] input = {13,8,5,9,4,6,12,2,1,3};
		// int[] height = {0,1,1,2,2,2,2,3,3,3};
		int[] height = {1,2,2,3,3,3,3,4,4,4};

		for (int i = 0; i < input.length; ++i) {
			v.insert(input[i]);
			System.out.println(v.height() == height[i]);
		}*/
		
		
		
		
		
		/*v.insert(13);
		v.insert(8);
		v.insert(5);
		v.insert(9);
		v.insert(4);
		v.insert(6);
		v.insert(12);
		v.insert(2);
		v.insert(1);
		v.insert(3);
				
		MyNode r =  v.root;
		System.out.println(r.getValue());
		//r = (MyNode) r.getLeftChild();
		System.out.println(r.getValue());
		r =  (MyNode) r.getRightChild();
		System.out.println(r.getValue());
		//r = (MyNode) r.getLeftChild();
		System.out.println(r.getValue());
		//r =  (MyNode) r.getLeftChild();
		System.out.println(r.getValue());
		r =  (MyNode) r.getRightChild();
		System.out.println(r.getValue());*/
		
	}
	public boolean validateAVL(INode<T> root) {
		return validateBST(root) && checkBalance(root);
	}

	public boolean validateBST(INode<T> root) {
		if (root == null)
			return true;

	ArrayList<T> inOrder = new ArrayList<> ();
	inOrderTraversal(root, inOrder);
	for (int i = 1; i < inOrder.size(); ++i) {
		T val = inOrder.get(i);
		T val2 = inOrder.get(i-1);
		if (val.compareTo(val2)< 0)
			return false;
		}
		return true;
	}

	private void inOrderTraversal(INode<T>  node, ArrayList<T>  list) {
		if (node == null)
			return;
		inOrderTraversal(node.getLeftChild(), list);
		list.add(node.getValue());
		inOrderTraversal(node.getRightChild(), list);
	}

		public boolean checkBalance(INode<T>  root) {
			return checkBalanceAux(root) != -1;
		}

		private int checkBalanceAux(INode<T>  node) {
			if (node == null)
				return 0;
			int left = checkBalanceAux(node.getLeftChild());
			int right = checkBalanceAux(node.getRightChild());
			if (left == -1 || right == -1)
				return -1;
			if (Math.abs(left - right)> 1) {
				return -1;
			}
			return 1 + Math.max(left, right);
		}
		
		
		public void testSearch(MyAvl avl) {
			int[] input = {13,8,5,9,4,6,12,2,1,3};
			int[] positive = {8,12,3};
			int[] negative = {0,11,20};

			for (int i = 0; i <input.length; ++i) {
				int value = input[i];
				avl.insert(value);
			}
			for (int q : positive)
				System.out.println(avl.search(q));
			for (int q : negative)
				System.out.println(avl.search(q));
		}
		
		public void testDelete(MyAvl avl) {
			int[] input = {13,8,5,9,4,6,12,2,1,3};
	
			for (int i = 0; i <input.length; ++i)
				avl.insert(input[i]);
			// try deleting non-existing elements
			for (int i = -1; i >= -5; --i) {
				System.out.println(avl.delete(i));
			}
			
			// check that the tree structure is not affected
			System.out.println(validateAVL(avl.getTree()));
			// delete all existing elements
			int[] deleteOrder = {8,4,2,12,9,13,5,3,1,6};
			for (int element : deleteOrder) {
				System.out.println(avl.delete(element));
				System.out.println(validateAVL(avl.getTree()));
			}
		}
		
		public void testHeight(MyAvl avl) {
			System.out.println(0 == avl.height());
			avl.insert(123);
			System.out.println(1 == avl.height());
			avl.insert(456);
			System.out.println(2 == avl.height());
			avl.delete(456);
			System.out.println(1 == avl.height());
			avl.delete(123);
			System.out.println(0 == avl.height());
			for(int i=0; i <1000; i++){
				avl.insert(55);
			}
			System.out.println(avl.height() ==1);
		}
		
		public void testSearchSmoke(MyAvl avl) {
			Set <Integer> elements = new HashSet <>();
			while(elements.size() < 1000){
				elements.add((int)(Math.random() * 100000));
			}
		/*	elements.add(97216);
			elements.add(15683);
			elements.add(60166);
			elements.add(72775);
			elements.add(70407);
			elements.add(48137);
			elements.add(40844);
			elements.add(85262);
			elements.add(90446);
			elements.add(28240);
			elements.add(67025);
			elements.add(15058);
			elements.add(23506);
			elements.add(79826);
			elements.add(41047);
			elements.add(82329);
			elements.add(88281);*/
			/*false
			0 , 27739
			1 , 93788
			2 , 87199
			3 , 20448
			4 , 27488
			5 , 70501
			6 , 50534
			7 , 85289
			8 , 17448
			9 , 87785
			10 , 40813
			11 , 79535
			12 , 22704
			13 , 21108
			14 , 93556
			15 , 52278
			16 , 32247
			17 , 69375*/

			int i = 0;
			int x = 0;
			for(Integer e : elements){
				i++;
				System.out.println(i + " , "+ e);
				//System.out.println(avl.search(e));
				avl.insert(e);
//				System.out.println(avl.height());
				if(!avl.search(e)){
					System.out.println(avl.search(e));
				    i = -1;
				    x = e;	
				}
				
			}
			if(i == -1){
				System.out.println(avl.search(x));
			}
		}
}
