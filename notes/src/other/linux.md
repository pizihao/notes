# Linux

## 一，常用命令

　　显示目录文件命令：ls (list) ls -lh /tmp/ 这样可以输出tmp文件下所有文件
　　切换目录命令：cd，注意要写完整路径
　　创建目录命令：mkdir (make directories) mkdir -p /tmp/a/b递归创建目录
　　显示当前目录命令：pwd（print working directory）
　　删除空目录命令：rmdir （remove empty directories），例子：删除指定空目录：rmdir /tmp/a
　　复制文件或目录命令：cp （语法： cp -rp 【原文件或目录】【目标目录】）复制到目标目录可以改文件名
　　剪切文件或目录命令：mv 语法： mv【原文件或目录】【目标目录】
　　删除文件或目录命令：rm
　　创建空文件命令：touch
　　分页显示文件内容命令（不能向前翻页）：more 【文件名】 q 或 Q 退出
　　反向文件内容命令（文件即时更新后也能动态显示，多用于日志文件显示）：tail

## 二，链接命令和权限管理命令

　　生成链接文件命令：ln -s 创建软链接，软链接类似与 windows 的快捷方式，
　　不加 -s 创建硬链接 类似于 cp 复制操作，但硬链接可以同步更新。
　　更改文件或目录权限命令：chmod
　　改变文件或目录所有者命令：chown

## 三，Linux常用命令之文件搜索命令

　　最强大的搜索命令：find 语法：find【搜索范围】【匹配条件】 注意：Linux严格区分文件大小写，加*模糊搜索。
　　-name 按文件名搜索
　　/ -size 按文件大小搜索
　　/ -user 按文所有者搜索
　　/ -group 按文所属者搜索
在文件资料库中查找文件命令：locate﻿ find是全盘检索，而locate 是在文件资料库中进行搜索。比find速度快。
在文件中搜寻字符串匹配的行并输出：grep 如 grep mysql /root/install.log
搜索命令所在的目录及别名信息：which　
搜索命令所在的目录及帮助文档路径：whereis

## 四，Linux常用命令之帮助和用户管理命令

获得命令或配置文件帮助信息：man
获得shell内置命令的帮助信息：help
得命令的中文帮助信息：–help 例如 ls –help
用户管理命令
添加新用户：useradd 语法 useradd【用户名】
设置用户密码：passwd 语法： passwd【用户名】
查看登录用户简单信息：who
查看登录用户详细信息：w

## 五，Linux常用命令之压缩和解压缩命令

  tar -zcf  将文件或目录压缩为 .tar.gz 格式    应用最广
1
﻿ tar -zxf 将 .tar.gz 文件解压
﻿ gzip 将文件压缩为 .gz
gunzip 将 .gz 文件解压
zip 将文件或目录压缩为 .zip 格式
unzip 将 .zip 文件解压

## 六，Linux常用命令之网络和关机重启命令

ping 测试网络连通性
ifconfig 查看和设置网卡信息
traceroute 显示数据包到主机间的路径
setup 配置网络
shutdown命令 关机命令(推荐使用) 如shutdown -h now

## 七，Linux文本编辑器vim

退出命令：按esc键——输入：wq！回车.

## 八，Linux软件包管理之RPM命令

## 九，Linux软件包管理之yum

﻿ 在安装软件时，我们使用yum命令将会简单方便很多。解决了软件包依赖性问题。

 通过设定 ACL 权限，我们为某个用户指定某个文件的特定权限
 为什么要给硬盘分区？
①、易于管理和使用
②、有利于数据安全
③、节约寻找文件的时间

磁盘的分区主要分为基本分区（primary partion）和扩充分区(extension partion)两种，基本分区和扩充分区的数目之和不能大于四个。且基本分区可以马上被使用但不能再分区。扩充分区必须再进行分区后才能使用，也就是说它必须还要进行二次分区。那么由扩充分区再分下去的是什么呢？它就是逻辑分区（logical partion），况且逻辑分区没有数量上限制。

## 十，Linux的bash基本功能

bash 常用快捷键
ctrl + C 强制退出当前命令
ctrl + L 清屏 等等