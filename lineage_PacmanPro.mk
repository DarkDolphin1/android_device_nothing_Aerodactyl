#
# SPDX-FileCopyrightText: The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from the custom device configuration.
$(call inherit-product, device/nothing/Aerodactyl/device-PacmanPro.mk)

# Inherit from the LineageOS configuration.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

PRODUCT_BRAND := Nothing
PRODUCT_DEVICE := PacmanPro
PRODUCT_MANUFACTURER := Nothing
PRODUCT_MODEL := A142P
PRODUCT_NAME := lineage_PacmanPro

PRODUCT_GMS_CLIENTID_BASE := android-nothing

PRODUCT_BUILD_PROP_OVERRIDES += \
    BuildDesc="sys_mssi_64_ww_armv82-user 16 BP2A.250605.031.A3 2602252039 release-keys" \
    BuildFingerprint=Nothing/PacmanPro/PacmanPro:16/BP2A.250605.031.A3/2602252039:user/release-keys \
    DeviceName=PacmanPro \
    DeviceProduct=PacmanPro \
    SystemDevice=PacmanPro \
    SystemName=PacmanPro

# Use blur 
TARGET_ENABLE_BLUR := true 

# AX-FX instead of dolby or viper 
TARGET_INCLUDE_AXFX := true

# Camera information 
AXION_CAMERA_REAR_INFO := 50,50
AXION_CAMERA_FRONT_INFO := 50

# Maintainer name (underscores become spaces in the UI)
AXION_MAINTAINER := Shravan 

# Processor name (underscores become spaces)
AXION_PROCESSOR := Mediatek_Dimensity_7350_Pro 

BYPASS_CHARGE_SUPPORTED ?= false

# CPU governor support
PERF_GOV_SUPPORTED := true
PERF_DEFAULT_GOV := schedutil
PERF_ANIM_OVERRIDE := false

# GPU
GPU_FREQS_PATH := /sys/devices/platform/28000000.mali/available_frequencies
GPU_MIN_FREQ_PATH := /sys/devices/platform/28000000.mali/hint_min_freq

# High Brightness Mode (HBM)
HBM_SUPPORTED := true

# Flashlight strength
TORCH_STR_SUPPORTED := true

# doze fix
# for devices with doze/sensor related issues 
TARGET_NEEDS_DOZE_FIX := true

TARGET_INCLUDES_LOS_PREBUILTS := false

# Enable or disable ScrollOptimizer globally
persist.sys.perf.scroll_opt = true

# Heavy app handling mode
# 0 - Disable heavy app classification
# 1 - Enable dynamic detection (based on frame duration and buffer load)
# 2 - Treat all apps as heavy for performance
persist.sys.perf.scroll_opt.heavy_app = 2
