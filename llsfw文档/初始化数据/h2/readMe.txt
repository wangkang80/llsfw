注意:
框架中的pdm是针对于oracle 11G 做的脚本
在h2中执行会有类型不匹配的问题,请在执行脚本之前做如下处理:
	*.所有的date类型转换为datetime类型
	也可以不修改脚本,脚本执行完毕后,执行
	SELECT 'alter table '||TABLE_NAME||' alter column '||COLUMN_NAME||' datetime;' FROM INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_CATALOG ='BFTC' AND TYPE_NAME='DATE'
	将结果拿出来执行一次,批量的更换数据类型