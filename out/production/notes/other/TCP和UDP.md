# TCP和UDP

## 1、TCP和UDP的区别

1. TCP面向连接；UDP面向无连接
2. TCP保证数据正确性；UDP可能丢包
3. TCP传输速度慢；UDP速度快
4. 每一条TCP连接只能是点到点的；UDP支持一对一，一对多，多对一和多对多的交互通信
5. TCP对系统资源要求较多，UDP对系统资源要求较少。

## 2、三次握手

三次握手的目的是建立可靠的通信信道。确认自己与对方的发送与接收机能正常。

### TCP三次握手过程:

1. 主机A通过向主机B 发送一个含有同步序列号的标志位的数据段给主机B ，向主机B请求建立连接,通过这个数据段,主机A告诉主机B两件事：我想要和你通信、你可以用哪个序列号作为起始数据段来回应我。
2. 主机B收到主机A的请求后,用一个带有确认应答(ACK)和同步序列号(SYN)标志位的数据段响应主机A,也告诉主机A两件事：我已经收到你的请求了,你可以传输数据了、你要用哪个序列号作为起始数据段来回应我。
3. 主机A收到这个数据段后，再发送一个确认应答，确认已收到主机B的数据段：我已收到回复，我现在要开始传输实际数据了。

这样3次握手就完成了,主机A和主机B 就可以传输数据了.

![img](https://img-blog.csdn.net/20180804210802870?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgzNTkxNg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

### 为什么需要三次握手：

三次握手能确认双发收发功能都正常，缺一不可。

1. 第一次握手：Client什么都不能确认；Server确认了对方发送正常。
2. 第二次握手：Client确认了：自己发送、接收正常，对方发送、接收正常；Server确认了：自己接收正常，对方发送正常。
3. 第三次握手：Client确认了：自己发送、接收正常，对方发送、接收正常；Server确认了：自己发送、接收正常，对方发送接收正常。

三次握手的另一个目标是确认确认双方都支持TCP，告知对方用TCP传输。

1. 第一次握手：Server猜测Client可能要建立TCP请求，但不确定，因为也可能是Client乱发了一个数据包给自己。
2. 第二次握手：通过ack=J+1，Client知道Server是支持TCP的，且理解了自己要建立TCP连接的意图。
3. 第三次握手：通过ack=K+1，Server知道Client是支持TCP的，且确实是要建立TCP连接。

## 3、四次挥手

1. 第一次挥手：主机1（可以使客户端，也可以是服务器端），设置Sequence Number和Acknowledgment Number，向主机2发送一个FIN报文段；此时，主机1进入FIN_WAIT_1状态；这表示主机1没有数据要发送给主机2了。
2. 第二次挥手：主机2收到了主机1发送的FIN报文段，向主机1回一个ACK报文段，Acknowledgment Number为Sequence Number加1；主机1进入FIN_WAIT_2状态；主机2告诉主机1，我“同意”你的关闭请求。
3. 第三次挥手：主机2向主机1发送FIN报文段，请求关闭连接，同时主机2进入LAST_ACK状态。
4. 第四次挥手：主机1收到主机2发送的FIN报文段，向主机2发送ACK报文段，然后主机1进入TIME_WAIT状态；主机2收到主机1的ACK报文段以后，就关闭连接；此时，主机1等待2MSL后依然没有收到回复，则证明Server端已正常关闭，那好，主机1也可以关闭连接了。

为什么要time wait，为什么是2msl

![img](https://img-blog.csdn.net/20180813193910829?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgzNTkxNg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

为了保证A发送的最后一个ACK报文能够到达B。这个ACK报文段有可能丢失，因而使处在LAST-ACK状态的B收不到对已发送的FIN+ACK报文段的确认。B会超时重传这个FIN+ACK报文段，而A就能在2MSL时间内收到这个重传的FIN+ACK报文段。如果A在TIME-WAIT状态不等待一段时间，而是在发送完ACK报文段后就立即释放连接，就无法收到B重传的FIN+ACK报文段，因而也不会再发送一次确认报文段。这样，B就无法按照正常的步骤进入CLOSED状态。



MSL指的是任何IP数据报能够在因特网上存活的最长时间。假设现在一个MSL的时候，接收端需要发送一个应答，这时候，我们也必须等待这个应答的消失，这个应答的消失也是需要一个MSL，所以我们需要等待2MSL。