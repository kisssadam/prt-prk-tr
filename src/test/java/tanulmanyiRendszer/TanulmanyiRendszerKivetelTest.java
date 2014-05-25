package tanulmanyiRendszer;

import org.junit.Test;

public class TanulmanyiRendszerKivetelTest {

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest1() throws TanulmanyiRendszerKivetel {
		throw new TanulmanyiRendszerKivetel();
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest2() throws TanulmanyiRendszerKivetel {
		throw new TanulmanyiRendszerKivetel("üzenet");
	}
	
}
