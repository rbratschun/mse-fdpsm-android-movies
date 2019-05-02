package at.rbratschun.mse.movies.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(movie: MovieEntity): Long

    @Query("SELECT * FROM movies")
    fun findAll(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun findById(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE :term OR year = CAST(:term AS NUMBER)")
    fun search(term: String): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE to_watch = 1 ORDER BY title")
    fun moviesToWatch(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE favorite = 1 ORDER BY title")
    fun favoriteMovies(): LiveData<List<MovieEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: MovieEntity): Void

    @Delete
    fun delete(movie: MovieEntity): Void
}