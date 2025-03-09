package app.dbalava.klear

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            AndroidLogger()
            androidContext(this@App)
            modules(appModule, dataModule, domainModule)
        }
    }
}