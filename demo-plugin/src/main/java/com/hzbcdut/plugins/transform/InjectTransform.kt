package com.hzbcdut.plugins.transform

import com.hzbcdut.plugins.transform.process.*


class InjectTransform : BaseTransform(), FileProcess by InjectProcess.convertToFileProcess()
class InjectImplTransform : BaseTransform(), FileProcess by InjectImplProcess.convertToFileProcess()
class InjectsTransform : BaseTransform(), FileProcess by InjectsProcess.convertToFileProcess()