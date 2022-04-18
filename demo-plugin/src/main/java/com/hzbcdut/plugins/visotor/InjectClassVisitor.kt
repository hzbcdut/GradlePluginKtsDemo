package com.hzbcdut.plugins.visotor

import com.hzbcdut.plugins.pluginLog
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

// 扫描所有被 Inject 注解的类
class InjectClassVisitor(private val interfaces: HashSet<String>, classVisitor: ClassVisitor) : ClassVisitor(Opcodes.ASM7, classVisitor) {
    private lateinit var internalName: String
    override fun visit(
        version: Int, access: Int, name: String,
        signature: String?, superName: String?, interfaces: Array<String>?
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        internalName = name
    }

    override fun visitAnnotation(desc: String?, visible: Boolean): AnnotationVisitor {
        // TODO:  
//        if (desc == Const.injectAnnotationDescriptor) {
//            pluginLog("in class: $internalName --- find: $desc")
//            interfaces.add(internalName)
//        }

        pluginLog(" InjectClassVisitor ==>   in class: $internalName --- find: $desc")
        return super.visitAnnotation(desc, visible)
    }

    override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        pluginLog(" InjectClassVisitor ==> visitMethod()  name = $name   descriptor =  $descriptor")
        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }
}