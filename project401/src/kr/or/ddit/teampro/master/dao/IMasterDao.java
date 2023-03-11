package kr.or.ddit.teampro.master.dao;


import kr.or.ddit.teampro.master.vo.MasterVO;

import java.util.List;

public interface IMasterDao { //x 안함
    public int insertMaster(MasterVO mv); //관리자 등록
    public int updateMaster(MasterVO mv); //관리자 수정
    public int deleteMaster(MasterVO mv); //관리자 삭제
    public MasterVO display(MasterVO mv); //관리자 내 정보 x
    public List<MasterVO> displayAll(); //모든 관리자보기 x
    public boolean isExist(MasterVO mv); //계정확인
    public MasterVO login(MasterVO mv); //로그인
}