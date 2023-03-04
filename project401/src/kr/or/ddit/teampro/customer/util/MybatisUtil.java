package kr.or.ddit.teampro.customer.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

public class MybatisUtil {
    //sqlSessionFactory 생성
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        //xml 문서 읽어오기
        Charset charset = Charset.forName("UTF-8");

        try {
            Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);

            rd.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static SqlSession getInstance() {
        return sqlSessionFactory.openSession();
    }

    public static SqlSession getInstance(Boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }

}
