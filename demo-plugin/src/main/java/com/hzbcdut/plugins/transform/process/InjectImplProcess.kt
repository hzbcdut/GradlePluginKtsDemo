package com.hzbcdut.plugins.transform.process

import com.hzbcdut.plugins.DP
import com.hzbcdut.plugins.InjectImpl
import com.hzbcdut.plugins.pluginLog
import com.hzbcdut.plugins.toInternalName
import org.objectweb.asm.ClassReader

object InjectImplProcess : BytesProcess {
    override fun read(bytes: ByteArray) {
        val classReader = ClassReader(bytes)
        val className = classReader.className
        val classInterfaces = classReader.interfaces
        val findedInterface = DP.data.injectInterfaceInternalNames.find(classInterfaces::contains)
        if (findedInterface != null) {
            pluginLog("findedInterface = [${findedInterface.toInternalName()}], implClassName = [${className.toInternalName()}]")
            DP.data.injectImpls.add(InjectImpl(className, findedInterface))
        }
    }
}