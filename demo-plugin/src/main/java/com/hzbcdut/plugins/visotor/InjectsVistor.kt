package com.hzbcdut.plugins.visotor

import com.hzbcdut.plugins.InjectImplsMap
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class InjectsVistor(
    classVisitor: ClassVisitor,
    private val injectImplsMap: InjectImplsMap
) : ClassVisitor(Opcodes.ASM7, classVisitor) {
    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor? {
        val methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions) ?: return null
        if (name == "findAnyWithIndex" && descriptor == "(Ljava/lang/String;I)Ljava/lang/Object;") {
            return object : MethodVisitor(api, methodVisitor) {
                override fun visitCode() {
                    methodVisitor.visitCode()
//                    ASMCodeGen.genImplsMethods(methodVisitor, injectImplsMap)
//                    ASMCodeGen.genMethodEndWithNull(methodVisitor)
                    methodVisitor.visitEnd()
                }
            }
        }
        if (name == "anyImplsCount" && descriptor == "(Ljava/lang/String;)I") {
            return object : MethodVisitor(api, methodVisitor) {
                override fun visitCode() {
                    methodVisitor.visitCode()
//                    ASMCodeGen.genImplCount(methodVisitor, injectImplsMap)
//                    ASMCodeGen.genMethodEndWithZero(methodVisitor)
                    methodVisitor.visitEnd()
                }
            }
        }
        return methodVisitor
    }
}