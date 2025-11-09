#
# SPDX-FileCopyrightText: The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

# Include the common BoardConfig.
include device/nothing/Aerodactyl/BoardConfig-common.mk

# Bootloader
TARGET_BOOTLOADER_BOARD_NAME := Pacman

# Properties
TARGET_PRODUCT_PROP += $(DEVICE_PATH)/Pacman/product.prop
TARGET_SYSTEM_EXT_PROP += $(DEVICE_PATH)/Pacman/system_ext.prop
TARGET_VENDOR_PROP += $(DEVICE_PATH)/Pacman/vendor.prop

# Inherit the proprietary files
include vendor/nothing/Pacman/BoardConfigVendor.mk
