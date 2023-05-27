package com.common;

/* 검색 기준은 실제 DB 데이터 송수신에 쓰일 객체는 아니므로 직렬화하지 않아도 무방함 */
public class SearchCriteria {

    private String condition;
    private String value;

    public SearchCriteria() {}

    public SearchCriteria(String condition, String value) {
        super();
        this.condition = condition;
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchCreteria [condition=" + condition + ", value=" + value + "]";
    }


}
