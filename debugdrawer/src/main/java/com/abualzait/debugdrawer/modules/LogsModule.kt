package com.abualzait.debugdrawer.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.abualzait.debugdrawer.R
import com.abualzait.debugdrawer.utils.Logger
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * Debug module that displays system logs.
 * Note: This requires READ_LOGS permission.
 */
class LogsModule @Inject constructor(
    private val context: Context,
    private val logger: Logger,
) : DebugModule {

    override val name: String = "logs"
    override val title: String = "System Logs"
    override val description: String = "View system and application logs"
    override val priority: Int = 4

    private var listView: ListView? = null
    private var adapter: LogAdapter? = null
    private val logEntries = mutableListOf<LogEntry>()

    override fun createView(): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.module_logs, null)

        setupLogs(view)

        logger.d("LogsModule", "Created logs view")
        return view
    }

    private fun setupLogs(view: View) {
        listView = view.findViewById(R.id.lv_logs)
        adapter = LogAdapter(context, logEntries)
        listView?.adapter = adapter

        // Set up refresh button
        view.findViewById<View>(R.id.btn_refresh_logs)?.setOnClickListener {
            refreshLogs()
        }

        // Set up clear button
        view.findViewById<View>(R.id.btn_clear_logs)?.setOnClickListener {
            clearLogs()
        }

        // Set up filter buttons
        view.findViewById<View>(R.id.btn_filter_verbose)?.setOnClickListener {
            filterLogs(LogLevel.VERBOSE)
        }
        view.findViewById<View>(R.id.btn_filter_debug)?.setOnClickListener {
            filterLogs(LogLevel.DEBUG)
        }
        view.findViewById<View>(R.id.btn_filter_info)?.setOnClickListener {
            filterLogs(LogLevel.INFO)
        }
        view.findViewById<View>(R.id.btn_filter_warning)?.setOnClickListener {
            filterLogs(LogLevel.WARNING)
        }
        view.findViewById<View>(R.id.btn_filter_error)?.setOnClickListener {
            filterLogs(LogLevel.ERROR)
        }
        view.findViewById<View>(R.id.btn_show_all)?.setOnClickListener {
            showAllLogs()
        }
    }

    private fun refreshLogs() {
        try {
            val process = Runtime.getRuntime().exec("logcat -d -v time")
            val reader = BufferedReader(InputStreamReader(process.inputStream))

            logEntries.clear()

            reader.useLines { lines ->
                lines.forEach { line ->
                    val logEntry = parseLogLine(line)
                    if (logEntry != null) {
                        logEntries.add(logEntry)
                    }
                }
            }

            adapter?.notifyDataSetChanged()
            logger.d("LogsModule", "Refreshed logs: ${logEntries.size} entries")
        } catch (e: Exception) {
            logger.e("LogsModule", "Failed to read logs", e)
        }
    }

    private fun clearLogs() {
        try {
            Runtime.getRuntime().exec("logcat -c")
            logEntries.clear()
            adapter?.notifyDataSetChanged()
            logger.d("LogsModule", "Cleared logs")
        } catch (e: Exception) {
            logger.e("LogsModule", "Failed to clear logs", e)
        }
    }

    private fun filterLogs(level: LogLevel) {
        val filteredEntries = logEntries.filter { it.level == level }
        adapter?.clear()
        adapter?.addAll(filteredEntries)
        adapter?.notifyDataSetChanged()
        logger.d("LogsModule", "Filtered logs by level: $level")
    }

    private fun showAllLogs() {
        adapter?.clear()
        adapter?.addAll(logEntries)
        adapter?.notifyDataSetChanged()
        logger.d("LogsModule", "Showing all logs")
    }

    private fun parseLogLine(line: String): LogEntry? {
        return try {
            // Parse logcat format: "MM-DD HH:MM:SS.XXX PID TID LEVEL TAG: MESSAGE"
            val parts = line.split(" ", limit = 6)
            if (parts.size >= 6) {
                val timestamp = "${parts[0]} ${parts[1]}"
                val pid = parts[2]
                val tid = parts[3]
                val levelStr = parts[4]
                val tagAndMessage = parts[5]

                val colonIndex = tagAndMessage.indexOf(':')
                val tag = if (colonIndex > 0) tagAndMessage.substring(0, colonIndex) else "Unknown"
                val message = if (colonIndex > 0) tagAndMessage.substring(colonIndex + 1).trim() else tagAndMessage

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
        refreshLogs()
    }

    override fun onAttach() {
        logger.d("LogsModule", "Attached to debug drawer")
    }

    override fun onDetach() {
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
    logs: List<LogEntry>,
) : ArrayAdapter<LogEntry>(context, R.layout.item_log, logs) {

    override fun getView(position: Int, convertView: View?, parent: android.view.ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_log, parent, false)
        val log = getItem(position) ?: return view

        view.findViewById<TextView>(R.id.tv_timestamp).text = log.timestamp
        view.findViewById<TextView>(R.id.tv_level).text = log.level.displayName
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
