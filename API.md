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

#### 安卓App下载相关

GET /api/download/origins 产品溯源系统app下载
GET /api/download/gateway 边缘网关系统app下载

#### 设备实时状态监控相关

WebSocket 地址：http://119.23.243.252:8080/ws

- 属性说明

|      属性       |       说明       |
| :-------------: | :--------------: |
|   temperature   |       温度       |
| temperatureWarn | 温度超过阈值预警 |
|       fan       | 散热风扇开启状态 |
|    humidity     |       湿度       |
|  humidityWarn   | 湿度超过阈值预警 |
|     voltage     |       电压       |
|    electric     |       电流       |
|      power      |       功耗       |
|     weight      |   加工货物重量   |
|   weightWarn    |   重量超载预警   |
|    motorOpen    |   电机开关状态   |
|   motorSpeed    |     电机转速     |
|    motorDir     |     电机转向     |
|    slideOpen    |   滑台开关状态   |
|   slideSpeed    |   滑台移动速度   |
|    slideDir     |   滑台移动方向   |
|   rodDistance   |   推杆推送距离   |
|  machineError   |   机器故障预警   |
|    reservedA    |    预留状态A     |
|    reservedB    |    预留状态B     |
|    reservedC    |    预留状态C     |
|    reservedD    |    预留状态D     |

- GET /api/status/{timestamp} 获取时间戳到现在的设备状态数据
  - Request
  ```json
    {
        "headers": {
            "token": "admin_token"
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
            "token": "admin_token"
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
- PUT /api/customer 通过客户 token 修改客户信息
  - Request
  ```json
  {
      "headers": {
          "Content-Type": "application/x-www-form-urlencoded",
          "token": "customer_token"
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
* GET /api/customer 通过 token 获取客户信息
  - Request
  ```json
  {
        "headers": {
            "token": "customer_token"
        }
    }
  ```
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

* DELETE /api/customer 通过客户token 删除客户信息
  - Request

  ```json
  {
        "headers": {
            "token": "customer_token"
        }
    }
  ```

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
          "Content-Type": "application/x-www-form-urlencoded"
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

* PUT /api/admin 通过 token 修改管理员信息
  * Request
  ```json
  {
      "headers": {
          "Content-Type": "application/x-www-form-urlencoded",
          "token": "admin_token"
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
* GET /api/admin 通过 token 查找管理员信息

  - Request

  ```json
  {
        "headers": {
            "token": "admin_token"
        }
    }
  ```

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

* DELETE /api/admin 通过 token 删除管理员信息

  - Request

  ```json
  {
        "headers": {
            "token": "admin_token"
        }
  }
  ```

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
          "token": "customer_token / admin_token"
      }
  }
  ```

#### 控制下发相关

- 指令对照表

| 指令字符串(cmd) |      含义      |
| :-------------: | :------------: |
|   motor-stop    |    电机静止    |
|  motor-forward  |    电机正转    |
|  motor-reverse  |    电机反转    |
|   motor-fast    |  电机速度: 快  |
|  motor-middle   |  电机速度: 中  |
|   motor-slow    |  电机速度: 慢  |
|   slide-open    |    滑台打开    |
|   slide-close   |    滑台关闭    |
|   slide-fast    |  滑台速度: 快  |
|  slide-middle   |  滑台速度: 中  |
|   slide-slow    |  滑台速度: 慢  |
|    rod-dis-0    | 推杆距离: 0cm  |
|    rod-dis-5    | 推杆距离: 5cm  |
|   rod-dis-15    | 推杆距离: 15cm |
|   rod-dis-20    | 推杆距离: 20cm |
|    fan-open     |    风扇打开    |
|    fan-close    |    风扇关闭    |



- GET /api/cmd/{cmd} 控制下发

  - Request

  ```json
  {
        "headers": {
            "token": "admin_token"
        }
  }
  ```

  - Response

  ```json
  {
      "code": 200,
      "msg": "请求成功"
  }
  ```

  

#### 设备相关

​	通过上传的数据自动生成设备

* GET /api/machine 获取机器设备列表

  * Request

  ```json
  {
      "headers": {
         	"token": "admin_token"
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
              "machineId": "8962cc3b65bc4618b94b28eeebdfb0e7",
              "name": "拉丝机",
              "machineDesc": "XXXX",
              "type": "大型机"
          }
      ]
  }
  ```

* GET /api/machine/{id} 获取设备信息

  * Request

  ```json
  {
      "headers": {
         	"token": "admin_token"
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
          "name": "Machine 1",
          "machineDesc": "XXXX",
          "type": "Small machine"
      }
  }
  ```

* PUT /api/machine/{id} 通过 ID 修改设备信息

  * Request

  ```json
  {
      "headers": {
          "Content-Type": "application/x-www-form-urlencoded",
         	"token": "admin_token"
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
          "token": "admin_token"
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
          "token": "admin_token"
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

#### 已生产产品相关

- GET /api/product/{id} 通过产品ID产找产品生产过程数据

  - Response

  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "third": {
              "dataId": "6ca3076bfa77412f87ef4015f7cd9468",
              "productId": "3001",
              "workerId": "1",
              "diameter": "0.250000",
              "length": "50.000000",
              "weight": "0.087331",
              "createTime": "2019-06-25T01:41:51.000+0000",
              "previous": "2001"
          },
          "fourth": {
              "dataId": "67cd140307cc4647986ba12e364c8f2b",
              "productId": "4001",
              "workerId": "2",
              "diameter": "0.250000",
              "length": "50.000000",
              "weight": "0.000000",
              "tensile": "452.000000",
              "createTime": "2019-06-25T01:49:16.000+0000",
              "previous": "3001"
          },
          "first": {
              "dataId": "545ad0a6856045a581378bc3bbd281fb",
              "productId": "1001",
              "workerId": "1",
              "copper": "0.010000",
              "tin": "0.030000",
              "zinc": "0.020000",
              "createTime": "2019-06-25T01:35:17.000+0000"
          },
          "second": {
              "dataId": "9a8e957a99f94ae181f83c031948a140",
              "productId": "2001",
              "workerId": "2",
              "diameter": "0.250000",
              "length": "100.000000",
              "weight": "0.174662",
              "createTime": "2019-06-25T01:38:15.000+0000",
              "previous": "1001"
          }
      }
  }
  ```

#### 订单相关

* GET /api/order 获取当前用户所有订单

  * Request

  ```json
  {
      "headers": {
         	"token": "customer_token"
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
              "handle": {
                  "orderId": "5e1ae67209fe4615b641a6617d51abda",
                  "producibleId": "b078a3da18c14ad0ad44e007daf46c68",
                  "customerId": "283fed7669514dada626a408f0b1096a",
                  "number": "30",
                  "diameter": "30",
                  "length": "31.1",
                  "weight": "20.3",
                  "createTime": "2019-06-22T08:12:37.000+0000",
                  "updateTime": "2019-06-22T08:33:50.000+0000"
              },
              "status": {
                  "orderId": "5e1ae67209fe4615b641a6617d51abda",
                  "orderStatus": "ACCEPT"
              }
          },
          {
              "handle": {
                  "orderId": "6225247741684ecab7f25594486363d7",
                  "producibleId": "b078a3da18c14ad0ad44e007daf46c68",
                  "customerId": "283fed7669514dada626a408f0b1096a",
                  "number": "10",
                  "diameter": "20.0",
                  "length": "30.0",
                  "weight": "40.0",
                  "createTime": "2019-06-22T08:12:05.000+0000",
                  "updateTime": "2019-06-22T08:12:05.000+0000"
              },
              "status": {
                  "orderId": "6225247741684ecab7f25594486363d7",
                  "orderStatus": "CREATED"
              }
          },
          {
              "handle": {
                  "orderId": "6fd1d61ae6b4486eaa5797f66a9a5817",
                  "producibleId": "b078a3da18c14ad0ad44e007daf46c68",
                  "customerId": "283fed7669514dada626a408f0b1096a",
                  "number": "30",
                  "diameter": "30",
                  "length": "31.1",
                  "weight": "20.3",
                  "createTime": "2019-06-22T08:29:23.000+0000",
                  "updateTime": "2019-06-22T08:29:23.000+0000"
              },
              "status": {
                  "orderId": "6fd1d61ae6b4486eaa5797f66a9a5817",
                  "orderStatus": "CREATED"
              }
          },
          {
              "handle": {
                  "orderId": "b5df94d4777140cfb20c06de475b4f1f",
                  "producibleId": "4ec812351eb046b1be768b5cf6cf331f",
                  "customerId": "283fed7669514dada626a408f0b1096a",
                  "number": "10",
                  "diameter": "20.0",
                  "length": "10.0",
                  "weight": "20.0",
                  "createTime": "2019-06-22T08:14:02.000+0000",
                  "updateTime": "2019-06-22T08:14:02.000+0000"
              },
              "status": {
                  "orderId": "b5df94d4777140cfb20c06de475b4f1f",
                  "orderStatus": "CREATED"
              }
          }
      ]
  }
  ```

* POST /api/order 创建一条订单

  * Request

  ```json
  {
      "headers": {
          "Content-type": "application/x-www-form-urlencoded",
          "token": "customer_token"
      },
      "body": {
          "producible_id": "可生产的产品ID",
          "number": "10",
          "diameter": "10.0",
          "length": "10.0",
          "weight": "10.0"
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

* GET /api/order/{id} 通过订单 ID 获取详细信息
  * Request
  ```json
  {
      "headers": {
          "token": "customer_token"
      }
  }
  ```
  * Response
  ```json
  {
      "code": 200,
      "msg": "请求成功",
      "data": {
          "order": {
              "orderId": "cbd74163ce924d4abfccaa38e82202e1",
              "producibleId": "43f56770be0946c58e94cd1d1157c9d8",
              "customerId": "7284124763ca4d888ba2dc1046ee5715",
              "number": "10",
              "diameter": "20.0",
              "length": "30.0",
              "weight": "4.0",
              "createTime": "2019-06-22T09:11:38.000+0000",
              "updateTime": "2019-06-22T09:11:38.000+0000"
          },
          "status": {
              "orderId": "cbd74163ce924d4abfccaa38e82202e1",
              "orderStatus": "CREATE"
          }
      }
  }
  ```

* PUT /api/order/{id} 通过 ID 修改订单信息

  * Request

  ```json
  {
      "headers": {
          "Content-type": "application/x-www-form-urlencoded",
          "token": "customer_token"
      },
      "body": {
          "producible_id": "可生产的产品ID",
          "number": "30",
          "diameter": "30.0",
          "length": "30.0",
          "weight": "30.0"
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

#### 订单处理相关

- GET /api/order/admin 获取所有的订单信息

  - Request

  ```json
  {
      "headers": {
         "token": "admin_token"
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
              "order": {
                  "orderId": "8cd6c4e9eb7a4c7888752847553fe26d",
                  "producibleId": "51af2759583d46599f2fa988b6a47a71",
                  "customerId": "ff99e3ae5edb451eb82fb7a9ca2561c6",
                  "number": "10",
                  "diameter": "20",
                  "length": "10",
                  "weight": "20",
                  "createTime": "2019-06-22T06:45:35.000+0000",
                  "updateTime": "2019-06-22T06:45:35.000+0000"
              },
              "status": {
                  "orderId": "8cd6c4e9eb7a4c7888752847553fe26d",
                  "orderStatus": "ACCEPT"
              }
          }
      ]
  }
  ```

  

- GET /api/handle 获取所有处理订单记录
  * Request
  ```json
  {
      "headers": {
         "token": "admin_token"
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
            "handle": {
                "orderId": "5e1ae67209fe4615b641a6617d51abda",
                "adminId": "67bc736f2f5c413089bcdeb3db3a424a",
                "handleTime": "2019-06-22T08:37:26.000+0000",
                "handleResult": "ACCEPT"
            },
            "status": {
                "orderId": "5e1ae67209fe4615b641a6617d51abda",
                "orderStatus": "ACCEPT"
            }
        }
    ]
  }
  ```

- PUT /api/handle/{id} 修改指定订单状态
  * Request
  ```json
  {
    "headers": {
        "token": "admin_token"
    },
    "body": {
        "status": "ACCEPT / REFUSE / CREATE"
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

#### 工人信息相关

* GET /api/worker 获取所有工人信息

  * Request

  ```json
  {
      "headers": {
          "token": "admin_token"
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
          "token": "admin_token"
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
          "Content-type": "application/x-www-form-urlencoded",
          "token": "admin_token"
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
          "Content-type": "application/x-www-form-urlencoded",
          "token": "admin_token"
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
          "token": "admin_token"
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