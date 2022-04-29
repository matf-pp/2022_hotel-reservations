package ReservationThings

open class Offer(open var food : Food,
                 open var num_persons : Int,
                 open var parking : Boolean,
                 open var wellness : Boolean,
                 open var massage : Int,
                 open var party : Boolean)
{
    var parking_price : Double = if(parking) 250.0 else 0.0
    var wellness_price : Double = if (wellness) 320.0 else 0.0
    var massage_price : Double = if (massage > 0) 1450.0 else 0.0
    var party_price : Double = if (party) 500.0 * num_persons else 0.0

    open fun offer_price_per_day() : Double{
        return parking_price + wellness_price + food.get_total_price() * num_persons
    }

    companion object {
        var price_parking = 250.0
        var price_wellness = 320.0
        var price_massage1 = 1450.0
        var price_party = 500
    }

}