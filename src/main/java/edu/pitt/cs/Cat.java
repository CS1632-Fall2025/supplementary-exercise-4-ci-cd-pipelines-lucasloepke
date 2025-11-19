package edu.pitt.cs;

import org.mockito.Mockito;
import static org.mockito.Mockito.*; 

public interface Cat {
	public static Cat createInstance(InstanceType type, int id, String name) {
		switch (type) {
			case IMPL:
				return new CatImpl(id, name);
			case BUGGY:
				return new CatBuggy(id, name);
			case SOLUTION:
				return new CatSolution(id, name);
			case MOCK: {
				Cat m = mock(Cat.class);
				final String[] nm = { name };
				final boolean[] rented = { false };

				when(m.getId()).thenReturn(id);
				when(m.getName()).thenAnswer(inv -> nm[0]);
				when(m.getRented()).thenAnswer(inv -> rented[0]);
				when(m.toString()).thenAnswer(inv -> "ID " + id + ". " + nm[0]);

				doAnswer(inv -> { rented[0] = true; return null; }).when(m).rentCat();
				doAnswer(inv -> { rented[0] = false; return null; }).when(m).returnCat();
				doAnswer(inv -> { nm[0] = inv.getArgument(0, String.class); return null; })
					.when(m).renameCat(Mockito.anyString());

				return m;
			}
			default:
				assert(false);
				return null;
		}
	}

	// WARNING: You are not allowed to change any part of the interface.
	// That means you cannot add any method nor modify any of these methods.
	
	public void rentCat();

	public void returnCat();

	public void renameCat(String name);

	public String getName();

	public int getId();

	public boolean getRented();

	public String toString();
}
