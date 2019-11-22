package uk.co.sullenart.waracle

import dagger.Component
import uk.co.sullenart.waracle.cakes.CakesFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(fragment: BaseFragment)
    fun inject(fragment: CakesFragment)
}
