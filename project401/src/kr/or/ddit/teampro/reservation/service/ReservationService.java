package kr.or.ddit.teampro.reservation.service;

import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.util.List;

public interface ReservationService {
    int registReservation(ReservationVo reservation);

    int modifyReservation(ReservationVo reservation);

    int removeReservation(String reservationNum);

    List<ReservationVo> searchUserReservation(String customerId);
    List<ReservationVo> searchCoAllReservation(String companyId);
    List<ReservationVo> searchAccomReservation(List<String> accomKey);
}
