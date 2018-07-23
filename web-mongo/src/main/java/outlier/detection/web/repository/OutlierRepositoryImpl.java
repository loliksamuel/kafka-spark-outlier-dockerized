package outlier.detection.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import outlier.detection.dto.OutputMessage;

import java.util.Collections;
import java.util.List;

@Repository
public class OutlierRepositoryImpl implements OutlierRepositoryCustom {

	private MongoOperations mongoOperations;
	private Class<?> clazz;
	private String collName;
	@Autowired
	public OutlierRepositoryImpl(
			MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
		this.clazz = OutputMessage.class;
		collName = this.mongoOperations.getCollectionName(this.clazz);
	}

	@Override
	public List<String> findDistinctPublishers() {
		

		List<String> res =  mongoOperations.getCollection(collName).distinct("publisher");
		Collections.sort(res);
		return res;
	}

}
