package com.academy.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.academy.db.utils.MovieModelConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val overview: String,
    val date: String,
    val vote: Double,
    val voteCount: Int
) : Parcelable {
    fun getTitleWithYear() = title + " (" + MovieModelConverter.getYear(date) + ")"
}
