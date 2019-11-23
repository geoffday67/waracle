package uk.co.sullenart.waracle.cakes

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import uk.co.sullenart.waracle.BasePresenter
import uk.co.sullenart.waracle.BaseView
import uk.co.sullenart.waracle.CakeEntry
import uk.co.sullenart.waracle.DataRepository
import javax.inject.Inject

class CakesPresenter @Inject constructor(
    private val dataRepository: DataRepository
) : BasePresenter() {
    lateinit var view: View

    fun start() {
        view.showRefreshing(true)
        refreshCakes()
    }

    fun refreshCakes() {
        add(
            dataRepository.getCakes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { view.showRefreshing(false) }
                .flattenAsFlowable { it }
                .distinct { it.title }
                .toSortedList { c1, c2 -> c1.title.compareTo(c2.title) }
                .subscribeBy(
                    onSuccess = { view.showCakes(it) },
                    onError = { view.showError(it) })
        )
    }

    fun onCakeClicked(cake: CakeEntry) {
        view.showDescription(cake)
    }

    interface View : BaseView {
        fun showRefreshing(show: Boolean)
        fun showCakes(cakes: List<CakeEntry>)
        fun showDescription(cake: CakeEntry)
    }
}