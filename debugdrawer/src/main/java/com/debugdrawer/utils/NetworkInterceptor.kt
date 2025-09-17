package com.debugdrawer.utils

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Network interceptor for logging HTTP requests and responses.
 * This interceptor captures network traffic for debugging purposes.
 */
@Singleton
class NetworkInterceptor @Inject constructor(
    private val logger: Logger,
) : Interceptor {

    private val networkLogs = mutableListOf<NetworkLog>()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestTime = System.currentTimeMillis()

        // Log request
        val requestLog = NetworkLog(
            id = generateId(),
            method = request.method,
            url = request.url.toString(),
            headers = request.headers.toMultimap(),
            requestBody = request.body?.let { body ->
                try {
                    val buffer = okio.Buffer()
                    body.writeTo(buffer)
                    buffer.readUtf8()
                } catch (e: Exception) {
                    "Unable to read request body: ${e.message}"
                }
            },
            timestamp = requestTime,
            type = NetworkLogType.REQUEST,
        )

        networkLogs.add(requestLog)
        logger.d("NetworkInterceptor", "Request: ${request.method} ${request.url}")

        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: IOException) {
            val errorLog = NetworkLog(
                id = generateId(),
                method = request.method,
                url = request.url.toString(),
                headers = emptyMap(),
                requestBody = null,
                responseCode = -1,
                responseMessage = e.message ?: "Unknown error",
                responseBody = null,
                timestamp = System.currentTimeMillis(),
                duration = System.currentTimeMillis() - requestTime,
                type = NetworkLogType.ERROR,
            )
            networkLogs.add(errorLog)
            logger.e("NetworkInterceptor", "Request failed: ${request.method} ${request.url}", e)
            throw e
        }

        val responseTime = System.currentTimeMillis()
        val duration = responseTime - requestTime

        // Log response
        val responseBody = response.body
        val responseBodyString = responseBody?.let { body ->
            try {
                val source = body.source()
                source.request(Long.MAX_VALUE)
                val buffer = source.buffer
                val responseBodyString = buffer.clone().readUtf8()
                response.newBuilder()
                    .body(responseBodyString.toResponseBody(body.contentType()))
                    .build()
            } catch (e: Exception) {
                logger.w("NetworkInterceptor", "Unable to read response body: ${e.message}")
                response
            }
        } ?: response

        val responseLog = NetworkLog(
            id = generateId(),
            method = request.method,
            url = request.url.toString(),
            headers = response.headers.toMultimap(),
            requestBody = null,
            responseCode = response.code,
            responseMessage = response.message,
            responseBody = responseBodyString.body?.let { body ->
                try {
                    val source = body.source()
                    source.request(Long.MAX_VALUE)
                    source.buffer.clone().readUtf8()
                } catch (e: Exception) {
                    "Unable to read response body: ${e.message}"
                }
            },
            timestamp = responseTime,
            duration = duration,
            type = NetworkLogType.RESPONSE,
        )

        networkLogs.add(responseLog)
        logger.d("NetworkInterceptor", "Response: ${response.code} ${response.message} (${duration}ms)")

        return responseBodyString
    }

    /**
     * Get all captured network logs.
     */
    fun getNetworkLogs(): List<NetworkLog> = networkLogs.toList()

    /**
     * Clear all network logs.
     */
    fun clearLogs() {
        networkLogs.clear()
        logger.d("NetworkInterceptor", "Cleared all network logs")
    }

    private fun generateId(): String = System.currentTimeMillis().toString() + (0..999).random()
}

/**
 * Data class representing a network log entry.
 */
data class NetworkLog(
    val id: String,
    val method: String,
    val url: String,
    val headers: Map<String, List<String>>,
    val requestBody: String?,
    val responseCode: Int = -1,
    val responseMessage: String? = null,
    val responseBody: String? = null,
    val timestamp: Long,
    val duration: Long = 0,
    val type: NetworkLogType,
)

/**
 * Enum representing the type of network log entry.
 */
enum class NetworkLogType {
    REQUEST,
    RESPONSE,
    ERROR,
}
