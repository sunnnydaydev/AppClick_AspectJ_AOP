# AppClick 自动化埋点采集：基于Aspectj-AOP

### [安卓AOP入门](https://github.com/sunnnydaydev/AppClick_AspectJ_AOP/blob/master/AOP.md)

### [自定义Gradle插件](https://blog.csdn.net/qq_38350635/article/details/106986739)

### [自定义Gradle 插件集成AspectJ 实现全埋点插件](https://github.com/sunnnydaydev/AspectJPlugin)

> 为啥这里要自定义实现一遍呢？或许有一下几点好处吧：
>
> 1、可以结合自定义插件复习下学过的自定义插件知识。
>
> 2、通过直接引入依赖方式，重复的代码太多，每次都要粘贴好多太繁琐。aspectJ集成在插件中更方便引入。
>
> 3、以这个例子明白下主流的aspectJ插件封装过程
>
> 4、结合安卓全埋点实战下

###### 

### github 大牛AspectJ插件推荐

>  如上我们通过自定义插件吧aspectj的功能封到插件中，方便每次快速引入。其实这个已经有大佬们做好了，而且还对功能做了拓展。[gradle_plugin_android_aspectjx](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx) 此框架对直接添加依赖引入AspectJ进行了封装，几句代码即可引入、功能也添加了扩展、十分强大。