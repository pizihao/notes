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

## 十一 常用命令

### 1，系统信息

- arch 显示机器的处理器架构

  ~~~shell
  #兼容x86的64位架构
  [root@iZm5eafmkjj6lnkoxwvg59Z ~]# arch
  x86_64
  ~~~

- uname 操作集合 输出一组系统信息 

  -   -a, --all 以如下次序输出所有信息。其中若 -p 和 -i 的探测结果不可知则被省略：

    ~~~shell
    #和下面的信息一一对应
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname -a
    Linux iZm5eafmkjj6lnkoxwvg59Z 4.18.0-147.5.1.el8_1.x86_64 #1 SMP Wed Feb 5 02:00:39 UTC 2020 x86_64 x86_64 x86_64 GNU/Linux
    ~~~

  -   -s, --kernel-name        输出内核名称

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname -s
    Linux
    ~~~

  -   -n, --nodename           输出网络节点上的主机名

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname -n
    iZm5eafmkjj6lnkoxwvg59Z
    ~~~

  -   -r, --kernel-release     输出内核发行号

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname -r
    4.18.0-147.5.1.el8_1.x86_64
    ~~~

  -   -v, --kernel-version     输出内核版本

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname -v
    #1 SMP Wed Feb 5 02:00:39 UTC 2020
    ~~~

  -   -m, --machine            输出主机的硬件架构名称

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname -m
    x86_64
    
    ~~~

  -   -p, --processor          输出处理器类型（不可移植）

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname -p
    x86_64
    ~~~

  -   -i, --hardware-platform  输出硬件平台或（不可移植）

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname -i
    x86_64
    ~~~

  -   -o, --operating-system   输出操作系统名称

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname -o
    GNU/Linux
    ~~~

  - 省缺 默认为 uname -s

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# uname
    Linux
    ~~~

- dmidecode  命令集 查看服务器硬件信息

  -  -d, --dev-mem FILE    从设备文件读取内存 默认 一般不需要写 

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# dmidecode --dev-mem FIFE
    # dmidecode 3.2
    Getting SMBIOS data from sysfs.
    SMBIOS 2.8 present.
    9 structures occupying 429 bytes.
    Table at 0x000F5850.
    
    Handle 0x0000, DMI type 0, 24 bytes
    BIOS Information
            Vendor: SeaBIOS
            Version: 8c24b4c
            Release Date: 04/01/2014
            Address: 0xE8000
            Runtime Size: 96 kB
            ROM Size: 64 kB
            Characteristics:
                    BIOS characteristics not supported
                    Targeted content distribution is supported
            BIOS Revision: 0.0
    
    Handle 0x0100, DMI type 1, 27 bytes
    System Information
            Manufacturer: Alibaba Cloud
            Product Name: Alibaba Cloud ECS
            Version: pc-i440fx-2.1
            Serial Number: f8d76361-7581-41cf-ab49-dcbee2b8ad88
            UUID: f8d76361-7581-41cf-ab49-dcbee2b8ad88
            Wake-up Type: Power Switch
            SKU Number: Not Specified
            Family: Not Specified
    
    Handle 0x0300, DMI type 3, 21 bytes
    Chassis Information
            Manufacturer: Alibaba Cloud
            Type: Other
            Lock: Not Present
            Version: pc-i440fx-2.1
            Serial Number: Not Specified
            Asset Tag: Not Specified
            Boot-up State: Safe
            Power Supply State: Safe
            Thermal State: Safe
            Security Status: Unknown
            OEM Information: 0x00000000
            Height: Unspecified
            Number Of Power Cords: Unspecified
            Contained Elements: 0
    
    Handle 0x0400, DMI type 4, 42 bytes
    Processor Information
            Socket Designation: CPU 0
            Type: Central Processor
            Family: Other
            Manufacturer: Alibaba Cloud
            ID: 54 06 05 00 FF FB 8B 0F
            Version: pc-i440fx-2.1
            Voltage: Unknown
            External Clock: Unknown
            Max Speed: Unknown
            Current Speed: Unknown
            Status: Populated, Enabled
            Upgrade: Other
            L1 Cache Handle: Not Provided
            L2 Cache Handle: Not Provided
            L3 Cache Handle: Not Provided
            Serial Number: Not Specified
            Asset Tag: Not Specified
            Part Number: Not Specified
            Core Count: 1
            Core Enabled: 1
            Thread Count: 2
            Characteristics: None
    
    Handle 0x1000, DMI type 16, 23 bytes
    Physical Memory Array
            Location: Other
            Use: System Memory
            Error Correction Type: Multi-bit ECC
            Maximum Capacity: 2 GB
            Error Information Handle: Not Provided
            Number Of Devices: 1
    
    Handle 0x1100, DMI type 17, 40 bytes
    Memory Device
            Array Handle: 0x1000
            Error Information Handle: Not Provided
            Total Width: Unknown
            Data Width: Unknown
            Size: 2048 MB
            Form Factor: DIMM
            Set: None
            Locator: DIMM 0
            Bank Locator: Not Specified
            Type: RAM
            Type Detail: Other
            Speed: Unknown
            Manufacturer: Alibaba Cloud
            Serial Number: Not Specified
            Asset Tag: Not Specified
            Part Number: Not Specified
            Rank: Unknown
            Configured Memory Speed: Unknown
            Minimum Voltage: Unknown
            Maximum Voltage: Unknown
            Configured Voltage: Unknown
    
    Handle 0x1300, DMI type 19, 31 bytes
    Memory Array Mapped Address
            Starting Address: 0x00000000000
            Ending Address: 0x0007FFFFFFF
            Range Size: 2 GB
            Physical Array Handle: 0x1000
            Partition Width: 1
    
    Handle 0x2000, DMI type 32, 11 bytes
    System Boot Information
            Status: No errors detected
    
    Handle 0x7F00, DMI type 127, 4 bytes
    End Of Table
    
    ~~~

  -  -h, --help             显示帮助信息

  -  -q, --quiet            简略输出信息

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# dmidecode -q
    BIOS Information
            Vendor: SeaBIOS
            Version: 8c24b4c
            Release Date: 04/01/2014
            Address: 0xE8000
            Runtime Size: 96 kB
            ROM Size: 64 kB
            Characteristics:
                    BIOS characteristics not supported
                    Targeted content distribution is supported
            BIOS Revision: 0.0
    
    System Information
            Manufacturer: Alibaba Cloud
            Product Name: Alibaba Cloud ECS
            Version: pc-i440fx-2.1
            Serial Number: f8d76361-7581-41cf-ab49-dcbee2b8ad88
            UUID: f8d76361-7581-41cf-ab49-dcbee2b8ad88
            Wake-up Type: Power Switch
            SKU Number: Not Specified
            Family: Not Specified
    
    Chassis Information
            Manufacturer: Alibaba Cloud
            Type: Other
            Lock: Not Present
            Version: pc-i440fx-2.1
            Serial Number: Not Specified
            Asset Tag: Not Specified
            Boot-up State: Safe
            Power Supply State: Safe
            Thermal State: Safe
            Security Status: Unknown
            OEM Information: 0x00000000
            Height: Unspecified
            Number Of Power Cords: Unspecified
            Contained Elements: 0
    
    Processor Information
            Socket Designation: CPU 0
            Type: Central Processor
            Family: Other
            Manufacturer: Alibaba Cloud
            ID: 54 06 05 00 FF FB 8B 0F
            Version: pc-i440fx-2.1
            Voltage: Unknown
            External Clock: Unknown
            Max Speed: Unknown
            Current Speed: Unknown
            Status: Populated, Enabled
            Upgrade: Other
            Serial Number: Not Specified
            Asset Tag: Not Specified
            Part Number: Not Specified
            Core Count: 1
            Core Enabled: 1
            Thread Count: 2
            Characteristics: None
    
    Physical Memory Array
            Location: Other
            Use: System Memory
            Error Correction Type: Multi-bit ECC
            Maximum Capacity: 2 GB
            Number Of Devices: 1
    
    Memory Device
            Total Width: Unknown
            Data Width: Unknown
            Size: 2048 MB
            Form Factor: DIMM
            Set: None
            Locator: DIMM 0
            Bank Locator: Not Specified
            Type: RAM
            Type Detail: Other
            Speed: Unknown
            Manufacturer: Alibaba Cloud
            Serial Number: Not Specified
            Asset Tag: Not Specified
            Part Number: Not Specified
            Rank: Unknown
            Configured Memory Speed: Unknown
            Minimum Voltage: Unknown
            Maximum Voltage: Unknown
            Configured Voltage: Unknown
    
    Memory Array Mapped Address
            Starting Address: 0x00000000000
            Ending Address: 0x0007FFFFFFF
            Range Size: 2 GB
            Partition Width: 1
    
    System Boot Information
            Status: No errors detected
    ~~~

  -  -s, --string KEYWORD   仅显示给定DMI字符串的值 

    ~~~shell
    字符串有：
      基本输入输出系统：
      bios-vendor
      bios-version
      bios-release-date
      系统：
      system-manufacturer
      system-product-name
      system-version
      system-serial-number
      system-uuid
      system-family
      主板：
      baseboard-manufacturer
      baseboard-product-name
      baseboard-version
      baseboard-serial-number
      baseboard-asset-tag
      处理器有关信息：
      chassis-manufacturer
      chassis-type
      chassis-version
      chassis-serial-number
      chassis-asset-tag
      processor-family
      processor-manufacturer
      processor-version
      processor-frequency
    这些
    ~~~

    比如显示system-uuid信息

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# dmidecode -s system-uuid
    f8d76361-7581-41cf-ab49-dcbee2b8ad88
    ~~~

  -  -t, --type TYPE     只显示指定类型的信息

    ~~~shell
    1   System
    2   Baseboard
    3   Chassis
    4   Processor
    5   Memory Controller
    6   Memory Module
    7   Cache
    8   Port Connector
    9   System Slots
    10   On Board Devices
    ~~~

    -t 后面跟数字和字符串是一样的

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# dmidecode -t system
    # dmidecode 3.2
    Getting SMBIOS data from sysfs.
    SMBIOS 2.8 present.
    
    Handle 0x0100, DMI type 1, 27 bytes
    System Information
            Manufacturer: Alibaba Cloud
            Product Name: Alibaba Cloud ECS
            Version: pc-i440fx-2.1
            Serial Number: f8d76361-7581-41cf-ab49-dcbee2b8ad88
            UUID: f8d76361-7581-41cf-ab49-dcbee2b8ad88
            Wake-up Type: Power Switch
            SKU Number: Not Specified
            Family: Not Specified
    
    Handle 0x2000, DMI type 32, 11 bytes
    System Boot Information
            Status: No errors detected
    
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# dmidecode -t 1
    # dmidecode 3.2
    Getting SMBIOS data from sysfs.
    SMBIOS 2.8 present.
    
    Handle 0x0100, DMI type 1, 27 bytes
    System Information
            Manufacturer: Alibaba Cloud
            Product Name: Alibaba Cloud ECS
            Version: pc-i440fx-2.1
            Serial Number: f8d76361-7581-41cf-ab49-dcbee2b8ad88
            UUID: f8d76361-7581-41cf-ab49-dcbee2b8ad88
            Wake-up Type: Power Switch
            SKU Number: Not Specified
            Family: Not Specified
    
    ~~~

  -  -u, --dump            显示不解码的信息

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# dmidecode -u
    # dmidecode 3.2
    Getting SMBIOS data from sysfs.
    SMBIOS 2.8 present.
    9 structures occupying 429 bytes.
    Table at 0x000F5850.
    
    Handle 0x0000, DMI type 0, 24 bytes
            Header and Data:
                    00 18 00 00 01 02 00 E8 03 00 08 00 00 00 00 00
                    00 00 00 04 00 00 FF FF
            Strings:
                    53 65 61 42 49 4F 53 00
                    "SeaBIOS"
                    38 63 32 34 62 34 63 00
                    "8c24b4c"
                    30 34 2F 30 31 2F 32 30 31 34 00
                    "04/01/2014"
    
    Handle 0x0100, DMI type 1, 27 bytes
            Header and Data:
                    01 1B 00 01 01 02 03 04 61 63 D7 F8 81 75 CF 41
                    AB 49 DC BE E2 B8 AD 88 06 00 00
            Strings:
                    41 6C 69 62 61 62 61 20 43 6C 6F 75 64 00
                    "Alibaba Cloud"
                    41 6C 69 62 61 62 61 20 43 6C 6F 75 64 20 45 43
                    53 00
                    "Alibaba Cloud ECS"
                    70 63 2D 69 34 34 30 66 78 2D 32 2E 31 00
                    "pc-i440fx-2.1"
                    66 38 64 37 36 33 36 31 2D 37 35 38 31 2D 34 31
                    63 66 2D 61 62 34 39 2D 64 63 62 65 65 32 62 38
                    61 64 38 38 00
                    "f8d76361-7581-41cf-ab49-dcbee2b8ad88"
    
    Handle 0x0300, DMI type 3, 21 bytes
            Header and Data:
                    03 15 00 03 01 01 02 00 00 03 03 03 02 00 00 00
                    00 00 00 00 00
            Strings:
                    41 6C 69 62 61 62 61 20 43 6C 6F 75 64 00
                    "Alibaba Cloud"
                    70 63 2D 69 34 34 30 66 78 2D 32 2E 31 00
                    "pc-i440fx-2.1"
    
    Handle 0x0400, DMI type 4, 42 bytes
            Header and Data:
                    04 2A 00 04 01 03 01 02 54 06 05 00 FF FB 8B 0F
                    03 00 00 00 00 00 00 00 41 01 FF FF FF FF FF FF
                    00 00 00 01 01 02 02 00 01 00
            Strings:
                    43 50 55 20 30 00
                    "CPU 0"
                    41 6C 69 62 61 62 61 20 43 6C 6F 75 64 00
                    "Alibaba Cloud"
                    70 63 2D 69 34 34 30 66 78 2D 32 2E 31 00
                    "pc-i440fx-2.1"
    
    Handle 0x1000, DMI type 16, 23 bytes
            Header and Data:
                    10 17 00 10 01 03 06 00 00 20 00 FE FF 01 00 00
                    00 00 00 00 00 00 00
    
    Handle 0x1100, DMI type 17, 40 bytes
            Header and Data:
                    11 28 00 11 00 10 FE FF FF FF FF FF 00 08 09 00
                    01 00 07 02 00 00 00 02 00 00 00 00 00 00 00 00
                    00 00 00 00 00 00 00 00
            Strings:
                    44 49 4D 4D 20 30 00
                    "DIMM 0"
                    41 6C 69 62 61 62 61 20 43 6C 6F 75 64 00
                    "Alibaba Cloud"
    
    Handle 0x1300, DMI type 19, 31 bytes
            Header and Data:
                    13 1F 00 13 00 00 00 00 FF FF 1F 00 00 10 01 00
                    00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
    
    Handle 0x2000, DMI type 32, 11 bytes
            Header and Data:
                    20 0B 00 20 00 00 00 00 00 00 00
    
    Handle 0x7F00, DMI type 127, 4 bytes
            Header and Data:
                    7F 04 00 7F
    ~~~

  - 主要是显示硬件信息

### 2，系统操作

- shutdown 命令集 代表关机操作
  - shutdown -h now 立刻关闭
  - shutdown -h hours:minutes  按预定时间关闭系统 
  - shutdown -h +x x是一个数字，代表x分钟后关机
  - shutdown -r now 立刻重启
  - shutdown -r +x '将在x分钟后重启' x是一个数字，代表x分钟后重启，后面单引号中的是提示信息
  - shutdown -k now '将在x分钟后重启' x是一个数字，后面单引号中的是提示信息,只用于提示
- init 0 关机，init 6 重启
- reboot 重启
- logout 注销

### 3，文件操作

- cd 文件夹目录基本操作

- pwd 显示工作路径

- ls 查看目录中的文件

  - ls -F 查看目录中的文件
  - ls -l 显示文件和目录的详细资料
  - ls -a 显示隐藏文件

- tree 显示文件和目录由根目录开始的树形结构

  ~~~shell
  [root@iZm5eafmkjj6lnkoxwvg59Z ~]# tree
  .
  ├── filename
  └── liu
  
  0 directories, 2 files
  ~~~

- mkdir newdir 创建一个文件夹 名字为newdir  文件夹可以跟多个代表创建多个文件夹

- rm rmdir 命令 删除文件或者目录

  - `rm -f filename` 删除 filename文件
  - `rm -rf filename` 删除filename文件夹，并删除其内容
  - `rmdir filename` 删除filename文件夹

- mv filename new_filename 重命名/移动 一个目录

- cp 复制命令

  - cp file1 file2 复制一个文件，可以指定目录
  - cp dir/* . 复制一个目录下的所有文件到当前工作目录
  - cp -a dir1 dir2 复制一个目录

- cat 命令集 用于连接文件并打印到标准输出设备上。

  - 语法格式  `cat [-AbeEnstTuv] [--help] [--version] fileName`

  - filename 添加存在的一个文件名，可以直接输出文件信息，空格算作一行

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# cat liu
    ufkj
    fkjlj
    
    
    fklg
    fkjlf
    kfjl
    ~~~

  - -n 或 --number：由 1 开始对所有输出的行数编号。

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# cat -n liu
         1  ufkj
         2  fkjlj
         3  
         4  
         5  fklg
         6  fkjlf
         7  kfjl
    ~~~

    空格算作一行

  - -b 或 --number-nonblank和 -n 相似，只不过对于空白行不编号。

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# cat -b liu
         1  ufkj
         2  fkjlj
    
    
         3  fklg
         4  fkjlf
         5  kfjl
    ~~~

    虽然把空行留了出来但是没有编号

  - -s 或 --squeeze-blank：当遇到有连续两行以上的空白行，就代换为一行的空白行。

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# cat -s liu
    ufkj
    fkjlj
    
    fklg
    fkjlf
    kfjl
    ~~~

  - -v 或 --show-nonprinting：显示使用 ^ 和 M- 符号，除了 LFD 和 TAB 之外。

  - -E 或 --show-ends : 在每行结束处显示 $。

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# cat -E liu
    ufkj$
    fkjlj$
    $
    $
    fklg$
    fkjlf$
    kfjl$
    ~~~

  - -T 或 --show-tabs: 将 TAB 字符显示为 ^I。

  - -A, --show-all：等价于 -vET。

  - -e：等价于"-vE"选项；

  - -t：等价于"-vT"选项；

  - 其他用法 tac 倒序输出，tac本身就是cat的倒序写法，此时不再使用参数信息

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# tac liu
    kfjl
    fkjlf
    fklg
    
    
    fkjlj
    ufkj
    ~~~

  - 创建文件

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# cat > filename << EOF
    > 1
    > 2
    > 3
    > 
    > 4
    > EOF
    ~~~

    创建一个名为filename的文件，内容是下面输入的EOF为终点

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# cat filename 
    1
    2
    3
    
    4
    ~~~

    此处的EOF 必须顶行写，使用`cat > filename << -EOF`可以在任意位置结束

  - 可以把数据从一个文件附加 到另一个文件
    1. `cat -n textfile1 > textfile2`，把 textfile1 的文档内容加上行号后输入 textfile2 这个文档里
    2. `cat -b textfile1 textfile2 >> textfile3`，把 textfile1 和 textfile2 的文档内容加上行号（空白行不加）之后将内容附加到 textfile3 文档里
    3. `cat textfile1 > textfile2 `，把textfile1 写入textfile2，会覆盖textfile2的内容

- find 命令集 用于文件搜索

  - 语法为`find [-H] [-L] [-P] [-D debugopts] [-Olevel] [path...] [expression]`，可以简化为 `find [path...] -option [-print] [-exec -ok ...]`

  - -name 按照指定文件名查找文件

    ~~~shell
    find /dir -name filename  在/dir目录及其子目录下面查找名字为filename的文件 
    find . -name "*.c" 在当前目录及其子目录（用“.”表示）中查找任何扩展名为“c”的文件
    ~~~

  - -prune 使find命令不在当前指定的目录中查找，如果同时使用-depth选项，那么-prune将被find命令忽略

    ~~~shell
    find /apps -path "/apps/bin" -prune -o –print 在/apps目录下查找文件，但不希望在/apps/bin目录下查找 
    ~~~

  - -mtime -n +n  按照文件的更改时间来查找文件， - n表示文件更改时间距现在n天以内，+ n表示文件更改时间距现在n天以前。

    ~~~shell
    find / -mtime -5 –print 在系统根目录下查找更改时间在5日以内的文件 
    find /var/adm -mtime +3 –print 在/var/adm目录下查找更改时间在3日以前的文件
    ~~~

  - 和xargs连用

    通过管道符将通过获取到的文件通过xargs 来进行处理

    比如：查找l开通的文件并通过cat 展示

    ~~~shell
    [root@iZm5eafmkjj6lnkoxwvg59Z ~]# find -name l* | xargs cat 
    1
    2
    3
    
    4
    ~~~

- grep命令 用于文本搜索 通过正则表达式

### 4，权限操作



### 5，压缩操作









