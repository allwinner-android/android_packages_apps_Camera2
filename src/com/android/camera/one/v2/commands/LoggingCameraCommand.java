/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.camera.one.v2.commands;

import android.hardware.camera2.CameraAccessException;

import com.android.camera.debug.Log;
import com.android.camera.one.v2.camera2proxy.CameraCaptureSessionClosedException;

/**
 * Wraps a {@link CameraCommand} with logging.
 */
public class LoggingCameraCommand implements CameraCommand {
    private static final Log.Tag TAG = new Log.Tag("CameraCommand");
    private final CameraCommand mCommand;
    private final String mName;

    public LoggingCameraCommand(CameraCommand command, String name) {
        mCommand = command;
        mName = name;
    }

    @Override
    public void run() throws InterruptedException, CameraAccessException,
            CameraCaptureSessionClosedException {
        Log.v(TAG, String.format("Executing Command: %s: START", mName));
        try {
            mCommand.run();
        } catch (Exception e) {
            Log.e(TAG, String.format("Executing Command: %s: Exception: ", mName));
            e.printStackTrace();
            throw e;
        }
        Log.v(TAG, String.format("Executing Command: %s: END", mName));
    }
}