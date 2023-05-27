package com.section01.javaconfig;

import org.apache.ibatis.annotations.Select;

public interface Mapper {

    /* Select Annotation 내부에 쿼리 작성
    *  메소드 선언부는 조회 결과 반환 타입과 호출할 쿼리ID */
    @Select("SELECT CURDATE() FROM DUAL")
    java.util.Date selectSysdate();

}
