package kr.or.ddit.teampro.master.service;

import jdk.nashorn.internal.objects.annotations.Setter;
import kr.or.ddit.teampro.master.dao.MasterDao;
import kr.or.ddit.teampro.master.vo.MasterVO;

import java.util.List;

public class MasterService implements IMasterService{

    private MasterVO mv;

    //singleton
    private static MasterService instance = null;

    public static MasterService getInstance() {
        if (instance == null) {
            instance = new MasterService();
        }
        return instance;
    }

    @Override
    public int insertMaster(MasterVO mv) {
        return MasterDao.getInstance().insertMaster(mv);
    }

    @Override
    public int updateMaster(MasterVO mv) {
        return MasterDao.getInstance().updateMaster(mv);
    }

    @Override
    public int deleteMaster(MasterVO mv) {
        return MasterDao.getInstance().deleteMaster(mv);
    }

    @Override
    public MasterVO display(MasterVO mv) {
        return MasterDao.getInstance().display(mv);
    }

    @Override
    public List<MasterVO> displayAll() {
        return MasterDao.getInstance().displayAll();
    }

    @Override
    public MasterVO getVo() {
        return this.mv;
    }

    @Override
    public MasterVO setVo(MasterVO mv) {
        this.mv = mv;
        return mv;
    }

    @Override
    public MasterVO logout() {
        setVo(null);
        return this.mv;
    }

    @Override
    public MasterVO login(MasterVO mv) {
        return MasterDao.getInstance().login(mv);
    }

    @Override
    public boolean isExist(MasterVO mv) {
        return MasterDao.getInstance().isExist(mv);
    }

}
