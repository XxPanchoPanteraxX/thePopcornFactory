package Preciado.francisco.popcornfactory

import android.os.Parcel
import android.os.Parcelable

data class Customer (var name: String,
                     var paymentMethod: PaymentMethod,
                     var seatNumber: Int) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        PaymentMethod.values()[parcel.readInt()],
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(paymentMethod.ordinal)
        parcel.writeInt(seatNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Customer> {
        override fun createFromParcel(parcel: Parcel): Customer {
            return Customer(parcel)
        }

        override fun newArray(size: Int): Array<Customer?> {
            return arrayOfNulls(size)
        }
    }

}