package cn.yat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperationExample() {
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

        public Criteria andOpsTypeIsNull() {
            addCriterion("ops_type is null");
            return (Criteria) this;
        }

        public Criteria andOpsTypeIsNotNull() {
            addCriterion("ops_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpsTypeEqualTo(Integer value) {
            addCriterion("ops_type =", value, "opsType");
            return (Criteria) this;
        }

        public Criteria andOpsTypeNotEqualTo(Integer value) {
            addCriterion("ops_type <>", value, "opsType");
            return (Criteria) this;
        }

        public Criteria andOpsTypeGreaterThan(Integer value) {
            addCriterion("ops_type >", value, "opsType");
            return (Criteria) this;
        }

        public Criteria andOpsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ops_type >=", value, "opsType");
            return (Criteria) this;
        }

        public Criteria andOpsTypeLessThan(Integer value) {
            addCriterion("ops_type <", value, "opsType");
            return (Criteria) this;
        }

        public Criteria andOpsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ops_type <=", value, "opsType");
            return (Criteria) this;
        }

        public Criteria andOpsTypeIn(List<Integer> values) {
            addCriterion("ops_type in", values, "opsType");
            return (Criteria) this;
        }

        public Criteria andOpsTypeNotIn(List<Integer> values) {
            addCriterion("ops_type not in", values, "opsType");
            return (Criteria) this;
        }

        public Criteria andOpsTypeBetween(Integer value1, Integer value2) {
            addCriterion("ops_type between", value1, value2, "opsType");
            return (Criteria) this;
        }

        public Criteria andOpsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ops_type not between", value1, value2, "opsType");
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

        public Criteria andHttpIsPostIsNull() {
            addCriterion("http_is_post is null");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostIsNotNull() {
            addCriterion("http_is_post is not null");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostEqualTo(Boolean value) {
            addCriterion("http_is_post =", value, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostNotEqualTo(Boolean value) {
            addCriterion("http_is_post <>", value, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostGreaterThan(Boolean value) {
            addCriterion("http_is_post >", value, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostGreaterThanOrEqualTo(Boolean value) {
            addCriterion("http_is_post >=", value, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostLessThan(Boolean value) {
            addCriterion("http_is_post <", value, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostLessThanOrEqualTo(Boolean value) {
            addCriterion("http_is_post <=", value, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostIn(List<Boolean> values) {
            addCriterion("http_is_post in", values, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostNotIn(List<Boolean> values) {
            addCriterion("http_is_post not in", values, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostBetween(Boolean value1, Boolean value2) {
            addCriterion("http_is_post between", value1, value2, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpIsPostNotBetween(Boolean value1, Boolean value2) {
            addCriterion("http_is_post not between", value1, value2, "httpIsPost");
            return (Criteria) this;
        }

        public Criteria andHttpUrlIsNull() {
            addCriterion("http_url is null");
            return (Criteria) this;
        }

        public Criteria andHttpUrlIsNotNull() {
            addCriterion("http_url is not null");
            return (Criteria) this;
        }

        public Criteria andHttpUrlEqualTo(String value) {
            addCriterion("http_url =", value, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlNotEqualTo(String value) {
            addCriterion("http_url <>", value, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlGreaterThan(String value) {
            addCriterion("http_url >", value, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlGreaterThanOrEqualTo(String value) {
            addCriterion("http_url >=", value, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlLessThan(String value) {
            addCriterion("http_url <", value, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlLessThanOrEqualTo(String value) {
            addCriterion("http_url <=", value, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlLike(String value) {
            addCriterion("http_url like", value, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlNotLike(String value) {
            addCriterion("http_url not like", value, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlIn(List<String> values) {
            addCriterion("http_url in", values, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlNotIn(List<String> values) {
            addCriterion("http_url not in", values, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlBetween(String value1, String value2) {
            addCriterion("http_url between", value1, value2, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpUrlNotBetween(String value1, String value2) {
            addCriterion("http_url not between", value1, value2, "httpUrl");
            return (Criteria) this;
        }

        public Criteria andHttpParamIsNull() {
            addCriterion("http_param is null");
            return (Criteria) this;
        }

        public Criteria andHttpParamIsNotNull() {
            addCriterion("http_param is not null");
            return (Criteria) this;
        }

        public Criteria andHttpParamEqualTo(String value) {
            addCriterion("http_param =", value, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamNotEqualTo(String value) {
            addCriterion("http_param <>", value, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamGreaterThan(String value) {
            addCriterion("http_param >", value, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamGreaterThanOrEqualTo(String value) {
            addCriterion("http_param >=", value, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamLessThan(String value) {
            addCriterion("http_param <", value, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamLessThanOrEqualTo(String value) {
            addCriterion("http_param <=", value, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamLike(String value) {
            addCriterion("http_param like", value, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamNotLike(String value) {
            addCriterion("http_param not like", value, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamIn(List<String> values) {
            addCriterion("http_param in", values, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamNotIn(List<String> values) {
            addCriterion("http_param not in", values, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamBetween(String value1, String value2) {
            addCriterion("http_param between", value1, value2, "httpParam");
            return (Criteria) this;
        }

        public Criteria andHttpParamNotBetween(String value1, String value2) {
            addCriterion("http_param not between", value1, value2, "httpParam");
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

        public Criteria andTcValListIsNull() {
            addCriterion("tc_val_list is null");
            return (Criteria) this;
        }

        public Criteria andTcValListIsNotNull() {
            addCriterion("tc_val_list is not null");
            return (Criteria) this;
        }

        public Criteria andTcValListEqualTo(String value) {
            addCriterion("tc_val_list =", value, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListNotEqualTo(String value) {
            addCriterion("tc_val_list <>", value, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListGreaterThan(String value) {
            addCriterion("tc_val_list >", value, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListGreaterThanOrEqualTo(String value) {
            addCriterion("tc_val_list >=", value, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListLessThan(String value) {
            addCriterion("tc_val_list <", value, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListLessThanOrEqualTo(String value) {
            addCriterion("tc_val_list <=", value, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListLike(String value) {
            addCriterion("tc_val_list like", value, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListNotLike(String value) {
            addCriterion("tc_val_list not like", value, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListIn(List<String> values) {
            addCriterion("tc_val_list in", values, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListNotIn(List<String> values) {
            addCriterion("tc_val_list not in", values, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListBetween(String value1, String value2) {
            addCriterion("tc_val_list between", value1, value2, "tcValList");
            return (Criteria) this;
        }

        public Criteria andTcValListNotBetween(String value1, String value2) {
            addCriterion("tc_val_list not between", value1, value2, "tcValList");
            return (Criteria) this;
        }

        public Criteria andJavaCodeIsNull() {
            addCriterion("java_code is null");
            return (Criteria) this;
        }

        public Criteria andJavaCodeIsNotNull() {
            addCriterion("java_code is not null");
            return (Criteria) this;
        }

        public Criteria andJavaCodeEqualTo(String value) {
            addCriterion("java_code =", value, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeNotEqualTo(String value) {
            addCriterion("java_code <>", value, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeGreaterThan(String value) {
            addCriterion("java_code >", value, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("java_code >=", value, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeLessThan(String value) {
            addCriterion("java_code <", value, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeLessThanOrEqualTo(String value) {
            addCriterion("java_code <=", value, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeLike(String value) {
            addCriterion("java_code like", value, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeNotLike(String value) {
            addCriterion("java_code not like", value, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeIn(List<String> values) {
            addCriterion("java_code in", values, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeNotIn(List<String> values) {
            addCriterion("java_code not in", values, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeBetween(String value1, String value2) {
            addCriterion("java_code between", value1, value2, "javaCode");
            return (Criteria) this;
        }

        public Criteria andJavaCodeNotBetween(String value1, String value2) {
            addCriterion("java_code not between", value1, value2, "javaCode");
            return (Criteria) this;
        }

        public Criteria andWaitTimeIsNull() {
            addCriterion("wait_time is null");
            return (Criteria) this;
        }

        public Criteria andWaitTimeIsNotNull() {
            addCriterion("wait_time is not null");
            return (Criteria) this;
        }

        public Criteria andWaitTimeEqualTo(Integer value) {
            addCriterion("wait_time =", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeNotEqualTo(Integer value) {
            addCriterion("wait_time <>", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeGreaterThan(Integer value) {
            addCriterion("wait_time >", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("wait_time >=", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeLessThan(Integer value) {
            addCriterion("wait_time <", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("wait_time <=", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeIn(List<Integer> values) {
            addCriterion("wait_time in", values, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeNotIn(List<Integer> values) {
            addCriterion("wait_time not in", values, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeBetween(Integer value1, Integer value2) {
            addCriterion("wait_time between", value1, value2, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("wait_time not between", value1, value2, "waitTime");
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