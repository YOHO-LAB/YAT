#!/bin/bash

# 安装 mysql5.7.22
wget https://dev.mysql.com/get/mysql57-community-release-el6-11.noarch.rpm
yum localinstall -y mysql57-community-release-el6-11.noarch.rpm
yum install -y mysql-community-server

# 手动部分
# service mysqld start
# 查看初始随机密码
# grep 'temporary password' /var/log/mysqld.log
# 使用初始随机密码登录
# mysql -u root -p
# <dnr?evFk3Uz
# 修改密码（密码必须包含小写、大写字母及特殊字符）
# SET PASSWORD = PASSWORD('aA@123456');
# ALTER USER 'root'@'localhost' PASSWORD EXPIRE NEVER;
# flush privileges;
# 退出后即可用新密码登录
# mysql -u root -p
# aA@123456
# 远程连接授权
# GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'aA@123456' WITH GRANT OPTION;
# GRANT ALL PRIVILEGES ON yat.* TO 'yat'@'%' IDENTIFIED BY 'aA@123456';
# 自行开放3306端口

# 导入sql
# mysql -uroot -p < yat.sql
# aA@123456
