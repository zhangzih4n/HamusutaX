@file:Suppress("UNUSED")
package hamusutax.core.io.checksum

expect fun ByteArray.crc8(): Long

expect fun ByteArray.crc8_sae_j1850(): Long

expect fun ByteArray.crc8_sae_j1850_zero(): Long

expect fun ByteArray.crc8_8h2f(): Long

expect fun ByteArray.crc8_cdma2000(): Long

expect fun ByteArray.crc8_darc(): Long

expect fun ByteArray.crc8_dvb_s2(): Long

expect fun ByteArray.crc8_ebu(): Long

expect fun ByteArray.crc8_icode(): Long

expect fun ByteArray.crc8_itu(): Long

expect fun ByteArray.crc8_maxim(): Long

expect fun ByteArray.crc8_rohc(): Long

expect fun ByteArray.crc8_wcdma(): Long

expect fun ByteArray.crc16_ccit_zero(): Long

expect fun ByteArray.crc16_arc(): Long

expect fun ByteArray.crc16_aug_ccit(): Long

expect fun ByteArray.crc16_buypass(): Long

expect fun ByteArray.crc16_ccitt_false(): Long

expect fun ByteArray.crc16_cdma2000(): Long

expect fun ByteArray.crc16_dds_110(): Long

expect fun ByteArray.crc16_dect_r(): Long

expect fun ByteArray.crc16_dect_x(): Long

expect fun ByteArray.crc16_dnp(): Long

expect fun ByteArray.crc16_en_13757(): Long

expect fun ByteArray.crc16_genibus(): Long

expect fun ByteArray.crc16_maxim(): Long

expect fun ByteArray.crc16_mcrf4xx(): Long

expect fun ByteArray.crc16_riello(): Long

expect fun ByteArray.crc16_t10_dif(): Long

expect fun ByteArray.crc16_teledisk(): Long

expect fun ByteArray.crc16_tms37157(): Long

expect fun ByteArray.crc16_usb(): Long

expect fun ByteArray.crc16_a(): Long

expect fun ByteArray.crc16_kermit(): Long

expect fun ByteArray.crc16_modbus(): Long

expect fun ByteArray.crc16_x_25(): Long

expect fun ByteArray.crc16_xmodem(): Long

expect fun ByteArray.crc32(): Long

expect fun ByteArray.crc32_bzip2(): Long

expect fun ByteArray.crc32_c(): Long

expect fun ByteArray.crc32_d(): Long

expect fun ByteArray.crc32_mpeg2(): Long

expect fun ByteArray.crc32_posix(): Long

expect fun ByteArray.crc32_q(): Long

expect fun ByteArray.crc32_jamcrc(): Long

expect fun ByteArray.crc32_xfer(): Long
