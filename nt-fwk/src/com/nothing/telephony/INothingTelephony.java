/*
 * Copyright (C) 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.nothing.telephony;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface INothingTelephony extends IInterface {
    String DESCRIPTOR = "com.nothing.telephony.INothingTelephony";

    Bundle generalGetter(String str, Bundle bundle) throws RemoteException;
    Bundle generalSetter(String str, Bundle bundle) throws RemoteException;

    abstract class Stub extends Binder implements INothingTelephony {
        public static INothingTelephony asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }

            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && iin instanceof INothingTelephony) {
                return (INothingTelephony) iin;
            }

            return null;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
