# Multi Input Join
## 待完成
- 请在 DSPPCode.hadoop.multi_input_join 中创建 OrderMapperImpl, 继承 OrderMapper, 实现抽象方法。
- 请在 DSPPCode.hadoop.multi_input_join 中创建 PersonMapperImpl, 继承 PersonMapper, 实现抽象方法。
- 请在 DSPPCode.hadoop.multi_input_join 中创建 ReduceJoinReducerImpl, 继承 ReduceJoinReducer, 实现抽象方法。

## 题目描述
输入中给出两张关系表，分别为 persons 和 orders，要求输出 persons-orders 关系表(不包含表头)。
## 样例

下面将依次给出两个表格的示例以及输入格式：

- Person 表

    | Id_P | LastName | FirstName | Address        | City     |
    | :--- | :------- | :-------- | :------------- | :------- |
    | 1    | Adams    | John      | Oxford Street  | London   |
    | 2    | Bush     | George    | Fifth Avenue   | New York |
    | 3    | Carter   | Thomas    | Changan Street | Beijing  |
    
    输入格式（每一列用制表符分隔）：
    
    ```
    1	Adams	John	Oxford Street	London
    2	Bush	George	Fifth Avenue	New York
    3	Carter	Thomas	Changan Street	Beijing
    ```
    
    
    
- Order 表

    | Id_O | OrderNo | Id_P |
    | :--- | :------ | :--- |
    | 1    | 77895   | 3    |
    | 2    | 44678   | 3    |
    | 3    | 22456   | 1    |
    | 4    | 24562   | 1    |
    | 5    | 34764   | 65   |
    
    输入格式（每一列用制表符分隔）：
    
    ```
    1	77895	3
    2	44678	3
    3	22456	1
    4	24562	1
    5	34764	65
    ```
    
    

结果输出格式（每一列用制表符分隔）：

```
Adams	John	24562	
Adams	John	22456	
Carter	Thomas	44678	
Carter	Thomas	77895	

```

