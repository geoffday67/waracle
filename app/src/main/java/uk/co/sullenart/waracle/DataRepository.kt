package uk.co.sullenart.waracle

import io.reactivex.Single

class DataRepository(
    private val cakeApi: CakeApi
) {
    // TODO Make this Flowable to we can supply a cached copy (e.g. from Room) immediately, then a live version when it's available
    fun getCakes(): Single<List<CakeEntry>> =
        cakeApi.getCakes()
}