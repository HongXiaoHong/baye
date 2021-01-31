## 1.声明

### 1.1. 变量定义

可以使用变量的类型（如`String`）或使用关键字`def`（或`var`）后跟变量名来定义变量：

```groovy
String x
def y
var z
```

`def`而`var`作为一个类型占位符，即更换为类型名称，如果你不想给一个明确的类型。可能是您不在意编译时的类型，或者只依赖于类型推断（具有Groovy的静态性质）。变量定义必须具有类型或占位符。如果省略，则类型名称将被视为引用现有变量（大概是在前面声明的）。对于脚本，假定未声明的变量来自脚本绑定。在其他情况下，您将获得缺少的属性（动态Groovy）或编译时错误（静态Groovy）。如果您想到`def`并`var`作为的别名`Object`，您会立即理解。

变量定义可以提供一个初始值，在这种情况下，就像一个声明和赋值（我们将在后面讨论）一样。

可以使用泛型来完善变量定义类型，例如中的`List<String> names`。


要了解有关泛型支持的更多信息，请阅读[泛型部分](http://groovy-lang.org/semantics.html#generics)

### 1.2. 变量分配

您可以将值分配给变量以供以后使用。请尝试以下操作：

```groovy
x = 1
println x

x = new java.util.Date()
println x

x = -3.1499392
println x

x = false
println x

x = "Hi"
println x
```

#### 1.2.1. 多重分配

Groovy支持多重分配，即可以一次分配多个变量，例如：

```groovy
def (a, b, c) = [10, 20, 'foo']
assert a == 10 && b == 20 && c == 'foo'
```

如果愿意，可以在声明中提供类型：

```groovy
def (int i, String j) = [10, 'foo']
assert i == 10 && j == 'foo'
```

与声明变量时使用的方法一样，它也适用于现有变量：

```groovy
def nums = [1, 3, 5]
def a, b, c
(a, b, c) = nums
assert a == 1 && b == 3 && c == 5
```

该语法适用于数组，列表以及返回以下任意一个的方法：

```groovy
def (_, month, year) = "18th June 2009".split()
assert "In $month of $year" == 'In June of 2009'
```

#### 1.2.2. 上溢和下溢

如果左侧的变量太多，则多余的变量将用null填充：

```groovy
def (a, b, c) = [1, 2]
assert a == 1 && b == 2 && c == null
```

如果右侧有太多变量，则多余的变量将被忽略：

```groovy
def (a, b) = [1, 2, 3]
assert a == 1 && b == 2
```

#### 1.2.3. 具有多个分配的对象分解

在描述各种
[Groovy运算符](http://groovy-lang.org/semantics.html#groovy-operators) 的部分中，

讨论了下[标运算符](http://groovy-lang.org/semantics.html#subscript-operator) 的大小写，解释了如何覆盖`getAt()`/`putAt()`方法。

使用这种技术，我们可以将多个分配和下标运算符方法结合起来以实现*对象分解*。

考虑下面的不可变`Coordinates`类，其中包含一对经度和纬度对，并注意该`getAt()`方法的实现：

```groovy
@Immutable
class Coordinates {
    double latitude
    double longitude

    double getAt(int idx) {
        if (idx == 0) latitude
        else if (idx == 1) longitude
        else throw new Exception("Wrong coordinate index, use 0 or 1")
    }
}
```

现在，让我们实例化该类并破坏其经度和纬度：

```groovy
def coordinates = new Coordinates(latitude: 43.23, longitude: 3.67) 

def (la, lo) = coordinates                                          

assert la == 43.23                                                  
assert lo == 3.67
```


### 1.3. 控制结构

#### 1.3.1. 条件结构

##### if-else

Groovy支持通常的if-else Java语法

```groovy
def x = false
def y = false

if ( !x ) {
    x = true
}

assert x == true

if ( x ) {
    x = false
} else {
    y = true
}

assert x == y
```

如果语法如下，否则Groovy还支持普通的Java“嵌套”：

```groovy
if ( ... ) {
    ...
} else if (...) {
    ...
} else {
    ...
}
```

##### switch/case

Groovy中的switch语句向后兼容Java代码. 因此您可以轻松解决多个匹配项共享相同代码的问题. 

但是，一个区别是Groovy switch语句可以处理任何类型的switch值，并且可以执行不同类型的匹配. 

```groovy
def x = 1.23
def result = ""

switch ( x ) {
    case "foo":
        result = "found foo"
        // lets fall through

    case "bar":
        result += "bar"

    case [4, 5, 6, 'inList']:
        result = "list"
        break

    case 12..30:
        result = "range"
        break

    case Integer:
        result = "integer"
        break

    case Number:
        result = "number"
        break

    case ~/fo*/: // toString() representation of x matches the pattern?
        result = "foo regex"
        break

    case { it < 0 }: // or { x < 0 }
        result = "negative"
        break

    default:
        result = "default"
}

assert result == "number"
```

Switch支持以下几种比较：

- 如果switch值是类的实例，则类大小写值匹配
- 如果`toString()`开关值的表示与正则表达式匹配，则正则表达式大小写值匹配
- 如果开关值包含在集合中，则集合用例值匹配. 这也包括范围（因为它们是列表）
- 如果根据[Groovy事实](http://groovy-lang.org/semantics.html#Groovy-Truth) ，调用闭包返回的结果为true，则闭包大小值匹配
- 如果未使用上述任何一项，则如果案例值等于开关值，则案例值匹配
- 当使用闭包大小值时，默认`it`参数实际上是开关值（在我们的示例中为variable `x`）. 

#### 1.3.2. 循环结构

##### 经典循环

Groovy支持标准Java / C for循环：

```groovy
String message = ''
for (int i = 0; i < 5; i++) {
    message += 'Hi '
}
assert message == 'Hi Hi Hi Hi Hi '
```

##### 增强的经典Java风格的for循环

现在支持带有逗号分隔表达式的Java经典for循环的更复杂形式. 例：

```groovy
def facts = []
def count = 5
for (int fact = 1, i = 1; i <= count; i++, fact *= i) {
    facts << fact
}
assert facts == [1, 2, 6, 24, 120]
```

##### 多分配结合for循环

自Groovy 1.6起，Groovy就支持多分配语句：

```groovy
// multi-assignment with types
def (String x, int y) = ['foo', 42]
assert "$x $y" == 'foo 42'
```

这些现在可以出现在for循环中：

```groovy
// multi-assignment goes loopy
def baNums = []
for (def (String u, int v) = ['bar', 42]; v < 45; u++, v++) {
    baNums << "$u $v"
}
assert baNums == ['bar 42', 'bas 43', 'bat 44']
```

##### 循环中

Groovy中的for循环要简单得多，并且可以与任何类型的数组，集合，Map等一起使用. 

```groovy
// iterate over a range
def x = 0
for ( i in 0..9 ) {
    x += i
}
assert x == 45

// iterate over a list
x = 0
for ( i in [0, 1, 2, 3, 4] ) {
    x += i
}
assert x == 10

// iterate over an array
def array = (0..4).toArray()
x = 0
for ( i in array ) {
    x += i
}
assert x == 10

// iterate over a map
def map = ['abc':1, 'def':2, 'xyz':3]
x = 0
for ( e in map ) {
    x += e.value
}
assert x == 6

// iterate over values in a map
x = 0
for ( v in map.values() ) {
    x += v
}
assert x == 6

// iterate over the characters in a string
def text = "abc"
def list = []
for (c in text) {
    list.add(c)
}
assert list == ["a", "b", "c"]
```

Groovy还支持带有冒号的Java冒号变体： `for (char c : text) {}`

##### while循环

Groovy支持像Java这样的常规{@}循环：

```groovy
def x = 0
def y = 5

while ( y-- > 0 ) {
    x++
}

assert x == 5
```

##### do/while循环

现在支持Java的class do / while循环. 例：

```groovy
// classic Java-style do..while loop
def count = 5
def fact = 1
do {
    fact *= count--
} while(count > 1)
assert fact == 120
```

#### 1.3.3. 异常处理

异常处理与Java相同. 

#### 1.3.4. try/catch/finally

您可以指定complete `try-catch-finally`，a`try-catch`或一`try-finally`组块. 

```groovy
try {
    'moo'.toLong()   // this will generate an exception
    assert false     // asserting that this point should never be reached
} catch ( e ) {
    assert e in NumberFormatException
}
```

我们可以将代码放在匹配的“ try”子句之后的“ finally”子句中，以便无论“ try”子句中的代码是否引发异常，finally子句中的代码将始终执行：

```groovy
def z
try {
    def i = 7, j = 0
    try {
        def k = i / j
        assert false        //never reached due to Exception in previous line
    } finally {
        z = 'reached here'  //always executed even if Exception thrown
    }
} catch ( e ) {
    assert e in ArithmeticException
    assert z == 'reached here'
}
```

#### 1.3.5. 多catch

使用multi catch块（自Groovy 2.0起），我们能够定义要捕获并由同一catch块处理的多个异常：

```groovy
try {
    /* ... */
} catch ( IOException | NullPointerException e ) {
    /* one block to handle 2 exceptions */
}
```

#### 1.3.6. ARM尝试使用资源

Groovy经常为Java 7的`try`-with-resources语句提供自动资源管理（ARM）的更好的选择. 现在，迁移到Groovy并仍希望使用旧样式的Java程序员支持该语法：

```groovy
class FromResource extends ByteArrayInputStream {
    @Override
    void close() throws IOException {
        super.close()
        println "FromResource closing"
    }

    FromResource(String input) {
        super(input.toLowerCase().bytes)
    }
}

class ToResource extends ByteArrayOutputStream {
    @Override
    void close() throws IOException {
        super.close()
        println "ToResource closing"
    }
}

def wrestle(s) {
    try (
            FromResource from = new FromResource(s)
            ToResource to = new ToResource()
    ) {
        to << from
        return to.toString()
    }
}

def wrestle2(s) {
    FromResource from = new FromResource(s)
    try (from; ToResource to = new ToResource()) { // Enhanced try-with-resources in Java 9+
        to << from
        return to.toString()
    }
}

assert wrestle("ARM was here!").contains('arm')
assert wrestle2("ARM was here!").contains('arm')
```

产生以下输出：

```
ToResource关闭
FromResource关闭
ToResource关闭
FromResource关闭
```

### 1.4. 断言

与Groovy共享`assert`关键字的Java不同，Groovy中后者的行为非常不同. 首先，始终执行Groovy中的断言，`-ea`而与JVM的标志无关. 这使其成为单元测试的一流选择. “力量断言”的概念与Groovy的`assert`行为方式直接相关. 

功率断言可分解为三个部分：

```
断言[左表达式] == [右表达式] ：（可选消息）
```

断言的结果与您在Java中获得的结果完全不同. 如果断言为真，则什么也不会发生. 如果断言为假，则它提供断言的表达式的每个子表达式的值的直观表示. 例如：

```groovy
assert 1+1 == 3
```

将产生：

```
捕获：断言失败：

断言1 + 1 == 3 
        | | 
        2假
```

当表达式更复杂时，幂断言变得非常有趣，如下面的示例所示：

```groovy
def x = 2
def y = 7
def z = 5
def calc = { a,b -> a*b+1 }
assert calc(x,y) == [x,z].sum()
```

它将为每个子表达式打印值：

```groovy
assert calc(x,y) == [x,z].sum()
       |    | |  |   | |  |
       15   2 7  |   2 5  7
                 false
```

如果您不想像上面那样打印出漂亮的错误消息，则可以通过更改断言的可选消息部分来回退到自定义错误消息，如以下示例所示：

```groovy
def x = 2
def y = 7
def z = 5
def calc = { a,b -> a*b+1 }
assert calc(x,y) == z*z : 'Incorrect computation result'
```

它将显示以下错误消息：

```groovy
Incorrect computation result. Expression: (calc.call(x, y) == (z * z)). Values: z = 5, z = 5
```

### 1.5. 带标签的声明

任何语句都可以与标签关联. 标签不会影响代码的语义，可用于使代码更易于阅读，如以下示例所示：

```groovy
given:
    def x = 1
    def y = 2
when:
    def z = x+y
then:
    assert z == 3
```

尽管不更改标记语句的语义，但是可以在`break`指令中使用标签作为跳转目标，如下面的示例所示. 但是，即使允许这样做，这种编码样式通常也被认为是不好的做法：

```groovy
for (int i=0;i<10;i++) {
    for (int j=0;j<i;j++) {
        println "j=$j"
        if (j == 5) {
            break exit
        }
    }
    exit: println "i=$i"
}
```

重要的是要了解默认情况下标签不会影响代码的语义，但是它们属于抽象语法树（AST），因此AST转换可以使用该信息对代码进行转换，因此导致不同的语义. [Spock框架](http://spockframework.github.io/spock/docs/current/index.html) 尤其这样 做是为了使测试更容易. 