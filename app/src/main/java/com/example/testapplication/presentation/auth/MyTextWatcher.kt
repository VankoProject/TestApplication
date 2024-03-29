package com.example.testapplication.presentation.auth

import android.text.Editable
import android.text.TextWatcher

abstract class MyTextWatcher(): TextWatcher {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable?) {}
}