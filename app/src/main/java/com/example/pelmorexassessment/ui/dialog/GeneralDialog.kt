package com.example.pelmorexassessment.ui.dialog

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.pelmorexassessment.R
import com.example.pelmorexassessment.databinding.DialogGeneralBinding

class GeneralDialog private constructor() : DialogFragment() {
    companion object {
        const val TITLE = "TITLE"
        const val CONTENT = "CONTENT"
        const val CONFIRM_TEXT = "CONFIRM_TEXT"
        const val ENABLE_SUCCESS = "ENABLE_SUCCESS"
        fun show(
            fragmentManager: FragmentManager,
            title: String,
            content: String,
            confirmText: String? = null,
            onEnter: (GeneralDialog) -> Unit,
            showSuccess: Boolean = false
        ): GeneralDialog {
            return GeneralDialog().apply {
                setStyle(STYLE_NORMAL, R.style.TransparentBottomSheetStyle)
                arguments = bundleOf(
                    TITLE to title,
                    CONTENT to content,
                    CONFIRM_TEXT to confirmText,
                    ENABLE_SUCCESS to showSuccess
                )
                this.onEnter = onEnter
            }.also {
                it.show(fragmentManager, null)
            }
        }
    }

    private lateinit var binding: DialogGeneralBinding
    private var onEnter: ((GeneralDialog) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setStyle(STYLE_NORMAL,R.style.dialog)
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogGeneralBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConfirm.setOnClickListener {
            this.dismiss()
            onEnter?.invoke(this)
        }
        binding.tvDialogTitle.text = requireArguments().getString(TITLE)
        binding.tvDialogDesc.text = requireArguments().getString(CONTENT)
        requireArguments().getString(CONFIRM_TEXT)?.let {
            binding.btnConfirm.text = it
        }
        requireArguments().getBoolean(ENABLE_SUCCESS).let {
            if (it) {
                binding.ivSuccessIcon.visibility = View.VISIBLE
            } else {
                binding.ivSuccessIcon.visibility = View.GONE
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    fun Int.dp(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }
}