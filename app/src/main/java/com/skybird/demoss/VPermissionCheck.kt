package com.skybird.demoss

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.VpnService
import androidx.activity.result.contract.ActivityResultContract
import com.github.shadowsocks.preference.DataStore
import com.github.shadowsocks.utils.Key

/**
 * Date：2022/5/24
 * Describe:
 */
class VPermissionCheck : ActivityResultContract<Void?, Boolean>() {
    private var cachedIntent: Intent? = null

    override fun getSynchronousResult(
        context: Context,
        input: Void?
    ): ActivityResultContract.SynchronousResult<Boolean>? {
        if (DataStore.serviceMode == Key.modeVpn) VpnService.prepare(context)?.let { intent ->
            cachedIntent = intent
            return null
        }
        return ActivityResultContract.SynchronousResult(false)
    }

    override fun createIntent(context: Context, input: Void?) =
        cachedIntent!!.also { cachedIntent = null }

    override fun parseResult(resultCode: Int, intent: Intent?) =
        resultCode != Activity.RESULT_OK
}