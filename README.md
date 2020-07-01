站点：<http://www.panzvor.com>
## 1、技术框架

- 前端开发框架：Vue.js 2.5
- 前端UI框架：Semantic UI 2.4
- 后端开发框架：SpringBoot 2.3.0
- 后端持久层框架：MyBatis 3.5.4
- 数据库：MySQL 5.7.3、Redis 3.2.1

## 2、架构

![博客架构图](http://cdn.panzvor.com/img/20200701014530.png)
## 3、目录结构

### 3.1、Vue

![image-20200701205011286](http://cdn.panzvor.com/img/20200701205013.png)

### 3.2、SpringBoot

![image-20200701205443725](http://cdn.panzvor.com/img/20200701205445.png)

## 4、项目部署

### 4.1、Vue项目

进入`Vue_blog/my_blog`目录：

安装依赖：

```shell
npm install
```

运行项目：

```shell
npm run serve
```

打包：

```shell
npm run build
```

接口请求地址在`network/request.js`中自定义：

![image-20200701205803455](http://cdn.panzvor.com/img/20200701205804.png)

部署时`vue.config.js`中代理的设置也要根据自己服务器的实际情况配置：

![image-20200701205927207](http://cdn.panzvor.com/img/20200701205929.png)

### 4.2、SpringBoot项目

`application.yml`中有几处需要自己改好的：

邮件发送：

![image-20200701210111987](http://cdn.panzvor.com/img/20200701210113.png)

以及数据库连接相关的：

![image-20200701210207845](http://cdn.panzvor.com/img/20200701210209.png)

端口号开发环境默认时8001，生产环境是8080，也可以根据自己的需要进行配置，在开发环境中成功部署之后，浏览器打开<http://localhost:8001/swagger-ui.html>即可看到接口文档。

![image-20200701210619725](http://cdn.panzvor.com/img/20200701210621.png)