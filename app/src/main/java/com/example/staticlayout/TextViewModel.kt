package com.example.staticlayout

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.core.text.set
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thedeanda.lorem.Lorem
import com.thedeanda.lorem.LoremIpsum

class TextViewModel : ViewModel() {
    private val lorem: Lorem = LoremIpsum.getInstance( )
    private val _list = MutableLiveData<List<CharSequence>>().apply {
        value = (1..200).toList().map { SpannableString(it.toString() + ": " + lorem.getWords(50)).apply {
            setSpan(StyleSpan(Typeface.BOLD_ITALIC), 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(StyleSpan(Typeface.ITALIC), 40, 50, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(StyleSpan(Typeface.BOLD), 70, 80, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(RelativeSizeSpan(1.2f), length/2, length/2+10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(UnderlineSpan(), length - 10, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        } }
    }

    val list: LiveData<List<CharSequence>> = _list
}