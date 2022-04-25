package ReservationThings

class SpecialOffer(override var food : Food,
                   override var num_persons: Int,
                   override var parking : Boolean,
                   override var wellness : Boolean,
                   override var massage : Boolean,
                   override var party : Boolean,
                   var discount_massage : Double,
                   var discount_party : Double,
                   var discount_food : Double,
                   var discount_wellness : Double) : Offer(food, num_persons, parking, wellness, massage, party)
{
    override fun offer_price_per_day(): Double {
        return super.wellness_price * discount_wellness + food.get_total_price() * num_persons * discount_food
            + super.massage_price * discount_massage + super.party_price * discount_party
        // u specijalnim ponudama parking je besplatan
    }

}

