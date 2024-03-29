package com.skybird.demoss

import android.app.Application
import com.github.shadowsocks.Core
import com.github.shadowsocks.database.Profile
import com.github.shadowsocks.database.ProfileManager
import com.github.shadowsocks.preference.DataStore
import timber.log.Timber

/**
 * Date：2022/5/24
 * Describe:
 */
class App : Application() {
    companion object{
        lateinit var mApp: App
        private set
    }
    override fun onCreate() {
        super.onCreate()
        Core.init(this, MainActivity::class)
        selectedProfile()
        mApp=this
    }


    private fun selectedProfile() {
        var profile = ProfileManager.getProfile(DataStore.profileId)
        if (profile == null) {
            profile = ProfileManager.createProfile(Profile())
            profile.name = "test"
            DataStore.profileId = profile.id
        }
        ProfileManager.updateProfile(profile)
    }
}