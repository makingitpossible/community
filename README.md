## 码匠社区
## 资料
http://elasticsearch.cn/explore
https://github.com/settings/developers设置OAuth APP，用于外部登录github账号
## 工具

## 数据库SQl
建表
```sql
create table USER
   (
   	ID INT auto_increment,
   	ACCOUNT_ID VARCHAR(100),
   	NAME VARCHAR(50),
   	TOKEN CHAR(36),
   	GMT_CREATE BIGINT,
   	GMT_MODIFIED BIGINT,
   	constraint USER_PK
   		primary key (ID)
   );
```
   
