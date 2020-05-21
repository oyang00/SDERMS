# SDERMS 接口

## 1、API 接口说明

- 接口基准地址：`http://127.0.0.1:8080/api/`
- 服务端已开启 CORS 跨域支持
- API v1 认证统一使用 Token 认证
- 需要授权的 API ，必须在请求头中使用 `Authorization` 字段提供 `token` 令牌
- 数据返回格式统一使用 JSON

### 1.1、支持的请求方式

- GET（SELECT）：从服务器取出资源（一项或多项）
- POST（CREATE）：在服务器新建一个资源
- PUT（UPDATE）：在服务器更新资源
- DELETE（DELETE）：从服务器删除资源

### 1.2、通用返回说明

| 参数 | 名称     | 描述           |
| ---- | -------- | -------------- |
| code | 返回码   |                |
| data | 返回数据 | 返回的数据     |
| msg  | 返回信息 | 返回描述字符串 |

### 1.3、返回码

| 码值 | 名称             | 描述                   |
| ---- | ---------------- | ---------------------- |
| 200  | 执行成功         | 执行操作成功返回       |
| 201  | 获取成功         | 获取操作成功返回       |
| 202  | 获取数据为空     | 获取数据为空时返回     |
| 400  | 执行失败         | 执行操作失败返回       |
| 401  | 获取失败         | 获取操作失败返回       |
| 402  | 参数校验未通过   | 参数校验未通过时返回   |
| 403  | Token 校验未通过 | Token 校验未通过时返回 |
| 404  | 权限校验未通过   | 权限校验未通过         |
| 405  | 请求方法错误     | 请求方法错误时返回     |
| 500  | 服务器异常       | 服务器异常时返回       |



## 2、用户(user)

### 2.1、登录

- 请求路径：user/login
- 请求方法：post
- 请求参数

| 参数名   | 参数类型 | 参数说明 | 备注                                       |
| -------- | -------- | -------- | ------------------------------------------ |
| account  | String   | 账号     | 不能为空                                   |
| password | String   | 密码     | 不能为空                                   |
| type     | int      | 账号类型 | 不能为空，0 为学生，1 为维修员，2 为管理员 |

- 响应数据

  若请求参数 type 为 0：（Object）

| 参数名    | 参数类型 | 参数说明 | 备注 |
| --------- | -------- | -------- | ---- |
| account   | String   | 账号     |      |
| name      | String   | 学生姓名 |      |
| dormitory | Object   | 宿舍     |      |
| - id      | long     | 宿舍 id  |      |
| - name    | String   | 宿舍名   |      |
| token     | String   | 令牌     |      |

​	否则：（Object）

| 参数名  | 参数类型 | 参数说明 | 备注 |
| ------- | -------- | -------- | ---- |
| account | String   | 账号     |      |
| token   | String   | 令牌     |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "登录成功",
    "data": {
        "account": "2016188001",
        "token": "520edaab546a49a19a72f98f4ae4cf1b",
        "name": "李四",
        "dormitory": {
            "id": 1,
            "name": "寝室a"
        }
    }
}
```

### 2.2、注销

- 请求路径：user/logout
- 请求方法：put
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据


| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "注销成功",
    "data": null
}
```

### 2.3、检测学生是否存在

- 请求路径：user/check
- 请求方法：get
- 请求参数

| 参数名  | 参数类型 | 参数说明 | 备注     |
| ------- | -------- | -------- | -------- |
| account | String   | 账号     | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "账号不存在",
    "data": null
}
```



### 2.4、学生注册

- 请求路径：user/register
- 请求方法：post
- 请求参数

| 参数名    | 参数类型 | 参数说明 | 备注              |
| --------- | -------- | -------- | ----------------- |
| account   | String   | 账号     | 不能为空          |
| password  | String   | 密码     | 不能为空          |
| name      | String   | 学生姓名 | 不能为空          |
| dormitory | long     | 宿舍     | 宿舍 id，不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "注册成功",
    "data": null
}
```

### 2.5、修改密码

- 请求路径：user/password
- 请求方法：put
- 请求参数

| 参数名  | 参数类型 | 参数说明 | 备注     |
| ------- | -------- | -------- | -------- |
| oldPass | String   | 旧密码   | 不能为空 |
| newPass | String   | 新密码   | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "修改成功",
    "data": null
}
```

### 2.6、学生修改姓名

- 请求路径：user/name
- 请求方法：put
- 请求参数

| 参数名  | 参数类型 | 参数说明 | 备注     |
| ------- | -------- | -------- | -------- |
| newName | String   | 新名字   | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "修改成功",
    "data": null
}
```

### 2.7、获取所有宿舍

- 请求路径：user/dormitory
- 请求方法：get
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
| id     | long     | 宿舍 id  |      |
| name   | String   | 宿舍名   |      |

- 响应数据

```json

```

## 3、报修表单(forms)

### 3.1、提出报修

- 请求路径：forms/form
- 请求方法：post
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注     |
| ------ | -------- | -------- | -------- |
| title  | String   | 问题标题 | 不能为空 |
| desc   | String   | 问题描述 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "提交成功",
    "data": null
}
```

### 3.2、完善报修

- 请求路径：forms/form
- 请求方法：put
- 请求参数

| 参数名 | 参数类型 | 参数说明    | 备注     |
| ------ | -------- | ----------- | -------- |
| id     | long     | 报修表单 id | 不能为空 |
| desc   | String   | 报修描述    | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "修改成功",
    "data": null
}
```

### 3.3、获取我的报修

- 请求路径：forms/my
- 请求方法：get
- 请求参数

| 参数名   | 参数类型 | 参数说明     | 备注                                           |
| -------- | -------- | ------------ | ---------------------------------------------- |
| pageNum  | int      | 当前页码     | 不能为空                                       |
| pageSize | int      | 每页显示条数 | 不能为空                                       |
| isHandle | int      | 是否处理     | 不能为空，-1为获取所有，0 为未处理，1 为已处理 |
|          |          |              |                                                |

- 响应数据（Object）

| 参数名         | 参数类型 | 参数说明   | 备注                   |
| -------------- | -------- | ---------- | ---------------------- |
| total          | long     | 总条数     |                        |
| forms          | List     | 报修表单   |                        |
| - id           | long     | id         |                        |
| - propose      | Object   | 提出人     |                        |
| -- account     | String   | 学生学号   |                        |
| -- name        | String   | 学生姓名   |                        |
| - proposeTitle | String   | 问题标题   |                        |
| - proposeDesc  | String   | 问题描述   |                        |
| - proposeTime  | String   | 提出时间   |                        |
| - repair       | Object   | 维修员     |                        |
| -- account     | String   | 维修员账号 |                        |
| - repairDesc   | String   | 维修描述   |                        |
| - repairTime   | String   | 维修时间   |                        |
| - isHandle     | int      | 维修标记   | 0 为未处理，1 为已处理 |

- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 2,
        "forms": [
            {
                "id": 1,
                "propose": {
                    "account": "2016188001",
                    "password": null,
                    "name": "李四",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修1",
                "proposeDesc": "测试报修1",
                "proposeTime": "2020-03-27T04:06:01",
                "repair": {
                    "account": "10010",
                    "password": null
                },
                "repairDesc": "完善报修表单1",
                "repairTime": "2020-03-27T04:21:56",
                "isHandle": 1
            },
            {
                "id": 4,
                "propose": {
                    "account": "2016188002",
                    "password": null,
                    "name": "赵六",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修3",
                "proposeDesc": "测试报修3",
                "proposeTime": "2020-03-27T12:26:48",
                "repair": {
                    "account": "10010",
                    "password": null
                },
                "repairDesc": "完善报修表单4",
                "repairTime": "2020-03-27T12:27:59",
                "isHandle": 1
            }
        ]
    }
}
```

### 3.4、获取所有报修

- 请求路径：forms/all
- 请求方法：get
- 请求参数

| 参数名   | 参数类型 | 参数说明     | 备注                                            |
| -------- | -------- | ------------ | ----------------------------------------------- |
| pageNum  | int      | 当前页码     | 不能为空                                        |
| pageSize | int      | 每页显示条数 | 不能为空                                        |
| isHandle | int      | 是否处理     | 不能为空，-1 为获取所有，0 为未处理，1 为已处理 |

- 响应数据（Object）

| 参数名         | 参数类型 | 参数说明   | 备注                   |
| -------------- | -------- | ---------- | ---------------------- |
| total          | long     | 总条数     |                        |
| forms          | List     | 报修表单   |                        |
| - id           | long     | id         |                        |
| - propose      | Object   | 提出人     |                        |
| -- account     | String   | 学生学号   |                        |
| -- name        | String   | 学生姓名   |                        |
| - proposeTitle | String   | 问题标题   |                        |
| - proposeDesc  | String   | 问题描述   |                        |
| - proposeTime  | String   | 提出时间   |                        |
| - repair       | Object   | 维修员     |                        |
| -- account     | String   | 维修员账号 |                        |
| - repairDesc   | String   | 维修描述   |                        |
| - repairTime   | String   | 维修时间   |                        |
| - isHandle     | int      | 维修标记   | 0 为未处理，1 为已处理 |

- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 3,
        "forms": [
            {
                "id": 1,
                "propose": {
                    "account": "2016188001",
                    "password": null,
                    "name": "李四",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修1",
                "proposeDesc": "测试报修1",
                "proposeTime": "2020-03-27T04:06:01",
                "repair": {
                    "account": "10010",
                    "password": null
                },
                "repairDesc": "完善报修表单1",
                "repairTime": "2020-03-27T04:21:56",
                "isHandle": 1
            },
            {
                "id": 2,
                "propose": {
                    "account": "2016188001",
                    "password": null,
                    "name": "李四",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修2",
                "proposeDesc": "测试报修2",
                "proposeTime": "2020-03-27T04:06:07",
                "repair": null,
                "repairDesc": null,
                "repairTime": null,
                "isHandle": 0
            },
            {
                "id": 4,
                "propose": {
                    "account": "2016188002",
                    "password": null,
                    "name": "赵六",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修3",
                "proposeDesc": "测试报修3",
                "proposeTime": "2020-03-27T12:26:48",
                "repair": {
                    "account": "10010",
                    "password": null
                },
                "repairDesc": "完善报修表单4",
                "repairTime": "2020-03-27T12:27:59",
                "isHandle": 1
            }
        ]
    }
}
```

### 3.5、获取某人报修

- 请求路径：forms/one
- 请求方法：get
- 请求参数

| 参数名   | 参数类型 | 参数说明     | 备注                                           |
| -------- | -------- | ------------ | ---------------------------------------------- |
| account  | String   | 账号         | 不能为空                                       |
| type     | int      | 用户类型     | 不能为空，0 为学生，1 为维修人员               |
| pageNum  | int      | 当前页码     | 不能为空                                       |
| pageSize | int      | 每页显示条数 | 不能为空                                       |
| isHandle | int      | 是否处理     | 不能为空，-1为获取所有，0 为未处理，1 为已处理 |

- 响应数据（Object）

| 参数名         | 参数类型 | 参数说明   | 备注                   |
| -------------- | -------- | ---------- | ---------------------- |
| total          | long     | 总条数     |                        |
| forms          | List     | 报修表单   |                        |
| - id           | long     | id         |                        |
| - propose      | Object   | 提出人     |                        |
| -- account     | String   | 学生学号   |                        |
| -- name        | String   | 学生姓名   |                        |
| - proposeTitle | String   | 问题标题   |                        |
| - proposeDesc  | String   | 问题描述   |                        |
| - proposeTime  | String   | 提出时间   |                        |
| - repair       | Object   | 维修员     |                        |
| -- account     | String   | 维修员账号 |                        |
| - repairDesc   | String   | 维修描述   |                        |
| - repairTime   | String   | 维修时间   |                        |
| - isHandle     | int      | 维修标记   | 0 为未处理，1 为已处理 |

- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 2,
        "forms": [
            {
                "id": 1,
                "propose": {
                    "account": "2016188001",
                    "password": null,
                    "name": "李四",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修1",
                "proposeDesc": "测试报修1",
                "proposeTime": "2020-03-27T04:06:01",
                "repair": {
                    "account": "10010",
                    "password": null
                },
                "repairDesc": "完善报修表单1",
                "repairTime": "2020-03-27T04:21:56",
                "isHandle": 1
            },
            {
                "id": 2,
                "propose": {
                    "account": "2016188001",
                    "password": null,
                    "name": "李四",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修2",
                "proposeDesc": "测试报修2",
                "proposeTime": "2020-03-27T04:06:07",
                "repair": null,
                "repairDesc": null,
                "repairTime": null,
                "isHandle": 0
            }
        ]
    }
}
```

### 3.6、查询某个报修

- 请求路径：forms/query
- 请求方法：get
- 请求参数

| 参数名   | 参数类型 | 参数说明     | 备注     |
| -------- | -------- | ------------ | -------- |
| query    | String   | 查询         | 不能为空 |
| pageNum  | int      | 当前页码     | 不能为空 |
| pageSize | int      | 每页显示条数 | 不能为空 |

- 响应数据（Object）

| 参数名         | 参数类型 | 参数说明   | 备注                   |
| -------------- | -------- | ---------- | ---------------------- |
| total          | long     | 总条数     |                        |
| forms          | List     | 报修表单   |                        |
| - id           | long     | id         |                        |
| - propose      | Object   | 提出人     |                        |
| -- account     | String   | 学生学号   |                        |
| -- name        | String   | 学生姓名   |                        |
| - proposeTitle | String   | 问题标题   |                        |
| - proposeDesc  | String   | 问题描述   |                        |
| - proposeTime  | String   | 提出时间   |                        |
| - repair       | Object   | 维修员     |                        |
| -- account     | String   | 维修员账号 |                        |
| - repairDesc   | String   | 维修描述   |                        |
| - repairTime   | String   | 维修时间   |                        |
| - isHandle     | int      | 维修标记   | 0 为未处理，1 为已处理 |

- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 3,
        "forms": [
            {
                "id": 1,
                "propose": {
                    "account": "2016188001",
                    "password": null,
                    "name": "李四",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修1",
                "proposeDesc": "测试报修1",
                "proposeTime": "2020-03-27T04:06:01",
                "repair": {
                    "account": "10010",
                    "password": null
                },
                "repairDesc": "完善报修表单1",
                "repairTime": "2020-03-27T04:21:56",
                "isHandle": 1
            },
            {
                "id": 2,
                "propose": {
                    "account": "2016188001",
                    "password": null,
                    "name": "李四",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修2",
                "proposeDesc": "测试报修2",
                "proposeTime": "2020-03-27T04:06:07",
                "repair": null,
                "repairDesc": null,
                "repairTime": null,
                "isHandle": 0
            },
            {
                "id": 4,
                "propose": {
                    "account": "2016188002",
                    "password": null,
                    "name": "赵六",
                    "dormitory": {
                        "id": 1,
                        "name": "寝室a"
                    }
                },
                "proposeTitle": "测试报修3",
                "proposeDesc": "测试报修3",
                "proposeTime": "2020-03-27T12:26:48",
                "repair": {
                    "account": "10010",
                    "password": null
                },
                "repairDesc": "完善报修表单4",
                "repairTime": "2020-03-27T12:27:59",
                "isHandle": 1
            }
        ]
    }
}
```



### 3.7、完善报修

- 请求路径：forms/feedback
- 请求方法：put
- 请求参数

| 参数名   | 参数类型 | 参数说明    | 备注     |
| -------- | -------- | ----------- | -------- |
| id       | long     | 报修表单 id | 不能为空 |
| feedback | String   | 报修反馈    | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "反馈成功",
    "data": null
}
```



## 4、管理员(admin)

### 4.1、学生(student)

#### 4.1.1、获取所有学生

- 请求路径：admin/student/all
- 请求方法：get
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
| pageNum  | int      | 当前页码     | 不能为空 |
| pageSize | int      | 每页显示条数 | 不能为空 |

- 响应数据（Object）

| 参数名      | 参数类型 | 参数说明 | 备注 |
| ----------- | -------- | -------- | ---- |
| total       | long     | 总条数   |      |
| students    | List     | 用户     |      |
| - account   | String   | 学生学号 |      |
| - name      | String   | 学生姓名 |      |
| - dormitory | Object   | 宿舍     |      |
| -- id       | long     | 宿舍 id  |      |
| -- name     | String   | 宿舍名   |      |

- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 3,
        "students": [
            {
                "account": "2016188001",
                "password": null,
                "name": "李四",
                "dormitory": {
                    "id": 1,
                    "name": "寝室a"
                }
            },
            {
                "account": "2016188002",
                "password": null,
                "name": "赵六",
                "dormitory": {
                    "id": 1,
                    "name": "寝室a"
                }
            },
            {
                "account": "2016188085",
                "password": null,
                "name": "王五",
                "dormitory": {
                    "id": 2,
                    "name": "寝室b"
                }
            }
        ]
    }
}
```

#### 4.1.2、查询某个学生

- 请求路径：admin/student/query
- 请求方法：get
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注     |
| ------ | -------- | -------- | -------- |
| query  | String   | 查询     | 不能为空 |
| pageNum  | int      | 当前页码     | 不能为空 |
| pageSize | int      | 每页显示条数 | 不能为空 |

- 响应数据（List）

| 参数名    | 参数类型 | 参数说明 | 备注 |
| --------- | -------- | -------- | ---- |
| account   | String   | 学生学号 |      |
| name      | String   | 学生姓名 |      |
| dormitory | Object   | 宿舍     |      |
| - id      | long     | 宿舍 id  |      |
| - name    | String   | 宿舍名   |      |

- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 1,
        "students": [
            {
                "account": "2016188001",
                "password": null,
                "name": "李四",
                "dormitory": {
                    "id": 1,
                    "name": "寝室a"
                }
            }
        ]
    }
}
```



#### 4.1.3、修改学生密码

- 请求路径：admin/student/password
- 请求方法：put
- 请求参数

| 参数名         | 参数类型 | 参数说明   | 备注     |
| -------------- | -------- | ---------- | -------- |
| adminPass      | String   | 管理员密码 | 不能为空 |
| studentAccount | String   | 学生账号   | 不能为空 |
| studentNewPass | String   | 学生新密码 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "修改成功",
    "data": null
}
```

#### 4.1.4、删除学生

- 请求路径：admin/student
- 请求方法：delete
- 请求参数

| 参数名         | 参数类型 | 参数说明   | 备注     |
| -------------- | -------- | ---------- | -------- |
| adminPass      | String   | 管理员密码 | 不能为空 |
| studentAccount | String   | 学生账号   | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "删除成功",
    "data": null
}
```

#### 4.1.5、修改学生宿舍

- 请求路径：admin/student/dormitory
- 请求方法：put
- 请求参数

| 参数名              | 参数类型 | 参数说明   | 备注     |
| ------------------- | -------- | ---------- | -------- |
| adminPass           | String   | 管理员密码 | 不能为空 |
| studentAccount      | String   | 学生账号   | 不能为空 |
| studentNewDormitory | long     | 学生新宿舍 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "修改成功",
    "data": null
}
```



### 4.2、维修人员(repair)

#### 4.2.1、获取所有维修人员

- 请求路径：admin/repair/all
- 请求方法：get
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
| pageNum  | int      | 当前页码     | 不能为空 |
| pageSize | int      | 每页显示条数 | 不能为空 |

- 响应数据（Object）

| 参数名    | 参数类型 | 参数说明   | 备注 |
| --------- | -------- | ---------- | ---- |
| total     | long     | 总条数     |      |
| repairs   | List     | 维修人员   |      |
| - account | String   | 维修员账号 |      |

- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 1,
        "repairs": [
            {
                "account": "10010",
                "password": null
            }
        ]
    }
}
```

#### 4.2.1、查询某个维修人员

- 请求路径：admin/repair/query
- 请求方法：get
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注     |
| ------ | -------- | -------- | -------- |
| query  | String   | 查询     | 不能为空 |
| pageNum  | int      | 当前页码     | 不能为空 |
| pageSize | int      | 每页显示条数 | 不能为空 |

- 响应数据（Object）

| 参数名    | 参数类型 | 参数说明   | 备注 |
| --------- | -------- | ---------- | ---- |
| total     | long     | 总条数     |      |
| repairs   | List     | 维修人员   |      |
| - account | String   | 维修员账号 |      |

- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 1,
        "repairs": [
            {
                "account": "10010",
                "password": null
            }
        ]
    }
}
```



#### 4.2.3、修改维修人员密码

- 请求路径：admin/repair/password
- 请求方法：put
- 请求参数

| 参数名        | 参数类型 | 参数说明       | 备注     |
| ------------- | -------- | -------------- | -------- |
| adminPass     | String   | 管理员密码     | 不能为空 |
| repairAccount | String   | 维修人员账号   | 不能为空 |
| repairNewPass | String   | 维修人员新密码 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "修改成功",
    "data": null
}
```

#### 4.2.4、新建维修人员

- 请求路径：admin/repair
- 请求方法：post
- 请求参数

| 参数名        | 参数类型 | 参数说明     | 备注     |
| ------------- | -------- | ------------ | -------- |
| adminPass     | String   | 管理员密码   | 不能为空 |
| repairAccount | String   | 维修人员账号 | 不能为空 |
| repairPass    | String   | 维修人员密码 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "新建成功",
    "data": null
}
```

#### 4.2.5、删除维修人员

- 请求路径：admin/repair
- 请求方法：delete
- 请求参数

| 参数名        | 参数类型 | 参数说明     | 备注     |
| ------------- | -------- | ------------ | -------- |
| adminPass     | String   | 管理员密码   | 不能为空 |
| repairAccount | String   | 维修人员账号 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "删除成功",
    "data": null
}
```

### 4.3、宿舍(dormitory)

#### 4.3.1、获取所有宿舍

- 请求路径：admin/dormitory/all
- 请求方法：get
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
| pageNum  | int      | 当前页码     | 不能为空 |
| pageSize | int      | 每页显示条数 | 不能为空 |

- 响应数据（Object）

| 参数名      | 参数类型 | 参数说明 | 备注 |
| ----------- | -------- | -------- | ---- |
| total       | long     | 总条数   |      |
| dormitories | List     | 宿舍     |      |
| - id        | long     | 宿舍 id  |      |
| - name      | String   | 宿舍名   |      |

- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 2,
        "dormitories": [
            {
                "id": 1,
                "name": "寝室a"
            },
            {
                "id": 2,
                "name": "寝室b"
            }
        ]
    }
}
```

#### 4.3.2、查询某个宿舍

- 请求路径：admin/dormitory/query
- 请求方法：get
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注     |
| ------ | -------- | -------- | -------- |
| query  | String   | 查询     | 不能为空 |
| pageNum  | int      | 当前页码     | 不能为空 |
| pageSize | int      | 每页显示条数 | 不能为空 |

- 响应数据（Object）

| 参数名      | 参数类型 | 参数说明 | 备注 |
| ----------- | -------- | -------- | ---- |
| total       | long     | 总条数   |      |
| dormitories | List     | 宿舍     |      |
| - id        | long     | 宿舍 id  |      |
| - name      | String   | 宿舍名   |      |


- 响应数据

```json
{
    "code": 201,
    "msg": "获取成功",
    "data": {
        "total": 2,
        "dormitories": [
            {
                "id": 1,
                "name": "寝室a"
            },
            {
                "id": 2,
                "name": "寝室b"
            }
        ]
    }
}
```

#### 4.3.3、新建宿舍

- 请求路径：admin/dormitory
- 请求方法：post
- 请求参数

| 参数名        | 参数类型 | 参数说明   | 备注     |
| ------------- | -------- | ---------- | -------- |
| adminPass     | String   | 管理员密码 | 不能为空 |
| dormitoryName | String   | 宿舍名     | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "新建成功",
    "data": null
}
```

#### 4.3.4、删除宿舍

- 请求路径：admin/dormitory
- 请求方法：delete
- 请求参数

| 参数名      | 参数类型 | 参数说明   | 备注     |
| ----------- | -------- | ---------- | -------- |
| adminPass   | String   | 管理员密码 | 不能为空 |
| dormitoryId | long     | 宿舍 id    | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "删除成功",
    "data": null
}
```

### 4.4、报修(form)

#### 4.4.1、删除报修

- 请求路径：admin/form
- 请求方法：delete
- 请求参数

| 参数名    | 参数类型 | 参数说明   | 备注     |
| --------- | -------- | ---------- | -------- |
| adminPass | String   | 管理员密码 | 不能为空 |
| formId    | long     | 报修 id    | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 200,
    "msg": "删除成功",
    "data": null
}
```

