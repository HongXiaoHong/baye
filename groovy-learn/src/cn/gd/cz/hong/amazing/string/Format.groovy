package cn.gd.cz.hong.amazing.string

/**
 * 插值字符串测试
 *
 *
 * the name of song is young
 */

song = "young"
println("the name of song is ${song}")

// 多行字符串加上\可以去掉换行
/**
 * with :
 * SELECT 1
 * FROM DUAL
 *
 * without : SELECT 1
 * FROM DUAL
 */

String multiple_with_return = """
SELECT 1
FROM DUAL
"""

String multiple_without_return = """\
SELECT 1
FROM DUAL
"""
println("with : ${multiple_with_return}")
println("without : ${multiple_without_return}")