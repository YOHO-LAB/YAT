package cn.yat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParameterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ParameterExample() {
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

        public Criteria andEnvIdIsNull() {
            addCriterion("env_id is null");
            return (Criteria) this;
        }

        public Criteria andEnvIdIsNotNull() {
            addCriterion("env_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnvIdEqualTo(Integer value) {
            addCriterion("env_id =", value, "envId");
            return (Criteria) this;
        }

        public Criteria andEnvIdNotEqualTo(Integer value) {
            addCriterion("env_id <>", value, "envId");
            return (Criteria) this;
        }

        public Criteria andEnvIdGreaterThan(Integer value) {
            addCriterion("env_id >", value, "envId");
            return (Criteria) this;
        }

        public Criteria andEnvIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("env_id >=", value, "envId");
            return (Criteria) this;
        }

        public Criteria andEnvIdLessThan(Integer value) {
            addCriterion("env_id <", value, "envId");
            return (Criteria) this;
        }

        public Criteria andEnvIdLessThanOrEqualTo(Integer value) {
            addCriterion("env_id <=", value, "envId");
            return (Criteria) this;
        }

        public Criteria andEnvIdIn(List<Integer> values) {
            addCriterion("env_id in", values, "envId");
            return (Criteria) this;
        }

        public Criteria andEnvIdNotIn(List<Integer> values) {
            addCriterion("env_id not in", values, "envId");
            return (Criteria) this;
        }

        public Criteria andEnvIdBetween(Integer value1, Integer value2) {
            addCriterion("env_id between", value1, value2, "envId");
            return (Criteria) this;
        }

        public Criteria andEnvIdNotBetween(Integer value1, Integer value2) {
            addCriterion("env_id not between", value1, value2, "envId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNull() {
            addCriterion("param_type is null");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNotNull() {
            addCriterion("param_type is not null");
            return (Criteria) this;
        }

        public Criteria andParamTypeEqualTo(Integer value) {
            addCriterion("param_type =", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotEqualTo(Integer value) {
            addCriterion("param_type <>", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThan(Integer value) {
            addCriterion("param_type >", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("param_type >=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThan(Integer value) {
            addCriterion("param_type <", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThanOrEqualTo(Integer value) {
            addCriterion("param_type <=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeIn(List<Integer> values) {
            addCriterion("param_type in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotIn(List<Integer> values) {
            addCriterion("param_type not in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeBetween(Integer value1, Integer value2) {
            addCriterion("param_type between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("param_type not between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andAddUserIdIsNull() {
            addCriterion("add_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAddUserIdIsNotNull() {
            addCriterion("add_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddUserIdEqualTo(Integer value) {
            addCriterion("add_user_id =", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdNotEqualTo(Integer value) {
            addCriterion("add_user_id <>", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdGreaterThan(Integer value) {
            addCriterion("add_user_id >", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("add_user_id >=", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdLessThan(Integer value) {
            addCriterion("add_user_id <", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("add_user_id <=", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdIn(List<Integer> values) {
            addCriterion("add_user_id in", values, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdNotIn(List<Integer> values) {
            addCriterion("add_user_id not in", values, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdBetween(Integer value1, Integer value2) {
            addCriterion("add_user_id between", value1, value2, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("add_user_id not between", value1, value2, "addUserId");
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

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andKvValIsNull() {
            addCriterion("kv_val is null");
            return (Criteria) this;
        }

        public Criteria andKvValIsNotNull() {
            addCriterion("kv_val is not null");
            return (Criteria) this;
        }

        public Criteria andKvValEqualTo(String value) {
            addCriterion("kv_val =", value, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValNotEqualTo(String value) {
            addCriterion("kv_val <>", value, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValGreaterThan(String value) {
            addCriterion("kv_val >", value, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValGreaterThanOrEqualTo(String value) {
            addCriterion("kv_val >=", value, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValLessThan(String value) {
            addCriterion("kv_val <", value, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValLessThanOrEqualTo(String value) {
            addCriterion("kv_val <=", value, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValLike(String value) {
            addCriterion("kv_val like", value, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValNotLike(String value) {
            addCriterion("kv_val not like", value, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValIn(List<String> values) {
            addCriterion("kv_val in", values, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValNotIn(List<String> values) {
            addCriterion("kv_val not in", values, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValBetween(String value1, String value2) {
            addCriterion("kv_val between", value1, value2, "kvVal");
            return (Criteria) this;
        }

        public Criteria andKvValNotBetween(String value1, String value2) {
            addCriterion("kv_val not between", value1, value2, "kvVal");
            return (Criteria) this;
        }

        public Criteria andDbIdIsNull() {
            addCriterion("db_id is null");
            return (Criteria) this;
        }

        public Criteria andDbIdIsNotNull() {
            addCriterion("db_id is not null");
            return (Criteria) this;
        }

        public Criteria andDbIdEqualTo(Integer value) {
            addCriterion("db_id =", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotEqualTo(Integer value) {
            addCriterion("db_id <>", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdGreaterThan(Integer value) {
            addCriterion("db_id >", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("db_id >=", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdLessThan(Integer value) {
            addCriterion("db_id <", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdLessThanOrEqualTo(Integer value) {
            addCriterion("db_id <=", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdIn(List<Integer> values) {
            addCriterion("db_id in", values, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotIn(List<Integer> values) {
            addCriterion("db_id not in", values, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdBetween(Integer value1, Integer value2) {
            addCriterion("db_id between", value1, value2, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotBetween(Integer value1, Integer value2) {
            addCriterion("db_id not between", value1, value2, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbSqlIsNull() {
            addCriterion("db_sql is null");
            return (Criteria) this;
        }

        public Criteria andDbSqlIsNotNull() {
            addCriterion("db_sql is not null");
            return (Criteria) this;
        }

        public Criteria andDbSqlEqualTo(String value) {
            addCriterion("db_sql =", value, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlNotEqualTo(String value) {
            addCriterion("db_sql <>", value, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlGreaterThan(String value) {
            addCriterion("db_sql >", value, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlGreaterThanOrEqualTo(String value) {
            addCriterion("db_sql >=", value, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlLessThan(String value) {
            addCriterion("db_sql <", value, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlLessThanOrEqualTo(String value) {
            addCriterion("db_sql <=", value, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlLike(String value) {
            addCriterion("db_sql like", value, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlNotLike(String value) {
            addCriterion("db_sql not like", value, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlIn(List<String> values) {
            addCriterion("db_sql in", values, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlNotIn(List<String> values) {
            addCriterion("db_sql not in", values, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlBetween(String value1, String value2) {
            addCriterion("db_sql between", value1, value2, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbSqlNotBetween(String value1, String value2) {
            addCriterion("db_sql not between", value1, value2, "dbSql");
            return (Criteria) this;
        }

        public Criteria andDbColumnIsNull() {
            addCriterion("db_column is null");
            return (Criteria) this;
        }

        public Criteria andDbColumnIsNotNull() {
            addCriterion("db_column is not null");
            return (Criteria) this;
        }

        public Criteria andDbColumnEqualTo(String value) {
            addCriterion("db_column =", value, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnNotEqualTo(String value) {
            addCriterion("db_column <>", value, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnGreaterThan(String value) {
            addCriterion("db_column >", value, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnGreaterThanOrEqualTo(String value) {
            addCriterion("db_column >=", value, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnLessThan(String value) {
            addCriterion("db_column <", value, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnLessThanOrEqualTo(String value) {
            addCriterion("db_column <=", value, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnLike(String value) {
            addCriterion("db_column like", value, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnNotLike(String value) {
            addCriterion("db_column not like", value, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnIn(List<String> values) {
            addCriterion("db_column in", values, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnNotIn(List<String> values) {
            addCriterion("db_column not in", values, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnBetween(String value1, String value2) {
            addCriterion("db_column between", value1, value2, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbColumnNotBetween(String value1, String value2) {
            addCriterion("db_column not between", value1, value2, "dbColumn");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeIsNull() {
            addCriterion("db_get_val_type is null");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeIsNotNull() {
            addCriterion("db_get_val_type is not null");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeEqualTo(Integer value) {
            addCriterion("db_get_val_type =", value, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeNotEqualTo(Integer value) {
            addCriterion("db_get_val_type <>", value, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeGreaterThan(Integer value) {
            addCriterion("db_get_val_type >", value, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("db_get_val_type >=", value, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeLessThan(Integer value) {
            addCriterion("db_get_val_type <", value, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeLessThanOrEqualTo(Integer value) {
            addCriterion("db_get_val_type <=", value, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeIn(List<Integer> values) {
            addCriterion("db_get_val_type in", values, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeNotIn(List<Integer> values) {
            addCriterion("db_get_val_type not in", values, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeBetween(Integer value1, Integer value2) {
            addCriterion("db_get_val_type between", value1, value2, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andDbGetValTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("db_get_val_type not between", value1, value2, "dbGetValType");
            return (Criteria) this;
        }

        public Criteria andTcIdIsNull() {
            addCriterion("tc_id is null");
            return (Criteria) this;
        }

        public Criteria andTcIdIsNotNull() {
            addCriterion("tc_id is not null");
            return (Criteria) this;
        }

        public Criteria andTcIdEqualTo(Integer value) {
            addCriterion("tc_id =", value, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcIdNotEqualTo(Integer value) {
            addCriterion("tc_id <>", value, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcIdGreaterThan(Integer value) {
            addCriterion("tc_id >", value, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tc_id >=", value, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcIdLessThan(Integer value) {
            addCriterion("tc_id <", value, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcIdLessThanOrEqualTo(Integer value) {
            addCriterion("tc_id <=", value, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcIdIn(List<Integer> values) {
            addCriterion("tc_id in", values, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcIdNotIn(List<Integer> values) {
            addCriterion("tc_id not in", values, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcIdBetween(Integer value1, Integer value2) {
            addCriterion("tc_id between", value1, value2, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tc_id not between", value1, value2, "tcId");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeIsNull() {
            addCriterion("tc_get_val_type is null");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeIsNotNull() {
            addCriterion("tc_get_val_type is not null");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeEqualTo(Integer value) {
            addCriterion("tc_get_val_type =", value, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeNotEqualTo(Integer value) {
            addCriterion("tc_get_val_type <>", value, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeGreaterThan(Integer value) {
            addCriterion("tc_get_val_type >", value, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tc_get_val_type >=", value, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeLessThan(Integer value) {
            addCriterion("tc_get_val_type <", value, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeLessThanOrEqualTo(Integer value) {
            addCriterion("tc_get_val_type <=", value, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeIn(List<Integer> values) {
            addCriterion("tc_get_val_type in", values, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeNotIn(List<Integer> values) {
            addCriterion("tc_get_val_type not in", values, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeBetween(Integer value1, Integer value2) {
            addCriterion("tc_get_val_type between", value1, value2, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcGetValTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("tc_get_val_type not between", value1, value2, "tcGetValType");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathIsNull() {
            addCriterion("tc_json_path is null");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathIsNotNull() {
            addCriterion("tc_json_path is not null");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathEqualTo(String value) {
            addCriterion("tc_json_path =", value, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathNotEqualTo(String value) {
            addCriterion("tc_json_path <>", value, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathGreaterThan(String value) {
            addCriterion("tc_json_path >", value, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathGreaterThanOrEqualTo(String value) {
            addCriterion("tc_json_path >=", value, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathLessThan(String value) {
            addCriterion("tc_json_path <", value, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathLessThanOrEqualTo(String value) {
            addCriterion("tc_json_path <=", value, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathLike(String value) {
            addCriterion("tc_json_path like", value, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathNotLike(String value) {
            addCriterion("tc_json_path not like", value, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathIn(List<String> values) {
            addCriterion("tc_json_path in", values, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathNotIn(List<String> values) {
            addCriterion("tc_json_path not in", values, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathBetween(String value1, String value2) {
            addCriterion("tc_json_path between", value1, value2, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonPathNotBetween(String value1, String value2) {
            addCriterion("tc_json_path not between", value1, value2, "tcJsonPath");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumIsNull() {
            addCriterion("tc_json_match_num is null");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumIsNotNull() {
            addCriterion("tc_json_match_num is not null");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumEqualTo(Integer value) {
            addCriterion("tc_json_match_num =", value, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumNotEqualTo(Integer value) {
            addCriterion("tc_json_match_num <>", value, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumGreaterThan(Integer value) {
            addCriterion("tc_json_match_num >", value, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("tc_json_match_num >=", value, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumLessThan(Integer value) {
            addCriterion("tc_json_match_num <", value, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumLessThanOrEqualTo(Integer value) {
            addCriterion("tc_json_match_num <=", value, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumIn(List<Integer> values) {
            addCriterion("tc_json_match_num in", values, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumNotIn(List<Integer> values) {
            addCriterion("tc_json_match_num not in", values, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumBetween(Integer value1, Integer value2) {
            addCriterion("tc_json_match_num between", value1, value2, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcJsonMatchNumNotBetween(Integer value1, Integer value2) {
            addCriterion("tc_json_match_num not between", value1, value2, "tcJsonMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLeftIsNull() {
            addCriterion("tc_left is null");
            return (Criteria) this;
        }

        public Criteria andTcLeftIsNotNull() {
            addCriterion("tc_left is not null");
            return (Criteria) this;
        }

        public Criteria andTcLeftEqualTo(String value) {
            addCriterion("tc_left =", value, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftNotEqualTo(String value) {
            addCriterion("tc_left <>", value, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftGreaterThan(String value) {
            addCriterion("tc_left >", value, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftGreaterThanOrEqualTo(String value) {
            addCriterion("tc_left >=", value, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftLessThan(String value) {
            addCriterion("tc_left <", value, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftLessThanOrEqualTo(String value) {
            addCriterion("tc_left <=", value, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftLike(String value) {
            addCriterion("tc_left like", value, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftNotLike(String value) {
            addCriterion("tc_left not like", value, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftIn(List<String> values) {
            addCriterion("tc_left in", values, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftNotIn(List<String> values) {
            addCriterion("tc_left not in", values, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftBetween(String value1, String value2) {
            addCriterion("tc_left between", value1, value2, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcLeftNotBetween(String value1, String value2) {
            addCriterion("tc_left not between", value1, value2, "tcLeft");
            return (Criteria) this;
        }

        public Criteria andTcRightIsNull() {
            addCriterion("tc_right is null");
            return (Criteria) this;
        }

        public Criteria andTcRightIsNotNull() {
            addCriterion("tc_right is not null");
            return (Criteria) this;
        }

        public Criteria andTcRightEqualTo(String value) {
            addCriterion("tc_right =", value, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightNotEqualTo(String value) {
            addCriterion("tc_right <>", value, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightGreaterThan(String value) {
            addCriterion("tc_right >", value, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightGreaterThanOrEqualTo(String value) {
            addCriterion("tc_right >=", value, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightLessThan(String value) {
            addCriterion("tc_right <", value, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightLessThanOrEqualTo(String value) {
            addCriterion("tc_right <=", value, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightLike(String value) {
            addCriterion("tc_right like", value, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightNotLike(String value) {
            addCriterion("tc_right not like", value, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightIn(List<String> values) {
            addCriterion("tc_right in", values, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightNotIn(List<String> values) {
            addCriterion("tc_right not in", values, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightBetween(String value1, String value2) {
            addCriterion("tc_right between", value1, value2, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcRightNotBetween(String value1, String value2) {
            addCriterion("tc_right not between", value1, value2, "tcRight");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumIsNull() {
            addCriterion("tc_lr_match_num is null");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumIsNotNull() {
            addCriterion("tc_lr_match_num is not null");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumEqualTo(Integer value) {
            addCriterion("tc_lr_match_num =", value, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumNotEqualTo(Integer value) {
            addCriterion("tc_lr_match_num <>", value, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumGreaterThan(Integer value) {
            addCriterion("tc_lr_match_num >", value, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("tc_lr_match_num >=", value, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumLessThan(Integer value) {
            addCriterion("tc_lr_match_num <", value, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumLessThanOrEqualTo(Integer value) {
            addCriterion("tc_lr_match_num <=", value, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumIn(List<Integer> values) {
            addCriterion("tc_lr_match_num in", values, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumNotIn(List<Integer> values) {
            addCriterion("tc_lr_match_num not in", values, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumBetween(Integer value1, Integer value2) {
            addCriterion("tc_lr_match_num between", value1, value2, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcLrMatchNumNotBetween(Integer value1, Integer value2) {
            addCriterion("tc_lr_match_num not between", value1, value2, "tcLrMatchNum");
            return (Criteria) this;
        }

        public Criteria andTcCookieIsNull() {
            addCriterion("tc_cookie is null");
            return (Criteria) this;
        }

        public Criteria andTcCookieIsNotNull() {
            addCriterion("tc_cookie is not null");
            return (Criteria) this;
        }

        public Criteria andTcCookieEqualTo(String value) {
            addCriterion("tc_cookie =", value, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieNotEqualTo(String value) {
            addCriterion("tc_cookie <>", value, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieGreaterThan(String value) {
            addCriterion("tc_cookie >", value, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieGreaterThanOrEqualTo(String value) {
            addCriterion("tc_cookie >=", value, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieLessThan(String value) {
            addCriterion("tc_cookie <", value, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieLessThanOrEqualTo(String value) {
            addCriterion("tc_cookie <=", value, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieLike(String value) {
            addCriterion("tc_cookie like", value, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieNotLike(String value) {
            addCriterion("tc_cookie not like", value, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieIn(List<String> values) {
            addCriterion("tc_cookie in", values, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieNotIn(List<String> values) {
            addCriterion("tc_cookie not in", values, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieBetween(String value1, String value2) {
            addCriterion("tc_cookie between", value1, value2, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcCookieNotBetween(String value1, String value2) {
            addCriterion("tc_cookie not between", value1, value2, "tcCookie");
            return (Criteria) this;
        }

        public Criteria andTcHeaderIsNull() {
            addCriterion("tc_header is null");
            return (Criteria) this;
        }

        public Criteria andTcHeaderIsNotNull() {
            addCriterion("tc_header is not null");
            return (Criteria) this;
        }

        public Criteria andTcHeaderEqualTo(String value) {
            addCriterion("tc_header =", value, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderNotEqualTo(String value) {
            addCriterion("tc_header <>", value, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderGreaterThan(String value) {
            addCriterion("tc_header >", value, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderGreaterThanOrEqualTo(String value) {
            addCriterion("tc_header >=", value, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderLessThan(String value) {
            addCriterion("tc_header <", value, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderLessThanOrEqualTo(String value) {
            addCriterion("tc_header <=", value, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderLike(String value) {
            addCriterion("tc_header like", value, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderNotLike(String value) {
            addCriterion("tc_header not like", value, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderIn(List<String> values) {
            addCriterion("tc_header in", values, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderNotIn(List<String> values) {
            addCriterion("tc_header not in", values, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderBetween(String value1, String value2) {
            addCriterion("tc_header between", value1, value2, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andTcHeaderNotBetween(String value1, String value2) {
            addCriterion("tc_header not between", value1, value2, "tcHeader");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultIsNull() {
            addCriterion("is_use_default is null");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultIsNotNull() {
            addCriterion("is_use_default is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultEqualTo(Integer value) {
            addCriterion("is_use_default =", value, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultNotEqualTo(Integer value) {
            addCriterion("is_use_default <>", value, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultGreaterThan(Integer value) {
            addCriterion("is_use_default >", value, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_use_default >=", value, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultLessThan(Integer value) {
            addCriterion("is_use_default <", value, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultLessThanOrEqualTo(Integer value) {
            addCriterion("is_use_default <=", value, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultIn(List<Integer> values) {
            addCriterion("is_use_default in", values, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultNotIn(List<Integer> values) {
            addCriterion("is_use_default not in", values, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultBetween(Integer value1, Integer value2) {
            addCriterion("is_use_default between", value1, value2, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andIsUseDefaultNotBetween(Integer value1, Integer value2) {
            addCriterion("is_use_default not between", value1, value2, "isUseDefault");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNull() {
            addCriterion("default_value is null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNotNull() {
            addCriterion("default_value is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualTo(String value) {
            addCriterion("default_value =", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualTo(String value) {
            addCriterion("default_value <>", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThan(String value) {
            addCriterion("default_value >", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("default_value >=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThan(String value) {
            addCriterion("default_value <", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("default_value <=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(String value) {
            addCriterion("default_value like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotLike(String value) {
            addCriterion("default_value not like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIn(List<String> values) {
            addCriterion("default_value in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotIn(List<String> values) {
            addCriterion("default_value not in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueBetween(String value1, String value2) {
            addCriterion("default_value between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotBetween(String value1, String value2) {
            addCriterion("default_value not between", value1, value2, "defaultValue");
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