package com.hzbcdut.plugins.transform.process

import com.hzbcdut.plugins.InjectImpl


class ProcessingData {
    // 所有被 @CatInject 注解的接口
    internal val injectInterfaceInternalNames = HashSet<String>()

    // 所有被实现了 injectInterfaceInternalNames 中接口的类
    internal val injectImpls = ArrayList<InjectImpl>()
}