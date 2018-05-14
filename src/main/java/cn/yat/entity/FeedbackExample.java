package cn.yat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeedbackExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIsNull() {
            addCriterion("feedback_type is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIsNotNull() {
            addCriterion("feedback_type is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeEqualTo(Integer value) {
            addCriterion("feedback_type =", value, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeNotEqualTo(Integer value) {
            addCriterion("feedback_type <>", value, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeGreaterThan(Integer value) {
            addCriterion("feedback_type >", value, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("feedback_type >=", value, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeLessThan(Integer value) {
            addCriterion("feedback_type <", value, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeLessThanOrEqualTo(Integer value) {
            addCriterion("feedback_type <=", value, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIn(List<Integer> values) {
            addCriterion("feedback_type in", values, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeNotIn(List<Integer> values) {
            addCriterion("feedback_type not in", values, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeBetween(Integer value1, Integer value2) {
            addCriterion("feedback_type between", value1, value2, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("feedback_type not between", value1, value2, "feedbackType");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataIsNull() {
            addCriterion("feedback_data is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataIsNotNull() {
            addCriterion("feedback_data is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataEqualTo(String value) {
            addCriterion("feedback_data =", value, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataNotEqualTo(String value) {
            addCriterion("feedback_data <>", value, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataGreaterThan(String value) {
            addCriterion("feedback_data >", value, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_data >=", value, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataLessThan(String value) {
            addCriterion("feedback_data <", value, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataLessThanOrEqualTo(String value) {
            addCriterion("feedback_data <=", value, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataLike(String value) {
            addCriterion("feedback_data like", value, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataNotLike(String value) {
            addCriterion("feedback_data not like", value, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataIn(List<String> values) {
            addCriterion("feedback_data in", values, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataNotIn(List<String> values) {
            addCriterion("feedback_data not in", values, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataBetween(String value1, String value2) {
            addCriterion("feedback_data between", value1, value2, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andFeedbackDataNotBetween(String value1, String value2) {
            addCriterion("feedback_data not between", value1, value2, "feedbackData");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andIsResolvedIsNull() {
            addCriterion("is_resolved is null");
            return (Criteria) this;
        }

        public Criteria andIsResolvedIsNotNull() {
            addCriterion("is_resolved is not null");
            return (Criteria) this;
        }

        public Criteria andIsResolvedEqualTo(Boolean value) {
            addCriterion("is_resolved =", value, "isResolved");
            return (Criteria) this;
        }

        public Criteria andIsResolvedNotEqualTo(Boolean value) {
            addCriterion("is_resolved <>", value, "isResolved");
            return (Criteria) this;
        }

        public Criteria andIsResolvedGreaterThan(Boolean value) {
            addCriterion("is_resolved >", value, "isResolved");
            return (Criteria) this;
        }

        public Criteria andIsResolvedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_resolved >=", value, "isResolved");
            return (Criteria) this;
        }

        public Criteria andIsResolvedLessThan(Boolean value) {
            addCriterion("is_resolved <", value, "isResolved");
            return (Criteria) this;
        }

        public Criteria andIsResolvedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_resolved <=", value, "isResolved");
            return (Criteria) this;
        }

        public Criteria andIsResolvedIn(List<Boolean> values) {
            addCriterion("is_resolved in", values, "isResolved");
            return (Criteria) this;
        }

        public Criteria andIsResolvedNotIn(List<Boolean> values) {
            addCriterion("is_resolved not in", values, "isResolved");
            return (Criteria) this;
        }

        public Criteria andIsResolvedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_resolved between", value1, value2, "isResolved");
            return (Criteria) this;
        }

        public Criteria andIsResolvedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_resolved not between", value1, value2, "isResolved");
            return (Criteria) this;
        }

        public Criteria andResolveTimeIsNull() {
            addCriterion("resolve_time is null");
            return (Criteria) this;
        }

        public Criteria andResolveTimeIsNotNull() {
            addCriterion("resolve_time is not null");
            return (Criteria) this;
        }

        public Criteria andResolveTimeEqualTo(Date value) {
            addCriterion("resolve_time =", value, "resolveTime");
            return (Criteria) this;
        }

        public Criteria andResolveTimeNotEqualTo(Date value) {
            addCriterion("resolve_time <>", value, "resolveTime");
            return (Criteria) this;
        }

        public Criteria andResolveTimeGreaterThan(Date value) {
            addCriterion("resolve_time >", value, "resolveTime");
            return (Criteria) this;
        }

        public Criteria andResolveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("resolve_time >=", value, "resolveTime");
            return (Criteria) this;
        }

        public Criteria andResolveTimeLessThan(Date value) {
            addCriterion("resolve_time <", value, "resolveTime");
            return (Criteria) this;
        }

        public Criteria andResolveTimeLessThanOrEqualTo(Date value) {
            addCriterion("resolve_time <=", value, "resolveTime");
            return (Criteria) this;
        }

        public Criteria andResolveTimeIn(List<Date> values) {
            addCriterion("resolve_time in", values, "resolveTime");
            return (Criteria) this;
        }

        public Criteria andResolveTimeNotIn(List<Date> values) {
            addCriterion("resolve_time not in", values, "resolveTime");
            return (Criteria) this;
        }

        public Criteria andResolveTimeBetween(Date value1, Date value2) {
            addCriterion("resolve_time between", value1, value2, "resolveTime");
            return (Criteria) this;
        }

        public Criteria andResolveTimeNotBetween(Date value1, Date value2) {
            addCriterion("resolve_time not between", value1, value2, "resolveTime");
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