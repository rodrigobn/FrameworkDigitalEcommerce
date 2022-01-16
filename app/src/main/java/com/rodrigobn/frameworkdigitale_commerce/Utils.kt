package com.rodrigobn.frameworkdigitale_commerce

fun String.toDecimals(): String {
    if (this.contains(",")){
        return this.replace("," , ".")
    }
    return this
}