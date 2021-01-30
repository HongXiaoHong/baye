# groovy 
## 让人觉得惊艳的点
- 闭包
- 正则表达式
- 文件操作
- 插值字符串
- 运行时类型推断
- 空值 判空

### cn.gd.cz.hong.amazing.type
用于测试类型
1. 变量的定义
2. 运行时类型推断

### cn.gd.cz.hong.amazing.string
1. 多行字符串去掉空行 \
2. 插值字符串


### cn.gd.cz.hong.amazing.simple
1. 判空 ?. [Groovy Tip 4 对象非空判断及“?”运算符](https://blog.csdn.net/hivon/article/details/2304049)

### cn.gd.cz.hong.amazing.file
File 对象
1. 文件读取 每行处理
2. 目录处理 用正则表达式匹配输出的文件
3. 输出文件

### cn.gd.cz.hong.amazing.regexp
正则表达式
使用类似~/^h.*\.txt$/的形式创建 Pattern
简化了java中创建的过程

### cn.gd.cz.hong.amazing.closure
闭包 - 委托策略
要理解委托的概念，我们必须首先解释this闭包内部的含义。闭包实际上定义了3个不同的东西：

this对应于定义闭包的封闭类

owner对应于定义闭包的封闭对象，它可以是类或闭包

delegate 对应于第三方对象，无论何时未定义消息的接收者，方法调用或属性都将被解析

可以参考
[闭包](http://groovy-lang.org/closures.html)
这里已经讲的很清楚了

## 项目结构

groovy-learn 就是学习的module

groovy是一种弱类型的的语言


