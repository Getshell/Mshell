# Mshell-攻防内存马研究

![Getshell](https://socialify.git.ci/Getshell/Mshell/image?description=1&font=Inter&forks=1&issues=1&language=1&name=1&owner=1&pattern=Circuit%20Board&stargazers=1&theme=Light)

不知从那天起，内存马悄悄成为了新的技术研究方向。一边习惯了技术的更迭而淡定自若，一边突然面对消失的技术而黯然伤神消沉。猛回头，发现突然消失的又何止是技术？本项目用来收集整理内存马相关的技术资料，包括内存马技术原理实现方法具体的项目等。内存马在Java领域独领风骚，因此我们将重点关注Java内存马。也用此项目致敬四大spy，致敬老兵，致敬消失的技术！作者：[0e0w](https://github.com/0e0w)

本项目创建于2023年5月1日，最近的一次更新时间为2023年12月25日。

- [01-内存马资源](https://github.com/Getshell/Mshell#01-%E5%86%85%E5%AD%98%E9%A9%AC%E8%B5%84%E6%BA%90)
- [02-内存马原理](https://github.com/Getshell/Mshell#02-%E5%86%85%E5%AD%98%E9%A9%AC%E5%8E%9F%E7%90%86)
- [03-内存马实战](https://github.com/Getshell/Mshell#03-%E5%86%85%E5%AD%98%E9%A9%AC%E5%AE%9E%E6%88%98)
- [04-内存马查杀](https://github.com/Getshell/Mshell#04-%E5%86%85%E5%AD%98%E9%A9%AC%E6%9F%A5%E6%9D%80)
- [05-内存马老师](https://github.com/Getshell/Mshell#05-%E5%86%85%E5%AD%98%E9%A9%AC%E8%80%81%E5%B8%88)

## 01-内存马资源

一、优秀文章
- [x] [内存马探究](https://www.anquanke.com/post/id/279160)@Tide安全团队
- [x] [Java内存马攻防实战——攻击基础篇](https://www.anquanke.com/post/id/273250)@长亭科技Eki
- [x] [主机安全技术剖析-手把手教会你防御Java内存马](https://www.anquanke.com/post/id/274443)@长亭科技Eki
- [x] [深入浅出内存马(一)](https://www.freebuf.com/articles/network/307970.html)@雷石安全实验室
- [x] [深入浅出内存马(二)](https://www.freebuf.com/articles/web/287989.html)@雷石安全实验室
- [ ] [一文看懂内存马](https://www.freebuf.com/articles/web/274466.html)@lex1993
- [ ] [Java安全学习——内存马](https://goodapple.top/archives/1355)@Claradoll
- [x] [手把手教你实现tomcat内存马](https://www.freebuf.com/articles/web/333625.html)@雷石安全实验室
- [ ] [内存马的攻防博弈之旅](http://blog.nsfocus.net/webshell-interceptor/)@绿盟陈建军
- [ ] [Tomcat内存马——Filter/servlet/Listener/valve](https://xz.aliyun.com/t/11988)@godown
- [ ] [Spring内存马——Controller/Interceptor构造](https://xz.aliyun.com/t/12047)@godown
- [ ] [JavaWeb 内存马一周目通关攻略](https://su18.org/post/memory-shell/)@su18
- [ ] [JavaWeb 内存马二周目通关攻略](https://su18.org/post/memory-shell-2/)@su18
- [ ] [JAVA内存马的“一生”](https://xz.aliyun.com/t/11003)@ga0weI
- [ ] [论如何优雅的注入Java Agent内存马](https://mp.weixin.qq.com/s/xxaOsJdRE5OoRkMLkIj3Lg)@rebeyond
- [ ] [Java内存攻击技术漫谈](https://xz.aliyun.com/t/10075)@rebeyond
- [ ] [一种新的Tomcat内存马 - Upgrade内存马](https://tttang.com/archive/1709/)@Sndav
- [ ] [Shell中的幽灵王者—JAVAWEB 内存马 【认知篇】](https://nosec.org/home/detail/5049.html)@su18
- [ ] [Goby 利用内存马中的一些技术细节【技术篇】](https://nosec.org/home/detail/5077.html)@su18
- [ ] [用 Goby 通过反序列化漏洞一键打入内存马【利用篇】](https://nosec.org/home/detail/5059.html)@su18
- [ ] [Tomcat 源代码调试笔记 - 看不见的 Shell](https://mp.weixin.qq.com/s/x4pxmeqC1DvRi9AdxZ-0Lw)@n1nty
- [ ] [Tomcat 源代码调试 - 看不见的 Shell 第二式增强之无痕](https://mp.weixin.qq.com/s/7b3Fyu_K6ZRgKlp6RkdYoA)@n1nty
- [ ] [Tomcat 源代码调试 - 看不见的 Shell 第二式之隐藏任意 Jsp 文件](https://mp.weixin.qq.com/s/1ZiLD396088TxiW_dUOFsQ)@n1nty
- [ ] [Java内存马系列-01-基础内容学习](https://drun1baby.top/2022/08/19/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-01-%E5%9F%BA%E7%A1%80%E5%86%85%E5%AE%B9%E5%AD%A6%E4%B9%A0/)@Drunkbaby
- [ ] [Java内存马系列-02-内存马介绍](https://drun1baby.top/2022/08/21/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-02-%E5%86%85%E5%AD%98%E9%A9%AC%E4%BB%8B%E7%BB%8D/)@Drunkbaby
- [ ] [Java内存马系列-03-Tomcat 之 Filter 型内存马](https://drun1baby.top/2022/08/22/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-03-Tomcat-%E4%B9%8B-Filter-%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC/)@Drunkbaby
- [ ] [Java内存马系列-04-Tomcat 之 Listener 型内存马](https://drun1baby.top/2022/08/27/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-04-Tomcat-%E4%B9%8B-Listener-%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC/)@Drunkbaby
- [ ] [Java内存马系列-05-Tomcat 之 Servlet 型内存马](https://drun1baby.top/2022/09/04/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-05-Tomcat-%E4%B9%8B-Servlet-%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC/)@Drunkbaby
- [ ] [Java内存马系列-06-Tomcat 之 Valve 型内存马](https://drun1baby.top/2022/09/07/Java%E5%86%85%E5%AD%98%E9%A9%AC%E7%B3%BB%E5%88%97-06-Tomcat-%E4%B9%8B-Valve-%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC/)@Drunkbaby
- [ ] [Java 内存马基础知识 —— Tomcat 架构学习](https://www.freebuf.com/articles/web/343094.html)@Drunkbaby
- [ ] [Java 内存马与 JSP 不得不说的那些事儿](https://www.freebuf.com/articles/web/343095.html)@Drunkbaby
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
- [ ] [步履维艰之Struts2内存马](https://xz.aliyun.com/t/12237)@f0ng
- [ ] [Resin内存马逆袭之路](https://xz.aliyun.com/t/11758)@Ha1ey
- [ ] [Executor内存马的实现](https://xz.aliyun.com/t/11593)@bluE0
- [ ] [Executor内存马的实现（二）](https://xz.aliyun.com/t/11613)@bluE0
- [ ] [java内存马分析集合](https://xz.aliyun.com/t/11084)@奈*七
- [ ] [浅析JSP型内存马](https://xz.aliyun.com/t/11020)@tyskill
- [ ] [Python 内存马分析](https://xz.aliyun.com/t/10933)@H3rmesk1t
- [ ] [RuoYi 可用内存马](https://xz.aliyun.com/t/10651)@lz2y
- [ ] [java filter马持久化](https://xz.aliyun.com/t/10582)@changeServer
- [ ] [利用Fastjson注入Spring内存马](https://xz.aliyun.com/t/10467)@洋洋
- [ ] [Tomcat 内存马（一）Listener型](https://xz.aliyun.com/t/10358)@洋洋
- [ ] [Tomcat 内存马（二）Filter型](https://xz.aliyun.com/t/10362)@洋洋
- [ ] [JSP内存马研究](https://xz.aliyun.com/t/10372)@藏青
- [ ] [JSP Webshell那些事——攻击篇（上）](https://www.anquanke.com/post/id/214435)@阿里云云安全中心
- [ ] [JSP Webshell那些事——攻击篇（下）](https://www.anquanke.com/post/id/214483)@阿里云云安全中心
- [ ] [基于Tomcat无文件Webshell研究](https://mp.weixin.qq.com/s?__biz=MzI0NzEwOTM0MA==&mid=2652474966&idx=1&sn=1c75686865f7348a6b528b42789aeec8&scene=21#wechat_redirect)@l1nk3r
- [ ] [java内存马攻击篇](https://dem0dem0.top/2022/07/18/java%E5%86%85%E5%AD%98%E9%A9%AC%E6%94%BB%E5%87%BB%E7%AF%87/)@dem0
- [ ] [一文简析内存马攻击防护解决方案](https://www.anquanke.com/post/id/236200)@安芯网盾
- [ ] [EXE文件内存加载](https://www.anquanke.com/post/id/260054)@D4ck
- [ ] [Tomcat 内存马检测](https://www.anquanke.com/post/id/219177)@jweny
- [ ] [Spring内存木马检测思路](https://www.anquanke.com/post/id/239868)@安全狗
- [ ] [一枚野生resin filter内存马调试](https://www.anquanke.com/post/id/239866)@superxx
- [ ] [Tomcat内存马之Valve和WebSocket型](https://www.freebuf.com/articles/web/365822.html)@Lemono
- [ ] [自己动手写Filter型内存马](https://www.freebuf.com/articles/web/365501.html)@s8ark
- [ ] [基于ysoserial的深度利用研究（命令回显与内存马）](https://www.freebuf.com/articles/network/361296.html)@盛邦安全WebRAY
- [ ] [对tomcat-servlet内存马的学习路](https://www.freebuf.com/vuls/356297.html)@superLeeH
- [ ] [结合CC链注入无文件Tomcat内存马](https://www.freebuf.com/articles/web/354385.html)@godownio
- [ ] [Yso-Java Hack 进阶：利用反序列化漏洞打内存马](https://www.freebuf.com/articles/network/353911.html)@yaklang
- [ ] [Spring Boot RCE到内存马探索](https://www.freebuf.com/articles/system/349374.html)@SecIN技术社区
- [ ] [Resin内存马逆袭之路](https://www.freebuf.com/articles/web/346946.html)@Ha1ey
- [ ] [MSMAP：一款功能强大的内存WebShell生成工具](https://www.freebuf.com/articles/system/351107.html)@Alpha_h4ck
- [ ] [如何从内存加载DLL](https://www.freebuf.com/articles/system/227996.html)@搬运工007
- [ ] [JavaAgent技术在内存马中的应用](https://www.freebuf.com/articles/web/323737.html)@安全狗
- [ ] [Java Agent到内存马](https://www.freebuf.com/articles/web/310224.html)@蚁景科技
- [ ] [蓝军反治系列之打造weblogic的持久化内存马后门](https://www.freebuf.com/articles/web/268728.html)@宽字节安全实验室
- [ ] [擅长捉弄的内存马同学：Valve内存马](https://www.freebuf.com/articles/web/348663.html)@potatosafe
- [ ] [擅长捉弄的内存马同学：Agent内存马（低卡）](https://www.freebuf.com/articles/web/323621.html)@potatosafe
- [ ] [擅长捉弄的内存马同学：Servlet内存马](https://www.freebuf.com/articles/web/322580.html)@potatosafe
- [ ] [擅长捉弄的内存马同学：Listener内存马](https://www.freebuf.com/articles/web/322309.html)@potatosafe
- [ ] [擅长捉弄的内存马同学：Filter内存马（高甜）](https://www.freebuf.com/articles/web/321975.html)@potatosafe
- [ ] [手搓Filter内存马从构造到利用讲解(内存马系列篇一)](https://www.freebuf.com/vuls/344284.html)@RoboTerh
- [ ] [完全摸透Servlet内存马(内存马系列篇二)](https://www.freebuf.com/vuls/344296.html)@RoboTerh
- [ ] [深入底层源码的Listener内存马(内存马系列篇三)](https://www.freebuf.com/vuls/344297.html)@RoboTerh
- [ ] [Tomcat架构之为Bypass内存马检测铺路(内存马系列篇四)](https://www.freebuf.com/vuls/344793.html)@RoboTerh
- [ ] [绕过检测之Executor内存马浅析(内存马系列篇五)](https://www.freebuf.com/vuls/344812.html)@RoboTerh
- [ ] [初探Upgrade内存马(内存马系列篇六)](https://www.freebuf.com/vuls/345119.html)@RoboTerh
- [ ] [WebSocket内存马之tomcat-websocket源码实现(内存马系列篇七)](https://www.freebuf.com/vuls/345739.html)@RoboTerh
- [ ] [再探WebSocket内存马(内存马系列篇八)](https://www.freebuf.com/vuls/346129.html)@RoboTerh
- [ ] [初探Spring内存马之Controller(内存马系列篇九)](https://www.freebuf.com/vuls/346315.html)@RoboTerh
- [ ] [再谈Spring内存马之Interceptor(内存马系列篇十)](https://www.freebuf.com/vuls/346644.html)@RoboTerh
- [ ] [浅析Tomcat架构上的Valve内存马(内存马系列篇十一)](https://www.freebuf.com/vuls/346943.html)@RoboTerh
- [ ] [谈谈java agent技术的实现(内存马系列篇十二)](https://www.freebuf.com/vuls/347070.html)@RoboTerh
- [ ] [构造agent类型的内存马(内存马系列篇十三)](https://www.freebuf.com/vuls/348082.html)@RoboTerh
- [ ] [spring回显方式在代码层面的复现(内存马系列篇十四)](https://www.freebuf.com/articles/web/348804.html)@RoboTerh
- [ ] [寻找全局Request进行Tomcat内存马注入(内存马系列篇十五)](https://www.freebuf.com/vuls/350482.html)@RoboTerh
- [ ] [对上篇注入内存马方式的缩短改造(内存马篇十六)](https://www.freebuf.com/vuls/350638.html)@RoboTerh
- [ ] [WebSphere内存马分析](https://xz.aliyun.com/t/12278)@饼干屑小鬼
- [ ] [GlassFish-Filter内存马分析](https://xz.aliyun.com/t/12240)@饼干屑小鬼
- [ ] [Jetty 内存马注入分析](https://xz.aliyun.com/t/12182)@饼干屑小鬼
- [ ] [Wildfly中间件内存马分析](https://xz.aliyun.com/t/12161)@饼干屑小鬼
- [ ] [Resin内存马分析](https://xz.aliyun.com/t/12147)@饼干屑小鬼
- [ ] [TemplatesImpl利用链与Fastjson注入内存马](https://xz.aliyun.com/t/12085)@ajie
- [ ] [反序列化分析到shiro注入WebSocket内存马](https://xz.aliyun.com/t/12042)@ajie
- [ ] [利用 PHP-FPM 做内存马的方法](https://xz.aliyun.com/t/11651)@wofeiwo
- [ ] [java Filter内存马分析](https://xz.aliyun.com/t/10888)@奈*七
- [ ] [Spring Interceptor 内存马分析](https://xz.aliyun.com/t/11039)@yecp
- [ ] [Java内存马：一种Tomcat全版本获取StandardContext的新方法](https://xz.aliyun.com/t/9914)@bitterz
- [ ] [Resin回显及内存马](https://xz.aliyun.com/t/9639)@九五二七
- [ ] [ASP.NET下的内存马(1) filter内存马](https://tttang.com/archive/1408/)@yzddmr6
- [ ] [ASP.NET下的内存马(2) Route内存马](https://tttang.com/archive/1420/)@yzddmr6
- [ ] [ASP.NET下的内存马(3) HttpListener内存马](https://tttang.com/archive/1451/)@yzddmr6
- [ ] [ASP.NET下的内存马(4) VirtualPath内存马](https://tttang.com/archive/1488/)@yzddmr6
- [ ] [NoAgent内存马检测工具](https://tttang.com/archive/1390/)@xyyl1l
- [ ] [瞒天过海计之Tomcat隐藏内存马](https://tttang.com/archive/1368/)@wh4am1
- [ ] [从一个被Tomcat拒绝的漏洞到特殊内存马](https://developer.aliyun.com/article/1160290)@4ra1n
- [ ] [jMG - 高度自定义的 Java 内存马生成工具](https://mp.weixin.qq.com/s/oAiGWY9ABhn2o148snA_sg)@pen4uin
- [ ] [jMG v1.0.5介绍](https://mp.weixin.qq.com/s/QjoRs_J5jVANrdEiiTtVtA)@pen4uin
- [ ] [学习JAVA内存SHELL](https://www.x1a0t.com/2020/08/04/Study-Java-Memory-Shell/)@x1a0t
- [ ] [Spring Memory Shell](https://jlkl.github.io/2022/05/26/Java-09/)@jlkl
- [ ] [Spring Memory Shell](https://ch1e.cn/post/spring-memory-shell/)@ch1e
- [ ] [ControllerAdviceBean Memory Shell](https://ch1e.cn/post/controlleradvicebean-memory-shell/)@ch1e
- [ ] [Executor Memory Shell](https://ch1e.cn/post/executor-memory-shell/)@ch1e
- [ ] [Servlet Memory Shell](https://ch1e.cn/post/servlet-memory-shell/)@ch1e
- [ ] [Java内存马之Filter内存马](https://ch1e.cn/post/java-nei-cun-ma-zhi-filter-nei-cun-ma/)@ch1e
- [ ] [Executor Memory Shell](https://cjlusec.ldxk.edu.cn/2023/02/15/Executor/)@cjlusec
- [ ] [Agent内存马的自动分析与查杀](https://www.cnblogs.com/slll/p/15937034.html)@白鹭鹭鹭
- [ ] [基于内存 Webshell 的无文件攻击技术研究](https://www.anquanke.com/post/id/198886)@LandGrey
- [ ] [利用 intercetor 注入 spring 内存 webshell](https://landgrey.me/blog/19/)@LandGrey
- [ ] [内存马学习专区](https://github.com/Y4tacker/JavaSec#5%E5%86%85%E5%AD%98%E9%A9%AC%E5%AD%A6%E4%B9%A0%E4%B8%93%E5%8C%BA)@Y4tacker
- [ ] [Tomcat Servlet-Api内存马总结及代码实现](https://mp.weixin.qq.com/s/r-JHLX5UoccGNZCfJS3klg)@automne
- [ ] [护网专题第一篇-Java内存马（上）](https://mp.weixin.qq.com/s/YVwqD6SwUq_jkEe_9afBCg)@零鉴科技
- [ ] [护网专题第二篇-Java内存马（下）](https://mp.weixin.qq.com/s/gmKSmW5SIME8lWKj8bvhWw)@零鉴科技
- [ ] [Linux下无文件Java agent探究](https://tttang.com/archive/1525/)@Xiaopan233
- [ ] [浅谈 Java Agent 内存马](http://wjlshare.com/archives/1582)@天下大木头
- [ ] [Java Agent 内存马攻防](https://www.freebuf.com/articles/web/331954.html)@iO快到碗里来
- [ ] [Java Agent 内存马](https://exp10it.cn/2023/01/java-agent-%E5%86%85%E5%AD%98%E9%A9%AC/)@X1r0zi
- [ ] [JavaAgent内存马研究](https://cangqingzhe.github.io/2021/10/13/JavaAgent%E5%86%85%E5%AD%98%E9%A9%AC%E7%A0%94%E7%A9%B6/)@藏青
- [ ] [Java Agent内存马学习](http://www.bmth666.cn/bmth_blog/2022/11/16/Java-Agent%E5%86%85%E5%AD%98%E9%A9%AC%E5%AD%A6%E4%B9%A0/)@bmth
- [ ] [Java Agent到内存马(一)](https://mp.weixin.qq.com/s?__biz=Mzg3ODg1MDUxNw==&mid=2247483687&idx=1&sn=4ce0094b03fe23bd3577c902754b99a1&chksm=cf0c399df87bb08b4a060ec91adbacd1e477fe56df200a2790fb741976abdbf078246fc6a828&scene=126&sessionid=1686886912#rd)@N0r4h
- [ ] [Java Agent到内存马(二)](https://mp.weixin.qq.com/s?__biz=Mzg3ODg1MDUxNw==&mid=2247483706&idx=1&sn=9440b7aaf5ee945bd79cba9e6d3efe35&chksm=cf0c3980f87bb0963ad207c5cfaea2caf13ab9130b37f2b7e5fead149f72120309a1d47a9a31&scene=126&sessionid=1686886912#rd)@N0r4h
- [ ] [Java Agent到内存马](https://mp.weixin.qq.com/s/4flGwH-LizMx17PK0PQEzA)@许木
- [ ] [Java Agent内存马演变历史](https://www.ctfiot.com/58172.html)@ctfiot
- [ ] [Java Agent 内存马简介](https://passbya.xyz/2023/01/29/Java%20Agent%20%E5%86%85%E5%AD%98%E9%A9%AC%E7%AE%80%E4%BB%8B/)@PassbyA
- [ ] [Tomcat Agent 型内存马](https://paoka1.top/2023/04/24/Tomcat-Agent-%E5%9E%8B%E5%86%85%E5%AD%98%E9%A9%AC/)@paoka1
- [ ] [Java Agent 内存马](https://www.viewofthai.link/2023/02/23/java-agent-内存马/)@viewofthai
- [ ] [Java Agent内存马--从入门到踩坑](https://blog.z3ratu1.cn/Java%20Agent%E7%AE%80%E6%98%93%E5%85%A5%E9%97%A8.html)@z3ratu1
- [ ] [Java Agent内存马实现与检测](https://lemono.fun/JavaAgent-MemShell/)@lemono
- [ ] [Java Agent内存马研究学习](https://blog.jd.army/2022/09/21/Java-Agent%E5%86%85%E5%AD%98%E9%A9%AC%E7%A0%94%E7%A9%B6%E5%AD%A6%E4%B9%A0/)@JD.Army
- [ ] [ServletListenerFilter内存马查杀手段一](https://www.freebuf.com/articles/web/369799.html)@RoboTerh
- [ ] [Servlet内存马利用分析](https://xz.aliyun.com/t/12629)@Sentiment
- [ ] [基于全局储存的新思路 | Tomcat的一种通用回显方法研究](https://mp.weixin.qq.com/s?__biz=MzIwNDA2NDk5OQ==&mid=2651374294&idx=3&sn=82d050ca7268bdb7bcf7ff7ff293d7b3)@Litch1
- [ ] [Msmap内存马生成框架（一）](https://hosch3n.github.io/2022/08/08/Msmap%E5%86%85%E5%AD%98%E9%A9%AC%E7%94%9F%E6%88%90%E6%A1%86%E6%9E%B6%EF%BC%88%E4%B8%80%EF%BC%89/)@hosch3n
- [ ] [Msmap内存马生成框架（二）](https://hosch3n.github.io/2022/08/09/Msmap%E5%86%85%E5%AD%98%E9%A9%AC%E7%94%9F%E6%88%90%E6%A1%86%E6%9E%B6%EF%BC%88%E4%BA%8C%EF%BC%89/)@hosch3n
- [ ] [Msmap内存马生成框架（三）](https://hosch3n.github.io/2022/10/29/Msmap%E5%86%85%E5%AD%98%E9%A9%AC%E7%94%9F%E6%88%90%E6%A1%86%E6%9E%B6%EF%BC%88%E4%B8%89%EF%BC%89/)@hosch3n
- [ ] [Java安全-记一次实战使用memoryshell](https://forum.butian.net/share/2007)@7bits
- [ ] [Executor内存马的实现](https://mp.weixin.qq.com/s/uHxQf86zHJvg9frTbjdIdA)@深蓝
- [ ] [第20篇：改造冰蝎客户端适配JNDIExploit的内存马](https://mp.weixin.qq.com/s/qE_AArdpAVL-EoBbILb_Jg)@abc123
- [ ] [深入浅出内存马](https://xz.aliyun.com/t/12705)@小*见
- [ ] [WebsocketAndTimer内存马的查杀分析和代码实现 ](https://www.freebuf.com/articles/web/372192.html)@RoboTerh

二、开源项目
- [x] https://github.com/topics/memshell
- [x] https://github.com/search?q=memshell
- [x] https://github.com/pen4uin/java-memshell-generator
- [x] https://github.com/hosch3n/msmap
- [x] https://github.com/0x00007c00/JundeadShell
- [x] https://github.com/threedr3am/ZhouYu
- [x] https://github.com/feihong-cs/memShell
- [x] https://github.com/jweny/MemShellDemo
- [x] https://github.com/achuna33/Memoryshell-JavaALL
- [x] https://github.com/achuna33/FuckMemshell
- [x] https://github.com/BeichenDream/GodzillaMemoryShellProject
- [x] https://github.com/ethushiroha/JavaAgentTools
- [x] https://github.com/rebeyond/memShell
- [x] https://github.com/ax1sX/MemShell
- [x] https://github.com/0x727/DropLabTools
- [x] https://github.com/su18/MemoryShell
- [x] https://github.com/safe6Sec/MemoryShell
- [x] https://github.com/NikolaGareth/MemoryShell
- [x] https://github.com/7BitsTeam/LearningAgentShell
- [x] https://github.com/gobysec/Memory-Shell
- [x] https://github.com/XhstormR/memshell-serial
- [x] https://github.com/minhangxiaohui/JAVA_memshells
- [x] https://github.com/kuron3k0/java_memshell
- [x] https://github.com/changheluor007/MemShell-1
- [x] https://github.com/bmth666/memshell
- [ ] https://github.com/Octoberfest7/MemFiles
- [ ] https://github.com/lz2y/yaml-payload-for-ruoyi
- [ ] https://github.com/retry-later/MemoryShell_java
- [ ] https://github.com/AzRunRCE/MemoryShellCodeExploit
- [ ] https://github.com/kyo-w/router-router
- [x] https://github.com/INT2ECALL/Awesome-JavaMemoryShell
- [x] https://github.com/cri1wa/MemShell
- [ ] https://github.com/c0ny1/java-object-searcher
- [ ] https://github.com/pwntester/ysoserial.net
- [ ] https://github.com/veo/vagent
- [ ] https://github.com/rzte/agentcrack
- [ ] https://github.com/veo/ebpf_shell

三、学术论文
- [ ] [面向Java 的高对抗内存型Webshell 检测技术](http://jcs.iie.ac.cn/xxaqxb/ch/reader/create_pdf.aspx?file_no=20220604&year_id=2022&quarter_id=6&falg=1)@张金莉 陈星辰等
- [ ] [一种针对Tomcat Filter型的MemShell检测技术研究](http://jcs.iie.ac.cn/xxaqxb/ch/reader/view_abstract.aspx?file_no=202112210000002)@蔡国宝 张昆等

四、其他项目

## 02-内存马原理

- [ ] https://0e0w.com/Mshell #文章编写中，待公开

## 03-内存马实战

一、Tomcat
- [x] https://github.com/Getshell/TomShell
- [x] https://github.com/ce-automne/TomcatMemShell
- [ ] https://github.com/K4ys0n/TomcatMemShellDemo
- [ ] https://github.com/bitterzzZZ/MemoryShellLearn
- [x] https://github.com/birdhan/Memory

二、Weblogic
- [x] https://github.com/Getshell/WeblogicShell
- [ ] https://github.com/keven1z/weblogic_memshell
- [ ] https://github.com/Y4er/WebLogic-Shiro-shell

三、Spring
- [x] https://github.com/Getshell/SpringShell
- [ ] https://github.com/passer-W/snakeyaml-memshell
- [ ] https://github.com/mieeA/SpringWebflux-MemShell
- [ ] https://github.com/viemsr/spring_cloud_gateway_memshell

四、Shiro
- [ ] https://github.com/KpLi0rn/ShiroVulnEnv
- [ ] https://github.com/yyhuni/shiroMemshell

五、Jboss

六、WebSphere

七、Python
- [ ] https://github.com/iceyhexman/flask_memory_shell

八、.NET
- [ ] https://github.com/crisprss/net_memory_webshell
- [ ] https://github.com/BeichenDream/GodzillaMemoryShellProject.NET

九、Struts2

十、WebSocket
- [x] https://github.com/veo/wsMemShell
- [ ] https://www.freebuf.com/articles/web/339702.html
- [ ] https://paper.seebug.org/1935
- [ ] [Tomcat WebSocket内存马原理浅析](https://tttang.com/archive/1673/)@wh1sper
- [ ] https://www.cnblogs.com/duanxz/p/5041110.html
- [ ] [WebSocket通信原理和在Tomcat中实现源码详解](https://blog.csdn.net/weixin_36586120/article/details/120025498)@徐同学呀
- [ ] [websocket新型内存马的应急响应](https://mp.weixin.qq.com/s/T3UfA1plrlG-e9lgfB4whg)@flamingo

十一、gRPC
- [ ] [内存马的攻防博弈之旅之GRPC内存马](http://blog.nsfocus.net/grpc/)@绿盟陈建军
- [ ] https://github.com/snailll/gRPCDemo
- [ ] [gRPC内存马研究与查杀](https://xz.aliyun.com/t/11985)@0goid

九九、待整理
- [ ] [Shiro反序列化注入内存马](https://ch1e.cn/post/shiro-fan-xu-lie-hua-zhu-ru-nei-cun-ma/)@ch1e
- [ ] [Fastjson反序列化注入内存马](https://ch1e.cn/post/fastjson-fan-xu-lie-hua-zhu-ru-nei-cun-ma/)@ch1e
- [ ] https://github.com/whwlsfb/cve-2022-22947-godzilla-memshell
- [ ] https://github.com/A-D-Team/SharpMemshell
- [ ] https://github.com/threedr3am/JSP-WebShells

## 04-内存马查杀

- [ ] [给木马带双眼睛](https://xz.aliyun.com/t/11655)@KyoDream
- [ ] [查杀Java web filter型内存马](https://gv7.me/articles/2020/kill-java-web-filter-memshell/)@c0ny1
- [ ] https://github.com/geekmc/FindShell
- [ ] https://github.com/4ra1n/shell-analyzer
- [ ] https://github.com/c0ny1/java-memshell-scanner
- [ ] https://github.com/LandGrey/copagent
- [ ] https://github.com/alibaba/arthas
- [ ] https://github.com/sf197/MemoryShellHunter
- [ ] https://github.com/cri1wa/DefendMemoryShell
- [ ] https://github.com/tovd-go/java-memshell-scan
- [ ] https://github.com/huoji120/DuckMemoryScan
- [ ] https://github.com/threedr3am/GuanYu
- [ ] https://mp.weixin.qq.com/s/y6qEtfhdA8Udmvxuh7H12Q

## 05-内存马参考

- [ ] https://github.com/pen4uin
- [ ] https://github.com/threedr3am
- [ ] https://github.com/Getshell/Webshell
- [ ] https://github.com/HackJava/HackJava
- [ ] https://github.com/HackJava/JNDI
- [ ] https://github.com/HackJava/jspshell
- [ ] https://github.com/RoboTerh

[![Stargazers over time](https://starchart.cc//Getshell/Mshell.svg)](https://starchart.cc/Getshell/Mshell)

![](01-内存马资源/wx.png)