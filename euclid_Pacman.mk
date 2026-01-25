#
# SPDX-FileCopyrightText: The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from the custom device configuration.
$(call inherit-product, device/nothing/Aerodactyl/device-Pacman.mk)

# Inherit from the euclid configuration.
$(call inherit-product, vendor/euclid/config/common_full_phone.mk)

PRODUCT_BRAND := Nothing
PRODUCT_DEVICE := Pacman
PRODUCT_MANUFACTURER := Nothing
PRODUCT_MODEL := A142
PRODUCT_NAME := euclid_Pacman

PRODUCT_GMS_CLIENTID_BASE := android-nothing

PRODUCT_BUILD_PROP_OVERRIDES += \
    BuildDesc="sys_mssi_64_ww_armv82-user 16 BP2A.250605.031.A3 2511282240 release-keys" \
    BuildFingerprint=Nothing/Pacman/Pacman:16/BP2A.250605.031.A3/2511282240:user/release-keys \
    DeviceName=Pacman \
    DeviceProduct=Pacman \
    SystemDevice=Pacman \
    SystemName=Pacman


# GMS
WITH_GMS_COMMS_SUITE := true 
TARGET_INCLUDE_STOCK_ARCORE := true 
TARGET_INCLUDE_PIXEL_LAUNCHER := true 
EUCLID_GAPPS := true

# Google Call recorder and ARcore 
TARGET_SUPPORTS_GOOGLE_RECORDER := true
TARGET_INCLUDE_STOCK_ARCORE := true

# Lets Include Live Wallpapers 
TARGET_INCLUDE_LIVE_WALLPAPERS := true  

# Basic Call Recorder 
TARGET_BUILD_BCR := true 

# DotGallery 
TARGET_BUILD_DOTGALLERY := false 

# UDFPS
TARGET_HAS_UDFPS := true
EXTRA_UDFPS_ANIMATIONS := true

# Extra Packages (Optional)
TARGET_PREBUILT_LAWNICONS := true 
TARGET_BUILD_DOTGALLERY := true 

# Device Specifications (Underscore becomes space in the UI)
EUCLID_DEVICE := Nothing_Phone_2a
EUCLID_PROCESSOR := Mediatek_Dimensity_7200_Pro

# Maintainer Name (Underscore becomes space in the UI)
EUCLID_MAINTAINER := Shravan