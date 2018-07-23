package outlier.detection.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import outlier.detection.dto.OutputMessage;


@RepositoryRestResource(collectionResourceRel = "outliers", path = "outliers")
public interface OutlierRepository extends MongoRepository<OutputMessage, String> {
	
	@Query(value= "{publisher: ?0}")
	Page<OutputMessage> findByPublisher(@Param("publisher") String publisher, Pageable pageable);
	
	//Page<OutputMessage> findByPublisher(@Param("publisher") String publisher, Pageable pageable);
	
	@Query("{}")
	Page<OutputMessage> findAll(Pageable pageable);
	
	
}
