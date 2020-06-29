

### 介绍

>1、AOP是Aspect Oriented Programming的缩写，即“面向切面编程”。
>
>2、AOP其实是OOP编程思想的一种延续。通过使用AOP，可以在编译期间对代码进行动态管理，以达到统一维护的目的。
>
>3、利用AOP，我们可以对业务逻辑的各个模块进行隔离，从而使得业务逻辑各个部分之间的耦合度降低，提高程序的可重用性，同时也会提高开发的效率。
>
>4、利用AOP，我们可以在无侵入的状态下在宿主中插入一些代码逻辑，从而实现一些特殊的功能，比如日志埋点、性能监控、动态权限控制、代码调试等。

### AOP术语

> 不得不说这些术语乍一看真的有点不好理解，但是当你过一遍结合栗子实践下就是过来人了。。。

###### 1、术语介绍

>1、Advice（通知、增强处理）：要实现的功能，例如方法耗时统计、日志收集等。
>
>2、JoinPoint（连接点）：能够切入的地方。这个就多了，一般方法执行前、方法执行后、方法执行前后、方法抛出异常时，都可以为连接点。
>
>3、Pointcut（切入点）：连接点是个概括的概念，例如一个类中有5个方法，那么这5个方法就有好多连接点对吧，假如我们不想对所有的方法都进行耗时统计（增强），只针对其中1个进行耗时统计，这时就使用切点的概念了。切点就是对连接点的筛选，筛选想要增强的方法。
>
>3、Aspect（切面）：通知和切入点的组合就叫切面。通知代表干什么（要实现的功能）什么时候干（方法执行的前、后）。切入点就代表在哪干（哪个方法）
>
>4、weaving（织入）：把切面应用到目标对象，产生新的代理对象。

###### 2、术语栗子图解

![](<https://github.com/sunnnydaydev/AppClick_AspectJ_AOP/blob/master/aop%E5%8E%9F%E7%90%86%E5%9B%BE.png>)

> 如上图，我们先有个概念：
>
> 1、使用切点筛选，要对哪些方法（目标方法）进行逻辑操作。至于怎样筛选就要使用切点表达式了（参考下文）
>
> 2、先理解为切面就是一个类即可。
>
> 3、如何把切面切入目标方法？这个就要借助一些技术了例如本文基于AOP思想的AspectJ技术。给自定义的类加上特殊注解这个类就是切面类了，给方法加上before注解就可以增强筛选的切点方法了。

###### 3、AOP织入方式

> 根据不同的实现技术，AOP有三种不同的织入方式:
>
> - 编译期织入，这要求使用特殊的Java编译器。
> - 类装载期织入，这要求使用特殊的类装载器。
> - 动态代理织入，在运行期为目标类添加增强生成子类的方式。（Spring框架采取方式）



### AOP注解与切点表达式

> aop是一种思想，其实现方式有多种，下面基于AOP思想AspectJ实现来讲解。

###### 1、常见AspectJ注解

>@Pointcut(切点表达式)：定义切点，标记方法。
>
>@Before(切点表达式)：前置通知，切点之前执行
>
>@Around(切点表达式)：环绕通知，切点前后执行
>
>@After(切点表达式)：后置通知，切点之后执行
>
>@AfterReturning(切点表达式)：返回通知，切点方法返回结果之后执行
>
>@AfterThrowing(切点表达式)：异常通知，切点抛出异常时执行（这个方法和@AfterReturning方法冲突，二者同时定义时只有一个满足条件执行）

###### 2、切点表达式

> 切点表达式就是注解的值呗，用来筛选方法的。只不过这个注解的值有一定的格式语法如下：

- 不完整写法：execution(<修饰符>? <返回类型> <方法名>(<参数>) <异常>?)

- 完整写法：execution(<@注解类型>? <修饰符>? <返回类型> <方法名>(<参数>) <异常>?)

（1）解释

- 带？的表示这部分是可选的，可有可无；

- 修饰符模式指的是public、private、protected等；

- 异常模式指的是如ClassNotFoundException异常等。

  ​

（2）栗子

> 先看看不完整写法吧，完整写法后续自定义注解再看。这里先忽略。
>
> 这里我们参考上图原理图，在切面类中我们定义个切点方法用于筛选要对哪个方法进行增强，如下就是对MainActivity的AspectJTest方法进行筛选。直接定位筛选到此方法。如下*表示任意，（..）表示任意参数个数方法都可以。

```java
// @Pointcut("execution(* com.example.aspectjpractise.MainActivity.AspectJTest(..))")
    public void pointCut() {
    }
```

# Talk is cheap. Show me the code.

> bb了一大堆不练习还是不行，接下来就练习下，针对MainActivity的一个方法进行增强。

###### 1、AspectJ在安卓上的使用

> 在Android Studio中使用AspectJ大概有两种方式:
>
> 1、android gradle配置下引入：通过在Gradle的构建脚本中，定义任务来使得项目执行ajc编译，将AOP的Module编织进入到目标工程中，从而达到非侵入式AOP的目的。
>
> 2、通过Gradle Plugin也可以通过插件来使用AspectJ。其实使用和方式1差不多，只是方式1的步骤放到了自定义插件中。然后在自定义插件中做逻辑。目前有很多开源的类似项目（插件，例如JakeWharton的hugo）
>
> 本文就讲解第一种方式，至于使用Gradle Plugin，需要具备gradle 自定义插件的知识，等文章末尾给出连接单独讲解。

###### 2、项目配置

（1）工程的build.gradle下插件引入

```java

buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.0'
        //aspectj
        classpath 'org.aspectj:aspectjtools:1.8.9'
        classpath 'org.aspectj:aspectjweaver:1.8.9'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
        
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

```

（2）app的build.gradle下引入依赖

```java

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.1'
    implementation 'org.aspectj:aspectjrt:1.8.9'//aspectj
}

// aspectj（直接copy来即可）
//代码放置位置与dependencies闭包同级即可
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
final def log = project.logger
final def variants = project.android.applicationVariants

variants.all { variant ->
    if (!variant.buildType.isDebuggable()) {
        log.debug("Skipping non-debuggable build type '${variant.buildType.name}'.")
        return
    }

    JavaCompile javaCompile = variant.javaCompile
    javaCompile.doLast {
        String[] args = ["-showWeaveInfo",
                         "-1.8",
                         "-inpath", javaCompile.destinationDir.toString(),
                         "-aspectpath", javaCompile.classpath.asPath,
                         "-d", javaCompile.destinationDir.toString(),
                         "-classpath", javaCompile.classpath.asPath,
                         "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
        log.debug "ajc args: " + Arrays.toString(args)

        MessageHandler handler = new MessageHandler(true)
        new Main().run(args, handler)
        for (IMessage message : handler.getMessages(null, true)) {
            switch (message.getKind()) {
                case IMessage.ABORT:
                case IMessage.ERROR:
                case IMessage.FAIL:
                    log.error message.message, message.thrown
                    break
                case IMessage.WARNING:
                    log.warn message.message, message.thrown
                    break
                case IMessage.INFO:
                    log.info message.message, message.thrown
                    break
                case IMessage.DEBUG:
                    log.debug message.message, message.thrown
                    break
            }
        }
    }

}

```



###### 3、栗子练习

```java

@Aspect
public class AspectJTest {

    @Pointcut("execution(* com.sunnyday.appclick_aspectj_aop.MainActivity.AspectJTest(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Log.d(TAG, "before method execute...");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.d(TAG, "around method execute...");
       
            // around 会默认拦截被切入的方法。可以使用proceed方法手动释放下原方法。
            proceedingJoinPoint.proceed(); // 此句代码之前的代码会在切点之前执行，此句代码之后的代码会在切点之后执行。
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        Log.d(TAG, "after method execute...");
    }

    @AfterReturning("pointCut()")
    public void AfterReturning(JoinPoint joinPoint, Object returnValue) {
        Log.d(TAG, "afterReturning, execution after return value ...");
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        Log.i(TAG, "afterThrowing: ");
        Log.d(TAG, "ex = " + ex.getMessage());
    }
}

```

> 1、around 和 after before的区别?
>
>  after、before 代表方法体中代码执行的前后，无论是正常或异常执行完毕。
>
>  around代表方法执行的前后其参数：ProceedingJoinPoint joinPoint的process方法 可释放around拦截原方法内容。释放之前的代码在切点之前执行，释放之后的代码在切点之后执行。
>
> 2、为啥设计了 around方法，和 before after冲突吗？ 
>
> 个人认为，在around方便处理方法执行前后的逻辑。如果放到两个不同的回调方法中，开发者的逻辑处理可能比较麻烦。

###### 4、JointPoint：切点信息的封装类

```java

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); // 获得切点方法的信息
        String methodName = methodSignature.getName();//获得方法名
        Method method = methodSignature.getMethod();//获得方法的Method 对象

        Class returnType = methodSignature.getReturnType();//返回值类型
        Class declaringType = methodSignature.getDeclaringType();// 方法所在类

        String[] parameterNames = methodSignature.getParameterNames();// 参数名
        Class[] parameterTypes = methodSignature.getParameterTypes();// 参数类型

        Log.d(TAG, "methodName:" + methodName);
        Log.d(TAG, "Method:" + method.getName());
        Log.d(TAG, "returnType:" + returnType.getSimpleName());
        Log.d(TAG, "declaringType:" + declaringType.getSimpleName());

        for (String name : parameterNames) {
            Log.i(TAG, "parameterName: " + name);
        }
        for (Class type : parameterTypes) {
            Log.i(TAG, "parameterTypes: " + type.getSimpleName());
        }
```



### 自定义注解&切点表达式

###### 1、切点表达式的全面写法

execution(<@注解类型>? <修饰符>? <返回类型> <方法名>(<参数>) <异常>?)

> 还记得上文介绍的这种写法吧，只是表达式里面多了个可选的注解类型。使用注解的方式方便解耦。其他和原来的方式没啥区别的。

###### 2、自定义注解标记目标方法

（1）自定义注解

```java

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAspectJ {
}

```

（2）标记目标方法

```java
 @MyAspectJ
    public void AspectJTest(View view) {
        Log.d(TAG, "AspectJ Test!!!");
        SystemClock.sleep(500);
    }
```

（3）修改切点表达式

> 注解使用类的全路径

```java
 // @Pointcut("execution(* com.sunnyday.appclick_aspectj_aop.MainActivity.AspectJTest(..))")
    @Pointcut("execution(@com.sunnyday.appclick_aspectj_aop.aspectj.MyAspectJ * *(..))") //使用注解的方式
    public void pointCut() {
    }
```



### 写到烂的栗子：方法耗时统计

> 貌似网上aop的文章大多都是用这个例子练习的，，，，其实代码也就那一丢丢。哈哈这里也搞一下吧！！！

```java
 @Around("pointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.d(TAG, "around method execute...");
        long startTime = System.currentTimeMillis();
        try {
            // around 会默认拦截被切入的方法。可以使用proceed方法手动释放下原方法。
            proceedingJoinPoint.proceed(); //   proceedingJoinPoint.proceed()此方法之前的代码会在切点之前执行，此方法之后的代码会在切点之后执行。
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            long during = endTime - startTime;
            Log.d(TAG, "方法耗时：" +during + "ms");
        }

    }
```

### 补充：call与execution区别

>在切点表达式中，execution与call是可以互换的那么二者有啥区别呢？
>当call捕获joinPoint时，捕获的是签名方法的调用点；而 execution 捕获joinPoint时，捕获的则是方法的执行点。
简单栗子来说就是call的 before、after切入到调用方法前后。execution的 before、after切入到方法的首行末行。

###### 伪代码& Call

```java

        //执行 Call (before) 代码
        AspectJTest(null);
        //执行Call(after)

```

###### 伪代码& execution

```java
              @MyAspectJ
               public void AspectJTest(View view) {
                  execution(before)
                  Log.d(TAG, "AspectJ Test!!!");
                  SystemClock.sleep(500);
                  execution(after)
           }
```

### 小结

> 总的来说aop入门还是很简单的，相对APT容易些，API少点，就是那几个术语写写栗子就明白了。至于AspectJ实战AppClick埋点、使用安卓Gradle方式配置AspectJ，就参考本项目README吧。

参考：

- 《安卓全埋点解决方案》第8章　$AppClick全埋点方案5：AspectJ

  ​