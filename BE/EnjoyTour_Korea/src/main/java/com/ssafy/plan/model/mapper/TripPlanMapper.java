package com.ssafy.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.notice.model.NoticeParameterDto;
import com.ssafy.plan.model.TripPlanDto;

@Mapper
public interface TripPlanMapper {
	void writePlan(TripPlanDto tripPlanDto) throws SQLException;
	void writeContent(TripPlanDto tripPlanDto) throws Exception;
	void authPlan(TripPlanDto tripPlanDto) throws Exception;
	List<TripPlanDto> listPlan(NoticeParameterDto noticeParameterDto) throws SQLException;
	TripPlanDto getPlan(int planNo) throws SQLException;
	void modifyPlan(TripPlanDto planNo) throws SQLException;
	void deletePlan(int planNo) throws SQLException;
}
