package com.theveloper.pixelplay.data.remote.youtube

import com.theveloper.pixelplay.data.model.Song
import com.theveloper.pixelplay.data.model.ArtistRef

fun com.theveloper.pixelplay.data.model.youtube.Song.toNativeSong(): Song {
    val durationMillis = parseDurationStringToMillis(duration)
    val artistIdHash = artist.hashCode().toLong()
    val songId = "youtube_$youtubeId"
    return Song(
        id = songId,
        title = title,
        artist = artist,
        artistId = artistIdHash,
        artists = listOf(ArtistRef(id = artistIdHash, name = artist, isPrimary = true)),
        album = "YouTube Music",
        albumId = "YouTube Music".hashCode().toLong(),
        albumArtist = artist,
        path = audioFilePath.orEmpty(),
        contentUriString = "youtube://$youtubeId",
        albumArtUriString = thumbnailPath ?: thumbnailHref,
        duration = durationMillis,
        genre = "YouTube",
        lyrics = null,
        isFavorite = false,
        trackNumber = 0,
        discNumber = null,
        year = 0,
        dateAdded = System.currentTimeMillis(),
        dateModified = System.currentTimeMillis(),
        mimeType = "audio/mpeg",
        bitrate = 128,
        sampleRate = 44100,
        telegramFileId = null,
        telegramChatId = null,
        neteaseId = null,
        gdriveFileId = null,
        qqMusicMid = null,
        navidromeId = null,
        jellyfinId = null,
        youtubeId = youtubeId
    )
}

private fun parseDurationStringToMillis(durationStr: String): Long {
    if (durationStr.isBlank()) return 0L
    val parts = durationStr.split(":")
    return try {
        when (parts.size) {
            1 -> parts[0].toLong() * 1000L
            2 -> (parts[0].toLong() * 60L + parts[1].toLong()) * 1000L
            3 -> ((parts[0].toLong() * 3600L + parts[1].toLong() * 60L + parts[2].toLong())) * 1000L
            else -> 0L
        }
    } catch (e: Exception) {
        0L
    }
}
