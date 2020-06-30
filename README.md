# AppClick 自动化埋点采集：基于Aspectj-AOP

[安卓AOP入门](https://github.com/sunnnydaydev/AppClick_AspectJ_AOP/blob/master/AOP.md)

[自定义Gradle插件](https://blog.csdn.net/qq_38350635/article/details/106986739)

### 基于Aspectj技术的AppClick全埋点实现

### 一、原理：

> 对于Android系统中的View，它的点击处理逻辑，都是通过设置相应的listener对象并重写相应的回调方法实现的。比如，对于Button、ImageView等控件，它设置的listener对象均是android.view.View.OnClickListener类型，然后重写它的onClick(android.view.View)回调方法。我们只要利用一定的技术原理，在应用程序编译期间（比如生成.dex之前），在其onClick(android.view.View)方法中插入相应的埋点代码，即可做到自动埋点，也就是全埋点。
> 核心实现：基于aspectJ 拦截 android.view.View.OnClickListener.onClick(android.view.View)。在方法执行前后插入代码即可。

二、自定义Gradle插件集成AspectJ 实现全埋点

> 吧AspectJ的功能集成到一个自定义的插件中供app module使用。也当练习了下自定义插件。以及全埋点实战。

###### 1、待续、、、

三、github 大牛AspectJ插件推荐

######  [gradle_plugin_android_aspectjx](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)

> 此框架对直接添加依赖引入AspectJ进行了封装，几句代码即可引入、功能也添加了扩展、十分强大。

待续！
