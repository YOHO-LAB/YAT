package cn.yat.entity;

import java.util.ArrayList;
import java.util.List;

public class DataSourceLoopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataSourceLoopExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andCaseIdIsNull() {
            addCriterion("case_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNotNull() {
            addCriterion("case_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseIdEqualTo(Integer value) {
            addCriterion("case_id =", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotEqualTo(Integer value) {
            addCriterion("case_id <>", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThan(Integer value) {
            addCriterion("case_id >", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_id >=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThan(Integer value) {
            addCriterion("case_id <", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_id <=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdIn(List<Integer> values) {
            addCriterion("case_id in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotIn(List<Integer> values) {
            addCriterion("case_id not in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdBetween(Integer value1, Integer value2) {
            addCriterion("case_id between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_id not between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andThListIsNull() {
            addCriterion("th_list is null");
            return (Criteria) this;
        }

        public Criteria andThListIsNotNull() {
            addCriterion("th_list is not null");
            return (Criteria) this;
        }

        public Criteria andThListEqualTo(String value) {
            addCriterion("th_list =", value, "thList");
            return (Criteria) this;
        }

        public Criteria andThListNotEqualTo(String value) {
            addCriterion("th_list <>", value, "thList");
            return (Criteria) this;
        }

        public Criteria andThListGreaterThan(String value) {
            addCriterion("th_list >", value, "thList");
            return (Criteria) this;
        }

        public Criteria andThListGreaterThanOrEqualTo(String value) {
            addCriterion("th_list >=", value, "thList");
            return (Criteria) this;
        }

        public Criteria andThListLessThan(String value) {
            addCriterion("th_list <", value, "thList");
            return (Criteria) this;
        }

        public Criteria andThListLessThanOrEqualTo(String value) {
            addCriterion("th_list <=", value, "thList");
            return (Criteria) this;
        }

        public Criteria andThListLike(String value) {
            addCriterion("th_list like", value, "thList");
            return (Criteria) this;
        }

        public Criteria andThListNotLike(String value) {
            addCriterion("th_list not like", value, "thList");
            return (Criteria) this;
        }

        public Criteria andThListIn(List<String> values) {
            addCriterion("th_list in", values, "thList");
            return (Criteria) this;
        }

        public Criteria andThListNotIn(List<String> values) {
            addCriterion("th_list not in", values, "thList");
            return (Criteria) this;
        }

        public Criteria andThListBetween(String value1, String value2) {
            addCriterion("th_list between", value1, value2, "thList");
            return (Criteria) this;
        }

        public Criteria andThListNotBetween(String value1, String value2) {
            addCriterion("th_list not between", value1, value2, "thList");
            return (Criteria) this;
        }

        public Criteria andTrListIsNull() {
            addCriterion("tr_list is null");
            return (Criteria) this;
        }

        public Criteria andTrListIsNotNull() {
            addCriterion("tr_list is not null");
            return (Criteria) this;
        }

        public Criteria andTrListEqualTo(String value) {
            addCriterion("tr_list =", value, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListNotEqualTo(String value) {
            addCriterion("tr_list <>", value, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListGreaterThan(String value) {
            addCriterion("tr_list >", value, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListGreaterThanOrEqualTo(String value) {
            addCriterion("tr_list >=", value, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListLessThan(String value) {
            addCriterion("tr_list <", value, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListLessThanOrEqualTo(String value) {
            addCriterion("tr_list <=", value, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListLike(String value) {
            addCriterion("tr_list like", value, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListNotLike(String value) {
            addCriterion("tr_list not like", value, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListIn(List<String> values) {
            addCriterion("tr_list in", values, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListNotIn(List<String> values) {
            addCriterion("tr_list not in", values, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListBetween(String value1, String value2) {
            addCriterion("tr_list between", value1, value2, "trList");
            return (Criteria) this;
        }

        public Criteria andTrListNotBetween(String value1, String value2) {
            addCriterion("tr_list not between", value1, value2, "trList");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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