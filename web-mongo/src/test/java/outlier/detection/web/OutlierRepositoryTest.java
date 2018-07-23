package outlier.detection.web;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import outlier.detection.Application;
import outlier.detection.dto.Outlier;
import outlier.detection.dto.OutputMessage;
import outlier.detection.web.repository.OutlierRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
public class OutlierRepositoryTest {

	@Autowired
	private OutlierRepository outlierRepository;

	@Before
	@After
	public void cleanUp(){
		outlierRepository.deleteAll();
	}
	
	@Test
	public void shouldCreate() {
		OutputMessage om = new OutputMessage();
		Outlier o = new Outlier("detMethod", Arrays.asList(1.0, 2.0, 3.0));
		om.setOutliers(Arrays.asList(o));

		OutputMessage created = outlierRepository.save(om);
		Assert.assertNotNull(created);
		Assert.assertEquals(om, created);
	}
	
	@Test
	public void shouldReturnLatestForPublisher() {
		
		long maxTime = 5;
		int publishers = 7;
		for (int i=1; i<=publishers; i++) {
			for(int time=1; time<=maxTime; time++) {
				OutputMessage om = buildForPublisherAndTime(String.valueOf(i), time);
				OutputMessage created = outlierRepository.save(om);
				Assert.assertNotNull(created);
			}
		}
		
		List<OutputMessage> all = outlierRepository.findAll();
		Assert.assertEquals(maxTime * publishers, all.size());
		
		String expectedPublisherId = "1"; 
		OutputMessage expected = buildForPublisherAndTime(expectedPublisherId, maxTime);
		
		Page<OutputMessage> page = outlierRepository.findByPublisher(expectedPublisherId, new PageRequest(0, 1, new Sort(Direction.DESC, "time")));
		Assert.assertEquals(maxTime, page.getTotalElements());
		Assert.assertEquals(1, page.getNumberOfElements());
		List<OutputMessage> contents = page.getContent();
		OutputMessage actual = contents.get(0);
		
		//equal except for id which was not known at the time of creation
		expected.assignId(actual.exposeId());
		
		Assert.assertEquals(expected, actual);
	}

	private OutputMessage buildForPublisherAndTime(String publisherId, long time) {
		OutputMessage om = new OutputMessage();
		Outlier o = new Outlier("detMethod", Arrays.asList(1.0, 2.0, 3.0));
		om.setOutliers(Arrays.asList(o));
		om.setPublisher(publisherId);
		om.setTime(new Date(time));
		return om;
	}
}
