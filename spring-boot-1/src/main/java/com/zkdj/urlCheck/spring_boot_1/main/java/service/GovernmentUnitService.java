package com.zkdj.urlCheck.spring_boot_1.main.java.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.zkdj.urlCheck.spring_boot_1.main.java.model.GatherRulesSource;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.GovernmentUnit;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.SearchEngineSource;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.PagingInfo;

@Service
public interface GovernmentUnitService {
	
	 void insertGovernmentUnit(List<GovernmentUnit> list);
	 
	 void insertRulesSource(List<GatherRulesSource> list);
	 
	 List<GovernmentUnit> findGovernmentUnit();
	 
	 List<GovernmentUnit> findGovernmentUnitAll();
	 
	 List<GatherRulesSource> findGatherRulesSource();
	 
	 PagingInfo findGovernmentUnit(PagingInfo pagingInfo);
	 
	 PagingInfo findBaMain(PagingInfo pagingInfo);

	List<SearchEngineSource> findSearchEngineSource();
	
	List<String> findWordName();

	List<Integer> findByEntityId(Integer entityId);
	
	List<Integer> findWordIdsByName(Set<String> list);
}
