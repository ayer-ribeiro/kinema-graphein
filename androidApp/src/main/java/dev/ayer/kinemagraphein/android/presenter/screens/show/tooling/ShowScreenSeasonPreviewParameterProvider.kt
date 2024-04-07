package dev.ayer.kinemagraphein.android.presenter.screens.show.tooling

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.ayer.kinemagraphein.android.presenter.screens.show.ShowScreenEpisodeData
import dev.ayer.kinemagraphein.android.presenter.screens.show.ShowScreenSeasonData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

class ShowScreenSeasonPreviewParameterProvider :
    PreviewParameterProvider<ImmutableList<ShowScreenSeasonData>> {
    private val season1 = ShowScreenSeasonData(
        number = 1,
        episodes = listOf(
            ShowScreenEpisodeData(
                name = "Pilot",
                summary = "When they were children, Sam (Padalecki) and Dean Winchester's (Ackles) mother Mary (Samantha Smith) died a violent and unexplainable death, which in turn led their father, John Winchester (Jeffrey Dean Morgan), to teach them hunting skills in search of the creature that took her life. 22 years later, while attending Stanford University, Sam is forced back into the paranormal world by Dean, who has come to tell him that their father is missing. The two travel to Jericho, California, to find him, but their search is put on hold when they discover that a ghostly Woman in White (Sarah Shahi)—the spirit of a woman who drowned her children and then killed herself—has been taking male victims. They investigate, but Dean is arrested for impersonating a federal agent. However, he manages to escape custody, and saves his brother from the vengeful spirit. The Woman in White is finally put to rest after the brothers force her to confront the spirits of her children. Sam later returns home but discovers his girlfriend Jessica (Adrianne Palicki) pinned to the ceiling. He is then forced to watch as she is killed in the same supernatural manner as his mother was. Jessica's death prompts him to return to the life of hunting with Dean.",
                releaseDate = "September 13, 2005",
                number = 1,
                imageUrl = "https://",
                season = 1
            ),
            ShowScreenEpisodeData(
                name = "Wendigo",
                summary = "After having no luck in the investigation of Jessica's death, the brothers follow instructions found in their father's journal and head to Blackwater Ridge, Lost Creek, Colorado. Posing as park rangers, they help a young woman named Haley (Gina Holden) and her younger brother Ben (Alden Ehrenreich) search for their lost older brother Tommy (Graham Wardle), who disappeared while on a camping trip. Sam and Dean soon realize that he was taken by a wendigo that has terrorized the woods since 1936. While searching, Haley and Dean are kidnapped by the creature, but Sam and Ben are able to track them to a mine. After Dean, Haley, and Tommy are freed, they kill the wendigo with a flare gun.",
                releaseDate = "September 20, 2005",
                number = 2,
                imageUrl = "https://",
                season = 1
            ),
            ShowScreenEpisodeData(
                name = "Dead in the Water",
                summary = "Taking a break from the search for their father, Sam and Dean head to Lake Manitoc in Wisconsin for a possible case. Three drownings have occurred there within a year, with the dead bodies mysteriously disappearing in the lake. The brothers soon come to believe that an avenging spirit of a young boy haunts the lake, and learn that the local sheriff (Daniel Hugh Kelly) and the father of the latest victim accidentally caused his death when they were children. Out for revenge, the spirit targets the sheriff's daughter Andrea (Amy Acker) and mute grandson Lucas (Nico McEown). It eventually pulls Lucas into the lake, so the sheriff gives himself over to the spirit, saving his grandson.",
                releaseDate = "September 27, 2005",
                number = 3,
                imageUrl = "https://",
                season = 1
            ),
            ShowScreenEpisodeData(
                name = "Phantom Traveler",
                summary = "The brothers are asked by a man whom Dean and his father had previously rescued to investigate the mysterious crash of a commercial airplane that left only seven survivors, with signs pointing to a demonically-possessed passenger causing the accident. When they realize that the demon is now eliminating the survivors one by one, Dean is forced to face his fear of flying by following its next target (Jaime Ray Newman) aboard an airliner. He and Sam manage to get the demon into the galley on-board, but it flees its host during the exorcism. Despite this, they continue the ritual, sending the demon back to Hell. Afterwards they get a surprise when the man who called them revealed he got their phone number from their father's voicemail. Though John doesn't answer when they call, they get their first indication that he's alive as his phone has been down since his disappearance.",
                releaseDate = "October 4, 2005",
                number = 3,
                imageUrl = "https://",
                season = 1
            ),
        ).toImmutableList()
    )

    private val season2 = ShowScreenSeasonData(
        number = 2,
        episodes = listOf(
            ShowScreenEpisodeData(
                name = "Pilot",
                summary = "When they were children, Sam (Padalecki) and Dean Winchester's (Ackles) mother Mary (Samantha Smith) died a violent and unexplainable death, which in turn led their father, John Winchester (Jeffrey Dean Morgan), to teach them hunting skills in search of the creature that took her life. 22 years later, while attending Stanford University, Sam is forced back into the paranormal world by Dean, who has come to tell him that their father is missing. The two travel to Jericho, California, to find him, but their search is put on hold when they discover that a ghostly Woman in White (Sarah Shahi)—the spirit of a woman who drowned her children and then killed herself—has been taking male victims. They investigate, but Dean is arrested for impersonating a federal agent. However, he manages to escape custody, and saves his brother from the vengeful spirit. The Woman in White is finally put to rest after the brothers force her to confront the spirits of her children. Sam later returns home but discovers his girlfriend Jessica (Adrianne Palicki) pinned to the ceiling. He is then forced to watch as she is killed in the same supernatural manner as his mother was. Jessica's death prompts him to return to the life of hunting with Dean.",
                releaseDate = "September 13, 2005",
                number = 1,
                imageUrl = "https://",
                season = 1
            ),
            ShowScreenEpisodeData(
                name = "Wendigo",
                summary = "After having no luck in the investigation of Jessica's death, the brothers follow instructions found in their father's journal and head to Blackwater Ridge, Lost Creek, Colorado. Posing as park rangers, they help a young woman named Haley (Gina Holden) and her younger brother Ben (Alden Ehrenreich) search for their lost older brother Tommy (Graham Wardle), who disappeared while on a camping trip. Sam and Dean soon realize that he was taken by a wendigo that has terrorized the woods since 1936. While searching, Haley and Dean are kidnapped by the creature, but Sam and Ben are able to track them to a mine. After Dean, Haley, and Tommy are freed, they kill the wendigo with a flare gun.",
                releaseDate = "September 20, 2005",
                number = 2,
                imageUrl = "https://",
                season = 2
            ),
            ShowScreenEpisodeData(
                name = "Dead in the Water",
                summary = "Taking a break from the search for their father, Sam and Dean head to Lake Manitoc in Wisconsin for a possible case. Three drownings have occurred there within a year, with the dead bodies mysteriously disappearing in the lake. The brothers soon come to believe that an avenging spirit of a young boy haunts the lake, and learn that the local sheriff (Daniel Hugh Kelly) and the father of the latest victim accidentally caused his death when they were children. Out for revenge, the spirit targets the sheriff's daughter Andrea (Amy Acker) and mute grandson Lucas (Nico McEown). It eventually pulls Lucas into the lake, so the sheriff gives himself over to the spirit, saving his grandson.",
                releaseDate = "September 27, 2005",
                number = 3,
                imageUrl = "https://",
                season = 2
            ),
            ShowScreenEpisodeData(
                name = "Phantom Traveler",
                summary = "The brothers are asked by a man whom Dean and his father had previously rescued to investigate the mysterious crash of a commercial airplane that left only seven survivors, with signs pointing to a demonically-possessed passenger causing the accident. When they realize that the demon is now eliminating the survivors one by one, Dean is forced to face his fear of flying by following its next target (Jaime Ray Newman) aboard an airliner. He and Sam manage to get the demon into the galley on-board, but it flees its host during the exorcism. Despite this, they continue the ritual, sending the demon back to Hell. Afterwards they get a surprise when the man who called them revealed he got their phone number from their father's voicemail. Though John doesn't answer when they call, they get their first indication that he's alive as his phone has been down since his disappearance.",
                releaseDate = "October 4, 2005",
                number = 3,
                imageUrl = "https://",
                season = 2
            ),
        ).toImmutableList()
    )

    override val values: Sequence<ImmutableList<ShowScreenSeasonData>>
        get() = sequenceOf(
            listOf(
                season1,
                season2,
            ).toImmutableList()
        )
}