package com.agromall.presentation.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


public inline fun <T> Flow<T>.handleError(crossinline action: (value: String) -> Unit): Flow<T> =
catch { e -> action(e.localizedMessage) }
