package com.section02.provider;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import com.common.MenuDTO;

public interface SqlBuilderMapper {

    @InsertProvider(type=SqlBuilderProvider.class, method="registMenu")
    int registMenu(MenuDTO menu);

    @UpdateProvider(type=SqlBuilderProvider.class, method="modifyMenu")
    int modifyMenu(MenuDTO menu);

    /* Map이나 getter가 있는 DTO가 아닌 기본 자료형 값을 전달해야 하는 경우 param 어노테이션을 이용
     * 전달해야 하는 값이 2개 이상인 경우도 파람 어노테이션의 key값으로 value값을 찾을 수 있음
     * 단, Provider 메소드의 "매개변수 선언부"는 없어야 함
     * */
    @DeleteProvider(type=SqlBuilderProvider.class, method="deleteMenu")
    int deleteMenu(@Param("code") int code);

}

