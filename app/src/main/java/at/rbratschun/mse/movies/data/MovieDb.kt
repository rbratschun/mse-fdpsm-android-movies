package at.rbratschun.mse.movies.data

class MovieDb {
    var movieCollection: ArrayList<MovieCollection> = ArrayList()

    fun fetchMovieCollections(): ArrayList<MovieCollection> {
        movieCollection.add(
            MovieCollection(
                "Bee Movie",
                2007,
                "tt0389790",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMjE1MDYxOTA4MF5BMl5BanBnXkFtZTcwMDE0MDUzMw@@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Epic Movie",
                2007,
                "tt0389790",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMTA3NDM5ODU3NzleQTJeQWpwZ15BbWU3MDgyMjgyNDE@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Movie 43",
                2013,
                "tt0389790",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMTg4NzQ3NDM1Nl5BMl5BanBnXkFtZTcwNjEzMjM3OA@@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Scary Movie",
                2000,
                "tt0175142",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMGEzZjdjMGQtZmYzZC00N2I4LThiY2QtNWY5ZmQ3M2ExZmM4XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Scary Movie 2",
                2001,
                "tt0257106",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMzQxYjU1OTUtYjRiOC00NDg2LWI4MWUtZGU5YzdkYTcwNTBlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Scary Movie 3",
                2003,
                "tt0306047",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BNDE2NTIyMjg2OF5BMl5BanBnXkFtZTYwNDEyMTg3._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "Scary Movie 4",
                2006,
                "tt0362120",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BZmFkMzc2NTctN2U1Ni00MzE5LWJmMzMtYWQ4NjQyY2MzYmM1XkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "The Lego Batman Movie",
                2017,
                "tt4116284",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMTcyNTEyOTY0M15BMl5BanBnXkFtZTgwOTAyNzU3MDI@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "The Lego Movie",
                2014,
                "tt1490017",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMTg4MDk1ODExN15BMl5BanBnXkFtZTgwNzIyNjg3MDE@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "The Shawshank Redemption",
                1996,
                "tt0111161",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
            )
        )
        movieCollection.add(
            MovieCollection(
                "The Simpsons Movie",
                2007,
                "tt0462538",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMTgxMDczMTA5N15BMl5BanBnXkFtZTcwMzk1MzMzMw@@._V1_SX300.jpg"
            )
        )
        return movieCollection
    }

    fun search(q: String): ArrayList<MovieCollection> {
        return ArrayList(movieCollection.filter { m -> m.Title.contains(q.toString()) })
    }
}