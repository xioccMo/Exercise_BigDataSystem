<font color=red>请在生成的学生试题仓库修改 "???" 处, 并删除本行</font>

# 大师编程 (DaSE Programming) 训练平台

![](./logo.jpg)

#### 1. 登录内网VPN
- VPN账号采用哈希方法分配:

  ```shell
  i = 序号 % 10 + 1;
  if i < 10 
      VPN用户名 = "dase" + "0" + i;
  else 
      VPN用户名 = "dase" + i;
  VPN密码 = VPN用户名;
  ```

  例如: 序号为9, 19的同学, VPN账号为 dase10; 序号为10, 20的同学, VPN账号为 dase01

- 访问 10.11.1.208:8080

#### 2. 注册 DIMA Evaluation Tool, 在 register 中填写信息
- First Name/Last Name: 请使用汉语拼音 
- University: 选 other, 填 ECNU
- Semester: ???
- Matriculation Number: 序号, 不足六位请在前面补0 (例如, 序号为8的同学, 此处填写 000008;
  序号为 18 的同学, 此处填写000018; 依此类推)

#### 3. 作业题
- 作业题说明入口 DIMA Evaluation Tool -> ??? -> Assignments
- 代码入口 `git clone https://gitlab.com/dukechain/???.git`
- 进入代码目录 `cd ???`
- 进入代码分支 `git checkout XXX`, 其中 `XXX` 代表分支名称 (如 hadoop_k_means 等)


#### 4. 编码环境设置
- 在 Intellij IDEA 中导入 Maven 工程 (**推荐**)
- 在 Eclipse 导入 Maven 工程

#### 5. 编码及调试
- 根据说明补充相应代码
- 运行 junit test 中的测试代码
- 查看测试是否通过, 并根据需要进行并调试

#### 6. 调试并提交
- 根据说明补充相应代码
- 本地测试通过后通过IDE导出 JAR 包
  - Intellij IDEA
    - 选择 File -> Project Structure -> Artifacts -> + JAR -> Empty, 点击 Output Layout 下的加号, 选择 Module Output 和 Module Sources
    - 选择 Build -> Build Artifacts, 在弹框中选择新建的 artifacts 进行 rebuild, JAR将包生成在 out 目录下或 classes 目录下
  - Eclipse
    - 选择 File -> Export -> Java -> JAR file
    - 勾选 bigdataprogramming_exercise 下的 src/main/java, 勾选下方的 Export generated class files and resources, Export Java source files and resources, Compress the content of the JAR file, Add directory entries
    - 选择输出路径, 点击 Finish
- 在 DIMA Evaluation Tool 中的 Submit test solution 处提交 JAR 包
