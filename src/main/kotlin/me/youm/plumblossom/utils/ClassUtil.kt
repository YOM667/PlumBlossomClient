package me.youm.plumblossom.utils

import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil
import java.lang.reflect.Modifier
import java.net.URI

object ClassUtil {
    fun <T : Any> packageScanner(packagePath: String, klass: Class<T>): List<Class<out T>> {
        //Log4j的工具类
        val resolver = ResolverUtil()

        resolver.classLoader = klass.classLoader
        //在packagePath传入的值中匹配
        resolver.findInPackage(object : ResolverUtil.Test{
            override fun matches(type: Class<*>?): Boolean = true
            override fun matches(resource: URI?): Boolean = true
            override fun doesMatchClass(): Boolean = true
            override fun doesMatchResource(): Boolean = true
        } ,packagePath)
        //存储找到的类
        val classes = mutableListOf<Class<out T>>()
        //遍历resolver中找到的元素
        for(resolved in resolver.classes) {
            resolved.declaredMethods.find { Modifier.isNative(it.modifiers) }?.let {
                throw UnsatisfiedLinkError("in ${resolved.name}.${it.declaringClass.typeName+"."+it.name}(Native Method)") // we don't want native methods
            }
            if(klass.isAssignableFrom(resolved) && !resolved.isInterface && !Modifier.isAbstract(resolved.modifiers)) {
                @Suppress("UNCHECKED_CAST")
                classes += resolved as Class<out T>
            }
        }
        return classes
    }
}