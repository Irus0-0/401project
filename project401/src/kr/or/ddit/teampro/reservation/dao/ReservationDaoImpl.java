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


    /**
     * 예약정보 넣기
     * @param reservation 예약 VO 객체
     * @return 성공 1 / 실패 0 반환
     */
    @Override
    public int insertReservation(ReservationVo reservation) {
        return insert("reservation.insertReservation", reservation);
    }

    /**
     * 예약 정보 수정하기
     * @param reservation 예약 VO 객체
     * @return 성공 1 / 실패 0 반환
     */
    @Override
    public int updateReservation(ReservationVo reservation) {
        return update("reservation.updateReservation", reservation);
    }

    /**
     * 예약 삭제하기
     * @param reservationNum 예약 번호
     * @return 성공 1 / 실패 0 반환
     */
    @Override
    public int deleteReservation(String reservationNum) {
        return delete("reservation.deleteReservation", reservationNum);
    }

    /**
     * 해당 유저의 모든 예약 정보 가져오기
     * @param customerId 사용자ID
     * @return 예약VO 객체를 LIST 에 담아서 전달
     */
    @Override
    public List<ReservationVo> selectUserAllReservation(String customerId) {
        List<ReservationVo> resList = selectList("reservation.selectMyAllReservation", customerId);
        return resList;
    }

    /**
     * 사용자의 종료되지 않은 모든 예약정보를 가져옴
     * @param customerId 사용자ID
     * @return 예약VO 객체를 LIST 에 담아서 전달
     */
    @Override
    public List<ReservationVo> selectUserAllUseReservation(String customerId) {
        List<ReservationVo> resList = selectList("reservation.selectMyAllUseReservation", customerId);
        return resList;
    }

    /**
     * 진행중인 예약 정보를 가져옴
     * @param customerId
     * @return
     */
    @Override
    public List<ReservationVo> selectContinuReservation(String customerId) {
        List<ReservationVo> resList = selectList("reservation.selectMyAllUseReservation2", customerId);
        return resList;
    }

    /**
     * 해당 기업의 모든 예약정보를 가져옴
     * @param companyId 기업 ID
     * @return 예약VO 객체를 LIST 에 담아서 전달
     */
    @Override
    public List<ReservationVo> selectCoAllReservation(String companyId) {
        List<ReservationVo> resList = selectList("reservation.selectCoALLReservation", companyId);
        return resList;
    }

    /**
     * 해당 숙박시설의 모든 예약정보를 가져옴
     * @param accomKey 0번 숙박시설명 1번 기업ID (List 타입)
     * @return 예약VO 객체를 LIST 에 담아서 전달
     */
    @Override
    public List<ReservationVo> selectAccomAllReservation(List<String> accomKey) {
        List<ReservationVo> resList = selectList("reservation.selectAccomALLReservation", accomKey);
        return resList;
    }
}
