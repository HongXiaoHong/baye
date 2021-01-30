package cn.gd.cz.hong.amazing.closure

/**
 * delegate 对应于第三方对象，无论何时未定义消息的接收者，方法调用或属性都将被解析
 * 可以自定义delegate的指向
 */
class DelegateTest {
    static void main(String[] args) {
        DelegateEnclosing delegateEnclosing = new DelegateEnclosing()
        delegateEnclosing.run()
    }
}

class DelegateEnclosing {
    void run() {
        def cl = { getDelegate() }
        def cl2 = { delegate }
        assert cl() == cl2()
        assert cl() == this
        def enclosed = {
            { -> delegate }.call()
        }
        assert enclosed() == enclosed
    }
}

