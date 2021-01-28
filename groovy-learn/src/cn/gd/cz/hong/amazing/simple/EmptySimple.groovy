package cn.gd.cz.hong.amazing.simple


class Employee {
    String name
}

em = new Employee()
em.name = 'Tom'

if (em) {
    println "name: $em.name"
}
// -->>> 如果用了?.的表达式 可以简化成这样

println("name ${em?.name}")