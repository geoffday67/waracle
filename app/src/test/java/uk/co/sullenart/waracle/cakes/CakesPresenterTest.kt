package uk.co.sullenart.waracle.cakes

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import uk.co.sullenart.waracle.CakeEntry
import uk.co.sullenart.waracle.DataRepository

class CakesPresenterTest {
    val mockView: CakesPresenter.View = mockk(relaxUnitFun = true)
    val mockDataRepository: DataRepository = mockk()

    lateinit var presenter: CakesPresenter

    companion object {
        @BeforeClass
        @JvmStatic
        fun init() {
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }

    @Before
    fun setup() {
        clearAllMocks()
        presenter = CakesPresenter(mockDataRepository).apply {
            view = mockView
        }
    }

    @Test
    fun `Starting the presenter should refresh the cakes in order without duplicates`() {
        val cakeA = CakeEntry("a")
        val cakeB = CakeEntry("b")
        val cakeC = CakeEntry("c")

        val rawCakes = listOf(cakeC, cakeB, cakeB, cakeA)
        val showCakes = listOf(cakeA, cakeB, cakeC)

        every { mockDataRepository.getCakes() } returns Single.just(rawCakes)

        presenter.start()

        verify { mockView.showCakes(showCakes) }
    }

    @Test
    fun `Starting the presenter and load fails an error is shown`() {
        val error = Exception()
        every { mockDataRepository.getCakes() } returns Single.error(error)

        presenter.start()

        verify { mockView.showError(error) }
    }
}