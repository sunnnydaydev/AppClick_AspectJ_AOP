package com.sunnyday.appclick_aspectj_aop

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task("CustomTask"){
            println("i am custom task!")
        }
    }
}