package com.f5.webmysql.web.controller;

import com.f5.webmysql.dto.ReadingMedian;
import com.f5.webmysql.web.repository.OutlierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class OutlierController   {
	@Autowired
	private OutlierRepository outlierRepo;

	//  http://localhost:8080/query?N=8&publisher=pub10
	@GetMapping("/query")
	List<ReadingMedian> findByPublisher(@RequestParam(value =   "N"  ) String N
									  , @RequestParam(value = "publisher") String publisherId){
		long n = Long.parseLong(N);
		return outlierRepo.findByPublisherId(publisherId).stream().limit(n).collect(Collectors.toList());
	}
	
	//Page<OutputMessage> findByPublisher(@Param("publisher") String publisher, Pageable pageable);


	List<ReadingMedian> findAll(){

		return outlierRepo.findAll();
	}

	@GetMapping(  "/create")
	void create(){
		ReadingMedian rm = new ReadingMedian();
		rm.setId("1");
		rm.setPublisherId("1");
		rm.setRead(1.1);
		outlierRepo.save(rm);
	}
	
	
}
