# baye

霸业

### 常用包

#### 内容提取

#### jsoup Java HTML 解析器

可参[Jsoup爬虫入门实战](https://www.bilibili.com/video/BV1La4y1x7Wm)
[jsoup 官方文档](https://jsoup.org/)
jsoup实现了WHATWG HTML5规范，并将 HTML 解析为与现代浏览器相同的 DOM。

- 从 URL、文件或字符串中抓取和解析HTML
- 使用 DOM 遍历或 CSS 选择器查找和提取数据
- 操作HTML 元素、属性和文本
- 根据安全列表清理用户提交的内容，以防止XSS攻击
- 输出整洁的 HTML

#### tika 文件内容提取

可参[Tika教程](https://www.sxt.cn/tika/tika.html)
Apache Tika 是什么?

- Apache Tika用于文件类型检测和从各种格式的文件内容提取的库。
- 在内部，Tika使用现有的各种文件解析器和文档类型的检测技术来检测和提取数据。
- 使用Tika，人们可以开发出通用型检测器和内容提取到的不同类型的文件，如电子表格，文本文件，图像，PDF文件甚至多媒体输入格式，在一定程度上提取结构化文本以及元数据。
- Tika提供用于解析不同文件格式的一个通用API。它采用83个现有的专业解析器库，为每个文档类型。
- 所有这些解析器库是根据一个叫做Parser接口单一接口封装。