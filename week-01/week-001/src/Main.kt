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

    println()

    var fruits = arrayOf("Apple", "Banana", "Orange")
    var emptyArray = emptyArray<Int>() // We can use emptyArray to create an empty array instead of arrayOf(size)

    for (fruit in fruits){
        println(fruit)
    }

    for (i in 0..<9){ // 0 to 8 skipping 9
        print(i)
    }

    println()

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    println(add(2, 3))

    println("-------------------------------")

    var array = emptyArray<String>()

    fun readArray(array: Array<String>){
        for (item in array){
            print("$item ")
        }
        println()
    }

    fun addToArray(array: Array<String>, item: String): Array<String>{
        return array.plus(item)
    }

    fun updateArray(array: Array<String>, index: Int, item: String): Array<String>{
        if (index < array.size){
            array[index] = item
        }
        return array
    }

    fun removeItem(array: Array<String>, index: Int): Array<String>{
        return array.drop(index).toTypedArray()
    }

    array = addToArray(array, "Apple")
    array = addToArray(array, "Banana")
    array = addToArray(array, "Orange")

    readArray(array)

    array = updateArray(array, 1, "Pineapple")

    readArray(array)

    array = removeItem(array, 1)

    readArray(array)

    println("-------------------------------")
}