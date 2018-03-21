package eg.edu.alexu.csd.filestructure.avl.cs53;

import eg.edu.alexu.csd.filestructure.avl.INode;

public class MyNode<T extends Comparable<T>> implements INode<T> {
	private MyNode<T> left = null;
	private MyNode<T> right = null;
	private MyNode<T> parent = null;
	private boolean isParentLeft ;
	int height = 0;
	T val ;
	
	public void setLeftChild(MyNode<T> l) {
		left = l;
	}

	
	public void setRightChild(MyNode<T> r) {
		right = r;
	}

	
	public void setParent(MyNode<T> p) {
		parent = p;
	}

	
	
	@Override
	public INode<T> getLeftChild() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public INode<T> getRightChild() {
		// TODO Auto-generated method stub
		return right;
	}

	public INode<T> getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public T getValue() {
		// TODO Auto-generated method stub
		return val;
	}

	@Override
	public void setValue(T value) {
		val = value;
		// TODO Auto-generated method stub
		
	}


	public boolean isParentLeft() {
		return isParentLeft;
	}


	public void setParentLeft(boolean isParentLeft) {
		this.isParentLeft = isParentLeft;
	}

}
