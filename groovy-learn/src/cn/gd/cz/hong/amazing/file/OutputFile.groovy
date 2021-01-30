package cn.gd.cz.hong.amazing.file

/**
 * 输出到文件
 */
def writeFile = {
    String fileName ->
    def file = new File(fileName)

    if (file.exists())
        file.delete()

    def printWriter = file.newPrintWriter() //

    printWriter.write('The first content of file')
    printWriter.write('\n')
    printWriter.write('The first content of file')

    printWriter.flush()
    printWriter.close()
}

def file_path = 'D:\\temp\\test\\groovy-output-test.txt';
writeFile.call(file_path)

/**
 * 除了  file.newPrintWriter()  可以得到一个 PrintWriter，类似方法还有  file.newInputStream() 、  file.newObjectInputStream() 等。
 *
 * 更简洁写法：
 * new File(fileName).withPrintWriter { printWriter ->
 *      printWriter.println('The first content of file')
 * }
 */