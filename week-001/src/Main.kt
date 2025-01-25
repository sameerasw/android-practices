fun main() {
    val name = "Kotlin" //values can not be changes
    var age = 4

    age = 10

    print("Hello $name ") // use $ to access var inside " "
    println(age)

    if (age > 20) {
        println("Age is greater than 20")
    } else {
        println("Age is lower than 20")
    }

    val day = 1

    val date = if (day == 1){ // you can assign values to vars easily writing conditions
        "Monday"
    } else if (day == 2){
        "Tuesday"
    } else if (day == 3){
        "Wednesday"
    } else if (day == 4){
        "Thursday"
    } else if (day == 5){
        "Friday"
    } else if (day == 6){
        "Saturday"
    } else if (day == 7){
        "Sunday"
    } else {
        "Invalid day"
    }

    println("It's $date")

    val result = when(day){ // same as above but more readable
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Invalid day" // else is mandatory
    }

    println("It's also $result")

    var i = 0

    while (i < 10){
        print(i)
        i++

        if (i == 5){
            break
        }
    }
}