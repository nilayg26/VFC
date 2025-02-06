package com.example.vfc

interface Destination{
    var route:String
}
object SignInPage:Destination{
    override var route="SignUpPage"
}
object LogInPage:Destination{
    override var route="LogInPage"
}
object HomePage: Destination{
    override var route="HomePage"
}

object ProfilePage: Destination{
    override var route="Profile"
}
object CartPage: Destination{
    override var route="CartPage"
}
object RestaurantPage: Destination{
    override var route="RestPage"
}
object Status:Destination{
    override var route: String="StatusPage"
}
object ForgotPassword:Destination{
    override var route: String="ForgotPassword"
}
