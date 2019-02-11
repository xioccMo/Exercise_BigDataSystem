2018-2019学年第一学期《分布式模型与编程》期末上机测试

1. 登录VPN, 访问10.11.1.208:8080

2. 注册 DIMA Evaluation Tool, 在 register 中填写信息
    * First Name/Last Name: 请使用汉语拼音 
    * University: 选other, 填ECNU
    * Semester：5
    * Matriculation Number：学号后六位
  
3. 试题
    * 试题说明入口 DIMA Evaluation Tool -> Distributed System Paradigm and Programming -> Assignments
    * 代码入口 `git clone https://gitlab.com/dukechain/dspp_exam2018.git`
    * 进入代码目录 `cd dspp_exam2018`
	* 进入代码分支 `git checkout XXX` XXX 代表 branch 名称 (如 hadoop / spark 等)

4. 编码环境设置
	* 建议: 在 Intellij IDEA 中导入 Maven 工程
	* 在 Eclipse 导入 Maven 工程

5. 编码及调试
	* 根据说明补充相应代码
	* 运行 junit test 中的测试代码
	* 查看测试是否通过，并根据需要进行并调试

4. 调试并提交
    * 根据说明补充相应代码
	* 本地测试通过后通过IDE导出 JAR 包
	    * Intellij IDEA
	        * 选择 File -> Project Structure -> Artifacts -> + JAR -> Empty, 点击 Output Layout 下的加号, 选择 Module Output 和 Module Sources
	        * 选择 Build -> Build Artifacts, 在弹框中选择新建的 artifacts 进行 rebuild, JAR将包生成在 out 目录下或 classes 目录下
	    * Eclipse
	        * 选择 File -> Export -> Java -> JAR file
	        * 勾选 dspp_exam2018 下的 src/main/java, 勾选下方的 Export generated class files and resources, Export Java source files and resources, Compress the content of the JAR file, Add directory entries
	        * 选择输出路径, 点击 Finish
    * 在 DIMA Evaluation Tool 中的Submit处提交JAR包
