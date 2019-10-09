# multi Join
## 待完成
- 请在DSPPCode.hadoop.multi_join中创建MultiJoinMapperImpl, 继承MultiJoinMapper, 实现虚函数。
- 请在DSPPCode.hadoop.multi_join中创建MultiJoinReducerImpl, 继承MultiJoinReducer, 实现虚函数。

## 题目描述
输入中给出两张关系表，分别为companyname-addressid和addressid-addressname，要求输出companyname-addressname关系表(包含表头)。
## 样例
输入文件：      
file1  
companyname	addressid  
xiaomi	1  
baidu	1  
xiecheng	2  
alibaba	3	  

file2  
addressid	addressname  
1	beijing  
2	shanghai  
3	hangzhou  

输出文件：      
companyname	addressname  
baidu	beijing  
xiaomi	beijing  
xiecheng	shanghai  
alibaba	hangzhou  
