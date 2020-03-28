## redo和undo日志

Undo Log记录某数据被修改前的值，可以用来在事务失败时进行rollback；

Redo Log记录某数据块被修改后的值，可以用来恢复未写入data file的已成功事务更新的数据。