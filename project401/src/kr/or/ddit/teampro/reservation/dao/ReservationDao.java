package kr.or.ddit.teampro.reservation.dao;

import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.util.List;

public interface ReservationDao {

    /**
     * 예약 정보 넣기
     * @param reservation 예약 VO 객체
     * @return DB 작업 성공시 1 반환 실패시 0 반환
     */
    int insertReservation(ReservationVo reservation);

    /**
     * 예약정보 수정하기
     * @param reservation 예약 VO 객체
     * @return DB 작업 성공시 1 반환 실패시 0 반환
     */
    int updateReservation(ReservationVo reservation);

    /**
     * 예약정보 삭제하기
     * @param reservationNum 예약 번호
     * @return DB 작업 성공시 1 반환 실패시 0 반환
     */
    int deleteReservation(String reservationNum);


    /**
     * 사용자 예약 모든 예약정보 가져오기
     * @param customerId 사용자ID
     * @return 사용자의 예약정보 LIST로 반환
     */
    List<ReservationVo> selectUserAllReservation(String customerId);

    /**
     * 사용자의 종료되지 않은 모든 예약정보를 가져옴
     * @param customerId 사용자ID
     * @return 사용자의 예약정보 LIST로 반환
     */
    List<ReservationVo> selectUserAllUseReservation(String customerId);

    /**
     * 진행중인 예약 정보를 가져옴
     * @param customerId
     * @return
     */
    List<ReservationVo> selectContinuReservation(String customerId);

    /**
     * 해당 기업의 모든 예약정보 가져오기
     * @param companyId 기업 ID
     * @return 기업이 소유한 모든 숙박시설의 예약정보를 LIST로 반환
     */
    List<ReservationVo> selectCoAllReservation(String companyId);

    /**
     * 숙박시설의 모든 예약정보 가져오기
     * @param accomKey 0번 숙박시설명 1번 기업ID
     * @return 선택한 숙박시설의 모든 예약정보를 LIST로 반환
     */
    List<ReservationVo> selectAccomAllReservation(List<String> accomKey);


}
