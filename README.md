# 霸业

> 学无止境 学习使人快乐 使人愉快 使人愉悦 使人开心 使人获益 使人升华

主要用来学习后端知识 前端可往 [鸿途](https://github.com/HongXiaoHong/hongtu) 或者 [前端之路](https://github.com/HongXiaoHong/front_road)

各个分支的学习内容放到各个分支中

下面的标题形式为

**分支名 | 简介**

### background-task | 后台任务

module

- Overdue payment statistics | 欠费统计
  - 调用接口对欠费用户进行统计
  - 之所以不进行直接统计是因为欠费的数据他不在我们这边
  - 我们需要调用接口进行欠费用户的统计
  - 通过 在 properties 配置文件中动态配置 cron 表达式, 而不再代码中写
  - 通过 forkjoin 进行优化