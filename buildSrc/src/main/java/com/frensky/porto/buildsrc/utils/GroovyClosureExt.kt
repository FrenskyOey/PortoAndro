package com.frensky.porto.buildsrc.utils

import org.codehaus.groovy.runtime.DefaultGroovyMethods
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun Any.withClosure(closure: Any?) {
    DefaultGroovyMethods.with(this, closure as groovy.lang.Closure<*>)
}

fun DependencyHandlerScope.withDependenciesClosure(closure: Any?) {
    DefaultGroovyMethods.with(this.dependencies, closure as groovy.lang.Closure<*>)
}
