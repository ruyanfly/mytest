package DesignPattern;

interface Collection{
	public Iterator iterator();
	public Object get(int i);
	public int size();
}

interface Iterator{
	public Object previous();
	public Object next();
	public boolean hasNext();
	public Object first();
}

class MyCollection implements Collection{
	public String string[]={"A","B","C","D","E"};
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new MyIterator(this);
	}
	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return string[i];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return string.length;
	}
}

class MyIterator implements Iterator{
	private Collection collection;
	private int pos=-1;
	public MyIterator(Collection collection){
		this.collection=collection;
	}
	@Override
	public Object previous() {
		// TODO Auto-generated method stub
		if(pos>0){
			pos--;
		}
		return collection.get(pos);
	}
	@Override
	public Object next() {
		// TODO Auto-generated method stub
		if(pos<collection.size()-1){
			pos++;
		}
		return collection.get(pos);
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(pos<collection.size()-1){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public Object first() {
		// TODO Auto-generated method stub
		pos=0;
		return collection.get(pos);
	}
}

public class IteratorMethod {
	public static void main(String[] args){
		Collection collection=new MyCollection();
		Iterator it=collection.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
