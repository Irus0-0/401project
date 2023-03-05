package kr.or.ddit.teampro.report.dao;

import dao.MyBatisDao;
import kr.or.ddit.teampro.report.vo.ReportVo;

import java.util.List;

public class ReportDaoImpl extends MyBatisDao implements ReportDao {

    private static ReportDaoImpl reportDao;

    private ReportDaoImpl() {

    }

    public static ReportDao getInstance() {
        if (reportDao == null) {
            reportDao = new ReportDaoImpl();
        }

        return reportDao;
    }

}
