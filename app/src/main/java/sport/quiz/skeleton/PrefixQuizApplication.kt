package sport.quiz.skeleton

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import sport.quiz.skeleton.di.dataModule
import sport.quiz.skeleton.di.dispatcherModule
import sport.quiz.skeleton.di.viewModule

class PrefixQuizApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dispatcherModule + dataModule + viewModule

        startKoin {
            androidLogger()
            androidContext(this@PrefixQuizApplication)
            modules(appModules)
        }
    }
}