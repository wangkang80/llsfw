注意点: 
*.开发环境数据库链接方式默认为jndi,请注意配置.
*.maven install时不会拷贝src/main/java下的xml文件,在POM配置后可解决

llsfw的包结构详解:
	src/main/java
		com.llsfw.core
			aspectj[切面]
			cache[缓存]
			common[工具]
			controller[控制器]
				XXXXX[分割模块]
			exception[全局异常]
			interceptors[拦截器]
			listener[监听器]
			mapper[dao接口]
				daoExpand[扩展]
				daoStandard[标准]
			model[模型]
				expand[扩展]
				standard[标准]
			scheduler[计划任务]
				XXXXX[分割模块]
			security[安全]
			service[业务逻辑服务]
				XXXXX[分割模块]
			sqlMap[sqlMap配置文件]
				sqlMapExpand[扩展]
				sqlMapStandard[标准]
	src/main/resources
		llsfw[私有配置文件,不可更改]
			generatorConfig[mybatis代码生成]
			quartz[计划任务配置]
			spring[spring配置]
			springmvc[springmvc配置]
		logback.xml[公有配置文件,日志]
		resources.properties[公有配置文件,系统属性]
		spring-scheduler.xml[公有配置文件,计划任务]
		spring-systemParam.xml[公有配置文件,系统参数]

待实现:
*.完善系统功能
*.quartz监控功能完善
*.探索工作流框架
*.添加移动,联通,电信短信发送和接受的支持
*.添加OAuth2协议支持
*.添加微信支持

已实现:
*.基于spring,spring mvc,ibatis,quartz等主流框架搭建的平台
*.功能权限管理模块
*.quartz计划任务监控和动态管理功能
*.细颗粒的权限管控,控制到按钮
*.分布式session,使用redis存储和缓存session(可切换为内存session)

了解/学习:
Hadoop
MongoDB

错误:
1.在某些IE版本下菜单无法打开(10.160.3.77)
