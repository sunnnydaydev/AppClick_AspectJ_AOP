package com.sunnyday.appclick_aspectj_aop

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

        project.extensions.create("MyPlugin",PluginConfig)//读取用户在app build.gradle下的MyPlugin闭包

        project.task("CustomTask"){
            println("i am custom task!")
        }

        project.afterEvaluate {
          println("debug="+project.MyPlugin.debug)
        }

    }
}