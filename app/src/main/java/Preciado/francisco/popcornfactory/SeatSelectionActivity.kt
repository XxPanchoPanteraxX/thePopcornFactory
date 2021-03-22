package Preciado.francisco.popcornfactory

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_seat_selection.*

class SeatSelectionActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val bundle = intent.extras
        var indexMovie = -1
        var movies = ArrayList<Media>()
        var movie : Media
        var hashSeats = HashMap<RadioButton,Int>()
        var selectedSeat=0

        if (bundle != null) {
            indexMovie = bundle.getInt("indexMovie")
            movies = bundle.getParcelableArrayList("movies")!!

        }
        if (movies != null) {
            movie = movies.get(indexMovie)

            tvSeatsTitle.setText(movie.title)

            var seatNumber=1

            lytSeatsRows.children.iterator().forEach {
                var rgRow:RadioGroup = it as RadioGroup
                rgRow.children.iterator().forEach {
                    var rbtn : RadioButton = it as RadioButton
                    hashSeats.set(rbtn,seatNumber++)
                }

                rgRow.setOnCheckedChangeListener{rgRow, i->
                    if(i>-1){
                        lytSeatsRows.children.iterator().forEach{
                            var rgThisRow:RadioGroup=it as RadioGroup
                            if(rgRow.id!=rgThisRow.id){
                                rgThisRow.clearCheck()
                            }
                        }
                        rgRow.check(i)
                        selectedSeat=hashSeats.getValue(findViewById(rgRow.checkedRadioButtonId))
                    }
                }
            }

            hashSeats.forEach{ it1->
                movie.seats.forEach{
                    if(it1.value==it.seatNumber) it1.key.isEnabled=false
                }
            }
        }



        btnConfirm.setOnClickListener {

            var intent : Intent= Intent(this,PurchaseSummaryActivity::class.java)
            intent.putExtra("indexMovie", indexMovie)
            intent.putExtra("movies",movies)
            intent.putExtra("selectedSeat", selectedSeat)


            startActivity(intent)

        }
        /*
        rgSeatsRow1.setOnCheckedChangeListener { radioGroup, i ->
            if (i > -1) {
                rgSeatsRow2.clearCheck()
                rgSeatsRow3.clearCheck()
                rgSeatsRow4.clearCheck()
                rgSeatsRow1.check(i)
            }
        }
        rgSeatsRow2.setOnCheckedChangeListener { radioGroup, i ->
            if (i > -1) {
                rgSeatsRow1.clearCheck()
                rgSeatsRow3.clearCheck()
                rgSeatsRow4.clearCheck()
                rgSeatsRow2.check(i)
            }
        }
        rgSeatsRow3.setOnCheckedChangeListener { radioGroup, i ->
            if (i > -1) {
                rgSeatsRow1.clearCheck()
                rgSeatsRow2.clearCheck()
                rgSeatsRow4.clearCheck()
                rgSeatsRow3.check(i)
            }
        }
        rgSeatsRow4.setOnCheckedChangeListener { radioGroup, i ->
            if (i > -1) {
                rgSeatsRow1.clearCheck()
                rgSeatsRow2.clearCheck()
                rgSeatsRow3.clearCheck()
                rgSeatsRow4.check(i)
            }
        }*/
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}