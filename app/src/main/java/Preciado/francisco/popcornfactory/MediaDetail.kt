package Preciado.francisco.popcornfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_media_detail.*

class MediaDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)

        val bundle = intent.extras
        var movies = ArrayList<Media>()
        var indexMovie=0
        var movie = Media()
        var numberSeats=0

        if (bundle != null) {
            movies=bundle.getParcelableArrayList("movies")!!
            indexMovie=bundle.getInt("indexMovie")

            movie=movies.get(indexMovie)

            numberSeats=20-movie.seats.size
            ivMovieImage.setImageResource(movie.header)
            tvMovieName.setText(movie.title)
            tvMovieSynopsis.setText(movie.synopsis)
            tvSeatsLeft.setText("$numberSeats seats available")
        }

        if(numberSeats==0){
            btnBuyTickets.isEnabled=false
        }else{
            btnBuyTickets.setOnClickListener{
                val intent = Intent(this, SeatSelectionActivity::class.java)
                intent.putExtra("title", movie.title)
                intent.putExtra("indexMovie", indexMovie)
                intent.putParcelableArrayListExtra("movies", movies)
                startActivity(intent)
            }
        }
    }
}