### 接口文档

#### 边缘网关

baseURL: http://localhost:8080

##### 机器设备相关

|  方法  | 地址                    |       描述       |
| :----: | :---------------------- | :--------------: |
|  GET   | /api/device             | 获取所有设备信息 |
|  POST  | /api/device             | 创建一个设备信息 |
|  GET   | /api/device/{id}        | 获取指定设备信息 |
|  PUT   | /api/device/{id}        | 修改指定设备信息 |
| DELETE | /api/device/{id}        | 删除指定设备信息 |
|  GET   | /api/device/{id}/status | 获取指定设备状态 |

##### 管理员相关

|  方法  | 地址            |        描述        |
| :----: | --------------- | :----------------: |
|  GET   | /api/admin/{id} | 获取指定管理员信息 |
|  POST  | /api/admin      | 创建一个管理员信息 |
|  PUT   | /api/admin/{id} | 修改指定管理员信息 |
| DELETE | /api/admin/{id} | 删除指定管理员信息 |

##### 操作记录相关

|  方法  | 地址             |       描述       |
| :----: | ---------------- | :--------------: |
|  GET   | /api/record      | 获取所有操作记录 |
|  GET   | /api/record/{id} | 获取指定操作记录 |
|  POST  | /api/record      | 创建一条操作记录 |
| DELETE | /api/record/{id} | 删除指定操作记录 |

#### 产品溯源

##### 客户相关

|  方法  | 地址               |       描述       |
| :----: | ------------------ | :--------------: |
|  GET   | /api/customer/{id} | 获取指定客户信息 |
|  POST  | /api/customer      | 创建一条客户信息 |
|  PUT   | /api/customer/{id} | 修改指定客户信息 |
| DELETE | /api/customer/{id} | 删除指定客户信息 |



##### 产品相关（由服务端根据上传数据自动生成）

|  方法  | 地址              |       描述       |
| :----: | ----------------- | :--------------: |
|  GET   | /api/product/{id} | 获取指定产品信息 |
| DELETE | /api/product/{id} | 删除指定产品信息 |

##### 订单相关

| 方法 | 地址            |                    描述                    |
| :--: | --------------- | :----------------------------------------: |
| GET  | /api/order      |                获取所有订单                |
| GET  | /api/order/{id} |                获取指定订单                |
| POST | /api/order      |                创建一条订单                |
| PUT  | /api/order      | 修改指定订单（未确认之前修改，否则无权限） |

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