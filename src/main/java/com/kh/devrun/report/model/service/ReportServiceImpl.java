package com.kh.devrun.report.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.devrun.report.model.dao.ReportDao;
import com.kh.devrun.report.model.vo.Report;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao reportDao;

	@Override
	public int insertReport(Report report) {
		return reportDao.insertReport(report);
	}

}
