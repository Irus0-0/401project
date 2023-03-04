package kr.or.ddit.teampro.master.service;

import kr.or.ddit.teampro.master.vo.MasterVO;

import java.util.List;

public interface IMasterService {
    public int insertMaster(MasterVO mv);
    public int updateMaster(MasterVO mv);
    public int deleteMaster(MasterVO mv);
    public MasterVO display(MasterVO mv);
    public List<MasterVO> displayAll();
    public MasterVO getVo ();
    public MasterVO setVo (MasterVO mv);
    public MasterVO logout ();
    public MasterVO login (MasterVO mv);
    public boolean isExist (MasterVO mv);
}
