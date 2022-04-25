package ReservationThings

class Food(breakfast : Boolean, half_board : Boolean, full_board : Boolean) {

    var price_breakfast : Double = if(breakfast) 750.0 else 0.0
    var price_half_board : Double = if(half_board) 2300.0 else 0.0
    var price_full_board : Double = if(full_board) 3500.0 else 0.0

    fun get_total_price() : Double{
        return price_breakfast + price_half_board + price_full_board
    }

    fun set_price_breakfast(procenat : Double){
        if(procenat < 0 || procenat > 1)
            return
        this.price_breakfast = procenat * this.price_breakfast
    }
    fun set_price_half_board(procenat : Double){
        if(procenat < 0 || procenat > 1)
            return
        this.price_half_board = procenat * this.price_half_board
    }
    fun set_price_full_board(procenat : Double){
        if(procenat < 0 || procenat > 1)
            return
        this.price_full_board = procenat * this.price_full_board
    }
}