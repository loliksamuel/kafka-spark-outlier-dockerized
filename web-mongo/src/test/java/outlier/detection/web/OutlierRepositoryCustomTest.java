package outlier.detection.web;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import outlier.detection.Application;
import outlier.detection.dto.Outlier;
import outlier.detection.dto.OutputMessage;
import outlier.detection.web.repository.OutlierRepository;
import outlier.detection.web.repository.OutlierRepositoryCustom;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
public class OutlierRepositoryCustomTest {

	@Autowired
	private OutlierRepositoryCustom outlierRepositoryCustom;

	@Autowired
	private OutlierRepository outlierRepository;

	@Before
	@After
	public void cleanUp(){
		outlierRepository.deleteAll();
	}
	
	@Test
	public void shouldReturnPublishers() {
		Iterable<OutputMessage> oms = Arrays.asList(
				buildForPublisher("1"),
				buildForPublisher("2"), buildForPublisher("3"),
				buildForPublisher("4"), buildForPublisher("5"),
				buildForPublisher("1"), buildForPublisher("2"),
				buildForPublisher("3"), buildForPublisher("4"));

		List<String> expected = Arrays.asList("1", "2", "3", "4", "5");

		outlierRepository.save(oms);

		List<String> actual = outlierRepositoryCustom.findDistinctPublishers();
		
		Assert.assertEquals(expected, actual);
	}

	private OutputMessage buildForPublisher(String publisherId) {
		OutputMessage om = new OutputMessage();
		Outlier o = new Outlier("detMethod", Arrays.asList(1.0, 2.0, 3.0));
		om.setOutliers(Arrays.asList(o));
		om.setTime(new Date());
		om.setPublisher(publisherId);
		return om;
	}

}
