package outlier.detection.spark.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.log4j.Logger;

import java.io.IOException;

public class MessageParser {
	private static final Logger LOGGER = Logger .getLogger(MessageParser.class);
	private final ObjectReader reader = new ObjectMapper().reader(InputMessage.class);

	public InputMessage parse(String input) {

		InputMessage parsed = null;
		if (input != null && input.trim().length() > 0) {
			try {
				parsed = this.reader.readValue(input);
			} catch (JsonProcessingException e) {
				LOGGER.error("could not parse:" + input, e);
			} catch (IOException e) {
				LOGGER.error("could not parse:" + input, e);
			}
		}
		return parsed;

	}
}
