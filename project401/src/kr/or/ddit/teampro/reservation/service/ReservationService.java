package kr.or.ddit.teampro.reservation.service;

import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.util.List;

public interface ReservationService {
    /**
     * 예약 등록하기
     * @param reservation
     * @return
     */
    int registReservation(ReservationVo reservation);

    /**
     * 예약 수정하기
     * @param reservation
     * @return
     */
    int modifyReservation(ReservationVo reservation);

    /**
     * 예약 취소하기
     * @param reservationNum
     * @return
     */
    int removeReservation(String reservationNum);

    /**
     * 해당 유저의 모든 예약 정보 가져오기
     * @param customerId
     * @return
     */
    List<ReservationVo> searchUserReservation(String customerId);

    /**
     * 시작하지 않은 모든 예약정보를 가져옴
     * @param customerId
     * @return
     */
    List<ReservationVo> searchUserUseReservation(String customerId);


    /**
     * 사용자의 종료된 예약정보를 가져옴
     * @param customerId
     * @return
     */
    public List<ReservationVo> searchCloseReservation(String customerId);

    /**
     * 진행중인 예약 정보를 가져옴
     * @param customerId
     * @return
     */
    List<ReservationVo> selectContinuReservation(String customerId);

    /**
     * 해당 기업의 모든 예약정보를 가져옴
     * @param companyId
     * @return
     */
    List<ReservationVo> searchCoAllReservation(String companyId);

    /**
     * 해당 숙박시설의 모든 예약정보를 가져옴
     * @param accomKey
     * @return
     */
    List<ReservationVo> searchAccomReservation(List<String> accomKey);

}
