package kr.or.ddit.teampro.master.service;


import kr.or.ddit.teampro.master.vo.MasterVO;

import java.util.List;

public interface IMasterService {
    public int insertMaster(MasterVO mv); //관리자 등록
    public int updateMaster(MasterVO mv); // 수정 x
    public int deleteMaster(MasterVO mv); //관리자 삭제
    public MasterVO display(MasterVO mv); //관리자 내 정보 구현 x
    public List<MasterVO> displayAll(); // x
    public MasterVO getVo (); //관리자 객체 불러오기
    public MasterVO setVo (MasterVO mv); //관리자 객체 저장
    public MasterVO logout (); //관리자 객체 null 저장
    public MasterVO login (MasterVO mv);//관리자 로그인
    public boolean isExist (MasterVO mv);//계정확인
}