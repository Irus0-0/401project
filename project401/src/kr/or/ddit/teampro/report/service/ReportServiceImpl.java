package kr.or.ddit.teampro.report.service;

import kr.or.ddit.teampro.report.dao.ReportDao;
import kr.or.ddit.teampro.report.dao.ReportDaoImpl;
import kr.or.ddit.teampro.report.vo.ReportResultVo;
import kr.or.ddit.teampro.report.vo.ReportVo;

import java.util.List;
import java.util.Map;

public class ReportServiceImpl implements ReportService {

    private ReportDao reportDao;

    private static ReportService reportService;

    private ReportServiceImpl() {
        reportDao = ReportDaoImpl.getInstance();
    }

    public static ReportService getInstance() {
        if (reportService == null) {
            reportService = new ReportServiceImpl();
        }

        return reportService;
    }


    @Override
    public int doReport(ReportVo reportVo, boolean whoReport) {
        return reportDao.insertReport(reportVo, whoReport);
    }

    @Override
    public int checkReport(ReportVo reportVo, boolean whoReport) {
        return reportDao.checkReport(reportVo, whoReport);
    }


    @Override
    public int modifyReport(ReportVo reportVo, boolean whoReport) {
        return reportDao.updateReport(reportVo, whoReport);
    }

    @Override
    public int cancelReport(int reportNum, boolean whoReport) {
        return reportDao.deleteReport(reportNum, whoReport);
    }

    @Override
    public List<ReportVo> ReportInquire(boolean whoReport) {
        List<ReportVo> reportVoList = reportDao.selectAllReport(whoReport);
        return reportVoList;
    }

    @Override
    public List<ReportVo> ReportInquire(boolean whoReport, Map search) {
        List<ReportVo> reportVoList = reportDao.selectAllReport(whoReport, search);
        return reportVoList;
    }

    @Override
    public List<ReportResultVo> ReportResultsInquire(boolean whoReport) {
        List<ReportResultVo> reportResultVoList = reportDao.selectAllReportResults(whoReport);
        return reportResultVoList;
    }

    @Override
    public List<ReportVo> ReportResultsFInquire(boolean whoReport) {
        List<ReportVo> reportResultVoList = reportDao.selectAllReportResultsF(whoReport);
        return reportResultVoList;
    }

    @Override
    public int registerReportResult(boolean whoReport,  ReportResultVo reportResultVo) {
        return reportDao.insertReportResult(whoReport, reportResultVo);
    }

    @Override
    public int modifyReportResult(boolean whoReport, ReportResultVo reportResultVo) {
        return reportDao.updateReportResult(whoReport, reportResultVo);
    }

    @Override
    public int checkingReportResult(boolean whoReport, ReportVo reportVo) {
        return reportDao.checkReportResult(whoReport, reportVo);
    }

    @Override
    public ReportResultVo InquireReportResult(boolean whoReport, ReportVo reportVo) {
        ReportResultVo reportResultVoList = reportDao.selectReportResult(whoReport, reportVo);
        return reportResultVoList;
    }


}
