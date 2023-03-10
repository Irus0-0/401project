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

    /**
     * 예약 하기
     * @param reservation
     * @return
     */
    @Override
    public int registReservation(ReservationVo reservation) {
        return resDao.insertReservation(reservation);
    }


    /**
     * 예약 수정하기
     * @param reservation
     * @return
     */
    @Override
    public int modifyReservation(ReservationVo reservation) {
        return resDao.updateReservation(reservation);
    }

    /**
     * 예약 취소하기
     * @param reservationNum
     * @return
     */
    @Override
    public int removeReservation(String reservationNum) {
        return resDao.deleteReservation(reservationNum);
    }

    /**
     * 해당 유저의 모든 예약 정보 가져오기
     * @param customerId
     * @return
     */
    @Override
    public List<ReservationVo> searchUserReservation(String customerId) {
        List<ReservationVo> resList = resDao.selectUserAllReservation(customerId);
        return resList;
    }

    /**
     * 사용자의 종료되지 않은 모든 예약정보를 가져옴
     * @param customerId
     * @return
     */
    @Override
    public List<ReservationVo> searchUserUseReservation(String customerId) {
        List<ReservationVo> resList = resDao.selectUserAllUseReservation(customerId);
        return resList;
    }

    /**
     * 진행 중인 예약 정보를 가져옴
     * @param customerId
     * @return
     */
    @Override
    public List<ReservationVo> selectContinuReservation(String customerId) {
        List<ReservationVo> resList = resDao.selectContinuReservation(customerId);
        return resList;
    }

    ;

    /**
     * 해당 기업의 모든 예약정보를 가져옴
     * @param companyId
     * @return
     */
    @Override
    public List<ReservationVo> searchCoAllReservation(String companyId) {
        List<ReservationVo> resList = resDao.selectCoAllReservation(companyId);
        return resList;
    }

    /**
     * 해당 숙박시설의 모든 예약정보를 가져옴
     * @param accomKey
     * @return
     */
    @Override
    public List<ReservationVo> searchAccomReservation(List<String> accomKey) {
        List<ReservationVo> resList = resDao.selectAccomAllReservation(accomKey);
        return resList;
    }
}
