package com.emrexample.mobile.dagger

import android.app.Application
import com.emrexample.mobile.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        ApiModule::class,
        RoomModule::class,
        ViewModelModule::class,
        AppModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
    override fun inject(application: BaseApplication)
}