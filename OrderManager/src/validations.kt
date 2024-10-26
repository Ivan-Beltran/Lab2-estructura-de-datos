fun ValidarString(input:String):Boolean{
    return !input.isNullOrBlank()
}


fun ValidarNumero(input: String): Boolean {
    // Verificar si el input está vacío
    if (input.isBlank()) {
        return false // Retornar false si está vacío
    }

    return try {
        val number = input.toInt()
        number > 0 // Retornar true solo si el número es positivo
    } catch (e: NumberFormatException) {
        false // Retornar false si no se puede convertir a número
    }
}


