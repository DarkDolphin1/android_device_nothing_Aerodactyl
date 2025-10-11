/*
 * Copyright (C) 2021-2025 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#include <libinit_variant.h>

#include "vendor_init.h"

static const variant_info_t PacmanProEEA_info = {
    .sku_value = "EEA",

    .name = "PacmanProEEA",
    .build_fingerprint = "Nothing/PacmanProEEA/PacmanPro:16/BP2A.250605.031.A3/2509172235:user/release-keys",
};

static const variant_info_t PacmanProIND_info = {
    .sku_value = "IND",

    .name = "PacmanProIND",
    .build_fingerprint = "Nothing/PacmanProIND/PacmanPro:16/BP2A.250605.031.A3/2509172235:user/release-keys",
};

static const variant_info_t PacmanProJPN_info = {
    .sku_value = "JPN",

    .name = "PacmanProJPN",
    .build_fingerprint = "Nothing/PacmanProJPN/PacmanPro:16/BP2A.250605.031.A3/2509172235:user/release-keys",
};

static const variant_info_t PacmanPro_info = {
    .sku_value = "",

    .name = "PacmanPro",
    .build_fingerprint = "Nothing/PacmanPro/PacmanPro:16/BP2A.250605.031.A3/2509172235:user/release-keys",
};

static const std::vector<variant_info_t> variants = {
    PacmanProEEA_info,
    PacmanProIND_info,
    PacmanProJPN_info,
    PacmanPro_info,
};

void vendor_load_properties() {
    search_variant(variants);
}
