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
*.添加shiro权限控制
*.quartz监控功能完善
*.(待定)探索quartz的远程监控功能
*.探索工作流框架
*.cache层使用redis数据库
*.用bootstrap替换easyui

了解/学习:
Hadoop
MongoDB

错误:
1.在某些IE版本下菜单无法打开(10.160.3.77)
