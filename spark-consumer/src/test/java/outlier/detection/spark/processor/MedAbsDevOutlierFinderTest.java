package outlier.detection.spark.processor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import outlier.detection.dto.Outlier;

import java.util.Arrays;

public class MedAbsDevOutlierFinderTest {

	private OutlierFinder outlierFinder;
	
	@Before
	public void setUp() {
		outlierFinder = new OutlierFinderMedAbsDev();
	}
	
	@Test
	public void testNoOutliers() {
		
		Outlier empty = outlier();
		
		double[][] input = {
				new double[]{},
				new double[]{0.0},
				new double[]{1.0},
				new double[]{-1.0},
				new double[]{-1.0, 1.0},
				new double[]{1.0, 1.0, 0.0, 2},
				new double[]{1.0, 1.0, 1.0, 1.0}
		};
				
		for (double[] in: input) {
			Outlier outlier = outlierFinder.findIn(in);
			Assert.assertEquals(empty, outlier);
		}		
	}

	
	@Test
	public void testOutliers() {
		
		double[][] input = {
				new double[]{1.0, 1.0, 0.0},//0				
				new double[]{1.0, 1.0, 0.0, 100.0},//1
				new double[]{1.0, 1.0, 0.0, 1.5, 2.5, 3.5, 5, 7, 8, 9},//2
				new double[]{1.0, 1.0, 0.0, 1.5, 2.5, 3.5, 5, 7, 8, 9, -5},//3
				new double[]{1, 13, 192, 7, 8, 99, 1014, 4},//4
				new double[]{5.0, 5.0, 5.0, 5.0, 105.0, 6.0, 7.0, 7.0, 105.0, 5.0, 2.0, 102.0, 6.0, 3.0},//5
				new double[]{6.0, 5.0, 4.0, 107.0, 5.0, 5.0, 7.0, 7.0, 2.0, 6.0, 104.0, 2.0, 105.0},//6
				new double[]{107.0, 7.0, 7.0, 5.0, 105.0, 4.0, 102.0, 7.0, 7.0, 102.0},//7
				new double[]{5.0, 3.0, 104.0, 4.0, 6.0, 4.0, 3.0, 4.0, 6.0, 2.0, 7.0}//8
		};
		
		Outlier[] expected = {
				outlier(0.0),//0				
				outlier(100.0),//1
				outlier(8.0, 9.0),//2
				outlier(8.0, 9.0, -5.0),//3
				outlier(192.0, 99.0, 1014.0),//4
				outlier(105.0, 105.0, 2.0, 102.0, 3.0),//5
				outlier(107.0, 2.0, 104.0, 2.0, 105.0),//6
				outlier(107.0, 105.0, 102.0, 102.0),//7
				outlier(104.0, 7.0)//8
		}; 
		
		Assert.assertEquals(input.length, expected.length);
		
		for (int i=0; i<input.length; i++) {
			Outlier outlier = outlierFinder.findIn(input[i]);
			Assert.assertEquals(expected[i], outlier);
		}		
	}
	
	private Outlier outlier(Double... readings) {		
		Outlier outlier = new Outlier("local_MedAbsDev", Arrays.asList(readings));
		return outlier;
	}
}
