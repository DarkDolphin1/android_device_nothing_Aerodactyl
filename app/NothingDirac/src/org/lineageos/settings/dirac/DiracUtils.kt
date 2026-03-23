
/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.dirac

import android.content.Context
import android.content.SharedPreferences
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.media.session.PlaybackState
import android.os.Handler
import android.os.Looper
import android.os.UserHandle
import android.os.SystemClock
import android.os.SystemProperties
import android.util.Log
import android.view.KeyEvent

import androidx.preference.PreferenceManager

class DiracUtils {

    private var mContext: Context? = null
    private var mMediaSessionManager: MediaSessionManager? = null
    private var mHandler: Handler = Handler(Looper.getMainLooper())

    companion object {
        private var mDiracSound: DiracSound? = null
        private var mInitialized = false
        private const val TAG = "DiracUtils"

        fun initialize(context: Context) {
            if (!mInitialized) {
                mDiracSound = DiracSound(0, 0)

                val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)

                val savedScenario = sharedPrefs.getString("dirac_scenario_pref", "MUSIC")
                setScenario(savedScenario)

                val savedPreset = sharedPrefs.getString("dirac_preset_pref", "0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0")
                setLevel(savedPreset)

                mInitialized = true
            }
        }

        fun isDiracEnabled(context: Context): Boolean {
            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            return sharedPrefs.getBoolean("dirac_enable", false)
        }

        fun setEnabled(enable: Boolean) {
            Log.i(TAG, "setEnabled: $enable")

            if (mDiracSound == null) {
                Log.e(TAG, "setEnabled: mDiracSound is NULL! TERRAIN TERRAIN, PULL UP.")
            }

            mDiracSound?.let {
                try {
                    it.setEnabled(if (enable) 1 else 0)
                    Log.i(TAG, "HAL accepted setEnabled")
                } catch (e: Exception) {
                    Log.e(TAG, "HAL rejected setEnabled")
                }
            } ?: return

            if (enable) {
                DiracUtils().refreshPlaybackIfNecessary()
            }

            try {
                val value = if (enable) "1.000000" else "0.000000"
                SystemProperties.set("persist.sys.dirac.enable", value)
                Log.i(TAG, "Set Dirac enable to $value")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to set Dirac enable property", e)
            }
        }

        fun setScenario(scenario: String?) {
            try {
                SystemProperties.set("persist.sys.dirac.scenario", scenario)
                Log.i(TAG, "Set Dirac scenario to $scenario")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to set Dirac scenario property", e)
            }
        }

        fun setLevel(preset: String?) {
            Log.i(TAG, "setLevel: $preset")

            val dirac = mDiracSound
            if (mDiracSound == null) {
                Log.e(TAG, "setLevel: mDiracSound is NULL! TERRAIN TERRAIN, PULL UP.")
                return
            }

            val level = preset?.split("\\s*,\\s*".toRegex()) ?: return

            level.forEachIndexed { band, value ->
                try {
                    dirac?.setLevel(band, value.toFloat())
                    Log.i(TAG, "HAL accepted write for band $band")
                } catch (e: Exception) {
                    Log.e(TAG, "HAL rejected write for band $band")
                }
            }
        }

        fun setVolume(level: Int) {
            Log.i(TAG, "setVolume: $level")

            if (mDiracSound == null) {
                Log.e(TAG, "setVolume: mDiracSound is NULL! TERRAIN TERRAIN, PULL UP.")
            }

            mDiracSound?.let {
                try {
                    it.setVolume(level)
                    Log.i(TAG, "HAL accepted setVolume")
                } catch (e: Exception) {
                    Log.e(TAG, "HAL rejected setVolume")
                }
            }

            try {
                val floatString = "%d.000000".format(level)
                SystemProperties.set("persist.sys.dirac.volume", floatString)
                Log.i(TAG, "Set Dirac volume to $floatString")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to set Dirac volume property", e)
            }
        }
    }

    fun refreshPlaybackIfNecessary() {
        if (mMediaSessionManager == null) {
            mMediaSessionManager = mContext?.getSystemService(Context.MEDIA_SESSION_SERVICE) as? MediaSessionManager
        }
        val sessions = mMediaSessionManager?.getActiveSessionsForUser(null, UserHandle.ALL) ?: return
        sessions.firstOrNull { PlaybackState.STATE_PLAYING == getMediaControllerPlaybackState(it) }
            ?.let { triggerPlayPause(it) }
    }

    fun triggerPlayPause(controller: MediaController) {
        val whenTime = SystemClock.uptimeMillis()
        val evDownPause = KeyEvent(whenTime, whenTime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PAUSE, 0)
        val evUpPause = KeyEvent.changeAction(evDownPause, KeyEvent.ACTION_UP)
        val evDownPlay = KeyEvent(whenTime, whenTime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY, 0)
        val evUpPlay = KeyEvent.changeAction(evDownPlay, KeyEvent.ACTION_UP)
        mHandler.post { controller.dispatchMediaButtonEvent(evDownPause) }
        mHandler.postDelayed({ controller.dispatchMediaButtonEvent(evUpPause) }, 20)
        mHandler.postDelayed({ controller.dispatchMediaButtonEvent(evDownPlay) }, 1000)
        mHandler.postDelayed({ controller.dispatchMediaButtonEvent(evUpPlay) }, 1020)
    }

    fun getMediaControllerPlaybackState(controller: MediaController?): Int {
        return controller?.playbackState?.state ?: PlaybackState.STATE_NONE
    }
}
