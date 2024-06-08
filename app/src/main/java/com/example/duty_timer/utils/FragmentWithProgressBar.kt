package com.example.duty_timer.utils

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment

open class FragmentWithProgressBar : Fragment() {

    private lateinit var root: ViewGroup
    private lateinit var progressBarView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root = requireView() as ViewGroup
    }

    protected fun <T> updateProgressBar(result: ResultTask<T>, progressBarView: View) {
        this.progressBarView = progressBarView

        when (result) {
            is SuccessTask -> setStatusCompleted()
            is PendingTask -> setStatusPending()
            else -> setStatusCompleted()
        }
    }

    private fun setStatusPending() {
        root.children.forEach {
            it.visibility = View.INVISIBLE
        }
        progressBarView.visibility = View.VISIBLE
    }

    private fun setStatusCompleted() {
        root.children.forEach {
            it.visibility = View.VISIBLE
        }
        progressBarView.visibility = View.INVISIBLE
    }

}