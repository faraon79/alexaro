package click.tomasz.network.learning;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeacherConfiguratorTest {

	TeacherConfigurator objectUnderTest;

	@Test
	public void can_get_random_weight_min() {
		objectUnderTest = new TeacherConfigurator();
		objectUnderTest.setRandomWeightRange(2.5);
		assertTrue(objectUnderTest.getRandomWeightMin() == -2.5);
	}

	@Test
	public void can_get_random_weight_max() {
		objectUnderTest = new TeacherConfigurator();
		objectUnderTest.setRandomWeightRange(2.5);
		assertTrue(objectUnderTest.getRandomWeightMax() == 2.5);
	}
}