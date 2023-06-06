# Mshell-攻防内存马研究

不知从那天起，内存马悄悄成为了新的技术研究方向。一边习惯了技术的更迭而淡定自若，一边突然面对消失的技术而黯然伤神消沉。猛回头，发现突然消失的又何止是技术？本项目用来收集整理内存马相关的技术资料，包括内存马技术原理实现方法具体的项目等。致敬曾经的四大spy，致敬中国菜刀！

本项目创建于2023年5月1日，最近的一次更新时间为2023年6月6日。作者：[0e0w]()

- [01-内存马资源]()
- [02-内存马原理]()
- [03-内存马分类]()
- [04-内存马工具]()
- [05-内存马查杀]()
- [06-内存马老师]()

## 01-内存马资源

一、优秀文章
- [ ] [Java内存马攻防实战——攻击基础篇](https://www.anquanke.com/post/id/273250)@长亭科技
- [ ] [手把手教你实现tomcat内存马](https://www.freebuf.com/articles/web/333625.html)@雷石安全实验室
- [ ] [内存马的攻防博弈之旅](http://blog.nsfocus.net/webshell-interceptor/)@绿盟陈建军
- [ ] [内存马的攻防博弈之旅之GRPC内存马](http://blog.nsfocus.net/grpc/)@绿盟陈建军
- [ ] [Tomcat内存马——Filter/servlet/Listener/valve](https://xz.aliyun.com/t/11988)@godown
- [ ] [内存马探究](https://www.anquanke.com/post/id/279160)@Tide安全团队
- [ ] [一文看懂内存马](https://www.freebuf.com/articles/web/274466.html)@lex1993
- [ ] [JavaWeb 内存马一周目通关攻略](https://su18.org/post/memory-shell/)@su18
- [ ] [JavaWeb 内存马二周目通关攻略](https://su18.org/post/memory-shell-2/)@su18
- [ ] [Java安全学习——内存马](https://goodapple.top/archives/1355)@Claradoll
- [ ] [JAVA内存马的“一生”](https://xz.aliyun.com/t/11003)@ga0weI
- [ ] [论如何优雅的注入Java Agent内存马](https://mp.weixin.qq.com/s/xxaOsJdRE5OoRkMLkIj3Lg)@rebeyond
- [ ] [Java内存攻击技术漫谈](https://xz.aliyun.com/t/10075)@rebeyond
- [ ] [内存马学习专区](https://github.com/Y4tacker/JavaSec#5%E5%86%85%E5%AD%98%E9%A9%AC%E5%AD%A6%E4%B9%A0%E4%B8%93%E5%8C%BA)@Y4tacker
- [ ] [一种新的Tomcat内存马 - Upgrade内存马](https://tttang.com/archive/1709/)@Sndav
- [ ] [Shell中的幽灵王者—JAVAWEB 内存马 【认知篇】](https://nosec.org/home/detail/5049.html)@su18
- [ ] [Goby 利用内存马中的一些技术细节【技术篇】](https://nosec.org/home/detail/5077.html)@su18
- [ ] [用 Goby 通过反序列化漏洞一键打入内存马【利用篇】](https://nosec.org/home/detail/5059.html)@su18
- [ ] [Tomcat 源代码调试笔记 - 看不见的 Shell](https://mp.weixin.qq.com/s/x4pxmeqC1DvRi9AdxZ-0Lw)@n1nty
- [ ] [Java内存马攻防实战--攻击基础篇](https://mp.weixin.qq.com/s/HODFJF3NJmsDW2Lcd-ebIg)@长亭Eki
- [ ] [Java内存马系列-01-基础内容学习](https://drun1baby.top/2022/08/19/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-01-%E5%9F%BA%E7%A1%80%E5%86%85%E5%AE%B9%E5%AD%A6%E4%B9%A0/)@Drunkbaby
- [ ] [Java内存马系列-02-内存马介绍](https://drun1baby.top/2022/08/21/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-02-%E5%86%85%E5%AD%98%E9%A9%AC%E4%BB%8B%E7%BB%8D/)@Drunkbaby
- [ ] [Java内存马系列-03-Tomcat 之 Filter 型内存马](https://drun1baby.top/2022/08/22/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-03-Tomcat-%E4%B9%8B-Filter-%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC/)@Drunkbaby
- [ ] [Java内存马系列-04-Tomcat 之 Listener 型内存马](https://drun1baby.top/2022/08/27/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-04-Tomcat-%E4%B9%8B-Listener-%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC/)@Drunkbaby
- [ ] [Java内存马系列-05-Tomcat 之 Servlet 型内存马](https://drun1baby.top/2022/09/04/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-05-Tomcat-%E4%B9%8B-Servlet-%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC/)@Drunkbaby
- [ ] [Java内存马系列-06-Tomcat 之 Valve 型内存马](https://drun1baby.top/2022/09/07/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-06-Tomcat-%E4%B9%8B-Valve-%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC/)@Drunkbaby
- [ ] [内存马的攻防博弈实操](https://www.cnblogs.com/renhaoblog/p/16851585.html)@renhao
- [ ] [Java利用技巧——Jetty Filter型内存马](https://3gstudent.github.io/Java%E5%88%A9%E7%94%A8%E6%8A%80%E5%B7%A7-Jetty-Filter%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC)@3gstudent
- [ ] [一种全新的内存马](https://veo.pub/2022/memshell/)@veo
- [ ] [如何在攻防演练中针对内存马做好最后的安全防线？](https://www.aqniu.com/vendor/72810.html)@安芯网盾
- [ ] [安芯网盾首发内存马攻击防护解决方案](https://anxinsec.com/view/about-us/news/0115.html)@安芯网盾
- [ ] [Listener内存马注入分析](https://sec-in.com/article/2020)@Sentiment
- [ ] [某知名Java框架内存马挖掘](https://forum.butian.net/share/923)@4ra1n
- [ ] [Filter/Servlet型内存马的扫描抓捕与查杀](https://gv7.me/articles/2020/filter-servlet-type-memshell-scan-capture-and-kill/)@c0ny1
- [ ] [Webshell 内存马分析](https://www.geekby.site/2021/09/webshell%E5%86%85%E5%AD%98%E9%A9%AC%E5%88%86%E6%9E%90/)@Geekby
- [ ] [防守视角tomcat内存马的查杀](https://qiita.com/shimizukawasaki/items/5dc9fe780ffbf3a7699c)@清水川崎
- [ ] [从零开始的内存马分析——如何骑马反杀(一)](https://forum.butian.net/share/1811)@Wumingzhilian
- [ ] [从零开始的内存马分析——如何骑马反杀(二)](https://forum.butian.net/share/1814)@Wumingzhilian
- [ ] [从零开始的内存马分析——如何骑马反杀(三)](https://forum.butian.net/share/1853)@Wumingzhilian
- [ ] [基于tomcat的内存 Webshell 无文件攻击技术](https://xz.aliyun.com/t/7388)@threedr3am
- [ ] [浅析Spring类内存马](https://xz.aliyun.com/t/12538)@Sentiment
- [ ] [Shiro注入回显内存马](https://xz.aliyun.com/t/12537)@Sentiment
- [ ] [Tomcat反序列化注入回显内存马](https://xz.aliyun.com/t/12494)@Sentiment
- [ ] [GlassFish-Filter内存马分析](https://xz.aliyun.com/t/12240)@饼干屑小鬼
- [ ] [步履维艰之Struts2内存马](https://xz.aliyun.com/t/12237)@f0ng
- [ ] [Jetty 内存马注入分析](https://xz.aliyun.com/t/12182)@饼干屑小鬼
- [ ] [Wildfly中间件内存马分析](https://xz.aliyun.com/t/12161)@饼干屑小鬼
- [ ] [Resin内存马分析](https://xz.aliyun.com/t/12147)@饼干屑小鬼
- [ ] [Resin内存马逆袭之路](https://xz.aliyun.com/t/11758)@Ha1ey
- [ ] [论如何优雅的注入Java Agent内存马](https://xz.aliyun.com/t/11640)@rebeyond
- [ ] [Executor内存马的实现](https://xz.aliyun.com/t/11593)@bluE0
- [ ] [Executor内存马的实现（二）](https://xz.aliyun.com/t/11613)@bluE0
- [ ] [java内存马分析集合](https://xz.aliyun.com/t/11084)@奈*七
- [ ] [浅析JSP型内存马](https://xz.aliyun.com/t/11020)@tyskill
- [ ] [JAVA内存马的“一生”](https://xz.aliyun.com/t/11003)@ga0weI
- [ ] [Python 内存马分析](https://xz.aliyun.com/t/10933)@H3rmesk1t
- [ ] [RuoYi 可用内存马](https://xz.aliyun.com/t/10651)@lz2y
- [ ] [java filter马持久化](https://xz.aliyun.com/t/10582)@changeServer
- [ ] [利用Fastjson注入Spring内存马](https://xz.aliyun.com/t/10467)@洋洋
- [ ] [Tomcat 内存马（一）Listener型](https://xz.aliyun.com/t/10358)@洋洋
- [ ] [Tomcat 内存马（二）Filter型](https://xz.aliyun.com/t/10362)@洋洋
- [ ] [JSP内存马研究](https://xz.aliyun.com/t/10372)@藏青

二、优秀项目

- https://github.com/topics/memshell
- https://github.com/search?q=memshell

- [ ] https://github.com/jweny/MemShellDemo
- [ ] https://github.com/ce-automne/TomcatMemShell
- [ ] https://github.com/achuna33/Memoryshell-JavaALL
- [ ] https://github.com/iceyhexman/flask_memory_shell
- [ ] https://github.com/passer-W/snakeyaml-memshell
- [ ] https://github.com/crisprss/net_memory_webshell
- [ ] https://github.com/achuna33/FuckMemshell
- [ ] https://github.com/BeichenDream/GodzillaMemoryShellProject
- [ ] https://github.com/BeichenDream/GodzillaMemoryShellProject.NET
- [ ] https://github.com/feihong-cs/memShell
- [ ] https://github.com/hosch3n/msmap
- [ ] https://github.com/bmth666/memshell
- [ ] https://github.com/Octoberfest7/MemFiles
- [ ] https://github.com/ethushiroha/JavaAgentTools
- [ ] https://mp.weixin.qq.com/s/oAiGWY9ABhn2o148snA_sg
- [ ] https://github.com/mieeA/SpringWebflux-MemShell
- [ ] https://github.com/jweny/MemShellDemo
- [ ] https://github.com/0x00007c00/JundeadShell
- [ ] https://github.com/rebeyond/memShell
- [ ] https://github.com/ax1sX/MemShell
- [ ] https://github.com/lz2y/yaml-payload-for-ruoyi

## 02-内存马原理

一、内存马概念

二、内存马实现

## 03-内存马分类

一、实现分类

- Servlet
- Filter
- Listener

二、组件分类

- Tomcat内存马
- Weblogic内存马

三、协议分类

- WebSocket

九、待分类

- https://github.com/veo/wsMemShell  

## 04-内存马工具

- https://github.com/pen4uin/jMemshellGenerator
- https://github.com/whwlsfb/cve-2022-22947-godzilla-memshell
- https://github.com/A-D-Team/SharpMemshell
- https://github.com/keven1z/weblogic_memshell

## 05-内存马查杀

- [gRPC内存马研究与查杀](https://xz.aliyun.com/t/11985)@0goid
- [给木马带双眼睛](https://xz.aliyun.com/t/11655)@KyoDream
- https://github.com/4ra1n/FindShell
- https://github.com/4ra1n/shell-analyzer
- https://github.com/c0ny1/java-memshell-scanner

## 06-内存马老师
