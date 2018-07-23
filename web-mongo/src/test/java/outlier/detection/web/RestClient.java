package outlier.detection.web;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.*;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.client.Traverson.TraversalBuilder;
import org.springframework.hateoas.mvc.TypeReferences.PagedResourcesType;
import org.springframework.hateoas.mvc.TypeReferences.ResourceType;
import org.springframework.hateoas.mvc.TypeReferences.ResourcesType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import outlier.detection.dto.OutputMessage;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.MediaTypes.HAL_JSON;


@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest(randomPort = true)
@SpringApplicationConfiguration
public class RestClient {
	private final static Logger log = LoggerFactory.getLogger(RestClient.class);
	
	@SpringBootApplication
	static class Config {

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}
	
	@Value("${local.server.port}") 
	String port ;

	private static final String SERVICE_URI = "http://localhost:%s/";

	@Test
	public void discoverOutliersSearch() {

		Traverson traverson = new Traverson(URI.create(String.format(SERVICE_URI, port)), MediaTypes.HAL_JSON);

		// Set up path traversal
		TraversalBuilder builder = traverson. //
				follow("outliers", "search", "findByPublisher");

		// Log discovered
		log.info("");
		log.info("Discovered link: {}", builder.asTemplatedLink());
		log.info("");
		

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("publisher", "publisher-1");
		parameters.put("sort", "time,desc");

		PagedResources<Resource<OutputMessage>> resources = builder.//
				withTemplateParameters(parameters).//
				toObject(new PagedResourcesType<Resource<OutputMessage>>() {});

		PageMetadata metadata = resources.getMetadata();

		log.info("Got {} of {} stores: ", resources.getContent().size(), metadata.getTotalElements());
	
		StreamSupport.stream(resources.spliterator(), false).//
				map(Resource::getContent).//
					forEach(output -> log.info("{} - {}", output.getPublisher(), output.getReadings())
				);						
	}
	
	@Autowired RestOperations restOperations;

	
	@Test
	@Ignore
	public void accessServiceUsingRestTemplate() {

		// Access root resource

		URI uri = URI.create(String.format(SERVICE_URI, port));
		RequestEntity<Void> request = RequestEntity.get(uri).accept(HAL_JSON).build();
		Resource<Object> rootLinks = restOperations.exchange(request, new ResourceType<Object>() {}).getBody();
		Links links = new Links(rootLinks.getLinks());

		// Follow stores link

		Link storesLink = links.getLink("outliers").expand();
		request = RequestEntity.get(URI.create(storesLink.getHref())).accept(HAL_JSON).build();
		Resources<OutputMessage> stores = restOperations.exchange(request, new ResourcesType<OutputMessage>() {}).getBody();

		stores.getContent().forEach(output -> 
			log.info("{} - {}", output.getPublisher(), output.getReadings())	
		);
	}
	
}
