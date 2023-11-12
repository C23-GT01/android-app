package academy.bangkit.trackmate.di

import android.content.Context
import academy.bangkit.trackmate.data.UserRepository
import academy.bangkit.trackmate.data.pref.UserPreference
import academy.bangkit.trackmate.data.pref.dataStore

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}