package cn.gd.cz.hong.amazing.closure
/**
 * this 对应于定义闭包的封闭类 或者说内部类
 */
class ThisTest {
    static void main(String[] args) {
        ThisEnclosing enclosing = new ThisEnclosing()
        enclosing.run()

        ThisEnclosedInInnerClass enclosedInInnerClass = new ThisEnclosedInInnerClass()
        enclosedInInnerClass.run()

        ThisNestedClosures nestedClosures = new ThisNestedClosures()
        nestedClosures.run()
    }
}

class ThisEnclosing {
    void run() {
        def whatIsThisObject = { getThisObject() }
        assert whatIsThisObject() == this
        def whatIsThis = { this }
        assert whatIsThis() == this
    }
}
class ThisEnclosedInInnerClass {
    class Inner {
        Closure cl = { this }
    }
    void run() {
        def inner = new Inner()
        assert inner.cl() == inner
    }
}
class ThisNestedClosures {
    void run() {
        def nestedClosures = {
            def cl = { this }
            cl()
        }
        assert nestedClosures() == this
    }
}

