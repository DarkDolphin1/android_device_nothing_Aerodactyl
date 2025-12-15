#!/usr/bin/env -S PYTHONPATH=../../../tools/extract-utils python3
#
# SPDX-FileCopyrightText: 2024 The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

from extract_utils.file import File
from extract_utils.fixups_blob import (
    blob_fixup,
    blob_fixups_user_type,
)
from extract_utils.fixups_lib import (
    lib_fixups,
    lib_fixups_user_type,
)
from extract_utils.main import (
    ExtractUtils,
    ExtractUtilsModule,
)

namespace_imports = [
    'device/nothing/Aerodactyl',
    'hardware/mediatek',
    'hardware/mediatek/libmtkperf_client',
]


def lib_fixup_vendor_suffix(lib: str, partition: str, *args, **kwargs):
    return f'{lib}_{partition}' if partition == 'vendor' else None


lib_fixups: lib_fixups_user_type = {
    **lib_fixups,
    'vendor.mediatek.hardware.videotelephony@1.0': lib_fixup_vendor_suffix,
}


blob_fixups: blob_fixups_user_type = {
    (
        'system_ext/etc/init/init.vtservice.rc',
        'vendor/etc/init/android.hardware.neuralnetworks-shim-service-mtk.rc'
    ): blob_fixup()
        .regex_replace('start', 'enable'),
    'system_ext/lib64/libimsma.so': blob_fixup()
        .replace_needed('libsink.so', 'libsink-mtk.so'),
    'system_ext/priv-app/ImsService/ImsService.apk': blob_fixup()
        .apktool_patch('blob-patches/ImsService'),
    'vendor/bin/hw/android.hardware.graphics.composer@3.1-service': blob_fixup()
        .replace_needed('android.hardware.graphics.composer@2.1-resources.so', 'android.hardware.graphics.composer@2.1-resources-v34.so'),
    'vendor/bin/hw/android.hardware.media.c2@1.2-mediatek-64b': blob_fixup()
        .add_needed('libstagefright_foundation-v33.so'),
    'vendor/bin/hw/android.hardware.security.keymint@2.0-service.trustonic': blob_fixup()
        .add_needed('android.hardware.security.rkp-V2-ndk.so'),
    'vendor/bin/hw/mt6886/camerahalserver': blob_fixup()
        .add_needed('libcamera_metadata_shim.so'),
    'vendor/etc/vintf/manifest/manifest_media_c2_V1_2_default.xml': blob_fixup()
        .regex_replace('1.1', '1.2'),
    (
        'vendor/etc/libnfc-hal-st.conf',
        'vendor/etc/libnfc-hal-st-st54j.conf'
    ): blob_fixup()
        .regex_replace('# STNFC_FW_BIN_NAME', 'STNFC_FW_BIN_NAME')
        .regex_replace('# STNFC_FW_CONF_NAME', 'STNFC_FW_CONF_NAME')
        .regex_replace('STNFC_FW_DEBUG_ENABLED=1', 'STNFC_FW_DEBUG_ENABLED=0'),
    'vendor/lib64/hw/audio.primary.mediatek.so': blob_fixup()
        .add_needed('libstagefright_foundation-v33.so')
        .replace_needed('libalsautils.so', 'libalsautils-v33.so')
        .replace_needed('libtinyxml2.so', 'libtinyxml2-bp2a.so')
        .binary_regex_replace(b'A2dpsuspendonly', b'A2dpSuspended\x00\x00')
        .binary_regex_replace(b'BTAudiosuspend', b'A2dpSuspended\x00'),
    'vendor/lib64/hw/hwcomposer.mtk_common.so': blob_fixup()
        .add_needed('libprocessgroup_shim.so')
        .binary_regex_replace(b'NTFingerprintDimLayer', b'SurfaceView[UdfpsCont'),
    (
        'vendor/lib64/hw/mt6886/vendor.mediatek.hardware.pq_aidl-impl.so',
        'vendor/lib64/mt6886/libmmlpqImpl.so',
        'vendor/lib64/libpqxmlparser.so'
    ): blob_fixup()
        .replace_needed('libtinyxml2.so', 'libtinyxml2-bp2a.so'),
    (
        'vendor/lib64/mt6886/libcam.hal3a.so',
        'vendor/lib64/mt6886/libcam.hal3a.ctrl.so',
        'vendor/lib64/mt6886/libmtkcam_cputrack.so',
        'vendor/lib64/mt6886/libmtkcam_request_requlator.so'
    ): blob_fixup()
        .add_needed('libprocessgroup_shim.so'),
    'vendor/lib64/mt6886/libmorpho_video_stabilizer.so': blob_fixup()
        .add_needed('libutils.so'),
    'vendor/lib64/mt6886/libneuralnetworks_sl_driver_mtk_prebuilt.so': blob_fixup()
        .clear_symbol_version('AHardwareBuffer_allocate')
        .clear_symbol_version('AHardwareBuffer_createFromHandle')
        .clear_symbol_version('AHardwareBuffer_describe')
        .clear_symbol_version('AHardwareBuffer_getNativeHandle')
        .clear_symbol_version('AHardwareBuffer_lock')
        .clear_symbol_version('AHardwareBuffer_release')
        .clear_symbol_version('AHardwareBuffer_unlock')
        .add_needed('libbase_shim.so'),
    (
        'vendor/lib64/libcodec2_vpp_AIMEMC_plugin.so',
        'vendor/lib64/libcodec2_vpp_AISR_plugin.so'
    ): blob_fixup()
        .replace_needed('android.hardware.graphics.allocator-V1-ndk.so', 'android.hardware.graphics.allocator-V2-ndk.so')
        .replace_needed('android.hardware.graphics.common-V3-ndk.so', 'android.hardware.graphics.common-V7-ndk.so'),
    'vendor/lib64/libneuron_adapter_mc.so': blob_fixup()
        .clear_symbol_version('AHardwareBuffer_describe'),
    'vendor/bin/hw/mtkfusionrild': blob_fixup()
        .add_needed('libutils-v33.so'),
    'vendor/lib64/libnvram.so': blob_fixup()
        .add_needed('libbase_shim.so'),
    'vendor/lib64/vendor.mediatek.hardware.pq_aidl-V1-ndk.so': blob_fixup()
        .replace_needed('android.hardware.graphics.common-V3-ndk.so', 'android.hardware.graphics.common-V7-ndk.so'),
}  # fmt: skip

module = ExtractUtilsModule(
    'Aerodactyl',
    'nothing',
    blob_fixups=blob_fixups,
    lib_fixups=lib_fixups,
    namespace_imports=namespace_imports,
)

if __name__ == '__main__':
    utils = ExtractUtils.device(module)
    utils.run()
