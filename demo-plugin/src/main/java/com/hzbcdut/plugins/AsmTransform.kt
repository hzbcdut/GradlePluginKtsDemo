package com.hzbcdut.plugins

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Project

class AsmTransform(project:Project) :Transform() {

    override fun getName(): String {
        println("自定义Transform  获取名字  ")
        return AsmTransform::class.java.canonicalName
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun isIncremental(): Boolean {
        return true
    }
}