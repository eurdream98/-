package com.section01.xmlconfig;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {

    /* SqlSessionFactory는 애플리케이션을 실행하는 동안 존재해야 하며,
     * 애플리케이션이 실행되는 동안 여러 차례 SqlSessionFactory를 다시 빌드하지 않는 것이 가장 좋은 형태
     * => 애플리케이션 스코프로 관리하기 위한 가장 간단한 방법은 싱글톤 패턴을 이용하는 것
     * */
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {

        /* SqlSessionFactoryBuilder로 SqlSession을 생성한 후 유지할 필요는 없으므로 메소드 스코프로 생성
         * 여러 개의 SqlSessionFactory를 빌드하기 위해 재사용할 수도 있지만 유지하지 않는 것을 권장
         * */
        if(sqlSessionFactory == null) {
            String resource = "mybatis-config.xml";
            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /* SqlSession은 Thread에 안전하지 않으므로(멀티 스레드 환경에서 안전하게 사용 불가)
         * Thread마다 생성하며 공유되지 않도록 요청 시마다 생성해야 함
         * HTTP 요청 라이프사이클과 유사하게 요청 시 생성하고 요청 처리가 완료되면 close하는 스코프 권장 */
        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        System.out.println("sqlSessionFactory의 hashCode() : " + sqlSessionFactory.hashCode());
        System.out.println("sqlSession의 hashCode() : " + sqlSession.hashCode());

        return sqlSession;
    }
}

