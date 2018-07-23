package outlier.detection.spark.input;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import outlier.detection.dto.OutputMessage;
import outlier.detection.spark.processor.Processor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class MessageParserTest {

	@Test
	public void testOK() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String s = "{\"publisher\": \"publisher-id\", \"time\": \"1970-01-01 00:00:00.000\", \"readings\": [ 1, 13, 192, 7, 8, 99, 1014, 4]}";
		InputMessage parsed = mapper.readValue(s, InputMessage.class);
		
		System.out.println(parsed);
		
		InputMessage expected = new InputMessage();
		expected.setPublisher("publisher-id");
		expected.setTime(new Date(0));
		expected.setReadings(Arrays.asList( 1.0, 13.0, 192.0, 7.0, 8.0, 99.0, 1014.0, 4.0));
		
		Assert.assertEquals(expected, parsed);
		
	}
	
	@Test
	public void testOK2() throws JsonParseException, JsonMappingException, IOException {
		MessageParser parser = new MessageParser();
		String s = "{\"publisher\": \"publisher-id\", \"time\": \"1970-01-01 00:00:00.000\", \"readings\": [ 1, 13, 192, 7, 8, 99, 1014, 4]}";
		InputMessage parsed = parser.parse(s);
		System.out.println(parsed);
		
		InputMessage expected = new InputMessage();
		expected.setPublisher("publisher-id");
		expected.setTime(new Date(0));
		expected.setReadings(Arrays.asList(  1.0, 13.0, 192.0, 7.0, 8.0, 99.0, 1014.0, 4.0));
		
		Assert.assertEquals(expected, parsed);
	}
	
	
	@Test
	public void testParseNotNull () throws JsonParseException, JsonMappingException, IOException {
		MessageParser parser = new MessageParser();
		String s = "{\"publisher\": \"publisher-id\", \"time\": \"not a date\", \"readings\": [ 1, 13, 192, 7, 8, 99, 1014, 4]}";
		InputMessage parsed = parser.parse(s);
		System.out.println(parsed);
		
		Assert.assertEquals(null, parsed);
	}
	
	
	@Test
	public void testProcessOK() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String s = "{\"publisher\": \"publisher-id\", \"time\": \"2015-11-03 15:03:30.352\", \"readings\": [ 1, 13, 192, 7, 8, 99, 1014, 4]}";
		InputMessage parsed = mapper.readValue(s, InputMessage.class);
		System.out.println(parsed);
		
		Processor p = new Processor();
		OutputMessage om = p.process(parsed);
			
		System.out.println(om);
		
		String outStr = mapper.writeValueAsString(om);
		
		System.out.println(outStr);
		
		Assert.assertEquals("{\"publisher\":\"publisher-id\",\"time\":{ \"$date\" : \"2015-11-03T15:03:30.352Z\"},\"readings\":[1.0,13.0,192.0,7.0,8.0,99.0,1014.0,4.0],\"outliers\":[{\"detectionMethod\":\"local_MedAbsDev\",\"readings\":[192.0,99.0,1014.0]}],\"stats\":{\"median\":10.5,\"mean\":167.25,\"sd\":348.74868151312467}}", outStr);
		
	}
	@Test
	public void testProcessMicrosendsInInputMessageIncreaseMinutesInOutput() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String s = "{\"publisher\": \"publisher-4\", \"readings\": [2, 107, 4, 7, 3, 3, 7, 7, 105, 4, 6], \"time\": \"2016-01-04 15:07:42.300925\"}";
		InputMessage parsed = mapper.readValue(s, InputMessage.class);
		System.out.println(parsed);
		
		Processor p = new Processor();
		OutputMessage om = p.process(parsed);
			
		System.out.println(om);
		
		String outStr = mapper.writeValueAsString(om);
		
		System.out.println(outStr);
		
		Assert.assertEquals("{\"publisher\":\"publisher-4\",\"time\":{ \"$date\" : \"2016-01-04T15:12:42.925Z\"},\"readings\":[2.0,107.0,4.0,7.0,3.0,3.0,7.0,7.0,105.0,4.0,6.0],\"outliers\":[{\"detectionMethod\":\"local_MedAbsDev\",\"readings\":[107.0,105.0]}],\"stats\":{\"median\":6.0,\"mean\":23.18181818181818,\"sd\":40.98735947049573}}", outStr);
	}
	
	
}
