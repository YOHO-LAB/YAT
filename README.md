# YAT自动化测试平台

## 如何部署
 - `数据库` MySQL5.7(安装步骤参照src/test/shell/installMysql57.sh),创建数据库yat,导入表结构(src/test/shell/yat.sql)
 - `应用` Tomcat 直接war包部署或者使用(src/test/shell/deploy.sh)一键自动部署
 - `首页地址` http://YOUR_SERVER:PORT/yat/pages/index.html
 
## Wiki 
   详细使用方法，请见wiki：https://github.com/YOHO-LAB/YAT/wiki
   
   * [概览页](https://github.com/YOHO-LAB/YAT/wiki/%E6%A6%82%E8%A7%88%E9%A1%B5)
   * [测试用例页](https://github.com/YOHO-LAB/YAT/wiki/%E6%B5%8B%E8%AF%95%E7%94%A8%E4%BE%8B%E9%A1%B5)
   * [参数化维护（全局变量）页](https://github.com/YOHO-LAB/YAT/wiki/%E5%8F%82%E6%95%B0%E5%8C%96%E7%BB%B4%E6%8A%A4%EF%BC%88%E5%85%A8%E5%B1%80%E5%8F%98%E9%87%8F%EF%BC%89%E9%A1%B5)
   * [数据库维护页](https://github.com/YOHO-LAB/YAT/wiki/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%BB%B4%E6%8A%A4%E9%A1%B5)
   * [前后置操作维护页](https://github.com/YOHO-LAB/YAT/wiki/%E5%89%8D%E5%90%8E%E7%BD%AE%E6%93%8D%E4%BD%9C%E7%BB%B4%E6%8A%A4%E9%A1%B5)
   * [运行结果列表页](https://github.com/YOHO-LAB/YAT/wiki/%E8%BF%90%E8%A1%8C%E7%BB%93%E6%9E%9C%E5%88%97%E8%A1%A8%E9%A1%B5)
   * [用例编辑](https://github.com/YOHO-LAB/YAT/wiki/%E7%94%A8%E4%BE%8B%E7%BC%96%E8%BE%91)
   * [测试报告](https://github.com/YOHO-LAB/YAT/wiki/%E6%B5%8B%E8%AF%95%E6%8A%A5%E5%91%8A)
   * [登录、环境切换](https://github.com/YOHO-LAB/YAT/wiki/%E7%99%BB%E5%BD%95%E3%80%81%E7%8E%AF%E5%A2%83%E5%88%87%E6%8D%A2)
   * [团队、项目、测试环境、服务名、测试机Hosts 维护页](https://github.com/YOHO-LAB/YAT/wiki/%E5%9B%A2%E9%98%9F%E3%80%81%E9%A1%B9%E7%9B%AE%E3%80%81%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83%E3%80%81%E6%9C%8D%E5%8A%A1%E5%90%8D%E3%80%81%E6%B5%8B%E8%AF%95%E6%9C%BAHosts-%E7%BB%B4%E6%8A%A4%E9%A1%B5)
 
## 如何新增一条用例
 - 首先维护好用例所属的项目/环境；
 - 切换到该环境下，在测试用例页面，点击新增用例；
 - 填写用例名、URL参数、检查点、前后置操作等信息；（用例中用到的团队名、服务名、前后置操作需要提前维护，也可以在用例中维护然后点击刷新即可选择）
 - 对用例中需要参数化的地方进行参数化；
 - 可以使用数据驱动，实现一条用例对应多种场景；也可以不使用，直接就是单条用例；
 - 保存后，点击调试，进行用例调试，根据调试结果进行用例修改；
 
## 工程结构及说明
`src/main`
 - `java/cn/yat`
   - - `controller`
      - - `GeneralController` Ajax请求入口
   - - `entity` 数据库表实体类 以及 实体类对应的mybatis工具类
   - - `ldap`
      - - `LdapAuthService` ldap验证登录类
      - - `LdapMapUser` ldap实体类映射
      - - `LdapUser` ldap实体类
   - - `mapper` 数据库表实体映射类
   - - `myentity` 
      - - `LogEntity` 运行日志实体类
      - - `LogDataSourceEntity` 数据池运行日志实体类
      - - `RunHttpResultEntity` Http请求执行结果实体类
   - - `service`
      - - `DataService` 测试数据相关实现类
      - - `DbService` 测试数据库相关实现类
      - - `EnvironmentService` 测试环境相关实现类
      - - `OperationService` 前后置操作相关实现类
      - - `ParameterService` 参数化相关实现类
      - - `ProjectService` 测试项目相关实现类
      - - `ServiceService` 服务名相关实现类
      - - `TeamService` 测试团队相关实现类
      - - `TestcaseService` 测试用例、测试报告相关实现类
      - - `UserService` 用户相关实现类
   - - `util` 工具类
      - - `AESUtil` AES加解密工具类
      - - `AsyncExecutorUtil` 异步多线程工具类
      - - `BeanShellUtil` 动态执行java代码片段工具类
      - - `CheckPointUtil` 检查点工具类
      - - `ClientSecretUtil` ClientSecret工具类
      - - `CronTask` 定时任务工具类
      - - `FileRWUtil` 文件读写工具类
      - - `HttpUtil` HttpClient工具类
      - - `JavaMailUtil` 发送邮件工具类
      - - `JdbcUtil` 执行SQL语句工具类
      - - `ListUtil` 链表工具类
      - - `LogUtil` 日志工具类
      - - `MD5` MD5加解密工具类
      - - `MD5Util` MD5加解密工具类
      - - `ParamUtil` 参数化工具类
      - - `SignatureVerifyUtil` 验签工具类
 - `resources`
   - - `config`
      - - `dev/config.properties` 开发环境配置
      - - `prd/config.properties` 线上环境配置
      - - `test/config.properties` 测试环境配置
   - - `spring` 
      - - `application-*.xml` spring配置
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
