# 大师编程 (DaSE Programming) 训练平台

![](./logo.jpg)

#### 1. 登录内网 VPN

- 参见大夏学堂实验课程部分
 
- 访问 http://10.11.1.208:8080

#### 2. 注册 DIMA Evaluation Tool, 在 register 中填写信息
- First Name/Last Name: 请使用汉语拼音 

- University: 选 other, 填 ECNU

- Semester: 5

- Matriculation Number: 序号, 不足六位请在前面补 0 (例如, 序号为8的同学, 此处填写 000008;
  序号为 18 的同学, 此处填写 000018; 依此类推)

#### 3. 试题
- 提交入口 DIMA Evaluation Tool → Distributed System Paradigm and Programming (Exercise) → Assignments (密码: dspp2019)

- 代码入口: 使用SSH clone (如未设置 SSH key, 请先完成 [Gitlab SSH key 设置](./SSH.md)) 或使用Https clone 命令为`git clone URL` (其中URL见仓库右上角clone)

- 更新代码: `git pull origin master` (**更新之前请参考最后一条保存当前分支进度**)

- 进入代码目录 `cd bigdataprogramming_exercise`

- 切换代码分支 `git checkout XXX`, 其中 `XXX` 代表分支名称 (如 hadoop_k_means 等) (**切换之前请参考最后一条保存当前分支进度**)

- 在做题过程中若要切换分支, 使用 `git add . ; git commit -m "v"` 保存当前分支进度

#### 4. 编码环境设置
- 在 Intellij IDEA 中导入 Maven 工程

#### 5. 编码及调试
- 根据说明补充相应代码

- 运行 junit test 中的测试代码

  以 hadoop_k_means 为例. 在 Intellij IDEA 中打开 `src/test/java/DSPPTest/student/hadoop/k_means/KMeansTest.java`

  按 Ctrl + Shift + F10 运行, 或右键选择 Run 'KMeansTest.java'

- 查看测试是否通过, 并根据需要进行并调试

#### 6. 调试并提交
- 根据说明补充相应代码

- 本地测试通过后通过 Intellij IDEA 导出 JAR 包

  - 选择 File → Project Structure → Artifacts → + JAR → Empty, 点击 Output Layout 下的加号, 选择 Module Output 和 Module Sources

  - 选择 Build → Build Artifacts, 在弹框中选择新建的 artifacts 进行 rebuild, JAR将包生成在 out 目录下或 classes 目录下

- 在 DIMA Evaluation Tool 中的 Submit test solution 处提交 JAR 包
