package com.zkdj.urlCheck.spring_boot_1.main.java.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkdj.urlCheck.spring_boot_1.main.java.mapper.GovernmentUnitMapper;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.BaMain;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.GatherRulesSource;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.GovernmentUnit;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.SearchEngineSource;
import com.zkdj.urlCheck.spring_boot_1.main.java.service.GovernmentUnitService;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.PagingInfo;

@Service
public class GovernmentUnitServiceImpl implements GovernmentUnitService{

	@Autowired
	private GovernmentUnitMapper governmentUnitMapper;
	@Override
	public void insertGovernmentUnit(List<GovernmentUnit> list) {
		
		Boolean saveGovernmentUnit = governmentUnitMapper.saveGovernmentUnit(list);
	}
	
	@Override
	public List<GovernmentUnit> findGovernmentUnit() {
		return governmentUnitMapper.findGovernmentUnit();
	}
	
	@Override
	public List<GovernmentUnit> findGovernmentUnitAll() {
		return governmentUnitMapper.findGovernmentUnitAll();
	}

	@Override
	public List<GatherRulesSource> findGatherRulesSource() {
		return governmentUnitMapper.findGatherRulesSource();
	}
	
	
	@Override
	public List<SearchEngineSource> findSearchEngineSource() {
		return governmentUnitMapper.findSearchEngineSource();
	}
	
	@Override
	public void insertRulesSource(List<GatherRulesSource> list) {
		Boolean saveGovernmentUnit = governmentUnitMapper.insertRulesSource(list);
		
	}

	@Override
	public PagingInfo findGovernmentUnit(PagingInfo pagingInfo) {
		// TODO Auto-generated method stub
		List<GatherRulesSource> findGovernmentUnitSS = governmentUnitMapper.findGovernmentUnitSS(pagingInfo);
		pagingInfo.setTotalCount(governmentUnitMapper.findGovernmentUnitCount(pagingInfo));
		pagingInfo.setList(findGovernmentUnitSS);
		return pagingInfo;
	}
	
	@Override
	public PagingInfo findBaMain(PagingInfo pagingInfo) {
		// TODO Auto-generated method stub
		List<BaMain> Bamain = governmentUnitMapper.findBaMain(pagingInfo);
		pagingInfo.setTotalCount(governmentUnitMapper.findBaMainCount(pagingInfo));
		pagingInfo.setList(Bamain);
		return pagingInfo;
	}

	@Override
	public List<String> findWordName() {
		return governmentUnitMapper.findWordName();
	}

	@Override
	public List<Integer> findByEntityId(Integer entityId) {
		return governmentUnitMapper.findByEntityId(entityId);
	}

	@Override
	public List<Integer> findWordIdsByName(Set<String> set) {
		return governmentUnitMapper.findWordIdsByName(set);
	}
	
}
