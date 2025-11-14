package com.example.testapp.persentation.feature.home_rcti.data

/**
 * @author Andika Bratadirja
 * @date 21/09/2025
 */
class BannerlModel(
    val data: List<BannerItem>
) {
    companion object {
        val mock = BannerlModel(
            data = listOf(
                BannerItem.mock1,
                BannerItem.mock2,
                BannerItem.mock3,
                BannerItem.mock4
            )
        )
    }
}

data class BannerItem(
    val id: Int,
    val title: String,
    val landscape_image: String,
    val portrait_image: String,
    val square_image: String,
    val popup_image: String,
    val type: String,
    val deeplink: String,
    val permalink: String,
    val external_link: String,
    val summary: String,
    val wording_button: String,
    val logo: String,
    val is_countdown: Boolean,
    val live_label: String,
    val is_live: Boolean,
    val live_at: Long
) {
    companion object {
        val mock1 = BannerItem(
            id = 7114,
            title = "Ur Weekend Playlist: Voice of the Idols",
            landscape_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/0_daily/BANNER/voice_of_the_idol_190925_w.jpg",
            portrait_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/0_daily/BANNER/voice_of_the_idol_190925_a.jpg",
            square_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/snapshot/040722/inewssore_new_s.jpg",
            popup_image = "",
            type = "now_playing",
            deeplink = "android-app://com.fta.rctitv/live-event/6704/voice-of-the-idol",
            permalink = "https://www.rctiplus.com/live-event/6704/voice-of-the-idol",
            external_link = "6704",
            summary = "",
            wording_button = "Klik Disini",
            logo = "",
            is_countdown = false,
            live_label = "",
            is_live = false,
            live_at = 0
        )

        val mock2 = BannerItem(
            id = 7113,
            title = "Weekend With Idolyfe: Panaroma Full Team",
            landscape_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/0_daily/BANNER/weekend_vibes_idolyfe_190925_w.jpg",
            portrait_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/0_daily/BANNER/weekend_vibes_idolyfe_190925_a.jpg",
            square_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/snapshot/040722/inewssore_new_s.jpg",
            popup_image = "",
            type = "now_playing",
            deeplink = "android-app://com.fta.rctitv/live-event/6705/panaroma-full-team",
            permalink = "https://www.rctiplus.com/live-event/6705/panaroma-full-team",
            external_link = "6705",
            summary = "",
            wording_button = "Klik Disini",
            logo = "",
            is_countdown = false,
            live_label = "",
            is_live = false,
            live_at = 0
        )

        val mock3 = BannerItem(
            id = 7116,
            title = "Diamonds on the Court",
            landscape_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/0_daily/BANNER/210925_01_w.jpg",
            portrait_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/0_daily/BANNER/210925_01_a.jpg",
            square_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/snapshot/040722/inewssore_new_s.jpg",
            popup_image = "",
            type = "upcoming_event",
            deeplink = "android-app://com.fta.rctitv/live-event/6707/diamonds-on-the-court-21-september-1300-wib",
            permalink = "https://www.rctiplus.com/live-event/6707/diamonds-on-the-court-21-september-1300-wib",
            external_link = "6707",
            summary = "",
            wording_button = "Klik Disini",
            logo = "",
            is_countdown = true,
            live_label = "Sun, 21 Sep 2025 - 13:00 WIB",
            is_live = false,
            live_at = 1758434400
        )

        val mock4 = BannerItem(
            id = 6707,
            title = "Kau Ditakdirkan Untukku - ALL TIME",
            landscape_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/snapshot/180625/kdu_w.jpg",
            portrait_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/snapshot/180625/kdu_a.jpg",
            square_image = "https://static.rctiplus.id/media/1080/files/fta_rcti/Banner_Landscape/snapshot/280225/2802_hafizindonesia_s.jpg",
            popup_image = "",
            type = "catchup",
            deeplink = "android-app://com.fta.rctitv/tv/rcti/304491/kau-ditakdirkan-untukku?date=2025-09-19",
            permalink = "https://www.rctiplus.com/tv/rcti/304491/kau-ditakdirkan-untukku?date=2025-09-19",
            external_link = "304491",
            summary = "",
            wording_button = "Klik Disini",
            logo = "",
            is_countdown = false,
            live_label = "",
            is_live = false,
            live_at = 0
        )
    }
}
