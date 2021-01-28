package cn.gd.cz.hong.amazing.type

/**
 * <h1>测试是否在运行时才选择方法</h1>
 * <div>结果证明确实是在运行时才选择执行的方法
 * 而不是在编译的时候</div>
 */
class DynamicTest {
    static def test(Object obj) {
        println("obj : ${obj}")
    }

    static def test(String str) {
        println("str : ${str}")
    }

    static void main(String[] args) {
        test("Hong")
        test(new Date())
        /**:)
         * result :
         *
         * str : Hong
         * obj : Thu Jan 28 22:46:14 CST 2021
         *
         *
         */
    }
}
