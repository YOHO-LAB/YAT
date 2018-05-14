package cn.yat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestcaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestcaseExample() {
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

        public Criteria andTeamIdIsNull() {
            addCriterion("team_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNotNull() {
            addCriterion("team_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIdEqualTo(Integer value) {
            addCriterion("team_id =", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotEqualTo(Integer value) {
            addCriterion("team_id <>", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThan(Integer value) {
            addCriterion("team_id >", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_id >=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThan(Integer value) {
            addCriterion("team_id <", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThanOrEqualTo(Integer value) {
            addCriterion("team_id <=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdIn(List<Integer> values) {
            addCriterion("team_id in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotIn(List<Integer> values) {
            addCriterion("team_id not in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdBetween(Integer value1, Integer value2) {
            addCriterion("team_id between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("team_id not between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNull() {
            addCriterion("service_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNotNull() {
            addCriterion("service_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceIdEqualTo(Integer value) {
            addCriterion("service_id =", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotEqualTo(Integer value) {
            addCriterion("service_id <>", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThan(Integer value) {
            addCriterion("service_id >", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_id >=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThan(Integer value) {
            addCriterion("service_id <", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThanOrEqualTo(Integer value) {
            addCriterion("service_id <=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIn(List<Integer> values) {
            addCriterion("service_id in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotIn(List<Integer> values) {
            addCriterion("service_id not in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdBetween(Integer value1, Integer value2) {
            addCriterion("service_id between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("service_id not between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdIsNull() {
            addCriterion("test_env_id is null");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdIsNotNull() {
            addCriterion("test_env_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdEqualTo(Integer value) {
            addCriterion("test_env_id =", value, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdNotEqualTo(Integer value) {
            addCriterion("test_env_id <>", value, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdGreaterThan(Integer value) {
            addCriterion("test_env_id >", value, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("test_env_id >=", value, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdLessThan(Integer value) {
            addCriterion("test_env_id <", value, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdLessThanOrEqualTo(Integer value) {
            addCriterion("test_env_id <=", value, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdIn(List<Integer> values) {
            addCriterion("test_env_id in", values, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdNotIn(List<Integer> values) {
            addCriterion("test_env_id not in", values, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdBetween(Integer value1, Integer value2) {
            addCriterion("test_env_id between", value1, value2, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andTestEnvIdNotBetween(Integer value1, Integer value2) {
            addCriterion("test_env_id not between", value1, value2, "testEnvId");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("method is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(String value) {
            addCriterion("method =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(String value) {
            addCriterion("method <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(String value) {
            addCriterion("method >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(String value) {
            addCriterion("method >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(String value) {
            addCriterion("method <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(String value) {
            addCriterion("method <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLike(String value) {
            addCriterion("method like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotLike(String value) {
            addCriterion("method not like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<String> values) {
            addCriterion("method in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<String> values) {
            addCriterion("method not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(String value1, String value2) {
            addCriterion("method between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(String value1, String value2) {
            addCriterion("method not between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andIsPostIsNull() {
            addCriterion("is_post is null");
            return (Criteria) this;
        }

        public Criteria andIsPostIsNotNull() {
            addCriterion("is_post is not null");
            return (Criteria) this;
        }

        public Criteria andIsPostEqualTo(Boolean value) {
            addCriterion("is_post =", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostNotEqualTo(Boolean value) {
            addCriterion("is_post <>", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostGreaterThan(Boolean value) {
            addCriterion("is_post >", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_post >=", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostLessThan(Boolean value) {
            addCriterion("is_post <", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostLessThanOrEqualTo(Boolean value) {
            addCriterion("is_post <=", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostIn(List<Boolean> values) {
            addCriterion("is_post in", values, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostNotIn(List<Boolean> values) {
            addCriterion("is_post not in", values, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostBetween(Boolean value1, Boolean value2) {
            addCriterion("is_post between", value1, value2, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_post not between", value1, value2, "isPost");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andParametersIsNull() {
            addCriterion("parameters is null");
            return (Criteria) this;
        }

        public Criteria andParametersIsNotNull() {
            addCriterion("parameters is not null");
            return (Criteria) this;
        }

        public Criteria andParametersEqualTo(String value) {
            addCriterion("parameters =", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersNotEqualTo(String value) {
            addCriterion("parameters <>", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersGreaterThan(String value) {
            addCriterion("parameters >", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersGreaterThanOrEqualTo(String value) {
            addCriterion("parameters >=", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersLessThan(String value) {
            addCriterion("parameters <", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersLessThanOrEqualTo(String value) {
            addCriterion("parameters <=", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersLike(String value) {
            addCriterion("parameters like", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersNotLike(String value) {
            addCriterion("parameters not like", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersIn(List<String> values) {
            addCriterion("parameters in", values, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersNotIn(List<String> values) {
            addCriterion("parameters not in", values, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersBetween(String value1, String value2) {
            addCriterion("parameters between", value1, value2, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersNotBetween(String value1, String value2) {
            addCriterion("parameters not between", value1, value2, "parameters");
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

        public Criteria andPreOpsIdsIsNull() {
            addCriterion("pre_ops_ids is null");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsIsNotNull() {
            addCriterion("pre_ops_ids is not null");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsEqualTo(String value) {
            addCriterion("pre_ops_ids =", value, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsNotEqualTo(String value) {
            addCriterion("pre_ops_ids <>", value, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsGreaterThan(String value) {
            addCriterion("pre_ops_ids >", value, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsGreaterThanOrEqualTo(String value) {
            addCriterion("pre_ops_ids >=", value, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsLessThan(String value) {
            addCriterion("pre_ops_ids <", value, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsLessThanOrEqualTo(String value) {
            addCriterion("pre_ops_ids <=", value, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsLike(String value) {
            addCriterion("pre_ops_ids like", value, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsNotLike(String value) {
            addCriterion("pre_ops_ids not like", value, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsIn(List<String> values) {
            addCriterion("pre_ops_ids in", values, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsNotIn(List<String> values) {
            addCriterion("pre_ops_ids not in", values, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsBetween(String value1, String value2) {
            addCriterion("pre_ops_ids between", value1, value2, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andPreOpsIdsNotBetween(String value1, String value2) {
            addCriterion("pre_ops_ids not between", value1, value2, "preOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsIsNull() {
            addCriterion("after_test_ops_ids is null");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsIsNotNull() {
            addCriterion("after_test_ops_ids is not null");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsEqualTo(String value) {
            addCriterion("after_test_ops_ids =", value, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsNotEqualTo(String value) {
            addCriterion("after_test_ops_ids <>", value, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsGreaterThan(String value) {
            addCriterion("after_test_ops_ids >", value, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsGreaterThanOrEqualTo(String value) {
            addCriterion("after_test_ops_ids >=", value, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsLessThan(String value) {
            addCriterion("after_test_ops_ids <", value, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsLessThanOrEqualTo(String value) {
            addCriterion("after_test_ops_ids <=", value, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsLike(String value) {
            addCriterion("after_test_ops_ids like", value, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsNotLike(String value) {
            addCriterion("after_test_ops_ids not like", value, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsIn(List<String> values) {
            addCriterion("after_test_ops_ids in", values, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsNotIn(List<String> values) {
            addCriterion("after_test_ops_ids not in", values, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsBetween(String value1, String value2) {
            addCriterion("after_test_ops_ids between", value1, value2, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andAfterTestOpsIdsNotBetween(String value1, String value2) {
            addCriterion("after_test_ops_ids not between", value1, value2, "afterTestOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsIsNull() {
            addCriterion("post_ops_ids is null");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsIsNotNull() {
            addCriterion("post_ops_ids is not null");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsEqualTo(String value) {
            addCriterion("post_ops_ids =", value, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsNotEqualTo(String value) {
            addCriterion("post_ops_ids <>", value, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsGreaterThan(String value) {
            addCriterion("post_ops_ids >", value, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsGreaterThanOrEqualTo(String value) {
            addCriterion("post_ops_ids >=", value, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsLessThan(String value) {
            addCriterion("post_ops_ids <", value, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsLessThanOrEqualTo(String value) {
            addCriterion("post_ops_ids <=", value, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsLike(String value) {
            addCriterion("post_ops_ids like", value, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsNotLike(String value) {
            addCriterion("post_ops_ids not like", value, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsIn(List<String> values) {
            addCriterion("post_ops_ids in", values, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsNotIn(List<String> values) {
            addCriterion("post_ops_ids not in", values, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsBetween(String value1, String value2) {
            addCriterion("post_ops_ids between", value1, value2, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andPostOpsIdsNotBetween(String value1, String value2) {
            addCriterion("post_ops_ids not between", value1, value2, "postOpsIds");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckIsNull() {
            addCriterion("http_code_check is null");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckIsNotNull() {
            addCriterion("http_code_check is not null");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckEqualTo(String value) {
            addCriterion("http_code_check =", value, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckNotEqualTo(String value) {
            addCriterion("http_code_check <>", value, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckGreaterThan(String value) {
            addCriterion("http_code_check >", value, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckGreaterThanOrEqualTo(String value) {
            addCriterion("http_code_check >=", value, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckLessThan(String value) {
            addCriterion("http_code_check <", value, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckLessThanOrEqualTo(String value) {
            addCriterion("http_code_check <=", value, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckLike(String value) {
            addCriterion("http_code_check like", value, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckNotLike(String value) {
            addCriterion("http_code_check not like", value, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckIn(List<String> values) {
            addCriterion("http_code_check in", values, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckNotIn(List<String> values) {
            addCriterion("http_code_check not in", values, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckBetween(String value1, String value2) {
            addCriterion("http_code_check between", value1, value2, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andHttpCodeCheckNotBetween(String value1, String value2) {
            addCriterion("http_code_check not between", value1, value2, "httpCodeCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckIsNull() {
            addCriterion("contain_check is null");
            return (Criteria) this;
        }

        public Criteria andContainCheckIsNotNull() {
            addCriterion("contain_check is not null");
            return (Criteria) this;
        }

        public Criteria andContainCheckEqualTo(String value) {
            addCriterion("contain_check =", value, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckNotEqualTo(String value) {
            addCriterion("contain_check <>", value, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckGreaterThan(String value) {
            addCriterion("contain_check >", value, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckGreaterThanOrEqualTo(String value) {
            addCriterion("contain_check >=", value, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckLessThan(String value) {
            addCriterion("contain_check <", value, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckLessThanOrEqualTo(String value) {
            addCriterion("contain_check <=", value, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckLike(String value) {
            addCriterion("contain_check like", value, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckNotLike(String value) {
            addCriterion("contain_check not like", value, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckIn(List<String> values) {
            addCriterion("contain_check in", values, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckNotIn(List<String> values) {
            addCriterion("contain_check not in", values, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckBetween(String value1, String value2) {
            addCriterion("contain_check between", value1, value2, "containCheck");
            return (Criteria) this;
        }

        public Criteria andContainCheckNotBetween(String value1, String value2) {
            addCriterion("contain_check not between", value1, value2, "containCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckIsNull() {
            addCriterion("not_contain_check is null");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckIsNotNull() {
            addCriterion("not_contain_check is not null");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckEqualTo(String value) {
            addCriterion("not_contain_check =", value, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckNotEqualTo(String value) {
            addCriterion("not_contain_check <>", value, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckGreaterThan(String value) {
            addCriterion("not_contain_check >", value, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckGreaterThanOrEqualTo(String value) {
            addCriterion("not_contain_check >=", value, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckLessThan(String value) {
            addCriterion("not_contain_check <", value, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckLessThanOrEqualTo(String value) {
            addCriterion("not_contain_check <=", value, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckLike(String value) {
            addCriterion("not_contain_check like", value, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckNotLike(String value) {
            addCriterion("not_contain_check not like", value, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckIn(List<String> values) {
            addCriterion("not_contain_check in", values, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckNotIn(List<String> values) {
            addCriterion("not_contain_check not in", values, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckBetween(String value1, String value2) {
            addCriterion("not_contain_check between", value1, value2, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andNotContainCheckNotBetween(String value1, String value2) {
            addCriterion("not_contain_check not between", value1, value2, "notContainCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckIsNull() {
            addCriterion("json_check is null");
            return (Criteria) this;
        }

        public Criteria andJsonCheckIsNotNull() {
            addCriterion("json_check is not null");
            return (Criteria) this;
        }

        public Criteria andJsonCheckEqualTo(String value) {
            addCriterion("json_check =", value, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckNotEqualTo(String value) {
            addCriterion("json_check <>", value, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckGreaterThan(String value) {
            addCriterion("json_check >", value, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckGreaterThanOrEqualTo(String value) {
            addCriterion("json_check >=", value, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckLessThan(String value) {
            addCriterion("json_check <", value, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckLessThanOrEqualTo(String value) {
            addCriterion("json_check <=", value, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckLike(String value) {
            addCriterion("json_check like", value, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckNotLike(String value) {
            addCriterion("json_check not like", value, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckIn(List<String> values) {
            addCriterion("json_check in", values, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckNotIn(List<String> values) {
            addCriterion("json_check not in", values, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckBetween(String value1, String value2) {
            addCriterion("json_check between", value1, value2, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andJsonCheckNotBetween(String value1, String value2) {
            addCriterion("json_check not between", value1, value2, "jsonCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckIsNull() {
            addCriterion("db_check is null");
            return (Criteria) this;
        }

        public Criteria andDbCheckIsNotNull() {
            addCriterion("db_check is not null");
            return (Criteria) this;
        }

        public Criteria andDbCheckEqualTo(String value) {
            addCriterion("db_check =", value, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckNotEqualTo(String value) {
            addCriterion("db_check <>", value, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckGreaterThan(String value) {
            addCriterion("db_check >", value, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckGreaterThanOrEqualTo(String value) {
            addCriterion("db_check >=", value, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckLessThan(String value) {
            addCriterion("db_check <", value, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckLessThanOrEqualTo(String value) {
            addCriterion("db_check <=", value, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckLike(String value) {
            addCriterion("db_check like", value, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckNotLike(String value) {
            addCriterion("db_check not like", value, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckIn(List<String> values) {
            addCriterion("db_check in", values, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckNotIn(List<String> values) {
            addCriterion("db_check not in", values, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckBetween(String value1, String value2) {
            addCriterion("db_check between", value1, value2, "dbCheck");
            return (Criteria) this;
        }

        public Criteria andDbCheckNotBetween(String value1, String value2) {
            addCriterion("db_check not between", value1, value2, "dbCheck");
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

        public Criteria andCookieListIsNull() {
            addCriterion("cookie_list is null");
            return (Criteria) this;
        }

        public Criteria andCookieListIsNotNull() {
            addCriterion("cookie_list is not null");
            return (Criteria) this;
        }

        public Criteria andCookieListEqualTo(String value) {
            addCriterion("cookie_list =", value, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListNotEqualTo(String value) {
            addCriterion("cookie_list <>", value, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListGreaterThan(String value) {
            addCriterion("cookie_list >", value, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListGreaterThanOrEqualTo(String value) {
            addCriterion("cookie_list >=", value, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListLessThan(String value) {
            addCriterion("cookie_list <", value, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListLessThanOrEqualTo(String value) {
            addCriterion("cookie_list <=", value, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListLike(String value) {
            addCriterion("cookie_list like", value, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListNotLike(String value) {
            addCriterion("cookie_list not like", value, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListIn(List<String> values) {
            addCriterion("cookie_list in", values, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListNotIn(List<String> values) {
            addCriterion("cookie_list not in", values, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListBetween(String value1, String value2) {
            addCriterion("cookie_list between", value1, value2, "cookieList");
            return (Criteria) this;
        }

        public Criteria andCookieListNotBetween(String value1, String value2) {
            addCriterion("cookie_list not between", value1, value2, "cookieList");
            return (Criteria) this;
        }

        public Criteria andHeaderListIsNull() {
            addCriterion("header_list is null");
            return (Criteria) this;
        }

        public Criteria andHeaderListIsNotNull() {
            addCriterion("header_list is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderListEqualTo(String value) {
            addCriterion("header_list =", value, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListNotEqualTo(String value) {
            addCriterion("header_list <>", value, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListGreaterThan(String value) {
            addCriterion("header_list >", value, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListGreaterThanOrEqualTo(String value) {
            addCriterion("header_list >=", value, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListLessThan(String value) {
            addCriterion("header_list <", value, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListLessThanOrEqualTo(String value) {
            addCriterion("header_list <=", value, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListLike(String value) {
            addCriterion("header_list like", value, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListNotLike(String value) {
            addCriterion("header_list not like", value, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListIn(List<String> values) {
            addCriterion("header_list in", values, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListNotIn(List<String> values) {
            addCriterion("header_list not in", values, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListBetween(String value1, String value2) {
            addCriterion("header_list between", value1, value2, "headerList");
            return (Criteria) this;
        }

        public Criteria andHeaderListNotBetween(String value1, String value2) {
            addCriterion("header_list not between", value1, value2, "headerList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListIsNull() {
            addCriterion("get_http_res_list is null");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListIsNotNull() {
            addCriterion("get_http_res_list is not null");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListEqualTo(String value) {
            addCriterion("get_http_res_list =", value, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListNotEqualTo(String value) {
            addCriterion("get_http_res_list <>", value, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListGreaterThan(String value) {
            addCriterion("get_http_res_list >", value, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListGreaterThanOrEqualTo(String value) {
            addCriterion("get_http_res_list >=", value, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListLessThan(String value) {
            addCriterion("get_http_res_list <", value, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListLessThanOrEqualTo(String value) {
            addCriterion("get_http_res_list <=", value, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListLike(String value) {
            addCriterion("get_http_res_list like", value, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListNotLike(String value) {
            addCriterion("get_http_res_list not like", value, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListIn(List<String> values) {
            addCriterion("get_http_res_list in", values, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListNotIn(List<String> values) {
            addCriterion("get_http_res_list not in", values, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListBetween(String value1, String value2) {
            addCriterion("get_http_res_list between", value1, value2, "getHttpResList");
            return (Criteria) this;
        }

        public Criteria andGetHttpResListNotBetween(String value1, String value2) {
            addCriterion("get_http_res_list not between", value1, value2, "getHttpResList");
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