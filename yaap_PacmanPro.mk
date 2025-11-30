#
# SPDX-FileCopyrightText: The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from the custom device configuration.
$(call inherit-product, device/nothing/Aerodactyl/device-PacmanPro.mk)

# Inherit from the YAAP configuration.
$(call inherit-product, vendor/yaap/config/common_full_phone.mk)

# YAAP Flags
TARGET_ENABLE_BLUR := false
PERF_ANIM_OVERRIDE := true

PRODUCT_BRAND := Nothing
PRODUCT_DEVICE := PacmanPro
PRODUCT_MANUFACTURER := Nothing
PRODUCT_MODEL := A142P
PRODUCT_NAME := yaap_PacmanPro

PRODUCT_GMS_CLIENTID_BASE := android-nothing

PRODUCT_BUILD_PROP_OVERRIDES += \
    BuildDesc="sys_mssi_64_ww_armv82-user 16 BP2A.250605.031.A3 2511201812 release-keys" \
    BuildFingerprint=Nothing/PacmanPro/PacmanPro:16/BP2A.250605.031.A3/2511201812:user/release-keys \
    DeviceName=PacmanPro \
    DeviceProduct=PacmanPro \
    SystemDevice=PacmanPro \
    SystemName=PacmanPro
