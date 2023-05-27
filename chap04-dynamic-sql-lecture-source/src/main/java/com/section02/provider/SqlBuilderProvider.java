package com.section02.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.common.MenuDTO;

public class SqlBuilderProvider {

    public String registMenu(MenuDTO menu) {

        SQL sql = new SQL();

        sql.INSERT_INTO("TBL_MENU")
                .VALUES("MENU_NAME", "#{ name }")
                .VALUES("MENU_PRICE", "#{ price }")
                .VALUES("CATEGORY_CODE", "#{ categoryCode }")
                .VALUES("ORDERABLE_STATUS", "#{ orderableStatus }");

        return sql.toString();

    }

    public String modifyMenu(MenuDTO menu) {

        SQL sql = new SQL();

        /* 모든 컬럼을 업데이트하는 경우 아래처럼 작성할 수 있으나, 동적 SQL을 작성하기는 어려움 */
        /*
		sql.UPDATE("TBL_MENU")
			.SET("MENU_NAME = #{ name }"
				, "MENU_PRICE = #{ price }"
				, "CATEGORY_CODE = #{ categoryCode }"
				, "ORDERABLE_STATUS = #{ orderableStatus }")
			.WHERE("MENU_CODE = #{ code }");
         */

        /* 비어있지 않은 값에 대해서만 업데이트 하는 동적 쿼리 작성 */
        /* 이처럼 동적 쿼리를 활용하는 이유는 동적 쿼리를 활용하지 않으면
        *  1. UPDATE 대상 컬럼에 NOT NULL 제약이 있다면 전체 컬럼을 UPDATE할 경우 에러 발생할 가능성 있음
        *  2. UPDATE를 원하지 않는 컬럼까지 UPDATE 될 우려가 있음
        *  이런 문제를 피하려면 조건마다 다른 쿼리문을 작성하고 실행해야 하는데 이런 번거로움과 유지보수의 어려움을 동적 쿼리로 해결 가능 */
        sql.UPDATE("TBL_MENU");

        if(menu.getName() != null && !"".equals(menu.getName())) {
            sql.SET("MENU_NAME = #{ name }");
        }

        if(menu.getPrice() > 0) {
            sql.SET("MENU_PRICE = #{ price }");
        }

        if(menu.getCategoryCode() > 0) {
            sql.SET("CATEGORY_CODE = #{ categoryCode }");
        }

        if(menu.getOrderableStatus() != null && !"".equals(menu.getOrderableStatus())) {
            sql.SET("ORDERABLE_STATUS = #{ orderableStatus }");
        }

        sql.WHERE("MENU_CODE = #{ code }");

        return sql.toString();

    }

    /* Param Annotation으로 파라미터 전달받았으므로 메소드의 매개변수 선언부 작성하지 않음 */
    public String deleteMenu() {

        SQL sql = new SQL();

        sql.DELETE_FROM("TBL_MENU")
                .WHERE("MENU_CODE = #{ code }");

        return sql.toString();

    }
}

