package click.tomasz.network.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NetworkParametersTest {

	NetworkParameters objectUnderTest;

	@Test
	public void can_get_layers_size(){
		objectUnderTest = new NetworkParameters();
		objectUnderTest.setInputSize(10);
		objectUnderTest.getInnerLayersSize().add(8);
		objectUnderTest.getInnerLayersSize().add(6);
		objectUnderTest.getInnerLayersSize().add(4);
		objectUnderTest.setResultSize(2);

		int[] result = objectUnderTest.getLayersSize();
		assertEquals(10, result[0]);
		assertEquals(8, result[1]);
		assertEquals(6, result[2]);
		assertEquals(4, result[3]);
		assertEquals(2, result[4]);
	}

}