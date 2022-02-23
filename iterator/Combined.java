package iterator;

import java.util.Iterator;

public class Combined<E> implements Iterable<E> 
{
	private Iterable<E> first;
	private Iterable<E> second;
	
	public Combined(Iterable<E> first, Iterable<E> second) 
	{
		this.first = first;
		this.second = second;
	}
	
	
	private class CombinedIt implements Iterator<E>{
		int cnt=0;
		
		private Iterator<E> F = first.iterator();
		private Iterator<E> S = second.iterator();
		@Override
		public boolean hasNext() {
			return (F.hasNext() || S.hasNext());

		}

		@Override
		public E next() 
		{
			if(cnt%2==0) 
			{
				cnt++;
				if(F.hasNext())
					return F.next();
				return S.next();
			}
			cnt++;
			if(S.hasNext())
				return S.next();
			return F.next();
		}
		
	}
	
	
	
	
	@Override
	public Iterator<E> iterator() {
		
		return new CombinedIt();
	}

}
