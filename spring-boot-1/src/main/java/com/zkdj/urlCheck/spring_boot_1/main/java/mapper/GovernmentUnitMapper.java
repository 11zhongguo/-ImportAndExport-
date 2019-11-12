package com.zkdj.urlCheck.spring_boot_1.main.java.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zkdj.urlCheck.spring_boot_1.main.java.model.BaMain;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.GatherRulesSource;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.GovernmentUnit;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.SearchEngineSource;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.PagingInfo;

/**
 * 网站主管部门
 * @Author si
 */
@Repository
public interface GovernmentUnitMapper {
	
	/**
	 * 保存
	 * @param GovernmentUnit
	 * @return
	 */
	Boolean saveGovernmentUnit(List<GovernmentUnit> list);
	
	/**
	 * 保存
	 * @param GovernmentUnit
	 * @return
	 */
	Boolean insertRulesSource(List<GatherRulesSource> list);
	
	/**
	 * 修改
	 * @param GovernmentUnit
	 * @return
	 */
	Boolean updateGovernmentUnit(GovernmentUnit governmentUnit);
	
	/**
	 * 修改
	 * @param GovernmentUnit
	 * @return
	 */
	List<GovernmentUnit> findGovernmentUnit();
	
	List<GovernmentUnit> findGovernmentUnitAll();
	
	List<GatherRulesSource> findGatherRulesSource();
	List<SearchEngineSource> findSearchEngineSource();
	
	Boolean updateRulesSource(List<GatherRulesSource> list);
	
	Boolean GovernmentUnit(List<GovernmentUnit> list);
	
	Boolean updateINRulesSource(GatherRulesSource gatherRulesSource);
	Boolean updateINSearchSource(SearchEngineSource gatherRulesSource);
	
	List<GatherRulesSource> findGovernmentUnitSS(PagingInfo pagingInfo);
	
	int findGovernmentUnitCount(PagingInfo pagingInfo);
	
	List<BaMain> findBaMain(PagingInfo pagingInfo);
	
	int findBaMainCount(PagingInfo pagingInfo);
	
	List<String> findWordName();
	
	List<Integer> findByEntityId(@Param("entityId") Integer entityId);
	
	List<Integer> findWordIdsByName(Set<String> list);
}
