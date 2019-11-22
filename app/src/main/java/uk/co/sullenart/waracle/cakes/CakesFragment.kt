package uk.co.sullenart.waracle.cakes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_cakes.*
import uk.co.sullenart.waracle.BaseFragment
import uk.co.sullenart.waracle.CakeEntry
import uk.co.sullenart.waracle.MainApplication
import uk.co.sullenart.waracle.R
import javax.inject.Inject

class CakesFragment : BaseFragment(), CakesPresenter.View {
    @Inject lateinit var presenter: CakesPresenter

    private val cakesAdapter = CakesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.application as MainApplication).component.inject(this)
        presenter.view = this

        return inflater.inflate(R.layout.fragment_cakes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cakes_list.adapter = cakesAdapter
        presenter.refreshCakes()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.stop()
    }

    override fun showCakes(cakes: List<CakeEntry>) {
        cakesAdapter.setEntries(cakes)
    }
}