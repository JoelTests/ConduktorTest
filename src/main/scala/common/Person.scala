package common

case class Person(
                 _id: String,
                 name: String,
                 dob: String,
                 address: Address,
                 telephone: String,
                 pets: Array[String],
                 score: Double,
                 email: String,
                 url: String,
                 description: String,
                 verified: Boolean,
                 salary: Int
                 )

case class Address (
                   street: String,
                   town: String,
                   postcode: String
                   )

case class CTRoot(ctRoot: Array[Person])
