package com.abualzait.debugdrawer.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.abualzait.debugdrawer.R
import com.abualzait.debugdrawer.utils.Logger
import com.abualzait.debugdrawer.utils.NetworkInterceptor
import com.abualzait.debugdrawer.utils.NetworkLog
import javax.inject.Inject

/**
 * Debug module that displays network request logs.
 */
class NetworkModule @Inject constructor(
    private val context: Context,
    private val logger: Logger,
    private val networkInterceptor: NetworkInterceptor,
) : DebugModule {

    override val name: String = "network"
    override val title: String = "Network Logs"
    override val description: String = "View HTTP request and response logs"
    override val priority: Int = 2

    private var listView: ListView? = null
    private var adapter: NetworkLogAdapter? = null

    override fun createView(): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.module_network, null)

        setupNetworkLogs(view)

        logger.d("NetworkModule", "Created network logs view")
        return view
    }

    private fun setupNetworkLogs(view: View) {
        listView = view.findViewById(R.id.lv_network_logs)
        adapter = NetworkLogAdapter(context, networkInterceptor.getNetworkLogs())
        listView?.adapter = adapter

        // Set up clear button
        view.findViewById<View>(R.id.btn_clear_logs)?.setOnClickListener {
            networkInterceptor.clearLogs()
            adapter?.clear()
            adapter?.addAll(networkInterceptor.getNetworkLogs())
            adapter?.notifyDataSetChanged()
            logger.d("NetworkModule", "Cleared network logs")
        }

        // Set up refresh button
        view.findViewById<View>(R.id.btn_refresh_logs)?.setOnClickListener {
            refreshLogs()
        }
    }

    private fun refreshLogs() {
        adapter?.clear()
        adapter?.addAll(networkInterceptor.getNetworkLogs())
        adapter?.notifyDataSetChanged()
        logger.d("NetworkModule", "Refreshed network logs")
    }

    override fun onShow() {
        refreshLogs()
    }

    override fun onAttach() {
        logger.d("NetworkModule", "Attached to debug drawer")
    }

    override fun onDetach() {
        logger.d("NetworkModule", "Detached from debug drawer")
    }
}

/**
 * Adapter for displaying network logs in a ListView.
 */
private class NetworkLogAdapter(
    context: Context,
    logs: List<NetworkLog>,
) : ArrayAdapter<NetworkLog>(context, R.layout.item_network_log, logs) {

    override fun getView(position: Int, convertView: View?, parent: android.view.ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_network_log, parent, false)
        val log = getItem(position) ?: return view

        view.findViewById<TextView>(R.id.tv_method).text = log.method
        view.findViewById<TextView>(R.id.tv_url).text = log.url
        view.findViewById<TextView>(R.id.tv_status).text = when (log.type) {
            com.abualzait.debugdrawer.utils.NetworkLogType.REQUEST -> "→"
            com.abualzait.debugdrawer.utils.NetworkLogType.RESPONSE -> "← ${log.responseCode}"
            com.abualzait.debugdrawer.utils.NetworkLogType.ERROR -> "✗ ${log.responseMessage}"
        }
        view.findViewById<TextView>(R.id.tv_duration).text = "${log.duration}ms"
        view.findViewById<TextView>(R.id.tv_timestamp).text = java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault())
            .format(java.util.Date(log.timestamp))

        return view
    }
}
