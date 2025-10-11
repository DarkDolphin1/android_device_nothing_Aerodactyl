/*
 * Copyright (C) 2021-2025 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#include <libinit_variant.h>

#include "vendor_init.h"

static const variant_info_t PacmanEEA_info = {
    .sku_value = "EEA",

    .name = "PacmanEEA",
    .build_fingerprint = "Nothing/PacmanEEA/Pacman:16/BP2A.250605.031.A3/2509172235:user/release-keys",
};

static const variant_info_t PacmanIND_info = {
    .sku_value = "IND",

    .name = "PacmanIND",
    .build_fingerprint = "Nothing/PacmanIND/Pacman:16/BP2A.250605.031.A3/2509172235:user/release-keys",
};

static const variant_info_t PacmanJPN_info = {
    .sku_value = "JPN",

    .name = "PacmanJPN",
    .build_fingerprint = "Nothing/PacmanJPN/Pacman:16/BP2A.250605.031.A3/2509172235:user/release-keys",
};

static const variant_info_t Pacman_info = {
    .sku_value = "",

    .name = "Pacman",
    .build_fingerprint = "Nothing/Pacman/Pacman:16/BP2A.250605.031.A3/2509172235:user/release-keys",
};

static const std::vector<variant_info_t> variants = {
    PacmanEEA_info,
    PacmanIND_info,
    PacmanJPN_info,
    Pacman_info,
};

void vendor_load_properties() {
    search_variant(variants);
}
