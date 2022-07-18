package com.ibrahim.nbafacts.model.nbadata

data class internal(
    val consolidatedDomKey: String,
    val endToEndTimeMillis: String,
    val igorPath: String,
    val pubDateTime: String,
    val xslt: String,
    val xsltCompileTimeMillis: String,
    val xsltForceRecompile: String,
    val xsltInCache: String,
    val xsltTransformTimeMillis: String
)