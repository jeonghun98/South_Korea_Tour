package com.ssafy.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.notice.model.NoticeDto;
import com.ssafy.notice.model.NoticeParameterDto;
import com.ssafy.notice.model.mapper.NoticeMapper;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	private NoticeMapper noticeMapper;

	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		super();
		this.noticeMapper = noticeMapper;
	}

	@Override
	@Transactional
	public void writeArticle(NoticeDto noticeDto) throws Exception {
		noticeMapper.writeArticle(noticeDto);
	}

	@Override
	public List<NoticeDto> listArticle(NoticeParameterDto noticeParameterDto) throws Exception {
		int start = noticeParameterDto.getPg() == 0 ? 0 : (noticeParameterDto.getPg() - 1) * noticeParameterDto.getSpp();
		noticeParameterDto.setStart(start);
		return noticeMapper.listArticle(noticeParameterDto);
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		param.put("key", key.isEmpty() ? "" : key);
//		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
//		return noticeMapper.listArticle(param);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int totalCount = noticeMapper.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public NoticeDto getArticle(int noticeNo) throws Exception {
		return noticeMapper.getArticle(noticeNo);
	}

	@Override
	public void updateHit(int noticeNo) throws Exception {
		noticeMapper.updateHit(noticeNo);
	}

	@Override
	public void modifyArticle(NoticeDto boardDto) throws Exception {
		noticeMapper.modifyArticle(boardDto);
		
	}

	@Override
	public void deleteArticle(int noticeNo) throws Exception {
		noticeMapper.deleteArticle(noticeNo);
	}

}
