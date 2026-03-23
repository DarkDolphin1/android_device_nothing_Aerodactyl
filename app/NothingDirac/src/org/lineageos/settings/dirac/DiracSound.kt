
/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.dirac

import android.media.audiofx.AudioEffect

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.UUID

class DiracSound(priority: Int, audioSession: Int) : AudioEffect(EFFECT_TYPE_NULL, EFFECT_TYPE_DIRACSOUND, priority, audioSession) {

    companion object {
        private const val DIRACSOUND_PARAM_EQ_LEVEL = 2
        private const val DIRACSOUND_PARAM_VOLUME = 5
        private const val DIRACSOUND_PARAM_ENABLE = 6

        private val EFFECT_TYPE_DIRACSOUND: UUID = UUID.fromString("ae737c63-f2c0-5457-909e-1e940c91b67b")
        private const val TAG = "DiracSound"
    }

    fun setEnabled(enable: Int) {
        checkStatus(setParameter(DIRACSOUND_PARAM_ENABLE, enable))
    }

    fun setVolume(volume: Int) {
        checkStatus(setParameter(DIRACSOUND_PARAM_VOLUME, volume))
    }

    fun setLevel(band: Int, level: Float) {
        val value = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder()).putFloat(level).array()
        checkStatus(setParameter(intArrayOf(DIRACSOUND_PARAM_EQ_LEVEL, band), value))
    }
}
