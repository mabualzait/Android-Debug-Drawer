package com.abualzait.debugdrawer.modules

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.abualzait.debugdrawer.R
import com.abualzait.debugdrawer.utils.Logger
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Enhanced debug module that displays real-time system logs with advanced filtering and search.
 * Note: This requires READ_LOGS permission.
 */
class LogsModule @Inject constructor(
    private val context: Context,
    private val logger: Logger,
) : DebugModule {

    companion object {
        private const val MAX_LOG_ENTRIES = 1000
        private const val LOG_PARTS_MIN_SIZE = 6
        private const val TAG_MESSAGE_DELIMITER = ':'
        private const val COLON_INDEX_MIN = 0
    }

    override val name: String = "logcat"
    override val title: String = "Logcat Viewer"
    override val description: String = "Real-time logcat with filtering and search"
    override val priority: Int = 4

    private var listView: ListView? = null
    private var adapter: LogAdapter? = null
    private var logCountTextView: TextView? = null
    private val allLogEntries = mutableListOf<LogEntry>()
    private val filteredLogEntries = mutableListOf<LogEntry>()
    
    private var logcatProcess: Process? = null
    private var isStreaming = false
    private var isPaused = false
    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())
    
    // Filter states
    private var currentLevelFilter: LogLevel? = null
    private var currentTagFilter: String = ""
    private var currentSearchQuery: String = ""
    private var autoScroll = true

    override fun createView(): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.module_logcat, null)

        setupLogcat(view)

        logger.d("LogsModule", "Created enhanced logcat view")
        return view
    }

    private fun setupLogcat(view: View) {
        listView = view.findViewById(R.id.lv_logs)
        logCountTextView = view.findViewById(R.id.tv_log_count)
        adapter = LogAdapter(context, filteredLogEntries)
        listView?.adapter = adapter

        // Set up control buttons
        view.findViewById<View>(R.id.btn_start_streaming)?.setOnClickListener {
            startLogcatStreaming()
        }
        
        view.findViewById<View>(R.id.btn_stop_streaming)?.setOnClickListener {
            stopLogcatStreaming()
        }
        
        view.findViewById<View>(R.id.btn_pause_resume)?.setOnClickListener {
            togglePauseResume()
        }

        view.findViewById<View>(R.id.btn_clear_logs)?.setOnClickListener {
            clearLogs()
        }
        
        view.findViewById<View>(R.id.btn_export_logs)?.setOnClickListener {
            exportLogs()
        }

        // Set up level filter buttons
        view.findViewById<View>(R.id.btn_filter_verbose)?.setOnClickListener {
            setLevelFilter(LogLevel.VERBOSE)
        }
        view.findViewById<View>(R.id.btn_filter_debug)?.setOnClickListener {
            setLevelFilter(LogLevel.DEBUG)
        }
        view.findViewById<View>(R.id.btn_filter_info)?.setOnClickListener {
            setLevelFilter(LogLevel.INFO)
        }
        view.findViewById<View>(R.id.btn_filter_warning)?.setOnClickListener {
            setLevelFilter(LogLevel.WARNING)
        }
        view.findViewById<View>(R.id.btn_filter_error)?.setOnClickListener {
            setLevelFilter(LogLevel.ERROR)
        }
        view.findViewById<View>(R.id.btn_show_all)?.setOnClickListener {
            setLevelFilter(null)
        }

        // Set up tag filter
        val tagFilterEditText = view.findViewById<EditText>(R.id.et_tag_filter)
        tagFilterEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
            override fun afterTextChanged(s: Editable?) {
                currentTagFilter = s?.toString() ?: ""
                applyFilters()
            }
        })

        // Set up search
        val searchEditText = view.findViewById<EditText>(R.id.et_search)
        searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
            override fun afterTextChanged(s: Editable?) {
                currentSearchQuery = s?.toString() ?: ""
                applyFilters()
            }
        })

        // Set up auto-scroll toggle
        view.findViewById<View>(R.id.btn_toggle_autoscroll)?.setOnClickListener {
            toggleAutoScroll()
        }
    }

    private fun startLogcatStreaming() {
        if (isStreaming) return
        
        isStreaming = true
        isPaused = false
        
        executor.execute {
            try {
                logcatProcess = Runtime.getRuntime().exec("logcat -v time")
                val reader = BufferedReader(InputStreamReader(logcatProcess?.inputStream))
                
                reader.useLines { lines ->
                    lines.forEach { line ->
                        if (!isPaused) {
                            val logEntry = parseLogLine(line)
                            if (logEntry != null) {
                                mainHandler.post {
                                    addLogEntry(logEntry)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                logger.e("LogsModule", "Failed to stream logs", e)
                mainHandler.post {
                    isStreaming = false
                }
            }
        }
        
        logger.d("LogsModule", "Started logcat streaming")
    }

    private fun stopLogcatStreaming() {
        isStreaming = false
        logcatProcess?.destroy()
        logcatProcess = null
        logger.d("LogsModule", "Stopped logcat streaming")
    }

    private fun togglePauseResume() {
        isPaused = !isPaused
        logger.d("LogsModule", "Logcat ${if (isPaused) "paused" else "resumed"}")
    }

    private fun addLogEntry(logEntry: LogEntry) {
        allLogEntries.add(logEntry)
        
        // Keep only last MAX_LOG_ENTRIES entries to prevent memory issues
        if (allLogEntries.size > MAX_LOG_ENTRIES) {
            allLogEntries.removeAt(0)
        }
        
        // Apply current filters
        if (matchesFilters(logEntry)) {
            filteredLogEntries.add(logEntry)
            adapter?.notifyDataSetChanged()
            updateLogCount()
            
            // Auto-scroll to bottom
            if (autoScroll) {
                listView?.post {
                    listView?.smoothScrollToPosition(filteredLogEntries.size - 1)
                }
            }
        }
    }

    private fun clearLogs() {
        try {
            Runtime.getRuntime().exec("logcat -c")
            allLogEntries.clear()
            filteredLogEntries.clear()
            adapter?.notifyDataSetChanged()
            logger.d("LogsModule", "Cleared logs")
        } catch (e: Exception) {
            logger.e("LogsModule", "Failed to clear logs", e)
        }
    }

    private fun exportLogs() {
        logger.d("LogsModule", "Export logs functionality not yet implemented")
    }

    private fun setLevelFilter(level: LogLevel?) {
        currentLevelFilter = level
        applyFilters()
        logger.d("LogsModule", "Set level filter: $level")
    }

    private fun toggleAutoScroll() {
        autoScroll = !autoScroll
        logger.d("LogsModule", "Auto-scroll ${if (autoScroll) "enabled" else "disabled"}")
    }

    private fun updateLogCount() {
        logCountTextView?.text = "Logs: ${filteredLogEntries.size}"
    }

    private fun applyFilters() {
        filteredLogEntries.clear()
        
        val filtered = allLogEntries.filter { logEntry ->
            matchesFilters(logEntry)
        }
        
        filteredLogEntries.addAll(filtered)
        adapter?.notifyDataSetChanged()
        updateLogCount()
        
        if (autoScroll && filteredLogEntries.isNotEmpty()) {
            listView?.post {
                listView?.smoothScrollToPosition(filteredLogEntries.size - 1)
            }
        }
    }

    private fun matchesFilters(logEntry: LogEntry): Boolean {
        // Level filter
        if (currentLevelFilter != null && logEntry.level != currentLevelFilter) {
            return false
        }
        
        // Tag filter
        if (currentTagFilter.isNotEmpty() && 
            !logEntry.tag.contains(currentTagFilter, ignoreCase = true)) {
            return false
        }
        
        // Search filter
        if (currentSearchQuery.isNotEmpty()) {
            val searchLower = currentSearchQuery.lowercase()
            return logEntry.message.contains(searchLower, ignoreCase = true) ||
                   logEntry.tag.contains(searchLower, ignoreCase = true)
        }
        
        return true
    }

    private fun parseLogLine(line: String): LogEntry? {
        return try {
            // Parse logcat format: "MM-DD HH:MM:SS.XXX PID TID LEVEL TAG: MESSAGE"
            val parts = line.split(" ", limit = LOG_PARTS_MIN_SIZE)
            if (parts.size >= LOG_PARTS_MIN_SIZE) {
                val timestamp = "${parts[0]} ${parts[1]}"
                val pid = parts[2]
                val tid = parts[3]
                val levelStr = parts[4]
                val tagAndMessage = parts[5]

                val colonIndex = tagAndMessage.indexOf(TAG_MESSAGE_DELIMITER)
                val tag = if (colonIndex > COLON_INDEX_MIN) tagAndMessage.substring(0, colonIndex) else "Unknown"
                val message = if (colonIndex > COLON_INDEX_MIN) tagAndMessage.substring(colonIndex + 1).trim() else tagAndMessage

                val level = when (levelStr) {
                    "V" -> LogLevel.VERBOSE
                    "D" -> LogLevel.DEBUG
                    "I" -> LogLevel.INFO
                    "W" -> LogLevel.WARNING
                    "E" -> LogLevel.ERROR
                    "F" -> LogLevel.FATAL
                    else -> LogLevel.INFO
                }

                LogEntry(timestamp, pid, tid, level, tag, message)
            } else {
                null
            }
        } catch (e: Exception) {
            logger.e("LogsModule", "Failed to parse log line: $line", e)
            null
        }
    }

    override fun onShow() {
        if (!isStreaming) {
            startLogcatStreaming()
        }
    }

    override fun onAttach() {
        logger.d("LogsModule", "Attached to debug drawer")
    }

    override fun onDetach() {
        stopLogcatStreaming()
        logger.d("LogsModule", "Detached from debug drawer")
    }
}

/**
 * Data class representing a log entry.
 */
data class LogEntry(
    val timestamp: String,
    val pid: String,
    val tid: String,
    val level: LogLevel,
    val tag: String,
    val message: String,
)

/**
 * Enum representing log levels.
 */
enum class LogLevel(val displayName: String) {
    VERBOSE("V"),
    DEBUG("D"),
    INFO("I"),
    WARNING("W"),
    ERROR("E"),
    FATAL("F"),
}

/**
 * Adapter for displaying log entries in a ListView.
 */
private class LogAdapter(
    context: Context,
    logs: MutableList<LogEntry>,
) : ArrayAdapter<LogEntry>(context, R.layout.item_log, logs) {

    override fun getView(position: Int, convertView: View?, parent: android.view.ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_log, parent, false)
        val log = getItem(position) ?: return view

        view.findViewById<TextView>(R.id.tv_timestamp).text = log.timestamp
        view.findViewById<TextView>(R.id.tv_level).text = log.level.displayName
        view.findViewById<TextView>(R.id.tv_pid).text = log.pid
        view.findViewById<TextView>(R.id.tv_tag).text = log.tag
        view.findViewById<TextView>(R.id.tv_message).text = log.message

        // Set color based on log level
        val levelColor = when (log.level) {
            LogLevel.VERBOSE -> 0xFF808080.toInt()
            LogLevel.DEBUG -> 0xFF0000FF.toInt()
            LogLevel.INFO -> 0xFF00FF00.toInt()
            LogLevel.WARNING -> 0xFFFFFF00.toInt()
            LogLevel.ERROR -> 0xFFFF0000.toInt()
            LogLevel.FATAL -> 0xFFFF00FF.toInt()
        }
        view.findViewById<TextView>(R.id.tv_level).setTextColor(levelColor)

        return view
    }
}
