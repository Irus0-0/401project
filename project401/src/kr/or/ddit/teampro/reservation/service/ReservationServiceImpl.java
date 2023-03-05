package kr.or.ddit.teampro.reservation.service;

import kr.or.ddit.teampro.reservation.dao.ReservationDao;
import kr.or.ddit.teampro.reservation.dao.ReservationDaoImpl;
import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.util.List;

public class ReservationServiceImpl implements ReservationService{

    private ReservationDao resDao;

    private static ReservationService resService;

    private ReservationServiceImpl(){
        resDao = ReservationDaoImpl.getInstance();
    }

    public static ReservationService getInstance() {
        if(resService == null) {
            resService = new ReservationServiceImpl();
        }

        return resService;
    }

    @Override
    public int registReservation(ReservationVo reservation) {
        return resDao.insertReservation(reservation);
    }

    @Override
    public int modifyReservation(ReservationVo reservation) {
        return resDao.updateReservation(reservation);
    }

    @Override
    public int removeReservation(String reservationNum) {
        return resDao.deleteReservation(reservationNum);
    }

    @Override
    public List<ReservationVo> searchUserReservation(String customerId) {
        List<ReservationVo> resList = resDao.selectUserAllReservation(customerId);
        return resList;
    }

    @Override
    public List<ReservationVo> searchUserUseReservation(String customerId) {
        List<ReservationVo> resList = resDao.selectUserAllUseReservation(customerId);
        return resList;
    }

    @Override
    public List<ReservationVo> searchCoAllReservation(String companyId) {
        List<ReservationVo> resList = resDao.selectCoAllReservation(companyId);
        return resList;
    }

    @Override
    public List<ReservationVo> searchAccomReservation(List<String> accomKey) {
        List<ReservationVo> resList = resDao.selectAccomAllReservation(accomKey);
        return resList;
    }
}
