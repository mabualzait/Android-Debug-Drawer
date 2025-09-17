package com.debugdrawer.modules

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.debugdrawer.R
import com.debugdrawer.utils.Logger
import javax.inject.Inject

/**
 * Debug module for clipboard operations.
 * Allows copying and pasting text for debugging purposes.
 */
class ClipboardModule @Inject constructor(
    private val context: Context,
    private val logger: Logger
) : DebugModule {

    override val name: String = "clipboard"
    override val title: String = "Clipboard Tools"
    override val description: String = "Copy and paste text for debugging"
    override val priority: Int = 6

    private lateinit var clipboardManager: ClipboardManager
    private lateinit var editText: EditText
    private lateinit var tvClipboardContent: TextView

    override fun createView(): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.module_clipboard, null)

        clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        setupClipboardTools(view)

        logger.d("ClipboardModule", "Created clipboard tools view")
        return view
    }

    private fun setupClipboardTools(view: View) {
        editText = view.findViewById(R.id.et_clipboard_input)
        tvClipboardContent = view.findViewById(R.id.tv_clipboard_content)

        // Copy button
        view.findViewById<Button>(R.id.btn_copy).setOnClickListener {
            copyToClipboard()
        }

        // Paste button
        view.findViewById<Button>(R.id.btn_paste).setOnClickListener {
            pasteFromClipboard()
        }

        // Clear button
        view.findViewById<Button>(R.id.btn_clear).setOnClickListener {
            clearInput()
        }

        // Refresh button
        view.findViewById<Button>(R.id.btn_refresh).setOnClickListener {
            refreshClipboardContent()
        }

        // Load current clipboard content
        refreshClipboardContent()
    }

    private fun copyToClipboard() {
        val text = editText.text.toString()
        if (text.isNotEmpty()) {
            val clip = ClipData.newPlainText("Debug Text", text)
            clipboardManager.setPrimaryClip(clip)
            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
            logger.d("ClipboardModule", "Copied text to clipboard: $text")
        } else {
            Toast.makeText(context, "Please enter some text to copy", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pasteFromClipboard() {
        if (clipboardManager.hasPrimaryClip()) {
            val clipData = clipboardManager.primaryClip
            if (clipData != null && clipData.itemCount > 0) {
                val text = clipData.getItemAt(0).text.toString()
                editText.setText(text)
                Toast.makeText(context, "Text pasted from clipboard", Toast.LENGTH_SHORT).show()
                logger.d("ClipboardModule", "Pasted text from clipboard: $text")
            }
        } else {
            Toast.makeText(context, "Clipboard is empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInput() {
        editText.setText("")
        Toast.makeText(context, "Input cleared", Toast.LENGTH_SHORT).show()
        logger.d("ClipboardModule", "Cleared input")
    }

    private fun refreshClipboardContent() {
        if (clipboardManager.hasPrimaryClip()) {
            val clipData = clipboardManager.primaryClip
            if (clipData != null && clipData.itemCount > 0) {
                val text = clipData.getItemAt(0).text.toString()
                tvClipboardContent.text = text
                logger.d("ClipboardModule", "Refreshed clipboard content: $text")
            } else {
                tvClipboardContent.text = "Clipboard is empty"
            }
        } else {
            tvClipboardContent.text = "Clipboard is empty"
        }
    }

    override fun onShow() {
        refreshClipboardContent()
    }

    override fun onAttach() {
        logger.d("ClipboardModule", "Attached to debug drawer")
    }

    override fun onDetach() {
        logger.d("ClipboardModule", "Detached from debug drawer")
    }
}
