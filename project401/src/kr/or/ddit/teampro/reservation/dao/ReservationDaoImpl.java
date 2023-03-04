package kr.or.ddit.teampro.reservation.dao;

import dao.MyBatisDao;
import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.util.List;

public class ReservationDaoImpl extends MyBatisDao implements ReservationDao {

    private static ReservationDao resDao;
    public ReservationDaoImpl() {
    }

    public static ReservationDao getInstance() {
        if (resDao == null) {
            resDao = new ReservationDaoImpl();
        }
        return resDao;
    }


    @Override
    public int insertReservation(ReservationVo reservation) {
        return insert("reservation.insertReservation", reservation);
    }

    @Override
    public int updateReservation(ReservationVo reservation) {
        return update("reservation.updateReservation", reservation);
    }

    @Override
    public int deleteReservation(String reservationNum) {
        return delete("reservation.deleteReservation", reservationNum);
    }

    @Override
    public List<ReservationVo> selectUserAllReservation(String customerId) {
        List<ReservationVo> resList = selectList("reservation.selectMyALLReservation", customerId);
        return resList;
    }

    @Override
    public List<ReservationVo> selectCoAllReservation(String companyId) {
        List<ReservationVo> resList = selectList("reservation.selectCoALLReservation", companyId);
        return resList;
    }

    @Override
    public List<ReservationVo> selectAccomAllReservation(List<String> accomKey) {
        List<ReservationVo> resList = selectList("reservation.selectAccomALLReservation", accomKey);
        return resList;
    }
}
