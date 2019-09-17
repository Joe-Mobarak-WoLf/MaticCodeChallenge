package data.model


data class NetworkState(val status: Status, val msg: Int) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS, 0)
        val LOADING = NetworkState(Status.RUNNING, 0)

    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}
