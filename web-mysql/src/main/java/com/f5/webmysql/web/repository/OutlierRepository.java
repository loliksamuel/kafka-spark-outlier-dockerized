package com.f5.webmysql.web.repository;

import com.f5.webmysql.dto.ReadingMedian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OutlierRepository extends JpaRepository<ReadingMedian, String> {
	

	List<ReadingMedian> findByPublisherId(String publisher);

	List<ReadingMedian> findAll();



	
	
}
