package com.example.flowtest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class FlowReceiver<T>(private val flow: Flow<T>) {
    private val channel = Channel<T>(capacity = Channel.UNLIMITED)
    private var scope = CoroutineScope(Dispatchers.Default)

    fun start(): FlowReceiver<T> {
        scope.launch {
            flow.collect { value -> channel.send(value) }
        }
        return this
    }

    suspend fun next() = channel.receive()

    fun cancel() = scope.cancel()
}

fun <T> Flow<T>.receiver() = FlowReceiver(this)
