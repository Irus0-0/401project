package review.dao;


import master.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import review.vo.ReviewVO;

import java.util.List;

public class ReviewDao implements IReviewDao{
    //singleton
    private static ReviewDao instance = null;
    private ReviewDao(){}
    public static ReviewDao getInstance(){
        if(instance == null){
            instance = new ReviewDao();
        }
        return instance;
    }
    @Override
    public int addReview(ReviewVO rv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        int result = 0;

        result = sqlSession.insert("review.add", rv);
        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    @Override
    public int updateReview(ReviewVO rv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        int result = 0;

        result = sqlSession.update("review.update", rv);
        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    @Override
    public int deleteReview(ReviewVO rv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        int result = 0;

        result = sqlSession.delete("review.delete", rv);
        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    @Override
    public ReviewVO getReview(ReviewVO rv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        ReviewVO result = null;

        result = sqlSession.selectOne("review.display", rv);
        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    @Override
    public List<ReviewVO> getReviewList() {
        SqlSession sqlSession = MybatisUtil.getInstance();
        List<ReviewVO> result = null;

        result = sqlSession.selectList("review.displayAll");
        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    public ReviewVO maxNum() {
        SqlSession sqlSession = MybatisUtil.getInstance();
        ReviewVO result = null;

        result = sqlSession.selectOne("review.maxNum");
        sqlSession.commit();
        sqlSession.close();

        return result;
    }
}
