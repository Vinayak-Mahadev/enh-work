package enh.team.interfaces.ds;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class ExampleQueue implements Queue<Integer>
{
	BlockingQueue<Object> e;
	private List<Integer> integerList;

	public ExampleQueue() {}

	public ExampleQueue(List<Integer> integerList) {
		super();
		this.integerList = integerList;
	}

	public List<Integer> getIntegerList() {
		return integerList;
	}

	public void setIntegerList(List<Integer> integerList) {
		this.integerList = integerList;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return integerList.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return integerList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return integerList.contains(o);
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return integerList.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return integerList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return integerList.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return integerList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return integerList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		// TODO Auto-generated method stub
		return integerList.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return integerList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return integerList.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		integerList.clear();
	}

	@Override
	public boolean add(Integer e) {
		// TODO Auto-generated method stub
		return integerList.add(e);
	}

	@Override
	public boolean offer(Integer e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer peek() {
		// TODO Auto-generated method stub
		return null;
	}


}
