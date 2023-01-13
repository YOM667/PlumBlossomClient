package me.youm.plumblossom.feature.module

@Retention
annotation class ModuleSign(
    val name: String,
    val category: ModuleCategory,
    val keyBind: Int,
    val description: String
)
