package com.example.pelmorexassessment.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.pelmorexassessment.R
import com.example.pelmorexassessment.databinding.ViewTitleEdittextBinding

class TitleEditTextView : FrameLayout {

    lateinit var dataBinding: ViewTitleEdittextBinding
    lateinit var customPhoneRegionCodeContainer: View
    lateinit var phoneRegionCode: TextView

    lateinit var inputText: EditText
    lateinit var inputTextTitle: TextView
    lateinit var frameOfEditBox: View
    var customInputType = CustomInputType.NotSpecify

    constructor(context: Context) :
            super(context) {
        customInit(context)
    }

    constructor(context: Context, attrs: AttributeSet) :
            this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        customInit(context, attrs)
    }

    private fun customInit(context: Context, attrs: AttributeSet? = null) {
        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_title_edittext,
            this,
            true
        )

        inputText = dataBinding.vInputText
        inputTextTitle = dataBinding.vInputTextTitle
        frameOfEditBox = dataBinding.vFrameOfEditText
        customPhoneRegionCodeContainer = dataBinding.vPhoneRegionCodeContainer
        phoneRegionCode = dataBinding.vPhoneRegionCode

        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.TitleEditTextView)
            if (a.hasValue(R.styleable.TitleEditTextView_titleEditTitleText)) {
                val myString =
                    a.getString(R.styleable.TitleEditTextView_titleEditTitleText)
                dataBinding.vInputTextTitle.text = myString
            }
            if (a.hasValue(R.styleable.TitleEditTextView_titleEditHintsText)) {
                val myString =
                    a.getString(R.styleable.TitleEditTextView_titleEditHintsText)
                dataBinding.vInputText.hint = myString
            }

            if (a.hasValue(R.styleable.TitleEditTextView_titleEditInputType)) {
                val myInt = a.getInt(R.styleable.TitleEditTextView_titleEditInputType, 99)
                customInputType = CustomInputType.getByValue(myInt)
                when (customInputType) {
                    CustomInputType.Normal -> inputText.inputType =
                        InputType.TYPE_CLASS_TEXT
                    CustomInputType.Password -> inputText.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    CustomInputType.PhoneNumber -> {
                        inputText.inputType =
                            InputType.TYPE_CLASS_NUMBER
                        customPhoneRegionCodeContainer.visibility = View.VISIBLE
                    }
                    CustomInputType.NotSpecify -> inputText.inputType =
                        InputType.TYPE_CLASS_TEXT
                    CustomInputType.Email -> inputText.inputType =
                        InputType.TYPE_CLASS_TEXT
                    CustomInputType.PasswordNoErrorCheck -> inputText.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    CustomInputType.DropDown -> inputText.inputType =
                        InputType.TYPE_CLASS_TEXT
                    CustomInputType.PhoneNumberNoErrorCheck -> inputText.inputType =
                        InputType.TYPE_CLASS_NUMBER
                }
            } else {
                inputText.inputType = InputType.TYPE_CLASS_TEXT
            }
            // input max length
            if (a.hasValue(R.styleable.TitleEditTextView_titleEditMaxLength)) {
                val myInt = a.getInt(R.styleable.TitleEditTextView_titleEditMaxLength, 12)
                inputText.filters =
                    arrayOf<InputFilter>(object : InputFilter.LengthFilter(myInt) {})
            }

            // Custom focus action
            val nextFocusId = a.getResourceId(R.styleable.TitleEditTextView_nextFocus, 0)
            Log.d("GFLEditText", "nextFocusId: $nextFocusId")
            if (nextFocusId > 0) {
                inputText.imeOptions = EditorInfo.IME_ACTION_NEXT
                inputText.setOnEditorActionListener { textView, i, keyEvent ->
                    if (i == EditorInfo.IME_ACTION_NEXT) {
                        val nextEditText = rootView.findViewById<View>(nextFocusId)
                        Log.d("GFLEditText", "nextEditText: ${nextEditText.id}")
                        when (nextEditText) {
                            is TitleEditTextView -> {
                                if (nextEditText.customInputType == CustomInputType.DropDown) {
                                    nextEditText.callOnClick()
                                } else {
                                    nextEditText.focus()
                                }
                            }
                            is TitleEditTextView -> {
                                nextEditText.focus()
                            }
                        }
                    }
                    false
                }
            }

            setupListener()
            a.recycle()
        }
    }

    fun setupListener() {
        dataBinding.vFrameOfEditText.setOnClickListener {
            inputText.requestFocus()
        }
    }

    fun focus() {
        Log.d("GFLEditText", "$id focus()")
        Handler(Looper.getMainLooper()).postDelayed({
            if (inputText.isAttachedToWindow) {
                inputText.requestFocus()
            }
        }, 180) // Programming delay to avoid Android request focus / request layout crash
    }

    enum class CustomInputType(val type: Int) {
        Normal(2),
        Password(1),
        PhoneNumber(0),
        PasswordNoErrorCheck(3),
        Email(4),
        DropDown(5),
        PhoneNumberNoErrorCheck(6),
        NotSpecify(99);

        companion object {
            fun getByValue(value: Int) = values().first { it.type == value }
        }
    }
}