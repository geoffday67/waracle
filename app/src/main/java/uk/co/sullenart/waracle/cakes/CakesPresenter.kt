package uk.co.sullenart.waracle.cakes

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import uk.co.sullenart.waracle.BasePresenter
import uk.co.sullenart.waracle.BaseView
import uk.co.sullenart.waracle.CakeEntry
import uk.co.sullenart.waracle.DataRepository
import javax.inject.Inject

class CakesPresenter @Inject constructor(
    private val dataRepository: DataRepository
) : BasePresenter() {
    lateinit var view: View

    fun refreshCakes() {
        add(
            dataRepository.getCakes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { view.showCakes(it) },
                    onError = { view.showError(it) })
        )
    }

    interface View : BaseView {
        fun showCakes(cakes: List<CakeEntry>)
    }
}