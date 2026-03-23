/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

import org.lineageos.settings.dirac.DiracUtils

class BootCompletedReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "NothingDirac"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Received boot completed intent")
        DiracUtils.initialize(context)
    }
}
