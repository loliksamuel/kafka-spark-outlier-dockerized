package com.f5.webmysql.web.repository;

import com.f5.webmysql.dto.OutputMessage;

import java.util.List;

//@Repository
public class OutlierRepositoryImpl implements OutlierRepositoryCustom {

	//private MongoOperations mongoOperations;
	private Class<?> clazz;
	private String collName;

	public OutlierRepositoryImpl( 	) {
		//this.mongoOperations = mongoOperations;
		this.clazz = OutputMessage.class;
		//collName = this.mongoOperations.getCollectionName(this.clazz);
	}

	@Override
	public List<String> findDistinctPublishers() {
		

		List<String> res = null;//(List<String>) mongoOperations.getCollection(collName);//.distinct("publisher");
		//Collections.sort(res);
		return res;
	}

}
