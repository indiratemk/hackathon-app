package com.example.hackathon.util

class Constants {

    companion object {
        const val BASE_URL = "https://hackathon-platform.space/api/"

        //region REQUEST CODE
        const val CAMERA_REQUEST_CODE = 1011
        const val AUTH_REQUEST_CODE = 1012
        const val QR_SCANNER_REQUEST_CODE = 1013
        const val HACKATHON_DETAIL_REQUEST_CODE = 1014
        const val HACKATHON_REGISTRATION_REQUEST_CODE = 1015
        const val PARTICIPATION_CONFIRMED_REQUEST_CODE = 1016
        //endregion

        //region EXTRAS
        const val HACKATHON_ID_EXTRA = "HACKATHON_ID_EXTRA"
        //endregion

        //region ERROR CODES
        const val NOT_FOUND_ERROR_CODE = 404
        //endregion

        //region PARTICIPATION TYPES
        const val STANDALONE = 0
        const val SEARCHING_FOR_TEAM = 1
        const val WITH_TEAM = 2
        //endregion
    }
}