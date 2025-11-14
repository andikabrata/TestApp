package com.example.testapp.persentation.feature.home_rcti.data

/**
 * @author Andika Bratadirja
 * @date 21/09/2025
 */
data class CategoryModel(
    val data: List<CategoryItem>
) {
    companion object {
        val mock = CategoryModel(
            data = listOf(
                CategoryItem.mock1,
                CategoryItem.mock2,
                CategoryItem.mock3,
                CategoryItem.mock4,
            )
        )
    }
}

data class CategoryItem(
    val id: Int,
    val name: String,
    val icon: String
) {
    companion object {
        val mock1 = CategoryItem(
            id = 62,
            name = "Microdrama",
            icon = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/00_dracin/DRACIN_ICON.png"
        )

        val mock2 = CategoryItem(
            id = 2,
            name = "R+ Original",
            icon = "https://static.rctiplus.id/media/500/files/fta_rcti/logo/r__original_4.png"
        )

        val mock3 = CategoryItem(
            id = 6,
            name = "TV Shows",
            icon = "https://static.rctiplus.id/media/500/files/fta_rcti/logo/0601_series.png"
        )

        val mock4 = CategoryItem(
            id = 17,
            name = "Sports",
            icon = "https://static.rctiplus.id/media/500/files/fta_rcti/Banner_Landscape/snapshot/110525/110525_altbola2_s1.png"
        )
    }
}