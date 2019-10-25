# Broadcast Join

## 待完成

  - 请在 DSPPCode.hadoop.broadcast_join 中创建 BroadcastJoinMapperImpl, 继承 BroadcastJoinMapper, 实现抽象方法

## 题目描述

  输入中给出两张关系表, 分别为 Persons 和 Orders, 要求输出

  ```sql
  SELECT LastName, FirstName, OrderNo
  FROM Persons, Orders
  WHERE Persons.Id_P = Orders.Id_P
  ```

## 样例

  下面将依次给出两个表格的示例和输入输出格式

  - Persons 表

    | Id_P | LastName | FirstName | Address        | City     |
    | :--- | :------- | :-------- | :------------- | :------- |
    | 1    | Adams    | John      | Oxford Street  | London   |
    | 2    | Bush     | George    | Fifth Avenue   | New York |
    | 3    | Carter   | Thomas    | Changan Street | Beijing  |

    输入格式 (字段间用 "," 分隔）

    ```
    1,Adams,John,Oxford,Street,London
    2,Bush,George,Fifth,Avenue,New York
    3,Carter,Thomas,Changan,Stree,Beijing
    ```

  - Order 表

    | Id_O | OrderNo | Id_P |
    | :--- | :------ | :--- |
    | 1    | 77895   | 3    |
    | 2    | 44678   | 3    |
    | 3    | 22456   | 1    |
    | 4    | 24562   | 1    |
    | 5    | 34764   | 65   |

    输入格式 (字段间用 "," 分隔）

    ```
    1,77895,3
    2,44678,3
    3,22456,1
    4,24562,1
    5,34764,65
    ```

  - 输出格式 (字段间用 "," 分隔）

    ```
    Adams,John,24562
    Adams,John,22456
    Carter,Thomas,44678
    Carter,Thomas,77895
    ```
