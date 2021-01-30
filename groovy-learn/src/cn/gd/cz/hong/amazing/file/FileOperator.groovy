package cn.gd.cz.hong.amazing.file
/**
 * 学习参考
 * [使用Groovy操作文件](https://blog.csdn.net/chenyulancn/article/details/65443468)
 */
/**
 * 传入文件路径
 * 输出文件内容
 */
def file_path = 'D:\\temp\\test\\java基础.txt';

def printFileContent = {
    String path ->
        println new File(path).text
}

printFileContent(file_path)

File file = new File(file_path)
assert file.name == 'java基础.txt'
assert file.isAbsolute()
assert file.path == file_path
assert file.parent == 'D:\\temp\\test'
println('--------------------------------------------------------------------')
//使用系统默认的编码处理文件流
file.eachLine {println it }

println('--------------------------------------------------------------------')
//指定处理流的编码
file.eachLine("UTF-8") { println it }

file.eachLine("UTF-8",10) {str,no->
    println str
    println no }

