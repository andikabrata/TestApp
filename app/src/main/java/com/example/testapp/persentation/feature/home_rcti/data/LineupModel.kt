package com.example.testapp.persentation.feature.home_rcti.data

/**
 * @author Andika Bratadirja
 * @date 21/09/2025
 */
data class LineupModel(
    val data: List<LineupItem>
) {
    companion object {
        val mock = LineupModel(
            data = listOf(
                LineupItem.mockTV,
                LineupItem.mockSport,
                LineupItem.mockWeekend,
                LineupItem.mockTV,
                LineupItem.mockWeekend,
                LineupItem.mockSport,
                LineupItem.mockSportV2,
                LineupItem.mockTVV2,
            )
        )
    }
}

data class LineupItem(
    val id: Int,
    val title: String,
    val lineup_type: String,
    val display_type: String,
    val content_type: String,
    val image_thematic: String,
    val url: String,
    val lineup_icon: String,
    val show_more_label: String,
    val lineup_type_detail: LineupTypeDetail
) {
    companion object {
        val mockTV = LineupItem(
            id = 474,
            title = "TV Terpopuler",
            lineup_type = "default",
            display_type = "news_tag",
            content_type = "video_content",
            image_thematic = "",
            url = "",
            lineup_icon = "",
            show_more_label = "",
            lineup_type_detail = LineupTypeDetail.mockSpecial
        )

        val mockSport = LineupItem(
            id = 1092,
            title = "Tayangan Sport Unggulan",
            lineup_type = "default",
            display_type = "potrait_wt",
            content_type = "video_content",
            image_thematic = "",
            url = "",
            lineup_icon = "",
            show_more_label = "",
            lineup_type_detail = LineupTypeDetail.mockSport
        )

        val mockWeekend = LineupItem(
            id = 448,
            title = "Weekend Mood Booster",
            lineup_type = "default",
            display_type = "potrait_wt",
            content_type = "video_live_event",
            image_thematic = "",
            url = "",
            lineup_icon = "",
            show_more_label = "",
            lineup_type_detail = LineupTypeDetail.mockLiveEvent
        )

        val mockSportV2 = LineupItem(
            id = 1092,
            title = "Tayangan Sport Unggulan",
            lineup_type = "default",
            display_type = "news_tag",
            content_type = "video_content",
            image_thematic = "",
            url = "",
            lineup_icon = "",
            show_more_label = "",
            lineup_type_detail = LineupTypeDetail.mockSport
        )

        val mockTVV2 = LineupItem(
            id = 474,
            title = "TV Terpopuler",
            lineup_type = "default",
            display_type = "potrait_wt",
            content_type = "video_content",
            image_thematic = "",
            url = "",
            lineup_icon = "",
            show_more_label = "",
            lineup_type_detail = LineupTypeDetail.mockSpecial
        )
    }
}

data class LineupTypeDetail(
    val typename: String,
    val detail: LineupDetail
) {
    companion object {
        val mockSpecial = LineupTypeDetail(
            typename = "LineupTypeDefault",
            detail = LineupDetail(
                data = listOf(
                    LineupContent.mockRCTI,
                    LineupContent.mockMNCTV,
                    LineupContent.mockRCTI,
                    LineupContent.mockMNCTV,
                    LineupContent.mockRCTI,
                    LineupContent.mockMNCTV,
                    LineupContent.mockRCTI,
                    LineupContent.mockMNCTV,
                    LineupContent.mockRCTI,
                    LineupContent.mockMNCTV,
                )
            )
        )

        val mockSport = LineupTypeDetail(
            typename = "LineupTypeDefault",
            detail = LineupDetail(
                data = listOf(
                    LineupContent.mockSport,
                    LineupContent.mockSport,
                    LineupContent.mockSport,
                    LineupContent.mockSport,
                    LineupContent.mockSport,
                    LineupContent.mockSport,
                    LineupContent.mockSport
                )
            )
        )

        val mockLiveEvent = LineupTypeDetail(
            typename = "LineupTypeDefault",
            detail = LineupDetail(
                data = listOf(
                    LineupContent.mockLiveEvent1,
                    LineupContent.mockLiveEvent2,
                    LineupContent.mockLiveEvent1,
                    LineupContent.mockLiveEvent2,
                    LineupContent.mockLiveEvent1,
                    LineupContent.mockLiveEvent2,
                    LineupContent.mockLiveEvent1,
                    LineupContent.mockLiveEvent2,
                    LineupContent.mockLiveEvent1,
                    LineupContent.mockLiveEvent2
                )
            )
        )
    }
}

data class LineupDetail(
    val data: List<LineupContent>
)

data class LineupContent(
    val id: Int,
    val content_id: Int,
    val content_type: String,
    val lineup_id: Int,
    val content_type_detail: ContentTypeDetail
) {
    companion object {
        val mockRCTI = LineupContent(
            id = 156899,
            content_id = 66,
            content_type = "special",
            lineup_id = 474,
            content_type_detail = ContentTypeDetail.mockRCTI
        )

        val mockMNCTV = LineupContent(
            id = 156900,
            content_id = 415,
            content_type = "special",
            lineup_id = 474,
            content_type_detail = ContentTypeDetail.mockMNCTV
        )

        val mockSport = LineupContent(
            id = 271204,
            content_id = 8469,
            content_type = "special",
            lineup_id = 1092,
            content_type_detail = ContentTypeDetail.mockSport
        )

        val mockLiveEvent1 = LineupContent(
            id = 271310,
            content_id = 6705,
            content_type = "live_event",
            lineup_id = 448,
            content_type_detail = ContentTypeDetail.mockLive1
        )

        val mockLiveEvent2 = LineupContent(
            id = 271308,
            content_id = 6704,
            content_type = "live_event",
            lineup_id = 448,
            content_type_detail = ContentTypeDetail.mockLive2
        )
    }
}

data class ContentTypeDetail(
    val typename: String,
    val detail: ContentDetail
) {
    companion object {
        val mockRCTI = ContentTypeDetail(
            typename = "ContentTypeSpecial",
            detail = ContentDetail(
                data = SpecialContent(
                    id = 66,
                    title = "RCTI Mweb",
                    label = "",
                    landscape_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Channel_Logo/RCTI.png",
                    medium_landscape_image = "",
                    portrait_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Channel_Logo/por-RCTI.png",
                    square_image = "https://static.rctiplus.id/media/500/files/fta_rcti/s_bolapon28sep.jpg",
                    permalink = "",
                    type = "special",
                    mandatory_login = false,
                    external_link = "https://www.rctiplus.com/videoplus/livetv?channel=1",
                    action_type = "to_video_plus",
                    publisher = ""
                )
            )
        )

        val mockMNCTV = ContentTypeDetail(
            typename = "ContentTypeSpecial",
            detail = ContentDetail(
                data = SpecialContent(
                    id = 415,
                    title = "MNCTV Mweb",
                    label = "",
                    landscape_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Channel_Logo/MNCTV.png",
                    medium_landscape_image = "",
                    portrait_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Channel_Logo/por-MNCTV.png",
                    square_image = "https://static.rctiplus.id/media/500/files/fta_rcti/s_bolapon28sep.jpg",
                    permalink = "",
                    type = "special",
                    mandatory_login = false,
                    external_link = "https://www.rctiplus.com/videoplus/livetv?channel=2",
                    action_type = "to_video_plus",
                    publisher = ""
                )
            )
        )

        val mockSport = ContentTypeDetail(
            typename = "ContentTypeSpecial",
            detail = ContentDetail(
                data = SpecialContent(
                    id = 415,
                    title = "MNCTV Mweb",
                    label = "",
                    landscape_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Channel_Logo/MNCTV.png",
                    medium_landscape_image = "",
                    portrait_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Channel_Logo/por-MNCTV.png",
                    square_image = "https://static.rctiplus.id/media/500/files/fta_rcti/s_bolapon28sep.jpg",
                    permalink = "",
                    type = "special",
                    mandatory_login = false,
                    external_link = "https://www.rctiplus.com/videoplus/livetv?channel=2",
                    action_type = "to_video_plus",
                    publisher = ""
                )
            )
        )

        val mockLive1 = ContentTypeDetail(
            typename = "ContentTypeLiveEvent",
            detail = ContentDetail(
                data = LiveEventContent(
                    id = 6705,
                    title = "Panaroma Full Team",
                    portrait_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/snapshot/110625/idolyfe_p.png",
                    landscape_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/0_daily/BANNER/weekend_vibes_idolyfe_190925_l.jpg",
                    permalink = "https://www.rctiplus.com/live-event/6705/panaroma-full-team",
                    deeplink = "android-app://com.fta.rctitv/live-event/6705/panaroma-full-team",
                    live_label = "Fri, 19 Sep 2025 - 16:55 WIB",
                    start_date = 1758205800,
                    is_live = true
                )
            )
        )

        val mockLive2 = ContentTypeDetail(
            typename = "ContentTypeLiveEvent",
            detail = ContentDetail(
                data = LiveEventContent(
                    id = 6704,
                    title = "Voice of the Idol",
                    portrait_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/snapshot/110625/idolyfe_p.png",
                    landscape_image = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/0_daily/BANNER/voice_of_the_idol_190925_l.jpg",
                    permalink = "https://www.rctiplus.com/live-event/6704/voice-of-the-idol",
                    deeplink = "android-app://com.fta.rctitv/live-event/6704/voice-of-the-idol",
                    live_label = "Fri, 19 Sep 2025 - 15:00 WIB",
                    start_date = 1758205800,
                    is_live = true
                )
            )
        )
    }
}

data class ContentDetail(
    val data: Any
)

data class SpecialContent(
    val id: Int = 0,
    val title: String = "",
    val label: String = "",
    val landscape_image: String = "",
    val medium_landscape_image: String = "",
    val portrait_image: String = "",
    val square_image: String = "",
    val permalink: String = "",
    val type: String = "",
    val mandatory_login: Boolean = false,
    val external_link: String = "",
    val action_type: String = "",
    val publisher: String = ""
)

data class LiveEventContent(
    val id: Int,
    val title: String,
    val portrait_image: String,
    val landscape_image: String,
    val medium_landscape_image: String = "",
    val square_image: String = "",
    val deeplink: String,
    val permalink: String,
    val countdown_s: Int = 0,
    val asset_name: String = "",
    val start_date: Long,
    val live_label: String,
    val is_interactive: Boolean = false,
    val is_live: Boolean
)