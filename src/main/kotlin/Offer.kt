open class Offer(open var food : Food,
                 open var num_persons : Int,
                 open var parking : Boolean,
                 open var wellness : Boolean,
                 open var massage : Boolean,
                 open var party : Boolean)
{
    protected var parking_price : Double = if(parking) 250.0 else 0.0
    protected var wellness_price : Double = if (wellness) 540.0 else 0.0
    protected var massage_price : Double = if (massage) 1450.0 else 0.0
    protected var party_price : Double = if (party) 750.0 else 0.0

    open fun offer_price_per_day() : Double{
        return parking_price + wellness_price + massage_price + party_price + food.get_total_price() * num_persons
    }

}