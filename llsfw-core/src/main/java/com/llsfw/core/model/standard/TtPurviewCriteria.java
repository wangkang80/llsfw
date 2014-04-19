package com.llsfw.core.model.standard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TtPurviewCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public TtPurviewCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPurviewCodeIsNull() {
            addCriterion("PURVIEW_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeIsNotNull() {
            addCriterion("PURVIEW_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeEqualTo(String value) {
            addCriterion("PURVIEW_CODE =", value, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeNotEqualTo(String value) {
            addCriterion("PURVIEW_CODE <>", value, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeGreaterThan(String value) {
            addCriterion("PURVIEW_CODE >", value, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PURVIEW_CODE >=", value, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeLessThan(String value) {
            addCriterion("PURVIEW_CODE <", value, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeLessThanOrEqualTo(String value) {
            addCriterion("PURVIEW_CODE <=", value, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeLike(String value) {
            addCriterion("PURVIEW_CODE like", value, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeNotLike(String value) {
            addCriterion("PURVIEW_CODE not like", value, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeIn(List<String> values) {
            addCriterion("PURVIEW_CODE in", values, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeNotIn(List<String> values) {
            addCriterion("PURVIEW_CODE not in", values, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeBetween(String value1, String value2) {
            addCriterion("PURVIEW_CODE between", value1, value2, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeNotBetween(String value1, String value2) {
            addCriterion("PURVIEW_CODE not between", value1, value2, "purviewCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIsNull() {
            addCriterion("FUNCTION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIsNotNull() {
            addCriterion("FUNCTION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeEqualTo(String value) {
            addCriterion("FUNCTION_CODE =", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotEqualTo(String value) {
            addCriterion("FUNCTION_CODE <>", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeGreaterThan(String value) {
            addCriterion("FUNCTION_CODE >", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCTION_CODE >=", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLessThan(String value) {
            addCriterion("FUNCTION_CODE <", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLessThanOrEqualTo(String value) {
            addCriterion("FUNCTION_CODE <=", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLike(String value) {
            addCriterion("FUNCTION_CODE like", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotLike(String value) {
            addCriterion("FUNCTION_CODE not like", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIn(List<String> values) {
            addCriterion("FUNCTION_CODE in", values, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotIn(List<String> values) {
            addCriterion("FUNCTION_CODE not in", values, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeBetween(String value1, String value2) {
            addCriterion("FUNCTION_CODE between", value1, value2, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotBetween(String value1, String value2) {
            addCriterion("FUNCTION_CODE not between", value1, value2, "functionCode");
            return (Criteria) this;
        }

        public Criteria andPurviewNameIsNull() {
            addCriterion("PURVIEW_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPurviewNameIsNotNull() {
            addCriterion("PURVIEW_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPurviewNameEqualTo(String value) {
            addCriterion("PURVIEW_NAME =", value, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameNotEqualTo(String value) {
            addCriterion("PURVIEW_NAME <>", value, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameGreaterThan(String value) {
            addCriterion("PURVIEW_NAME >", value, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameGreaterThanOrEqualTo(String value) {
            addCriterion("PURVIEW_NAME >=", value, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameLessThan(String value) {
            addCriterion("PURVIEW_NAME <", value, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameLessThanOrEqualTo(String value) {
            addCriterion("PURVIEW_NAME <=", value, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameLike(String value) {
            addCriterion("PURVIEW_NAME like", value, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameNotLike(String value) {
            addCriterion("PURVIEW_NAME not like", value, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameIn(List<String> values) {
            addCriterion("PURVIEW_NAME in", values, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameNotIn(List<String> values) {
            addCriterion("PURVIEW_NAME not in", values, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameBetween(String value1, String value2) {
            addCriterion("PURVIEW_NAME between", value1, value2, "purviewName");
            return (Criteria) this;
        }

        public Criteria andPurviewNameNotBetween(String value1, String value2) {
            addCriterion("PURVIEW_NAME not between", value1, value2, "purviewName");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andPurviewCodeLikeInsensitive(String value) {
            addCriterion("upper(PURVIEW_CODE) like", value.toUpperCase(), "purviewCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLikeInsensitive(String value) {
            addCriterion("upper(FUNCTION_CODE) like", value.toUpperCase(), "functionCode");
            return (Criteria) this;
        }

        public Criteria andPurviewNameLikeInsensitive(String value) {
            addCriterion("upper(PURVIEW_NAME) like", value.toUpperCase(), "purviewName");
            return (Criteria) this;
        }

        public Criteria andCreateByLikeInsensitive(String value) {
            addCriterion("upper(CREATE_BY) like", value.toUpperCase(), "createBy");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated do_not_delete_during_merge Sat Apr 19 13:01:25 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TT_PURVIEW
     *
     * @mbggenerated Sat Apr 19 13:01:25 CST 2014
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}