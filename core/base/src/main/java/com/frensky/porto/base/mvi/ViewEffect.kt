package com.frensky.porto.base.mvi

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Wrapper for effect: a one-time event sent from the ViewModel to the UI.
 * Examples include:
 * - Showing an error snack bar
 * - Navigating to another page
 *
 * @param T The type of the event.
 * @param content The actual content of the event.
 */
class ViewEffect<out T>(
    private val content: T,
) {
    private val hasBeenHandled = AtomicBoolean(false)

    /**
     * Returns the content if it hasn't been handled yet, and marks it as handled.
     *
     * @return The content if it hasn't been handled, otherwise null.
     */
    fun getIfNotHandled(): T? =
        if (hasBeenHandled.getAndSet(true)) {
            null
        } else {
            content
        }

    /**
     * Returns the content, even if it's already been handled.
     *
     * @return The content.
     */
    fun peek(): T = content
}