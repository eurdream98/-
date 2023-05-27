package com.section02.provider;

import org.apache.ibatis.jdbc.SQL;

import com.common.SearchCriteria;

public class SelectBuilderProvider {

    /* SQL문을 문자열 형태로 반환하도록 반환타입 지정 */

    public String selectAllMenu() {

        /* 참고.
         * static 메소드 형태로 SELECT() 이런 메소드가 있었는데 전부 deprecated 되어 있다.
         * 최근에는 Builder 패턴을 이용한 형태로 사용하고 있으며, MyBatis 3.2버전 이전에는 다른 방법을 사용했다.
         * (공식 문서 발췌 내용) 최근에는 빌더 타입의 패턴과 익명 내부 클래스를 사용하는 추세이다. 그러므로 SelectBuilder 와 SqlBuilder는 향후 제거할 예정이다.
         * */
        return new SQL()
                .SELECT("A.MENU_CODE")
                .SELECT("A.MENU_NAME")
                .SELECT("A.MENU_PRICE")
                .SELECT("A.CATEGORY_CODE")
                .SELECT("A.ORDERABLE_STATUS")
                .FROM("TBL_MENU A")
                .WHERE("A.ORDERABLE_STATUS = 'Y'").toString();
    }

    public String searchMenuByCondition(SearchCriteria searchCriteria) {

        SQL sql = new SQL();

        sql.SELECT("A.MENU_CODE")
                .SELECT("A.MENU_NAME")
                .SELECT("A.MENU_PRICE")
                .SELECT("A.CATEGORY_CODE")
                .SELECT("A.ORDERABLE_STATUS")
                .FROM("TBL_MENU A");

        if("category".equals(searchCriteria.getCondition())) {
            sql.JOIN("TBL_CATEGORY B ON (A.CATEGORY_CODE = B.CATEGORY_CODE)")
                    .WHERE("A.ORDERABLE_STATUS = 'Y'")
                    .AND()
                    .WHERE("B.CATEGORY_NAME = #{ value }");

        } else if("name".equals(searchCriteria.getCondition())) {
            /* 가변인자를 이용하면 자동 AND로 처리하기 때문에 OR를 사용해야 하는 경우 OR를 이용해야 함 */
            sql.WHERE("A.ORDERABLE_STATUS = 'Y'"
                    , "A.MENU_NAME LIKE CONCAT('%', #{ value }, '%')");
        }

        return sql.toString();
    }
}
