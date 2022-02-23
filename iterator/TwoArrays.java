package iterator;

import java.util.Iterator;

public class TwoArrays implements Iterable<Integer> {
	private int UnionArr[];
	private int size;
	public TwoArrays(int[] a1, int[] a2)
	{
		int i,UnionCnt=0;
		size = a1.length+a2.length;
		UnionArr = new int[size];
		for(i=0; i<a1.length || i<a2.length ;i++)
		{
			if(i<a1.length) UnionArr[UnionCnt++] = a1[i];
			if(i<a2.length) UnionArr[UnionCnt++] = a2[i];
		}
	}


private class TwoArraysIt implements Iterator<Integer> {

		private int cnt = 0;			
		public boolean hasNext() 
		{
			if(cnt<size) return true; // Maybe there will be a problem here
			return false;
		}
	
		@Override
		public Integer next() {
			
			return UnionArr[cnt++];
		}
	}
	public Iterator<Integer> iterator() 
	{
		return new TwoArraysIt();
	}
}
