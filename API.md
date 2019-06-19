### 接口文档

此接口文档为边缘网关和产品溯源两个系统的接口。

baseURL: http://119.23.243.252:8080

#### 返回结果状态码枚举

```json
{
    "200": "请求成功",
    "201": "创建或修改成功",
    "204": "删除成功",
    "301": "创建或修改失败",
    "400": "请求参数错误",
    "401": "未登录",
    "403": "禁止访问",
    "404": "资源未找到",
    "500": "系统错误"
}
```

#### 设备实时状态监控相关

WebSocket 地址：http://119.23.243.252:8080/ws

- GET /api/status/{timestamp} 获取时间戳到现在的设备状态数据
  - Request
  
  ```json
    {
        "headers": {
            "token": "生成的token"
        }
    }
  ```
  
  - Response
  
  ```json
    {
        "code": 200,
        "msg": "请求成功",
        "data": [
            {
                "statusId": "00fee5083e7844019698f5aaacabdd65",
                "machineId": "527333003",
                "createTime": "2019-06-19T10:56:43.000+0000",
                "temperature": "26.5",
                "temperatureWarn": "1",
                "fan": "0",
                "humidity": "40.5",
                "humidityWarn": "1",
                "voltage": "24.000",
                "electric": "4.123",
                "power": "96.999",
                "weight": "5.666",
                "weightWarn": "1",
                "motorOpen": "1",
                "motorSpeed": "300",
                "motorDir": "1",
                "slideOpen": "1",
                "slideSpeed": "8",
                "slideDir": "1",
                "rodDistance": "15",
                "machineError": "1",
                "reservedA": "100",
                "reservedB": "100",
                "reservedC": "100",
                "reservedD": "1"
            }
        ]
    }
  ```

- GET /api/status/{start}/{end} 获取start到end的设备状态数据

  - Request
  
  ```json
    {
        "headers": {
            "token": "生成的token"
        }
    }
  ```
  
  - Response
  
  ```json
    {
        "code": 200,
        "msg": "请求成功",
        "data": [
            {
                "statusId": "00fee5083e7844019698f5aaacabdd65",
                "machineId": "527333003",
                "createTime": "2019-06-19T10:56:43.000+0000",
                "temperature": "26.5",
                "temperatureWarn": "1",
                "fan": "0",
                "humidity": "40.5",
                "humidityWarn": "1",
                "voltage": "24.000",
                "electric": "4.123",
                "power": "96.999",
                "weight": "5.666",
                "weightWarn": "1",
                "motorOpen": "1",
                "motorSpeed": "300",
                "motorDir": "1",
                "slideOpen": "1",
                "slideSpeed": "8",
                "slideDir": "1",
                "rodDistance": "15",
                "machineError": "1",
                "reservedA": "100",
                "reservedB": "100",
                "reservedC": "100",
                "reservedD": "1"
            }
        ]
    }
  ```

#### 客户相关

- POST /api/customer 客户注册并添加信息
  - Request

  ```json
  {
      "headers": {
          "Content-Type": "application/x-www-form-urlencoded"
      },
      "body": {
          "name": "Bob",
          "password": "123",
          "phone": "1837782225",
          "email": "Bob@qq.com",
          "addr": "中国"
      }
  }
  ```

  - Response

  ```json
  {
      "code":201,
      "msg":"创建，修改成功",
      "data":{
          "id":"bef1a6535ff34426a59da75f909e56c3"
      }
  }
  ```

- PUT /api/customer/{id} 通过客户 ID 修改客户信息

  - Request

  ```json
  {
      "headers": {
          "Content-Type": "application/x-www-form-urlencoded"
      },
      "body": {
          "name": "Jack",
          "phone": "12345678",
          "email": "test@test.com",
          "addr": "江西"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "row": "1"
      }
  }
  ```

* GET /api/customer/{id} 通过 Id 获取客户信息

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "customerId": "d80f0a80f15e41fc9b93e83d7d8f7b92",
          "name": "Alice",
          "phone": "1234567",
          "email": "test@123.com",
          "addr": "江西",
          "createTime": "2019-06-18T00:53:35.000+0000",
          "updateTime": "2019-06-18T00:55:05.000+0000"
      }
  }
  ```

* DELETE /api/customer/{id} 通过客户 ID 删除客户信息

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "row": "2"
      }
  }
  ```

#### 管理员相关 

* POST /api/admin 管理员注册

  * Request

  ```json
  {
      "headers": {
          "Content-Type": "x-www-form-urlencoded"
      },
      "body": {
          "name": "Jack",
          "password": "1234",
          "email": "test@test.com",
          "phone": "12345678"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 201,
      "msg": "创建，修改成功",
      "data": {
          "id": "2a7f659608b8485bac1c3c387aeb8c8a"
      }
  }
  ```

* PUT /api/admin/{id} 通过 ID 修改管理员信息

  * Request

  ```json
  {
      "headers": {
          "Content-Type": "x-www-form-urlencoded"
      },
      "body": {
          "name": "Jack2",
          "email": "tes22t@123.com",
          "phone": "1234567822"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "row": "1"
      }
  }
  ```

* GET /api/admin/{id} 通过 ID 查找管理员信息

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "adminId": "2a7f659608b8485bac1c3c387aeb8c8a",
          "name": "Jack2",
          "phone": "1234567822",
          "email": "tes22t@123.com",
          "createTime": "2019-06-18T01:03:44.000+0000",
          "updateTime": "2019-06-18T01:06:39.000+0000"
      }
  }
  ```

* DELETE /api/admin/{id} 通过 ID 删除管理员信息

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "row": "2"
      }
  }
  ```

#### 账户相关

* POST /api/account 账户登录授权

  * Request

  ```json
  {
      "headers": {
          "Content-Type": "application/x-www-form-urlencoded"
      },
      "body": {
          "name": "test",
          "password": "1234"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "token": "生成的token"
      }
  }
  ```

#### 设备相关

​	通过上传的数据自动生成设备

* GET /api/machine 获取机器设备列表

  * Request

  ```json
  {
      "headers": {
         	"token": "生成的token"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": [
          {
              "producibleId": "3f65bae7481348cd8c8b01aeaaf932f2",
              "name": "Line 1",
              "producibleDesc": "xxx",
              "type": "1001",
              "image": "xxx.xx.jpg"
          }
      ]
  }
  ```

* GET /api/machine/{id} 获取设备信息

  * Request

  ```json
  {
      "headers": {
         	"token": "生成的token"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "machineId": "8962cc3b65bc4618b94b28eeebdfb0e7",
          "name": "拉丝机",
          "machineDesc": "XXXX",
          "type": "大型机"
      }
  }
  ```

* PUT /api/machine/{id} 通过 ID 修改设备信息

  * Request

  ```json
  {
      "headers": {
          "Content-Type": "application/x-www-form-urlencodec",
         	"token": "生成的token"
      },
      "body": {
          "name": "machine 1",
          "desc": "XXX",
          "type": "Large machine"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "row": "1"
      }
  }
  ```

#### 可生产产品相关

* GET /api/producible 获取所有可生产的产品列表

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": [
          {
              "producibleId": "3f65bae7481348cd8c8b01aeaaf932f2",
              "name": "Line 1",
              "producibleDesc": "xxx",
              "type": "1001",
              "image": "xxx.xx.jpg"
          }
      ]
  }
  ```

* POST /api/producible 创建一条可生产产品信息

  * Request

  ```json
  {
      "headers": {
          "Content-type": "application/x-www-form-urlencoded",
          "token": "生成的token"
      },
      "body": {
          "name": "Line 1",
          "desc": "xxx",
          "type": "1001",
          "image": "xxx.xx.jpg"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 201,
      "msg": "创建，修改成功",
      "data": {
          "id": "03439fffdf1c469ab7d3e69d05228c3b"
      }
  }
  ```

* GET /api/producible/{id}  获取指定可生产产品信息

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "producibleId": "03439fffdf1c469ab7d3e69d05228c3b",
          "name": "Line 1",
          "producibleDesc": "xxx",
          "type": "1001",
          "image": "xxx.xx.jpg"
      }
  }
  ```

* DELETE /api/producible/{id} 删除指定可生产产品信息1

  * Request

  ```json
  {
      "headers": {
          "token": "生成的token"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "row": "1"
      }
  }
  ```

#### 已生产产品相关 (UNDO)

​	通过上传的数据生成产品实体

* GET /api/product/{id} 通过 ID 查找产品信息

#### 订单相关

* GET /api/order 获取当前用户所有订单

  * Request

  ```json
  {
      "headers": {
         	"token": "生成的token"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": [
          {
              "orderId": "2070ecd0f1a444f4ba4823ed9c38c8e2",
              "producibleId": "03439fffdf1c469ab7d3e69d05228c3b",
              "customerId": "ae9e6b3b17fa495597ad8881b528f6bc",
              "number": 30,
              "diameter": 30,
              "length": 30,
              "weight": 30,
              "createTime": "2019-06-18T03:21:08.000+0000",
              "updateTime": "2019-06-18T03:23:32.000+0000"
          },
          {
              "orderId": "c7507620eb2d4b1da900b8c8051752b3",
              "producibleId": "03439fffdf1c469ab7d3e69d05228c3b",
              "customerId": "ae9e6b3b17fa495597ad8881b528f6bc",
              "number": 20,
              "diameter": 10,
              "length": 10,
              "weight": 10,
              "createTime": "2019-06-18T03:21:38.000+0000",
              "updateTime": "2019-06-18T03:21:38.000+0000"
          }
      ]
  }
  ```

* POST /api/order 创建一条订单

  * Request

  ```json
  {
      "headers": {
          "Content-type": "x-www-form-urlencoded",
          "token": "生成的token"
      },
      "body": {
          "producible_id": "可生产的产品ID",
          "number": 10,
          "diameter": 10.0,
          "length": 10.0,
          "weight": 10.0
      }
  }
  ```

  * Response

  ```json
  {
      "code": 201,
      "msg": "创建，修改成功",
      "data": {
          "id": "2070ecd0f1a444f4ba4823ed9c38c8e2"
      }
  }
  ```

* PUT /api/order/{id} 通过 ID 修改订单信息

  * Request

  ```json
  {
      "headers": {
          "Content-type": "x-www-form-urlencoded",
          "token": "生成的token"
      },
      "body": {
          "producible_id": "可生产的产品ID",
          "number": 30,
          "diameter": 30.0,
          "length": 30.0,
          "weight": 30.0
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": "1"
  }
  ```

#### 工人信息相关

* GET /api/worker 获取所有工人信息

  * Request

  ```json
  {
      "headers": {
          "token": "生成的token"
      }
  }
  ```

  * Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": [
          {
              "workerId": "621eecf5ddc641bbaad79c7ef5f5c03b",
              "name": "workerA",
              "phone": "18379822223",
              "department": "A1",
              "post": "worker"
          }
      ]
  }
  ```

  

* GET /api/worker/{id}  通过 ID 获取工人信息

  * Request

  ```json
  {
      "headers": {
          "token": "生成的token"
      }
  }
  ```

  - Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "workerId": "621eecf5ddc641bbaad79c7ef5f5c03b",
          "name": "workerA",
          "phone": "18379822223",
          "department": "A1",
          "post": "worker"
      }
  }
  ```

  

* POST /api/worker 创建一条工人信息

  * Request

  ```json
  {
      "headers": {
          "Content-type": "x-www-form-urlencoded",
          "token": "生成的token"
      },
      "body": {
          "name": "workerA",
          "phone": "18379822223",
          "department": "A1",
          "post": "worker"
      }
  }
  ```

  - Response

  ```json
  {
      "code": 201,
      "msg": "创建，修改成功",
      "data": {
          "id": "621eecf5ddc641bbaad79c7ef5f5c03b"
      }
  }
  ```

  

* PUT /api/worker/{id}  通过 ID 修改工人信息

  - Request

  ```json
  {
      "headers": {
          "Content-type": "x-www-form-urlencoded",
          "token": "生成的token"
      },
      "body": {
          "name": "Aszed",
          "phone": "18379822223",
          "department": "A2",
          "post": "manager"
      }
  }
  ```

  

  - Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "row": "1"
      }
  }
  ```

  

* DELETE /api/worker/{id}  通过 ID 删除工人信息

  - Request

  ```json
  {
      "headers": {
          "token": "生成的token"
      }
  }
  ```

  - Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "row": "1"
      }
  }
  ```

##### 机器设备相关(通过上传的数据生成设备)

| 方法 | 地址              |       描述       |
| :--: | :---------------- | :--------------: |
| GET  | /api/machine      | 获取所有设备信息 |
| GET  | /api/machine/{id} | 获取指定设备信息 |
| PUT  | /api/machine/{id} | 修改指定设备信息 |

##### 管理员相关

|  方法  | 地址            |         描述         |
| :----: | --------------- | :------------------: |
|  GET   | /api/admin/{id} |  获取指定管理员信息  |
|  POST  | /api/admin      | 管理员注册并添加信息 |
|  PUT   | /api/admin/{id} |  修改指定管理员信息  |
| DELETE | /api/admin/{id} |  删除指定管理员信息  |

##### 操作记录相关(通过上传数据创建记录)

|  方法  | 地址             |       描述       |
| :----: | ---------------- | :--------------: |
|  GET   | /api/record      | 获取所有操作记录 |
|  GET   | /api/record/{id} | 获取指定操作记录 |
| DELETE | /api/record/{id} | 删除指定操作记录 |

##### 账户相关

| 方法 | 地址         |        描述         |
| :--: | ------------ | :-----------------: |
| POST | /api/account | 账户登录，返回token |

#### 产品溯源

##### 客户相关

|  方法  | 地址               |        描述        |
| :----: | ------------------ | :----------------: |
|  GET   | /api/customer/{id} |  获取指定客户信息  |
|  POST  | /api/customer      | 客户注册并添加信息 |
|  PUT   | /api/customer/{id} |  修改指定客户信息  |
| DELETE | /api/customer/{id} |  删除指定客户信息  |



##### 生产成品相关（生产过程数据由服务端根据上传数据自动生成）

| 方法 | 地址              |       描述       |
| :--: | ----------------- | :--------------: |
| GET  | /api/product/{id} | 获取指定产品信息 |

##### 可生产产品相关

|  方法  | 地址                 |                  描述                  |
| :----: | -------------------- | :------------------------------------: |
|  GET   | /api/producible      |         获取所有可生产产品信息         |
|  POST  | /api/producible      | 创建一条可生产产品信息(需要管理员权限) |
|  GET   | /api/producible/{id} |         获取指定可生产产品信息         |
| DELETE | /api/producible/{id} | 删除指定可生产产品信息(需要管理员权限) |

##### 订单相关

| 方法 | 地址            |                    描述                    |
| :--: | --------------- | :----------------------------------------: |
| GET  | /api/order      |                获取所有订单                |
| GET  | /api/order/{id} |                获取指定订单                |
| POST | /api/order      |                创建一条订单                |
| PUT  | /api/order/{id} | 修改指定订单（未确认之前修改，否则无权限） |

##### 管理员相关

同边缘网关管理员接口

##### 工人相关

|  方法  | 地址             |       描述       |
| :----: | ---------------- | :--------------: |
|  GET   | /api/worker      | 获取所有工人信息 |
|  GET   | /api/worker/{id} | 获取指定工人信息 |
|  POST  | /api/worker      | 创建一条工人信息 |
|  PUT   | /api/worker/{id} | 修改指定工人信息 |
| DELETE | /api/worker/{id} | 删除一条工人信息 |