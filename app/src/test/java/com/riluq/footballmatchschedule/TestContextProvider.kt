package com.riluq.footballmatchschedule

import kotlinx.coroutines.experimental.Unconfined
import org.junit.Test

import org.junit.Assert.*
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider: CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}