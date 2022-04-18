package com.hzbcdut.plugins.transform.process

import com.hzbcdut.plugins.DP
import com.hzbcdut.plugins.visotor.InjectClassVisitor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter

object InjectProcess : BytesProcess {
    override fun read(bytes: ByteArray) {
        val classReader = ClassReader(bytes)
        val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
        val injectClassVisitor = InjectClassVisitor(DP.data.injectInterfaceInternalNames, classWriter)
        classReader.accept(injectClassVisitor, 0)
    }
}