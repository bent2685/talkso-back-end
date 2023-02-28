# TalkSo项目介绍

> 2023-2-28

## 命名规范

### 方法名命名规范

**方法名最好以动词或者助动词开头**

| 方法名              | 描述                                          |
| ------------------- | --------------------------------------------- |
| listAll             | 查询所有(无条件)                              |
| listAllByPage       | 通过分页条件查询所有                          |
| listAllBy{name}     | 通过name查询所有                              |
| findOne             | 查询某个                                      |
| findOneBy{name}     | 通过name查询某个                              |
| is{name}            | 表示布尔状态。name是否为true，例如：`isLogin` |
| do{name}            | 做某事，例如：`doLogin`                       |
| exec{name}          | 执行某事，例如：`execQuery`                   |
| do{name}With{name2} | 做某事伴随着name2，例如：`doLoginWithCaptha`  |
| get{name}           | 得到某物，例如：`getUserList`                 |
| delOneBy{name}      | 通过name删除某个                              |
| delBy{name}         | 通过name删除某些                              |

---

### 变量名命名规范

| 变量名           | 描述                                                         |
| ---------------- | ------------------------------------------------------------ |
| is{name}         | 表示布尔状态。name是否为true，例如：`isSuccess`              |
| {name}List       | 某物的集合形式，例如：`userList`                             |
| can{name}        | 表示布尔状态(能否做某事)，例如：`canNext`                    |
| {name}For{name2} | 为了name2而查找的name1，例如：`userForExist`(为了判断用户是否存在而查找的用户) |
| has{name}        | 是否有某个状态，例如：`hasEmail`                             |
| {name}Exist      | 某物是否存在，例如：`userExist`                              |

---

### 路由名命名规范

| 路由名                    | 描述                               |
| ------------------------- | ---------------------------------- |
| /user/listAll             | 查询所有用户                       |
| /user/findOne/1           | 查询主键id为1的用户                |
| /user/findOneByUname/bent | 查询uname为bent的用户              |
| /user/update              | 更新用户                           |
| /user/delOne/1            | 删除主键id为1的用户                |
| /user/delByUname/bent     | 删除uname为bent的所有用户          |
| /is{name}                 | 获得是/否状态，例如`/user/isLogin` |
| /exec{name}               | 执行某事                           |
| /do{name}                 | 做某事                             |
| /post/addOne              | 添加一条帖子信息                   |
| /send/sendEmailCaptcha    | 发送邮箱验证码                     |
