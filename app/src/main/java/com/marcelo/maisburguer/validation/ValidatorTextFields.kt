package com.marcelo.maisburguer.validation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

interface TextString {

    @get : Composable
    val value: String
}

class ResiurceString(@StringRes private val intup: Int): TextString {

    override val value: String
        @Composable
        get() = stringResource(intup)

}

class RawString(private val input: String): TextString {

    override val value: String
        @Composable
        get() = input

}
