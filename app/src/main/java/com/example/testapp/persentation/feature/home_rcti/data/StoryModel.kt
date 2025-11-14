package com.example.testapp.persentation.feature.home_rcti.data

/**
 * @author Andika Bratadirja
 * @date 21/09/2025
 */
data class StoryModel(
    val data: List<ProgramStory>
) {
    companion object {
        val mock = StoryModel(
            data = listOf(
                ProgramStory.mock1,
                ProgramStory.mock2,
                ProgramStory.mock3,
                ProgramStory.mock4,
                ProgramStory.mock5,
                ProgramStory.mock6,
                ProgramStory.mock7
            )
        )
    }
}


data class ProgramStory(
    val program_id: Int,
    val program_img: String,
    val title: String,
) {
    companion object {
        val mock1 = ProgramStory(
            program_id = 1,
            title = "Cinta Lama Belum Kelar",
            program_img = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/00_dracin/cinta_lama_belum_kelar/cinta_lama_belum_kelar_S.jpg"
        )

        val mock2 = ProgramStory(
            program_id = 2,
            title = "Wujud Lain",
            program_img = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/0_daily/EPG/wujud_lain_i.jpg"
        )

        val mock3 = ProgramStory(
            program_id = 3,
            title = "Sang Pewaris",
            program_img = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/00_dracin/sang_pewaris/sang_pewaris_S.jpg"
        )

        val mock4 = ProgramStory(
            program_id = 4,
            title = "Looks Radio",
            program_img = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/snapshot/looks_logo_768x768.jpg"
        )

        val mock5 = ProgramStory(
            program_id = 5,
            title = "ZW7 Radio",
            program_img = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/snapshot/zw7_logo_768x768.jpg"
        )

        val mock6 = ProgramStory(
            program_id = 6,
            title = "Grim Radio",
            program_img = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/snapshot/grim_logo_768x768.jpg"
        )

        val mock7 = ProgramStory(
            program_id = 7,
            title = "XYZ Radio",
            program_img = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/snapshot/xyz_logo_768x768.jpg"
        )
    }
}