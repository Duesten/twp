package models

case class User(id:Long,name:String, password:String)

case class UserData(name: String, password:String)

case class Item(id:Long, title: String, description:String, reserved : Boolean)

case class ItemData(title:String, description:String, reserved : Boolean)

case class Reservation(item:Item, user:User)

case class ReservationData(id:Long, item: Item, user: User)

