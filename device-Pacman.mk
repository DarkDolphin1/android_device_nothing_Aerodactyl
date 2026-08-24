#
# SPDX-FileCopyrightText: The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from the custom common configuration.
$(call inherit-product, device/nothing/Aerodactyl/device-common.mk)

# Overlays
PRODUCT_PACKAGES += \
    NothingWifiResPacman

# Properties
PRODUCT_COPY_FILES += \
    $(call find-copy-subdir-files,*,$(LOCAL_PATH)/configs/skus/nonPro/,$(TARGET_COPY_OUT_ODM)/etc)

# Inherit the proprietary files
$(call inherit-product, vendor/nothing/Pacman/Pacman-vendor.mk)

PERF_ANIM_OVERRIDE := true