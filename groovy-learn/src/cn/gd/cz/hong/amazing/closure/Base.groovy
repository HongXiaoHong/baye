package cn.gd.cz.hong.amazing.closure

/**
 * 闭包绝对是groovy的核心
 * 定义的形式为 {[参数->] code}*/

def output_name = {
    String name ->
        println("hello my name is ${name}")
}

/**
 * 调用直接闭包名字加()就好
 * 标准的方式是 闭包.call(参数)
 */

output_name('hongxiaohong')
output_name.call('洪晓鸿')

/**
 * hello my name is hongxiaohong
 * hello my name is 洪晓鸿
 */