package com.oguzdogdu.recipes.domain.model

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)