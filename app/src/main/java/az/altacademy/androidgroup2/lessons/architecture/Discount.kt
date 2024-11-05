package az.altacademy.androidgroup2.lessons.architecture


interface DiscountStrategy{
    fun applyPrice(price: Double): Double
}

class MonthlyDiscount: DiscountStrategy{
    override fun applyPrice(price: Double): Double {
        return price
    }
}

class SeasonalDiscount: DiscountStrategy{
    override fun applyPrice(price: Double): Double {
        return price * 0.9
    }
}

class YearlyDiscount: DiscountStrategy{
    override fun applyPrice(price: Double): Double {
        return price * 0.7
    }
}