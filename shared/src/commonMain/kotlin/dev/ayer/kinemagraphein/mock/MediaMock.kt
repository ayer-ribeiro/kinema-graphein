package dev.ayer.kinemagraphein.mock

import dev.ayer.kinemagraphein.entity.media.Episode
import dev.ayer.kinemagraphein.entity.media.Season
import dev.ayer.kinemagraphein.entity.media.Show
import dev.ayer.kinemagraphein.entity.time.Schedule
import kotlinx.datetime.Clock

val underTheDomeMediaMock
    get() = Show(
        id = 1,
        isFavorite = false,
        summary = "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>",
        releaseDate = "2013-06-25",
        name = "Under The Dome",
        mediumImageUrl = "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
        schedule = Schedule(
            time = "21:00",
            weekdays = listOf(
                "Friday"
            )
        ),
        genres = listOf(
            "Drama",
            "Science-Fiction",
            "Thriller",
        ),
        seasons = listOf(
            Season(
                id = "1",
                summary = "asd hausdhaosduhao sidauhsd ",
                mediumImageUrl = null,
                originalImageUrl = null,
                number = 1,
                episodes = listOf(
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 1,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        originalImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        isFavorite = false,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 2,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = null,
                        originalImageUrl = null,
                        isFavorite = false,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 3,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        originalImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        isFavorite = false,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 4,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        originalImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        isFavorite = false,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 5,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        originalImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        isFavorite = false,
                    )
                ),
                isFavorite = true,
                name = "Kennon"
            ),
            Season(
                summary = "apifja 0js9 ja0sdas89d",
                mediumImageUrl = null,
                originalImageUrl = null,
                number = 2,
                episodes = listOf(
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 1,
                        season = 2,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        originalImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        isFavorite = false,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 2,
                        season = 2,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        originalImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        isFavorite = false,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 3,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        originalImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        isFavorite = false,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 4,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        originalImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        isFavorite = false,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 5,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        originalImageUrl = "https://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg",
                        isFavorite = false,
                    )
                ), id = "Shakeya", isFavorite = false, name = "Marica"
            )
        ),
        originalImageUrl = null,
        lastAccess = Clock.System.now()
    )

val personOfInterestMediaMock
    get() = Show(
        id = 1,
        isFavorite = true,
        summary = "<p>You are being watched. The government has a secret system, a machine that spies on you every hour of every day. I know because I built it. I designed the Machine to detect acts of terror but it sees everything. Violent crimes involving ordinary people. People like you. Crimes the government considered \\\"irrelevant\\\". They wouldn't act so I decided I would. But I needed a partner. Someone with the skills to intervene. Hunted by the authorities, we work in secret. You'll never find us. But victim or perpetrator, if your number is up, we'll find you.</p>",
        releaseDate = "2020-10-26",
        name = "Person of Interest",
        mediumImageUrl = "https://static.tvmaze.com/uploads/images/medium_portrait/163/407679.jpg",
        originalImageUrl = "https://static.tvmaze.com/uploads/images/medium_portrait/163/407679.jpg",
        schedule = Schedule(
            time = "10:00",
            weekdays = listOf(
                "Monday",
            )
        ),
        genres = listOf(
            "Action",
            "Crime",
            "Science-Fiction",
        ),
        lastAccess = Clock.System.now(),
        seasons = listOf(
            Season(
                summary = "asd hausdhaosduhao sidauhsd ",
                mediumImageUrl = null,
                originalImageUrl = null,
                number = 1,
                episodes = listOf(
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 1,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = null,
                        isFavorite = false,
                        originalImageUrl = null,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 2,
                        season = 1,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = null,
                        isFavorite = false,
                        originalImageUrl = null,
                    )
                ),
                id = "Lillian",
                isFavorite = true,
                name = "Nikhil"
            ),
            Season(
                summary = "apifja 0js9 ja0sdas89d",
                mediumImageUrl = null,
                originalImageUrl = null,
                number = 2,
                episodes = listOf(
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 1,
                        season = 2,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = null,
                        originalImageUrl = null,
                        isFavorite = false,
                    ),
                    Episode(
                        id = "Cordell",
                        name = "Evie",
                        number = 2,
                        season = 2,
                        summary = "Wynter",
                        releaseDate = "2013-06-25",
                        mediumImageUrl = null,
                        originalImageUrl = null,
                        isFavorite = false,
                    )
                ),
                id = "Shawnte",
                isFavorite = false,
                name = "Bobbiejo"
            )
        )
    )
