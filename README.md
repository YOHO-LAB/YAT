# YAT自动化测试平台

## 如何部署
 - `MySQL` 详细步骤参照test/shell/installMysql57.sh
 - `Tomcat` 下载并安装maven git，配置git config，下载Tomcat并解压，执行test/shell/deploy.sh 自动发布
 - `首页地址` http://YOUR_SERVER:PORT/yat/pages/index.html

## 工程结构及说明
`src/main`
 - `java/cn/yat`
   - - `controller`
      - - - `GeneralController` Ajax请求入口
   - - `entity` 数据库表实体类 以及 实体类对应的mybatis工具类
   - - `ldap`
      - - - `LdapAuthService` ldap验证登录类
      - - - `LdapMapUser` ldap实体类映射
      - - - `LdapUser` ldap实体类
   - - `mapper` 数据库表实体映射类
   - - `myentity` 
      - - - `LogEntity` 运行日志实体类
      - - - `LogDataSourceEntity` 数据池运行日志实体类
      - - - `RunHttpResultEntity` Http请求执行结果实体类
   - - `service`
      - - - `DataService` 测试数据相关实现类
      - - - `DbService` 测试数据库相关实现类
      - - - `EnvironmentService` 测试环境相关实现类
      - - - `OperationService` 前后置操作相关实现类
      - - - `ParameterService` 参数化相关实现类
      - - - `ProjectService` 测试项目相关实现类
      - - - `ServiceService` 服务名相关实现类
      - - - `TeamService` 测试团队相关实现类
      - - - `TestcaseService` 测试用例、测试报告相关实现类
      - - - `UserService` 用户相关实现类
   - - `util` 工具类
      - - - `AESUtil` AES加解密工具类
      - - - `AsyncExecutorUtil` 异步多线程工具类
      - - - `BeanShellUtil` 动态执行java代码片段工具类
      - - - `CheckPointUtil` 检查点工具类
      - - - `ClientSecretUtil` ClientSecret工具类
      - - - `CronTask` 定时任务工具类
      - - - `FileRWUtil` 文件读写工具类
      - - - `HttpUtil` HttpClient工具类
      - - - `JavaMailUtil` 发送邮件工具类
      - - - `JdbcUtil` 执行SQL语句工具类
      - - - `ListUtil` 链表工具类
      - - - `LogUtil` 日志工具类
      - - - `MD5` MD5加解密工具类
      - - - `MD5Util` MD5加解密工具类
      - - - `ParamUtil` 参数化工具类
      - - - `RecordUtil` 操作记录工具类
      - - - `SignatureVerifyUtil` 验签工具类
 - `resources`
   - - `config`
      - - - `dev/config.properties` 开发环境配置
      - - - `prd/config.properties` 线上环境配置
      - - - `test/config.properties` 测试环境配置
   - - `spring` 
      - - - `application-*.xml` spring配置
   - - `logback.xml` logback配置
 - `webapp`
   - - `res` 静态资源
   - - `WEB-INF/*.xml` mvc配置
`src/test`
 - `java`
   - - `generatorConfig.xml` mybatis自动生成实体代码工具
   - - `Test` 测试类
 - `shell`
   - - `deploy.sh` 自动发布脚本
   - - `installMysql57.sh` mysql5.7.22安装步骤
   - - `yat.sql` yat数据库表结构转存储文件