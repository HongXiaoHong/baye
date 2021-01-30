package cn.gd.cz.hong.amazing.closure

/**
 * owner对应于定义闭包的封闭对象，它可以是类或闭包
 */
class OwnerTest {
    static void main(String[] args) {
        OwnerEnclosing enclosing = new OwnerEnclosing()
        enclosing.run()

        OwnerEnclosedInInnerClass enclosedInInnerClass = new OwnerEnclosedInInnerClass()
        enclosedInInnerClass.run()

        OwnerNestedClosures nestedClosures = new OwnerNestedClosures()
        nestedClosures.run()
    }
}

class OwnerEnclosing {
    void run() {
        def whatIsOwnerMethod = { getOwner() }
        assert whatIsOwnerMethod() == this
        def whatIsOwner = { owner }
        assert whatIsOwner() == this
    }
}

class OwnerEnclosedInInnerClass {
    class Inner {
        Closure cl = { owner }
    }

    void run() {
        def inner = new Inner()
        assert inner.cl() == inner
    }
}

class OwnerNestedClosures {
    void run() {
        def nestedClosures = {
            def cl = { owner }
            cl()
        }
        assert nestedClosures() == nestedClosures
    }
}

