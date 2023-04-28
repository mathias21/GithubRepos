package com.batcuevasoft.githubrepo.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.stateIn(
    scope: CoroutineScope,
    initialValue: T
) = stateIn(scope = scope, started = SharingStarted.WhileSubscribed(5000L), initialValue = initialValue)