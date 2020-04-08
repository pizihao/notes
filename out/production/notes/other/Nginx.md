# Nginx

## 一，安装

1. ```shell
   yum -y install gcc gcc-c++ autoconf pcre pcre-devel make automake
   ```

2. ~~~shell
   yum -y install wget httpd-tools vim
   ~~~

3. 关闭防火墙，关闭防火墙开启自启动

   ~~~shell
   systemctl stop firewalld
   systemctl disable firewalld
   ~~~

4. 初始化基本目录

   ~~~shell
   mkdir /soft/{code,logs,package/src,backup} -p
   ~~~

5. 安装基本安装包

   ~~~shell
   yum	install -y gcc gcc-c++ autoconf pcre pcre-devel	make automake wget httpd-tools vim tree
   ~~~

6. 配置nginx官方yum源

   ~~~shell
   vim /etc/yum.repos.d/nginx.repo
   
   [nginx-stable]
   name=nginx stable repo
   baseurl=http://nginx.org/packages/centos/$releasever/$basearch/
   gpgcheck=1
   enabled=1
   gpgkey=https://nginx.org/keys/nginx_signing.key
   
   [nginx-mainline]
   name=nginx mainline repo
   baseurl=http://nginx.org/packages/mainline/centos/$releasever/$basearch/
   gpgcheck=1
   enabled=0
   gpgkey=https://nginx.org/keys/nginx_signing.key
   ~~~

7. 安装

   ~~~shell
   yum	install	nginx -y
   ~~~

8. 查看版本

   ~~~shell
   nginx -v
   ~~~


## 二，nginx安装目录

| 路径                                                         | 类型     | 作用                           |
| ------------------------------------------------------------ | -------- | ------------------------------ |
| /etc/nginx <br/>/etc/nginx/nginx.conf<br/>/etc/nginx/conf.d<br/>/etc/nginx/conf.d/default.conf | 配置文件 | Nginx主配置⽂文件              |
| /etc/nginx/fastcgi_params <br/>/etc/nginx/scgi_params <br/>/etc/nginx/uwsgi_params | 配置文件 | Cgi、Fastcgi、Uwcgi配置⽂文件  |
| /etc/nginx/win-utf <br/>/etc/nginx/koi-utf <br/>/etc/nginx/koi-win | 配置文件 | Nginx编码转换映射⽂文件        |
| /etc/nginx/mime.types                                        | 配置文件 | http协议的Content-Type与扩展名 |
| /usr/lib/systemd/system/nginx.service                        | 配置文件 | 配置系统守护进程管理理器器     |
| /etc/logrotate.d/nginx                                       | 配置文件 | Nginx⽇日志轮询,⽇日志切割     |
| /usr/sbin/nginx <br/>/usr/sbin/nginx-debug                   | 命令     | Nginx终端管理理命令            |
| /etc/nginx/modules <br/>/usr/lib64/nginx <br/>/usr/lib64/nginx/modules | 目录     | Nginx模块⽬目录                |
| /usr/share/nginx <br/>/usr/share/nginx/html <br/>/usr/share/nginx/html/50x.html <br/>/usr/share/nginx/html/index.html | 目录     | Nginx默认站点⽬目录            |
| /usr/share/doc/nginx-1.12.2 <br/>/usr/share/man/man8/nginx.8.gz | 目录     | Nginx的帮助手册                |
| /var/cache/nginx                                             | 目录     | Nginx的缓存目录                |
| /var/log/nginx                                               | 目录     | Nginx的日志目录                |

## 三，nginx内置变量

$uri:	当前请求的uri，不不带参数 

$request_uri:	请求的uri，带完整参数 

$host:	http请求报⽂文中host⾸首部,如果没有则以处理理此请求的虚拟主机的主机名代替 

$hostname:	nginx服务运⾏行行在主机的主机名 

$remote_addr:	客户端IP

$remote_port:	客户端端⼝口 

$remote_user:	使⽤用⽤用户认证时客户端⽤用户输⼊入的⽤用户名 

$request_filename:	⽤用户请求中的URI经过本地root或alias转换后映射的本地⽂文件路路径、、

$request_method:	请求⽅方法,	GET	POST	PUT 

$server_addr:	服务器器地址 

$server_name:	服务器器名称 

$server_port:	服务器器端⼝

$server_protocol:	服务器器向客户端发送响应时的协议,	如http/1.1	http/1.0 

$scheme:在请求中使⽤用scheme,	如http://xxx.com中的http 

$http_HEADER:	匹配请求报⽂文中指定的HEADER 

$http_host:	匹配请求报⽂文中的host⾸首部 

$document_root:	当前请求映射到的root配置

