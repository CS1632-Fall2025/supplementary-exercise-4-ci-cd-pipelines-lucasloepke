package edu.pitt.cs;

import org.mockito.Mockito;
import static org.mockito.Mockito.*; 

public interface RentACat {
	public static RentACat createInstance(InstanceType type) {
		switch (type) {
			case IMPL:
				return new RentACatImpl();
			case BUGGY:
				return new RentACatBuggy();
			case SOLUTION:
				return new RentACatSolution();
			case MOCK: {
				RentACat m = mock(RentACat.class);

				when(m.rentCat(anyInt())).thenReturn(true);
				when(m.returnCat(anyInt())).thenReturn(true);
				when(m.renameCat(anyInt(), anyString())).thenReturn(true);
				when(m.listCats()).thenReturn("");

				return m;
			}
			default:
				assert (false);
				return null;
		}
	}

	// WARNING: You are not allowed to change any part of the interface.
	// That means you cannot add any method nor modify any of these methods.

	public boolean returnCat(int id);

	public boolean rentCat(int id);

	public boolean renameCat(int id, String name);

	public String listCats();

	public void addCat(Cat c);
}
