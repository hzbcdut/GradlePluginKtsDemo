package com.hzbcdut.plugins.transform.process

import com.hzbcdut.plugins.DP
import com.hzbcdut.plugins.InjectImpl
import com.hzbcdut.plugins.printMappedInterfaceAndImpls
import com.hzbcdut.plugins.visotor.InjectsVistor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter

object InjectsProcess : BytesProcess {
    override fun process(bytes: ByteArray): ByteArray? {
        if (DP.data.injectImpls.isEmpty()) return null
        val classReader = ClassReader(bytes)
        val internalName = classReader.className
//        if (internalName != Const.injectsUtilInternalName) return null
        val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
        val injectImplMap = DP.data.injectImpls
            .groupBy(InjectImpl::interfaceName) // interface: [impls]
            .mapValues { it.value.map(InjectImpl::implName) } // interface: [implsName]
            .toMutableMap()
        printMappedInterfaceAndImpls(injectImplMap)
        val visitor = InjectsVistor(classWriter, injectImplMap)
        classReader.accept(visitor, 0)
        return classWriter.toByteArray()
    }
}