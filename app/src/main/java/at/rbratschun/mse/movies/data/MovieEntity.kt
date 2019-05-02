package at.rbratschun.mse.movies.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies",
    indices = [
        Index(value = ["title"], unique = true),
        Index(value = ["to_watch"], unique = false),
        Index(value = ["favorite"], unique = false)]
)
class MovieEntity(
    @PrimaryKey @ColumnInfo(name = "id") val imdbID: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "to_watch") var toWatch: Boolean,
    @ColumnInfo(name = "favorite") var favorite: Boolean
)