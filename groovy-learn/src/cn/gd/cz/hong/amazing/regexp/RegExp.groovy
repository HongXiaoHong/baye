package cn.gd.cz.hong.amazing.regexp

import java.util.regex.Pattern

/**
 * 学习如何使用groovy的正则表达式
 *
 */

// 过滤h 开头 后缀是.txt的
def name_reg = ~/^h.*\.txt$/
def name = 'hong.txt'
def matches_result = name.matches(name_reg)
println("name_${name}.matches(name_reg_${name_reg}) = ${matches_result}")
assert name.matches(name_reg)
assert name_reg instanceof Pattern

