package cn.gd.cz.hong.amazing.file

def dirName = 'D:\\temp\\test';

def dir = new File(dirName)
if (dir.isDirectory()) {
    dir.eachFileRecurse { file ->
        println file
    }
}

dir.eachFileMatch(~/.*\.txt/) {File it-> println it.name  } //使正则表达式匹配文件名
//dir.eachFileMatch(FILES, ~/.*\.txt/) { File it-> println it.name  }

