package Preciado.francisco.popcornfactory

import android.os.Parcel
import android.os.Parcelable

class Media : Parcelable{

    var title: String?=""
    var image: Int=-1
    var header: Int=-1
    var synopsis: String?=""
    var seats:ArrayList<Customer> = ArrayList()


    constructor(){

    }



    constructor(source:Parcel):this() {
        title = source.readString()
        image = source.readInt()
        header = source.readInt()
        synopsis = source.readString()
        seats=source.createTypedArrayList(Customer.CREATOR)!!
        /**
         * arrayListOf<Customer>().apply {
        source.readArrayList(Customer::class.java.classLoader)
        }
         */
    }

    constructor(title: String?, image: Int, header: Int, synopsis: String?, seats: ArrayList<Customer>) {
        this.title = title
        this.image = image
        this.header = header
        this.synopsis = synopsis
        this.seats = seats
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(title)
        p0?.writeInt(image)
        p0?.writeInt(header)
        p0?.writeString(synopsis)
        p0?.writeTypedList(seats)
    }

    companion object CREATOR : Parcelable.Creator<Media> {
        override fun createFromParcel(parcel: Parcel): Media {
            return Media(parcel)
        }

        override fun newArray(size: Int): Array<Media?> {
            return arrayOfNulls(size)
        }
    }
}