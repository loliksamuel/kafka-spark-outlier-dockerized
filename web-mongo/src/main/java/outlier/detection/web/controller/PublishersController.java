package outlier.detection.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import outlier.detection.web.repository.OutlierRepositoryCustom;

import java.util.List;

@Controller
@RequestMapping("/publishers")
public class PublishersController implements ResourceProcessor<RepositoryLinksResource> {

	@Autowired
	private OutlierRepositoryCustom customRepo;

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(PublishersController.class).withRel("publishers"));
        return resource;
    }


	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<String>> findPublisherNames() {
		return ResponseEntity.ok(customRepo.findDistinctPublishers());
	}
	
}
