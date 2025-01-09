package io.duhanmo.paymentmvcpractice.adapter.controller.common

data class ApiResponse<T>(
    val message: String? = "",
    val body: T? = null,
) {
    companion object {
        fun <T> success(body: T?): ApiResponse<T> = ApiResponse(body = body)

        fun error(message: String?): ApiResponse<Unit> = ApiResponse(message = message)
    }
}
