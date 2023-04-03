package com.example.kotkin_team.feed.domain

data class FeedLoadingState(val status: Status, val msg: String? = null) {
    companion object {
        val LOADED = FeedLoadingState(Status.SUCCESS)
        val LOADING = FeedLoadingState(Status.RUNNING)
        fun error(msg: String?) = FeedLoadingState(Status.FAILED, msg)
    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}
