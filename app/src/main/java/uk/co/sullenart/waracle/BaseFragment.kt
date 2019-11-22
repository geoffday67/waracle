package uk.co.sullenart.waracle

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import javax.inject.Inject

open class BaseFragment : BaseView, Fragment() {
    @Inject
    protected lateinit var dataRepository: DataRepository

    protected lateinit var safeContext: Context

    override fun onAttach(context: Context) {
        safeContext = context
        (activity?.application as MainApplication).component.inject(this)

        super.onAttach(context)
    }

    override fun showError(t: Throwable) {
        Toast.makeText(safeContext, t.message, LENGTH_LONG).show()
    }
}
