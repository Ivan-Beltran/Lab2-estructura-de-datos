fun ValidarString(input:String):Boolean{
    return !input.isNullOrBlank()
}


fun ValidarNumero(input: String): Boolean {
    if (input.isBlank()) {
        return false
    }

    return try {
        val number = input.toInt()
        number > 0
    } catch (e: NumberFormatException) {
        false
    }
}


