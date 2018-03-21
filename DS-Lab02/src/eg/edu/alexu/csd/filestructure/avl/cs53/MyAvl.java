package eg.edu.alexu.csd.filestructure.avl.cs53;

import java.util.Stack;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class MyAvl<T extends Comparable<T>> implements IAVLTree<T> {

	MyNode<T> root = new MyNode<>();
	int size = 0;
	Stack<MyNode<T>> stack = new Stack<>();

	@Override
	public void insert(T key) {
		if (size == 0) {
			root.setValue(key);
			root.height = 1;
		} else {
			boolean found = false;
			MyNode<T> node = root;
			while (!found) {
				if (key.compareTo(node.getValue()) < 0) {
					stack.push(node);
					if (node.getLeftChild() != null) {
						node = (MyNode<T>) node.getLeftChild();
					} else {
						found = true;
						MyNode<T> temp = new MyNode<>();
						temp.setValue(key);
						temp.height = 1;
						temp.setParent(node);
						temp.setParentLeft(true);
						node.setLeftChild(temp);
					}
				} else if (key.compareTo(node.getValue()) > 0) {
					stack.push(node);

					if (node.getRightChild() != null) {
						node = (MyNode<T>) node.getRightChild();
					} else {
						found = true;
						MyNode<T> temp = new MyNode<>();
						temp.setValue(key);
						temp.height = 1;
						temp.setParent(node);
						temp.setParentLeft(false);
						node.setRightChild(temp);
					}
				}else{
					found = true;
				}
			}
			balance();
		}
		size++;
	}

	private void balance() {
		while (!stack.isEmpty()) {
			MyNode<T> node = stack.pop();
			int leftH = 0;
			int rightH = 0;
			if(node.getLeftChild() != null){
				leftH = ((MyNode<T>) node.getLeftChild()).height;
			}			
			if(node.getRightChild() != null){			
				rightH = ((MyNode<T>) node.getRightChild()).height;
			}
			
			
			if(Math.abs(leftH- rightH) >= 2){
				if(leftH > rightH){
					int LeftLeftH = 0;
					int LeftRightH = 0;
					if(leftH != 0 && node.getLeftChild().getLeftChild() != null){
						LeftLeftH = ((MyNode<T>) node.getLeftChild().getLeftChild()).height;
					}			
					if(leftH != 0 && node.getLeftChild().getRightChild() != null){			
						LeftRightH = ((MyNode<T>) node.getLeftChild().getRightChild()).height;
					}
					if(LeftLeftH >= LeftRightH){
						SingleRightRotate(node);
					}else{
						DoubleRightRotate(node);
					}
				}else{
					int RightLeftH = 0;
					int RightRightH = 0;
					if(rightH != 0 && node.getRightChild().getLeftChild() != null){
						RightLeftH = ((MyNode<T>) node.getRightChild().getLeftChild()).height;
					}			
					if(rightH != 0 && node.getRightChild().getRightChild() != null){			
						RightRightH = ((MyNode<T>) node.getRightChild().getRightChild()).height;
					}
					if(RightRightH >= RightLeftH){
						SingleLeftRotate(node);
					}else{
						DoubleLeftRotate(node);
					}
				}				
			}else{
				if(leftH > rightH){
					node.height = leftH+1;				
				}else{
					node.height = rightH+1;
				}
			}				
		}
	}

	private void SingleRightRotate(MyNode<T> node) {
		MyNode<T> leftNode = (MyNode<T>) node.getLeftChild();
		MyNode<T> leftRightNode = (MyNode<T>) leftNode.getRightChild();		
		
		leftNode.setParent((MyNode<T>) node.getParent());
		
		if(node.getParent() != null){
			if(node.isParentLeft()){
				((MyNode<T>) leftNode.getParent()).setLeftChild(leftNode);
				leftNode.setParentLeft(true);
			}else{
				((MyNode<T>) leftNode.getParent()).setRightChild(leftNode);
				leftNode.setParentLeft(false);
			}
		}else{
			root = leftNode;
		}
		
		leftNode.setRightChild(node);				
		node.setParent(leftNode);
		node.setParentLeft(false);
		
		
		node.setLeftChild(leftRightNode);
		if(leftRightNode != null){
			leftRightNode.setParent(node);
			leftRightNode.setParentLeft(true);
		}		
		setHeight(node);
		setHeight(leftNode);
	}

	private void setHeight(MyNode<T> node) {
		int leftH = 0;
		int rightH = 0;
		if(node.getLeftChild() != null){
			leftH = ((MyNode<T>) node.getLeftChild()).height;
		}			
		if(node.getRightChild() != null){			
			rightH = ((MyNode<T>) node.getRightChild()).height;
		}
		if(leftH > rightH){
			node.height = leftH+1;				
		}else{
			node.height = rightH+1;
		}
		
	}

	private void SingleLeftRotate(MyNode<T> node) {
		MyNode<T> rightNode = (MyNode<T>) node.getRightChild();
		MyNode<T> rightLeftNode = (MyNode<T>) rightNode.getLeftChild();		
		
		rightNode.setParent((MyNode<T>) node.getParent());
		
		if(node.getParent() != null){
			if(node.isParentLeft()){
				((MyNode<T>) rightNode.getParent()).setLeftChild(rightNode);
				rightNode.setParentLeft(true);
			}else{
				((MyNode<T>) rightNode.getParent()).setRightChild(rightNode);
				rightNode.setParentLeft(false);
			}
		}else{
			root = rightNode;
		}
		
		rightNode.setLeftChild(node);				
		node.setParent(rightNode);
		node.setParentLeft(true);
		
		
		node.setRightChild(rightLeftNode);
		if(rightLeftNode != null){
			rightLeftNode.setParent(node);
			rightLeftNode.setParentLeft(false);
		}		
		setHeight(node);
		setHeight(rightNode);
	}

	private void DoubleLeftRotate(MyNode<T> node) {
		SingleRightRotate((MyNode<T>) node.getRightChild());
		SingleLeftRotate(node);
	}

	private void DoubleRightRotate(MyNode<T> node) {
		SingleLeftRotate((MyNode<T>) node.getLeftChild());
		SingleRightRotate(node);		
	}

	@Override
	public boolean delete(T key) {
		if (size == 0) {
			return false;
		} else {
			boolean found = false;
			MyNode<T> node = root;
			while (!found) {
				if (key.compareTo(node.getValue()) < 0) {
					stack.push(node);
					if (node.getLeftChild() != null) {
						node = (MyNode<T>) node.getLeftChild();
					} else {
						found = true;
						stack.removeAllElements();
						return false;
					}
				} else if(key.compareTo(node.getValue()) > 0){
					stack.push(node);
					if (node.getRightChild() != null) {
						node = (MyNode<T>) node.getRightChild();
					} else {
						found = true;
						stack.removeAllElements();
						return false;
					}
				} else{
					MyNode<T> deletedNode = node;
					if(node.getLeftChild()!= null){
						stack.push(node);
						node = (MyNode<T>) node.getLeftChild();
						while(node.getRightChild() != null){
							stack.push(node);
							node = (MyNode<T>)node.getRightChild();
						}
						
						deletedNode.setValue(node.getValue());
						
						if(node.getParent() != null){
							if(node.isParentLeft()){
								((MyNode<T>)node.getParent()).setLeftChild(null);
							}else {
								((MyNode<T>)node.getParent()).setRightChild(null);
							}
						}else{//root
							root = new MyNode<>();
						}
					}else if(node.getRightChild()!= null){
						stack.push(node);
						node = (MyNode<T>) node.getRightChild();
						while(node.getLeftChild() != null){
							stack.push(node);
							node = (MyNode<T>)node.getLeftChild();
						}
						deletedNode.setValue(node.getValue());
						if(node.getParent() != null){
							if(node.isParentLeft()){
								((MyNode<T>)node.getParent()).setLeftChild(null);
							}else {
								((MyNode<T>)node.getParent()).setRightChild(null);
							}
						}else{//root
							root = new MyNode<>();
						}
					}else{
						if(node.getParent() != null){
							if(node.isParentLeft()){
								((MyNode<T>)node.getParent()).setLeftChild(null);
							}else {
								((MyNode<T>)node.getParent()).setRightChild(null);
							}
						}else{//root
							root = new MyNode<>();
						}
					}
					balance();
					size--;
					return true;
				}
			}
			return false;			
		}		
	}

	@Override
	public boolean search(T key) {
		if (size == 0) {
			return false;
		} else {
			boolean found = false;
			MyNode<T> node = root;
			while (!found) {
				if (key.compareTo(node.getValue()) < 0) {
					if (node.getLeftChild() != null) {
						node = (MyNode<T>) node.getLeftChild();
					} else {
						found = true;
						return false;
					}
				} else if(key.compareTo(node.getValue()) > 0){
					if (node.getRightChild() != null) {
						node = (MyNode<T>) node.getRightChild();
					} else {
						found = true;
						return false;
					}
				} else{
					return true;
				}
			}
			return false;			
		}
		//return false;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return root.height;
	}

	@Override
	public INode<T> getTree() {
		// TODO Auto-generated method stub
		return  root;
	}

}
